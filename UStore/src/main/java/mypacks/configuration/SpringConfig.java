package mypacks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import mypacks.dao.LoginDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"mypacks.*","mypacks.model"})
public class SpringConfig extends WebMvcConfigurerAdapter
{
	@Bean
	public TilesConfigurer tilesConfigurer()
	{
		TilesConfigurer tilesConfigurer=new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles-def/tiles.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	/*@Bean  
	public ResourceBundleViewResolver resourceBundleViewResolver() 
	{  
		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
		resolver.setBasename("views");
		resolver.setOrder(0);
        return resolver;  
    }*/
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) 
	{
		TilesViewResolver tilesViewResolver=new TilesViewResolver();
		tilesViewResolver.setOrder(1);
		registry.viewResolver(tilesViewResolver);
		
		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
		resolver.setBasename("views");
		resolver.setOrder(0);
		
		registry.viewResolver(resolver);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	
}
