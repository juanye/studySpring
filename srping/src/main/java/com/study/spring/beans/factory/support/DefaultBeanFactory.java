package com.study.spring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.factory.BeanCreationException;
import com.study.spring.beans.factory.BeanFactory;
import com.study.spring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory,BeanDefinitionRegistry {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
	
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
		//classLoader的具体作用就是将class文件加载到jvm虚拟机中去，程序就可以运行了。
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		}catch (Exception e) {
			throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
		}
	}

	@Override
	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}

}
