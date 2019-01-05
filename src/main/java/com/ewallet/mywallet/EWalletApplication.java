package com.ewallet.mywallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableOAuth2Client
public class EWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(EWalletApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		  objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
			@Override
			public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass ac) {
				if (ac.hasAnnotation(JsonPOJOBuilder.class)) {
					return super.findPOJOBuilderConfig(ac);
				}
				//for lombok generated pojo builders there wont be any annotation and the with prefix is empty and builder method is build
				return new JsonPOJOBuilder.Value("build", "");
			}
		});
		return objectMapper;
	}
}
