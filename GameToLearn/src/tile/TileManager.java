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
        mapTileNumber = new int[gp.maxScreenCol] [gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/world.txt", "world");
        overlayTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
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

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    if (type == "world") {
                        mapTileNumber[col][row] = num;
                    } else if (type == "overlay") {
                        overlayTileNumber[col][row] = num;
                    }
                    col++;
                }
                if (col == gp.maxScreenCol) {
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
        int col = 0, row = 0, x = 0, y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNumber[col][row];
            int overlayNum = overlayTileNumber[col][row];

            // fills the tile. First with the background (mapTileNumber), and then the overlay above it
            g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);  // background
            if (overlayNum != -1) { // -1 means there is no overlay in that tile
                g2.drawImage(tile[overlayNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);   // overlay
            }
            col++;

            x += gp.getTileSize();

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
    }
}
