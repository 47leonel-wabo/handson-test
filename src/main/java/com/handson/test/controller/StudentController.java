package com.handson.test.controller;

import com.handson.test.entity.Student;
import com.handson.test.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/student"})
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService mStudentService;

    @GetMapping(path = {"/{id}"})
    Student getStudentById(final @PathVariable Long id){
        return mStudentService.getStudentById(id);
    }

}
