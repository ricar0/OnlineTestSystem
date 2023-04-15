package com.kaka.config;

import com.kaka.entity.CheckTeacher;
import com.kaka.entity.Material;
import com.kaka.service.IOssService;
import com.kaka.service.RoleService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/pic")
@RestController
public class PicController {

    @Autowired
    private IOssService ossService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/uploadPic", method = RequestMethod.POST)
    ResponseResult uploadPic(@RequestParam(value = "files", required = false) MultipartFile[] files,
                             @RequestParam(value="user_id") Long user_id,
                             @RequestParam(value="time") String time) throws IOException {

        CheckTeacher checkTeacher = new CheckTeacher();
        checkTeacher.setUser_id(user_id);
        checkTeacher.setState(0);
        checkTeacher.setTime(time);
        Long check_id = roleService.addTeacherCheck(checkTeacher);
        for (MultipartFile file : files) {
            String url = ossService.upload(file);
            Material material = new Material();
            material.setUrl(url);
            material.setCheck_id(check_id);
            roleService.addMaterialToCheck(material);
        }
        return new ResponseResult(200, "上传成功");
    }
}
