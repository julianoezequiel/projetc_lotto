package br.com.ot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;

@SpringBootApplication(exclude = {
GroovyTemplateAutoConfiguration.class})
public class ProjectOtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOtApplication.class, args);
	}
}
