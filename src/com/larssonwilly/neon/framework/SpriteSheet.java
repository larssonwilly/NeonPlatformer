package com.larssonwilly.neon.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image)	{
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int w, int h)	{
		BufferedImage img = image.getSubimage((col * w) - w, (row * h) - h, w, h);
		return img;
	}
	
	
	
}
