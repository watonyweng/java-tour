package me.weitao.java.jdk8;

/**
 * 函数式接口
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@FunctionalInterface
interface Supplier<T> {

    /**
     * 获取数据
     *
     * @return 泛型数据
     */
    T get();

}