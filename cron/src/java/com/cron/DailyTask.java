package com.cron;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.DBUtil;
import com.util.DateUtil;
import com.util.SeoUtil;



/**
 * 每日任务
 */
public class DailyTask {
	
	private static Logger logger = LoggerFactory.getLogger(DailyTask.class);
	
	public void execute() {
		logger.error(DailyTask.class.getCanonicalName() + ".execute()开始执行...");
		long current = System.currentTimeMillis();
		
		String yesterday = DateUtil.getYesterday(DateUtil.yyyyMMdd);
		String weekAgo = DateUtil.getWeekAgo(DateUtil.yyyyMMdd);
		String monthAgo = DateUtil.getMonthAgo(DateUtil.yyyyMMdd);
		String begin_day = yesterday + " 00:00:00";
		String begin_week = weekAgo + " 00:00:00";
		String begin_month = monthAgo + " 00:00:00";
		String end = yesterday + " 23:59:59";
		
		DailyTask.reg(begin_day, end);
		DailyTask.reg(begin_month, end);
		DailyTask.reg(begin_week, end);
		
		DailyTask.login(begin_day, end);
		DailyTask.login(begin_week, end);
		DailyTask.login(begin_month, end);
		
		DailyTask.deleteAITempUploadFile();
		
		current = System.currentTimeMillis() - current;
		logger.error(DailyTask.class.getCanonicalName() + ".execute()执行完毕..." + " 耗时：" + current);
	}
	
	// 昨日注册数
	public static void reg(String begin, String end) {
		String sql_query = "select count(1) num from t_user info where info.create_time > ? and info.create_time < ?";
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sql_query, new Object[]{begin, end});
		int num = Integer.valueOf(resultList.get(0).get("num")+"");
	}
	
	// 昨日登录数
	public static void login(String begin, String end) {
		String sql_query = "select count(distinct(info.user_id)) num from t_user_login_log info where info.create_time > ? and info.create_time < ?";
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sql_query, new Object[]{begin, end});
		int num = Integer.valueOf(resultList.get(0).get("num")+"");
	}
	
	// 删除昨天AI上传的文件
	public static void deleteAITempUploadFile() {
		String today = DateUtil.getSysCurrentYearMonthDateStr();
		String savePath = SeoUtil.domain + "/upload";
		File file = new File(savePath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				String filename = f.getName();
				if (filename.startsWith(today)) {
					f.delete();
				}
			}
		}
	}
}
