package com.example;

import com.example.annotationt.DemoObject;
import com.example.annotationt.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DemoObject demoObject;

    @Test
    void contextLoads() {
        Student student = new Student();
        student.setId(1);
        student.setMobile("15982343108");
        student.setName("xx");
        demoObject.run(student);
        System.out.println(student);
    }
}
