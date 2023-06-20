package com.sangeng;

import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.ArticleVo;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.BeanCopyUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/26 20:41
 * @Since version 1.0
 */
@SpringBootTest
public class Test2 {
    @Autowired
    private ArticleService articleService;
    @Test
    public void test(){
        Article article = articleService.getById(1);
//        System.out.println(article);
        Object articleVo = ConvertUtils.convert(article, ArticleVo.class);
        System.out.println(articleVo);
        ArticleVo articleVo1 = BeanCopyUtils.copyBean(article, ArticleVo.class);
        System.out.println(articleVo1);
    }
}
