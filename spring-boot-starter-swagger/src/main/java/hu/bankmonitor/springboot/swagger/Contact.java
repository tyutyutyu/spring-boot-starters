package hu.bankmonitor.springboot.swagger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contact {

	private String name;

	private String url;

	private String email;

	public springfox.documentation.service.Contact convertToSwaggerContact() {

		return new springfox.documentation.service.Contact(name, url, email);
	}

}
