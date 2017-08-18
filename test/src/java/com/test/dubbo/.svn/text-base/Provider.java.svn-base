package com.test.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo服务提供者
 *
 */
public class Provider {

	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-provider.xml"});
        context.start();

        System.in.read();// 为保证服务一直开着，利用输入流的阻塞来模拟   按任意键退出
	}

}
