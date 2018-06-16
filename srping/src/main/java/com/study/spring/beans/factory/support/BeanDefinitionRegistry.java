package com.study.spring.beans.factory.support;

import com.study.spring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	/**
	 * 根据beanID，获取bean
	 * @param beanID
	 * @return
	 */
	BeanDefinition getBeanDefinition(String beanID);
	/**
	 * 注册bean
	 * @param beanID
	 * @param bd
	 */
	void registerBeanDefinition(String beanID, BeanDefinition bd);
}
