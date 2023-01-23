package io.issc.kotlin_basics;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {
    private static ThreadPool _instance;
    public static ThreadPool getInstance() {
        if (_instance == null) {
            synchronized (ThreadPool.class) {
                _instance = new ThreadPool();
            }
        }
        return _instance;
    }

    ExecutorService exeService;
    private ThreadPool() {
        exeService = Executors.newCachedThreadPool();
    }

    public <T> Future<T> submit(Callable<T> task) {
        return exeService.submit(task);
    }

    public Future submit(Runnable task){
        return exeService.submit(task);
    }
}
