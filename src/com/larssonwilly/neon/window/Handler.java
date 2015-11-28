package com.larssonwilly.neon.window;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.larssonwilly.neon.framework.*;
import com.larssonwilly.neon.objects.*;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private Random r = new Random();
	private GameObject tempObject;
	
	public Handler()	{
		
	}
	
	public void tick()	{
		for(int i = 0; i < object.size(); i++)	{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)	{
		for(int i = 0; i < object.size(); i++)	{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearObjects()	{
		for(int i = 0; i < object.size(); i++)	{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() != ObjectId.Player)	{
				object.remove(tempObject);
				i = 0;
			}
		}
	}
	
	/*public void addObjects(int nr, ObjectId id, int x, int y, boolean horizontal)	{
		if(id == ObjectId.Block && horizontal == true)	{
			for(int i = 0; i < nr; i++)	{
				addObject(new Block(32*i + x, y, ObjectId.Block));
			}
		} else if(id == ObjectId.Block && horizontal == false)	{
			for(int i = 0; i < nr; i++)	{
				addObject(new Block(x, 32 * i + y, ObjectId.Block));
			}
		}
	}*/
	
	
	
	public void addObject(GameObject object)	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)	{
		this.object.remove(object);
	}
	
	public LinkedList<GameObject> getObject() {
		return object;
	}
	
}
