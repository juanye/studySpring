package com.study.spring.test.v1;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.study.spring.beans.core.io.ClassPathResource;
import com.study.spring.beans.core.io.FileSystemResource;
import com.study.spring.beans.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws IOException {
		InputStream inputStream = null;
		Resource resource = new ClassPathResource("petStore-v1.xml");
		try {
			inputStream = resource.getInputStream();
			Assert.assertNotNull(inputStream);
		}finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	@Test
	public void testFileSystemResource() throws IOException {
		InputStream inputStream = null;
		//修改为相对路径
		Resource resource = new FileSystemResource("src\\test\\resources\\petStore-v1.xml");
		
		try {
			inputStream = resource.getInputStream();
			Assert.assertNotNull(inputStream);
		}finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

}
