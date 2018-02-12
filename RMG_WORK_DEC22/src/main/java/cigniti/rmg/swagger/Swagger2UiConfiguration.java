package cigniti.rmg.swagger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
//@PropertySource(value = { "classpath:log4j.properties" })
public class Swagger2UiConfiguration extends WebMvcConfigurerAdapter
{
    @SuppressWarnings("unchecked")
	@Bean
    public Docket api() {
        // @formatter:off
        //Register the controllers to swagger
        //Also it is configuring the Swagger Docket
    	Parameter build=	new ParameterBuilder().name("Content-Type").description("Content-Type Header").modelRef(new ModelRef("string")).parameterType("header").required(true).defaultValue("application/json").build();
    	List<Parameter> list=new ArrayList<Parameter>();
    	list.add(build);
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(list).select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("cigniti.timesheets.controller")))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Time sheet controller",
                "Alavalible services for Time sheet",
                "V 1.0",
                "No Author Details",
                "Innovation",
                "Cigniti licence version 1.0", "http://cigniti.com"
        );
        return apiInfo;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    
 
}