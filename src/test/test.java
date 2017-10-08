package test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class test {
	public static void main(String[] args) {
		//BeanFactory context=new XmlBeanFactory(new FileSystemResource("Beans.xml"));
		//ApplicationContext context
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		triangle triangle = (triangle) context.getBean("triangle");
		//triangle cd=(triangle)factory.getBean("bean1");
		triangle.draw();
	}

}
