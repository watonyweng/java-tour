package me.weitao.java.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * Callable接口实现多线程
 *
 * @author Watony Weng
 * @date 2018/12/02
 */

@Slf4j
public class CallableDemo implements Callable<Integer> {

    static final int SIZE = 100;

    @Override
    public Integer call() {
        int i = 0;
        for (; i < SIZE; i++) {
            if (log.isInfoEnabled()) {
                log.info("{} => {}", Thread.currentThread().getName(), i);
            }
        }
        return i;
    }

}
