package me.weitao.java.jdk11;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiApp {

  public static void main(String[] args) {
    Stream.of("a", "b", "c", "", "e", "f").dropWhile(s -> !s.isEmpty())
        .forEach(System.out::println);

    IntStream.iterate(3, x -> x < 10, x -> x + 3).forEach(System.out::println);

    long count = Stream.ofNullable(100).count();
    System.out.println("count -> " + count);

    count = Stream.ofNullable(null).count();
    System.out.println(count);
  }
}
