package com.sangeng;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/17 22:51
 * @Description
 * @Since version 1.0
 */
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;
    @Test
    public void test01(){
        ResponseResult categoryList = categoryService.getCategoryList();
        System.out.println(categoryList.getData());
    }
}
