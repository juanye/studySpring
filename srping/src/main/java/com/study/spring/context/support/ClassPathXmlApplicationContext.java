package com.study.spring.context.support;

import com.study.spring.beans.core.io.ClassPathResource;
import com.study.spring.beans.core.io.Resource;
import com.study.spring.beans.factory.support.DefaultBeanFactory;
import com.study.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.study.spring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {
	
	private DefaultBeanFactory factory = null; 
	
	public ClassPathXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource(configFile);
		reader.loadBeanDefinitions(resource);
	}
	
	@Override
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

}
