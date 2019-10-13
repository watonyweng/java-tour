package me.weitao.java.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 存放线程共享信号量的上下文
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
class AppContext {

    /**
     * 叉子数量(资源)
     */
    static final int NUM_OF_FORKS = 5;

    /**
     * 哲学家数量(线程)
     */
    static final int NUM_OF_PHILOSOPHERS = 5;

    /**
     * 叉子的信号量
     */
    static Semaphore[] forks;

    /**
     * 哲学家的信号量
     */
    static Semaphore counter;

    static {
        forks = new Semaphore[NUM_OF_FORKS];
        for (int i = 0, len = forks.length; i < len; ++i) {
            // 每个叉子的信号量为1
            forks[i] = new Semaphore(1);
        }
        // 如果有N个哲学家，最多只允许N-1人同时取叉子
        counter = new Semaphore(NUM_OF_PHILOSOPHERS - 1);
    }

    /**
     * 取得叉子
     *
     * @param index     第几个哲学家
     * @param leftFirst 是否先取得左边的叉子
     * @throws InterruptedException 取得叉子中断
     */
    public static void putOnFork(int index, boolean leftFirst) throws InterruptedException {
        if (leftFirst) {
            forks[index].acquire();
            forks[(index + 1) % NUM_OF_PHILOSOPHERS].acquire();
        } else {
            forks[(index + 1) % NUM_OF_PHILOSOPHERS].acquire();
            forks[index].acquire();
        }
    }

    /**
     * 放回叉子
     *
     * @param index     第几个哲学家
     * @param leftFirst 是否先放回左边的叉子
     */
    public static void putDownFork(int index, boolean leftFirst) {
        if (leftFirst) {
            forks[index].release();
            forks[(index + 1) % NUM_OF_PHILOSOPHERS].release();
        } else {
            forks[(index + 1) % NUM_OF_PHILOSOPHERS].release();
            forks[index].release();
        }
    }

}

/**
 * 哲学家
 *
 * @author Watony Weng
 * @date 2018/12/03
 */

@Slf4j
class Philosopher implements Runnable {
    /**
     * 编号
     */
    private int index;
    /**
     * 名字
     */
    private String name;

    public Philosopher(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public void run() {
        do {
            try {
                AppContext.counter.acquire();
                boolean leftFirst = index % 2 == 0;
                AppContext.putOnFork(index, leftFirst);
                // 取到两个叉子就可以进食
                log.info("{}正在吃意大利面（通心粉）...", name);
                AppContext.putDownFork(index, leftFirst);
                AppContext.counter.release();
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage());
                Thread.currentThread().interrupt();
            }
        } while (true);
    }
}

/**
 * 哲学家示例
 *
 * @author Watony Weng
 * @date 2018/12/03
 */
public class PhilosopherApp {
    /**
     * 主函数
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        // 声明哲学家名字
        String[] names = {"吃鸡大王", "李二牛", "张三丰", "杨过", "李莫愁"};
        // 构建线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5,
                TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
        for (int i = 0, len = names.length; i < len; ++i) {
            // 执行线程
            threadPoolExecutor.execute(new Philosopher(i, names[i]));
        }
    }
}
