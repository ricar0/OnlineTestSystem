package com.kaka.service.Impl;

import com.kaka.entity.CheckTeacher;
import com.kaka.entity.Material;
import com.kaka.mapper.RoleMapper;
import com.kaka.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<CheckTeacher> getCheckList(CheckTeacher checkTeacher) {
        return roleMapper.getCheckList(checkTeacher);
    }

    @Override
    public List<String> getMaterialById(Long id) {
        return roleMapper.getMaterialById(id);
    }

    @Override
    public void updateCheckState(CheckTeacher checkTeacher) {
        roleMapper.updateCheckState(checkTeacher);
    }

    @Override
    public Long addTeacherCheck(CheckTeacher checkTeacher) {
        roleMapper.addTeacherCheck(checkTeacher);
        return checkTeacher.getCheck_id();
    }

    @Override
    public void addMaterialToCheck(Material material) {
        roleMapper.addMaterialToCheck(material);
    }

    @Override
    public List<CheckTeacher> getCheckStateById(Long user_id) {
        return roleMapper.getCheckStateById(user_id);
    }


}
