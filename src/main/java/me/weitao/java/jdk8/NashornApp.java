package me.weitao.java.jdk8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornApp {

  public static void main(String[] args) {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
    String name = "Google";
    Integer result = null;
    try {
      scriptEngine.eval("print('" + name + "')");
      result = (Integer) scriptEngine.eval("10 + 2");
    } catch (ScriptException e) {
      System.out.println("执行脚本错误: " + e.getMessage());
    }
    System.out.println(result.toString());
  }
}
