### 初始化sql文件，在doc目录下的
执行sql文件，启动项目即可
   
   
### 部署启动
服务器部署启动，请在在java -jar xxx.jar 之后加上参数--spring.profiles.active=prod会读取
application-prod.yml的配置文件，可以将正式库的数据库密码和Redis密码等相关的敏感数据配置在
application-prod.yml中。application-prod.yml文件需要自己复制一份改名称和改相应的数据，
本项目中就放出来了
      
      