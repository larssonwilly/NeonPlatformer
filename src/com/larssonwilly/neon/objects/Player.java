package com.larssonwilly.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.larssonwilly.neon.framework.GameObject;
import com.larssonwilly.neon.framework.ObjectId;
import com.larssonwilly.neon.framework.Texture;
import com.larssonwilly.neon.window.Animation;
import com.larssonwilly.neon.window.Game;
import com.larssonwilly.neon.window.Handler;


public class Player extends GameObject {

	private float width = 48, height = 96;
	private int facing = 1; // 1 right, -1 left
	
	private float gravity = 0.18f;
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalk, playerWalkLeft;
	
	public Player(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		playerWalk = new Animation(5, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
		playerWalkLeft = new Animation(5, tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13]);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0)	facing = -1;
		if(velX > 0)	facing = 1;
		
		if(falling || jumping)	{
			velY += gravity;
			
			Game.boundary((int)velY, 10, -10);
		}
		
		collision(object);
		
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
	}

	private void collision(LinkedList<GameObject> object)	{
		for(int i = 0; i < handler.object.size(); i++)	{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)	{
				
				if(getBoundsBot().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}else	{
					falling = true;
				}
				
				if(getBounds().intersects(tempObject.getBounds()))	{
					y = tempObject.getY() + height/2 - 13;
					velY = 0;
					//falling = true;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds()))	{
					x = tempObject.getX() - 49;
					velX = 0;
				}
				if(getBoundsLeft().intersects(tempObject.getBounds()))	{
					x = tempObject.getX() + width - 14;
					velX = 0;
				}
				
				
				
			}
		}
	}
	
	@Override
	public void render(Graphics g) {

		g.setColor(Color.blue);
		if(jumping)	{
			if(facing == 1)
				g.drawImage(tex.player_jump[2], (int)x, (int)y, 48, 96, null);
			else
				g.drawImage(tex.player_jump[3], (int)x, (int)y, 48, 96, null);
		}	else	{
				if(velX != 0)	{
					if(facing == 1)
						playerWalk.drawAnimation(g, (int)x, (int)y, 48, 96);
					else
						playerWalkLeft.drawAnimation(g, (int)x, (int)y, 48, 96);
				}
				else	{
					if(facing == 1)
						g.drawImage(tex.player[0], (int)x, (int)y, 48, 96, null);
					else
						g.drawImage(tex.player[7], (int)x, (int)y, 48, 96, null);
					
					
				}
		}

	}

	
	public Rectangle getBoundsBot() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x + (int) width/2 - (int)(width/4)-5, (int)y + (int)height/2, (int)width/2+10, (int) height/2);
	}
	@Override // top
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x + (int) width/2 - (int)(width/4)-5, (int)y-2, (int)width/2+10, (int) height/2);
	}
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x + (int) width - 5, (int)y + 5, (int) 5, (int) height - 10);
	}
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y + 5, (int) 5, (int) height - 10);
	}

}
