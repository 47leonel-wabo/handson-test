package com.handson.test;

import com.handson.test.entity.Student;
import com.handson.test.repository.StudentRepository;
import com.handson.test.service.StudentService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class StudentCacheTest {

    @Autowired
    private StudentService mStudentService;

    @MockBean
    private StudentRepository mStudentRepository;

    @Test
    void getStudentByIdForMultipleRequest_returnStudentFromCache() {
        // given
        Long id = 1230L;
        BDDMockito
                .given(mStudentRepository.findById(id))
                .willReturn(Optional.of(new Student(id, "elza", false, 11)));

        // when
        // multiple calls (03) of the same element
        mStudentService.getStudentById(id);
        mStudentService.getStudentById(id);
        mStudentService.getStudentById(id);

        // then
        // Force student repository to be called only once
        BDDMockito
                .then(mStudentRepository)
                .should(times(1))
                .findById(id);
    }

}
