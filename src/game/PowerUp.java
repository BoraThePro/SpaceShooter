package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class PowerUp {
    protected int x, y;
    protected final int size = 20;
    protected int speed = 2;

    public PowerUp(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update() {
        y += speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public boolean isOffScreen(int panelHeight) {
        return y > panelHeight;
    }

    public abstract void draw(Graphics g);
    public abstract void applyEffect(GamePanel panel);
}

