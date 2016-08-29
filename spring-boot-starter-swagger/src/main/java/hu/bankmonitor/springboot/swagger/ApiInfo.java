package hu.bankmonitor.springboot.swagger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiInfo {

	private String version;

	private String title;

	private String description;

	private String termsOfServiceUrl;

	private String license;

	private String licenseUrl;

	private Contact contact;

	public springfox.documentation.service.ApiInfo convertToSwaggerApiInfo() {

		return new springfox.documentation.service.ApiInfo(title, description, version, termsOfServiceUrl, contact.convertToSwaggerContact(), license, licenseUrl);
	}

}
