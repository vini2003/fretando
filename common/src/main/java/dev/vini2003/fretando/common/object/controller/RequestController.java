package dev.vini2003.fretando.common.object.controller;

import dev.vini2003.fretando.common.object.Request;
import dev.vini2003.fretando.common.object.controller.base.AbstractController;
import dev.vini2003.fretando.common.object.controller.base.Type;

import java.util.Collection;
import java.util.UUID;

public class RequestController {
    private static AbstractController<Request> instance;

    public static void initialize(String host, int port) {
        if (instance == null) {
            instance = new AbstractController<>(Type.REQUEST, host, port);
        }
    }

    public static Request get(UUID id) {
        if (instance != null) {
            return instance.get(id);
        }
        return null;
    }

    public static Collection<Request> getAll() {
        if (instance != null) {
            return instance.getAll();
        }
        return null;
    }

    public static Request create(Request object) {
        if (instance != null) {
            return instance.create(object);
        }
        return null;
    }

    public static Request update(Request object) {
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
