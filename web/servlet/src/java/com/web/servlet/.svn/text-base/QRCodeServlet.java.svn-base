package com.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.util.SeoUtil;


/**
 * ZXing方式
 */
public class QRCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String redirect_main = SeoUtil.domain;
	public static final String redirect_app_iphone = SeoUtil.domain;
	public static final String redirect_app_ipad = SeoUtil.domain;
	public static final String redirect_app_android = SeoUtil.domain;
	
	public static final int qrcode_width = 200;
	public static final int qrcode_height = 200;
	public static final int qrcode_black = 0xFF000000;
	public static final int qrcode_white = 0xFFFFFFFF;
	public static final String qrcode_format = "png";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String redirectUrl = request.getParameter("ref");
		if (StringUtils.isBlank(redirectUrl)) {
			redirectUrl = redirect_main;
		} else if ("iphone".equals(redirectUrl)) {
			redirectUrl = redirect_app_iphone;
		} else if ("ipad".equals(redirectUrl)) {
			redirectUrl = redirect_app_ipad;
		} else if ("android".equals(redirectUrl)) {
			redirectUrl = redirect_app_android;
		} else {
			redirectUrl = "";
		}
		
		geneQRCode(redirectUrl, response);
	}
	
	// 创建二维码
	private void geneQRCode(String redirectUrl, HttpServletResponse response) {
		BitMatrix matrix = createBitMatrix(redirectUrl);
		BufferedImage buffImage = createBufferedImage(matrix);
		writeImage(response, buffImage);
	}
	
	// 1.创建比特矩阵
	public static BitMatrix createBitMatrix(String redirectUrl) {
		BitMatrix byteMatrix = null;
		try {
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hints = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);//设置二维码空白边框的大小 1-4，1是最小 4是默认的国标
			
			byteMatrix = new MultiFormatWriter().encode(redirectUrl, BarcodeFormat.QR_CODE, qrcode_width, qrcode_height, hints);
		}catch(Exception e){
			e.printStackTrace();
		}
		return byteMatrix;
	}
	
	// 2.比特矩阵生成内存图片
	public static BufferedImage createBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x=0; x<width; x++) {
			for (int y=0; y<width; y++) {
				buffImage.setRGB(x, y, matrix.get(x, y)?qrcode_black:qrcode_white);
			}
		}
		return buffImage;
	}
	
	// 3.把内存图片写到页面
	public static void writeImage(HttpServletResponse response, BufferedImage buffImage) {
		try {
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(buffImage, qrcode_format, sos);
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
