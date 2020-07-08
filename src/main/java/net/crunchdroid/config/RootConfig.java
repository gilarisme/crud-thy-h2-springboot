package net.crunchdroid.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "net.crunchdroid")
@PropertySources({
		@PropertySource("file:/opt/application.properties"),
		@PropertySource("file:/opt/path.properties")})
public class RootConfig {
	

	@PostConstruct
	public void initApp() {
		System.out.println("Starting application.. hua hua hua");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
