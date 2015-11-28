package com.ludum.src.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}
	
	public BufferedImage grabImage_ext(int col, int row, int spriteWidth, int spriteHeight, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * spriteWidth) - spriteWidth, (row * spriteHeight) - spriteHeight, width, height);
		return img;
	}
	
}
