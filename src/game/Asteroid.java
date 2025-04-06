package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Asteroid {
    private int x, y;
    private final int width = 30;
    private final int height = 30;
    private final int speed;

    public Asteroid(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update() {
        y += speed; // move down
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, width, height);
    }

    public boolean isOffScreen(int panelHeight) {
        return y > panelHeight;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

