package com.ludum.src.interfaces;

import java.awt.image.BufferedImage;

import com.ludum.src.gfx.SpriteSheet;
import com.ludum.src.window.Game;

public class Texture 
{
	
	private SpriteSheet ps, bs;
	
	public BufferedImage[] player = new BufferedImage[21];
	public BufferedImage[] block = new BufferedImage[11];
	public BufferedImage[] coin = new BufferedImage[9];
	public BufferedImage[] menu_p = new BufferedImage[3];
	
	public Texture(Game game)
	{
		ps = new SpriteSheet(game.getPlayerSheet());
		bs = new SpriteSheet(game.getBlockSheet());
		
		
		
		getTextures();
	}
	
	public void getTextures()
	{
		//load in the textures right here
		player[0] = ps.grabImage(1, 1, 32, 64);
		player[1] = ps.grabImage(2, 1, 32, 64);
		player[2] = ps.grabImage(3, 1, 32, 64);
		player[3] = ps.grabImage(4, 1, 32, 64);
		player[4] = ps.grabImage(5, 1, 32, 64);
		player[5] = ps.grabImage(6, 1, 32, 64);
		player[6] = ps.grabImage(7, 1, 32, 64);
		
		player[7] = ps.grabImage(20, 1, 32, 64);
		player[8] = ps.grabImage(19, 1, 32, 64);
		player[9] = ps.grabImage(18, 1, 32, 64);
		player[10] = ps.grabImage(17, 1, 32, 64);
		player[11] = ps.grabImage(16, 1, 32, 64);
		player[12] = ps.grabImage(15, 1, 32, 64);
		player[13] = ps.grabImage(14, 1, 32, 64);
		
		//player jumping
		player[14] = ps.grabImage(8, 2, 32, 64);
		player[15] = ps.grabImage(9, 2, 32, 64);
		player[16] = ps.grabImage(10, 2, 32, 64);
		
		player[17] = ps.grabImage(13, 2, 32, 64);
		player[18] = ps.grabImage(12, 2, 32, 64);
		player[19] = ps.grabImage(11, 2, 32, 64);
		
		player[20] = ps.grabImage_ext(4, 6, 32, 64, 160, 96);
		
		//blocks
		block[0] = bs.grabImage(1, 1, 32, 32);
		block[1] = bs.grabImage(2, 1, 32, 32);
		block[2] = bs.grabImage(2, 4, 32, 32);
		
		block[3] = bs.grabImage_ext(1, 4, 32, 32, 119, 119);
		block[4] = bs.grabImage_ext(4, 4, 32, 32, 119, 119);
		
		block[5] = bs.grabImage(3, 1, 32, 32);
		block[6] = bs.grabImage(4, 1, 32, 32);
		block[7] = bs.grabImage(5, 1, 32, 32);
		block[8] = bs.grabImage(6, 1, 32, 32);
		
		block[9] = bs.grabImage(7, 1, 32, 32);
		block[10] = bs.grabImage(8, 1, 32, 32);
		
		//coins
		coin[0] = bs.grabImage(1, 2, 32, 32);
		coin[1] = bs.grabImage(2, 2, 32, 32);
		coin[2] = bs.grabImage(3, 2, 32, 32);
		coin[3] = bs.grabImage(4, 2, 32, 32);
		coin[4] = bs.grabImage(5, 2, 32, 32);
		coin[5] = bs.grabImage(6, 2, 32, 32);
		coin[6] = bs.grabImage(1, 3, 32, 32);
		coin[7] = bs.grabImage(2, 3, 32, 32);
		coin[8] = bs.grabImage(3, 3, 32, 32);
		
		//menu player
		menu_p[0] = ps.grabImage(1, 2, 32, 64);
		menu_p[1] = ps.grabImage(2, 2, 32, 64);
		menu_p[2] = ps.grabImage(3, 2, 32, 64);
	}
	
}
