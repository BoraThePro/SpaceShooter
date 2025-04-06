package game;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Spaceship spaceship;
    private final List<Asteroid> asteroids = new ArrayList<>();
    private final Random random = new Random();
    private int spawnTimer = 0;
    private final int spawnDelay = 60; 
    private boolean gameOver = false;


    private Thread gameThread;

    public GamePanel() {
    	this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improves performance

        this.setFocusable(true);
        this.requestFocusInWindow(); // ensures key input focus
        spaceship = new Spaceship(380, 550); // center horizontally near bottom

        startGameThread();
    }

    private void startGameThread() {
        gameThread = new Thread(this); // 'this' implements Runnable
        gameThread.start(); // calls run()
    }

    @Override
    public void run() {
        // Game loop here - for now, just print
    	while (!gameOver) {
    	    updateGame();   // logic
    	    repaint();      // render
    	    try {
    	        Thread.sleep(1000 / 60); // ~60 FPS
    	    } catch (InterruptedException e) {
    	        e.printStackTrace();
    	    }
    	}
    }
    
    private void updateGame() {
        // Spawn new asteroid
        spawnTimer++;
        if (spawnTimer >= spawnDelay) {
            int x = random.nextInt(getWidth() - 30); // random horizontal position
            int speed = 2 + random.nextInt(3);       // speed: 2–4
            asteroids.add(new Asteroid(x, 0, speed));
            spawnTimer = 0;
        }

        // Update asteroid positions
        Iterator<Asteroid> iterator = asteroids.iterator();
        while (iterator.hasNext()) {
            Asteroid asteroid = iterator.next();
            asteroid.update();
            if (asteroid.getBounds().intersects(spaceship.getBounds())) {
                gameOver = true;
                gameThread.interrupt(); // stop the game loop
                break; // stop checking once we detect a hit
            }

            if (asteroid.isOffScreen(getHeight())) {
                iterator.remove(); // clean up off-screen asteroid
            }
        }
    }
    
    private void drawSpaceship(Graphics g) {
        spaceship.draw(g);
    }
    
    private void drawAsteroids(Graphics g) {
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(g);
        }
    }
    
    private void drawGameOverScreen(Graphics g) {
        // Dark overlay
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Main "Game Over" text
        g.setColor(Color.RED);
        g.setFont(g.getFont().deriveFont(36f));
        String text = "GAME OVER";
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(text, x, y);

        // Smaller restart text
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(18f));
        String restartText = "Press 'R' to Restart";
        FontMetrics restartMetrics = g.getFontMetrics();
        int restartX = (getWidth() - restartMetrics.stringWidth(restartText)) / 2;
        int restartY = y + 40; // slightly below "GAME OVER"
        g.drawString(restartText, restartX, restartY);
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSpaceship(g);
        drawAsteroids(g);

        if (gameOver) {
            drawGameOverScreen(g);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!gameOver) {
            if (key == KeyEvent.VK_LEFT) {
                spaceship.moveLeft();
            }
            if (key == KeyEvent.VK_RIGHT) {
                spaceship.moveRight();
            }
        } else {
            // Handle restart when game is over
            if (key == KeyEvent.VK_R) {
                restartGame();
            }
        }
    }
    
    private void restartGame() {
        // Reset spaceship
        spaceship = new Spaceship(380, 550);

        // Clear asteroids
        asteroids.clear();

        // Reset game state
        gameOver = false;
        spawnTimer = 0;

        // Start new game thread
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

