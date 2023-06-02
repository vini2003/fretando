package dev.vini2003.fretando.server;

import dev.vini2003.fretando.server.server.Server;

public class Main {
    public static void main(String[] args) {
        var server = new Server(8080);
        server.start();
    }
}
