package net.engineeringdigest.journalApp2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
       // redisTemplate.opsForValue().set("email", "tasfiquerishad127@gmail.com");

        Object email = redisTemplate.opsForValue().get("username");
        int a=1;
    }


}
