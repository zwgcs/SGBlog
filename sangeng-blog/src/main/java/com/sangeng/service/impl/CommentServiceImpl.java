

package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.CommentVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.service.CommentService;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Comment::getRootId, -1);
        lambdaQueryWrapper.eq("0".equals(commentType), Comment::getArticleId, articleId);
        lambdaQueryWrapper.eq(Comment::getType, commentType);
        Page<Comment> page = new Page(pageNum, pageSize);
        this.page(page, lambdaQueryWrapper);
        List<Comment> commentList = page.getRecords();
        List<CommentVo> commentVoList = toCommentVoList(commentList);
        commentVoList.stream().forEach(o -> {
            List<CommentVo> children = getChildren(o.getId());
            o.setChildren(children);
        });
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult add(Comment comment) {
        if (!Strings.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        } else {
            save(comment);
            return ResponseResult.okResult();
        }
    }

    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVoList = BeanCopyUtils.copyBean(commentList, CommentVo.class);
        Iterator<CommentVo> iterator = commentVoList.iterator();

        while (iterator.hasNext()) {
            CommentVo vo = iterator.next();
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            if (vo != null && userService.getOne(wrapper.eq(User::getId, vo.getCreateBy())) != null) {
                vo.setUsername((userService.getOne(wrapper.eq(User::getId, vo.getCreateBy()))).getNickName());
                vo.setAvatar((userService.getOne(wrapper.eq(User::getId, vo.getCreateBy()))).getAvatar());
            }
        }

        commentVoList.stream().filter(o -> {
            if (o != null) {
                return o.getToCommentUserId() != -1L;
            } else {
                return false;
            }
        }).forEach(o -> {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            if (o != null && this.userService.getOne(wrapper.eq(User::getId, o.getToCommentUserId())) != null) {
                o.setToCommentUserName((userService.getOne(wrapper.eq(User::getId, o.getToCommentUserId()))).getNickName());
            }

        });
        return commentVoList;
    }


    private List<CommentVo> getChildren(Long rid) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Comment::getRootId, rid);
        lambdaQueryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(lambdaQueryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(list);
        return commentVoList;
    }
}
