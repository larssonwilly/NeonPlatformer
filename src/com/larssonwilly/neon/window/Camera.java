package com.larssonwilly.neon.window;

import java.awt.Color;
import java.awt.Graphics;

import com.larssonwilly.neon.framework.*;

public class Camera {

	private float x,y;
	public static int HEALTH = 100;
	private int width = 300, height = 35;
	
	
	public Camera(float x, float y)	{
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player)	{
		x = -player.getX() + Game.WIDTH/2 - 16;
		HEALTH = Game.boundary(HEALTH, 100, 0);
		
	}

	public void render(Graphics g)	{
		g.setColor(Color.RED);
		g.fillRect(-(int)x+10, -(int)y+10, width, height);
		
		g.setColor(Color.green);
		g.fillRect(-(int)x+10, -(int)y+10, HEALTH*3, height);

		g.setColor(Color.white);
		g.drawRect(-(int)x+10, -(int)y+10, width, height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
