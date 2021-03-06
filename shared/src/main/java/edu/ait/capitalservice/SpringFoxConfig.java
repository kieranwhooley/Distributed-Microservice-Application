package edu.ait.capitalservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    public static final Contact CUSTOM_CONTACT = new Contact("Kieran Whooley", "www.ait.ie", "A00279690@student.ait.ie");
    public static final ApiInfo CUSTOM_API_INFO = new ApiInfoBuilder().title("US State Capital Service")
            .description("A list of US State Capitals")
            .version("1.0")
            .contact(CUSTOM_CONTACT)
            .build();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("edu.ait.capitalservice"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(CUSTOM_API_INFO);
    }
}
