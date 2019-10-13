package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 可选项
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class OptionalApp {

    public static void main(String[] args) {
        Integer value2 = 10;
        // Optional.ofNullable - 允许传递为Null参数
        Optional<Integer> a = Optional.empty();

        // Optional.of - 如果传递的参数是Null，抛出异常NullPointerException
        Optional<Integer> b = Optional.of(value2);
        Integer result = OptionalApp.sum(a, b);
        if (log.isInfoEnabled()) {
            log.info("Calculation Result = {}", result);
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
        if (log.isInfoEnabled()) {
            log.info("第一个参数值存在 {}", a.isPresent());
            log.info("第二个参数值存在 {}", b.isPresent());
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
