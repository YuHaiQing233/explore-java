package com.explore.reflect;

import cn.hutool.json.JSONUtil;
import com.explore.service.Animal;
import com.explore.service.impl.TianYuanDog;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/23 10:48
 */
@Slf4j
public class ExploreReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

        // 1. 获取Dog的Class对象
        Class<?> dogClazz = Class.forName("com.explore.service.impl.TianYuanDog");


        // 2. 通过反射创建对象实例

        // 2.1 newInstance()使用此方法创建实例对象的方式已过期
        TianYuanDog dog = (TianYuanDog) dogClazz.newInstance();

        // 2.2 使用新的方式创建对象实例

        // 2.2.1 根据无参构造函数创建实例对象
        TianYuanDog d1 = (TianYuanDog) dogClazz.getConstructor().newInstance();
        // 2.2.2 根据有参构造函数创建实例对象
        TianYuanDog d2 = (TianYuanDog) dogClazz.getConstructor(String.class, String.class).newInstance("110", "天安门");

        log.info("d1:{},content{}", d1, JSONUtil.toJsonStr(d1));
        log.info("d2:{},content{}", d2, JSONUtil.toJsonStr(d2));

        // 3. 通过反射，给对象进行赋值

        // 3.1 给公共字段赋值
        Class<? extends TianYuanDog> d1Clazz = d1.getClass();
        Field email = d1Clazz.getField("email");
        email.set(d1, "abc@163.com");
        log.info("new d1:{},content:{}", d1, JSONUtil.toJsonStr(d1));

        // 3.2 给私有字段赋值
        Class<? extends TianYuanDog> d2Clazz = d2.getClass();
        Field phone = d2Clazz.getDeclaredField("phone");
        phone.setAccessible(true);
        phone.set(d2, "120");
        log.info("new d2:{},content:{}", d2, JSONUtil.toJsonStr(d2));

        // 4. 通过反射，调用对象的方法

        // 4.1 调用公有方法
        Class<? extends TianYuanDog> d11Clazz = d1.getClass();
        Method run = d11Clazz.getMethod("getEmail");
        Object invoke = run.invoke(d1);
        log.info("d11 调用公有方法run  result:{}", invoke);


        // 4.2 调用私有方法
        Class<? extends TianYuanDog> d22Clazz = d2.getClass();
        Method eat = d22Clazz.getDeclaredMethod("eat", String.class);
        eat.setAccessible(true);
        Object dxd = eat.invoke(d2, "DXD");
        log.info("d22 调用私有方法, 结果:{}", dxd);

    }

    private static void method() throws ClassNotFoundException, NoSuchMethodException {
        // 1. 获取Dog的Class对象
        Class<?> dogClazz = Class.forName("com.explore.service.impl.TianYuanDog");

        // 2. 获取类对象的所有公有方法(本类 + 父类)
        Method[] methods = dogClazz.getMethods();
        log.info("获取类对象的所有公有方法: ");
        for (Method method : methods) {
            log.info(method.toGenericString());
        }

        // 3. 获取类对象的所有方法 公共方法、受保护方法、私有方法（本类）
        Method[] declaredMethods = dogClazz.getDeclaredMethods();
        log.info("获取类对象的所有方法: ");
        for (Method declaredMethod : declaredMethods) {
            log.info(declaredMethod.toGenericString());
        }

        // 4. 获取类对象的无参公有方法与有参公有方法
        Method getPhone = dogClazz.getMethod("getPhone");
        log.info("获取类对象的公有无参方法: ");
        log.info(getPhone.toGenericString());

        Method setPhone = dogClazz.getMethod("setPhone", String.class);
        log.info("获取类对象的公有有参方法: ");
        log.info(setPhone.toGenericString());

        // 5. 获取类对象的无参方法与有参方法
        Method run = dogClazz.getDeclaredMethod("run");
        log.info("获取类对象的无参方法: ");
        log.info(run.toGenericString());

        Method eat = dogClazz.getDeclaredMethod("eat", String.class);
        log.info("获取类对象的有参方法: ");
        log.info(eat.toGenericString());
    }

    private static void field() throws ClassNotFoundException, NoSuchFieldException {
        // 1. 获取Dog的Class对象
        Class<?> dogClazz = Class.forName("com.explore.service.impl.TianYuanDog");

        // 2. 获取Class对象中所有公共的字段集合（本类 + 父类）
        Field[] fields = dogClazz.getFields();
        log.info("Class对象中全部的公有字段: ");
        for (Field field : fields) {
            log.info(field.toGenericString());
        }

        // 2.1 如果通过getField()获取一个私有字段，则会报错 【java.lang.NoSuchFieldException】
        // Field errorField = dogClazz.getField("phone");
        // log.info(errorField.toGenericString());

        // 3. 获取Class对象中所有的字段集合（本类）
        Field[] declaredFields = dogClazz.getDeclaredFields();
        log.info("Class对象中所有字段: {}");
        for (Field declaredField : declaredFields) {
            log.info(declaredField.toGenericString());
        }

        // 4. 获取指定的公有字段（本类/父类中的公有字段）
        Field host = dogClazz.getField("host");
        log.info("获取指定的公有字段：");
        log.info(host.toGenericString());

        // 5. 获取本类中的任意字段(本类)
        Field phone = dogClazz.getDeclaredField("phone");
        log.info("获取本类中的任意字段: ");
        log.info(phone.toGenericString());
    }

    private static void constructor() throws ClassNotFoundException, NoSuchMethodException {
        // 1. 获取Dog的Class对象
        Class<?> dogClazz = Class.forName("com.explore.service.impl.TianYuanDog");

        // 2. 获取类对象的公有构造方法（public）
        Constructor<?>[] constructors = dogClazz.getConstructors();
        log.info("获取所有公共的构造方法(public): ");
        for (Constructor<?> constructor : constructors) {
            log.info(constructor.toGenericString());
        }

        // 3. 获取类对象的所有构造（public、protected、private）
        Constructor<?>[] declaredConstructors = dogClazz.getDeclaredConstructors();
        log.info("当前类中全部的构造(public、protected和private): ");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            log.info(declaredConstructor.toGenericString());
        }

        // 4. 无参构造函数
        Constructor<?> constructor = dogClazz.getConstructor();
        log.info("公开的无参构造函数: ");
        log.info(constructor.toGenericString());

        // 5.获取有参构造,获取有参构造对象时需要指定入参的数据类型
        Constructor<?> c1 = dogClazz.getConstructor(String.class, String.class);
        log.info("公开的有参构造函数: ");
        log.info(c1.toGenericString());

        // 6. 特殊的类对象构造,以下Class对象均没有构造函数
        Class<Void> voidClazz = void.class;
        Class<Double> doubleClazz = double.class;
        Class<int[]> intArrayClazz = int[].class;
        Class<Animal> animalClazz = Animal.class;

        log.info("void 构造: {}", voidClazz.getDeclaredConstructors());
        log.info("double构造: {}", doubleClazz.getDeclaredConstructors());
        log.info("int[] 构造: {}", intArrayClazz.getDeclaredConstructors());
        log.info("接口 构造: {}", animalClazz.getDeclaredConstructors());
    }

}
