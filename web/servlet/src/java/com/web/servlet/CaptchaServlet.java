package com.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.util.ImageUtil;

// 自定义生成验证码
public class CaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(CaptchaServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("cache-control","no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("expires","0");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		String ref = request.getParameter("ref");
		
		BufferedImage bufImg = ImageUtil.creatImage(request);
		ImageIO.write(bufImg, "JPEG", response.getOutputStream());
	}
}
