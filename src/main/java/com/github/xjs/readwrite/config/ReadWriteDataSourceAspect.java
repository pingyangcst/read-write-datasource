package com.github.xjs.readwrite.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadWriteDataSourceAspect {

    @Before("readPointcut()")
    public void read() {
        ReadWriteDataSourceHolder.switchToSlave();
    }

    @Before("writePointcut()")
    public void write() {
        ReadWriteDataSourceHolder.switchToMaster();
    }

    @Pointcut("!@annotation(com.github.xjs.readwrite.config.Master) " +
            "&& (" +
            "   execution(* com.github.xjs.readwrite.service..*.select*(..)) " +
            "|| execution(* com.github.xjs.readwrite.service..*.get*(..))" +
            "|| execution(* com.github.xjs.readwrite.service..*.list*(..))" +
            ")")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.github.xjs.readwrite.config.Master) " +
            "|| execution(* com.github.xjs.readwrite.service..*.insert*(..)) " +
            "|| execution(* com.github.xjs.readwrite.service..*.add*(..)) " +
            "|| execution(* com.github.xjs.readwrite.service..*.update*(..)) " +
            "|| execution(* com.github.xjs.readwrite.service..*.edit*(..)) " +
            "|| execution(* com.github.xjs.readwrite.service..*.delete*(..)) " +
            "|| execution(* com.github.xjs.readwrite.service..*.remove*(..))")
    public void writePointcut() {

    }

}
