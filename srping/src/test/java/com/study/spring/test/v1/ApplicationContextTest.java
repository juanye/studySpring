package com.study.spring.test.v1;

import org.junit.Assert;
import org.junit.Test;

import com.study.spring.context.ApplicationContext;
import com.study.spring.context.support.ClassPathXmlApplicationContext;
import com.study.spring.context.support.FileSystemXmlApplicationContext;
import com.study.spring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petStore-v1.xml");
		
		PetStoreService petStoreService = (PetStoreService)applicationContext.getBean("petStore");
		Assert.assertNotNull(petStoreService);
	}
	
	 @Test 
	public void testGetBeanFromFileSystemContext(){
	    //修改为相对路径
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src\\test\\resources\\petStore-v1.xml");
		PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
		Assert.assertNotNull(petStore);
		
	}

}
