package com.vpr.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	
	//Constructor
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 1;
	}
	
	//Metodos
	@Override
	public void tick() {
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
	}
	
}
