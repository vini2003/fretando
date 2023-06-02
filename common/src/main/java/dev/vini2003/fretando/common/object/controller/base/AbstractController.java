package dev.vini2003.fretando.common.object.controller.base;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.UUID;

public class AbstractController<T> {
    private final Type type;

    private final String host;
    private final int port;

    public AbstractController(Type type, String host, int port) {
        this.type = type;

        this.host = host;
        this.port = port;
    }

    public T get(UUID id) {
        try (var socket = new Socket(host, port);
             var objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             var objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(type);
            objectOutputStream.writeObject(Operation.GET);
            objectOutputStream.writeObject(id);

            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<T> getAll() {
        try (var socket = new Socket(host, port);
             var objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             var objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(type);
            objectOutputStream.writeObject(Operation.GET_ALL);

            return (Collection<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T create(T object) {
        try (var socket = new Socket(host, port);
             var objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             var objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(type);
            objectOutputStream.writeObject(Operation.CREATE);
            objectOutputStream.writeObject(object);

            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T update(T object) {
        try (var socket = new Socket(host, port);
             var objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             var objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(type);
            objectOutputStream.writeObject(Operation.UPDATE);
            objectOutputStream.writeObject(object);

            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(UUID id) {
        try (var socket = new Socket(host, port);
             var objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             var objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(type);
            objectOutputStream.writeObject(Operation.DELETE);
            objectOutputStream.writeObject(id);

            return objectInputStream.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
