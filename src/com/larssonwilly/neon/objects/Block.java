package com.larssonwilly.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.larssonwilly.neon.framework.GameObject;
import com.larssonwilly.neon.framework.ObjectId;
import com.larssonwilly.neon.framework.Texture;
import com.larssonwilly.neon.window.Game;

import java.awt.Rectangle;

public class Block extends GameObject	{

	Texture tex = Game.getInstance();
	private int type;
	
	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(tex.block[type], (int)x, (int)y, null);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
