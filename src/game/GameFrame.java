package game;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
    public GameFrame() {
        this.setTitle("Space Shooter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new GamePanel());
        this.pack(); 
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
