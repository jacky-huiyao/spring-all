package com.hy.mybatis.annotation;

import com.hy.mybatis.config.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * DataSource注解处理类
 *
 * 方法名会影响代理的先后顺序
 * {@link org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory}
 */
@Aspect
public class DynamicDataSourceAspect {

    /**
     * 处理标注在类上的注解
     * @param point
     * @param dataSource
     */
    @Before(value = "@within(dataSource)")
    public void switchDataSource1(JoinPoint point, DataSource dataSource){
        DataSourceContext.setSource(dataSource.value());
    }

    /**
     * 处理标注在类上的注解
     * @param point
     * @param dataSource
     */
    @After(value = "@within(dataSource)")
    public void restoreDataSource1(JoinPoint point, DataSource dataSource){
        DataSourceContext.setSource(null);
    }

    /**
     * 处理标注在方法上的注解
     * @param point
     * @param dataSource
     */
    @Before(value = "@annotation(dataSource)")
    public void switchDataSource2(JoinPoint point, DataSource dataSource){
        DataSourceContext.setSource(dataSource.value());
    }

    /**
     * 处理标注在方法上的注解
     * @param point
     * @param dataSource
     */
    @After(value = "@annotation(dataSource)")
    public void restoreDataSource2(JoinPoint point, DataSource dataSource){
        DataSourceContext.setSource(null);
    }

}
