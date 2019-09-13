package me.weitao.java.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * Runnable接口实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

@Slf4j
class RunnableDemo implements Runnable {

    /**
     * 线程对象
     */
    private Thread thread;

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 构造方法
     *
     * @param name 名称
     */
    RunnableDemo(String name) {
        threadName = name;
        log.info(MessageFormat.format("Creating {0}", threadName));
    }

    @Override
    public void run() {
        log.info(MessageFormat.format("Running {0}", threadName));
        try {
            for (int i = 4; i > 0; i--) {
                log.info(MessageFormat.format("Thread: {0} => {1}", threadName, i));
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            log.error(MessageFormat.format("Thread {0} interrupted.", threadName));
            Thread.currentThread().interrupt();
        }
        log.info(MessageFormat.format("Thread {0} exiting.", threadName));
    }

    public void start() {
        log.info(MessageFormat.format("Starting {0}", threadName));
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

}