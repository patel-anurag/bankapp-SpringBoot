package com.cg.bankapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger in the BankApp application.

 * @see Configuration
 * @see EnableSwagger2
 */
@Configuration
@EnableSwagger2
public class BankAppConfig {

	/**
	 * The base package to be used for scanning APIs in the BankApp application for Swagger documentation.
	 * @value ${basePackage} The base package specified in the application properties for Swagger API scanning.
	 * @apiNote This value is used in the Swagger configuration to specify the base package that Swagger will scan for APIs to include in the documentation.
	*/
	@Value("${basePackage}")
	private String basePackage;

	/**
	 * 	Creates and configures a Docket bean for Swagger 2 documentation.
	 * @return Docket A Docket instance configured for Swagger 2.
	 * @apiNote The base package for scanning APIs is configured using the 'basePackage' property from application properties.
	 * This property specifies the base package that Swagger will use to scan for APIs to include in the documentation.
	 * 
	 */
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(basePackage))
				.build();
// .apiInfo(getApiMetaData());
	}
}