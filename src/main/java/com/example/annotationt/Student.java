package com.example.annotationt;


public class Student {
    private long id;

    private String name;
    @Length(min = 11,max = 11,errorMsg = "手机号码非法")
    private String mobile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}

