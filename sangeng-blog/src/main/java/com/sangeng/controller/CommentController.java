package com.sangeng.controller;


import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.Comment;
import com.sangeng.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 处理文章评论相关的请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/3/31 19:52
 * @Since version 1.0
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "文章评论",description = "文章评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @LogPrint(BusinessName = "文章评论列表接口")
    @ApiOperation(value = "文章评论列表",notes = "查询文章评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "一页显示的文章评论条数", required = true)
    }
    )
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @GetMapping("/linkCommentList")
    @LogPrint(BusinessName = "友链评论列表接口")
    @ApiOperation(value = "友链评论列表",notes = "查询友链评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "一页显示的友链评论条数", required = true)
    }
    )
    public ResponseResult LinkCommentList(Integer pageNum,Integer pageSize){

        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
    @PostMapping
    @LogPrint(BusinessName = "添加评论接口")
    @ApiOperation(value = "添加评论",notes = "添加文章/友链评论")
    public ResponseResult addComment(@RequestBody Comment  comment){
        return commentService.add(comment);
    }
}
