package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 20:17
 * @Description
 * @Since version 1.0
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
