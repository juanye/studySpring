package com.study.spring.beans.factory;

import com.study.spring.beans.BeansException;

public class BeanCreationException extends BeansException {

	public BeanCreationException(String msg) {
		super(msg);	
	}
	
	public BeanCreationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
