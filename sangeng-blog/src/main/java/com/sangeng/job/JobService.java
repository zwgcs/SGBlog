package com.sangeng.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 通过定时任务更新数据库中阅读量数据
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/6 19:49
 * @Since version 1.0
 */
@Component
public class JobService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount() {

        LambdaUpdateWrapper<Article> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        //每隔5秒钟将Redis中的数据取出来，更新到数据库
        Map<String, Object> map = redisCache.getCacheMap("id-viewcount-map");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Long articleId = new Long(entry.getKey());
            Long viewCount = Long.parseLong(entry.getValue().toString());
            //System.out.println(articleId + "-->" + viewCount);
            Article article = new Article();
            article.setId(articleId);
            article.setViewCount(viewCount);
            articleMapper.updateById(article);
        }

    }

}
