package me.weitao.java.concurrent;

import java.util.concurrent.CountDownLatch;

class Worker {

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
        System.out.println(name + " begins to work...");
        try {
            Thread.sleep(workDuration); // 用休眠模拟工作执行的时间
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(name + " has finished the job...");
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

    private static final int MAX_WORK_DURATION = 5000;  // 最大工作时间
    private static final int MIN_WORK_DURATION = 1000;  // 最小工作时间

    // 产生随机的工作时间
    private static long getRandomWorkDuration(long min, long max) {
        return (long) (Math.random() * (max - min) + min);
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);   // 创建倒计时闩并指定倒计时次数为2
        Worker w1 = new Worker("王者", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));
        Worker w2 = new Worker("荣耀", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));

        new Thread(new WorkerThread(w1, countDownLatch)).start();
        new Thread(new WorkerThread(w2, countDownLatch)).start();

        try {
            countDownLatch.await();  // 等待倒计时减到0
            System.out.println("All jobs have been finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
