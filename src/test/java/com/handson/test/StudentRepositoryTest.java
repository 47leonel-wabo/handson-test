package com.handson.test;

import com.handson.test.entity.Student;
import com.handson.test.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository mStudentRepository;

    @Autowired
    TestEntityManager mTestEntityManager;

    @Test
    void fetchStudentByName_returnStudentDetails() {
        // given
        // this just interact with the first level of spring data jpa, not the real database
        // Student savedStudent = mStudentRepository.save(new Student(null, "mishi"));

        // given
        // Now, data is persisted
        Student savedStudent = mTestEntityManager.persistFlushFind(new Student(null, "mishi", true, 80));

        // when
        Student student = mStudentRepository.getStudentByName("mishi");

        // then
        then(savedStudent.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());
    }

    @Test
    void fetchStudentsWithinCertainGrade_returnListOfStudents() {
        // given
        Student shioma = new Student(null, "shioma", true, 70);
        Student awale = new Student(null, "awale", true, 90);
        Student kemi = Student.builder().name("kemi").active(false).grade(20).build();
        Arrays.asList(shioma, awale, kemi).forEach(student -> mTestEntityManager.persistFlushFind(student));

        // when
        List<Student> students = this.mStudentRepository.getstudentByGradeInterval(60, 100);

        // then
        then(students.size()).isEqualTo(2);

    }

}
