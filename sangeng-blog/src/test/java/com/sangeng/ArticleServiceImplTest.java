package com.sangeng;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.vo.ArticleDetailVo;
import com.sangeng.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/17 21:33
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class ArticleServiceImplTest {
    @Autowired
    private ArticleService articleService;
    @Test
    public void hotArticleListTest(){
        ResponseResult responseResult = articleService.hotArticleList();
        System.out.println(responseResult.getData());
    }
    @Test
    public void articleListTest(){
        ResponseResult responseResult = articleService.articleList(1,5,null);
        System.out.println(responseResult.getData());
    }
    @Test
    public void getArticleDetailTest(){
        ResponseResult articleDetail = articleService.getArticleDetail(1);
        ArticleDetailVo data = (ArticleDetailVo) articleDetail.getData();
        System.out.println(data.getCategoryName());
    }
}
