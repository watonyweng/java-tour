package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 车辆接口
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

interface Vehicle {

    Logger logger = LoggerFactory.getLogger(Vehicle.class);

    /**
     * 打印信息
     */
    default void print() {
        logger.info("我是一辆车");
    }

    /**
     * 按喇叭
     */
    static void blowHorn() {
        logger.info("按喇叭啦");
    }

}
