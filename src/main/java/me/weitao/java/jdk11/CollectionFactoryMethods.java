package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethods {

    private static final Logger logger = LoggerFactory.getLogger(CollectionFactoryMethods.class);

    public static void main(String[] args) {
        Set<String> set = Set.of("A", "B", "C");
        logger.info(MessageFormat.format("Set is {0}", set));

        List<String> list = List.of("A", "B", "C");
        logger.info(MessageFormat.format("List is {0}", list));

        Map<String, String> map = Map.of("A", "Apple", "B", "Boy", "C", "Cat");
        logger.info(MessageFormat.format("Map is {0}", map));

        Map<String, String> entryMap = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("A", "Apple"),
                new AbstractMap.SimpleEntry<>("B", "Boy"),
                new AbstractMap.SimpleEntry<>("C", "Cat"));
        logger.info(MessageFormat.format("AbstractMap is {0}", entryMap));
    }
}
