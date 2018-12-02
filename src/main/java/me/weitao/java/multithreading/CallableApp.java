package me.weitao.java.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * Callable接口实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

@Slf4j
public class CallableApp {

    public static void main(String[] args) {

        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);
        for (int i = 0; i < CallableDemo.SIZE; i++) {
            if (log.isInfoEnabled()) {
                log.info(MessageFormat.format("{0} 的循环变量i的值 -> {1}", Thread.currentThread().getName(), i));
            }
            if (i == 20 && log.isInfoEnabled()) {
                futureTask.run();
            }
        }
        try {
            if (log.isInfoEnabled()) {
                log.info(MessageFormat.format("子线程的返回值：{0}", futureTask.get()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
