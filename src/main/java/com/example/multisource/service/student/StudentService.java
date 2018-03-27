package com.example.multisource.service.student;

import com.example.multisource.neo.entity.StudentEntity;
import com.example.multisource.util.PageBean;

public interface StudentService {

    PageBean<StudentEntity> getAll1();

    PageBean<StudentEntity> getAll2();

    void update(int age);

    void updateOne(int age);

    void updateTwo(int age);

    void updateThree(int age);

    StudentEntity   getOne(int age);

    StudentEntity   getTwo();

    StudentEntity   getThree();
}
