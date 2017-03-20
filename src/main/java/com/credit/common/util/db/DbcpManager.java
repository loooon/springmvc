/**
 * Purpose: DBCP管理类
 *
 * @author J.CHEN
 * @version 1.0 2003-12-12
 *
 * Copyright (C) 2003, 2006, HC SOFT.
 *
 */

package com.credit.common.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库连接池
 * 
 * @version 0.9
 * @since 0.9
 */

public class DbcpManager
{
    private static Logger logger = LoggerFactory.getLogger(DbcpManager.class);

    private static Map configured = new HashMap();

    private static final String POOLDRV_NAME = "jdbc:apache:commons:dbcp:";

    public static final GenericObjectPool init(String poolname, String jdbcDriver, String dbUrl, String dbUser, String dbPwd, String dbCharSet, int maxActive)  throws Exception
    {
        synchronized (DbcpManager.class)
        {
            return startDbcp(poolname, jdbcDriver, dbUrl, dbUser, dbPwd, dbCharSet, maxActive, null);
        }
    }

    public static final GenericObjectPool init(String poolname, String jdbcDriver, String dbUrl, String dbUser, String dbPwd, String dbCharSet, int maxActive,
											   String validationQuery) throws Exception
    {
        synchronized (DbcpManager.class)
        {
            return startDbcp(poolname, jdbcDriver, dbUrl, dbUser, dbPwd, dbCharSet, maxActive, validationQuery);
        }
    }

    private static GenericObjectPool startDbcp(String poolname, String jdbcDriver, String dbUrl, String dbUser, String dbPwd, String dbCharSet, int maxActive,
											   String validationQuery) throws Exception
    {
        // 如果池已经存在，则返回
        if (null != configured.get(poolname) && ((Boolean) configured.get(poolname)).booleanValue())
        {
            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver(POOLDRV_NAME);
            return (GenericObjectPool) driver.getPool(poolname);
        }

        Class.forName(jdbcDriver);

        // construct Pool
        GenericObjectPool connectionPool = new GenericObjectPool(null);
        connectionPool.setTimeBetweenEvictionRunsMillis(300000);
        connectionPool.setMaxWait(10000);
        connectionPool.setMinEvictableIdleTimeMillis(300000);
        connectionPool.setTestOnBorrow(true);
        connectionPool.setMaxActive(maxActive);

        DriverManagerConnectionFactory connectionFactory;
        if (dbCharSet == null)
        {
            connectionFactory = new DriverManagerConnectionFactory(dbUrl, dbUser, dbPwd);
        }
        else
        {
            Properties p = new Properties();
            p.setProperty("user", dbUser);
            p.setProperty("password", dbPwd);
            p.setProperty("charset", dbCharSet);
            connectionFactory = new DriverManagerConnectionFactory(dbUrl, p);
        }

        new PoolableConnectionFactory(connectionFactory, connectionPool, null, validationQuery, false, true);
        Class.forName("org.apache.commons.dbcp.PoolingDriver");
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver(POOLDRV_NAME);
        driver.registerPool(poolname, connectionPool);
        if (configured.get(poolname) != null)
        {
            configured.remove(poolname);
        }
        configured.put(poolname, Boolean.TRUE);
        return connectionPool;
    }

    /**
     * 根据数据池和数据库名取连接，注意，这里的的数据库别名必须是Global中的变量，如Global.DB_NAME_CA
     * 
     * @param db_name
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String poolname) throws Exception
    {
        if (configured.get(poolname) == null)
        {
            String err = "数据池" + poolname + "未初始化。请先调用初始函数。";
            logger.error(err);
            throw new Exception(err);
        }

        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver(POOLDRV_NAME);
        GenericObjectPool connectionPool = (GenericObjectPool) driver.getPool(poolname);
        Connection conn = null;
        try
        {
            conn = (Connection) connectionPool.borrowObject();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex.getMessage());
        }
        return conn;
    }

    // 清空池
    public static void clearConnection(String poolname)
    {
        if (configured.get(poolname) == null)
        {
            return;
        }

        PoolingDriver driver;
        try
        {
            driver = (PoolingDriver) DriverManager.getDriver(POOLDRV_NAME);
        }
        catch (Exception e)
        {
            // ignore
            return;
        }
        GenericObjectPool connectionPool = (GenericObjectPool) driver.getPool(poolname);
        connectionPool.clear();
    }
}
