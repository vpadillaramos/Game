package com.vpr.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{
	//Constantes
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH/12*9;
	
	//Atributos
	private static final long serialVersionUID = -5601790740681108114L;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Random r;
	
	//Constructor
	public Game() {
		new Window(WIDTH, HEIGHT, "Game", this);
		
		handler = new Handler();
		
		handler.addObject(new Player(100,100,ID.Player)); //añado un objeto Player
	}
	
	//Metodos
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//Atributos
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		//Cuerpo
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3); //numero de buffers que se crean
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//pantalla
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	//******MAIN*****
	public static void main(String[] args) {
		new Game();
	}
}
