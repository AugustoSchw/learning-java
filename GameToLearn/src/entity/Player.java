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

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        speed = 3;
        x = 100;
        y = 100;
        direction = "down";
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
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

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleU1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingU1.png"));
            up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingU2.png"));
            up4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingU3.png"));


            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleD1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingD1.png"));
            down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingD2.png"));
            down4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingD3.png"));

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleL1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingL1.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingL2.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingL3.png"));

            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleR1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingR1.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingR2.png"));
            right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walkingR3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
                else if (spriteNum == 3) {
                    image = up3;
                }
                else if (spriteNum == 4) {
                    image = up4;
                }

                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                else if (spriteNum == 3) {
                    image = down3;
                }
                else if (spriteNum == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                else if (spriteNum == 3) {
                    image = left3;
                }
                else if (spriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
                else if (spriteNum == 3) {
                    image = right3;
                }
                else if (spriteNum == 4) {
                    image = right4;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }
}
