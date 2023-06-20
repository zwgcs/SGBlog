package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Category;
/**
 * 处理文章类别相关业务
 * @Author SQ Email:1718048299@qq.com
 * @Since version 1.0
 */
public interface CategoryService extends IService<Category> {
    /**
     * 分页查询类别列表
     * @return
     */
    ResponseResult getCategoryList();
}