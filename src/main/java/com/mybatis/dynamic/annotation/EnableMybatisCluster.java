package com.mybatis.dynamic.annotation;

import com.mybatis.dynamic.config.MultiDataSourceConfig;
import com.mybatis.dynamic.config.MybatisPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:31 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import({  MultiDataSourceConfig.class, MybatisPlusConfig.class})
public @interface EnableMybatisCluster {
}
