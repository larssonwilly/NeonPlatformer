package com.ludum.src.interfaces;

public class Entity 
{

	protected int x, y;
	protected ObjectId id;
	protected Texture tex;
	
	public Entity(int x, int y, Texture tex, ObjectId id)
	{
		this.x = x;
		this.y = y;
		this.tex = tex;
		this.id = id;
	}
	
}
