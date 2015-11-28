package com.ludum.src.window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.ludum.src.gfx.Menu;
import com.ludum.src.gfx.Time;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.window.Game.STATE;

public class KeyInput extends KeyAdapter
{
	
	private Controller controller;
	private Menu menu;
	private Time time;
	private Game game;
	
	private int jumpHeight = 12;
	private int downHeight = 6;
	
	Random r = new Random();
	
	public KeyInput(Controller controller, Menu menu, Time time, Game game)
	{
		this.controller = controller;
		this.menu = menu;
		this.time = time;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < controller.object.size(); i++)
		{
			GameObject object = controller.object.get(i);
			
			if(object.getId() == ObjectId.Player && object.getMove() && Game.state == STATE.Game)
			{
				//player Controls
				if(key == KeyEvent.VK_D) object.setVelX(object.getSpeed());
				if(key == KeyEvent.VK_A) object.setVelX(-object.getSpeed());
				if(key == KeyEvent.VK_S) object.setFallSpeed(downHeight);
				
				if(key == KeyEvent.VK_RIGHT) object.setVelX(object.getSpeed());
				if(key == KeyEvent.VK_LEFT) object.setVelX(-object.getSpeed());
				if(key == KeyEvent.VK_DOWN) object.setFallSpeed(downHeight);
				
				if(key == KeyEvent.VK_SPACE)
				{
					if(!object.getJumping())
					{
						object.setFalling(true);
						object.setFallSpeed(-jumpHeight);
						object.setJumping(true);
					}
				}
				
			}
		}
		
		if(Game.state == Game.STATE.Menu)
		{
			if(key == KeyEvent.VK_W){  menu.setSelected(menu.getSelected() - 1); int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play(); }
			if(key == KeyEvent.VK_S){ menu.setSelected(menu.getSelected() + 1);int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play(); }
			
			if(key == KeyEvent.VK_UP){ menu.setSelected(menu.getSelected() - 1); int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play(); }
			if(key == KeyEvent.VK_DOWN){ menu.setSelected(menu.getSelected() + 1); int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play(); }
			
			if(key == KeyEvent.VK_ENTER && menu.getSelected() == 0) controller.createGame(time, game);
			if(key == KeyEvent.VK_ENTER && menu.getSelected() == 1) Game.state = STATE.Options;
			if(key == KeyEvent.VK_ENTER && menu.getSelected() == 2) System.exit(1);
		}
		
		if(Game.state == STATE.Options)
		{
			if(key == KeyEvent.VK_BACK_SPACE)
			{
				Game.state = STATE.Menu;
			}
		}
		
		if(Game.state == STATE.Dead)
		{
			if(key == KeyEvent.VK_ENTER){
				
				controller.object.clear();
				Game.state = STATE.Menu;
				
				Time.U1 = 1;
				Time.U2 = 1;
				Time.U3 = 1;
				Time.U4 = 1;
				
				time.time = 9;
				time.timeMil = 9;
				
				jumpHeight = 12;
				downHeight = 6;
				controller.coinSpawn = 18;
				
				Time.LEVEL = 1;
				Time.SCORE = 0;
				
				controller.isDead = false;
				controller.generateLevel(10, 10);
			}
		}
		
		if(Game.state == Game.STATE.Store)
		{
			if(key == KeyEvent.VK_W)  {
				int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play();
				controller.storeSelect--;
			}
			if(key == KeyEvent.VK_S) {
				int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play();
				controller.storeSelect++;
			}
			
			if(key == KeyEvent.VK_UP) {
				int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play();
				controller.storeSelect--;
			}
			if(key == KeyEvent.VK_DOWN) {
				int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("doot").play();else AudioPlayer.getSound("deet").play();
				controller.storeSelect++;
			}
			
			if(key == KeyEvent.VK_ENTER && controller.storeSelect == 0){
				if(Time.U1 == 1){
					if(Time.SCORE >= 3000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 3000;
						Time.U1++;
						controller.speed += 2;
					}
				}else if(Time.U1 == 2){
					if(Time.SCORE >= 5000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 5000;
						Time.U1++;
						controller.speed += 2;
					}
				}else if(Time.U1 == 3){
					if(Time.SCORE >= 7000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 7000;
						Time.U1++;
						controller.speed += 2;
					}
				}else if(Time.U1 == 4){
					if(Time.SCORE >= 9000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 9000;
						Time.U1++;
						controller.speed += 2;
					}
				}
			}
			if(key == KeyEvent.VK_ENTER && controller.storeSelect == 1){
				if(Time.U2 == 1){
					if(Time.SCORE >= 3000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 3000;
						Time.U2++;
						jumpHeight += 2;
					}
				}else if(Time.U2 == 2){
					if(Time.SCORE >= 5000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 5000;
						Time.U2++;
						jumpHeight += 2;
					}
				}else if(Time.U2 == 3){
					if(Time.SCORE >= 7000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 7000;
						Time.U2++;
						jumpHeight += 2;
					}
				}else if(Time.U2 == 4){
					if(Time.SCORE >= 9000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 9000;
						Time.U2++;
						jumpHeight += 2;
					}
				}
			}
			if(key == KeyEvent.VK_ENTER && controller.storeSelect == 2){
				if(Time.U3 == 1){
					if(Time.SCORE >= 3000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 3000;
						Time.U3++;
						downHeight += 2;
					}
				}else if(Time.U3 == 2){
					if(Time.SCORE >= 5000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 5000;
						Time.U3++;
						downHeight += 2;
					}
				}else if(Time.U3 == 3){
					if(Time.SCORE >= 7000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 7000;
						Time.U3++;
						downHeight += 2;
					}
				}else if(Time.U3 == 4){
					if(Time.SCORE >= 9000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 9000;
						Time.U3++;
						downHeight += 2;
					}
				}
			}
			if(key == KeyEvent.VK_ENTER && controller.storeSelect == 3){
				if(Time.U4 == 1){
					if(Time.SCORE >= 3000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 3000;
						Time.U4++;
						controller.coinSpawn -= 15;
					}
				}else if(Time.U4 == 2){
					if(Time.SCORE >= 5000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 5000;
						Time.U4++;
						controller.coinSpawn -= 1;
					}
				}else if(Time.U4 == 3){
					if(Time.SCORE >= 7000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 7000;
						Time.U4++;
						controller.coinSpawn -= 1;
					}
				}else if(Time.U4 == 4){
					if(Time.SCORE >= 9000){
						int x = r.nextInt(2); if(x==0)AudioPlayer.getSound("ching").play();else AudioPlayer.getSound("ching2").play();
						Time.SCORE -= 9000;
						Time.U4++;
						controller.coinSpawn -= 1;
					}
				}
			}
			
			if(key == KeyEvent.VK_SPACE)
			{
				Game.state = STATE.Game;
				controller.proceed = true;
			}
				
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < controller.object.size(); i++)
		{
			GameObject object = controller.object.get(i);
			
			if(object.getId() == ObjectId.Player  && object.getMove())
			{
				//player Controls
				if(key == KeyEvent.VK_D) object.setVelX(0);
				if(key == KeyEvent.VK_A) object.setVelX(0);
				
				if(key == KeyEvent.VK_RIGHT) object.setVelX(0);
				if(key == KeyEvent.VK_LEFT) object.setVelX(0);
				
				
			}
		}
	}
	
}
