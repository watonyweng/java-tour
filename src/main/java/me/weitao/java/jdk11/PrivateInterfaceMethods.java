package me.weitao.java.jdk11;

/**
 * 私有接口方法
 *
 * @author Watony Weng
 * @date 2018-11-30
 */

public class PrivateInterfaceMethods {

    public static void main(String[] args) {
        LogOracle logOracle = new LogOracle();
        logOracle.logInfo("Info");
        logOracle.logWarn("Warn");
        logOracle.logError("Error");
        logOracle.logFatal("Fatal");

        LogMySql logMySQL = new LogMySql();
        logMySQL.logInfo("Info");
        logMySQL.logWarn("Warn");
        logMySQL.logError("Error");
        logMySQL.logFatal("Fatal");
    }
}
