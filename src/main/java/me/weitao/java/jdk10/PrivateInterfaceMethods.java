package me.weitao.java.jdk10;

public class PrivateInterfaceMethods {

  public static void main(String[] args) {
    LogOracle log = new LogOracle();
    log.logInfo("");
    log.logWarn("");
    log.logError("");
    log.logFatal("");

    LogMySQL log1 = new LogMySQL();
    log1.logInfo("");
    log1.logWarn("");
    log1.logError("");
    log1.logFatal("");
  }
}
