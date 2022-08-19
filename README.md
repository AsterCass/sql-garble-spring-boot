# sql拦截修改器 spring boot start

## 说明
1. 本项目依赖mybatis，需要项目中有引入mybaits
## 快速开始
```shell
mvn clean install
```
## 注意
1. 如果包含自定义的拦截器且拦截器的操作是使用控制invocation的args[1]来实现Sql变更的，
那么最好使用@AutoConfigureAfter让自定义的拦截器先加载
2. 使用@AutoConfigureAfter等注解时，需要注意如果使用@Configuration自定义配置类，
那么这个注解不会生效，需要在resource中添加META—INF文件夹，使用spring.factories完成自动配置类注入
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.aster.plugin.garble.spring.config.GarbleAutoConfig
```