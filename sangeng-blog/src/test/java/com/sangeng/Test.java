package com.sangeng;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.MenuVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/13 13:06
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class Test {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ArticleMapper articleMapper;

//    @org.junit.jupiter.api.Test
//    public void test01(){
//        String encode = passwordEncoder.encode("123456");
//        System.out.println(encode);
//    }
//
    @org.junit.jupiter.api.Test
    public void test02(){
        MenuVo menuVos = menuMapper.menuList1(102L);
    }
//    @org.junit.jupiter.api.Test
//    public void test03(){
//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        list.stream().forEach( i -> i+=1);
//        System.out.println(list);
//    }
//    @org.junit.jupiter.api.Test
//    public void test04(){
//        Jedis jedis = new Jedis("8.130.14.57",6379);
//        jedis.auth("SHIquan8023");
//        jedis.set("d","c1");
//
//    }
@org.junit.jupiter.api.Test
public void test03(){
     Article article = new Article();
    article.setSummary("郭德纲");
    LambdaUpdateWrapper<Article> wrapper = new LambdaUpdateWrapper<>();
    wrapper.eq(Article::getId,21L);
    articleMapper.update(article,wrapper);
}
}
