package me.weitao.java.jdk11;

/**
 * 抽象Handler
 *
 * @author Watony Weng
 * @date 2018/11/30
 */
abstract class BaseHandler<T> {

    /**
     * 内容
     */
    T content;

    /**
     * 构造方法
     */
    BaseHandler(T content) {
        this.content = content;
    }

    /**
     * 处理数据
     */
    abstract void handle();

}
