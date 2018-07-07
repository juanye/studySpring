package com.study.spring.context.support;

import com.study.spring.beans.core.io.Resource;
import com.study.spring.beans.factory.support.DefaultBeanFactory;
import com.study.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.study.spring.context.ApplicationContext;
import com.study.spring.util.ClassUtils;

/**
 * 因为FileSystemXmlApplicationContext和ClassPathXmlApplicationContext中有相似方法，仅部份内容不同，
 * 故而抽象出相同部份，再分别继承抽象类实现其中不同部份。（模板方法）
 * @author chenjuan
 *
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	
	private ClassLoader beanClassLoader;
	
	public AbstractApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);	
		Resource resource = this.getResourceByPath(configFile);//模板方法
		reader.loadBeanDefinitions(resource);
		factory.setBeanClassLoader(this.getBeanClassLoader());
	}
	
	@Override
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}
	
	protected abstract Resource getResourceByPath(String path);

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return (beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}
}
