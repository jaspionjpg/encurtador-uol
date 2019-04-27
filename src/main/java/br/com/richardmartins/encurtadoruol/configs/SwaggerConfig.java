package br.com.richardmartins.encurtadoruol.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${security.oauth2.client.client-id}")
	private String clienteId;
	@Value("${security.oauth2.client.client-secret}")
	private String clienteSecret;
	@Value("${url_dominio_aplicacao}")
	private String urlDominioAplicacao;
    @Bean
    public Docket apiDoc(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.richardmartins.encurtadoruol"))
//                .paths(PathSelectors.regex("/api.*"))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(Collections.singletonList(
//                    new ParameterBuilder()
//                    .name("Authorization")
//                    .description("Bearer token")
//                    .modelRef(new ModelRef("string"))
//                    .parameterType("header")
//                    .required(true)
//                    .build()))
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Projeto de teste de processo seletivo UOL")
                .description("Projeto de teste de processo seletivo da UOL, em Java com Spring Boot, PostgreSQL, Swagger, Flyway")
                .version("1.0")
                .contact(new Contact("Richard Martins", "http://richardmartins.com.br", "richardx4000@hotmail.com"))
                .license("Apache license version 2.0")
                .licenseUrl("http://www.apache.org/license/license-2-0")
                .build();
    }
    
    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
            .clientId(clienteId)
            .clientSecret(clienteSecret)
            .scopeSeparator(" ")
            .useBasicAuthenticationWithAccessCodeGrant(true)
            .build();
    }
    
    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
            .tokenEndpoint(new TokenEndpoint(urlDominioAplicacao + "/oauth/token", "oauthtoken"))
            .tokenRequestEndpoint(
              new TokenRequestEndpoint(urlDominioAplicacao + "/oauth/authorize", clienteId, clienteSecret))
            .build();
     
        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
            .grantTypes(Arrays.asList(grantType))
            .scopes(Arrays.asList(scopes()))
            .build();
        return oauth;
    }
    
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = { 
          new AuthorizationScope("password", "Accesso a API") };
        return scopes;
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
          .securityReferences(
            Arrays.asList(new SecurityReference("spring_oauth", scopes())))
          .forPaths(PathSelectors.any())
          .build();
    }
}