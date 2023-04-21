package com.explore;

import cn.hutool.json.JSONUtil;
import com.explore.service.Animal;
import com.explore.service.impl.Dog;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Modifier;

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/20 17:55
 */
@Slf4j
public class Caller {

    public static void main(String[] args) throws ClassNotFoundException {

        // 1. 获取类对象的方式
        // clazz();

        // 2. 获取类的基本信息
        // exploreClass();

        // 3. 获取接口信息
        Class<?> clazz = Class.forName("com.explore.service.impl.Dog");


        log.info("此类或接口直接实现的接口集合: {}", JSONUtil.toJsonStr(clazz.getInterfaces()));
        log.info("此类中存在的超接口类型注解集合: {}", JSONUtil.toJsonStr(clazz.getAnnotatedInterfaces()));
        log.info("", JSONUtil.toJsonStr(clazz.getGenericInterfaces()));

        log.info("Java语言规范定义的基础类的规范名称: ", clazz.getCanonicalName());

        log.info("返回此类上存在的注解: {}", JSONUtil.toJsonStr(clazz.getAnnotations()));
        log.info("类对象转为字符串: {}", clazz.toString());

        Class intClass = int.class;
        log.info("判断此类是否为一个基本类型: {}", intClass.isPrimitive());

    }

    private static void exploreClass() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.explore.service.impl.Dog");

        log.info("类名: {}", clazz.getSimpleName());
        log.info("包名: {}", clazz.getPackageName());
        log.info("全限定类名: {}", clazz.getName());
        log.info("类的修饰符: {}", Modifier.toString(clazz.getModifiers()));
        log.info("类加载器: {}", clazz.getClassLoader());
        log.info("类的父类: {}", clazz.getSuperclass());
        log.info("类的类型名称信息字符串: {}", clazz.getTypeName());
    }

    private static void clazz() throws ClassNotFoundException {

        // 1.1 第一种（推荐使用）
        Class<?> clazz = Class.forName("com.explore.service.impl.Dog");

        // 1.2 第二种
        Class<?> clazz1 =Dog.class;

        // 1.3 第三种
        Dog dog = new Dog();
        Class<? extends Dog> clazz2 = dog.getClass();
    }

}
