package me.weitao.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 求和计算
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
public class SumCalculator implements Callable<Long> {

    private int[] numbers;
    private int start;
    private int end;
    private List<FutureTask<Long>> tasks = new ArrayList<FutureTask<Long>>();
    private ExecutorService executor;

    public SumCalculator(final int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.executor = Executors.newFixedThreadPool(10);
    }

    @Override
    public Long call() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public long sum(int[] numbers) throws ExecutionException {
        int increment;
        int coreCpuNum = Runtime.getRuntime().availableProcessors();
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到线程池
        for (int i = 0; i < coreCpuNum; i++) {
            increment = numbers.length / coreCpuNum + 1;
            start = i * increment;
            end = start + increment;
            if (end > numbers.length) {
                end = numbers.length;
            }
            SumCalculator calculator = new SumCalculator(numbers, start, end);
            FutureTask<Long> task = new FutureTask<>(calculator);
            tasks.add(task);
            if (!executor.isShutdown()) {
                executor.submit(task);
            }
        }
        return getPartSum();
    }

    private long getPartSum() throws ExecutionException {
        long sum = 0;
        for (FutureTask<Long> task : tasks) {
            try {
                sum += task.get();
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage());
                Thread.currentThread().interrupt();
            }
        }
        return sum;
    }
}

