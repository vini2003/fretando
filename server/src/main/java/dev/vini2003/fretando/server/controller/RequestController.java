package dev.vini2003.fretando.server.controller;

import dev.vini2003.fretando.common.entity.Request;
import dev.vini2003.fretando.server.repository.RequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(requestRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        return ResponseEntity.ok(requestRepository.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        var request = requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not exists with id:" + id));

        return ResponseEntity.ok(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request requestDetails){
        var request = requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not exists with id:" + id));

        request.setOrigin(requestDetails.getOrigin());
        request.setDestination(requestDetails.getDestination());
        request.setCargo(requestDetails.getCargo());
        request.setBids(requestDetails.getBids());

        var updatedRequest = requestRepository.save(request);

        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRequest(@PathVariable Long id){
        var request = requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not exists with id:" + id));

        requestRepository.delete(request);

        var response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
