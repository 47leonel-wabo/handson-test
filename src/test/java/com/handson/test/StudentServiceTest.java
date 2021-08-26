package com.handson.test;

import com.handson.test.entity.Student;
import com.handson.test.repository.StudentRepository;
import com.handson.test.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE) // don't load the full web environment
@Transactional // to avoid side effect on data change
public class StudentServiceTest {

    @Autowired
    private StudentRepository mStudentRepository;

    @Autowired
    private StudentService mStudentService;

    @DisplayName("Return saved student from service layer")
    @Test
    void getStudentById_aSavedStudentIsReturned() {
        // given
        Student savedStudent = mStudentRepository.save(new Student(null, "nazari", false, 17));

        // when
        Student studentById = mStudentService.getStudentById(savedStudent.getId());

        // then
        then(studentById).isNotNull();
        then(savedStudent.getName()).isEqualTo("nazari");

    }

}
