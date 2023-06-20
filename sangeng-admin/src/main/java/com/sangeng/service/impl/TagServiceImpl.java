package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.TagListDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.TagVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.TagMapper;
import com.sangeng.service.TagService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author 我是你爸爸
* @description 针对表【sg_tag(标签)】的数据库操作Service实现
* @createDate 2023-04-11 23:31:04
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {


    @Override
    public ResponseResult contentTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        wrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());
        Page<Tag> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<Tag> tags = page.getRecords();
        List<TagVo> tagVos = BeanCopyUtils.copyBean(tags, TagVo.class);
        return ResponseResult.okResult(new PageVo<TagVo>(tagVos,page.getTotal()));
    }

    @Override
    public ResponseResult addTag(Tag tag) {
        //标签不能为空
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.TAG_NOT_NULL);
        }
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long tagId) {
        removeById(tagId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        LambdaUpdateWrapper<Tag> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Tag::getId,tag.getId());
        wrapper.set(StringUtils.hasText(tag.getName()),Tag::getName,tag.getName());
        wrapper.set(StringUtils.hasText(tag.getRemark()),Tag::getRemark,tag.getRemark());
        update(tag, wrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getInfo(Long tagId) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getId,tagId);
        wrapper.select(Tag::getId,Tag::getName,Tag::getRemark);
        Tag tag = getOne(wrapper);
        if(tag == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"id为："+tagId+"的标签不存在！");
        }
        return ResponseResult.okResult(tag);
    }

    @Override
    public ResponseResult listAllTag() {
        List<Tag> tags = list();
        List<TagVo> tagVos = BeanCopyUtils.copyBean(tags, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}




