package com.ludum.src.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ludum.src.gfx.Animation;
import com.ludum.src.interfaces.Entity;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;
import com.ludum.src.window.Controller;

public class Coin extends Entity implements GameObject
{
	
	private int speed = 5;
	private float velX = 0, velY = 0;
	
	private Animation coin;
	Controller controller;
	
	public Coin(int x, int y, Texture tex, ObjectId id, Controller controller) {
		super(x, y, tex, id);
	
		this.controller = controller;
		
		coin = new Animation(9, tex.coin[0], tex.coin[1], tex.coin[2], tex.coin[3], tex.coin[4], tex.coin[5], tex.coin[6], tex.coin[7], tex.coin[8]);
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		coin.runAnimation();
	}

	
	public void render(Graphics g) 
	{		
		if(id == ObjectId.Coin)
			coin.drawAnimation(g, x, y);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
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
