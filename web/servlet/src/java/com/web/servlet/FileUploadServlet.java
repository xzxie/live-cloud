package com.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.util.DateUtil;
import com.util.SeoUtil;
import com.util.WebUtil;


public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FileUploadServlet.class);
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "";
		String savePath = request.getSession().getServletContext().getRealPath("/")+"/upload";
		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		
		try {
			// 创建磁盘文件工厂
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			// 创建servlet文件上传组件
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			fileUpload.setHeaderEncoding("UTF-8");
			
			@SuppressWarnings("unchecked")
			List<FileItem> fileList = fileUpload.parseRequest(request);
			for (FileItem item : fileList) {
				// 如果不是普通表单域，当做文件域来处理
				if (!item.isFormField()) {
					String fileName = item.getName();
					if (StringUtils.isBlank(fileName)) {
						continue;
					}
					String now = DateUtil.getSysCurrentYearMonthDateStr(DateUtil.yyyyMMddHHmmss_series);
					String uuid = UUID.randomUUID().toString().replace("-", "");
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					fileName = now + uuid + fileType;
					url = (SeoUtil.domain + "/upload/" + fileName).trim();
					
					InputStream is = item.getInputStream();
					FileOutputStream fos = new FileOutputStream(savePath + File.separator + fileName);
					
					byte[] buffer = new byte[1024];
					int length = 0;
					while ((length = is.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}
					
					is.close();
					fos.close();
					item.delete();
					
					WebUtil.write(response, url.trim());
				}
			}
		} catch (FileUploadException e) {
			logger.error("文件上传失败", e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
