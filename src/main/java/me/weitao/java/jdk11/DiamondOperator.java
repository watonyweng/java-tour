package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 砖石操作类主要用于演示Handler的不同创建方式
 *
 * @author Watony Weng
 * @date 2018/11/30
 */

public class DiamondOperator {

    private static final Logger logger = LoggerFactory.getLogger(DiamondOperator.class);

    public static void main(String[] args) {
        // 使用基础包装类的形式创建Handler
        BaseHandler<Integer> intHandler = new BaseHandler<>(1) {
            @Override
            public void handle() {
                if (logger.isDebugEnabled()) {
                    logger.debug(MessageFormat.format("Content is {0}", content));
                }
            }
        };
        intHandler.handle();
        // 使用泛型继承的形式创建Handler
        BaseHandler<? extends Number> numberHandler = new BaseHandler<>(2) {
            @Override
            public void handle() {
                if (logger.isDebugEnabled()) {
                    logger.debug(MessageFormat.format("Content is {0}", content));
                }
            }
        };
        numberHandler.handle();
        // 用泛型的形式创建Handler
        BaseHandler<?> handler = new BaseHandler<>("DiamondOperator") {
            @Override
            public void handle() {
                if (logger.isDebugEnabled()) {
                    logger.debug(MessageFormat.format("Content is {0}", content));
                }
            }
        };
        handler.handle();
    }
}
