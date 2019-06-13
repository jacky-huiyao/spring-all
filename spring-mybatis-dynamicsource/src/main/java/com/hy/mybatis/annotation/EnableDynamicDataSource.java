package com.hy.mybatis.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = DynamicDataSourceAspect.class)
public @interface EnableDynamicDataSource {
}
