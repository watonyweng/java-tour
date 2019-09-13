package me.weitao.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

/**
 * 并发对象
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
public class ConcurrentApp {

    public static void main(String[] args) {
        int[] arrays = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11};
        long sum = 0;
        try {
            sum = new SumCalculator(arrays, 1, 1).sum(arrays);
        } catch (ExecutionException e) {
            log.error(e.getLocalizedMessage());
        }
        log.info(MessageFormat.format("sum -> {0}", sum));
    }

}
