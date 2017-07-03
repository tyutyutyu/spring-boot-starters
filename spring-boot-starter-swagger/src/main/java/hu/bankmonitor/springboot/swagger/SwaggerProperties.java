package hu.bankmonitor.springboot.swagger;

import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
@Slf4j
@ToString
public class SwaggerProperties {

	private String host;

	private ApiInfo apiInfo;

	private Map<SelectorType, String> pathSelectors;

	@PostConstruct
	public void init() {

		log.debug("init - properties: {}", this);
	}

}
