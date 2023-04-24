package com.explore.service.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/23 14:15
 */
@Slf4j
public class TianYuanDog extends Dog{

    private String phone;

    protected String address;

    public String email;

    public TianYuanDog() {
    }

    public TianYuanDog(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public TianYuanDog(String name, String phone, String address) {
        super(name);
        this.phone = phone;
        this.address = address;
    }

    protected TianYuanDog(String name) {
        super(name);
    }

    private TianYuanDog(Integer age, String phone, String address) {
        super(age);
        this.phone = phone;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String eat(String food){
        log.info("eat()方法被执行.... food:{}", food);
        return "正在吃 " + food;
    }

    protected void run(){
        log.info("run()方法被执行.....");
    }

}
