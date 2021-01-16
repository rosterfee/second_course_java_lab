package ru.itis.javaLab.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {

    Server server;
    BufferedReader fromClient;
    PrintWriter toClient;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toClient = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void run() {
        new Thread(receiveMessageFromClient).start();
    }

    public Runnable receiveMessageFromClient = () -> {
        String message;
        try {
            while (true) {
                message = fromClient.readLine();
                if (message != null) {
                    server.broadcastMessage(message);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    };

    public void sendMessageToClient(String message) {
        toClient.println(message);
    }

}
