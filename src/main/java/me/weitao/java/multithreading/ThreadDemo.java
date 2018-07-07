package me.weitao.java.multithreading;

import java.util.stream.IntStream;

class ThreadDemo extends Thread {

  private Thread thread;
  private String threadName;

  ThreadDemo(String name) {
    threadName = name;
    System.out.println("Creating " + threadName);
  }

  @Override
  public void run() {
    System.out.println("Running " + threadName);
    IntStream.range(1, 5)
      .forEach(i -> {
          try {
            System.out.println("Thread: " + threadName + " -> " + i);
            // 让线程睡眠一会
            Thread.sleep(50);
          } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
          }
        }
      );
    System.out.println("Thread " + threadName + " exiting.");
  }

  public void start() {
    System.out.println("Starting " + threadName);
    if (thread == null) {
      thread = new Thread(this, threadName);
      thread.start();
    }
  }
}
