package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleU1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleU2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleD1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleD2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleL1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleL2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleR1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/idleR2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }
}
