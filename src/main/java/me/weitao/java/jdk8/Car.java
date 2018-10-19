package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

class Car {

    private static final Logger logger = LoggerFactory.getLogger(Car.class);

    protected static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    protected static void collide(final Car car) {
        logger.info(MessageFormat.format("Collided {0}", car.toString()));
    }

    protected void follow(final Car another) {
        logger.info(MessageFormat.format("Following the {0}", another.toString()));
    }

    protected void repair() {
        logger.info(MessageFormat.format("Repaired {0}", this.toString()));
    }

}
