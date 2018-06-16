package com.study.spring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.factory.BeanDefinitionStoreException;
import com.study.spring.beans.factory.support.BeanDefinitionRegistry;
import com.study.spring.beans.factory.support.GenericBeanDefinition;
import com.study.spring.util.ClassUtils;

public class XmlBeanDefinitionReader {

	public static final String ID_ATTRIBUTE = "id";	

	public static final String CLASS_ATTRIBUTE = "class";
	
	public static final String SCOPE_ATTRIBUTE = "scope";
	
	BeanDefinitionRegistry registry;
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
		this.registry = registry;
	}
	
	public void loadBeanDefinitions(String configFile){
		InputStream is = null;
		try{			
			ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement(); //<beans>
			Iterator<Element> iter = root.elementIterator();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				//GenericBeanDefinition是xml中<bean>元素，生成bean对象
				BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
				this.registry.registerBeanDefinition(id, bd);
			}
		} catch (Exception e) {		
			throw new BeanDefinitionStoreException("IOException parsing XML document from " + configFile, e);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
		
	}
}