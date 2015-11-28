package com.ludum.src.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ludum.src.gfx.Animation;
import com.ludum.src.gfx.Time;
import com.ludum.src.interfaces.Entity;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;
import com.ludum.src.window.AudioPlayer;
import com.ludum.src.window.Controller;
import com.ludum.src.window.Game;
import com.ludum.src.window.Game.STATE;

public class Player extends Entity implements GameObject
{	
	private int speed = 5;
	private float Gravity = 0.5f;
	private boolean isJumping = false;
	private boolean isFalling = true;
	private float FallSpeed = 0;
	private float velX = 0, velY = 0;
	private boolean canMove = true;

	private final int MAX_FALL_SPEED = 20;
	
	private Animation walkingRight, walkingLeft;
	Time time;
	Controller controller;
	private int facing = 1;
	
	private boolean addScore = false;
	private int bonus = 0;

	public Player(int x, int y, Texture tex, ObjectId id, Time time, Controller controller) {
		super(x, y, tex, id);
		
		this.time = time;
		this.controller = controller;
		
		speed = controller.speed;
		
		walkingRight = new Animation(7,tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
		walkingLeft = new Animation(7,tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13]);
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		
		speed = controller.speed;
		
		if(canMove)
			x += velX;
		else{
			if(velX > 0)
				velX -= 0.1f;
			else
			{
				velX = 0;
				
				if(Game.ALPHA < 0.01)
					canMove = true;
			}
				
			x += velX;
		}
			
		
		//Gravity
		if(isFalling || isJumping)
		{
			y += FallSpeed;
			
			if(FallSpeed < MAX_FALL_SPEED)
				FallSpeed += Gravity;
		}
		
		if(time.timeMil <= 0)
		{
			canMove = false;
			time.timer.stop();
			time.timer2.stop();
			
			if(!controller.isDead)
				Game.ALPHA = 0.1f;
			
			controller.isDead = true;
			controller.proceed = true;
			
			velX = 0;
		}
		
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			if(getBounds().intersects(tempObject.getBounds()))
			{
				if(tempObject.getId() == ObjectId.Coin)
				{
					AudioPlayer.getSound("pop").play();
					controller.removeObject(tempObject);
					Time.SCORE += 115;
				}
				
				if(tempObject.getId() == ObjectId.Lava)
				{
					//Game.state = STATE.Dead;
					//for(int ii = 0; ii < 50; ii++)
					//	controller.addObject(new Blood(x, y, tex, ObjectId.Blood, controller));
					canMove = false;
					time.timer.stop();
					time.timer2.stop();
					
					if(!controller.isDead)
						Game.ALPHA = 0.1f;
					
					controller.isDead = true;
					controller.proceed = true;
					
					canMove = false;
					y = tempObject.getY() + 512;
					
					isFalling = false;
					isJumping = false;
					FallSpeed = 0;
					
					velX = 0;
					
					//controller.object.clear();
					//controller.generateLevel(10, 10);
				}
				
				//Collision
				if(tempObject.getId() == ObjectId.Moon_Top || tempObject.getId() == ObjectId.End_Block)
				{
					if(tempObject.getId() == ObjectId.End_Block && canMove)
					{
						if(canMove)
						{
							AudioPlayer.getSound("woah").play();
							canMove = false;
						}
						
						time.timer.stop();
						time.timer2.stop();
					}
					
					if(FallSpeed < -1)
					{
						y = tempObject.getY() + 8;
						
						isFalling = false;
						FallSpeed = 0;
					}
					else
					{
						y = tempObject.getY() - 96;
						
						isFalling = false;
						isJumping = false;
						FallSpeed = 0;
					}
					
				}
			}else 
				isFalling = true;
			
			if(getBoundsRight().intersects(tempObject.getBounds()))
			{
				if(tempObject.getId() != ObjectId.Coin)
					x += speed;
			
			}else if(getBoundsLeft().intersects(tempObject.getBounds()))
			{
				if(tempObject.getId() != ObjectId.Coin)
				{
					if(FallSpeed > 5)
						x -= (speed * 2);
					else
						x -= speed;
				}		
			}
		}
		
		if(velX > 0) facing = 1;
		else if(velX < 0) facing = -1;
		
		//animation
		walkingRight.runAnimation();
		walkingLeft.runAnimation();
		
