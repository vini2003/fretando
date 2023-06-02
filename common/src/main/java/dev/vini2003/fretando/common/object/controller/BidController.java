package dev.vini2003.fretando.common.object.controller;

import dev.vini2003.fretando.common.object.Bid;
import dev.vini2003.fretando.common.object.controller.base.AbstractController;
import dev.vini2003.fretando.common.object.controller.base.Type;

import java.util.Collection;
import java.util.UUID;

public class BidController {
    private static AbstractController<Bid> instance;

    public static void initialize(String host, int port) {
        if (instance == null) {
            instance = new AbstractController<>(Type.BID, host, port);
        }
    }

    public static Bid get(UUID id) {
        if (instance != null) {
            return instance.get(id);
        }
        return null;
    }

    public static Collection<Bid> getAll() {
        if (instance != null) {
            return instance.getAll();
        }
        return null;
    }

    public static Bid create(Bid object) {
        if (instance != null) {
            return instance.create(object);
        }
        return null;
    }

    public static Bid update(Bid object) {
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
