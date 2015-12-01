package com.larssonwilly.neon.framework;

import java.awt.image.BufferedImage;

import com.larssonwilly.neon.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[14];
	public BufferedImage[] player_jump = new BufferedImage[6];
	
	public Texture()	{
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try	{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
		}catch(Exception e)	{
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures()	{
		block[0] = bs.grabImage(1, 1, 32, 32); //dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); //grass
		
		for(int i = 0; i < 7; i++)	{ // right
			player[i] = ps.grabImage(i+1, 1, 32, 64);
		}
		for(int i = 7; i < 14; i++)	{ // left
			player[i] = ps.grabImage(27-i, 1, 32, 64);
		}
		for(int i = 0; i < 6; i++)	{ // left
			player_jump[i] = ps.grabImage(8+i, 2, 32, 64);
		}
		
	}
	
}










