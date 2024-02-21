@echo off

set REVERSION=1.0.4
set SERVER_NAME=ycc-imt
set CHANGE_LIST=-RELEASE
echo Server:%SERVER_NAME%,Version:%REVERSION%,changelist:%CHANGE_LIST%
echo SYS OS: %OS%
@REM 设置源文件路径
set sourcePath=..\target\%SERVER_NAME%.jar

echo First parameter: %1
set skip_build=%1

@REM 编译打包
if not "%skip_build%" equ "skip" (
    call mvn clean package -Dreversion=%REVERSION% -Dchangelist=%CHANGE_LIST% -pl ycc-modules/ycc-imt -am -f ../../../pom.xml
)

@REM copy jar file to bin
echo Copying JAR file...
REM 使用 xcopy 命令复制文件
xcopy /Y %sourcePath% .
echo JAR file copied successfully.
@REM 编译image
call docker build -t ccr.ccs.tencentyun.com/docker_xqsu/%SERVER_NAME%:%REVERSION% .
@REM docker编译完成后删除bin目录中jar
echo Deleting JAR file...
REM 使用 del 命令删除当前目录（bin 目录）中的文件
del /F %SERVER_NAME%.jar
echo JAR file deleted successfully.
@REM 推送image 之前需要先登录docker
call docker tag ccr.ccs.tencentyun.com/docker_xqsu/%SERVER_NAME%:%REVERSION% ccr.ccs.tencentyun.com/docker_xqsu/%SERVER_NAME%:latest
call docker push ccr.ccs.tencentyun.com/docker_xqsu/%SERVER_NAME%:%REVERSION%
call docker push ccr.ccs.tencentyun.com/docker_xqsu/%SERVER_NAME%:latest
echo image push successfully
@REM 移除image latest版本 保留有版本号的版本
call docker image rm ccr.ccs.tencentyun.com/docker_xqsu/%SERVER_NAME%:latest
echo local image remove successfully