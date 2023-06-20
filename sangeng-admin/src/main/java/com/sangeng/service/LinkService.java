package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.LinkDto;
import com.sangeng.domain.entity.Link;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/17 0:26
 * @Description
 * @Since version 1.0
 */
public interface LinkService extends IService<Link> {
    /**
     * 分页查询友链信息，对友链名进行模糊查询
     * @param pageNum 页码
     * @param pageSize 一页大小
     * @param name 友链名
     * @param status 友链状态
     * @return
     */
    ResponseResult linkList(Integer pageNum, Integer pageSize, String name, String status);

    /**
     * 新增友链
     * @param linkDto 新增的友链对象
     * @return
     */
    ResponseResult add(LinkDto linkDto);

    /**
     * 查询友链信息
     * @param linkId 友链Id
     * @return
     */
    ResponseResult linkInfo(Long linkId);

    /**
     * 修改友链信息
     * @param linkDto 修改后的友链信息
     * @return
     */
    ResponseResult updateLink(LinkDto linkDto);

    /**
     * 删除友链
     * @param linkId
     * @return
     */
    ResponseResult delete(Long linkId);
}
