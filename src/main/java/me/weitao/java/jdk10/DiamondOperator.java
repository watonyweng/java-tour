package me.weitao.java.jdk10;


public class DiamondOperator {

  public static void main(String[] args) {
    Handler<Integer> intHandler1 = new Handler<>(1) {
      @Override
      public void handle() {
        System.out.println(content);
      }
    };
    intHandler1.handle();
    Handler<? extends Number> intHandler2 = new Handler<>(2) {
      @Override
      public void handle() {
        System.out.println(content);
      }
    };
    intHandler2.handle();
    Handler<?> handler = new Handler<>("DiamondOperator") {
      @Override
      public void handle() {
        System.out.println(content);
      }
    };

    handler.handle();
  }
}
