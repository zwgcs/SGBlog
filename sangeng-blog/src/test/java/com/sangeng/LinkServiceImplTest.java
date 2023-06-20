package com.sangeng;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/18 22:26
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class LinkServiceImplTest {
    @Autowired
    private LinkService linkService;
    @Test
    public void getAllLinkTest(){
        ResponseResult allLink = linkService.getAllLink();
        System.out.println(allLink);
    }
}
