package mypacks.configuration;



import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class UStoreWebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer 
									
{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{RootConfig.class,SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}

}
