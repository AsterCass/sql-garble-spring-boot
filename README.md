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
### 在直接的测试项目中引入
```xml
        <dependency>
            <groupId>com.github.aster</groupId>
            <artifactId>demo-spring-boot-starter</artifactId>
            <version>${springboot.version}</version>
        </dependency>
```
