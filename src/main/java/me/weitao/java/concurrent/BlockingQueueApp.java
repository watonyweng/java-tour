package me.weitao.java.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class Constants {
    static final int MAX_BUFFER_SIZE = 10;
    static final int NUM_OF_PRODUCER = 2;
    static final int NUM_OF_CONSUMER = 3;
}

class Task {
    private String id;  // 任务编号

    public Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}

class Consumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private BlockingQueue<Task> buffer;

    public Consumer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = buffer.take();
                logger.info(MessageFormat.format("Consumer[{0}] get {1}",
                        Thread.currentThread().getName(), task));
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}

class Producer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private BlockingQueue<Task> buffer;

    public Producer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = new Task();
                buffer.put(task);
                logger.info(MessageFormat.format("Producer[{0}] put {1}",
                        Thread.currentThread().getName(), task));
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

}

public class BlockingQueueApp {

    public static void main(String[] args) {
        BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(Constants.MAX_BUFFER_SIZE);
        ExecutorService es = Executors.newFixedThreadPool(Constants.NUM_OF_CONSUMER + Constants.NUM_OF_PRODUCER);
        for (int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
            es.execute(new Producer(buffer));
        }
        for (int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
            es.execute(new Consumer(buffer));
        }
    }
}
