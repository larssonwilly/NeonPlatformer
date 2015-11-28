package com.ludum.src.window;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import com.ludum.src.gfx.BufferedImageLoader;
import com.ludum.src.gfx.Menu;
import com.ludum.src.gfx.Time;
import com.ludum.src.interfaces.Camera;
import com.ludum.src.interfaces.GameObject;
import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;
import com.ludum.src.objects.Player;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 5996886121335760115L;
	
	private Thread thread;
	private boolean running = false;
	
	public static float ALPHA = 0f;
	
	private BufferedImage player_sheet = null, block_sheet = null;
	private BufferedImage background = null;
	
	private Controller controller;
	private Camera cam;
	private Texture tex;
	private Time time;
	private Menu menu;
	
	public static int COLOR_RED = 0, COLOR_BLUE = 0, COLOR_GREEN = 0;
	
	Random r = new Random();
	
	private boolean fade = false;
	
	private int chunk_size = 5;
	private int chunk_length = 10;
	
	public static enum STATE{
		Menu,
		Store,
		Dead,
		Options,
		Counter,
		Game;
	}
	
	public static STATE state = STATE.Menu; 
	
	private void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		player_sheet = loader.loadImage("/player_sheet.png");
		block_sheet = loader.loadImage("/block_sheet.png");
		background = loader.loadImage("/background.png");
		
		AudioPlayer.init();
		
		AudioPlayer.getMusic("soundtrack").loop();
		
		//font loading
		try{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/peach_milk.TTF")));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		tex = new Texture(this);
		controller = new Controller(tex);
		time = new Time(controller);
		cam = new Camera(0, 0);
		menu = new Menu(tex, controller);
		
		this.addKeyListener(new KeyInput(controller, menu, time, this));
		
		controller.generateLevel(chunk_size, chunk_length);
		
		/*
		for(int xx = 0; xx < getWidth() * 3; xx += 32)
			controller.addObject(new Base(xx, getHeight() - 32, tex, ObjectId.Moon_Top));
		
		for(int xx = (32 * 5); xx < getWidth() + 32; xx += 32)
			controller.addObject(new Base(xx, (32 * 7), tex, ObjectId.Moon_Top));
		*/
		
		//controller.addObject(new Player(100, 100, tex, ObjectId.Player, time, controller));
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this, "Game Thread");
		thread.start();
	}
	
	public void run()
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 90.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("TICKS: " + updates + " FPS: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()
	{
		controller.tick();
		
		if(state == STATE.Game || state == STATE.Store || state == STATE.Counter)
		{
			
			for(int i = 0; i < controller.object.size(); i++)
			{
				GameObject object = controller.object.get(i);
				
				if(object.getId() == ObjectId.Player)
				{
					if(object.getX() > 320)
						cam.setX(object.getX() - (getWidth() / 2) + 16);
					else
						cam.setX(0);
				}
			}
		}

		if(Game.ALPHA > 0.9 && !fade)
		{
			
			if(!controller.isDead)
			{
				controller.object.clear();
				fade = true;
				
				controller.storeSelect = 0;
				controller.proceed = false;
				
				state = STATE.Counter;
				
				
				chunk_size++;
				
				//if(Time.LEVEL > 1)
				//	chunk_length -= 1;
				
				//if(chunk_length <= 0) chunk_length = 1;
				
				Time.LEVEL++;
				time.time = 9;
				time.timeMil = 9;
				controller.generateLevel(chunk_size, chunk_length);
				
				time.timer3.start();
				
				controller.addObject(new Player(100, 100, tex, ObjectId.Player, time, controller));
			}
			else
			{
				controller.speed = 5;
				controller.object.clear();
				controller.generateLevel(10, 10);	
				fade = true;
				state = STATE.Dead;
				controller.proceed = false;
				chunk_size = 5;
				chunk_length = 10;
			}
		}
		
		if(fade)
		{	
			if(Game.ALPHA > 0.01)
				Game.ALPHA -= 0.01f;
			else
			{
				fade = false;
			}
		}
		
		if(state == STATE.Menu)
		{
			menu.tick();
		}
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(49, 72, 122));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(state == STATE.Game || state == STATE.Store || state == STATE.Counter)
		{
			g2d.translate(-cam.getX(), -cam.getY());
			
			
		}
		
		
		//drawing background
		for(int xx = 0; xx < (639 * 15); xx += 639)
				g.drawImage(background, xx, 0, this);
		
		try{
			if(controller.object.size() >= 1)
			controller.render(g);
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			sw.toString(); // stack trace as a string
			
			//JOptionPane.showMessageDialog(null, "Looks like you got an error :/ please tell me the error in the comments \n" + sw + "\n RESART THE GAME AND KEEP PLAYING :D", "SATYR RUN ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
		
		if(state == STATE.Game || state == STATE.Store || state == STATE.Counter)
		{
			
			g2d.translate(cam.getX(), cam.getY());
			
			time.render(g, tex);
			
			g2d.translate(-cam.getX(), -cam.getY());
			
			if(Game.ALPHA < 0.1)
			{
				for(int i = 0; i < controller.object.size(); i++)
				{
					if(controller.object.get(i).getId() == ObjectId.Player)
					{
						Point2D center = new Point2D.Float(controller.object.get(i).getX() - 32, controller.object.get(i).getY() + 64);
						float[] dist = {0.0f, 1.0f};
						Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(COLOR_RED, COLOR_GREEN, COLOR_BLUE)};
						RadialGradientPaint p = new RadialGradientPaint(center, 700, dist, colors);
						g2d.setPaint(p);
						g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .95f));
						g2d.fillRect(cam.getX(), cam.getY(), 800, 800);
						g2d.dispose();
					}
					
				}
			}
				
			
			
			g2d.translate(cam.getX(), cam.getY());
			
			g2d.setComposite(makeTransparent(ALPHA));
			
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g2d.setComposite(makeTransparent(1));
			
		}
		
		if(state == STATE.Options)
		{
			
			g.setColor(Color.red);
			g.setFont(new Font("Peach Milk", 0, 120));
			g.drawString("Satyr Run", 130, 100);
			
			
			g.setColor(Color.yellow);
			g.setFont(new Font("Peach Milk", 0, 50));
			g.drawString("You're not turning off the music...", 40, 200);
			
			g.setFont(new Font("Peach Milk", 0, 30));
			g.drawString("(Made by zack berenger LD27)", 170, 240);
			
			g.setColor(Color.red);
			g.setFont(new Font("Peach Milk", 0, 30));
			g.drawString("The only option you get is to send me gifts in the mail", 40, 280);
			
			g.setColor(Color.white);
			g.setFont(new Font("Peach Milk", 0, 30));
			g.drawString("Address: 105 Saytr Str, Black Forest County", 40, 320);
			g.drawString("Zip: 333666", 40, 360);
			
			g.setColor(Color.yellow);
			g.setFont(new Font("Peach Milk", 0, 20));
			g.drawString("Special thanks to the subscribers of realtutsgml", 190, 400);
			
			g.setColor(Color.red);
			g.setFont(new Font("Peach Milk", 0, 50));
			g.drawString("Press \"backspace\" key to return", 50, 460);
			
			
			
		}
		
		if(state == STATE.Menu)
		{
			
			menu.render(g);
		}
		
		
		if(state == STATE.Dead)
		{
			
			g.setColor(Color.red);
			g.setFont(new Font("Peach Milk", 0, 120));
			g.drawString("YOU FAILED", 125, 100);

			g.drawImage(tex.player[20], -170, -500, (32 * 40), (32 * 36), this);
			
			
			g.setFont(new Font("Peach Milk", 0, 40));
			g.drawString("You only made it to stage " + Time.LEVEL + "!!!", 130, 400);
			
			g.setFont(new Font("Peach Milk", 0, 50));
			g.drawString("Press the \"Enter\" key to try again!", 30, 460);
		
		}
		
		
		g.dispose();
		bs.show();
	}
	
	
	public BufferedImage getPlayerSheet()
	{
		return player_sheet;
	}
	
	public BufferedImage getBlockSheet()
	{
		return block_sheet;
	}
	
	public static void main(String args[])
	{
		new Window(640, 480, "LD27 | Satyr Run!", new Game());
	}

	public int getChunk_size() {
		return chunk_size;
	}

	public int getChunk_length() {
		return chunk_length;
	}
}
