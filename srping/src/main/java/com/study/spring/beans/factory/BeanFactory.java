package com.study.spring.beans.factory;

import com.study.spring.beans.BeanDefinition;

public interface BeanFactory {

	BeanDefinition getBeanDefinition(String beanID);

	Object getBean(String beanID);

}
