package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.LinkDto;
import com.sangeng.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/17 0:23
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum,Integer pageSize,String name,String status){
        return linkService.linkList(pageNum,pageSize,name,status);
    }
    @PostMapping
    public ResponseResult add(@RequestBody LinkDto linkDto){
        return linkService.add(linkDto);
    }
    @GetMapping("/{linkId}")
    public ResponseResult info(@PathVariable Long linkId){
        return linkService.linkInfo(linkId);
    }
    @PutMapping
    private ResponseResult update(@RequestBody LinkDto linkDto){
        return linkService.updateLink(linkDto);
    }
    @DeleteMapping("/{linkId}")
    public ResponseResult delete(@PathVariable Long linkId){
        return linkService.delete(linkId);
    }
}
