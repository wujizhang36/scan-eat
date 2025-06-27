package com.snowflake.basic.manage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ConditionalOnProperty(name = "spring.snowflake.async-thread-pool.enable", havingValue = "true", matchIfMissing = true)
public class ThreadPoolConfig {

    @Value("${spring.snowflake.async-thread-pool.core-pool-size}")
    private int corePoolSize;

    @Value("${spring.snowflake.async-thread-pool.max-pool-size}")
    private int maxPoolSize;

    @Value("${spring.snowflake.async-thread-pool.queue-capacity}")
    private int queueCapacity;

    @Value("${spring.snowflake.async-thread-pool.thread-name-prefix}")
    private String threadNamePrefix;

    @Value("${spring.snowflake.async-thread-pool.keep-alive-seconds}")
    private int keepAliveSeconds;

    @Value("${spring.snowflake.async-thread-pool.allow-core-thread-time-out}")
    private boolean allowCoreThreadTimeout;

    @Value("${spring.snowflake.async-thread-pool.wait-for-tasks-to-complete-on-shutdown}")
    private boolean waitForTasksToCompleteOnShutdown;

    @Value("${spring.snowflake.async-thread-pool.await-termination-seconds}")
    private int awaitTerminationSeconds;

    /**
     * 创建一个线程池并作为 TaskExecutor 被使用
     * @return ThreadPoolTaskExecutor 线程池执行器
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeout);
        executor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);

        // 初始化线程池
        executor.initialize();
        return executor;
    }
}