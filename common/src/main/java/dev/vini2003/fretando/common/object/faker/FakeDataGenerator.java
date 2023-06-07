package dev.vini2003.fretando.common.object.faker;

import com.github.javafaker.Faker;
import dev.vini2003.fretando.common.object.Address;
import dev.vini2003.fretando.common.object.Bid;
import dev.vini2003.fretando.common.object.Cargo;
import dev.vini2003.fretando.common.object.Request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FakeDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    public static Address createFakeAddress() {
        return new Address(
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.address().country(),
                faker.lorem().sentence()
        );
    }

    public static Bid createFakeBid(UUID request) {
        return new Bid(
                request,
                Currency.getInstance(faker.currency().code()),
                faker.number().randomDouble(2, 1, 100)
        );
    }

    public static Cargo createFakeCargo() {
        return new Cargo(
                faker.number().randomDouble(2, 1, 100),
                Cargo.LengthUnit.METERS,
                faker.number().randomDouble(2, 1, 100),
                Cargo.LengthUnit.METERS,
                faker.number().randomDouble(2, 1, 100),
                Cargo.LengthUnit.METERS,
                faker.number().randomDouble(2, 1, 100),
                Cargo.WeightUnit.KILOGRAMS,
                faker.lorem().sentence()
        );
    }

    public static Request createFakeRequest() {
        Address origin = createFakeAddress();
        Address destination = createFakeAddress();
        Cargo cargo = createFakeCargo();

        List<Bid> bids = IntStream.range(0, faker.number().numberBetween(1, 5))
                .mapToObj(i -> createFakeBid(null)) // Passing null for Request, because at this point we haven't created the Request yet
                .collect(Collectors.toList());

        Request request = new Request(origin, destination, cargo);
        request.setBids(bids);

        // Now we can set the Request in each Bi

        return request;
    }
}
