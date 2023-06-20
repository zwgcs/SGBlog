package com.sangeng.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.CategoryDto;
import com.sangeng.domain.dto.CategoryStatusDto;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.ExcelCategoryVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/15 12:35
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        return categoryService.listAllCategory();
    }


    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryList = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBean(categoryList, ExcelCategoryVo.class);
            //将数据写入到excel中
            EasyExcel.write(response.getOutputStream(),ExcelCategoryVo.class).sheet("模板").doWrite(excelCategoryVos);
        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum,Integer pageSize,String name,String status){
        return categoryService.categoryList(pageNum,pageSize,name,status);
    }
    @PostMapping
    public ResponseResult add(@RequestBody CategoryDto categoryDto){
        return categoryService.add(categoryDto);
    }
    @GetMapping("/{categoryId}")
    public ResponseResult info(@PathVariable Long categoryId){
        return categoryService.categoryInfo(categoryId);
    }
    @PutMapping
    public ResponseResult update(@RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryDto);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseResult delete(@PathVariable Long categoryId){
        return categoryService.delete(categoryId);
    }

    @PutMapping("/changeStatus")
    ResponseResult changStatus(@RequestBody CategoryStatusDto categoryStatusDto){
        return categoryService.changeStatus(categoryStatusDto);
    }
}
