package main;

import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { // Runnable so it keeps updating
    final int originalTileSize = 16;    // tile size = 16x16
    final int scale = 3;    // scale of tile size, so they look 3 times bigger
    final int tileSize = originalTileSize * scale;  // 48x48

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);


    public final int maxWorldCol = 51;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

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

        tileM.draw(g2); // before draw of player, else the background will cover the player
        player.draw(g2);
        g2.dispose();   // disposes and releases any resources it was using
    }

    public int getTileSize() {
        return tileSize;
    }
}
