package firstgame.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstgame.GameFramework.GamePanel;
import firstgame.GameFramework.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    private BufferedImage[] walkRightFrames;
    private BufferedImage[] walkLeftFrames;
    private BufferedImage[] idleFrames;
    private BufferedImage[] currentSprites;
    private int length = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "idle";
    }

    public void getPlayerImage() {
        try {
            walkRightFrames = new BufferedImage[] {
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk1.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk2.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk3.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk4.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk5.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk6.png"))
            };

            walkLeftFrames = new BufferedImage[] {
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walkLeft1.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walkLeft2.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walkLeft3.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walkLeft4.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walkLeft5.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Walk/walkLeft6.png"))
            };

            idleFrames = new BufferedImage[] {
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle1.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle2.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle3.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle4.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle5.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle6.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle7.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle8.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle9.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle10.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle11.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/idle/idle12.png"))
            };

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        } else {
            direction = "idle";
        }

        //Set sprite array to current direction
        if (direction.equals("right")) {
            if (spriteNum > 6) spriteNum = 1;
            currentSprites = walkRightFrames;
            length = walkRightFrames.length;
        } else if (direction.equals("left")) {
            if (spriteNum > 6) spriteNum = 1;
            currentSprites = walkLeftFrames;
            length = walkLeftFrames.length;
        } else if (direction.equals("idle")) {
            currentSprites = idleFrames;
            length = idleFrames.length;
        }

        spriteCounter++;
        if (spriteCounter > 8) {
            spriteNum = (spriteNum % length) + 1;
            spriteCounter = 0;
        }
            } 

    public void draw(Graphics2D g2) {
       BufferedImage image = currentSprites[spriteNum - 1];
       g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
