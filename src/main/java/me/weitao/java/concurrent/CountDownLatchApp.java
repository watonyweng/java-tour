package me.weitao.java.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * 工作
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
class Worker {

    /**
     * 名字
     */
    private String name;
    
    /**
     * 工作持续时间
     */
    private long workDuration;

    /**
     * 构造器
     */
    public Worker(String name, long workDuration) {
        this.name = name;
        this.workDuration = workDuration;
    }

    /**
     * 完成工作
     */
    public void doWork() {
        log.info(MessageFormat.format("{0} begins to work...", name));
        try {
            // 用休眠模拟工作执行的时间
            Thread.sleep(workDuration);
        } catch (InterruptedException ex) {
            log.error(ex.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }
        log.info(MessageFormat.format("{0} has finished the job...", name));
    }
}

/**
 * 工作线程
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

class WorkerThread implements Runnable {

    private Worker worker;
    private CountDownLatch cdLatch;

    public WorkerThread(Worker worker, CountDownLatch cdLatch) {
        this.worker = worker;
        this.cdLatch = cdLatch;
    }

    @Override
    public void run() {
        // 让工人开始工作
        worker.doWork();
        // 工作完成后倒计时次数减1
        cdLatch.countDown();
    }

}

/**
 * CountDownLatch
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
public class CountDownLatchApp {

    /**
     * 最大工作时间
     */
    private static final int MAX_WORK_DURATION = 5000;

    /**
     * 最小工作时间
     */
    private static final int MIN_WORK_DURATION = 1000;

    /**
     * 产生随机的工作时间
     *
     * @return long 工作时间
     */
    private static long getRandomWorkDuration() {
        return (long) (Math.random() * (MAX_WORK_DURATION - MIN_WORK_DURATION) + MIN_WORK_DURATION);
    }

    public static void main(String[] args) {
        // 创建倒计时并指定倒计时次数为2
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker w1 = new Worker("王者", getRandomWorkDuration());
        Worker w2 = new Worker("荣耀", getRandomWorkDuration());

        // 构建线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5,
                TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
        // 执行线程
        threadPoolExecutor.execute(new WorkerThread(w1, countDownLatch));
        threadPoolExecutor.execute(new WorkerThread(w2, countDownLatch));
        try {
            // 等待倒计时减到0
            countDownLatch.await();
            log.info("All jobs have been finished...");
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }
    }

}