		if(canMove == false && velX == 0)
		{
			int finBonus = time.time * 500;
			
			if(bonus < finBonus)
			{
				if(time.time >= 3)
					bonus += 10;
				else
					bonus += 5;
			}	
			else
			{
				if(!addScore)
				{
					addScore = true;
					Time.SCORE += bonus;
				}
			}
		}
		
	}

	
	public void render(Graphics g) 
	{
		//Graphics2D g2d =(Graphics2D) g;
		//g.setColor(Color.red);
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsRight());
		//g.setColor(Color.green);
		//g2d.draw(getBounds());
		
		if(facing == 1)
		{
			if(FallSpeed > 2)
				g.drawImage(tex.player[16], x, y, 64, 96, null);
			else
			{
				if(isJumping)
				{
					if(FallSpeed > 0)
						g.drawImage(tex.player[16], x, y, 64, 96, null);
					else 
						g.drawImage(tex.player[14], x, y, 64, 96, null);
				}
					
				else if(velX > 0)
					walkingRight.drawAnimation(g, x, y, 64, 96);
				else
					g.drawImage(tex.player[0], x, y, 64, 96, null);
			}
			
		}if(facing == -1)
		{	
			if(FallSpeed > 2)
				g.drawImage(tex.player[19], x, y, 64, 96, null);
			else
			{
				if(isJumping)
				{
					if(FallSpeed > 0)
						g.drawImage(tex.player[19], x, y, 64, 96, null);
					else 
						g.drawImage(tex.player[17], x, y, 64, 96, null);
				}	
				else if(velX < 0)
					walkingLeft.drawAnimation(g, x, y, 64, 96);
				else
					g.drawImage(tex.player[7], x, y, 64, 96, null);
			}
			
		}
		
		if(canMove == false && velX == 0)
		{
			if(!controller.isDead)
			{
				g.setColor(Color.yellow);
				g.setFont(new Font("Peach Milk", 0, 70));
				g.drawString("Stage " + Time.LEVEL + " Complete", x - 180, 130);
				
				g.setFont(new Font("Peach Milk", 0, 30));
				g.drawString("(s) remaining: " + time.time + " * 500 = " + bonus , x - 120, 180);
				
				g.setColor(Color.white);
				g.drawString("Press the \"Space\" key to continue", x - 140, 480);
			}
			
			
			
			if(addScore && !controller.isDead)
			{
				g.setColor(Color.green);
				Game.state = STATE.Store;
				
				g.setFont(new Font("Peach Milk", 0, 30));
				
				g.setColor(Color.yellow);
				g.drawString("--)", x - 150, controller.storeY);
				
				if(Time.U1 == 1){
					if(Time.SCORE >= 3000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$3000 Move Faster : LVL " + Time.U1, x - 105, 250);
				}
				else if(Time.U1 == 2){
					if(Time.SCORE >= 5000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$5000 Move Faster : LVL " + Time.U1, x - 105, 250);
				}	
				else if(Time.U1 == 3){
					if(Time.SCORE >= 7000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$7000 Move Faster : LVL " + Time.U1, x - 105, 250);
				}
				else if(Time.U1 == 4){
					if(Time.SCORE >= 9000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$9000 Move Faster : LVL " + Time.U1, x - 105, 250);
				}
				
				if(Time.U2 == 1){
					if(Time.SCORE >= 3000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$3000 Jump Higher : LVL " + Time.U2, x - 105, 300);
				}
				else if(Time.U2 == 2){
					if(Time.SCORE >= 5000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$5000 Jump Higher : LVL " + Time.U2, x - 105, 300);
				}
				else if(Time.U2 == 3){
					if(Time.SCORE >= 7000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$7000 Jump Higher : LVL " + Time.U2, x - 105, 300);
				}
				else if(Time.U2 == 4){
					if(Time.SCORE >= 9000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$9000 Jump Higher : LVL " + Time.U2, x - 105, 300);
				}
					
				
				if(Time.U3 == 1){
					if(Time.SCORE >= 3000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$3000 Fall Quicker : LVL " + Time.U3, x - 105, 350);
				}
				else if(Time.U3 == 2){
					if(Time.SCORE >= 5000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$5000 Fall Quicker : LVL " + Time.U3, x - 105, 350);
				}
				else if(Time.U3 == 3){
					if(Time.SCORE >= 7000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$7000 Fall Quicker : LVL " + Time.U3, x - 105, 350);
				}	
				else if(Time.U3 == 4){
					if(Time.SCORE >= 9000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$9000 Fall Quicker : LVL " + Time.U3, x - 105, 350);
				}
					
				
				if(Time.U4 == 1){
					if(Time.SCORE >= 3000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$3000 More Coins : LVL " + Time.U4, x - 105, 400);
				}	
				else if(Time.U4 == 2){
					if(Time.SCORE >= 5000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$5000 More Coins : LVL " + Time.U4, x - 105, 400);
				}	
				else if(Time.U4 == 3){
					if(Time.SCORE >= 7000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$7000 More Coins : LVL " + Time.U4, x - 105, 400);
				}
				else if(Time.U4 == 4){
					if(Time.SCORE >= 9000)
						g.setColor(Color.green);
					else
						g.setColor(Color.red);
					g.drawString("$9000 More Coins : LVL " + Time.U4, x - 105, 400);
				}	
			}
			
			if(controller.proceed)
			{
				if(!controller.isDead)
					Game.state = STATE.Game;
				
				if(!controller.isDead)
				{
					if(Game.ALPHA < 0.99)
						Game.ALPHA += 0.01f;
				}else{
					if(Game.ALPHA < 0.99)
						Game.ALPHA += 0.001f;
				}
				
				
				addScore = true;
			}else
				Game.ALPHA = 0.1f;
			
		}
		
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x + 28, y + 32, 8, 64);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle(x + 20, y + 32, 3, 60);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle(x + 42, y + 32, 3, 60);
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
		this.FallSpeed = FallSpeed;
	}

	
	public float getFallSpeed() {
		return FallSpeed;
	}

	
	public boolean getJumping() {
		return isJumping;
	}

	
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	
	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

	public boolean getFalling() {
		return isFalling;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public boolean getMove(){
		return canMove;
	}

	
	public ObjectId getId() {
		return id;
	}

}
