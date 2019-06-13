package com.hy.mybatis.annotation;

import com.hy.mybatis.config.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DynamicDataSourceAspect {

    @Before(value = "@annotation(dataSource)")
    public void switchDataSource(JoinPoint point, DataSource dataSource){
        DataSourceContext.setSource(dataSource.value());
    }

    @After(value = "@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, DataSource dataSource){
        DataSourceContext.setSource(null);
    }
}
