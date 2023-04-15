package com.kaka.mapper;

import com.kaka.entity.CheckTeacher;
import com.kaka.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    public List<CheckTeacher> getCheckList(CheckTeacher checkTeacher);

    public List<String> getMaterialById(Long id);

    public void updateCheckState(CheckTeacher checkTeacher);

    public Long addTeacherCheck(CheckTeacher checkTeacher);

    public void addMaterialToCheck(Material material);

    public List<CheckTeacher> getCheckStateById(Long user_id);
}
