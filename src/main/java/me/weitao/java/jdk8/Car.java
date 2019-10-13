package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

/**
 * 汽车类
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class Car {


    protected static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    protected static void collide(final Car car) {
        if (log.isInfoEnabled()) {
            log.info("Collided {}", car.toString());
        }
    }

    protected void follow(final Car another) {
        if (log.isInfoEnabled()) {
            log.info("Following the {}", another.toString());
        }

    }

    protected void repair() {
        if (log.isInfoEnabled()) {
            log.info("Repaired {}", this.toString());
        }
    }

}
