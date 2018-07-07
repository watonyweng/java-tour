package me.weitao.java.jdk8;

class PonyCar implements Vehicle, FourWheeler {

  public void print() {
    Vehicle.super.print();
    FourWheeler.super.print();
    Vehicle.blowHorn();
    System.out.println("我是一辆汽车!");
  }

}
