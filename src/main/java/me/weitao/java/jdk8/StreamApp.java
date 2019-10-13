package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据流操作
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class StreamApp {

    public static void main(String[] args) {
        if (log.isInfoEnabled()) {
            log.info("使用Java 7:");
            // 计算空字符串
            List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
            log.info("字符串列表: {}", strings);
            long count = getCountEmptyStringUsingJava7(strings);

            log.info("空字符数量为: {}", count);
            count = getCountLength3UsingJava7(strings);
            log.info("字符串长度为3的数量为: {}", count);

            // 删除空字符串
            List<String> filteredString = deleteEmptyStringsUsingJava7(strings);
            log.info("筛选后的列表: {}", filteredString);

            // 删除空字符串，并使用逗号把它们合并起来
            String mergedString = getMergedStringUsingJava7(strings, ", ");
            log.info("合并字符串: {}", mergedString);

            List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
            // 获取列表元素平方数
            List<Integer> squaresList = getSquares(numbers);
            log.info("平方数列表: {}", squaresList);

            List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
            log.info("整型列表: {}", integers);
            log.info("列表中最大的数: {}", getMaxOrMin(integers, true));
            log.info("列表中最小的数: {}", getMaxOrMin(integers, false));
            log.info("所有数之和: {}", getSum(integers));
            log.info("平均数: {}", getAverage(integers));

            log.info("使用Java 8:");
            log.info("字符串列表: {}", strings);
            count = strings.stream().filter(String::isEmpty).count();
            log.info("空字符串数量为: {}", count);

            count = strings.stream().filter(string -> string.length() == 3).count();
            log.info("字符串长度为 3 的数量为: {}", count);

            filteredString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
            log.info("筛选后的列表: {}", filteredString);

            mergedString = strings.stream().filter(string -> !string.isEmpty())
                    .collect(Collectors.joining(", "));
            log.info("合并字符串: {}", mergedString);

            squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
            log.info("平方数: {}", squaresList);
            log.info("整型列表: {}", integers);

            IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();
            log.info("列表中最大的数: {}", stats.getMax());
            log.info("列表中最小的数: {}", stats.getMin());
            log.info("所有数之和: {}", stats.getSum());
            log.info("平均数: {}", stats.getAverage());

            // 并行处理
            count = strings.parallelStream().filter(String::isEmpty).count();
            log.info("空字符串的数量为: {}", count);
        }
    }

    /**
     * 统计空字符串的个数
     *
     * @param strings 操作数
     * @return 统计个数
     */
    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;
        // 遍历数组
        for (String string : strings) {
            // 判断是否为空，若不为空则累加
            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    /**
     * 统计空字符串的个数
     *
     * @param strings 操作数
     * @return 统计个数
     */
    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    /**
     * 删除空字符串
     *
     * @param strings 操作数
     * @return 删除空字符串后的数组
     */
    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<>();
        for (String string : strings) {
            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    /**
     * 合并字符串
     *
     * @param strings   字符串数组
     * @param separator 操作符
     * @return 合并后的字符串
     */
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

    /**
     * 计算平方数
     *
     * @param numbers 操作数
     * @return 计算结果数组
     */
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

    /**
     * 计算最大值或最小值
     *
     * @param numbers 操作数
     * @return result 计算结果
     */
    private static int getMaxOrMin(List<Integer> numbers, boolean isMax) {
        int max = numbers.get(0);
        int min = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (isMax) {
                if (number > max) {
                    max = number;
                }
            } else {
                if (number < min) {
                    min = number;
                }
            }
        }
        return isMax ? max : min;
    }

    /**
     * 求和
     *
     * @param numbers 操作数
     * @return 计算结果
     */
    private static int getSum(List numbers) {
        int sum = (int) (numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            sum += (int) numbers.get(i);
        }
        return sum;
    }

    /**
     * 求平均值
     *
     * @param numbers 操作数
     * @return 计算结果
     */
    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }

}
