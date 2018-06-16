package com.study.spring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
//套件
@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest.class, BeanFactoryTest.class })
public class V1AllTests {

}
