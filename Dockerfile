# 声明基础镜像
FROM openjdk:8-jre

# 复制Jar文件到指定目录
ADD target/lib /usr/share/java/lib

# 声明变量
ARG JAR_FILE

# 复制文件
ADD target/${JAR_FILE} /usr/share/java/app.jar

# 启动脚本
ENTRYPOINT ["java", "-jar", "/usr/share/java/app.jar"]
