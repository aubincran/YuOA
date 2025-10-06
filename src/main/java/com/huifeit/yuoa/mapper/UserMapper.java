package com.huifeit.yuoa.mapper;

import com.huifeit.yuoa.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM sys_user")
    List<SysUser> findAll();
}
