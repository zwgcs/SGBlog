package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 8:30
 * @Description
 * @Since version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
