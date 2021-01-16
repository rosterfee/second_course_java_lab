package ru.itis.javaLab.server;

import com.beust.jcommander.JCommander;
import ru.itis.javaLab.args.Args;

public class ServerMain {
    public static void main(String[] argv) {

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        new Server(args.port).run();
    }
}
