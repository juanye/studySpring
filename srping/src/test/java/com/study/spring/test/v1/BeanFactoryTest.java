package com.study.spring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.factory.BeanCreationException;
import com.study.spring.beans.factory.BeanDefinitionStoreException;
import com.study.spring.beans.factory.support.DefaultBeanFactory;
import com.study.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.study.spring.service.v1.PetStoreService;

public class BeanFactoryTest {
	
	//目的是在每次运行@Before方法前清空
	DefaultBeanFactory beanFactory = null;
	
	XmlBeanDefinitionReader reader = null;
	
	//使用注解，重构测试案例，重复代码抽取，@Before注解的方法每次都在@Test方法前运行
	/**
	 * @Before
	 * @Test
	 * 
	 * @Before
	 * @Test
	 */
	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFactory();
		//读取xml配置文件
		reader = new XmlBeanDefinitionReader(beanFactory);
	}

	@Test
	public void testGetBean() {
		reader.loadBeanDefinitions("petStore-v1.xml");
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
		//根据className,判断获取到的类是否是xml中配置的类
		assertEquals("com.study.spring.service.v1.PetStoreService", 
				beanDefinition.getBeanClassName());
		
		PetStoreService petStoreService = (PetStoreService)beanFactory.getBean("petStore");
		
		assertNotNull(petStoreService);
	}
	
	@Test
	public void testInvalidBean() {
		reader.loadBeanDefinitions("petStore-v1.xml");
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
			reader.loadBeanDefinitions("xxx.xml");
		}catch (BeanDefinitionStoreException e) {
			return;
		}
		
		Assert.fail("expect BeanDefinitionStoreException");
	}

}
