package com.study.spring.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.study.spring.context.ApplicationContext;
import com.study.spring.context.support.ClassPathXmlApplicationContext;
import com.study.spring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petStore-v1.xml");
		
		PetStoreService petStoreService = (PetStoreService)applicationContext.getBean("petStore");
		assertNotNull(petStoreService);
	}

}
