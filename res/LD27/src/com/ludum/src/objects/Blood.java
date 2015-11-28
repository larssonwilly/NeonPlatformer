package com.ludum.src.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.ludum.src.interfaces.Entity;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;
import com.ludum.src.window.Controller;

public class Blood extends Entity implements GameObject
{
	
	private int speed = 5;
	private float velX = 0, velY = 0;
	
	Controller controller;
	
	private float ALPHA = 1.0f;
	
	Random r = new Random();
	
	public Blood(int x, int y, Texture tex, ObjectId id, Controller controller) {
		super(x, y, tex, id);
	
		this.controller = controller;
		
		int z = r.nextInt(2);
		
		if(z == 0) velX = r.nextInt(3);
		else velX = -r.nextInt(3);
		
		velY = -r.nextInt(4) + -1;
		
	}

	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));}
	
	public void tick(LinkedList<GameObject> object) 
	{
		x += velX;
		y += velY;
		
		if(ALPHA >= 0.1)
			ALPHA -= 0.01f;
		else
			controller.removeObject(this);
	}

	
	public void render(Graphics g) 
	{		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(makeTransparent(ALPHA));
		
		g.setColor(Color.red);
		g.fillOval(x, y, 6, 6);
		
		g2d.setComposite(makeTransparent(1));
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
