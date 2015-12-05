package com.larssonwilly.neon.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.larssonwilly.neon.window.Game.STATE;

public class Menu extends MouseAdapter	{

	private Game game;
	private Handler handler;
	private int gameCounter = 0;
	
	public Menu(Game game, Handler handler)	{
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e)	{
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu)	{

			//play
			if(mouseOver(mx, my, 300, 150, 200, 64)){
				game.gameState = STATE.Game;
				
				
				//System.out.println(gameCounter);
				/*if(gameCounter != 0)	{
					System.out.println(handler.object.size());
					handler.object.clear();
					
				}*/
				
				game.init();
				gameCounter++;
			}
			//quit
			if(mouseOver(mx, my, 300, 350, 200, 64))	{
				System.exit(1);
			}
			
			//help
			if(mouseOver(mx, my, 300, 250, 200, 64))	{
				game.gameState = STATE.Help;
			}
			
		}else if(game.gameState == STATE.Help)	{
			if(mouseOver(mx, my, 220, 350, 200, 64))	{
				game.gameState = STATE.Menu;
				return;
			}
		} else if(game.gameState == STATE.End)	{
			if(mouseOver(mx, my, 220, 350, 200, 64))
				game.gameState = STATE.Menu;
		}
	}
	
	public void mouseReleased(MouseEvent e)	{
		
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height)	{
		if(mx > x && mx < x + width)	{
			if(my > y && my < y + height)	{
				return true;
			}else return false;
			
		} else return false;
	
	}
	
	public void render(Graphics g)	{
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		if(game.gameState == STATE.Menu)	{

			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			g.setFont(font);
			
			g.setColor(Color.white);
			g.drawString("Menu", 340, 80);
			
			g.setFont(font2);
			g.drawRect(300, 150, 200, 64);
			g.drawString("Play", 370, 193);
			
			g.drawRect(300, 250, 200, 64);
			g.drawString("Help", 370, 293);
			
			g.drawRect(300, 350, 200, 64);
			g.drawString("Quit", 370, 393);

		} else if(game.gameState == STATE.Help)	{
			Font font = new Font("arial", 1, 15);
			g.setFont(font);
			
			g.setColor(Color.white);
			g.drawString("Use A, D keys to move. Try not to hit shit. It's not that hard.", 40, 30);
		
			Font font2 = new Font("arial", 1, 30);
			g.setFont(font2);
			
			g.drawRect(220, 350, 200, 64);
			g.drawString("Back", 290, 393);
		
		} else if(game.gameState == STATE.End)	{
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);
			
			g.setFont(font);
			
			g.setColor(Color.white);
			g.drawString("Game over", 190, 70);
		
			
			g.setFont(font2);
			g.drawString("You lost. ", 110, 200);
			
			//g.setFont(font3);
			g.drawRect(220, 350, 200, 64);
			g.drawString("Try again", 255, 393);
		
		}
		
	
	}
	
	public void tick()	{
		
	}
	
}






