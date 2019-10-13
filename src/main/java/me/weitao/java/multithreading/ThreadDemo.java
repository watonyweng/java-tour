package me.weitao.java.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Thread实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

@Slf4j
class ThreadDemo extends Thread {

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
    ThreadDemo(String name) {
        this.threadName = name;
        log.info("Creating {}", threadName);
    }

    @Override
    public void run() {
        log.info("Running {}", threadName);
        IntStream.range(1, 5)
                .forEach(i -> {
                            try {
                                log.info("Thread: {} -> {}", threadName, i);
                                // 让线程睡眠一会
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                log.error("Thread: {} interrupted.", threadName);
                                Thread.currentThread().interrupt();
                            }
                        }
                );
        log.info("Thread {} exiting.", threadName);
    }

    @Override
    public void start() {
        if (log.isInfoEnabled()) {
            log.info("Starting {}", threadName);
            // 创建线程工厂
            ThreadFactory threadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("threadPool-%d").build();
            // 创建线程池
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_POOL_SIZE, 10,
                    5, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                // 执行线程
                threadPoolExecutor.execute(new ThreadDemo(threadName));
            }
            // 关闭线程池
            threadPoolExecutor.shutdown();
        }

    }

}
