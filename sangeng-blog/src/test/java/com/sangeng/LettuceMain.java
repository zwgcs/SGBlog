package com.sangeng;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/10 9:29
 * @Description
 * @Since version 1.0
 */
public class LettuceMain {

    public static void main(String[] args) {
        RedisURI redisUri = RedisURI.builder()
                .withHost("121.40.214.104")
                .withPort(6379)
                .withPassword("SHIquan8023")
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        System.out.println(commands.ping());
        connection.close();
        redisClient.shutdown();
    }
}
