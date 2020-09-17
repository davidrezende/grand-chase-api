package br.com.gc.api.doc;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Arrays;
import java.util.List;

public class BaseSwaggerConfig {

	private final String basePackage;

	public BaseSwaggerConfig(String basePackage) {
		this.basePackage = basePackage;
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Grand Chase - API")
				.description("Endpoints of entitys management from game Grand Chase Season V.")
				.version("1.0")
				.contact((new Contact("David Rezende ( Haruhashi )","https://github.com/davidrezende/grand-chase-api","" )))
				.license("https://github.com/davidrezende/grand-chase-api")
				.build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.ant("/v1/**"))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("ADMIN", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(
				new SecurityReference("Token Access", authorizationScopes));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.build()
				.securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
				.securityContexts(Arrays.asList(securityContext()))
				.apiInfo(metaData());
	}
}
