package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.MessageFormat;

/**
 * Nashorn引擎
 * Warning: Nashorn engine is planned to be removed from a future JDK release for JDK 11
 * @author Watony Weng
 * @date 2018/12/01
 */

public class NashornApp {

    public static final Logger logger = LoggerFactory.getLogger(NashornApp.class);

    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        int result = 0;
        try {
            result = (int) scriptEngine.eval("10 / 2");
        } catch (ScriptException e) {
            logger.error(MessageFormat.format("执行脚本错误 : {0}", e.getMessage()));
        }
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("脚本执行结果 : {0}", result));
        }
    }

}
