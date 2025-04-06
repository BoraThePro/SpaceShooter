package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spaceship {

    private int x, y;
    private final int width = 40;
    private final int height = 20;
    private final int speed = 10;

    public Spaceship(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

