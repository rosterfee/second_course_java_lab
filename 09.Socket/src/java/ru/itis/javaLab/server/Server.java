package ru.itis.javaLab.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    ServerSocket serverSocket;
    ArrayList<ClientHandler> clients = new ArrayList<>();

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void run() {
        int counter = 0;
        ClientHandler clientHandler;
        while (true) {
            try {
                clientHandler = new ClientHandler(this, serverSocket.accept());
                counter++;
                System.out.println("client " + counter + " connected to server");
                clients.add(clientHandler);
                clientHandler.run();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void broadcastMessage(String message) {
        clients.forEach(clientHandler -> clientHandler.sendMessageToClient(message));
    }

}
