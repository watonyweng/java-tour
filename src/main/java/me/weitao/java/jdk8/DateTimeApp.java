package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class DateTimeApp {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeApp.class);

    public static void main(String[] args) {
        DateTimeApp.getLocalDateTime();
    }

    private static void getLocalDateTime() {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        logger.info(MessageFormat.format("当前时间: {0}", currentTime));

        LocalDate currentDate = currentTime.toLocalDate();
        logger.info(MessageFormat.format("当前日期: {0}", currentDate));

        // 根据当前时间设置日期
        LocalDateTime customDate = currentTime.withDayOfMonth(10).withYear(2012);
        logger.info(MessageFormat.format("根据给定时间设置日期：{0}", customDate));

        // 设置日期
        LocalDate date = LocalDate.of(2018, Month.DECEMBER, 6);
        logger.info(MessageFormat.format("设置日期：{0}", date));

        // 设置时间
        LocalTime time = LocalTime.of(22, 15);
        logger.info(MessageFormat.format("设置时间：{0}", time));

        // 解析时间字符串
        LocalTime localTime = LocalTime.parse("20:15:30");
        logger.info(MessageFormat.format("解析时间：{0}", localTime));
    }
}
