package com.example.annotationt;

import org.springframework.stereotype.Component;

@Component
public class DemoObject {
    //@MyAnnotation("自定义的注解测试")
    public Student run(Student student) {
        System.out.println("执行run方法");
        return student;
    }
}
