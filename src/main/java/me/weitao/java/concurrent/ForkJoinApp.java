package me.weitao.java.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinApp extends RecursiveTask<Integer> {

    private static final Logger logger = LoggerFactory.getLogger(ForkJoinApp.class);
    private int threshold = Runtime.getRuntime().availableProcessors();
    private static int count = 0;
    private int start;
    private int end;

    /*
     * 构造方法
     * @param start
     * @param end
     */
    private ForkJoinApp(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        System.out.println("开启了一条线程单独干: " + count++);
        logger.info(MessageFormat.format("开启了一条线程单独干 => {0}", count++));
        // 如果任务足够小, 就直接执行
        boolean canCompute;
        canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 任务大于阈值，分裂为2个任务
            int middle = (start + end) / 2;
            ForkJoinApp countTask1 = new ForkJoinApp(start, middle);
            ForkJoinApp countTask2 = new ForkJoinApp(middle + 1, end);
            invokeAll(countTask1, countTask2);
            Integer join1 = countTask1.join();
            Integer join2 = countTask2.join();
            // 结果合并
            sum = join1 + join2;
        }
        return sum;
    }

    /*
     * 主方法
     * @throws ExecutionException 执行异常
     * @throws InterruptedException 中断异常
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinApp forkJoinApp = new ForkJoinApp(1, 100);

        ForkJoinTask<Integer> result = forkJoinPool.submit(forkJoinApp);
        logger.info(MessageFormat.format("result => {0}", result.get()));
    }
}
