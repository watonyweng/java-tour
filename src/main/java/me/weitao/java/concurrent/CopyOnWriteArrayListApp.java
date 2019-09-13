package me.weitao.java.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.*;

/**
 * 随机数线程
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
class RandomThread implements Runnable {

    private List<Double> list;
    private final int SIZE = 10000;

    public RandomThread(List<Double> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < SIZE; ++i) {
            double random = Math.random();
            list.add(random);
            log.info(MessageFormat.format("random => {0}", random));
        }
    }

}

/**
 * 复制写入对象
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

public class CopyOnWriteArrayListApp {

    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        List<Double> list = new CopyOnWriteArrayList<>();
        // 创建线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_POOL_SIZE, 10,
                5, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            // 执行线程
            threadPoolExecutor.execute(new RandomThread(list));
        }
        // 关闭线程池
        threadPoolExecutor.shutdown();
    }

}
