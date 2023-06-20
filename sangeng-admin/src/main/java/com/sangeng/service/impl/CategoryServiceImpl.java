

package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.CategoryDto;
import com.sangeng.domain.dto.CategoryStatusDto;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.CategoryVo;
import com.sangeng.domain.vo.CategoryVo1;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.mapper.CategoryMapper;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author sq
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public ResponseResult listAllCategory() {
        List<Category> categoryList = list();
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBean(categoryList, CategoryVo.class);
        return ResponseResult.okResult(categoryList);
    }

    @Override
    public ResponseResult categoryList(Integer pageNum, Integer pageSize, String categoryName, String status) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(categoryName),Category::getName,categoryName);
        wrapper.eq(StringUtils.hasText(status),Category::getStatus,status);
        Page<Category> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<Category> categories = page.getRecords();
        List<CategoryVo1> categoryVo1s = BeanCopyUtils.copyBean(categories, CategoryVo1.class);
        return ResponseResult.okResult(new PageVo<CategoryVo1>(categoryVo1s,page.getTotal()));
    }

    @Override
    public ResponseResult add(CategoryDto categoryDto) {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult categoryInfo(Long categoryId) {
        Category category = getById(categoryId);
        CategoryVo1 categoryVo1 = BeanCopyUtils.copyBean(category, CategoryVo1.class);
        return ResponseResult.okResult(categoryVo1);
    }

    @Override
    public ResponseResult updateCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(Long.parseLong(categoryDto.getId()));
        category.setName(categoryDto.getName());
        category.setStatus(categoryDto.getStatus());
        category.setDescription(categoryDto.getDescription());
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(Long categoryId) {
        removeById(categoryId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult changeStatus(CategoryStatusDto categoryStatusDto) {
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Category::getId,categoryStatusDto.getCategoryId());
        wrapper.set(Category::getStatus,categoryStatusDto.getStatus());
        update(wrapper);
        return ResponseResult.okResult();
    }
}
