## morning系统
主要是做一个全新前后端分离的CMS管理系统，包括PC,PC端会做成响应式的布局，  
 兼容大部分的手机或者平板；后端会使用SpringBoot 2.0来实现；前后端的分离采用接口的方式来实现，
 前端主要是用Vue js 来实现页面的数据交互; 

### 快速开始
拉取项目或fuck项目。

### 项目结构
```
morning
├─doc                 # sql文件等
├─morning-api         # 服务api
├─morning-common      # 公共模块
├─morning-model       # 实体相关
├─morning-web         # 后台管理页面
└─morning-service     # 服务层
```

### 部署启动
将doc目录下的sql文件导入mysql。

配置morning-api下的application-dev.yml里的配置信息

目前需要配置数据库mysql、redis（可选）、邮箱配置（可选）

然后启动模块morning-api下的MorningApplication.class




