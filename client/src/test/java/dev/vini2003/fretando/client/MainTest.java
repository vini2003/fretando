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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Date;
import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private static final Faker FAKER = new Faker();

    private static final Logger LOGGER = Logger.getLogger(MainTest.class.getName());

    static {
        LOGGER.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler() {
            {
                setOutputStream(System.out);
                setFormatter(new SimpleFormatter() {
                    private static final String FORMAT = "[%1$tF %1$tT] [%2$s] %3$s %n";

                    @Override
                    public synchronized String format(LogRecord record) {
                        return String.format(FORMAT,
                                new Date(record.getMillis()),
                                record.getLevel().getLocalizedName(),
                                record.getMessage()
                        );
                    }
                });
            }
        };

        LOGGER.addHandler(consoleHandler);
    }


    @BeforeAll
    public static void initialize() {
        AddressController.initialize("localhost", 8080);
        BidController.initialize("localhost", 8080);
        CargoController.initialize("localhost", 8080);
        RequestController.initialize("localhost", 8080);
    }

    @Test
    public void testAddressLifecycle() {
        // Create
        var address = randomAddress();
        LOGGER.info("Creating address: " + address);
        var createdAddress = AddressController.create(address);
        assertEquals(address, createdAddress);

        // Get
        LOGGER.info("Getting address: " + address.getId());
        var retrievedAddress = AddressController.get(address.getId());
        assertEquals(address, retrievedAddress);

        // Update
        // Modify the retrievedAddress here
        // retrievedAddress.setXXX(YYY);
        LOGGER.info("Updating address: " + retrievedAddress);
        var updatedAddress = AddressController.update(retrievedAddress);
        assertEquals(retrievedAddress, updatedAddress);

        // Get all
        LOGGER.info("Getting all addresses");
        var allAddresses = AddressController.getAll();
        assertTrue(allAddresses.contains(updatedAddress));

        // Delete
        LOGGER.info("Deleting address: " + address.getId());
        var isDeleted = AddressController.delete(address.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testBidLifecycle() {
        // Create
        var bid = randomBid();
        LOGGER.info("Creating bid: " + bid);
        var createdBid = BidController.create(bid);
        assertEquals(bid, createdBid);

        // Get
        LOGGER.info("Getting bid: " + bid.getId());
        var retrievedBid = BidController.get(bid.getId());
        assertEquals(bid, retrievedBid);

        // Update
        // Modify the retrievedBid here
        // retrievedBid.setXXX(YYY);
        LOGGER.info("Updating bid: " + retrievedBid);
        var updatedBid = BidController.update(retrievedBid);
        assertEquals(retrievedBid, updatedBid);

        // Get all
        LOGGER.info("Getting all bids");
        var allBids = BidController.getAll();
        assertTrue(allBids.contains(updatedBid));

        // Delete
        LOGGER.info("Deleting bid: " + bid.getId());
        var isDeleted = BidController.delete(bid.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testCargoLifecycle() {
        // Create
        var cargo = randomCargo();
        LOGGER.info("Creating cargo: " + cargo);
        var createdCargo = CargoController.create(cargo);
        assertEquals(cargo, createdCargo);

        // Get
        LOGGER.info("Getting cargo: " + cargo.getId());
        var retrievedCargo = CargoController.get(cargo.getId());
        assertEquals(cargo, retrievedCargo);

        // Update
        // Modify the retrievedCargo here
        // retrievedCargo.setXXX(YYY);
        LOGGER.info("Updating cargo: " + retrievedCargo);
        var updatedCargo = CargoController.update(retrievedCargo);
        assertEquals(retrievedCargo, updatedCargo);

        // Get all
        LOGGER.info("Getting all cargos");
        var allCargos = CargoController.getAll();
        assertTrue(allCargos.contains(updatedCargo));

        // Delete
        LOGGER.info("Deleting cargo: " + cargo.getId());
        var isDeleted = CargoController.delete(cargo.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testRequestLifecycle() {
        // Create
        var request = randomRequest();
        LOGGER.info("Creating request: " + request);
        var createdRequest = RequestController.create(request);
        assertEquals(request, createdRequest);

        // Get
        LOGGER.info("Getting request: " + request.getId());
        var retrievedRequest = RequestController.get(request.getId());
        assertEquals(request, retrievedRequest);

        // Update
        // Modify the retrievedRequest here
        // retrievedRequest.setXXX(YYY);
        LOGGER.info("Updating request: " + retrievedRequest);
        var updatedRequest = RequestController.update(retrievedRequest);
        assertEquals(retrievedRequest, updatedRequest);

        // Get all
        LOGGER.info("Getting all requests");
        var allRequests = RequestController.getAll();
        assertTrue(allRequests.contains(updatedRequest));

        // Delete
        LOGGER.info("Deleting request: " + request.getId());
        var isDeleted = RequestController.delete(request.getId());
        assertTrue(isDeleted);
    }

    public static Address randomAddress() {
        var address = new Address();
        address.setId(UUID.randomUUID());
        address.setStreet(FAKER.address().streetName());
        address.setCity(FAKER.address().city());
        address.setState(FAKER.address().state());
        address.setPostalCode(FAKER.address().zipCode());
        address.setCountry(FAKER.address().country());
        // Set other address properties using faker
        return address;
    }

    public static Bid randomBid() {
        var bid = new Bid();
        bid.setId(UUID.randomUUID());
        bid.setRequest(randomRequest());
        bid.setCurrency(Currency.getInstance(FAKER.currency().code()));
        bid.setCurrencyAmount(FAKER.number().randomDouble(2, 10, 1000));
        // Set other bid properties using faker
        return bid;
    }

    public static Cargo randomCargo() {
        var cargo = new Cargo();
        cargo.setId(UUID.randomUUID());
        cargo.setLength(FAKER.number().randomDouble(2, 10, 100));
        cargo.setWidth(FAKER.number().randomDouble(2, 10, 100));
        cargo.setHeight(FAKER.number().randomDouble(2, 10, 100));
        cargo.setWeight(FAKER.number().randomDouble(2, 100, 1000));
        cargo.setDescription(FAKER.lorem().word());
        // Set other cargo properties using faker
        return cargo;
    }

    public static Request randomRequest() {
        var request = new Request();
        request.setId(UUID.randomUUID());
        request.setOrigin(randomAddress());
        request.setDestination(randomAddress());
        // Set other request properties using faker
        return request;
    }
}
