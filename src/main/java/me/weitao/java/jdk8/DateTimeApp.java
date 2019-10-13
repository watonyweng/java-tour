package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * 日期时间类
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class DateTimeApp {

    public static void main(String[] args) {
        DateTimeApp.getLocalDateTime();
    }

    /**
     * 获取本地日期时间
     */
    private static void getLocalDateTime() {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        if (log.isInfoEnabled()) {
            log.info("当前时间: {}", currentTime);
        }

        LocalDate currentDate = currentTime.toLocalDate();
        if (log.isInfoEnabled()) {
            log.info("当前日期: {}", currentDate);
        }

        // 根据当前时间设置日期
        LocalDateTime customDate = currentTime.withDayOfMonth(10).withYear(2012);
        if (log.isInfoEnabled()) {
            log.info("根据给定时间设置日期：{}", customDate);
        }
        // 设置日期
        LocalDate date = LocalDate.of(2018, Month.DECEMBER, 6);
        if (log.isInfoEnabled()) {
            log.info("设置日期：{}", date);
        }

        // 设置时间
        LocalTime time = LocalTime.of(22, 15);
        if (log.isInfoEnabled()) {
            log.info("设置时间：{}", time);
        }

        // 解析时间字符串
        LocalTime localTime = LocalTime.parse("20:15:30");
        if (log.isInfoEnabled()) {
            log.info("解析时间：{}", localTime);
        }
    }

}
