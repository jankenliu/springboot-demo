package com.jankin.springboot.demo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 资源文件读取工具
 *
 * @author lyy
 * @date 2018-11-13
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context = null;

	private static BeanDefinitionRegistry beanDefinitonRegistry = null;


	private SpringContextUtil() {
		super();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
		beanDefinitonRegistry = (BeanDefinitionRegistry) configurableApplicationContext
				.getBeanFactory();
	}

	/**
	 * 根据名称获取bean
	 *
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * 根据bean名称获取指定类型bean
	 *
	 * @param beanName bean名称
	 * @param clazz    返回的bean类型,若类型不匹配,将抛出异常
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return context.getBean(beanName, clazz);
	}

	/**
	 * 根据类型获取bean
	 *
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		T t = null;
		Map<String, T> map = context.getBeansOfType(clazz);
		for (Map.Entry<String, T> entry : map.entrySet()) {
			t = entry.getValue();
		}
		return t;
	}

	/**
	 * 是否包含bean
	 *
	 * @param beanName
	 * @return
	 */
	public static boolean containsBean(String beanName) {
		return context.containsBean(beanName);
	}

	/**
	 * 是否是单例
	 *
	 * @param beanName
	 * @return
	 */
	public static boolean isSingleton(String beanName) {
		return context.isSingleton(beanName);
	}

	/**
	 * bean的类型
	 *
	 * @param beanName
	 * @return
	 */
	public static Class getType(String beanName) {
		return context.getType(beanName);
	}


	/**
	 * 动态注册bean
	 *
	 * @param beanName
	 * @param beanDefinition
	 */
	public synchronized static void registerBean(@NotNull String beanName, BeanDefinition beanDefinition) {
		//DefaultListableBeanFactory beanDefinitonRegistry = (DefaultListableBeanFactory) app.getAutowireCapableBeanFactory();
		if (!beanDefinitonRegistry.containsBeanDefinition(beanName)) {
			beanDefinitonRegistry.registerBeanDefinition(beanName, beanDefinition);
		}
	}

	public static void registerBean(BeanDefinition beanDefinition) {
		//DefaultListableBeanFactory beanDefinitonRegistry = (DefaultListableBeanFactory) app.getAutowireCapableBeanFactory();
		String simpleNameString = beanDefinition.getBeanClassName();
		if (simpleNameString != null && simpleNameString.contains(".")) {
			simpleNameString = simpleNameString.substring(simpleNameString.lastIndexOf(".") + 1);
		}
		simpleNameString = StringUtil.toLowerCaseFirstOne(simpleNameString);
		registerBean(simpleNameString, beanDefinition);
	}

	public static BeanDefinitionBuilder getBeanDefinitionBuilder(Class clazz) {
		return BeanDefinitionBuilder.genericBeanDefinition(clazz);

	}
}
