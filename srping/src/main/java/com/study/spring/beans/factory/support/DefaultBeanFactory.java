package com.study.spring.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.factory.BeanCreationException;
import com.study.spring.beans.factory.BeanDefinitionStoreException;
import com.study.spring.beans.factory.BeanFactory;
import com.study.spring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory {
	public static final String ID_ATTRIBUTE = "id";	
	public static final String CLASS_ATTRIBUTE = "class";

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
	
	public DefaultBeanFactory(String configFile) {
		loadBeanDefinition(configFile);
	}
	
	private void loadBeanDefinition(String configFile) {
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
				this.beanDefinitionMap.put(id, bd);
			}
		} catch (DocumentException e) {		
			//抛出异常
//			e.printStackTrace();
			throw new BeanDefinitionStoreException("IOException parsing XML document from"
					+ configFile, e);
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

	@Override
	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}
	
	@Override
	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if(bd == null){
//			return null;
			throw new BeanCreationException("Bean Definition does not exist");
		}
		//lassLoader的具体作用就是将class文件加载到jvm虚拟机中去，程序就可以运行了。
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		/*} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}*/
		}catch (Exception e) {
			throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
		}
//		return null;
	}

}
