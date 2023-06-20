package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.CategoryDto;
import com.sangeng.domain.dto.CategoryStatusDto;
import com.sangeng.domain.entity.Category;

public interface CategoryService extends IService<Category> {

    /**
     * 查询所有的文章分类
     * @return
     */
    ResponseResult listAllCategory();

    /**
     * 分页查询分类列表信息
     * @param pageNum 页码数
     * @param pageSize 页码大小
     * @param categoryName 类别名称
     * @param status 类别状态
     * @return
     */
    ResponseResult categoryList(Integer pageNum,Integer pageSize,String categoryName,String status);

    /**
     * 添加分类
     * @param categoryDto 分类信息
     * @return
     */
    ResponseResult add(CategoryDto categoryDto);

    /**
     * 查询分类信息
     * @param categoryId
     * @return
     */
    ResponseResult categoryInfo(Long categoryId);

    /**
     * 更改分类信息
     * @param categoryDto 更改之后的分类信息
     * @return
     */
    ResponseResult updateCategory(CategoryDto categoryDto);

    /**
     * 删除分类
     * @param categoryId 要删除的分类Id
     * @return
     */
    ResponseResult delete(Long categoryId);

    /**
     * 修改类别状态
     * @param categoryStatusDto 修改后的类别状态Id
     * @return
     */
    ResponseResult changeStatus(CategoryStatusDto categoryStatusDto);
}