package ru.itis.javaLab.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;
    BufferedReader fromServer;
    PrintWriter toClients;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toClients = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void run() {
        new Thread(receiveMessageTask).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            sendMessage(scanner.nextLine());
        }
    }

    public void sendMessage(String message) {
        toClients.println(message);
    }

    public Runnable receiveMessageTask = () -> {
        String message;
        try {
            while (true) {
                message = fromServer.readLine();
                if (message != null) {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    };

}
