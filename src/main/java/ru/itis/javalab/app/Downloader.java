package ru.itis.javalab.app;

import com.beust.jcommander.JCommander;
import ru.itis.javalab.utils.ThreadPool;

import java.io.IOException;
import java.main.ImageDownloader;
import java.util.ArrayList;
import java.util.List;

public class Downloader {
    public static void main(String[] argv) {

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);



        if (args.mode.equals("one-thread")) {

            List<Runnable> tasks = createTasks(args);

            Thread thread;
            for (Runnable task: tasks) {
                thread = new Thread(task);
                thread.start();;
            }
        }
        else if (args.mode.equals("multi-thread")) {

            ThreadPool threadPool = ThreadPool.newPool(args.URLs.size());
            List<Runnable> tasks = createTasks(args);

            for (Runnable task: tasks) {
                threadPool.submit(task);
            }
        }
    }

    public static List<Runnable> createTasks(Args args) {

        List<Runnable> list = new ArrayList<>();

        Runnable task;
        for (String url : args.URLs) {
            task = () -> {
                int urlLen = url.length();
                try {
                    ImageDownloader.downloadImage(url, args.path, url.substring(urlLen - 5) + ".jpg");
                } catch (IOException e) {
                    throw new IllegalStateException();
                }
            };
            list.add(task);
        }

        return list;
    }
}
