package com.finest.zhy.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 *
 * @author kezy
 * @date 2019-05-27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.finest"))
                .paths(PathSelectors.any())
                .build()
               /* .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())*/;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智慧指挥调度平台系统接口详情")
                .termsOfServiceUrl("http://www.finest.com.cn")
                .contact(new Contact("finest", "http://www.finest.com.cn", ""))
                .version("v1.0")
                .build();
    }

}
