package hu.bankmonitor.springboot.cors;

import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(CorsMappingProperties.class)
@Slf4j
public class CorsAutoConfiguration extends WebMvcConfigurerAdapter {

	private static final CorsConfiguration DEFAULT_CORS_CONFIGURATION = new CorsConfiguration().applyPermitDefaultValues();

	@Autowired
	private CorsMappingProperties properties;

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		if (properties.getCorsMappings() != null) {

			for (Map.Entry<String, CorsConfiguration> mappingEntry : properties.getCorsMappings().entrySet()) {

				log.debug("addCorsMappings - mappingEntry: {}", mappingEntry);

				// @formatter:off
				registry
						.addMapping(mappingEntry.getKey())
							.allowedOrigins(extractCorsProps(mappingEntry.getValue().getAllowedOrigins(), DEFAULT_CORS_CONFIGURATION.getAllowedOrigins()))
							.allowedMethods(extractCorsProps(mappingEntry.getValue().getAllowedMethods(), DEFAULT_CORS_CONFIGURATION.getAllowedMethods()))
							.allowedHeaders(extractCorsProps(mappingEntry.getValue().getAllowedHeaders(), DEFAULT_CORS_CONFIGURATION.getAllowedHeaders()))
							.exposedHeaders(extractCorsProps(mappingEntry.getValue().getExposedHeaders(), DEFAULT_CORS_CONFIGURATION.getExposedHeaders()))
							.allowCredentials(BooleanUtils.toBooleanDefaultIfNull(mappingEntry.getValue().getAllowCredentials(), DEFAULT_CORS_CONFIGURATION.getAllowCredentials()))
							.maxAge(MoreObjects.firstNonNull(mappingEntry.getValue().getMaxAge(), DEFAULT_CORS_CONFIGURATION.getMaxAge()));
				// @formatter:on
			}
		}
	}

	private static String[] extractCorsProps(List<String> prop, List<String> defaultProp) {

		if (CollectionUtils.isEmpty(prop)) {
			return defaultProp.toArray(new String[] { });
		}

		return prop.toArray(new String[] { });
	}

}
