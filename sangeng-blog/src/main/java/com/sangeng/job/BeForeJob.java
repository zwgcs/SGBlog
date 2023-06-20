package com.sangeng.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 当SpringBoot项目启动时完成将数据库中阅读量数据存入Redis工作
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/6 19:54
 * @Since version 1.0l
 */
@Component
public class BeForeJob implements CommandLineRunner {


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public void run(String... args) throws Exception {
        //将数据库中每篇文章的id与阅读量构造map存入Redis中
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Article::getId, Article::getViewCount);
        List<Article> list = articleMapper.selectList(lambdaQueryWrapper);
        ObjectMapper objectMapper = new ObjectMapper();


        Map<String, Integer> map = new HashMap<>();
        list.stream().forEach(article -> {
            map.put(article.getId().toString(), article.getViewCount().intValue());
        });
        redisCache.setCacheMap("id-viewcount-map", map);
    }
}
