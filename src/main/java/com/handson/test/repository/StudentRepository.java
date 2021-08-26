package com.handson.test.repository;

import com.handson.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByName(final String name);

    @Query("select s from Student s where s.grade > :min and s.grade < :max")
    List<Student> getstudentByGradeInterval(final Integer min, final Integer max);
}
