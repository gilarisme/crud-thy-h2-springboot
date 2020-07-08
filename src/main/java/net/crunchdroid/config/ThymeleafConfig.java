package net.crunchdroid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ThymeleafConfig {
	
	@Value("${dir.pathInput}")
    private String pathInput;

    @SuppressWarnings("unused")
	private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    FileTemplateResolver xmlTemplateResolver(ApplicationContext appCtx) {	
        FileTemplateResolver templateResolver = new FileTemplateResolver();

        templateResolver.setSuffix(".xml");
        templateResolver.setPrefix(pathInput);
        templateResolver.setTemplateMode("XML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);

        return templateResolver;
    }

    @Bean(name="springTemplateEngine")
    SpringTemplateEngine templateEngine(ApplicationContext appCtx) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(xmlTemplateResolver(appCtx));
        return templateEngine;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }

}
