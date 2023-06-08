package dev.vini2003.fretando.common.util;

import com.github.javafaker.Faker;
import dev.vini2003.fretando.common.entity.Address;
import dev.vini2003.fretando.common.entity.Bid;
import dev.vini2003.fretando.common.entity.Cargo;
import dev.vini2003.fretando.common.entity.Request;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mock {
    private static final Faker FAKER = new Faker(new Locale("en-US"));

    public static Address address() {
        return new Address(
                FAKER.random().nextLong(),
                FAKER.address().streetName(),
                FAKER.address().buildingNumber(),
                FAKER.address().city(),
                FAKER.address().state(),
                FAKER.address().zipCode(),
                FAKER.address().country(),
                FAKER.lorem().sentence()
        );
    }

    public static Bid bid() {
        return new Bid(
                FAKER.random().nextLong(),
                FAKER.random().nextLong(),
                FAKER.number().randomDouble(2, 1, 100)
        );
    }

    public static Cargo cargo() {
        return new Cargo(
                FAKER.random().nextLong(),
                FAKER.number().randomDouble(2, 1, 100),
                Cargo.LengthUnit.METERS,
                FAKER.number().randomDouble(2, 1, 100),
                Cargo.LengthUnit.METERS,
                FAKER.number().randomDouble(2, 1, 100),
                Cargo.LengthUnit.METERS,
                FAKER.number().randomDouble(2, 1, 100),
                Cargo.WeightUnit.KILOGRAMS,
                FAKER.lorem().sentence()
        );
    }

    public static Request request() {
        Address origin = address();
        Address destination = address();
        Cargo cargo = cargo();

        List<Bid> bids = IntStream.range(0, 5)
                .mapToObj(i -> bid())
                .collect(Collectors.toList());

        return new Request(FAKER.random().nextLong(), origin, destination, cargo, bids);
    }
}
