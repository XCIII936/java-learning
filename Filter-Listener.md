Filter简介
过滤拦截 权限控制 编码处理等等
如防止没登入直接访问数据
![alt text](images-FL\1.png)

@WebFilter("/*")拦截资源路径
Filter执行流程[[]]
![alt text](images-FL\2.png)

过滤器链
![alt text](images-FL\3.png)
执行顺序按排序
注解配置的Filter 按类名的自然排序 字母数字...

监听器Listener
![alt text](images-FL\4.png)
