package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface FourWheeler {

    Logger LOGGER = LoggerFactory.getLogger(FourWheeler.class);

    default void print() {
        LOGGER.info("我是一辆四轮车");
    }
}
