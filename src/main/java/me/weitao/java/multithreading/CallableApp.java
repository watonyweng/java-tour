package me.weitao.java.multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableApp implements Callable<Integer> {

    private static final Logger logger = LoggerFactory.getLogger(CallableApp.class);

    public static void main(String[] args) {
        CallableApp ca = new CallableApp();
        FutureTask<Integer> ft = new FutureTask<>(ca);
        for (int i = 0; i < 100; i++) {
            logger.info(MessageFormat.format("{0} 的循环变量i的值 -> {1}", Thread.currentThread().getName(), i));
            if (i == 20) {
                new Thread(ft, "有返回值的线程").start();
            }
        }
        try {
            logger.info(MessageFormat.format("子线程的返回值：{0}", ft.get()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Integer call() {
        int i = 0;
        for (; i < 100; i++) {
            logger.info(MessageFormat.format("{0} -> {1}", Thread.currentThread().getName(), i));
        }
        return i;
    }
}
