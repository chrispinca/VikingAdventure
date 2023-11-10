package com.viking_game.Levels;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.viking_game.GameFramework.GamePanel;

public class Tile {
    BufferedImage image;
    public boolean collision;
    protected Rectangle tileHitbox;
    int tileNum1, tileNum2;

    public Tile(boolean collision) {
        this.collision = collision;
    }

    public boolean collision(GamePanel gp) {
        return collision;
    }

    public BufferedImage getImage() {
        return image;
    }

    protected void initTileHitbox(int x, int y, int width, int height) {
        tileHitbox = new Rectangle(x, y, width, height);
    }

    public void updateHitbox(int x, int y) {
        tileHitbox.x = x; 
        tileHitbox.y = y;
    }
    public Rectangle getHitbox() {
        return tileHitbox;
    }
    public int getHitboxX() {
        return tileHitbox.x;
    }
    
    public int getHitboxY() {
        return tileHitbox.y;
    }
    
    public int getHitboxWidth() {
        return tileHitbox.width;
    }
    
    public int getHitboxHeight() {
        return tileHitbox.height;
    }
}
