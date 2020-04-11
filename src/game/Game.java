package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import control.Keyboard;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private static volatile boolean working = false;

	private static final String NAME = "Game";

	private static int ups = 0;
	private static int fps = 0;

	private static JFrame window;
	private static Thread thread;
	private static Keyboard keyboard;

	public Game() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		keyboard = new Keyboard();
		addKeyListener(keyboard);

		window = new JFrame(NAME);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		Game Game = new Game();
		Game.start();
	}

	public synchronized void start() {
		working = true;

		thread = new Thread(this, "Graphics");
		thread.start();
	}

	private synchronized void stop() {
		working = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		keyboard.update();
		
		if(keyboard.up) {
			System.out.println("up");
		}
		
		if(keyboard.down) {
			System.out.println("down");
		}
		
		if(keyboard.left) {
			System.out.println("left");
		}
		
		if(keyboard.right) {
			System.out.println("right");
		}
		
		ups++;
	}

	private void shown() {
		fps++;
	}

	public void run() {
		final int NS_PER_SECOND = 1000000000;
		final byte UPS_OBJECTIVE = 60;
		final double NS_PER_UPDATE = NS_PER_SECOND / UPS_OBJECTIVE;

		long referenceUpdate = System.nanoTime();
		long referenceCounter = System.nanoTime();

		double timeElapse;
		double delta = 0;
		
		

		while (working) {
			final long startLoop = System.nanoTime();

			timeElapse = startLoop - referenceUpdate;
			referenceUpdate = startLoop;

			delta += timeElapse / NS_PER_UPDATE;

			while (delta >= 1) {
				update();
				delta--;
			}

			shown();
			if (System.nanoTime() - referenceCounter > NS_PER_SECOND) {
				window.setTitle(NAME + " || UPS: " + ups + " || FPS: " + fps);
				ups = 0;
				fps = 0;
				referenceCounter = System.nanoTime();
			}
		}
	}
}
