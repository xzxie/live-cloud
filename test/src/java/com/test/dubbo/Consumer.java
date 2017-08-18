package com.test.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo服务消费者
 *
 */
public class Consumer {

	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
		context.start();
		
		DemoService demoService = (DemoService) context.getBean("demoService");// 获取远程服务代理
		String sayHello = demoService.sayHello("world");// 执行远程方法
		
		System.out.println(sayHello);// 显示结果
	}

}
