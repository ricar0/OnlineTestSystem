package com.kaka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AppConfig {
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 设置核心线程数为10
        executor.setMaxPoolSize(20); // 设置最大线程数为20
        executor.setQueueCapacity(200); // 设置队列容量为200
        executor.setThreadNamePrefix("MyThread-"); // 设置线程名称前缀
        executor.initialize(); // 初始化线程池
        return executor;
    }
}
