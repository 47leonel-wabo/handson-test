package com.handson.test;

import com.handson.test.entity.Student;
import com.handson.test.exception.StudentNotFoundException;
import com.handson.test.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mMockMvc;

    @MockBean
    private StudentService mStudentService;

    @Test
    void getStudentAfterSavingNewOne() throws Exception {
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

        // when

        // then
        mMockMvc
                .perform(get("/api/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("assa"))
                .andExpect(jsonPath("active").value(true))
                .andExpect(jsonPath("grade").value(17));
    }

    @Test
    void getStatus404ForMissingStudent() throws Exception {
        // given
        BDDMockito
                .given(mStudentService.getStudentById(anyLong()))
                .willThrow(StudentNotFoundException.class);

        // when

        // then
        mMockMvc
                .perform(get("/api/student/1"))
                .andExpect(status().isNotFound());

    }
}
