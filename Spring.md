Spring Framework 是其他子项目的基础

核心模块:IoC AOP
IoC:Inverse of Control 控制反转
AOP: Aspect Oriented Programming 面向切面编程
![alt text](images-Spring\1.png)

Spring Framework 特点
![alt text](images-Spring\2.png)

Spring依赖引入
![alt text](images-Spring\3.png)

Spring入门开发步骤
![alt text](images-Spring\4.png)

Spring运用
![alt text](images-Spring\5.png)
示例:
//加载Spring配置文件 对象创建
        ApplicationContext context=
                new ClassPathXmlApplicationContext("bean.xml");
        //获取创建的对象
        User user=(User)context.getBean("user");
        System.out.println(user);
        //使用对象调用方法测试
        user.add();

无参数构造会执行
不用new方式 用反射创建对象
使用返回的创建对象：
1.加载bean.xml配置文件
2.对xml文件进行解析操作
3.获取xml文件bean标签属性值(id class)
4.使用反射根据类全路径创建对象
![alt text](images-Spring\6.png)

将创建对象放到 Map< String,BeanDefinition > beanDefinitionMap
key:唯一表识  value:类的定义(描述信息)

启用Log4j2日志框架
![alt text](images-Spring\7.png)

日志书写 log4j2.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <loggers>
    <root level="DEBUG">
        <appender-ref ref="spring6log"/>
        <appender-ref ref="RollingFile"/>
        <appender-ref ref="log"/>
    </root>
    </loggers>
    <appenders>
        <console name="spring6log" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
        </console>
        <File name="log" fileName="d:/java/spring6_log/test.log" append="false">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
        </File>
        <RollingFile name="RollingFile" fileName="d:/java/spring6_log/app.log"
                     filePattern="log/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd 'at' HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
            <SizeBasedTriggeringPolicy size="50MB"/>
            <!--默认最多统一文件夹下7个文件 重新设为20-->
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>
    </appenders>

</configuration>
```


容器IoC
一种设计思想 管理Java对象的实例化初始化 控制对象间依赖关系
被管理的Java对象为Spring Bean 与new出的对象无区别

Bean定义信息 BeanDefinition 
用接口BeanDefinitionReader加载生成类

IoC中用BeanFactory工厂+反射进行实例化 再进行初始化 生成最终对象
用Context.getBean("user");获取
![alt text](images-Spring\8.png)


DI:Dependency Injection 依赖注入
指Spring创建对象的过程中 将对象依赖属性通过配置进行注入
有1.set注入 2.构造注入
实现了控制反转的思想

获取bean对象方法
1.根据id 2.根据类型 3.根据id和类型
![alt text](images-Spring\9.png)
注意根据类型时相同的类对象只能有一个bean
根据类型获取时 一个接口多个实现类无法通过bean获取 bean不唯一

## 创建对象过程中向属性设置值 
1.set 
生成set属性方法 在spring配置文件中配置
在bean中加标签property name="..." value="..."
![alt text](images-Spring\10.png)
2.构造器
生成带参构造方法
在bean中加标签constructor-arg name="..." value="..."(用index代替name要保证位置相同容易混)

## 特殊值处理
1.字面量 2.null值加< null/ >标签 
3.xml实体加转义符号 4.CDATA区 
![alt text](images-Spring\11.png)

## 特殊类型属性注入
### 1.对象类型
(1)外部bean 
value由ref替换 指向另一个的bean的id
![alt text](images-Spring\12.png)

(2)内部bean
在bean内部property后不写value而是再定义一个bean
![alt text](images-Spring\13.png)

(3)级联赋值
将property name中dept.dname后直接改value实现级联赋值
![alt text](images-Spring\14.png)

### 2.数组类型
加上array标签 array里面写value标签来进行赋值
![alt text](images-Spring\15.png)

### 3.Map集合
![alt text](images-Spring\16.png)

### 4.List集合
注入普通类型 用util:类型 定义 完成list、map类型注入
要引入标签约束 复制将beans改为util
![alt text](images-Spring\17.png)
![alt text](images-Spring\18.png)


## 引入外部属性文件
1.引入数据库相关依赖
pom.xml引入依赖

2.创建外部属性文件 properties格式 定义数据信息
resources文件夹加入properties
![alt text](images-Spring\19.png)

3.创建spring配置文件 引入Context命名空间 引入属性文件 使用表达式完成注入
![alt text](images-Spring\20.png)

## bean作用域
scope来指定 默认singleton 单实例IOC容器初始化时创建对象
prototype 多实例 获取bean时创建

## bean的生命周期
![alt text](images-Spring\21.png)

# 注解开发
格式 @注解(属性1=属性值...)

1.引入依赖 
2.开启组件扫描(先引入名称空间context) 
```
<context:component-scan base-package="包路径">
```
![alt text](images-Spring\22.png)

@Autowired 根据类型自动装配
注意点
![alt text](images-Spring\23.png)

有两个实现类时 要用两个注解根据名称注入
@Autowired @Qualifier(value="user....Impl")

@Resource 指定名称注入 不指定根据类型名称注入

全注解开发 增加配置类 @Configuration


# AOP(Aspect-Oriented-Programming)
面向切面编程

## 代理模式

![alt text](images-Spring\24.png)

### 静态代理
将被代理对象传进来 调用目标对象的方法实现核心业务
不具备灵活性 日志没有统一管理
### 动态代理
![alt text](images-Spring\25.png)
创建代理工厂类
传递目标对象 返回代理对象
return Proxy.newProxyInstance()方法的三个参数
1.ClassLoader 加载动态生成代理类的加载器
2.interfaces 目标对象实现的所有接口的class类型数组
3.InvocationHandler 设置代理对象实现目标对象方法的过程
创建代理对象(动态) 调用方法

## AOP相关术语

### 横切关注点
分散在各个模块中解决一样的问题 如验证、日志...

### 通知
![alt text](images-Spring\26.png)

### 切面
封装通知方法的类

### 目标和代理
被代理的目标对象 创建的代理对象

### 连接点
spring预习使用通知的地方

### 切入点
定位连接点的方式
实际去增强的方法

## JDK动态代理和cglib动态代理
有接口使用JDK 生成接口实现类代理对象proxy实现interface

无接口 用cglib动态代理 生成子类代理对象 继承被代理的目标类

## AspectJ 
AOP思想的实现 本质上是静态代理
将代理逻辑织入目标类编译得到字节码文件 效果为动态

## 实现AOP功能
1.添加依赖
aop aspects

2.创建目标资源
接口 实现类

3.创建切面类
切入点 
execution(访问修饰符 增强方法返回类型 正确方法所在类全路径 方法名称)
![alt text](images-Spring\27.png)
通知类型
```
@Before(value="excution(...)")
public void beforeMethod(){...}
```
前置@Before 
![alt text](images-Spring\28.png)
返回@AfterReturning可以得到目标方法的返回值
异常@AfterThrowing
后置@After 
环绕@Round ProceedingJoinPoint子接口有个proceed方法

### 重用切入点表达式
```
@Pointcut(value="execution(...)")
public void pointCut(){}
```
然后把其他的value后面一串换为pointCut()即可
不是同一个切面要加上 包的名称.类名字.方法

### 基于配置文件xml实现
beanaop.xml配置文件加入
开启组件扫描
![alt text](images-Spring\29.png)
配置aop五种通知类型
![alt text](images-Spring\30.png)
![alt text](images-Spring\31.png)