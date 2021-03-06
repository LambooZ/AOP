package aoptest;

import dev.edu.javaee.spring.aop.Advisor;
import dev.edu.javaee.spring.aop.framework.AdvisedSupport;
import dev.edu.javaee.spring.aop.framework.JdkDynamicAopProxy;

public class ProxyFactoryBean {

	private AdvisedSupport advicesupport = new AdvisedSupport();
	private String proxyInterfaces;
	private Object target;
	private Advisor advisor;

	public String getProxyInterfaces() {
		return proxyInterfaces;
	}

	public void setProxyInterfaces(String proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Advisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}

	public Object getProxy() {
		advicesupport.addAdvisor(advisor);
		Class<?>[] interfaces = target.getClass().getInterfaces();
		int i = 0;
		for (Class<?> Interface : interfaces) {
			String str[] = Interface.getName().split("\\.");
			if (str[str.length - 1].equals(proxyInterfaces)) {
				advicesupport.setInterfaces(Interface);
				break;
			}
			i++;
		}
		advicesupport.setInterfaces(target.getClass().getInterfaces()[i]);
		advicesupport.setTarget(target);
		return new JdkDynamicAopProxy(advicesupport).getProxy();
	}
}
