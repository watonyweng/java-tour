package me.weitao.java.multithreading;

/**
 * Thread实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

public class ThreadApp {

    public static void main(String[] args) {
        ThreadDemo threadDemo1 = new ThreadDemo("Thread-1");
        threadDemo1.start();

        ThreadDemo threadDemo2 = new ThreadDemo("Thread-2");
        threadDemo2.start();
    }
}
