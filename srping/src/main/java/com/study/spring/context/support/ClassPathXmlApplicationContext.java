package com.study.spring.context.support;

import com.study.spring.beans.core.io.ClassPathResource;
import com.study.spring.beans.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	public ClassPathXmlApplicationContext(String configFile) {
		super(configFile);
	}

	@Override
	protected Resource getResourceByPath(String path) {
		return new ClassPathResource(path, this.getBeanClassLoader());
	}

}
