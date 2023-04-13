package com.kaka.controller;

import com.kaka.service.RoleService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getCheckList", method = RequestMethod.POST)
    ResponseResult getCheckList() {
        return new ResponseResult(200, "获取成功!", roleService.getCheckList());
    }
}
