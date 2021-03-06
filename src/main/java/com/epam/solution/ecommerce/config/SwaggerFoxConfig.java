package com.epam.solution.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerFoxConfig  extends WebMvcConfigurationSupport {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.epam.solution.ecommerce"))
				.paths(PathSelectors.any()).build();
	}
	
	@Override
	protected void addResourceHandlers(final ResourceHandlerRegistry resourceHandlerRegistry) {

		resourceHandlerRegistry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:META-INF/resources/");
		resourceHandlerRegistry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:META-INF/resources/webjars");
	}
}
