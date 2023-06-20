package com.sangeng;

import com.sangeng.domain.ResponseResult;
import com.sangeng.constants.SystemConstants;
import com.sangeng.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 21:19
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void test01(){
        ResponseResult responseResult = commentService.commentList(SystemConstants.ARTICLE_COMMENT,1L, 1, 10);
        System.out.println(responseResult.getData());
    }
}
