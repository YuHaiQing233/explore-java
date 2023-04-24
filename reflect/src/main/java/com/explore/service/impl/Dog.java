package com.explore.service.impl;

import com.explore.service.Animal;

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/20 17:54
 */
public class Dog implements Animal {

    private String name;

    protected Integer age;

    public String host;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    protected Dog(Integer age) {
        this.age = age;
    }

    private Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String kind() {
        return "Dog";
    }

    @Override
    public String color() {
        return "Yellow";
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    protected void d1() {

    }

    private void d2() {

    }

}
