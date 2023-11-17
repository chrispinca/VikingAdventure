package com.viking_game.Items;

import com.viking_game.Entity.Player;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {
    private int x, y, width, height;
    private BufferedImage[] itemFrames;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void onPickup(Player player) {

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
