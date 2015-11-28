package com.ludum.src.window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window extends Canvas
{
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game)
	{
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		ImageIcon icon = new ImageIcon("res/icon.png");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.setVisible(true);
		game.start();
	}
	
}
