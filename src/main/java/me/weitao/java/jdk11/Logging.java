package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

interface Logging {

    Logger LOGGER = LoggerFactory.getLogger(Logging.class);
    String ORACLE = "Oracle_Database";
    String MYSQL = "MySQL_Database";

    default void logInfo(String message) {
        getConnection();
        LOGGER.info(MessageFormat.format("Log Info Message : {0}", message));
        closeConnection();
    }

    default void logWarn(String message) {
        getConnection();
        LOGGER.warn(MessageFormat.format("Log Warn Message : {0}", message));
        closeConnection();
    }

    default void logError(String message) {
        getConnection();
        System.out.println("Log Message : " + "ERROR");
        LOGGER.error(MessageFormat.format("Log Error Message : {0}", message));
        closeConnection();
    }

    default void logFatal(String message) {
        getConnection();
        LOGGER.error(MessageFormat.format("Log Fatal Message : {0}", message));
        closeConnection();
    }

    static void getConnection() {
        LOGGER.info("Open Database Connection");
    }

    static void closeConnection() {
        LOGGER.info("Close Database Connection");
    }
}
