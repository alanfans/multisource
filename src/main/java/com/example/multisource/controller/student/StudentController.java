package com.example.multisource.controller.student;

import com.example.multisource.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student1")
    @ResponseBody
    public Object studentList1()
    {
        return studentService.getAll1();
    }

    @RequestMapping("/student2")
    @ResponseBody
    public Object studentList2()
    {
        return studentService.getAll2();
    }

    @RequestMapping("/student3")
    @ResponseBody
    public Object studentOne()
    {
        return studentService.getOne();
    }

    @RequestMapping("/student4")
    @ResponseBody
    public Object studentTwo()
    {
        return studentService.getTwo();
    }

    @RequestMapping("/student5")
    @ResponseBody
    public Object studentThree()
    {
        return studentService.getThree();
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update()
    {
        studentService.update(25);
    }
}
