package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class DiamondOperator {

    private static final Logger logger = LoggerFactory.getLogger(DiamondOperator.class);

    public static void main(String[] args) {
        // 使用基础包装类的形式创建Handler
        Handler<Integer> intHandler = new Handler<>(1) {
            @Override
            public void handle() {
                logger.info(MessageFormat.format("Content is {0}", content));
            }
        };
        intHandler.handle();
        // 使用泛型继承的形式创建Handler
        Handler<? extends Number> numberHandler = new Handler<>(2) {
            @Override
            public void handle() {
                logger.info(MessageFormat.format("Content is {0}", content));
            }
        };
        numberHandler.handle();
        // 用泛型的形式创建Handler
        Handler<?> handler = new Handler<>("DiamondOperator") {
            @Override
            public void handle() {
                logger.info(MessageFormat.format("Content is {0}", content));
            }
        };
        handler.handle();
    }
}
