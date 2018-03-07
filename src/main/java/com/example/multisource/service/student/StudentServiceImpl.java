package com.example.multisource.service.student;

import com.example.multisource.config.DataSourceContextHolder;
import com.example.multisource.config.Ds;
import com.example.multisource.neo.entity.StudentEntity;
import com.example.multisource.neo.mapper.StudentMapper;
import com.example.multisource.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StudentService self;

    @Transactional
    @Ds("ds")
    @Override
    public PageBean<StudentEntity> getAll1()
    {
        PageHelper.startPage(1,3);
        return new PageBean<>(studentMapper.getAll());
    }


    @Ds("ds1")
    @Override
    public PageBean<StudentEntity> getAll2()
    {
        PageHelper.startPage(1,3);
        PageBean pageBean=new PageBean<>(studentMapper.getAll());
        return pageBean;
    }

    @Transactional
    @Override
    public void update(int age)
    {
        self.updateOne(age);
        self.updateTwo(age);
        self.updateThree(age);
        Integer.valueOf("aaa");
    }


    @Ds("ds")
    @Override
    public void updateOne(int age) {
        StudentEntity entity=studentMapper.getOne("EA64B8CF0DB8443ABF003268939FBF74");
        entity.setAge(age);
        studentMapper.update(entity);
    }


    @Ds("ds1")
    @Override
    public void updateTwo(int age)
    {
        StudentEntity entity=studentMapper.getOne("B7A381F4C11145688DFB18A7814AC9F6");
        entity.setAge(age);
        studentMapper.update(entity);
    }

    @Ds("ds")
    @Override
    public StudentEntity getOne() {
        return studentMapper.getOne("EA64B8CF0DB8443ABF003268939FBF74");
    }

    @Ds("ds1")
    @Override
    public StudentEntity getTwo() {
        return studentMapper.getOne("B7A381F4C11145688DFB18A7814AC9F6");
    }


    @Ds("ds2")
    @Override
    public void updateThree(int age)
    {
        StudentEntity entity=studentMapper.getOne("583A03307B9B4A439AE316031C4893DC");
        entity.setAge(age);
        studentMapper.update(entity);
    }

    @Ds("ds2")
    @Override
    public StudentEntity getThree()
    {
        return studentMapper.getOne("583A03307B9B4A439AE316031C4893DC");
    }
}
