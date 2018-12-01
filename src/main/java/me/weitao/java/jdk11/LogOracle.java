package me.weitao.java.jdk11;

import java.text.MessageFormat;

/**
 * MySQL日志实现
 *
 * @author Watony Weng
 * @date 2018/11/30
 */

final class LogOracle implements Logging {
    @Override
    public void logInfo(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Message is {0} for {1}", message, DatabaseType.OracleDb));
        }
    }
}
