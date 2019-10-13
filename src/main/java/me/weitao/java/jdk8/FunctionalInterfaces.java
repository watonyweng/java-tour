package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class FunctionalInterfaces {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        log.info("输出所有数据 =>");
        eval(list, n -> true);

        log.info("输出所有偶数 =>");
        eval(list, n -> n % 2 == 0);

        log.info("输出大于3的所有数字 =>");
        eval(list, n -> n > 3);
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n) && log.isInfoEnabled()) {
                log.info("{} ", n);
            }
        }
    }

}
