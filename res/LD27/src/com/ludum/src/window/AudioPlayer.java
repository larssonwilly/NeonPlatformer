package com.ludum.src.window;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer 
{
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void init()
	{
		try{
			soundMap.put("pop", new Sound("res/Pickup_Coin.wav"));
			soundMap.put("woah", new Sound("res/woah.ogg"));
			soundMap.put("doot", new Sound("res/doot.ogg"));
			soundMap.put("deet", new Sound("res/deet.ogg"));
			soundMap.put("ching", new Sound("res/ching.ogg"));
			soundMap.put("ching2", new Sound("res/ching2.ogg"));
			
			
			musicMap.put("soundtrack", new Music("res/soundtrack.ogg"));
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key){ 
		 return musicMap.get(key);
	}
	
	public static Sound getSound(String key){ 
		 return soundMap.get(key);
	}
	
}
