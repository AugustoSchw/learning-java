package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { // Runnable so it keeps updating
    final int originalTileSize = 16;    // tile size = 16x16
    final int scale = 3;    // scale of tile size, so they look 3 times bigger
    final int tileSize = originalTileSize * scale;  // 48x48

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    int FPS = 60;


    public GamePanel() {    // constructor
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);    // so the game can be "focused" for key input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // for the game loop
        double fpsInterval = 1000000000d/FPS;   // interval of 0.016 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / fpsInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // Update info
                update();
                // Draw updated info
                repaint();  // calls paintComponent (GamePanel extends JPanel)
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {  // paintComponent is a standard JPanel method
        super.paintComponent(g);    // calls the paintComponent from JPanel
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);
        g2.dispose();   // disposes and releases any resources it was using
    }

    public int getTileSize() {
        return tileSize;
    }
}
