package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalInterfaces {

    private static final Logger logger = LoggerFactory.getLogger(FunctionalInterfaces.class);

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        logger.info("输出所有数据 =>");
        eval(list, n -> true);

        logger.info("输出所有偶数 =>");
        eval(list, n -> n % 2 == 0);

        logger.info("输出大于3的所有数字 =>");
        eval(list, n -> n > 3);
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                logger.info(MessageFormat.format("{0} ", n));
            }
        }
    }
}
