package me.weitao.java.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;

class Worker {

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);
    private String name;        // 名字
    private long workDuration;  // 工作持续时间

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
        logger.info(MessageFormat.format("{0} begins to work...", name));
        try {
            Thread.sleep(workDuration); // 用休眠模拟工作执行的时间
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        logger.info(MessageFormat.format("{0} has finished the job...", name));
    }
}

class WorkerThread implements Runnable {

    private Worker worker;
    private CountDownLatch cdLatch;

    public WorkerThread(Worker worker, CountDownLatch cdLatch) {
        this.worker = worker;
        this.cdLatch = cdLatch;
    }

    @Override
    public void run() {
        worker.doWork();        // 让工人开始工作
        cdLatch.countDown();    // 工作完成后倒计时次数减1
    }
}

public class CountDownLatchApp {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchApp.class);
    private static final int MAX_WORK_DURATION = 5000;  // 最大工作时间
    private static final int MIN_WORK_DURATION = 1000;  // 最小工作时间

    // 产生随机的工作时间
    private static long getRandomWorkDuration() {
        return (long) (Math.random() * (MAX_WORK_DURATION - MIN_WORK_DURATION) + MIN_WORK_DURATION);
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);   // 创建倒计时闩并指定倒计时次数为2
        Worker w1 = new Worker("王者", getRandomWorkDuration());
        Worker w2 = new Worker("荣耀", getRandomWorkDuration());

        new Thread(new WorkerThread(w1, countDownLatch)).start();
        new Thread(new WorkerThread(w2, countDownLatch)).start();

        try {
            countDownLatch.await();  // 等待倒计时减到0
            logger.info("All jobs have been finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
