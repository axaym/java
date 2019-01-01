package com.assignment.library;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MainWebAppInitializer implements WebApplicationInitializer {
	@Override
	 public void onStartup(ServletContext container) {
	  AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	  rootContext.register(AppConfig.class);
	  ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
	  container.addListener(contextLoaderListener);
	  container.setInitParameter("contextInitializerClasses", "com.assignment.library.MainWebAppInitializer");
	  AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
	  webContext.register(MvcConfiguration.class);
	  DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
	  ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", dispatcherServlet);
	  dispatcher.addMapping("/");
	 }
}
