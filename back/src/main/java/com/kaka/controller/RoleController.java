package com.kaka.controller;

import com.kaka.entity.CheckTeacher;
import com.kaka.service.RoleService;
import com.kaka.service.UserService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getCheckList", method = RequestMethod.POST)
    ResponseResult getCheckList(@RequestBody CheckTeacher checkTeacher) {
        List<CheckTeacher> tmp = roleService.getCheckList(checkTeacher);
        for (int i = 0; i < tmp.size(); i++) {
            List<String> material = roleService.getMaterialById(tmp.get(i).getCheck_id());
            tmp.get(i).setMaterial(material);
        }
        return new ResponseResult(200, "获取成功!", tmp);
    }

    @RequestMapping(value = "/getCheckListSize", method = RequestMethod.POST)
    ResponseResult getCheckListSize(@RequestBody CheckTeacher checkTeacher) {
        List<CheckTeacher> tmp = roleService.getCheckList(checkTeacher);
        return new ResponseResult(200, "获取成功!", tmp.size());
    }

    @RequestMapping(value="/acceptCheck", method = RequestMethod.POST)
    ResponseResult AcceptCheck(@RequestBody CheckTeacher checkTeacher) {
        userService.updateRole(checkTeacher.getUser_id());
        roleService.updateCheckState(checkTeacher);
        return new ResponseResult(200, "修改成功");
    }

    @RequestMapping(value="/refuseCheck", method = RequestMethod.POST)
    ResponseResult RefuseCheck(@RequestBody CheckTeacher checkTeacher) {
        roleService.updateCheckState(checkTeacher);
        return new ResponseResult(200, "修改成功");
    }

    @RequestMapping(value="/getCheckStateById", method = RequestMethod.POST)
    ResponseResult getCheckStateById(@RequestBody CheckTeacher checkTeacher) {
        List<CheckTeacher> tmp = roleService.getCheckStateById(checkTeacher.getUser_id());
        if (tmp.size() == 0) {
            return new ResponseResult(200, "查询成功!", -1);
        } else {
            return new ResponseResult(200, "查询成功!", tmp.get(0).getState());
        }
    }
}
