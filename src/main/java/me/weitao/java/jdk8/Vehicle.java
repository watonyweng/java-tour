package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Vehicle {

    Logger LOGGER = LoggerFactory.getLogger(Vehicle.class);

    default void print() {
        LOGGER.info("我是一辆车");
    }

    static void blowHorn() {
        LOGGER.info("按喇叭啦");
    }

}
