package com.handson.test.service;

import com.handson.test.entity.Student;
import com.handson.test.exception.StudentNotFoundException;
import com.handson.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository mStudentRepository;

    @Cacheable("students")
    public Student getStudentById(final Long id) {
        return mStudentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(
                String.format("Student with ID %s not found", id)
        ));
    }
}
