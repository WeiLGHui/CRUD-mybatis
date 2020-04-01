package com.xatu.utils;

/**
 * @Auther WeiLGHui
 * @Date 2020-03-19 11:25
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务相关的工具类，它包含了开启事务  提交事务   回滚事务  和释放连接
 */
@Component("txManager")
@Aspect   //标志当前类为切入类
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.xatu.service.impl.*.*(..))")
    private void pt1(){};
    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放连接
     */

    public void release(){
        try {
            connectionUtils.getThreadConnection().close();   //，关闭连接，返回连接池中
            connectionUtils.removeConnection();   //把连接与线程解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Around("pt1()")
    public Object aroundConnection(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            Object[] args = pjp.getArgs();
            beginTransaction();
            rtValue = pjp.proceed(args);
            commit();
            return rtValue;
        } catch (Throwable throwable) {
            rollback();
            throw new RuntimeException(throwable);
        }finally {
            release();
        }
    }
}
