package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 四驱机车接口
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

interface FourWheeler {

    Logger LOGGER = LoggerFactory.getLogger(FourWheeler.class);

    /**
     * 打印信息
     */
    default void print() {
        LOGGER.info("我是一辆四轮车");
    }

}
