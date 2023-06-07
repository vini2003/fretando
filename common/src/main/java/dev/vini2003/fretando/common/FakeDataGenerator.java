package dev.vini2003.fretando.common;

import com.github.javafaker.Faker;
import dev.vini2003.fretando.common.entity.Address;
import dev.vini2003.fretando.common.entity.Bid;
import dev.vini2003.fretando.common.entity.Cargo;
import dev.vini2003.fretando.common.entity.Request;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FakeDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    public static Address createFakeAddress() {
        return new Address(
                faker.random().nextLong(),
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.address().country(),
                faker.lorem().sentence()
        );
    }

    public static Bid createFakeBid() {
        return new Bid(
                faker.random().nextLong(),
                faker.random().nextLong(),
                faker.number().randomDouble(2, 1, 100)
        );
    }

    public static Cargo createFakeCargo() {
        return new Cargo(
                faker.random().nextLong(),
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
                .mapToObj(i -> createFakeBid())
                .collect(Collectors.toList());

        return new Request(faker.random().nextLong(), origin, destination, cargo, bids);
    }
}
