package com.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;

public class ImageUtil {

	
	public static final Integer width = 200;
	public static final Integer height = 60;
	
	public static BufferedImage creatImage(HttpServletRequest request) {
		BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = buffImage.createGraphics();
		g.setBackground(Color.BLUE);
		g.clearRect(0, 0, width, height);
		g.setColor(Color.RED);
		g.drawLine(0, 0, width, height);
		
		g.dispose();
		buffImage.flush();
		
		return buffImage;
	}
}
