## morning系统
前后端分离的CMS管理系统

包括PC,PC端支持响应式布局，兼容大部分的手机或者平板

后端使用SpringBoot 2.0等相关技术来实现

前后端的分离采用restful接口的方式来实现

前端主要是用VueJS来实现页面的展示和数据交互

### 快速开始
拉取项目或fuck本项目。

### 项目结构
```
morning
├─doc                 # 文档说明
├─morning-deps       # 依赖层
├─morning-admin       # 后台管理模块
├─morning-common      # 公共模块
├─morning-framework   # 框架层
├─morning-starter     # 服务入口
├─morning-service     # 服务层
├─morning-web         # 前端vuejs
└─sql                 # sql目录
```

### 部署启动
将doc目录下的sql文件导入mysql。

配置morning-api下的application-dev.yml里的配置信息

目前需要配置数据库mysql、redis（可选）、邮箱配置（可选）

然后启动模块morning-api下的MorningApplication.class




