package me.weitao.java.jdk8;

/**
 * 庞奈卡尔汽车
 *
 * @author Watony Weng
 * @date 2018/12/01
 */
class PonyCar implements Vehicle, FourWheeler {

    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        Vehicle.LOGGER.info("我是一辆汽车");
    }

}
