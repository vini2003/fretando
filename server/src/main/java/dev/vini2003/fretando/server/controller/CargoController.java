package dev.vini2003.fretando.server.controller;

import dev.vini2003.fretando.common.entity.Cargo;
import dev.vini2003.fretando.server.repository.CargoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {
    private final CargoRepository cargoRepository;

    public CargoController(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cargo>> getAllCargos() {
        return ResponseEntity.ok(cargoRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Cargo> createCargo(@RequestBody Cargo cargo) {
        return ResponseEntity.ok(cargoRepository.save(cargo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getCargoById(@PathVariable Long id) {
        var cargo = cargoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cargo not exists with id:" + id));

        return ResponseEntity.ok(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable Long id, @RequestBody Cargo cargoDetails){
        var cargo = cargoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cargo not exists with id:" + id));

        cargo.setDescription(cargoDetails.getDescription());
        cargo.setLength(cargoDetails.getLength());
        cargo.setLengthUnit(cargoDetails.getLengthUnit());
        cargo.setWidth(cargoDetails.getWidth());
        cargo.setWidthUnit(cargoDetails.getWidthUnit());
        cargo.setHeight(cargoDetails.getHeight());
        cargo.setHeightUnit(cargoDetails.getHeightUnit());
        cargo.setWeight(cargoDetails.getWeight());
        cargo.setWeightUnit(cargoDetails.getWeightUnit());

        var updatedCargo = cargoRepository.save(cargo);

        return ResponseEntity.ok(updatedCargo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCargo(@PathVariable Long id){
        var cargo = cargoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cargo not exists with id:" + id));

        cargoRepository.delete(cargo);

        var response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
