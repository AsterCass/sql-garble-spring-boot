# spring boot starter 开发 demo

## JDK版本
1.8
## 快速开始
### 先将配置项目install到本地仓库
```shell
cd ./demo-spring-boot-autoconfigure
mvn clean install
```
### 在将starter项目install到本地仓库
```shell
cd ../demo-spring-starter
mvn clean install
```
### 在你的测试项目中引入
```xml
        <dependency>
            <groupId>com.github.aster.plugin</groupId>
            <artifactId>demo-spring-boot-starter</artifactId>
            <version>${springboot.version}</version>
        </dependency>
```
## 项目修改
1. 修改模块名称
2. 修改模块的文件名称（1、2两步可以在idea中使用shift+F6完成）
3. 修改模块的pom文件
4. 修改config模块的properties文件
5. 修改config模块的spring.factories文件
6. 修改文件路径com.github.aster.plugin -> xxx.xxx.xxx.xx
