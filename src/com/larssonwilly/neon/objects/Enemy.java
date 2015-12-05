package com.larssonwilly.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.larssonwilly.neon.framework.*;
import com.larssonwilly.neon.window.Handler;

public class Enemy extends GameObject	{
	
	Handler handler;
	private float width = 32, height = 32;
	private float velX = 3;
	
	public Enemy(float x, float y, ObjectId id, Handler handler)	{
		super(x, y, id);
		this.handler = handler;
	}
	
	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		
		collision();
		
	}

	public void collision()	{
		for(int i = 0; i < handler.object.size(); i++)	{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)	{
				if(getBounds().intersects(tempObject.getBounds()))	{
					velX *= -1;
				}
				if(!tempObject.getBounds().contains(getBounds().getX(), getBounds().getY()+height+5)){
					velX *= -1;
				}
				if(!tempObject.getBounds().contains(getBounds().getX()+width, getBounds().getY()+height+5)){
					velX *= -1;
				}
			}
			
			
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, (int)width, (int) height);
	}

}
