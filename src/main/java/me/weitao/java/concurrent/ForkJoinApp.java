package me.weitao.java.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinApp extends RecursiveTask<Integer> {

  public static final int THRESHOLD = 2;
  private volatile static int count = 0;
  private int start;
  private int end;

  public ForkJoinApp(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  protected Integer compute() {
    int sum = 0;
    System.out.println("开启了一条线程单独干: " + count++);
    // 如果任务足够小, 就直接执行
    boolean canCompute = (end - start) <= THRESHOLD;
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

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ForkJoinPool forkJoinPool = new ForkJoinPool();

    ForkJoinApp forkJoinApp = new ForkJoinApp(1, 100);
    ForkJoinTask<Integer> result = forkJoinPool.submit(forkJoinApp);
    System.out.println(result.get());
  }
}
