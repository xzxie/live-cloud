package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class DBUtil {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://120.24.48.215:3306/xiexiaozhang";
	private static final String username = "anwsion";
	private static final String password = "anwsion";
	
	private static final Logger logger = Logger.getLogger(DBUtil.class);
	private static final Log slow = LogFactory.getLog("slowLog");
	private static final Log error = LogFactory.getLog("errorLog");
	
	// 使用ThreadLocal保存Connection变量
	public static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();
	
	/*public static Connection getConnection() {
		// 如果connThreadLocal没有本线程对应的Connection创建一个新的Connection,并将其保存到线程本地变量中
		if (connThreadLocal.get() == null) {
			Connection conn = getConn();
			connThreadLocal.set(conn);
			return conn;
		} else {
			return connThreadLocal.get();
		}
	}*/
	
	// 获取连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			logger.warn(log() + "连接失败......");
		}
		return conn;
	}
	
	// 开启事务(jdbc事务)
	public static void beginTransaction(Connection conn) {
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
		} catch(SQLException e) {
			logger.error("开启事务失败.");
		}
		
	}
	
	// 提交事务(jdbc事务)
	public static void commitTransaction(Connection conn) {
		try {
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
		} catch(SQLException e) {
			logger.error("提交事务失败.");
		}
	}
	
	public static List<Map<String, Object>> executeQuery(String sql) {
		return executeQuery(sql, null);
	}
	
	// 查询
	public static List<Map<String, Object>> executeQuery(String sql, Object[] param) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet res = null;
		try {
			long start = new Date(System.currentTimeMillis()).getTime();
			conn = getConnection();
			long conntime = new Date(System.currentTimeMillis()).getTime()-start;
			pstmt = null;
			res = null;
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			pstmt = conn.prepareStatement(sql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if (param[i] == null) {
						break;
					}
					pstmt.setObject(i + 1, param[i]);
				}
			}
			res = pstmt.executeQuery();
			long end = new Date(System.currentTimeMillis()).getTime();
			if((end-start)>5000){
				slow.warn("time is "+(end-start)+" ,get conn time is:"+conntime+" long time sql :" + sql);
			}
			if (res != null) {
				int columnCount = res.getMetaData().getColumnCount();
				while (res.next()) {
					Map<String, Object> resultRow = new HashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						resultRow.put(res.getMetaData().getColumnLabel(i), res.getObject(i));
					}
					result.add(resultRow);
					resultRow = null;
				}
			}
			return result;
		} catch (Exception ex) {
			String debug = "##DEBUG:";
			String params = "param : ";
			if(params != null){
				for(int i = 0 ; i < param.length ; i++){
					params += param[i] + ",";
				}
			}
			error.error(debug + "error sql:" + sql + "db.executeQuery error , " + param, ex);
			return null;
		} finally {
			closeConnection(conn, pstmt, res);
		}
	}
	
	public static int executeUpdate(String sql) {
		Connection conn = getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			return result;
		} catch (SQLException e) {
			logger.warn(log() + "更新失败......");
		} finally {
			closeConnection(conn, stmt, null);
		}
		return -1;
	}
	
	// 更新
	public static int executeUpdate(String sql, Object[] parm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			if(sql == null){
				return 0;
			}
			boolean ifInsert = false;
			if (sql.indexOf("insert") > -1 || sql.indexOf("INSERT") > -1)
				ifInsert = true;
			if (ifInsert == true) {
				pstmt = conn.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
			} else {
				pstmt = conn.prepareStatement(sql);
			}
			if (parm != null && parm.length > 0) {
				for (int i = 0; i < parm.length; i++) {
					pstmt.setObject(i + 1, parm[i]);
				}
			}
			if (ifInsert) {
				int result = pstmt.executeUpdate();
				ResultSet keys = pstmt.getGeneratedKeys();
				int id = 0;
				if (keys.next()) {
					Long t = keys.getLong(1);
					id = t.intValue();
				}
				if(id != 0){
					result = id;
				}
				return result;
			} else {
				return pstmt.executeUpdate();
			}
		} catch(Exception ex) {
			String em = ex.getMessage();
			if (em != null && em.indexOf("Duplicate entry") >= 0) {
				String param = "param : ";
				if(parm != null){
					for(int i = 0 ; i < parm.length ; i++){
						param += parm[i] + ",";
					}
				}
				error.error("Duplicate entry ,error sql:" + sql +" param :"+param);
			} else {
				String param = "param : ";
				if(parm != null){
					for(int i = 0 ; i < parm.length ; i++){
						param += parm[i] + ",";
					}
				}
				error.error("db.executeUpdate error \r\n" + sql +"\r\ntable name \r\n " + param + "\r\n", ex);
			}
			return -1;
		} finally {
			closeConnection(conn, pstmt, null);
		}
	}
	
	public static void closeConnection(Connection conn, Statement stm, ResultSet rs) {
		try {
			if (conn != null) conn.close();
			if (stm != null) stm.close();
			if (rs != null) rs.close();
		} catch(Exception e) {
			logger.warn(log() + "关闭失败......");
		}
    }
	
	private static String log() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		String claszz = stackTrace[2].getClassName();
		String method = stackTrace[2].getMethodName();
		String log = claszz + "." + method + "() ";
		return log;
	}
	
	
	
	public static void main(String[] args) {
		// 连接测试
		String clazz = new DBUtil().getConnection().getClass().getName();
		System.out.println(clazz);
		
		// 查询测试
		String sql = "select * from t_dictionary where id=?";
		List<Map<String, Object>> list = DBUtil.executeQuery(sql, new Object[]{1});
		System.out.println(list);
		
		// 更新测试
		sql = "update t_dictionary set value=? where id=?";
		int result = DBUtil.executeUpdate(sql, new Object[]{"value", 1});
		System.out.println(result);
	}
}
