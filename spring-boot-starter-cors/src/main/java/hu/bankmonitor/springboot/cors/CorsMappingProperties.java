package hu.bankmonitor.springboot.cors;

import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@Setter
@Slf4j
@ToString
public class CorsMappingProperties {

	private Map<String, CorsConfiguration> corsMappings;

	@PostConstruct
	public void init() {

		log.debug("init - properties: {}", this);
	}

}
