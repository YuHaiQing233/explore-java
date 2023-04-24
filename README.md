# explore-java
探索Java相关功能


## Java.Lang.Class


### 一些理解

**Class是什么？**
```text
java.lang.Class是什么，它也是类的一种，跟Object、自定义的类（People、Student等）一样，由某些实例特征抽象而成的类型；
```
**它的作用是什么**
```text
我们自定义的People类是由男人、女人、学生、教师等具体的事物抽象成的，因此可以通过People来创建具体的实例对象（男人、女人等）；
而java.lang.Class也是由Java中的所有类抽象而成的。通过Class类可以创建出所有的Java类；
```
**它抽象了什么**
```text
举个例子：我们定义一个People类，那么就可以称这个People类是java.lang.Class的实例，那这个实例中抽象类哪些东西呢，性别、职业、肤色等；
同样的Class类则抽象类所有类都有的类修饰符（public、protected、private）、类名、类别（接口、抽象类、普通类）、构造函数、方法、成员变量等；
```
**Class类应用**
````text
JVM装载.class文件的过程就是创建Class对象的过程；Class没有公共构造方法，Class对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的 defineClass 方法自动构造的，因此不能显式地声明一个Class对象。
每个类都有一个Class对象，
````

```java
public class Caller {
    public static void main(String[] args) throws ClassNotFoundException {
        
        // 1. 基本数据类型
        java.lang.Class intClazz = int.class;
        java.lang.Class longClazz = long.class;
        java.lang.Class byteClazz = byte.class;
        java.lang.Class charClazz = char.class;
        java.lang.Class floatClazz = float.class;
        java.lang.Class shortClazz = short.class;
        java.lang.Class doubleClazz = double.class;
        java.lang.Class booleanClazz = boolean.class;
        
        // 2. 关键字Class对象
        java.lang.Class voidClazz = void.class;

        // 3. 数组对象
        java.lang.Class arrayClazz = int[].class;

        // 4. 注解对象
        java.lang.Class overrideClazz = Override.class;

        // 5. 枚举对象
        java.lang.Class enumClazz = UserTypeEnum.class;
    }
}
```

**获取Class对象的方式**
```java
public class Caller {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1.1 第一种（推荐使用）
        Class<?> clazz = Class.forName("com.explore.service.impl.Dog");
        
        // 1.2 第二种
        Class<?> clazz1 =Dog.class;

        // 1.3 第三种
        Dog dog = new Dog();
        Class<? extends Dog> clazz2 = dog.getClass();
        
        // 1.4 通过类加载器的方式
        Class<?> clazz3 = clazz.getClassLoader().getClass();
    }
}
```


### Reflect

**Java反射是什么**
```text
Java的反射(reflection)机制是指在程序的运行状态中，可以构造任意一个类的对象，可以了解任意一个对象所属的类，可以了解任意一个类的成员变量和方法，可以调用任意一个对象的属性和方法。
这种动态获取程序信息以及动态调用对象的功能称为Java语言的反射机制。反射被视为动态语言的关键。

Java反射机制主要提供了以下功能： 在运行时判断任意一个对象所属的类;在运行时构造任意一个类的对象;在运行时判断任意一个类所具有的成员变量和方法;在运行时调用任意一个对象的方法;生成动态代理。
```

**自定义一些接口类与实现类**
```java
package com.explore.service;

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/20 17:51
 */
public interface Animal extends Color {

    /**
     * 种族
     *
     * @return
     * @Author: HaiQing.Yu
     * @Date: 2023-04-20 17:53
     */
    String kind();

    /**
     * 颜色
     *
     * @return
     * @Author: HaiQing.Yu
     * @Date: 2023-04-20 17:53
     */
    String color();

}

```
```java
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
```
```java
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
```

**通过反射，获取类的构造函数**

```java

@Slf4j
public class ExploreReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        // 1. 获取TianYuanDog的Class对象
        Class<?> dogClazz = Class.forName("com.explore.service.impl.TianYuanDog");

        // 2. 获取类对象的公有构造方法（public）
        Constructor<?>[] constructors = dogClazz.getConstructors();
        log.info("获取所有公共的构造方法(public): ");
        for (Constructor<?> constructor : constructors) {
            log.info(constructor.toGenericString());
        }

        // 3. 获取类对象的所有构造（public、protected、private，默认构造）
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
```

