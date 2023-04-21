# explore-java
探索Java相关功能


## Java.Lang.Class

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
    }
}
```