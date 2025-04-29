package game;

import java.awt.Color;
import java.awt.Graphics;

public class BonusPointPowerUp extends PowerUp {

    private final int bonusAmount = 10;

    public BonusPointPowerUp(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, size, size);
    }

    @Override
    public void applyEffect(GamePanel panel) {
        panel.addScore(bonusAmount);
    }
}

