package com.lzh.jmeter.commons.datasource.aspect;
import com.lzh.jmeter.commons.datasource.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(value = 1)
@Component
public class DataSourceAspect {

    @Pointcut("!@annotation(com.lzh.jmeter.commons.datasource.annotation.Master) " +
            "&& (execution(* com.lzh.jmeter.*.service..*.select*(..)) " +
            "|| execution(* com.lzh.jmeter.*.service..*.get*(..)))" +
            "|| execution(* com.lzh.jmeter.*.service..*.query*(..)))"
      )
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.lzh.jmeter.commons.datasource.annotation.Master) " +
            "|| execution(* com.lzh.jmeter.*.service..*.insert*(..)) " +
            "|| execution(* com.lzh.jmeter.*.service..*.add*(..)) " +
            "|| execution(* com.lzh.jmeter.*.service..*.update*(..)) " +
            "|| execution(* com.lzh.jmeter.*.service..*.edit*(..)) " +
            "|| execution(* com.lzh.jmeter.*.service..*.delete*(..)) " +
            "|| execution(* com.lzh.jmeter.*.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DataSourceContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DataSourceContextHolder.master();
    }
}
