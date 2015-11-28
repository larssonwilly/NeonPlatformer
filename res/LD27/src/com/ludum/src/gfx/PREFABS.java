package com.ludum.src.gfx;

import com.ludum.src.interfaces.ObjectId;
import com.ludum.src.interfaces.Texture;
import com.ludum.src.objects.Base;
import com.ludum.src.objects.Lava;
import com.ludum.src.window.Controller;

public class PREFABS 
{
	
	public static void generateStairs(int xx, int level, Texture tex, Controller controller)
	{
		
		//stairs left
		controller.addObject(new Base(xx + 64, (level - 96), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 32, (level - 64), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx, (level - 32), tex, ObjectId.Moon_Top));
		
		//left pillar
		controller.addObject(new Base(xx + 96, (level - 96), tex, ObjectId.Moon_Top));
		
		//lava
		for(int xxx = xx + 128; xxx < xx + 256; xxx += 32)
			controller.addObject(new Lava(xxx, (level - 32), tex, ObjectId.Lava));
		
		//stairs right
		controller.addObject(new Base(xx + 256, (level - 96), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 288, (level - 64), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 320, (level - 32), tex, ObjectId.Moon_Top));
		
		//right pillar
		controller.addObject(new Base(xx + 224, (level - 96), tex, ObjectId.Moon_Top));

	}
	
	public static void generatePillar(int xx, int level, Texture tex, Controller controller)
	{
		
		//stairs left
		controller.addObject(new Base(xx + 64, (level - 64), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 32, (level - 64), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx, (level - 32), tex, ObjectId.Moon_Top));
		
		//lava left
		for(int xxx = xx + 96; xxx < xx + 288; xxx += 32)
				controller.addObject(new Lava(xxx, level, tex, ObjectId.Lava));
		
		//middle pillar
		controller.addObject(new Base(xx + 288, (level - 96), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 320, (level - 96), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 352, (level - 96), tex, ObjectId.Moon_Top));
				
		//lava left
		for(int xxx = xx + 384; xxx < xx + 576; xxx += 32)
				controller.addObject(new Lava(xxx, level, tex, ObjectId.Lava));
		
		//stairs right
		controller.addObject(new Base(xx + 576, (level - 64), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 608, (level - 64), tex, ObjectId.Moon_Top));
		controller.addObject(new Base(xx + 640, (level - 32), tex, ObjectId.Moon_Top));
		
	}
	
	
}
