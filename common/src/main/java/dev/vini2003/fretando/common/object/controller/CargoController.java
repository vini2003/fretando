package dev.vini2003.fretando.common.object.controller;

import dev.vini2003.fretando.common.object.Cargo;
import dev.vini2003.fretando.common.object.controller.base.AbstractController;
import dev.vini2003.fretando.common.object.controller.base.Type;

import java.util.Collection;
import java.util.UUID;

public class CargoController {
    private static AbstractController<Cargo> instance;

    public static void initialize(String host, int port) {
        if (instance == null) {
            instance = new AbstractController<>(Type.CARGO, host, port);
        }
    }

    public static Cargo get(UUID id) {
        if (instance != null) {
            return instance.get(id);
        }
        return null;
    }

    public static Collection<Cargo> getAll() {
        if (instance != null) {
            return instance.getAll();
        }
        return null;
    }

    public static Cargo create(Cargo object) {
        if (instance != null) {
            return instance.create(object);
        }
        return null;
    }

    public static Cargo update(Cargo object) {
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
