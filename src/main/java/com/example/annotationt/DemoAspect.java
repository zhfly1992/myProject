package com.example.annotationt;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Aspect
@Component
public class DemoAspect {

    //使用基于注解式拦截
    @Pointcut("@annotation(com.example.annotationt.MyAnnotation)")
    private void cut() {
    }


    @Before("cut() && @annotation(myAnnotation)")
    private void beforeRun(MyAnnotation myAnnotation) {
        System.out.println("before run");
        System.out.println("获取到注解内容" + myAnnotation.value());
    }

    //基于方法规则式拦截
    @Pointcut("execution(* com.example.annotationt.DemoObject.*(..))")
    private void checkStudent() {
    }
    //这个通知不能阻止连接点前的执行,意思是before无法阻止目标方法的运行
    @Before("checkStudent()")
    private void dobefore(JoinPoint joinPoint){
        System.out.println("dobefore方法执行");
    }

    @After(value = "checkStudent()")
    private void doafter() {
        System.out.println("doafter执行");
    }

    @AfterReturning(value = "checkStudent()", returning = "result")
    private void doafterReturn(JoinPoint joinPoint, Object result) {
        System.out.println("doafterReturn执行");
        if (result instanceof Student) {
            Student student = (Student) result;
            student.setName("被更改");
        }
        System.out.println("doafterReturn修改完毕");
    }

    @Around("checkStudent()")
    private void doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = proceedingJoinPoint.getTarget().getClass().getName();
        System.out.println("joinPoint.getTarget().getClass().getName()---" + name);
        String name1 = proceedingJoinPoint.getSignature().getName();
        System.out.println("joinPoint.getSignature().getName()----" + name1);
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object object : args) {
            if (object.getClass().equals(Student.class)) {
                Field[] declaredFields = object.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Length.class)) {
                        Length length = field.getAnnotation(Length.class);
                        field.setAccessible(true);
                        int length1 = ((String) field.get(object)).length();
                        if (length1 < length.min() || length1 > length.max()) {
                            System.out.println(length.errorMsg());
                            return;
                        }
                    }
                }
            }
        }
        proceedingJoinPoint.proceed();
    }
}
