package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.getTileSize()/2);  // player stays at the center of screen
        screenY = gp.screenHeight/2 - (gp.getTileSize()/2) ;

        setDefaultValues();
        //initBuffers();
        getPlayerImage();
    }

    public void setDefaultValues() {
        speed = 3;
        worldX = gp.getTileSize() * 8;
        worldY = gp.getTileSize() * 6;
        direction = "down";
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            else {
                direction = "right";
                worldX += speed;
            }
    
            spriteCounter++;
            if (spriteCounter > 10) {   // to delay the change of animation
                if (spriteNum < 4) {    // 1 is idle. 2, 3 and 4 are different walking animations
                    spriteNum++;
                }
                else if (spriteNum == 4) {  // reached the last walking animation
                    spriteNum = 2;  // returns to the first, to become a loop
                }
                spriteCounter = 0;  // resets the "cooldown" counter
            }

        } else {    // no "wasd" key is pressed, it stays idle
            spriteNum = 1;  // to stay idle
        }
    }

    public void getPlayerImage() {
        try {
            up[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleU1.png"));
            down[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleD1.png"));
            left[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleL1.png"));
            right[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleR1.png"));

            for (int i = 1; i < 4; i++) {
                up[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingU" + i +".png"));
                down[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingD"+ i +".png"));
                left[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingL"+ i +".png"));
                right[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingR"+ i + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up[(spriteNum - 1)];
                break;
            case "down":
                image = down[(spriteNum - 1)];
                break;
            case "left":
                image = left[(spriteNum - 1)];
                break;
            case "right":
                image = right[(spriteNum - 1)];
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }
}
