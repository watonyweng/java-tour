package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 方法引用
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class MethodReferences {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Google");
        names.add("TaoBao");
        names.add("BaiBu");
        names.add("SoHu");

        names.forEach(log::info);

        // 构造器引用
        final Car car = Car.create(Car::new);
        final List<Car> cars = Collections.singletonList(car);

        // 静态方法引用
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用
        cars.forEach(Car::repair);

        // 特定对象的方法引用
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

}
