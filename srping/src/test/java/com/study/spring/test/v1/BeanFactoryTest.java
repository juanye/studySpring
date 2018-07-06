package com.study.spring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.core.io.ClassPathResource;
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
//		reader.loadBeanDefinitions("petStore-v1.xml");
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
		
		//判断是否为scope单例模式
		assertTrue(beanDefinition.isSingleton());
		
		assertFalse(beanDefinition.isPrototype());
		
		assertEquals(BeanDefinition.SCOPE_DEFAULT,beanDefinition.getScope());
		
		
		//根据className,判断获取到的类是否是xml中配置的类
		assertEquals("com.study.spring.service.v1.PetStoreService", 
				beanDefinition.getBeanClassName());
		
		PetStoreService petStoreService = (PetStoreService)beanFactory.getBean("petStore");
		
		assertNotNull(petStoreService);
		
		//校验是否同一对象
		PetStoreService petStore1 = (PetStoreService)beanFactory.getBean("petStore");
		
		assertTrue(petStoreService.equals(petStore1));
	}
	
	@Test
	public void testInvalidBean() {
//		reader.loadBeanDefinitions("petStore-v1.xml");
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
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
//			reader.loadBeanDefinitions("xxx.xml");
			reader.loadBeanDefinitions(new ClassPathResource("xxx.xml"));
		}catch (BeanDefinitionStoreException e) {
			return;
		}
		
		Assert.fail("expect BeanDefinitionStoreException");
	}

}
