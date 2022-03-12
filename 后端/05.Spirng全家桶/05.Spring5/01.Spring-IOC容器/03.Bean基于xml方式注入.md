



### 1、创建对象

> 在src中创建一个Spring的xml配置文件，通过`id`来配置类的标识，通过`class`来配置类的完整路径，形成映射关系
>
> 在使用的使用，通过`ClassPathXmlApplicationContext`来加载配置文件，然后通过id标识来获取到类 ，完成

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- 
        id: 类的标色符号
        class: 类的完整路径
    -->
    <bean id="userService" class="day01.UserService"/>
</beans>
```

```java
//1.加载配置文件
ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
//2 获取配置创建的对象， userService 对应配置中的id，必须一样，通过id来获取到类
UserService user = context.getBean("userService", UserService.class);
user.add();
```

### 2、注入属性

> *注入属性可以通过set方法注入，和构造函数注入*：set方法需要提供set方法，构造函数需要提供构造函数

- **通过Set注入**

  ```xml
  <!--  注入要提交属性的set方法才能注入  -->
  <property name="age" value="18"/>
  <property name="name" value="小明"/>
  ```

- **通过构造函数注入**

  ```xml
  <!--  注意：要先提供构造函数，构造函数有几个参数，下面就写几个  -->
  <constructor-arg name="name" value="小1明"/>
  <constructor-arg name="age" value=" 10"/>
  ```

### 3、注入bean

> 有A、B两个类，在A类中使用了B类作为属性，那么我们就需要将B类注入到A类中。在A类中需要提供Set来通过Set的方法注入B类。注入类有三种方式，1.内部注入 2.外部注入 3.级联赋值

- 内部注入

  ```xml
  <bean id="emp" class="day01.Emp">
      <property name="ename" value="夏明"/>
      <property name="dept">
          <bean class="day01.Dept">
              <property name="name" value="开发部门"/>
          </bean>
      </property>
  </bean>
  ```

- 外部注入

  ```xml
  <bean id="emp" class="day01.Emp">
      <property name="ename" value="夏明"/>
      <property name="dept" ref="dep"/>
  </bean>
  
  <bean id="dep" class="day01.Dept">
      <property name="name" value="开发部门"/>
  </bean>
  ```

- 级联赋值

  > 意思是：在通过外部注入bean的过程中，在A类中提供了获取B类的方法，那么就可以在注入B类后，修改B对象的值。

  ```xml
  <bean id="emp" class="day01.Emp">
      <property name="ename" value="夏明"/>
      <!--
        级联赋值：通过外部注入时，在emp类中提供了dept属性的get方法
                   那么在注入后，还可以通过get获取到dept对象，然后通过dept.name来从新给它赋值
      -->
      <property name="dept" ref="dep"/>
      <property name="dept.name" value="财务部门"/>
  </bean>
  
  <bean id="dep" class="day01.Dept">
      <property name="name" value="开发部门"/>
  </bean>
  ```
