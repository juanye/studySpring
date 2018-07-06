package com.study.spring.context.support;

import com.study.spring.beans.core.io.FileSystemResource;
import com.study.spring.beans.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

	public FileSystemXmlApplicationContext(String configFile) {
		super(configFile);
	}

	@Override
	protected Resource getResourceByPath(String path) {
		return new FileSystemResource(path);
	}

}
