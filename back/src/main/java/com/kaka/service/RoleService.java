package com.kaka.service;

import com.kaka.entity.CheckTeacher;
import com.kaka.entity.Material;

import java.util.List;

public interface RoleService {
    public List<CheckTeacher> getCheckList(CheckTeacher checkTeacher);

    public List<String> getMaterialById(Long id);

    public void updateCheckState(CheckTeacher checkTeacher);

    public Long addTeacherCheck(CheckTeacher checkTeacher);

    public void addMaterialToCheck(Material material);

    public List<CheckTeacher> getCheckStateById(Long user_id);
}
