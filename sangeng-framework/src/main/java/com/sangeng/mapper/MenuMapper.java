package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.vo.MenuVo;
import com.sangeng.domain.vo.MenuVo1;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 我是你爸爸
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2023-04-11 23:20:08
* @Entity com.sangeng.domain.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    MenuVo menuList1(Long menuId);
    MenuVo menuList2(Long menuParentId);

    MenuVo1 menuList3(Long menuId);
    MenuVo1 menuList4(Long menuParentId);

}




