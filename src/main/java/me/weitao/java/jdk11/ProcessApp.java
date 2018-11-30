package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.ZoneId;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 获取进程信息
 *
 * @author Watony Weng
 * @date 2018-11-30
 */

public class ProcessApp {

    private static final Logger logger = LoggerFactory.getLogger(ProcessApp.class);

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("/Applications/iTerm.app/Contents/MacOS/iTerm2");
        String np = "Not Present";
        Process p = pb.start();
        ProcessHandle.Info info = p.info();
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Process Id : {0}", p.pid()));
            logger.info(MessageFormat.format("Command Name : {0}", info.command().orElse(np)));
            logger.info(MessageFormat.format("Command Line : {0}", info.commandLine().orElse(np)));
            logger.info(MessageFormat.format("Start Time: {0}", info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                    .toLocalDateTime().toString()).orElse(np)));
            logger.info(MessageFormat.format("Arguments: {0}", info.arguments().map(a -> Stream.of(a).collect(
                    Collectors.joining(" "))).orElse(np)));
            logger.info(MessageFormat.format("User: {0}", info.user().orElse(np)));
        }
    }
}
