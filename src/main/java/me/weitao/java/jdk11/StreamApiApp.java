package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.stream.Stream;

import static java.util.stream.IntStream.iterate;

/**
 * 流API的基本使用
 *
 * @author Watony Weng
 * @date 2018/11/30
 */

public class StreamApiApp {

    private static final Logger logger = LoggerFactory.getLogger(StreamApiApp.class);

    public static void main(String[] args) {
        Stream.of("a", "b", "c", "", "e", "f").dropWhile(s -> !s.isEmpty())
                .forEach(logger::info);

        iterate(3, x -> x < 10, x -> x + 3).forEach(System.out::println);

        long count = Stream.ofNullable(100).count();
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("count -> {0}", count));
        }

        count = Stream.ofNullable(null).count();
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("result count -> {0}", count));
        }
    }

}
