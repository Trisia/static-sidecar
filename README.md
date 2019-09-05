# Sidecar服务说明


- springboot 2.0.7.RELEASE
- springcloud  Finchley.SR2

`pom.xml` 引入依赖
```xml
 <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
 </dependency>
 <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-netflix-sidecar</artifactId>
 </dependency>
```

`eureka-client`是用于注册发现的，`sidecar`是sidecar的代理。

在启动类上增加上`@EnableSidecar`
```java
@EnableSidecar
@SpringBootApplication
public class StaticSidecarApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticSidecarApplication.class, args);
    }
}
```

配置`resources/application.yaml`
```yaml
server:
  port: 7777
spring:
  application:
    name: static-sidecar
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

sidecar:
  port: 7776                                     # 本地的 nginx 服务端口
  health-uri: http://localhost:7776/health.json  # 健康检查
```

除了上面springboot和springcloud的常规配置之外，主要是`sidecar`的配置

1. 由于我们将会把sidecar和nginx部署到同一台机器上，所以此处不用指令nginx服务的后台地址，只需填写本地nginx启动的端口。
2. 为了能让eureka进行服务治理，sidecar需要知道nginx这边的服务状态是否可用，因此需要访问之前在前端项目中预先准备好的健康检查的文件`health.json`，所需要手动指明健康检查的路径。

启动sidecar服务，就可以完成对本地7776端口的代理并且能够从注册中心中发现该服务。
