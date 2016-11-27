package dev.edu.javaee.spring.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dev.edu.javaee.spring.bean.BeanDefinition;
import aoptest.ProxyFactoryBean;

public abstract class AbstractBeanFactory implements BeanFactory{
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	public Object getBean(String beanName)
	{
		if (this.beanDefinitionMap.get(beanName).getBean() instanceof ProxyFactoryBean) {
			return ((ProxyFactoryBean) this.beanDefinitionMap.get(beanName)
					.getBean()).getProxy();
		} else {
			return this.beanDefinitionMap.get(beanName).getBean();
		}
	}
	
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
	{
		beanDefinition = GetCreatedBean(beanDefinition);
		this.beanDefinitionMap.put(beanName, beanDefinition);
	}
	
	protected abstract BeanDefinition GetCreatedBean(BeanDefinition beanDefinition);
}
