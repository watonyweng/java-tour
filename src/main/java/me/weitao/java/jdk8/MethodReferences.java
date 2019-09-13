package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 方法引用
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

public class MethodReferences {

    private static final Logger logger = LoggerFactory.getLogger(MethodReferences.class);

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Google");
        names.add("TaoBao");
        names.add("BaiBu");
        names.add("SoHu");

        names.forEach(logger::info);

        // 构造器引用
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        // 静态方法引用
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用
        cars.forEach(Car::repair);

        // 特定对象的方法引用
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

}
