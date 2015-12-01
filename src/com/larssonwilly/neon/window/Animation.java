package com.larssonwilly.neon.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed; //of animation
	private int frames; // how many frames in animation, 7
	
	private int index = 0; // currently at
	private int count = 0; // frame we are on
	
	private BufferedImage[] images; //how many images we have
	private BufferedImage currentImg; // to display
	
	public Animation(int speed, BufferedImage... args)	{
		this.speed = speed;
		images = new BufferedImage[args.length];
		
		for(int i = 0; i < args.length; i++)	{
			images[i] = args[i];
		}
		
		frames = args.length;
	}
	
	public void runAnimation()	{
		index++;
		if(index > speed)	{
			index = 0;
			nextFrame();
		}
	}
	
	private void nextFrame()	{
		for(int i = 0; i < frames; i++)	{
			if(count == i)	{
				currentImg = images[i]; //image0 first frame
			}
		}
		
		count++;
		
		if(count > frames)
			count = 0;
	}
	
	public void drawAnimation(Graphics g, int x, int y)	{
		g.drawImage(currentImg, x, y, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY)	{
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}
	
}















