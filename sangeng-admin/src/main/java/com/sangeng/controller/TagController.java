package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.TagListDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/11 23:33
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult tagList(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.contentTagList(pageNum, pageSize, tagListDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }

    @PreAuthorize("@ps.hasPermission('content:tag:index')")
    @PostMapping
    public ResponseResult addTag(@RequestBody Tag tag){
        return tagService.addTag(tag);
    }

    @PreAuthorize("@ps.hasPermission('content:tag:index')")
    @DeleteMapping("/{tagId}")
    public ResponseResult deleteTag(@PathVariable Long tagId){
        return tagService.deleteTag(tagId);
    }
    @PreAuthorize("@ps.hasPermission('content:tag:index')")
    @PutMapping
    public ResponseResult updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag);
    }
    @GetMapping("/{tagId}")
    public ResponseResult tagInfo(@PathVariable Long tagId){
        return tagService.getInfo(tagId);
    }
}
