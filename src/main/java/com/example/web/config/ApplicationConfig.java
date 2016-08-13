package com.example.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.example.dal.entity.RydeRyderRequest;
import com.example.service.util.CustomStringTimeConverter;
import com.example.web.model.request.RydeCreateRequest;

@Configuration
@ComponentScan({ "com.example.dal.dao", "com.example.service", "com.example.web.*"})
@PropertySources({
    @PropertySource("classpath:common.properties"),
    @PropertySource("classpath:${spring.profiles.active}.properties")
})
public class ApplicationConfig {
	
	@Autowired
    private Environment env;

	    @Bean(name = "org.dozer.Mapper")
	    public DozerBeanMapper dozerBean() {
	      List<String> mappingFiles = Arrays.asList("dozer-object-mapper.xml");
	      List<CustomConverter> customConverters = new ArrayList<CustomConverter>();
	      customConverters.add(new CustomStringTimeConverter());
	      BeanMappingBuilder beanBuilder = new BeanMappingBuilder() {
			
			@Override
			protected void configure() {
				mapping(RydeCreateRequest.class, RydeRyderRequest.class).fields("startTime", "startTime", FieldsMappingOptions.customConverter(CustomStringTimeConverter.class));
			}
		};
	      DozerBeanMapper dozerBean = new DozerBeanMapper();
	      dozerBean.setCustomConverters(customConverters);
	      dozerBean.setMappingFiles(mappingFiles);
	      dozerBean.addMapping(beanBuilder);
	      return dozerBean;
	    }

	    @Bean
	    public JavaMailSender javaMailService() {
	        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

	        javaMailSender.setHost(env.getProperty("mail.smtp.host"));
	        javaMailSender.setPort(Integer.valueOf(env.getProperty("mail.smtp.port")));
	        javaMailSender.setUsername(env.getProperty("mail.user"));
	        javaMailSender.setPassword(env.getProperty("mail.password"));
	        javaMailSender.setJavaMailProperties(getMailProperties());
	        return javaMailSender;
	    }

	    private Properties getMailProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("mail.smtp.auth", "true");
	        properties.setProperty("mail.smtp.starttls.enable", "true");
	        properties.setProperty("mail.debug", "false");
	        return properties;
	    }
	    
	    /*@Bean
	    public VelocityEngineFactoryBean getVelocityEngine() throws VelocityException, IOException{
	      VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
	      Properties props = new Properties();
	      props.put("resource.loader","class");
	      props.put("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	      factory.setVelocityProperties(props);
	      return factory;
	    }*/
}
