package me.weitao.java.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RandomThread implements Runnable {
    private List<Double> list;

    public RandomThread(List<Double> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            double random = Math.random();
            list.add(random);
            System.out.println(random);
        }
    }

}

public class CopyOnWriteArrayListApp {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        List<Double> list = new CopyOnWriteArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        es.execute(new RandomThread(list));
        es.execute(new RandomThread(list));
        es.shutdown();
    }
}
