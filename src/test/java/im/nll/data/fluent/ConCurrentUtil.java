package im.nll.data.fluent;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzq fivesmallq@gmail.com
 * @time 2011-6-14下午03:24:37 并发线程工具类
 */
public class ConCurrentUtil {
    private static final Logger logger = LogManager
            .getLogger(ConCurrentUtil.class.getName());
    /**
     * 默认线程池大小
     */
    public static int DEFAULT_POOL_SIZE = 5;

    /**
     * 默认一个任务的超时时间，单位为毫秒
     */
    public static final long DEFAULT_TASK_TIMEOUT = 100000;

    static ExecutorService executorService;
    public SimpleTimeLimiter timeLimiter;
    static boolean isInited = false;

    public synchronized static void init(int poolSize) {
        if (isInited) {
            return;
        }
        try {
            DEFAULT_POOL_SIZE = poolSize;
        } catch (Exception e) {
            logger.warn("pool size not found .use default 5");
        }
        try {
            if (executorService == null) {
                logger.info("init executorService...");
                executorService = Executors
                        .newFixedThreadPool(DEFAULT_POOL_SIZE);
                isInited = true;
                logger.info("init executorService success");
            }
        } catch (Exception e) {
            logger.warn("init executorService error", e);
            throw new RuntimeException("init executorService error");
        }
    }

    /**
     * 使用线程池中的线程来执行任务
     */
    public static void execute(Runnable task) {
        try {
            executorService.execute(task);
        } catch (Exception e) {
            logger.warn("execute job error", e);
            throw new RuntimeException("execute job error");
        }
    }

    /**
     * 关闭当前ExecutorService
     *
     * @param timeout 以毫秒为单位的超时时间
     */
    public static void shutdown(long timeout) {
        try {
            if (executorService != null && !executorService.isShutdown()) {
                try {
                    executorService.awaitTermination(timeout,
                            TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executorService.shutdown();
            }
        } catch (Exception e) {
            // 强制关闭
            executorService.shutdownNow();
            logger.warn("shutdown executorService error", e);
            throw new RuntimeException("shutdown executorService error");
        }
    }

    /**
     * 关闭当前ExecutorService,默认延时3秒
     */
    public static void shutdown() {
        try {
            if (executorService != null && !executorService.isShutdown()) {
                try {
                    executorService.awaitTermination(3000,
                            TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executorService.shutdown();
            }
        } catch (Exception e) {
            // 强制关闭
            executorService.shutdownNow();
            logger.warn("shutdown executorService error", e);
            throw new RuntimeException("shutdown executorService error");
        }
    }

    /**
     * 关闭当前ExecutorService，创建新的ExecutorService
     */
    public static void createExecutorService() {
        shutdown(1000);
        executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
    }

    public static SimpleTimeLimiter getTimeLimiter() {
        if (!ConCurrentUtil.isInited) {
            ConCurrentUtil.init(DEFAULT_POOL_SIZE);
        }
        return new SimpleTimeLimiter(executorService);

    }

}
