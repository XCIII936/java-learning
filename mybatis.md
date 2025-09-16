我的maven仓库配置在D:\software\apache-maven-3.9.11-bin\apache-maven-3.9.11\mvn_resp
idea记得在设置中修改路径

mybatis官网:
https://mybatis.net.cn/getting-started.html

将jar包依赖填入maven

![alt text](images\1.png)

<!--连接信息-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mybatis?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>

映射文件为表名字加Mapper如:UserMapper

配置xxxMapper时用java下的类名来填入resultType 如:
![alt text](images\2.png)


alt + 鼠标左键可以整列操作

与pojo同级下增加MyBatisDemo

public static void main(String[] args) throws IOException {
        //加载mybatis配置文件 网站上找 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取SqlSession对象 执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql
        List<User> users=sqlSession.selectList("test.selectAll");

        System.out.println(users);
        //释放资源
        sqlSession.close();
    }
    输入以上即可实现查询功能selectAll

![alt text](images\3.png)

需要遵守的要求:
![alt text](images\4.png)

注意在resources中新建包要用/不能用.分隔
![alt text](images\5.png)

而且完成上面后路径记得变动因为把UserMapper.xml重构了

警告处理:--enable-native-access=ALL-UNNAMED


先点 修改选项 -> 再勾选 添加 VM 选项 -> 最后在出现的框里填参数。


在xml配置中environment可以多个配置不同数据源比如测试和开发用不同数据库就配置两个environment用

default切换
用<typeAliases>来将一串包名取别名
如:
![alt text](images\6.png)

MyBatis完成增删改查三步:编写接口方法->编写SQL->执行方法

数据库表的字段名称和实体类属性名称不一样就不能自动封装数据
解决方法:1.起别名 sql片段来减少每次查询的内容 2.resultMap*** 

![alt text](images\7.png)

resultType改为resultMap

其中id:唯一标识 type:映射的类型
column:表的列名  property:实体类的属性名

resultMap:1.定义resultMap标签 2.在 select标签中，使用resultMap属性替换resultType属性

在select中写条件查询 where id=#{id} 可防止sql注入 会替换为?

特殊字符处理:
1.转义字符 <为  & l t ;  (无空格)
2.CDATA区 输入CD 跳转将字符写入

用typeAliases给包起别名后可以简化resultType不用包的前缀并且默认大小写都一样只要自己的类名即可
如图

![alt text](images\8.png)

![alt text](images\9.png)

查询方法:
1.编写方法接口:Mapper接口
2.参数
3.结果 List< T >
4.编写sql语句:sql映射文件

查询多个参数要用 @Param来区别不同参数
![alt text](images\10.png)

模糊匹配在传入参数前进行参数处理 如 name="%"+name+"%;

![alt text](images\11.png)

动态sql查询满足客户只输入单个参数的查询

![alt text](images\12.png)
存在问题第一个参数没有导致sql为where and ...

解决方法:
1.缓存恒等式 where 1=1 加在第一个if标签前面
2.< where >替换where关键字

![alt text](images\13.png)

单条件动态查询

![alt text](images\14.png)
问题:无选择条件时多个where
方法:1. choose < when test=... < otherwise  2.< where>标签

实现添加功能时要手动提交事务sqlSession.commit();
也可以在SqlSession sqlSession=sqlSessionFactory.openSession();中设置参数为true 即openSession(true);

返回添加数据主键要在 insert 里加 insert useGeneratedKeys="true" keyProperty="id"

修改动态字段 用set标签 和 if test="... !=null and ... !=''"
![alt text](images\15.png)

删除操作 获取参数id void结果 
批量删除 获取id数组 
![alt text](images\16.png)
标签 where id in(foreach...);->第一种
第二种:
foreach collection="ids" item="id" separator="," open="(" close=")" #{id}
其中open 和 close 表示在开始和结束加( ) separator 表示分隔符
mybatis会将数组参数封装成一个Map集合
默认:array键 值为数组
![alt text](images\17.png)
可以用@Param注解改变map集合默认key名称(array) 加在方法声明的参数里如上两张图


MyBatis封装参数 都使用@Param注解修改 可读性高
![alt text](images\18.png)

注解完成crud简单 配置文件xml完成复杂sql(动态...)
![alt text](images\19.png)





