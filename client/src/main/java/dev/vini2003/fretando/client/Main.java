package dev.vini2003.fretando.client;

import com.github.javafaker.Faker;

import dev.vini2003.fretando.common.object.Address;
import dev.vini2003.fretando.common.object.Bid;
import dev.vini2003.fretando.common.object.Cargo;
import dev.vini2003.fretando.common.object.Request;
import dev.vini2003.fretando.common.object.controller.AddressController;
import dev.vini2003.fretando.common.object.controller.BidController;
import dev.vini2003.fretando.common.object.controller.CargoController;
import dev.vini2003.fretando.common.object.controller.RequestController;

import java.util.Collection;
import java.util.Currency;
import java.util.UUID;

public class Main {
    private static final Faker faker = new Faker();

    public static void main(String[] args) {
        // Initialize the controllers.
        AddressController.initialize("localhost", 8080);
        BidController.initialize("localhost", 8080);
        CargoController.initialize("localhost", 8080);
        RequestController.initialize("localhost", 8080);

        // TEST 1: CREATE
        Address address = randomAddress();
        Address createdAddress = AddressController.create(address);
        System.out.println("Created address: " + createdAddress);

        // TEST 2: GET
        UUID id = address.getId(); // replace with the id of the created address
        Address retrievedAddress = AddressController.get(id);
        System.out.println("Retrieved address: " + retrievedAddress);

        // TEST 3: GET ALL
        Collection<Address> allAddresses = AddressController.getAll();
        System.out.println("All addresses: " + allAddresses);

        // TEST 4: UPDATE
        // Modify the retrievedAddress here
        // retrievedAddress.setXXX(YYY);
        Address updatedAddress = AddressController.update(retrievedAddress);
        System.out.println("Updated address: " + updatedAddress);

        // TEST 5: DELETE
        boolean isDeleted = AddressController.delete(id);
        System.out.println("Address deletion status: " + isDeleted);

        // TEST 6: CREATE BID
        Bid bid = randomBid();
        Bid createdBid = BidController.create(bid);
        System.out.println("Created bid: " + createdBid);

        // TEST 7: GET BID
        UUID bidId = bid.getId(); // replace with the id of the created bid
        Bid retrievedBid = BidController.get(bidId);
        System.out.println("Retrieved bid: " + retrievedBid);

        // TEST 8: GET ALL BIDS
        Collection<Bid> allBids = BidController.getAll();
        System.out.println("All bids: " + allBids);

        // TEST 9: UPDATE BID
        // Modify the retrievedBid here
        // retrievedBid.setXXX(YYY);
        Bid updatedBid = BidController.update(retrievedBid);
        System.out.println("Updated bid: " + updatedBid);

        // TEST 10: DELETE BID
        boolean isBidDeleted = BidController.delete(bidId);
        System.out.println("Bid deletion status: " + isBidDeleted);

        // TEST 11: CREATE CARGO
        Cargo cargo = randomCargo();
        Cargo createdCargo = CargoController.create(cargo);
        System.out.println("Created cargo: " + createdCargo);

        // TEST 12: GET CARGO
        UUID cargoId = cargo.getId(); // replace with the id of the created cargo
        Cargo retrievedCargo = CargoController.get(cargoId);
        System.out.println("Retrieved cargo: " + retrievedCargo);

        // TEST 13: GET ALL CARGOS
        Collection<Cargo> allCargos = CargoController.getAll();
        System.out.println("All cargos: " + allCargos);

        // TEST 14: UPDATE CARGO
        // Modify the retrievedCargo here
        // retrievedCargo.setXXX(YYY);
        Cargo updatedCargo = CargoController.update(retrievedCargo);
        System.out.println("Updated cargo: " + updatedCargo);

        // TEST 15: DELETE CARGO
        boolean isCargoDeleted = CargoController.delete(cargoId);
        System.out.println("Cargo deletion status: " + isCargoDeleted);

        // TEST 16: CREATE REQUEST
        Request request = randomRequest();
        Request createdRequest = RequestController.create(request);
        System.out.println("Created request: " + createdRequest);

        // TEST 17: GET REQUEST
        UUID requestId = request.getId(); // replace with the id of the created request
        Request retrievedRequest = RequestController.get(requestId);
        System.out.println("Retrieved request: " + retrievedRequest);

        // TEST 18: GET ALL REQUESTS
        Collection<Request> allRequests = RequestController.getAll();
        System.out.println("All requests: " + allRequests);

        // TEST 19: UPDATE REQUEST
        // Modify the retrievedRequest here
        // retrievedRequest.setXXX(YYY);
        Request updatedRequest = RequestController.update(retrievedRequest);
        System.out.println("Updated request: " + updatedRequest);

        // TEST 20: DELETE REQUEST
        boolean isRequestDeleted = RequestController.delete(requestId);
        System.out.println("Request deletion status: " + isRequestDeleted);
    }

    public static Address randomAddress() {
        Address address = new Address();
        address.setId(UUID.randomUUID());
        address.setStreet(faker.address().streetName());
        address.setCity(faker.address().city());
        address.setCountry(faker.address().country());
        // Set other address properties using faker

        return address;
    }

    public static Bid randomBid() {
        Bid bid = new Bid();
        bid.setId(UUID.randomUUID());
        bid.setRequest(randomRequest());
        bid.setCurrency(Currency.getInstance(faker.currency().code()));
        bid.setCurrencyAmount(faker.number().randomDouble(2, 10, 1000));
        // Set other bid properties using faker

        return bid;
    }

    public static Cargo randomCargo() {
        Cargo cargo = new Cargo();
        cargo.setId(UUID.randomUUID());
        cargo.setLength(faker.number().randomDouble(2, 10, 100));
        cargo.setWidth(faker.number().randomDouble(2, 10, 100));
        cargo.setHeight(faker.number().randomDouble(2, 10, 100));
        cargo.setWeight(faker.number().randomDouble(2, 100, 1000));
        cargo.setDescription(faker.lorem().word());
        // Set other cargo properties using faker

        return cargo;
    }

    public static Request randomRequest() {
        Request request = new Request();
        request.setId(UUID.randomUUID());
        request.setOrigin(randomAddress());
        request.setDestination(randomAddress());
        // Set other request properties using faker

        return request;
    }
}
