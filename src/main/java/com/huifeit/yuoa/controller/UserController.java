package com.huifeit.yuoa.controller;

import com.huifeit.yuoa.common.Result;
import com.huifeit.yuoa.entity.SysUser;
import com.huifeit.yuoa.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/list")
    public Result<List<SysUser>> listUsers() {
        return Result.success(userService.findAll());
    }
}
