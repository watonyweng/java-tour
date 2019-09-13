package me.weitao.java.multithreading;

/**
 * Runnable接口实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

public class RunnableApp {

    public static void main(String[] args) {
        RunnableDemo runnableDemo1 = new RunnableDemo("Thread-1");
        runnableDemo1.start();

        RunnableDemo runnableDemo2 = new RunnableDemo("Thread-2");
        runnableDemo2.start();
    }

}
