package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 日志接口
 *
 * @author Watony Weng
 * @date 2018/11/30
 */

public interface Logging {

    Logger logger = LoggerFactory.getLogger(Logging.class);

    /**
     * 记录日志
     *
     * @param message 消息
     */
    default void logInfo(String message) {
        getConnection();
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Log Info Message : {0}", message));
        }
        closeConnection();
    }

    /**
     * 记录日志
     *
     * @param message 消息
     */
    default void logWarn(String message) {
        getConnection();
        if (logger.isWarnEnabled()) {
            logger.warn(MessageFormat.format("Log Warn Message : {0}", message));
        }
        closeConnection();
    }

    /**
     * 记录日志
     *
     * @param message 消息
     */
    default void logError(String message) {
        getConnection();
        if (logger.isErrorEnabled()) {
            logger.error(MessageFormat.format("Log Error Message : {0}", message));
        }
        closeConnection();
    }

    /**
     * 记录日志
     *
     * @param message 消息
     */
    default void logFatal(String message) {
        getConnection();
        if (logger.isErrorEnabled()) {
            logger.error(MessageFormat.format("Log Fatal Message : {0}", message));
        }
        closeConnection();
    }

    /**
     * 打开数据库连接
     */
    static void getConnection() {
        logger.info("Open Database Connection");
    }

    /**
     * 关闭数据库连接
     */
    static void closeConnection() {
        logger.info("Close Database Connection");
    }
}
