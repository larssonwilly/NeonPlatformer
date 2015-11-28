package com.larssonwilly.neon.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.larssonwilly.neon.framework.KeyInput;
import com.larssonwilly.neon.framework.ObjectId;
import com.larssonwilly.neon.framework.Texture;
import com.larssonwilly.neon.objects.Block;
import com.larssonwilly.neon.objects.Player;


public class Game extends Canvas implements Runnable	{

	private static final long serialVersionUID = 1L;
	public static int HEIGHT, WIDTH;

	private BufferedImage level = null;
			
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	Camera cam;
	Random r = new Random();
	static Texture tex;
	
	public synchronized void start()	{
		if(running)	return;
		
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void init()	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		handler = new Handler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); //load level
		loadImageLevel(level);
		
		
		
		cam = new Camera(0, 0);
		
		/*handler.addObjects(41, ObjectId.Block, 0, Game.HEIGHT - 32, true);
		handler.addObjects(10, ObjectId.Block, Game.WIDTH/3, Game.HEIGHT - 200, true);
		handler.addObjects(35, ObjectId.Block, 0, 0, false);
		handler.addObject(new Player(200, 100, ObjectId.Player, handler));
		*/
		this.addKeyListener(new KeyInput(handler));
		setFocusable(true);
		requestFocus();
		
	}
	
	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
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
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	public void tick()	{
		handler.tick();
		
		for(int i = 0; i < handler.object.size(); i++)	{
			if(handler.object.get(i).getId() == ObjectId.Player)	{
				cam.tick(handler.object.get(i));
			}
		}
		
	}
	
	public void render()	{
		BufferStrategy bs = this.getBufferStrategy(); //canvas method
		if(bs == null)	{ //only create bs first time
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)	g; // for camera
		/////////////////////////////////////

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY());//begin of cam
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());//end of cam
		
		///////////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	
	public static int boundary(int var, int max, int min)	{
		if(var > max) var = max;
		if(var < min) var = min;
		
		return var;
	}
	
	private void loadImageLevel(BufferedImage image)	{
		int h = image.getHeight();
		int w = image.getWidth();
		int red, green, blue;
		
		for(int xx = 0; xx < h; xx++)	{
			
			for(int yy = 0; yy < w; yy++)	{
				int pixel = image.getRGB(xx, yy);
				red = (pixel >> 16)	& 0xff;
				green = (pixel >> 8) & 0xff;
				blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255)	{
					handler.addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
				}
				if(red == 0 && green == 0 && blue == 255)	{
					handler.addObject(new Player(xx*32, yy*32, ObjectId.Player, handler));
				}
			}
			
		}
	}
	
	public static Texture getInstance()	{
		return tex;
	}
	
	public static void main(String[] args) {
		new Window(800, 600, "Neon Platform Game Prototype", new Game());

	}

}
























