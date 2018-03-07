package com.example.multisource.neo.mapper;

import com.example.multisource.neo.entity.StudentEntity;

import java.util.List;

public interface StudentMapper {
    List<StudentEntity> getAll();

    StudentEntity getOne(String id);

    void insert(StudentEntity user);

    void update(StudentEntity user);

    void delete(String id);
}
