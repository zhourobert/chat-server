package com.robertZhou.chat.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan("com.robertZhou.chat")
@MapperScan("com.robertZhou.chat.dao")
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class,args);
    }

    /**
     * 统一的跨域过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(corsConfigurationSource);
    }

    /**
     * 自定义的线程池
     * IO密集型 - 项目中频繁的IO访问(文件IO、网络IO)  线程池 = CPU核心数 * 2~3
     * CPU密集型 - 项目中频繁的CPU计算 线程池 = CPU核心数
     * @return
     */
    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor() {

        //CPU核心
        int cpuCores = Runtime.getRuntime().availableProcessors();

        //新建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                cpuCores * 3,
                cpuCores * 4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        return threadPoolExecutor;
    }
}
