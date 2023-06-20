package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.TagListDto;
import com.sangeng.domain.entity.Tag;

/**
* @author 我是你爸爸
* @description 针对表【sg_tag(标签)】的数据库操作Service
* @createDate 2023-04-11 23:31:04
*/
public interface TagService extends IService<Tag> {

    /**
     * 查询文章内容便签列表
     * @param pageNum 页码数
     * @param pageSize 一页大小
     * @param tagListDto 标签信息
     * @return
     */
    ResponseResult contentTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    /**
     * 添加标签
     * @param tag 标签信息对象
     * @return
     */
    ResponseResult addTag(Tag tag);

    /**
     * 删除标签
     * @param tagId 标签Id
     * @return
     */
    ResponseResult deleteTag(Long tagId);

    /**
     * 更新标签信息
     * @param tag 更改后的标签信息
     * @return
     */
    ResponseResult updateTag(Tag tag);

    /**
     * 获取标签信息
     * @param tagId 标签Id
     * @return
     */
    ResponseResult getInfo(Long tagId);

    /**
     * 获取所有的标签
     * @return
     */
    ResponseResult listAllTag();
}
