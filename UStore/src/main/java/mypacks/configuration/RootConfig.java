package mypacks.configuration;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mypacks.model.Category;
import mypacks.model.Product;
import mypacks.model.Register;
import mypacks.model.Role;

@Configuration
@ComponentScan(basePackages={"mypacks"})
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class RootConfig
{
	@Bean(name="dataSource")
	public DataSource getDataSource() throws FileNotFoundException, IOException
	{
		/*Properties prop=new Properties();
		prop.load(new FileInputStream("mysql.properties"));
		
		
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(prop.getProperty("driverClass"));
		dataSource.setUrl(prop.getProperty("url"));
		dataSource.setUsername(prop.getProperty("username"));
		dataSource.setPassword(prop.getProperty("password"));*/
		
		
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/OnlineShop");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	return properties;
    }
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	   	sessionBuilder.addProperties(getHibernateProperties());
	   	/*sessionBuilder.addAnnotatedClasses(Register.class);
	   	sessionBuilder.addAnnotatedClasses(Role.class);
		sessionBuilder.addAnnotatedClasses(Category.class);
		sessionBuilder.addAnnotatedClasses(Product.class);*/
	   	sessionBuilder.scanPackages("mypacks.model");
	   	return sessionBuilder.buildSessionFactory();
	    }
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
}
