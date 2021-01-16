package ru.itis.javaLab.client;

import com.beust.jcommander.JCommander;
import ru.itis.javaLab.args.Args;

public class ClientMain {

    public static void main(String[] argv) {

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Client client = new Client(args.ip, args.port) ;
        client.run();
    }
}
