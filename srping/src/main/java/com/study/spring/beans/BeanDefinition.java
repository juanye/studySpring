package com.study.spring.beans;

public interface BeanDefinition {

	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	public static final String SCOPE_DEFAULT = "";
	
	public String getBeanClassName();
	
	public boolean isSingleton();
	
	public boolean isPrototype();
	
	String getScope();
	
	void setScope(String scope);//xml解析时使用

}
