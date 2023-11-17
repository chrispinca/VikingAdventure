package com.viking_game.GameFramework;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.viking_game.Entity.Player;
import com.viking_game.Levels.LevelHandler;
import com.viking_game.Items.Coin;

import java.util.ArrayList;
import java.util.List;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    // Setting the Screen settings
    final int initialTileSize = 32;
    public final int scale = 3; // scale for TileSize

    private List<Coin> coins = new ArrayList<>();

    public final int tileSize = initialTileSize * scale;
    final int maxScreenCol = 10;
    final int maxScreenRow = 7;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // Items
    Coin coin;

    // Create array to store the levels and an int to track current level
    String Level[] = { "/maptest.json", "/level2.json" };
    int currentLevel = 0;

    // FPS
    int FPS = 60;

    // Set default player position and speed
    float playerX = 100;
    float playerY = 100;
    int playerSpeed = 4;

    InputHandler keyH = new InputHandler();
    Thread gameThread;

    public LevelHandler level = new LevelHandler(Level[currentLevel]);

    Player player = new Player(this, keyH);
    public CollisionHandler checkCollision = new CollisionHandler(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(keyH);
        this.setFocusable(true);
        initializeCoins();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() { // this will update and repaint the game in its current state

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
        updateCoins();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*
         * Image backgroundImage = null;
         * try {
         * backgroundImage =
         * ImageIO.read(getClass().getResourceAsStream("/Background/Background_01.png"))
         * ;
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */

        Graphics2D g2 = (Graphics2D) g;

        // g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);

        level.draw(g2);
        player.draw(g2);
        drawCoins(g2);

        g2.dispose();
    }

    public void nextLevel() {
        currentLevel++;
        if (currentLevel < Level.length) {
            level = new LevelHandler(Level[currentLevel]);
            player.setDefaultValues();
        } else {
            currentLevel = 0;
            level = new LevelHandler(Level[currentLevel]);
        }
    }

    private void initializeCoins() {
        coins.add(new Coin(100, 354));
    }

    private void updateCoins() {
        for (Coin coin : coins) {
            coin.coinAnimationLoop();
        }
    }

    private void drawCoins(Graphics2D g2) {
        for (Coin coin : coins) {
            coin.draw(g2);
        }
    }

}
