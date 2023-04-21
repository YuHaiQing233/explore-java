package com.explore.service.impl;

import com.explore.service.Animal;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/20 17:54
 */
@Data
@Slf4j
public class Dog implements Animal{

    public String kind() {
        return "Dog";
    }

    public String color() {
        return "Yellow";
    }
}
