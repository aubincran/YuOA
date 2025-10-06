package com.huifeit.yuoa.service.impl;

import com.huifeit.yuoa.entity.SysUser;
import com.huifeit.yuoa.mapper.UserMapper;
import com.huifeit.yuoa.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public List<SysUser> findAll() {
        return userMapper.findAll();
    }
}
