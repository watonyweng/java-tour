package me.weitao.java.multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

class RunnableDemo implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RunnableDemo.class);
    private Thread thread;
    private String threadName;

    RunnableDemo(String name) {
        threadName = name;
        logger.info(MessageFormat.format("Creating {0}", threadName));
    }

    @Override
    public void run() {
        logger.info(MessageFormat.format("Running {0}", threadName));
        try {
            for (int i = 4; i > 0; i--) {
                logger.info(MessageFormat.format("Thread: {0} => {1}", threadName, i));
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            logger.error(MessageFormat.format("Thread {0} interrupted.", threadName));
        }
        logger.info(MessageFormat.format("Thread {0} exiting.", threadName));
    }

    public void start() {
        logger.info(MessageFormat.format("Starting {0}", threadName));
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}