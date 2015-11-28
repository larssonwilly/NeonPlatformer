package com.larssonwilly.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.larssonwilly.neon.framework.GameObject;
import com.larssonwilly.neon.framework.ObjectId;
import com.larssonwilly.neon.framework.Texture;
import com.larssonwilly.neon.window.Game;
import com.larssonwilly.neon.window.Handler;


public class Player extends GameObject {

	private float width = 48, height = 96;
	
	private float gravity = 0.18f;
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	public Player(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(falling || jumping)	{
			velY += gravity;
			
			Game.boundary((int)velY, 10, -10);
		}
		
		collision(object);
		
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
		g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);

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
