package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * 可选项
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

public class OptionalApp {

    private static final Logger logger = LoggerFactory.getLogger(OptionalApp.class);

    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = 10;
        // Optional.ofNullable - 允许传递为Null参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是Null，抛出异常NullPointerException
        Optional<Integer> b = Optional.of(value2);
        Integer result = OptionalApp.sum(a, b);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Calculation Result: {0}", result.intValue()));
        }
    }

    /**
     * 求和
     *
     * @param a 操作数
     * @param b 操作数
     * @return 操作结果
     */
    private static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // Optional.isPresent - 判断值是否存在
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("第一个参数值存在: {0}", a.isPresent()));
            logger.info(MessageFormat.format("第二个参数值存在: {0}", b.isPresent()));
        }

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(0);
        Integer value2 = 0;
        if (b.isPresent()) {
            value2 = b.get();
        }
        return value1 + value2;
    }
}
