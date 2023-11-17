package com.viking_game.Items;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.viking_game.GameFramework.GamePanel;

public class Coin extends Item {
    private BufferedImage[] coinFrames;
    public int spriteCounter = 0;
    public static int spriteNum;
    public int length;
    GamePanel gp;

    public Coin(int x, int y) {
        super(x, y);
        spriteNum = 1;
        spriteCounter = 0;
        loadCoinImage();
    }

    public void loadCoinImage() {
        try {
            coinFrames = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_21.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_22.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_23.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_24.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_25.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_26.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_27.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_28.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_29.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Items/Coin/Gold_30.png"))
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void coinAnimationLoop() {
        length = coinFrames.length;
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum % length) + 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = coinFrames[spriteNum - 1];
        int newWidth = coinFrames[spriteNum - 1].getWidth() / 20;
        int newHeight = coinFrames[spriteNum - 1].getHeight() / 20;
        g2.drawImage(image, getX(), getY(), newWidth, newHeight, null);
        int circleRadius = 15; // Adjust the radius as needed
        g2.drawOval(100, 354, circleRadius * 2, circleRadius * 2);
    }

}
