package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.LinkDto;
import com.sangeng.domain.entity.Link;
import com.sangeng.domain.vo.LinkVo1;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.mapper.LinkMapper;
import com.sangeng.service.LinkService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/17 0:26
 * @Description
 * @Since version 1.0
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult linkList(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(name),Link::getName,name);
        wrapper.eq(StringUtils.hasText(status),Link::getStatus,status);
        Page<Link> page = new Page<>(pageNum, pageSize);
        page(page,wrapper);
        List<Link> links = page.getRecords();
        List<LinkVo1> linkVo1s = BeanCopyUtils.copyBean(links, LinkVo1.class);
        return ResponseResult.okResult(new PageVo<LinkVo1>(linkVo1s,page.getTotal()));
    }

    @Override
    public ResponseResult add(LinkDto linkDto) {
        Link link = new Link();
        link.setLogo(linkDto.getLogo());
        link.setName(linkDto.getName());
        link.setStatus(linkDto.getStatus());
        link.setAddress(linkDto.getAddress());
        link.setDescription(linkDto.getDescription());
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult linkInfo(Long linkId) {
        Link link = getById(linkId);
        LinkVo1 linkVo1 = BeanCopyUtils.copyBean(link, LinkVo1.class);
        return ResponseResult.okResult(linkVo1);
    }

    @Override
    public ResponseResult updateLink(LinkDto linkDto) {
        Link link = new Link();
        link.setId(Long.parseLong(linkDto.getId()));
        link.setLogo(linkDto.getLogo());
        link.setName(linkDto.getName());
        link.setStatus(linkDto.getStatus());
        link.setAddress(linkDto.getAddress());
        link.setDescription(linkDto.getDescription());
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(Long linkId) {
        removeById(linkId);
        return ResponseResult.okResult();
    }
}
