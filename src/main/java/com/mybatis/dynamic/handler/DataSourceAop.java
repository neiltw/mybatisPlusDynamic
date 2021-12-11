package com.mybatis.dynamic.handler;

import com.mybatis.dynamic.config.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author neil.lin
 * @version 1.0
 * @since 2021-12-11 - 12:22 PM
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {
    /**
     * select using slave
     */
    @Pointcut("" +
            "!@annotation(com.mybatis.dynamic.annotation.MybatisClusterMaster) " +
            "&& " +
            " (execution(* com.myProject.*.service..*.select*(..)) " +
            "|| execution(* com.myProject.*.service..*.query*(..)) " +
            "|| execution(* com.myProject.*.service..*.find*(..)) " +
            "|| execution(* com.myProject.*.service..*.get*(..)) " +
            ")")
    public void readPointcut() {
    }

    /**
     * other using master
     */
    @Pointcut("@annotation(com.mybatis.dynamic.annotation.MybatisClusterMaster) " +
            "|| execution(* com.myProject.*.service..*.insert*(..)) " +
            "|| execution(* com.myProject.*.service..*.add*(..)) " +
            "|| execution(* com.myProject.*.service..*.save*(..)) " +
            "|| execution(* com.myProject.*.service..*.update*(..)) " +
            "|| execution(* com.myProject.*.service..*.edit*(..)) " +
            "|| execution(* com.myProject.*.service..*.modify*(..)) " +
            "|| execution(* com.myProject.*.service..*.delete*(..)) " +
            "|| execution(* com.myProject.*.service..*.remove*(..))"
    )
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
