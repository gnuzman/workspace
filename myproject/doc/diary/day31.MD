尝试使用高频切换法
##1.redis x
    已完成最基本的操作。x
    5种数据结构的操作。
##2.elasticsearch x
    完成windows下的安装，运行。x
    基本的添加查找功能能。
添加插件
https://my.oschina.net/frylan/blog/639980
##3.aop x
    aop的基本操作
    aop切所有的controller操作
##4.logback
##5.design pattern x
 单例
##6.java
##7.netty
##8.kafka
    下载资源
##9.druid连接池 x
spring boot 集成druid
https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
##10.MD文件

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(newHashSet("http"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```