package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 集合工厂方法
 *
 * @author Watony Weng
 * @date 2018/11/30
 */

public class CollectionFactoryMethods {

    private static final Logger logger = LoggerFactory.getLogger(CollectionFactoryMethods.class);

    public static void main(String[] args) {
        Set<String> set = Set.of("A", "B", "C");
        if (logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("Set is {0}", set));
        }

        List<String> list = List.of("A", "B", "C");
        if (logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("List is {0}", list));
        }

        Map<String, String> map = Map.of("A", "Apple", "B", "Boy", "C", "Cat");
        if (logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("Map is {0}", map));
        }

        Map<String, String> entryMap = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("A", "Apple"),
                new AbstractMap.SimpleEntry<>("B", "Boy"),
                new AbstractMap.SimpleEntry<>("C", "Cat"));
        if (logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("AbstractMap is {0}", entryMap));
        }
    }

}
