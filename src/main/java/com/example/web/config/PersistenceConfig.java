package com.example.web.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

		@Autowired
	    private Environment env;
	 
		@Bean
	    public LocalSessionFactoryBean  sessionFactory() throws IllegalStateException, PropertyVetoException {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(new String[] { "com.example.dal.entity" });
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	     }
		
	    @Bean
	    public DataSource dataSource() throws IllegalStateException, PropertyVetoException {
	    	ComboPooledDataSource dataSource = new ComboPooledDataSource();
	    	dataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
	    	dataSource.setJdbcUrl(env.getRequiredProperty("jdbc.databaseurl"));
	    	dataSource.setUser(env.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
	        dataSource.setInitialPoolSize(20);
	        dataSource.setMinPoolSize(20);
	        dataSource.setMaxPoolSize(150);
	        return dataSource;
	    }
	     
	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        //properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", "true");
	        properties.put("hibernate.hbm2ddl.auto", "update");
	        //properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        return properties;        
	    }
	     
	    @Bean
	    @Autowired
	    public HibernateTransactionManager transactionManager(SessionFactory s) {
	       HibernateTransactionManager txManager = new HibernateTransactionManager();
	       txManager.setSessionFactory(s);
	       return txManager;
	    }
	    
}
