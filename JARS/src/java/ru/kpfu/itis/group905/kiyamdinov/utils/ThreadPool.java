package java.ru.kpfu.itis.group905.kiyamdinov.utils;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {

    private static Deque<Runnable> tasks;
    private PoolWorker[] pool;

    public static ThreadPool newPool(int threadsCount) {
        ThreadPool threadPool = new ThreadPool();
        tasks = new ConcurrentLinkedDeque<>();
        threadPool.pool = new PoolWorker[threadsCount];

        for (int i = 0; i < threadPool.pool.length; i++) {
            threadPool.pool[i] = new PoolWorker();
            threadPool.pool[i].start();
        }
        return threadPool;
    }


    public void submit(Runnable task) {
        synchronized (tasks) {
            tasks.addLast(task);
            tasks.notify();
        }

    }

    private static class PoolWorker extends Thread {
        @Override
        public void run() {

            Runnable task;
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException ex) {
                            throw new IllegalStateException(ex);
                        }
                    }
                    task = tasks.removeFirst();
                }
                try {
                    task.run();
                } catch (RuntimeException ex) {
                    throw new IllegalStateException(ex);
                }
            }
        }
    }
}

