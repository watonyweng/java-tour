package me.weitao.java.jdk11;

public class PrivateInterfaceMethods {

    public static void main(String[] args) {
        LogOracle logOracle = new LogOracle();
        logOracle.logInfo("Info");
        logOracle.logWarn("Warn");
        logOracle.logError("Error");
        logOracle.logFatal("Fatal");

        LogMySQL logMySQL = new LogMySQL();
        logMySQL.logInfo("Info");
        logMySQL.logWarn("Warn");
        logMySQL.logError("Error");
        logMySQL.logFatal("Fatal");
    }
}
