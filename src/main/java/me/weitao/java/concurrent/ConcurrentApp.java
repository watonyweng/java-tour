package me.weitao.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ConcurrentApp {

  private int coreCpuNum;
  private ExecutorService executor;
  private List<FutureTask<Long>> tasks = new ArrayList<FutureTask<Long>>();

  private ConcurrentApp() {
    // 返回Java虚拟机可用的处理器个数
    coreCpuNum = Runtime.getRuntime().availableProcessors();
    // 创建线程数量的线程池
    executor = Executors.newFixedThreadPool(coreCpuNum);
  }

  class SumCalculator implements Callable<Long> {

    int numbers[];
    int start;
    int end;

    public SumCalculator(final int numbers[], int start, int end) {
      this.numbers = numbers;
      this.start = start;
      this.end = end;
    }

    @Override
    public Long call() {
      long sum = 0;
      for (int i = start; i < end; i++) {
        sum += numbers[i];
      }
      return sum;
    }
  }

  public long sum(int[] numbers) throws ExecutionException {
    int start, end, increment;
    // 根据CPU核心个数拆分任务，创建FutureTask并提交到线程池
    for (int i = 0; i < coreCpuNum; i++) {
      increment = numbers.length / coreCpuNum + 1;
      start = i * increment;
      end = start + increment;
      if (end > numbers.length) {
        end = numbers.length;
      }
      SumCalculator calculator = new SumCalculator(numbers, start, end);
      FutureTask<Long> task = new FutureTask<Long>(calculator);
      tasks.add(task);
      if (!executor.isShutdown()) {
        executor.submit(task);
      }
    }
    return getPartSum();
  }

  public long getPartSum() throws ExecutionException {
    long sum = 0;
    for (FutureTask<Long> task : tasks) {
      try {
        sum += task.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    int arrays[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11};
    long sum = 0;
    try {
      sum = new ConcurrentApp().sum(arrays);
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println("sum -> " + sum);
  }
}
