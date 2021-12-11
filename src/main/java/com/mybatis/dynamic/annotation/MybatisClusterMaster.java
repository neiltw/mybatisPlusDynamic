package com.mybatis.dynamic.annotation;

import java.lang.annotation.*;

/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:29 PM
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MybatisClusterMaster {
}
