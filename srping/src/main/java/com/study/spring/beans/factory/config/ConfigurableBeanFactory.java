package com.study.spring.beans.factory.config;

import com.study.spring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {
	void setBeanClassLoader(ClassLoader beanClassLoader);
	ClassLoader getBeanClassLoader();
}
