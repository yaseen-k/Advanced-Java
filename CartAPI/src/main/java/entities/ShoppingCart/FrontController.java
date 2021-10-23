package entities.ShoppingCart;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {MvcConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}