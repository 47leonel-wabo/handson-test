package com.handson.test;

import com.handson.test.exception.StudentNotFoundException;
import com.handson.test.service.StudentService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class StudentNotFoundExceptionTest {

    @Autowired
    private StudentService mStudentService;

    @Test
    void shouldThrowStudentNotFoundWhenStudentWithIdNotExist() {
        // given
        Long dummyId = 100000L; // this student doesn't exist

        // when
        Throwable throwable = BDDAssertions.catchThrowable(() -> mStudentService.getStudentById(dummyId));

        // then
        BDDAssertions
                .then(throwable)
                .isInstanceOf(StudentNotFoundException.class);
    }

}
