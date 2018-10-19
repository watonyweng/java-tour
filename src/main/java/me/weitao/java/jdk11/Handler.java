package me.weitao.java.jdk11;

abstract class Handler<T> {

    T content;

    Handler(T content) {
        this.content = content;
    }

    abstract void handle();
}
