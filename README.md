## 项目说明 

基于SpringBoot、MyBatis、Shiro框架的一套后台管理系统框架，支持MariaDB等数据库。

## 软件需求 
- JDK11 
- MySQL5.5+
- Maven3.0+

## 技术选型
- 核心框架：Spring Boot 2.7
- 权限框架：Apache Shiro 2.0
- 视图框架：Spring MVC 5.3
- 数据持久：MyBatis Plus 3.5
- 定时任务：Quartz 2.4
- 日志管理：SLF4J 1.7、Log4j 2.17

## 项目特点
- 二次开发友好
- 框架代码封装隔离
- 基于Shiro的双权限
- 支持XSS防御
- 支持防SQL注入
- 支持分布式部署
- 集成Quartz任务

## 权限架构
- 数据权限：通过部门管理、数据创建人实现数据权限隔离。
- 功能权限：基于角色管理实现菜单、按钮等功能权限。


## 项目结构 
```
timbercld-sys-service
├─timbercld-core     框架核心模块
│ 
├─timbercld-core-ws  框架核心服务（Web Service）
│    ├─authority 系统权限
│    ├─logger 系统日志服务
│    ├─scheduler 定时任务
│    ├─subsys 子系统管理
│    ├─system 系统管理（基础功能）
│    └─resources
│        ├─mapper   MyBatis文件
│
│
├─timbercld-starter  框架启动器
│    ├─TimbercldAppStarter 启动入口
│    ├─demo 开发示例
└─resources
│        ├─mapper   MyBatis文件
│        └─application.yml   全局配置文件
```

## 📖 开源共建

### 开源协议

[木链云](timbercld.com)开源软件遵循 [Apache 2.0 协议](https://www.apache.org/licenses/LICENSE-2.0.html)。
允许商业使用，但务必保留类作者、Copyright 信息。

![](https://www.apache.org/img/asf-estd-1999-logo.jpg)

### 其他说明

1. 欢迎提交 [issue](https://gitee.com/timbercld/timbercld-sys-frame/issues)，请写清楚遇到问题的原因、开发环境、复现步骤。

2. 联系作者 <a href="mailto:account@timbercld.com">account@timbercld.com</a>