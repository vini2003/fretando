package dev.vini2003.fretando.common.object.controller;

import dev.vini2003.fretando.common.object.Address;
import dev.vini2003.fretando.common.object.controller.base.AbstractController;
import dev.vini2003.fretando.common.object.controller.base.Type;

import java.util.Collection;
import java.util.UUID;

public class AddressController {
    private static AbstractController<Address> instance;

    public static void initialize(String host, int port) {
        if (instance == null) {
            instance = new AbstractController<>(Type.ADDRESS, host, port);
        }
    }

    public static Address get(UUID id) {
        if (instance != null) {
            return instance.get(id);
        }
        return null;
    }

    public static Collection<Address> getAll() {
        if (instance != null) {
            return instance.getAll();
        }
        return null;
    }

    public static Address create(Address object) {
        if (instance != null) {
            return instance.create(object);
        }
        return null;
    }

    public static Address update(Address object) {
        if (instance != null) {
            return instance.update(object);
        }
        return null;
    }

    public static boolean delete(UUID id) {
        if (instance != null) {
            return instance.delete(id);
        }
        return false;
    }
}
