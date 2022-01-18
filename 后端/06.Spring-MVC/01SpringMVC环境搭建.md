

# SpringMVC环境搭建

### 1.SpringMVC环境搭建步骤

1. 创建一个Maven项目,并添加SpringMVC的依赖和将Maven打包方式设置为war
2. 为项目添加JavaWeb项目支持，并配置servlet和前端控制器的映射
3. 添加一个Controller的软件包，并在resources中添加SpringMVC配置文件，开始组件扫描
4. 启动项目



### 1、导入SpringMVC需要的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>demo10</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!--配置打包方式-->
    <packaging>war</packaging>


    <dependencies>
        <!--Spring坐标-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.0.5.RELEASE</version>
        </dependency>
        <!--SpringMVC坐标-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.5.RELEASE</version>
        </dependency>

        <!--Servlet坐标-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <!--Jsp坐标-->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
        </dependency>
    </dependencies>

</project>
```

### 2.添加JavaWeb支持

- 在main创建 webapp ==> WEB-INF==> web.xml 
- 在web.xml中 配置配置servlet和SpringMVC前端控制器的映射

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.0" xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">

    <!--配置前端控制器-->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

### 3. 添加SpringMVC的配置文件

- 在resources目录中添加SpringMVC配置文件

- 在Java中创建controller包，并在SpringMVC配置文件中开始这个包的注解扫描

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
  
      <!--配置注解扫描-->
      <context:component-scan base-package="controller"/>
  </beans>
  ```

### 4.创建一个测试类

- 在controller中创建一个测试类

  ```java
  @Controller
  public class TestSpringMVC {
  
      @RequestMapping("/api")
      public String test01() {
          System.out.println("sss");
          return "index.jsp";
      }
  }
  ```

### 5.配置Tomact服务

- 
