package com.ludum.src.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ludum.src.gfx.Animation;
import com.ludum.src.interfaces.Entity;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;

public class Lava extends Entity implements GameObject
{
	
	private int speed = 5;
	private float velX = 0, velY = 0;
	
	private Animation lava, lava_base;
	
	public Lava(int x, int y, Texture tex, ObjectId id) {
		super(x, y, tex, id);
	
		lava = new Animation(14, tex.block[5], tex.block[6], tex.block[7]);
		lava_base = new Animation(14, tex.block[8], tex.block[9], tex.block[10]);
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		lava.runAnimation();
		lava_base.runAnimation();
	}

	
	public void render(Graphics g) 
	{		
		if(id == ObjectId.Lava)
			lava.drawAnimation(g, x, y);
		else if(id == ObjectId.LavaBase)
			lava_base.drawAnimation(g, x, y);
		
		for(int yy = y + 32; yy < 512; yy += 32)
		{
			if(id == ObjectId.Lava)
				lava_base.drawAnimation(g, x, yy);
		}
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 256);
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
