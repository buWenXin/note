



## 1.IOC容器注解

- **实例化Bean注解：**  

  - 作用在类上，将类作为组件添加到IOC容器中进行管理

  | 注解        | 说明                              |
  | ----------- | --------------------------------- |
  | @Component  | 使用在类上用于实例化Bean          |
  | @Controller | 使用在web层类上用于实例化Bean     |
  | @Service    | 使用在service层类上用于实例化Bean |
  | @Repository | 使用在dao层类上用于实例化Bean     |

- **依赖注入注解：**

  | 注解           | 说明                                                         |
  | -------------- | ------------------------------------------------------------ |
  | @Autowired     | 使用在字段上用于根据*类型*依赖注入                           |
  | @Qualifier     | 需要结合`@Autowired` 一起使用，根据名称注入。                |
  | @Resource      | 相当于上面的结合，如果不指定名称则根据类型注入，如果指定名称则根据名称注入 |
  | @Value         | 注入普通属性                                                 |
  | @PostConstruct | 使用在方法上标注该方法是Bean的初始化方法                     |
  | @PreDestroy    | 使用在方法上标注该方法是Bean的销毁方法                       |

- **配置文件注解：**

  | 注解            | 说明                                                         |
  | --------------- | ------------------------------------------------------------ |
  | @Configuration  | 标识在类上，将该类作为Spring的配置类                         |
  | @Bean           | 标识在有`@Configuration注解`的类中的方法上，将方法的返回值作为组件添加到容器中 |
  | @ComponentScan  | 标识在有`@Configuration注解`的类上，设置扫描注解包           |
  | @Import         | 标识在有`@Configuration注解`的类上，导入类作为组件添加到容器中 |
  | @Scope          | 标识在有`@Configuration注解`的类上，标注Bean的作用范围       |
  | @PropertySource | 标识在有`@Configuration注解`的类上，用于加载.properties 文件中的配置 |

## 2.AOP切面注解

> AspectJ 框架为 AOP 开发提供了一套注解。AspectJ 允许使用注解定义切面、切入点和增强处理，Spring 框架可以根据这些注解生成 AOP 代理。

| 名称            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| @Aspect         | 用于定义一个切面。用在加强类上                               |
| @Pointcut       | 用于定义一个切入点。                                         |
| @Before         | 用于定义前置通知，相当于 BeforeAdvice。                      |
| @AfterReturning | 用于定义后置通知，相当于 AfterReturningAdvice。              |
| @Around         | 用于定义环绕通知，相当于MethodInterceptor。                  |
| @AfterThrowing  | 用于定义抛出通知，相当于ThrowAdvice。                        |
| @After          | 用于定义最终final通知，不管是否异常，该通知都会执行。        |
| @DeclareParents | 用于定义引介通知，相当于IntroductionInterceptor（不要求掌握）。 |