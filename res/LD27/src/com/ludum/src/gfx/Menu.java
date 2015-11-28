package com.ludum.src.gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import com.ludum.src.interfaces.Texture;
import com.ludum.src.window.Controller;

public class Menu 
{
	Texture tex;
	Controller controller;
	
	private float radius = 800;
	private boolean changing = true;
	
	private int x = 100, y = 100;
	private float velX = 1f;
	private float velY = 1f;
	
	private int selected = 0;
	
	public Menu(Texture tex, Controller controller)
	{
		this.tex = tex;
		this.controller = controller;
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		if(x <= 0) 
			velX *= -1;
		if(y <= 0)
			velY *= -1;
		if(x >= 640)
			velX *= -1;
		if(y >= 480)
			velY *= -1;
		
		
		if(selected < 0)
			selected = 2;
		else if(selected > 2)
			selected = 0;
		
		if(changing)
		{
			if(radius > 700)
				radius -= 0.2f;
			else
				changing = false;
		}
		else
		{
			if(radius < 800)
				radius += 0.2f;
			else
				changing = true;
		}
	}
	
	public void render(Graphics g)
	{
		if(selected == 0)
			g.drawImage(tex.menu_p[0], -140, -20, (32 * 14), (32 * 20), null);
		if(selected == 2)
			g.drawImage(tex.menu_p[1], -140, -20, (32 * 14), (32 * 20), null);
		if(selected == 1)
			g.drawImage(tex.menu_p[2], -120, -20, (32 * 14), (32 * 20), null);
		
		g.setColor(Color.red);
		g.setFont(new Font("Peach Milk", 0, 120));
		g.drawString("Satyr Run", 130, 100);
		
		
		g.setColor(Color.red);
		
		if(selected == 0){
			g.setFont(new Font("Peach Milk", 0, 100));
			g.drawString("Play", 370, 250);
		}		
		else{
			g.setFont(new Font("Peach Milk", 0, 70));
			g.drawString("Play", 390, 250);
		}
		
		if(selected == 1){
			g.setFont(new Font("Peach Milk", 0, 100));
			g.drawString("Options", 320, 350);
		}		
		else{
			g.setFont(new Font("Peach Milk", 0, 70));
			g.drawString("Options", 355, 350);
		}
		
		if(selected == 2){
			g.setFont(new Font("Peach Milk", 0, 100));
			g.drawString("Quit", 380, 450);
		}
		else{
			g.setFont(new Font("Peach Milk", 0, 70));
			g.drawString("Quit", 392, 450);
		}
			
		
		g.setColor(Color.red);
		g.setFont(new Font("Peach Milk", 0, 20));
		
		g.drawString("Ludum Dare 27", 550, 465);
		g.drawString("Made By Zack Berenger", 490, 485);
		
		Graphics2D g2d = (Graphics2D) g;
		
		Point2D center = new Point2D.Float(x, y);
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.black};
		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
		g2d.setPaint(p);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .95f));
		g2d.fillRect(0, 0, 800, 800);
		g2d.dispose();
	}
	
	public int getSelected(){
		return selected;
	}
	
	public void setSelected(int selected){
		this.selected = selected;
	}
	
	
}