**执行结果**
```text
14:42:45.536 [main] INFO com.explore.reflect.ExploreReflect - 获取所有公共的构造方法(public):
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog()
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog(java.lang.String,java.lang.String)
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog(java.lang.String,java.lang.String,java.lang.String)
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - 当前类中全部的构造(public、protected和private):
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - protected com.explore.service.impl.TianYuanDog(java.lang.String)
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - private com.explore.service.impl.TianYuanDog(java.lang.Integer,java.lang.String,java.lang.String)
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog()
14:42:45.543 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog(java.lang.String,java.lang.String)
14:42:45.544 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog(java.lang.String,java.lang.String,java.lang.String)
14:42:45.544 [main] INFO com.explore.reflect.ExploreReflect - 公开的无参构造函数:
14:42:45.544 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog()
14:42:45.544 [main] INFO com.explore.reflect.ExploreReflect - 公开的有参构造函数:
14:42:45.544 [main] INFO com.explore.reflect.ExploreReflect - public com.explore.service.impl.TianYuanDog(java.lang.String,java.lang.String)
14:42:45.544 [main] INFO com.explore.reflect.ExploreReflect - void 构造: {}
14:42:45.545 [main] INFO com.explore.reflect.ExploreReflect - int[] 构造: {}
```


**通过反射，获取类对象的字段**
```java
/**
 * @Author HaiQing.Yu
 * @Date 2023/4/23 10:48
 */
@Slf4j
public class ExploreReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {

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
}
```

**结果**
```text
15:18:53.231 [main] INFO com.explore.reflect.ExploreReflect - Class对象中全部的公有字段: 
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.email
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.Dog.host
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - Class对象中所有字段: {}
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - private java.lang.String com.explore.service.impl.TianYuanDog.phone
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - protected java.lang.String com.explore.service.impl.TianYuanDog.address
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.email
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - 获取指定的公有字段：
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.Dog.host
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - 获取本类中的任意字段: 
15:18:53.238 [main] INFO com.explore.reflect.ExploreReflect - private java.lang.String com.explore.service.impl.TianYuanDog.phone
```

**通过反射，获取类对象的方法**
```java

/**
 * @Author HaiQing.Yu
 * @Date 2023/4/23 10:48
 */
@Slf4j
public class ExploreReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {

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
}
```

**执行结果**
```text
16:11:08.024 [main] INFO com.explore.reflect.ExploreReflect - 获取类对象的所有公有方法: 
16:11:08.028 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getEmail()
16:11:08.028 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setEmail(java.lang.String)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getPhone()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setPhone(java.lang.String)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getAddress()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setAddress(java.lang.String)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.Dog.color()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.Integer com.explore.service.impl.Dog.getAge()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.Dog.setAge(java.lang.Integer)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.Dog.setHost(java.lang.String)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.Dog.getName()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.Dog.setName(java.lang.String)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.Dog.kind()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.Dog.getHost()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public final void java.lang.Object.wait() throws java.lang.InterruptedException
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public boolean java.lang.Object.equals(java.lang.Object)
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String java.lang.Object.toString()
16:11:08.029 [main] INFO com.explore.reflect.ExploreReflect - public native int java.lang.Object.hashCode()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public final native java.lang.Class<?> java.lang.Object.getClass()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public final native void java.lang.Object.notify()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public final native void java.lang.Object.notifyAll()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - 获取类对象的所有方法: 
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getEmail()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setEmail(java.lang.String)
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getPhone()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setPhone(java.lang.String)
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - private void com.explore.service.impl.TianYuanDog.eat(java.lang.String)
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - protected void com.explore.service.impl.TianYuanDog.run()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getAddress()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setAddress(java.lang.String)
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - 获取类对象的公有无参方法: 
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public java.lang.String com.explore.service.impl.TianYuanDog.getPhone()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - 获取类对象的公有有参方法: 
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - public void com.explore.service.impl.TianYuanDog.setPhone(java.lang.String)
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - 获取类对象的无参方法: 
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - protected void com.explore.service.impl.TianYuanDog.run()
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - 获取类对象的有参方法: 
16:11:08.031 [main] INFO com.explore.reflect.ExploreReflect - private void com.explore.service.impl.TianYuanDog.eat(java.lang.String)
```

**通过反射，创建对象实例**
```java
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
}
```
**执行结果**
````text
16:55:24.255 [main] INFO com.explore.reflect.ExploreReflect - d1:com.explore.service.impl.TianYuanDog@7403c468,content{}
16:55:24.262 [main] INFO com.explore.reflect.ExploreReflect - d2:com.explore.service.impl.TianYuanDog@10dba097,content{"address":"天安门","phone":"110"}
16:55:24.263 [main] INFO com.explore.reflect.ExploreReflect - new d1:com.explore.service.impl.TianYuanDog@7403c468,content:{"email":"abc@163.com"}
16:55:24.264 [main] INFO com.explore.reflect.ExploreReflect - new d2:com.explore.service.impl.TianYuanDog@10dba097,content:{"address":"天安门","phone":"120"}
16:55:24.264 [main] INFO com.explore.reflect.ExploreReflect - d11 调用公有方法run  result:abc@163.com
16:55:24.265 [main] INFO com.explore.service.impl.TianYuanDog - eat()方法被执行.... food:DXD
16:55:24.302 [main] INFO com.explore.reflect.ExploreReflect - d22 调用私有方法, 结果:正在吃 DXD
````
