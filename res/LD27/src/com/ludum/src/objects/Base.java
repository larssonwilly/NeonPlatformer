package com.ludum.src.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ludum.src.interfaces.Entity;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;

public class Base extends Entity implements GameObject
{
	
	private int speed = 5;
	private float velX = 0, velY = 0;
	
	public Base(int x, int y, Texture tex, ObjectId id) {
		super(x, y, tex, id);
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		
	}

	
	public void render(Graphics g) 
	{		
		//Graphics2D g2d =(Graphics2D) g;
	//	g.setColor(Color.red);
	//	g2d.draw(getBounds());
		
		if(id == ObjectId.Moon_Top)
			g.drawImage(tex.block[1], x, y, null);
		else if(id == ObjectId.End_Block)
			g.drawImage(tex.block[2], x, y, null);
		
		for(int yy = y + 32; yy < 512; yy += 32)
		{
			if(id == ObjectId.Moon_Top)
				g.drawImage(tex.block[0], x, yy, null);
			else if(id == ObjectId.End_Block)
				g.drawImage(tex.block[2], x, yy, null);
		}
		
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 512);
	}

	
	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}

	
	public void setX(int x) {
		this.x = x;
	}

	
	public void setY(int y) {
		this.y = y;
	}

	
	public float getVelX() {
		return velX;
	}

	
	public float getVelY() {
		return velY;
	}

	
	public void setVelX(float velX) {
		this.velX = velX;
	}

	
	public void setVelY(float velY) {
		this.velY = velY;
	}

	
	public void setFallSpeed(float FallSpeed) {
	}

	
	public float getFallSpeed() {
		return 0;
	}

	
	public boolean getJumping() {
		return false;
	}

	
	public void setJumping(boolean isJumping) {
	}

	
	public void setFalling(boolean isFalling) {
	}

	public boolean getFalling() {
		return false;
	}
	
	public boolean getMove(){
		return false;
	}
	
	public int getSpeed() {
		return speed;
	}

	
	public ObjectId getId() {
		return id;
	}

}
