解决URL路径 运行->编辑配置->部署->下面看到运用上下文

动态调试 部署服务器 启用调试功能 选择更新类和资源 F5刷新界面

![alt text](images-HTTP\1.png)

请求数据格式 请求行 头 体

头和体有空行
![alt text](images-HTTP\2.png)

响应数据格式 响应行 头 体
![alt text](images-HTTP\3.png)

常见状态码 
![alt text](images-HTTP\4.png)

响应状态码查询网址:https://cloud.tencent.com/developer/chapter/13553

localhost:8080可以换为本机ipv4地址加:8080

ctrl+c 正常关闭Tomcat服务器 startup.bat启动服务器

![alt text](images-HTTP\5.png)

使用骨架 maven archetype 选webapp
![alt text](images-HTTP\6.png)

将Tomcat集成到idea 运行->编辑配置->右上角新增配置->选择Tomcat路径->部署项目
![alt text](images-HTTP\7.png)

导入插件 右键项目运行 Tomcat7
![alt text](images-HTTP\8.png)

![alt text](images-HTTP\9.png)
创建web项目 导入Servlet坐标
scope provided
定义类实现Servlet接口重写方法
配置访问路径@WebServlet("/d1")
启动Tomcat 浏览器输入url(/d1)

Servlet由web服务器创建/调用

![alt text](images-HTTP\10.png)
放loadOnStartup=1在@WebServlet()里面
在服务器启动生成Servlet

Servlet体系 只需继承HttpServlet后重写doGet和doPost
![alt text](images-HTTP\11.png)

Post用写个表单提交方式测试
![alt text](images-HTTP\12.png)

请求方式不同分别处理 1.获取请求方式 2.判断 
![alt text](images-HTTP\13.png)

一个Servlet可以配多个urlPattern@WebServlet(urlPatterns={"/d1","/d2"})
多种配置规则
![alt text](images-HTTP\14.png)

精确匹配优先级高于目录匹配
/* 优先级高于 /   /会覆盖defaultServlet导致静态资源访问失效

xml配置Servlet 版本3.0前用的
![alt text](images-HTTP\15.png)

Request获取请求参数 doPost 用this.doGet代码写在doGet复用
![alt text](images-HTTP\16.png)

idea用模版创建 右键包->新建->Servlet
设置 editor里面找到可以修改模版
![alt text](images-HTTP\17.png)

中文乱码解决方案
Post:request.setCharacterEncoding("UTF-8")
URL编码:字符串转为二进制 每个字节转为2个16进制数并在前面加上%
Get: new String(username.getBytes("ISO-8849-1"),"UTF-8");
![alt text](images-HTTP\18.png)

请求转发:
req.getRequestDispatcher("资源b路径").forword(req,resp);无需虚拟目录
资源间共享数据 路径不发生改变 只能转发到服务器内部资源 只有一次请求
![alt text](images-HTTP\19.png)

Response方法功能
![alt text](images-HTTP\20.png)

重定向完成 状态码302 响应头 location:...
实现方式 resp.setStatus(302); resp.setHeader("location","访问路径");
路径要加上项目名称 如 /project/...
简化方式:response.sendRedirect("访问路径");
重定向可以访问服务器外部资源 两次请求 路径变化
![alt text](images-HTTP\21.png)

路径问题 浏览器使用加虚拟目录(项目访问路径) 服务器用无需加
动态获取虚拟目录 contextPath=request.getContextPath();

Response响应字符数据 
1.获取字符输出流 PrintWriter writer=response.getWriter();
writer.write("...") 
设置头信息 response.setHeader("content-type","text/html");
这个流无需关闭自动回随着Response销毁
中文不支持会乱码 
2.设置流编码 
response.setContentType("text/html;charset=utf-8");直接替代setHeader

Response响应字节数据(图片视频音频...)
1.读取文件
FileInputStream fis = new FileInputStream("路径");

2.获取Response字节输出流
ServletOutputStream os=response.getOutputStream();

3.完成流的copy
byte[] buff=new byte[1024]; int len=0; 
while((len=fis.read(buff))!=-1){os.write(buff,0,len);} fis.close();

一般采用工具类完成上面操作 commons-io
IOUtils.copy(fis,os)->(输入流，输出流);替代3操作
![alt text](images-HTTP\22.png)

mybatis-config.xml中把java下的包起别名到pojo 然后url中&在xml无法识别改为&amp;
![alt text](images-HTTP\24.png)


response.setContentType(...); PrintWriter writer=response.getWriter();
然后判断是否为null
![alt text](images-HTTP\23.png)

SqlSessionFactory代码优化
![alt text](images-HTTP\25.png)
用工具类util完成静态代码块
static{try{}catch(){}}
![alt text](images-HTTP\26.png)