xiexiaozhang: 视频直播解决方案企业站

web/main/src/java	前台控制器
web/admin/src/java	后台控制器
service/src/java	服务层
dao/src/java		数据访问层
model/src/java		实体层
util/src/java		工具类
cron/src/java		定时任务

conf				配置文件
resources			资源文件


后台引入的第三方组件：
zookeeper：
dubbo：服务治理
druid：数据库连接池、SQL解析
shiro：权限系统

/************************************************/
springMvc + zookeeper(redis/memcache) + dubbo架构：
zookeeper作为注册中心(registry),发布订阅服务
springMvc的controller层调用服务,在配置文件配置dubbo
dubbo发布服务(服务提供者),订阅服务(服务消费者),监控服务(monitor),调度服务(schedule)
/************************************************/

第三方JS库：
1.layer弹层：	http://layer.layui.com
2.日历插件		http://www.layui.com/laydate
3.分页插件		http://www.layui.com/laypage
4.报表数据可视化：echarts.js
	http://echarts.baidu.com
	demo示例：http://echarts.baidu.com/echarts2/doc/example.html
	api文档：http://echarts.baidu.com/echarts2/doc/doc.html
5.文件上传(jquery-uploadify)	http://www.uploadify.com


集成第三方：

1.touclick
java-sdk-1.1.0.jar  jackson-*.jar

2.1 qq互联登录
Sdk4J.jar
qqconnectconfig.properties

2.2 微博互联
weibo4j.jar
config.properties

2.3 微信登录

3.聚合支付
MustPay	https://www.mustpay.com.cn

4.支付宝支付集成
缺少营业执照,无法申请

5.短信验证
LeanCloud  https://leancloud.cn		Hengyang1990
(或自行到移动/电信/中国网建(http://sms.webchinese.com.cn/)申请web接口)

6.聚合
https://www.juhe.cn

7.云脉OCR
http://www.yunmaiocr.com

8.人脸识别
检测库：openCV --> javaCV
调用第三方SDK： face++ / 商汤

9.在线编辑器：
xheditor
editor.md

10.百度开发者
http://developer.baidu.com

