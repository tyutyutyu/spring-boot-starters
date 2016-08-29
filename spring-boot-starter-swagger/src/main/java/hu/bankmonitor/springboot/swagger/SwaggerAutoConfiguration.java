package hu.bankmonitor.springboot.swagger;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
public class SwaggerAutoConfiguration {

	@Autowired
	private SwaggerProperties properties;

	@Bean
	public Docket swaggerSpringfoxDocket() {

		// @formatter:off
        ApiSelectorBuilder selectorBuilder = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(properties.getApiInfo().convertToSwaggerApiInfo())
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(java.time.LocalDate.class, String.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select();
        // @formatter:on

		for (Map.Entry<SelectorType, String> selectorEntry : properties.getPathSelectors().entrySet()) {
			if (selectorEntry.getKey() == SelectorType.ANT) {
				selectorBuilder.paths(PathSelectors.ant(selectorEntry.getValue()));
			} else if (selectorEntry.getKey() == SelectorType.REGEX) {
				selectorBuilder.paths(PathSelectors.regex(selectorEntry.getValue()));
			}
		}

		return selectorBuilder.build();
	}

}
