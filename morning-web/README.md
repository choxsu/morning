# morning-web

> 管理后台。它只包含了 Element UI & axios & iconfont & permission control & lint，

## Build Setup

```bash
# 克隆项目
git clone https://github.com/choxsu/morning.git

# 进入项目目录
cd morning/morning-web

# 安装依赖
npm install

# 设置淘宝源注册仓库
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run dev
```

浏览器访问 [http://localhost:9528](http://localhost:9528)

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```

## 其它

```bash
# 预览发布环境效果
npm run preview

# 预览发布环境效果 + 静态资源分析
npm run preview -- --report

# 代码格式检查
npm run lint

# 代码格式检查并自动修复
npm run lint -- --fix
```
