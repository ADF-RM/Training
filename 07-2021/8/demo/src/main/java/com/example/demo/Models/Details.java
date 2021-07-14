package com.example.demo.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Details {
    @Id
    private Integer rollNo;
    private String name;
    private Integer age;

    public Integer getRollNo() {
        return rollNo;
    }
    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
        
}
