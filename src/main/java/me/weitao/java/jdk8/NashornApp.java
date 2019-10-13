package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn引擎
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class NashornApp {

    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        int result = 0;
        try {
            result = (int) scriptEngine.eval("10 / 2");
        } catch (ScriptException e) {
            log.error("执行脚本错误 : {}", e.getMessage());
        }
        if (log.isInfoEnabled()) {
            log.info("脚本执行结果 : {}", result);
        }
    }

}
