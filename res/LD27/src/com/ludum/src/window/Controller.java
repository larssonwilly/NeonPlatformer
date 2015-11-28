package com.ludum.src.window;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.ludum.src.gfx.PREFABS;
import com.ludum.src.gfx.Time;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;
import com.ludum.src.objects.Base;
import com.ludum.src.objects.Coin;
import com.ludum.src.objects.Player;
import com.ludum.src.window.Game.STATE;

public class Controller 
{
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	Texture tex;
	
	Random r = new Random();
	
	public boolean proceed = false;
	
	public int storeY = 250;
	public int storeSelect = 0;
	
	public boolean isDead = false;
	
	public int coinSpawn = 18;
	
	public int speed = 5;
	
	public Controller(Texture tex)
	{
		this.tex = tex;
	}
	
	public void createGame(Time time, Game game)
	{
		object.clear();
		
		Game.state = STATE.Counter;
		Time.LEVEL = 1;
		
		generateLevel(game.getChunk_size(), game.getChunk_length());
		this.addObject(new Player(100, 100, tex, ObjectId.Player, time, this));
		time.timer3.start();
	}
	
	public void generateLevel(int chunk_size, int chunk_length)
	{
		int level = 240 + r.nextInt(240);
		
		level = (level / 32) * 32;
		
		int xx = 0;
		
		for(int i = 0; i < chunk_size; i++)
		{
			if(i == chunk_size - 1)
			{
				for(int j = 0; j < (45 * 32); j += 32)
				{	
					this.addObject(new Base(xx, level, tex, ObjectId.End_Block));
					xx += 32;
				}
			}
			else
			{
				for(int j = 0; j < (32 * (r.nextInt(chunk_length + 5) + chunk_length)); j += 32)
				{	
					
					int createChunk = r.nextInt(15);
					
					if(createChunk == 1 && i < (chunk_size - 2) && i > 0 && Time.LEVEL > 2)
					{
						PREFABS.generateStairs(xx, level, tex, this);
						
						j += 160;
						
						xx += (32 * 11);
					}
					else if(createChunk == 2 && i < (chunk_size - 2) && i > 0 && Time.LEVEL > 4)
					{
						
						j += 256;
						
						PREFABS.generatePillar(xx, level, tex, this);
						xx += (32 * 21);
					}
					else
					{
						if(i > 1)
						{
							int z = r.nextInt(coinSpawn);
							if(z == 0)
								this.addObject(new Coin(xx, level - 32, tex, ObjectId.Coin, this));
						//	coinCount = 0;
						}
						//else coinCount++;
						
						
						this.addObject(new Base(xx, level, tex, ObjectId.Moon_Top));
						xx += 32;
					}
				}
				
				int z = r.nextInt(6);
				
				if(z == 0 && level < 352) level += 32;
				else if(z == 1 && level < 352) level += 64;
				else if(z == 2 && level < 352) level += 96;
				else level -= 64;
				
				if(z == 4 && level > 256) level -= 32;
				else if(z == 5 && level > 256) level -= 64;
				else if(z == 6 && level > 256) level -= 96;
				else level += 64;
			}
		}
	}
	
	public void tick()
	{	
		if(storeSelect < 0) storeSelect = 3;
		else if(storeSelect > 3) storeSelect = 0;
		
		if(storeSelect == 0) storeY = 250;
		else if(storeSelect == 1) storeY = 300;
		else if(storeSelect == 2) storeY = 350;
		else if(storeSelect == 3) storeY = 400;
		
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
}
