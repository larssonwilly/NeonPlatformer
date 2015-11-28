package com.ludum.src.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public interface GameObject 
{
	public void tick(LinkedList<GameObject> object);
	public void render(Graphics g);
	public Rectangle getBounds();
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public float getVelX();
	public float getVelY();
	public void setVelX(float velX);
	public void setVelY(float velY);
	public void setFallSpeed(float FallSpeed);
	public float getFallSpeed();
	public boolean getJumping();
	public void setJumping(boolean isJumping);
	public void setFalling(boolean isFalling);
	public boolean getFalling();
	public boolean getMove();
	public int getSpeed();
	public ObjectId getId();	
}
