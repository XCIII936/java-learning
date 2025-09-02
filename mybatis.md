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

#{}防止sql注入 会替换为?

特殊字符处理:
1.转义字符 <为  & l t ;  (无空格)
2.CDATA区 输入CD 跳转将字符写入

