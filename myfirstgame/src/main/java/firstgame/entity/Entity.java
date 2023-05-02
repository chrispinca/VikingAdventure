package firstgame.entity;

import java.awt.image.BufferedImage;

//values belonging to any object of type Entity
public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage walk1, walk2, walk3, walk4, walk5, walk6, 
    walkLeft1, walkLeft2, walkLeft3, walkLeft4, walkLeft5, walkLeft6, 
    idle1, idle2, idle3, idle4, idle5, idle6, idle7, idle8, idle9, idle10, idle11, idle12;

    public String direction;

    public int spriteCounter = 0;
    public static int spriteNum = 1;
}
