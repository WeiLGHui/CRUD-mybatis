package com.xatu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Auther WeiLGHui
 * @Date 2020-03-19 11:16
 */

/**
 * 连接的工具类，它用于从数据源中获取一个链接，并且实现和线程的绑定
 * 为了转账安全  防止发生一个已转，一个未接收到
 */
@Component("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnection(){
        //先从ThreadLocal上获取
        Connection conn = tl.get();
        //判断当前线程上是否有连接
        try {
            if(conn == null) {
                //3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}

