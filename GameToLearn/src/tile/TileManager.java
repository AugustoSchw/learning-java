package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNumber[] [];
    int overlayTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[1440]; // 10 types of tiles
        mapTileNumber = new int[gp.maxWorldCol] [gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world.txt", "world");
        overlayTileNumber = new int[gp.maxWorldCol] [gp.maxWorldRow];
        loadMap("/maps/overlay.txt", "overlay");
        // other loadMap calls can be made below, so there is a "depth"
    }

    public void getTileImage() {
        try {
            for (int i = 0; i < 1440; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tile_"+i+".png"));
            }

//            tile[0] = new Tile(); // tile[i] = new Tile()   -----   tile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tile_"+i+".png"));
//            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass1.png"));
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall1.png"));
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water1.png"));
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/dirt1.png"));
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand1.png"));
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sandBLeft.png"));
//            tile[6] = new Tile();
//            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sandBRight.png"));
//            tile[7] = new Tile();
//            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sandDown.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, String type) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    if (type == "world") {
                        mapTileNumber[col][row] = num;
                    } else if (type == "overlay") {
                        overlayTileNumber[col][row] = num;
                    }
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNumber[worldCol][worldRow];
            int overlayNum = overlayTileNumber[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.getTileSize() > gp.player.worldX - gp.player.screenX && worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&     // just draw the tiles in the player's screen
                    worldY + gp.getTileSize() > gp.player.worldY - gp.player.screenY && worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);  // background
                if (overlayNum != -1) { // -1 means there is no overlay in that tile
                    g2.drawImage(tile[overlayNum].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);   // overlay
                }
            }
            // fills the tile. First with the background (mapTileNumber), and then the overlay above it
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
