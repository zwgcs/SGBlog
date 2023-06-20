package com.sangeng;

import com.sangeng.domain.vo.MenuVo;
import com.sangeng.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/13 13:06
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class Test {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuMapper menuMapper;
    @org.junit.jupiter.api.Test
    public void test01(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @org.junit.jupiter.api.Test
    public void test02(){
        MenuVo menuVos = menuMapper.menuList1(102L);
    }
    @org.junit.jupiter.api.Test
    public void test03(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream().forEach( i -> i+=1);
        System.out.println(list);
    }
    @org.junit.jupiter.api.Test
    public void test04(){
        Jedis jedis = new Jedis("8.130.14.57",6379);
        jedis.auth("SHIquan8023");
        jedis.set("c","c1");

    }
}
