JSP:Java Server Pages
Java服务端页面 动态网页技术 可以定义多种静态内容或java动态代码
JSP=HTML+Java

![alt text](images-JSP\1.png)

快速入门JSP 导入坐标 创建文件 编写标签、代码
![alt text](images-JSP\2.png)

JSP原理本质上为Servlet
![alt text](images-JSP\3.png)

JSP脚本分类:
<%...%>内容直接放到_jspService()方法中
<%=...%>内容放到out.print()中作为其参数
<%!...%>内容放到_jspService()方法外 被HttpJspBase类直接包含

Java代码可以截断 直接<%%>HTML<%%>

JSP缺点 书写、运行、阅读麻烦...
![alt text](images-JSP\4.png)

EL表达式替换获取数据代码 Expression Language
语法:${expression} 获取域中存储的key为expression的数据
jsp页面加上<%@ page isELIgnored="false"%>不然无法识别EL
![alt text](images-JSP\5.png)

${pageContext.request.contextPath}获取当前Web应用的上下文路径

JSTL标签替换循环遍历代码(Jsp Standarded Tag Library)
![alt text](images-JSP\6.png)

循环标签
varStatus遍历状态对象 status从0开始 count从1开始
常规循环 begin end step var
![alt text](images-JSP\7.png)

MVC分层开发:Model View Controller
职责单一 分工协作 组件重用

三层架构对MVC架构的实现思想 表现层 业务逻辑层 数据访问层
SpringMVC Spring Mybatis ->SSM

Service书写查询
![alt text](images-JSP\8.png)

添加方式
![alt text](images-JSP\9.png)
![alt text](images-JSP\10.png)
添加注意路径大小写 还有sqlSession.commit();
乱码问题解决方案 在AddServlet中处理post请求的乱码问题
request.setCharacterEncoding("UTF-8")

回显数据(修改时展示数据)
状态回显要做判断 
update.jsp写个隐藏域 input type=hidden value="${brand.id}"
从DAO写起->Service->jsp->Servlet
与添加相比要多接受一个id来判断哪个要修改
![alt text](images-JSP\11.png)
![alt text](images-JSP\12.png)




