package hu.bankmonitor.springboot.cors;

import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(CorsMappingProperties.class)
@Slf4j
public class CorsAutoConfiguration extends WebMvcConfigurerAdapter {

	private static final String[] DEFAULT_ALLOWED_ORIGINS = CrossOrigin.DEFAULT_ORIGINS;

	private static final String[] DEFAULT_ALLOWED_METHODS = new String[] { HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name() };

	private static final String[] DEFAULT_ALLOWED_HEADERS = CrossOrigin.DEFAULT_ALLOWED_HEADERS;

	private static final String[] DEFAULT_EXPOSED_HEADERS = new String[] { };

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
							.allowedOrigins(extractCorsProps(mappingEntry.getValue().getAllowedOrigins(), DEFAULT_ALLOWED_ORIGINS))
							.allowedMethods(extractCorsProps(mappingEntry.getValue().getAllowedMethods(), DEFAULT_ALLOWED_METHODS))
							.allowedHeaders(extractCorsProps(mappingEntry.getValue().getAllowedHeaders(), DEFAULT_ALLOWED_HEADERS))
							.exposedHeaders(extractCorsProps(mappingEntry.getValue().getExposedHeaders(), DEFAULT_EXPOSED_HEADERS))
							.allowCredentials(BooleanUtils.toBooleanDefaultIfNull(mappingEntry.getValue().getAllowCredentials(), CrossOrigin.DEFAULT_ALLOW_CREDENTIALS))
							.maxAge(MoreObjects.firstNonNull(mappingEntry.getValue().getMaxAge(), CrossOrigin.DEFAULT_MAX_AGE));
				// @formatter:on
			}
		}
	}

	private static String[] extractCorsProps(List<String> prop, String[] defaultProp) {

		if (CollectionUtils.isEmpty(prop)) {
			return defaultProp;
		}

		return prop.toArray(new String[] { });
	}

}
