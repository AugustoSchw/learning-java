package main;

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

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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
        double nextFpsTime = System.nanoTime() + fpsInterval;

        while (gameThread != null) {
            // Update info
            update();
            // Draw updated info
            repaint();  // calls paintComponent (GamePanel extends JPanel)

            try {
                double remainingTime = nextFpsTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // so it keeps the "refresh rate" of the game in 60 fps
                nextFpsTime += fpsInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {  // paintComponent is a standard JPanel method
        super.paintComponent(g);    // calls the paintComponent from JPanel
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();   // disposes and releases any resources it was using
    }
}
