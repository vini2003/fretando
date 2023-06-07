package dev.vini2003.fretando.server.controller;

import dev.vini2003.fretando.common.entity.Bid;
import dev.vini2003.fretando.server.repository.BidRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bid")
public class BidController {
    private final BidRepository bidRepository;

    public BidController(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bid>> getAllBids() {
        return ResponseEntity.ok(bidRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
        return ResponseEntity.ok(bidRepository.save(bid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable Long id) {
        var bid = bidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid not exists with id:" + id));
        return ResponseEntity.ok(bid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bid> updateBid(@PathVariable Long id, @RequestBody Bid bidDetails){
        var bid = bidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid not exists with id:" + id));

        bid.setRequestId(bidDetails.getRequestId());
        bid.setAmount(bidDetails.getAmount());

        Bid updatedBid = bidRepository.save(bid);
        return ResponseEntity.ok(updatedBid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBid(@PathVariable Long id){
        var bid = bidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid not exists with id:" + id));

        bidRepository.delete(bid);

        var response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
