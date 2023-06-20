package com.sangeng;

import com.sangeng.domain.entity.OSSProperty;
import com.sangeng.utils.OSSUtils;
import com.sangeng.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/5 21:39
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class Test1 {

    @Autowired
    private OSSProperty oosProperty;


    @Autowired
    private RedisCache redisCache;

    @Value("${oos.endpoint}")
    private static String s;


    @Autowired
    private OSSUtils ossUtils;

    @Test
    public void test01(){
        System.out.println(oosProperty.getEndpoint());
    }

    @Test
    public void test02(){
        ossUtils.createBucket("sq-bucket-02");
    }
    @Test
    public void test03(){
//        String path = ossUtils.uploadFile("sq-bucket-02", "001", );
//        System.out.println(path);
    }

    @Test
    public void test04(){
        redisCache.setCacheObject("123","dsds");
    }


}
