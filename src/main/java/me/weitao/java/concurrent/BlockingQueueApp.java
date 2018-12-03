package me.weitao.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.*;

class Constants {
    static final int MAX_BUFFER_SIZE = 10;
    static final int NUM_OF_PRODUCER = 2;
    static final int NUM_OF_CONSUMER = 3;
}

/**
 * 任务
 *
 * @author Watony Weng
 * @date 2018/12/03
 */
class Task {
    /**
     * 任务编号
     */
    private String id;

    public Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}

/**
 * 消费者
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
class Consumer implements Runnable {

    private BlockingQueue<Task> buffer;

    public Consumer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = buffer.take();
                log.info(MessageFormat.format("Consumer[{0}] get {1}",
                        Thread.currentThread().getName(), task));
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}

/**
 * 生产者
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
class Producer implements Runnable {

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
                log.info(MessageFormat.format("Producer[{0}] put {1}",
                        Thread.currentThread().getName(), task));
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

}

/**
 * 阻塞队列
 *
 * @author Watony Weng
 * @date 2018/12/03
 */
public class BlockingQueueApp {

    public static void main(String[] args) {
        BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(Constants.MAX_BUFFER_SIZE);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Constants.NUM_OF_CONSUMER + Constants.NUM_OF_PRODUCER);
        for (int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
            scheduledExecutorService.execute(new Producer(buffer));
        }
        for (int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
            scheduledExecutorService.execute(new Consumer(buffer));
        }
    }
}
