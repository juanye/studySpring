package com.study.spring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.factory.BeanCreationException;
import com.study.spring.beans.factory.config.ConfigurableBeanFactory;
import com.study.spring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
	implements ConfigurableBeanFactory,BeanDefinitionRegistry {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
	
	private ClassLoader beanClassLoader;
	
	@Override
	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}
	
	@Override
	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if(bd == null){
			throw new BeanCreationException("Bean Definition does not exist");
		}
		
		if (bd.isSingleton()) {
			Object bean = this.getSingleton(beanID);
			if (bean == null) {
				bean = createBean(bd);
				this.registerSingleton(beanID, bean);
			}
			return bean;
		}
		return createBean(bd);
	}
	
	private Object createBean(BeanDefinition bd) {
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {			
			throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
		}	
	}

	@Override
	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return (beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}

}
