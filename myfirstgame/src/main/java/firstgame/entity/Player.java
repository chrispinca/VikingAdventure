package firstgame.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sql.rowset.CachedRowSet;

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

    // for jumping/gravity
    public float jumpGravity = 0.35f * 3 ;
    public float airspeed = 0f; 
    public float jumpSpeed = -2.5f * 4;
    public float fallSpeedAfterCollision = 0.1f;
    public boolean inAir = false;
    public int count = 0;
    public boolean jumpCheck = false;
    


    private boolean left, right, up, down;

    public int x;
    public int y;
    private int width, height;
    private Rectangle hitbox;
    private int hitbox_X_Offset;
    private int hitbox_Y_Offset;
    private int length = 0;
    private int maxLengthJump = 8;
    public int constantGravity = 5;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        //Initializes the hitbox, sets default player values and loads the player animation image arrays
        initHitbox(gp.level.tileSize - 10, gp.level.tileSize-5);
        setDefaultValues();
        loadPlayerImage();    
    }

    //Sets default player x and y coordinates, speed and animation 
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "idle";
    }

    //Loads the player images into an array responsible for player movement animation based on direction
    public void loadPlayerImage() {
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

    //Updates the player state
    public void update() {

        addGravity();
        handleInput();
        updateAnimation();
        checkCollision();
        movePlayer();
        checkCollision();
        gp.checkImage(null, width, height, gp);
        updateHitbox(x+21, y+55);
        animationLoop();
        checkCollision();
            } 

    //Handles the keyboard input and sets the direction based on the key pressed
    public void handleInput() {
        if (keyH.spacePressed && count < maxLengthJump) {
            direction = "jump";
        } else if (keyH.spacePressed && count < maxLengthJump && keyH.rightPressed) {
            direction = "jumpRight";
        } else if (keyH.spacePressed && count < maxLengthJump && keyH.leftPressed) {
            direction = "jumpLeft";
        } else if (keyH.downPressed && !keyH.upPressed) {
            direction = "down";
            
        } else if (keyH.leftPressed && !keyH.rightPressed ) {
            direction = "left";
            
        } else if (keyH.rightPressed && !keyH.leftPressed ) {
            direction = "right";
            
        } else if (keyH.upPressed && !keyH.downPressed) {
            direction = "up";
        }  else {
            direction = "idle";
        }
    }

    //Updates the player current animation and sets the length variable to the size of the specific direction animation array 
    public void updateAnimation() {
        //Set sprite array to current direction
        if (direction.equals("right")) {
            if (spriteNum > 6) spriteNum = 1;
            currentSprites = walkRightFrames;
            length = walkRightFrames.length;
        } else if (direction.equals("left")) {
            if (spriteNum > 6) spriteNum = 1;
            currentSprites = walkLeftFrames;
            length = walkLeftFrames.length;
        } else if (direction.equals("jump") || direction.equals("jumpRight") || direction.equals("jumpLeft") || !onGround) {
            if (spriteNum > 6) spriteNum = 1;
            currentSprites = jumpFrames;
            length = jumpFrames.length;
        } else if (direction.equals("idle")) {
            currentSprites = idleFrames;
            length = idleFrames.length;
        }
    }

    //Moves player on screen based on x and y coordinates if no collision exists 
    public void movePlayer() {
        
        if(collisionOn == false) {
            switch(direction) {
                case "right":
                    x += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "jump":
    
                    airspeed = jumpSpeed;
                    y += airspeed;
                    airspeed += jumpGravity;
                    count++;

                    if (count > maxLengthJump) {
                        keyH.spacePressed = false;
                        jumpCheck = false;
                        count = 0;
                    }
                    break;
                case "jumpRight":
                    airspeed = jumpSpeed;
                    y += airspeed;
                    airspeed += jumpGravity;

                    x += speed;

                    count++;

                    if (count > maxLengthJump) {
                        keyH.spacePressed = false;
                        jumpCheck = false;
                        count = 0;
                    }
                    break;
                case "jumpLeft":
                    airspeed = jumpSpeed;
                    y += airspeed;
                    airspeed += jumpGravity;

                    x -= speed;

                    count++;

                    if (count > maxLengthJump) {
                        keyH.spacePressed = false;
                        jumpCheck = false;
                        count = 0;
                    }
                    break;
            }
            
            
         } 
    }

    //Checks for collision
    public void checkCollision() {
        collisionOn = false;
        gp.checkCollision.checkTile(this, gp.level);
        gp.checkCollision.checkGround(this, gp.level);
    }

    //Loop for animations to play based on size of the specified direction animation movement array
    public void animationLoop() {
        spriteCounter++;
        if (spriteCounter > 8) {
            spriteNum = (spriteNum % length) + 1;
            spriteCounter = 0;
        }
    }

    public void addGravity() {
        if(!onGround) {
            y += constantGravity;
        } else {
            count = 0;
        }
        
       
    }

    //draws the player and animation images on the screen
    public void draw(Graphics2D g2) {
       BufferedImage image = currentSprites[spriteNum - 1];
       g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

       // Draw a green rectangle below the player when onGround is true
    if (onGround) {
        g2.setColor(Color.GREEN);
        int rectWidth = gp.tileSize;
        int rectHeight = 5;
        g2.fillRect(x, y + gp.tileSize, rectWidth, rectHeight);
    }
    
       
       g2.setColor(Color.BLACK);
       //best fit for the player character sprite hitbox to be implemented
       drawHitbox(g2);
       //g2.drawRect((int) (x+21),(int) (y+55), gp.tileSize/3 - 10, gp.tileSize/3-5);
    }

    //Gets the player's x coordinate
    public int getPlayerX() {
        return x;
    }

    //Gets the player's y coordinate
    public int getPlayerY() {
        return y;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void getImage(String imageFile) {
    try {
        ImageIO.read(getClass().getResourceAsStream(imageFile));
    }catch(IOException e) {
        e.printStackTrace();
    }
    }
    
}