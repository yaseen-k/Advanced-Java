package com.patternfly.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyFrontController extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {MvcConfig.class};
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
