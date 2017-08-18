package com.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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


public class FileUploadServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(FileUploadServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		
		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			fileUpload.setHeaderEncoding("UTF-8");
			if (!fileUpload.isMultipartContent(request)) {
				return;
			}
			List<FileItem> fileList = fileUpload.parseRequest(request);
			for (FileItem item : fileList) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString(name);
					value = new String(name.getBytes("iso8859-1"),"UTF-8");
				} else {
					String fileName = item.getName();
					if (StringUtils.isBlank(fileName)) {
						continue;
					}
					fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
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
				}
				
			}
			
		} catch (FileUploadException e) {
			logger.error("文件上传失败", e);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
