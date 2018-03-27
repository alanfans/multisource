package com.example.multisource.neo.entity;

import java.io.Serializable;


public class StudentEntity implements Serializable{


    private static final long serialVersionUID = 3041550937549380686L;

    private String id;

    private String name;

    private Integer age;

    private Integer sex;

    private String className;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


}
