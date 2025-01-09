package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final int originalTileSize = 16;    // tile size = 16x16
    final int scale = 3;    // scale of tile size, so they look 3 times bigger
    final int tileSize = originalTileSize * scale;  // 48x48

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;


    public GamePanel() {    // constructor
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
    }
}
