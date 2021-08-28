package com.handson.test;

import com.handson.test.entity.Student;
import com.handson.test.service.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest
public class StudentControllerBaseClass {

    @MockBean
    private StudentService mStudentService;

    @Autowired
    private MockMvc mMockMvc;

    @BeforeEach
    void beforeTest() throws Exception {
        RestAssuredMockMvc.mockMvc(mMockMvc);
        // given
        BDDMockito
                .given(mStudentService.getStudentById(anyLong()))
                .willReturn(
                        Student.builder()
                                .id(1L)
                                .name("assa")
                                .grade(17)
                                .active(true)
                                .build()
                );
    }
}
