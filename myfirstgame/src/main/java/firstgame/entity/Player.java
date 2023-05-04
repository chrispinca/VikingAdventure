package firstgame.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstgame.GameFramework.GamePanel;
import firstgame.GameFramework.KeyHandler;

import java.awt.Rectangle;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    private BufferedImage[] walkRightFrames;
    private BufferedImage[] walkLeftFrames;
    private BufferedImage[] idleFrames;
    private BufferedImage[] jumpFrames;
    private BufferedImage[] highJumpFrames;
    private BufferedImage[] currentSprites;

    private boolean isJumping = false;
    private int jumpHeight = 50;
    private int jumpDuration = 30;
    private int jumpCounter = 0;

    private float x, y;
    private int width, height;
    private Rectangle hitbox;

    private int length = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();

        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle();

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

            jumpFrames = new BufferedImage[] {
                ImageIO.read(getClass().getResourceAsStream("/player/Jump/jump1.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Jump/jump2.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Jump/jump3.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Jump/jump4.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Jump/jump5.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/Jump/jump6.png"))
            };

            highJumpFrames = new BufferedImage[] {
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump1.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump2.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump3.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump4.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump5.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump6.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump7.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump8.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump9.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump10.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump11.png")),
                ImageIO.read(getClass().getResourceAsStream("/player/High_Jump/high_jump12.png"))
            };

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void jump() {
        if(!isJumping) {
            isJumping = true;
            jumpCounter = 0;
        }
    }

    public void update() {

        updateHitbox();

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
        } else if (keyH.spacePressed == true) {
            direction = "jump";
            if (isJumping) {
                y -= jumpHeight / jumpDuration * jumpCounter * (jumpCounter - jumpDuration);
                jumpCounter++;

                if (jumpCounter >= jumpDuration) {
                    isJumping = false;
                }

            }
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
        } else if (direction.equals("jump")) {
            if (spriteNum > 6) spriteNum = 1;
            currentSprites = jumpFrames;
            length = jumpFrames.length;
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
       g2.drawImage(image, (int)x,(int) y, gp.tileSize, gp.tileSize, null);
       drawHitbox(g2);

    }
}
