package me.weitao.java.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Runnable接口实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

@Slf4j
class RunnableDemo implements Runnable {

    /**
     * 线程数量
     */
    private static final int THREAD_COUNT = 4;

    /**
     * 线程池大小
     */
    private static final int THREAD_POOL_SIZE = 2;

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
        log.info("Creating {}", threadName);
    }

    @Override
    public void run() {
        log.info("Running {}", threadName);
        try {
            for (int i = THREAD_COUNT; i > 0; i--) {
                log.info("Thread: {} => {}", threadName, i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            log.error("Thread {} interrupted.", threadName);
            Thread.currentThread().interrupt();
        }
        log.info("Thread {} exiting.", threadName);
    }

    public void start() {
        log.info("Starting {}", threadName);
        // 创建线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_POOL_SIZE, 10,
                5, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            // 执行线程
            threadPoolExecutor.execute(new RunnableDemo(threadName));
        }
        // 关闭线程池
        threadPoolExecutor.shutdown();
    }

}