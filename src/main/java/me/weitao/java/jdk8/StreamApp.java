package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamApp {

    private static final Logger logger = LoggerFactory.getLogger(StreamApp.class);

    public static void main(String[] args) {
        logger.info("使用Java 7:");
        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        logger.info(MessageFormat.format("字符串列表: {0}", strings));
        long count = getCountEmptyStringUsingJava7(strings);

        logger.info(MessageFormat.format("空字符数量为: {0}", count));
        count = getCountLength3UsingJava7(strings);
        logger.info(MessageFormat.format("字符串长度为3的数量为: {0}", count));

        // 删除空字符串
        List<String> filteredString = deleteEmptyStringsUsingJava7(strings);
        logger.info(MessageFormat.format("筛选后的列表: {0}", filteredString));

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        logger.info(MessageFormat.format("合并字符串: {0}", mergedString));

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        logger.info(MessageFormat.format("平方数列表: {0}", squaresList));

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        logger.info(MessageFormat.format("整型列表: {0}", integers));
        logger.info(MessageFormat.format("列表中最大的数: {0}", getMax(integers)));
        logger.info(MessageFormat.format("列表中最小的数: {0}", getMin(integers)));
        logger.info(MessageFormat.format("所有数之和: {0}", getSum(integers)));
        logger.info(MessageFormat.format("平均数: {0}", getAverage(integers)));

        logger.info("使用Java 8:");
        logger.info(MessageFormat.format("字符串列表: {0}", strings));
        count = strings.stream().filter(String::isEmpty).count();
        logger.info(MessageFormat.format("空字符串数量为: {0}", count));

        count = strings.stream().filter(string -> string.length() == 3).count();
        logger.info(MessageFormat.format("字符串长度为 3 的数量为: {0}", count));

        filteredString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        logger.info(MessageFormat.format("筛选后的列表: {0}", filteredString));

        mergedString = strings.stream().filter(string -> !string.isEmpty())
                .collect(Collectors.joining(", "));
        logger.info(MessageFormat.format("合并字符串: {0}", mergedString));

        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        logger.info(MessageFormat.format("平方数: {0}", squaresList));
        logger.info(MessageFormat.format("整型列表: {0}", integers));

        IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();
        logger.info(MessageFormat.format("列表中最大的数: {0}", stats.getMax()));
        logger.info(MessageFormat.format("列表中最小的数: {0}", stats.getMin()));
        logger.info(MessageFormat.format("所有数之和: {0}", stats.getSum()));
        logger.info(MessageFormat.format("平均数: {0}", stats.getAverage()));

        // 并行处理
        count = strings.parallelStream().filter(String::isEmpty).count();
        logger.info(MessageFormat.format("空字符串的数量为: {0}", count));
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<>();
        for (String string : strings) {
            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<>();
        for (Integer number : numbers) {
            Integer square = number * number;
            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    private static int getSum(List numbers) {
        int sum = (int) (numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            sum += (int) numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }

}
