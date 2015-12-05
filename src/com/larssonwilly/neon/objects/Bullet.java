package com.larssonwilly.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.larssonwilly.neon.framework.*;
import com.larssonwilly.neon.window.*;

public class Bullet extends GameObject	{

	private int velX = 8;
	Handler handler;
	Player player;
	private int width = 8, height = 8;
	private int facing;
	
	public Bullet(float x, float y, ObjectId id, Handler handler, int facing)	{
		super(x, y, id);
		this.handler = handler;
		this.facing = facing;
	}
	
	public void tick(LinkedList<GameObject> object) {
		x += velX*facing;
		
		collision();
		
	}
	
	public void collision()	{
		for(int i = 0; i < handler.object.size(); i++)	{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Enemy){
				if(getBounds().intersects(tempObject.getBounds()))	{
					handler.removeObject(tempObject);
					handler.removeObject(this);
				}
			}
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y+20, width, height);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, width, height);
	}

}
