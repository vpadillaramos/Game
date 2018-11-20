package com.vpr.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5215217660411836748L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame f = new JFrame(title);
		
		//tama�o ventana, maximo y minimo
		f.setPreferredSize(new Dimension(width, height));
		f.setMaximumSize(new Dimension(width, height));
		f.setMinimumSize(new Dimension(width, height));
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.add(game);
		f.setVisible(true);
		game.start();
		
		
	}
}
