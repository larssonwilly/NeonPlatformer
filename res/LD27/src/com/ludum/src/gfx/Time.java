package com.ludum.src.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.ludum.src.interfaces.Texture;
import com.ludum.src.window.Controller;
import com.ludum.src.window.Game;
import com.ludum.src.window.Game.STATE;

public class Time implements ActionListener
{
	
	public Timer timer, timer2, timer3;
	Controller controller;
	
	public static int LEVEL = 7, SCORE = 0;
	
	private boolean canStop = false;
	
	public static int U1 = 1, U2 = 1, U3 = 1, U4 = 1;
	
	public int time = 9, timeMil = 9, counter = 3;
	
	public Time(Controller controller)
	{
		this.controller = controller;
		
		timer = new Timer(1000, this);
		timer2 = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				timeMil--;
				if(timeMil <= 0){
					if(!canStop){
						timeMil = 9;
					}else{
						timer2.stop();
						canStop = false;
					}
				}
					
			}
		});
		
		timer3 = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
					counter--;
					if(counter <= 0)
					{
						Game.state = STATE.Game;
						timer.start();
						timer2.start();
						counter = 3;
						timer3.stop();
					}
			}
		});
	}
	
	public void startTime(){
		timer.start();
		timer2.start();
	}
	
	public void render(Graphics g, Texture tex)
	{
		g.setColor(Color.yellow);
		g.setFont(new Font("Peach Milk", 0, 120));
		if(time == 10)
			g.drawString(time + "." + timeMil, 500, 480);
		else if(time == 0)
			g.drawString(time + "." + timeMil, 530, 480);
		else
			g.drawString(time + "." + timeMil, 540, 480);
		
		
		g.setFont(new Font("Peach Milk", 1, 40));
		g.drawString("STAGE " + LEVEL, 10, 480);
		
		g.drawString("SCORE: " + SCORE, 10, 50);
		
		if(Game.state == STATE.Counter)
		{	
			
			if(counter == 3)
			{
				g.setColor(Color.red);
				g.setFont(new Font("Peach Milk", 1, 190));
				g.drawString("READY", 140, 300);
			}else if(counter == 2)
			{
				g.setColor(Color.yellow);
				g.setFont(new Font("Peach Milk", 1, 190));
				g.drawString("SET", 200, 300);
			}else if(counter == 1)
			{
				g.setColor(Color.green);
				g.setFont(new Font("Peach Milk", 1, 190));
				g.drawString("SATYR!!", 100, 300);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(time > 0)
		{
			time--;
			timer.setRepeats(true);
		}else{
			canStop = true;
			timer.stop();
		}
			
	}
	
}
