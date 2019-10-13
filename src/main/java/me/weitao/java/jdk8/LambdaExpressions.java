package me.weitao.java.jdk8;

import lombok.extern.slf4j.Slf4j;

/**
 * Lambda表达式
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

@Slf4j
public class LambdaExpressions {

    /**
     * 数字操作
     */
    interface MathOperation {
        /**
         * 具体执行哪个操作
         *
         * @param a 操作数
         * @param b 操作数
         * @return int 操作结果
         */
        int operation(int a, int b);
    }

    /**
     * 问候服务
     */
    interface GreetingService {
        /**
         * 发出问候
         *
         * @param message 消息
         */
        void sayMessage(String message);
    }

    /**
     * @param a             操作数
     * @param b             操作数
     * @param mathOperation 操作对象
     * @return result 结果
     */
    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {

        // 使用类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不使用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> a * b;

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        if (log.isInfoEnabled()) {
            log.info("10 + 5 = {}", LambdaExpressions.operate(10, 5, addition));
            log.info("10 - 5 = {}", LambdaExpressions.operate(10, 5, subtraction));
            log.info("10 x 5 = {}", LambdaExpressions.operate(10, 5, multiplication));
            log.info("10 / 5 = {}", LambdaExpressions.operate(10, 5, division));
        }

        // 不使用括号
        GreetingService greetService1 = message -> {
            if (log.isInfoEnabled()) {
                log.info("Hello {}", message);
            }
        };


        // 使用括号
        GreetingService greetService2 = (message) -> {
            if (log.isInfoEnabled()) {
                log.info("Hello {}", message);
            }
        };

        greetService1.sayMessage("BaiDu");
        greetService2.sayMessage("Google");
    }

}
