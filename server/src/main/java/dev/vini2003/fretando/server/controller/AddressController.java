package dev.vini2003.fretando.server.controller;

import dev.vini2003.fretando.common.entity.Address;
import dev.vini2003.fretando.server.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressRepository.save(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        var address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not exists with id:" + id));
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails){
        var address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not exists with id:" + id));

        address.setStreet(addressDetails.getStreet());
        address.setNumber(addressDetails.getNumber());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setCountry(addressDetails.getCountry());
        address.setNotes(addressDetails.getNotes());

        Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAddress(@PathVariable Long id){
        var address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not exists with id:" + id));

        addressRepository.delete(address);

        var response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
