package com.study.spring.test.v1;

import static org.junit.Assert.assertNotNull;

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
	    //注意，这里仍然是硬编码本地路径，这是不好的实践! 
		ApplicationContext ctx = new FileSystemXmlApplicationContext("D:\\GitHub\\study\\studySpring\\srping\\src\\test\\resources\\petStore-v1.xml");
		PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
		Assert.assertNotNull(petStore);
		
	}

}
