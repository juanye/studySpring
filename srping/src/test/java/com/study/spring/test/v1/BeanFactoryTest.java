package com.study.spring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.factory.BeanCreationException;
import com.study.spring.beans.factory.BeanDefinitionStoreException;
import com.study.spring.beans.factory.BeanFactory;
import com.study.spring.beans.factory.support.DefaultBeanFactory;
import com.study.spring.service.v1.PetStoreService;

public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		//读取xml配置文件
		BeanFactory beanFactory = new DefaultBeanFactory("petStore-v1.xml");
		//解析xml配置文件
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
		
		//根据className,判断获取到的类是否是xml中配置的类
		assertEquals("com.study.spring.service.v1.PetStoreService", 
				beanDefinition.getBeanClassName());
		
		PetStoreService petStoreService = (PetStoreService)beanFactory.getBean("petStore");
		
		assertNotNull(petStoreService);
	}
	
	@Test
	public void testInvalidBean() {
		BeanFactory beanFactory = new DefaultBeanFactory("petStore-v1.xml");
		try {
			beanFactory.getBean("invalidBean");
		}catch (BeanCreationException e) {
			return;
		}
		
		Assert.fail("expect BeanCreationException");
	}
	
	@Test
	public void testInvalidXml() {
		try {
			new DefaultBeanFactory("xxx.xml");
		}catch (BeanDefinitionStoreException e) {
			return;
		}
		
		Assert.fail("expect BeanDefinitionStoreException");
	}

}
