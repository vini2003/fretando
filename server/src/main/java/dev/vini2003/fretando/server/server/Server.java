package dev.vini2003.fretando.server.server;

import dev.vini2003.fretando.common.object.base.AbstractObject;
import dev.vini2003.fretando.common.object.controller.base.Operation;
import dev.vini2003.fretando.common.object.controller.base.Type;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;

public class Server {

    private final int port;
    private final Map<Type, Map<UUID, Serializable>> databases;
    private final Logger logger = Logger.getLogger(Server.class.getName());

    public Server(int port) {
        this.port = port;

        databases = new HashMap<>();
        for (Type type : Type.values()) {
            databases.put(type, new HashMap<>());
        }
    }

    public void start() {
        try (var serverSocket = new ServerSocket(port)) {
            logger.info("Server started on port " + port);
            while (true) {
                var clientSocket = serverSocket.accept();
                logger.info("Accepted connection from " + clientSocket.getRemoteSocketAddress());

                Thread.startVirtualThread(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            logger.severe("Failed to start server: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (var objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
             var objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

            var type = (Type) objectInputStream.readObject();
            var operation = (Operation) objectInputStream.readObject();
            logger.info("Received " + operation + " request for " + type);

            var database = databases.get(type);

            switch (operation) {
                case GET -> {
                    var id = (UUID) objectInputStream.readObject();
                    var result = database.get(id);
                    objectOutputStream.writeObject(result);
                    logger.info("Sent object with id " + id);
                }
                case GET_ALL -> {
                    var result = new ArrayList<>(database.values());
                    objectOutputStream.writeObject(result);
                    logger.info("Sent all objects");
                }
                case CREATE -> {
                    var object = (AbstractObject) objectInputStream.readObject();
                    database.put(object.getId(), object);
                    objectOutputStream.writeObject(object); // Ideally, you should return some sort of identifier here.
                    logger.info("Created object with id " + object.getId());
                }
                case UPDATE -> {
                    var object = (Serializable) objectInputStream.readObject();
                    for (Map.Entry<UUID, Serializable> entry : database.entrySet()) {
                        if (entry.getValue().equals(object)) {
                            database.put(entry.getKey(), object);
                            objectOutputStream.writeObject(object);
                            logger.info("Updated object with id " + entry.getKey());
                            break;
                        }
                    }
                }
                case DELETE -> {
                    var id = (UUID) objectInputStream.readObject();
                    boolean result = database.remove(id) != null;
                    objectOutputStream.writeBoolean(result);
                    logger.info("Deleted object with id " + id);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Failed to handle client: " + e.getMessage());
        }
    }
}
