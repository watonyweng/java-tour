package me.weitao.java.jdk10;

abstract class Handler<T> {

  public T content;

  public Handler(T content) {
    this.content = content;
  }

  abstract void handle();
}
