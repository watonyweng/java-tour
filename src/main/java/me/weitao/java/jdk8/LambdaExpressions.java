package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class LambdaExpressions {

    private static final Logger logger = LoggerFactory.getLogger(LambdaExpressions.class);

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {

        // 使用类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不使用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        logger.info(MessageFormat.format("10 + 5 = {0}", LambdaExpressions.operate(10, 5, addition)));
        logger.info(MessageFormat.format("10 - 5 = {0}", LambdaExpressions.operate(10, 5, subtraction)));
        logger.info(MessageFormat.format("10 x 5 = {0}", LambdaExpressions.operate(10, 5, multiplication)));
        logger.info(MessageFormat.format("10 / 5 = {0}", LambdaExpressions.operate(10, 5, division)));

        // 不使用括号
        GreetingService greetService1 = message ->
                logger.info(MessageFormat.format("Hello {0}", message));

        // 使用括号
        GreetingService greetService2 = (message) ->
                logger.info(MessageFormat.format("Hello {0}", message));

        greetService1.sayMessage("BaiDu");
        greetService2.sayMessage("Google");
    }
}
