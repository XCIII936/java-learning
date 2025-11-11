Cookie基本使用
数据保存到客户端 以后每次请求携带Cookie

创建Cookie 
Cookie cookie=new Cookie("username","zhangsan");

发送Cookie 
response.addCookie(cookie);

获取Cookie 1.获取Cookie数组 2.遍历数组 3.获取数据
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies)
{
    String name=cookie.getName(); 
    if("username".equals(name)){
        String value=cookie.getValue();
        System.out.println(name +":"value);
        break;
        }
    }

Cookie存活时间
默认下Cookie存在浏览器内存中 浏览器关闭 内存释放 Cookie销毁
setMaxAge(int seconds):设置Cookie存活时间
默认负数 浏览器关闭销毁
正数即为多少秒 0表示销毁

Cookie存储中文
默认不能直接存中文

进行URL编码
String value="张三";
value=URLEncoder.encode(value,"UTF-8");
获取Cookie中进行解码
value=URLDecoder.decode(value,"UTF-8");

Session数据保存到服务端 HttpSession
HttpSession session = request.getSession();
session.setAttribute("key","value");
session.getAttribute("key");
![alt text](images-Cookie-Session\1.png)
![alt text](images-Cookie-Session\2.png)

用户登入
![alt text](images-Cookie-Session\3.png)

LoginServlet书写
![alt text](images-Cookie-Session\4.png)
![alt text](images-Cookie-Session\5.png)

记住用户
![alt text](images-Cookie-Session\6.png)
设置checkbox值为1 然后在登入成功页面判断是否勾选
注意将 "1".equals(remember);这样可以有效防止空指针异常
创建两个Cookie 分别存账号密码 然后持久存储设置存活时间 setMaxAge()
response.addCookie(...);*2 发送Cookie
在login.jsp中用EL表达式 ${cookie.key.value}来获取Cookie
![alt text](images-Cookie-Session\7.png)


注册用户
![alt text](images-Cookie-Session\8.png)
注册成功带信息跳转到登入页面

验证码展示实现
工具类如下
![alt text](images-Cookie-Session\9.png)

![alt text](images-Cookie-Session\10.png)
可以防止机器自动注册攻击服务器

只需把OutputStream fos = new FileOutputStream("....");
换为Response的字节输出流即可
![alt text](images-Cookie-Session\11.png)

用JavaScript实现看不清功能
![alt text](images-Cookie-Session\12.png)

注意要用时间来填入src路径防止缓存导致图片无法切换
![alt text](images-Cookie-Session\13.png)

验证码功能完整jsp
![alt text](images-Cookie-Session\14.png)

验证码校验
将生成的验证码存入Session中
![alt text](images-Cookie-Session\15.png)

比对忽略大小写
![alt text](images-Cookie-Session\16.png)
