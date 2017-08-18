package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

import org.apache.log4j.Logger;

public class DBConnectionPoolUtil {

	private static final Logger logger = Logger.getLogger(DBConnectionPoolUtil.class);
	
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://120.24.48.215:3306/xiexiaozhang";
	private static final String username = "anwsion";
	private static final String password = "anwsion";
	
	private static Vector<Connection> pool;
	private static int poolSize = 100;
	private static Connection conn = null;
	
	static {
		pool = new Vector<Connection>(poolSize);
		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
				pool.add(conn);
			} catch (Exception e) {
				logger.error("init connection pool error.", e);
			}
		}
	}
	
	// 返回连接到连接池
	public synchronized void release() {
		pool.add(conn);
	}
	
	// 从连接池中获取连接
	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}
}
