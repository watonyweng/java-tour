package me.weitao.java.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.stream.IntStream;

/**
 * Thread实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

@Slf4j
class ThreadDemo extends Thread {

    private Thread thread;
    private String threadName;

    ThreadDemo(String name) {
        this.threadName = name;
        log.info(MessageFormat.format("Creating {0}", threadName));
    }

    @Override
    public void run() {
        log.info(MessageFormat.format("Running {0}", threadName));
        IntStream.range(1, 5)
                .forEach(i -> {
                            try {
                                log.info(MessageFormat.format("Thread: {0} -> {1}", threadName, i));
                                // 让线程睡眠一会
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                log.error(MessageFormat.format("Thread: {0} interrupted.", threadName));
                                Thread.currentThread().interrupt();
                            }
                        }
                );
        log.info(MessageFormat.format("Thread {0} exiting.", threadName));
    }

    @Override
    public void start() {
        if (log.isInfoEnabled()) {
            log.info(MessageFormat.format("Starting {0}", threadName));
            if (thread == null) {
                thread = new Thread(this, threadName);
                thread.start();
            }
        }

    }
}
