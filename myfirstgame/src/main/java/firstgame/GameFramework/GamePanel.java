package firstgame.GameFramework;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import firstgame.entity.Player;
import firstgame.entity.Levels.LevelHandler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;


public class GamePanel extends JPanel implements Runnable{

    //Setting the Screen settings
    final int initialTileSize = 32;
    final int scale = 3; //scale for TileSize

    public final int tileSize = initialTileSize * scale; //48x48 actual tile size on screen
    final int maxScreenCol = 15;
    final int maxScreenRow = 10;
    final int screenWidth = tileSize * maxScreenCol; 
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    //Set default player position and speed
    float playerX = 100;
    float playerY = 100;
    int playerSpeed = 4;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() { 
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { //this will update and repaint the game in its current state

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("/Background/Background_01.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);

        LevelHandler level = new LevelHandler("C:/Users/Chris/Desktop/personalgameproj/myfirstgame/src/main/resources/player/Walk/maptest.json");

        level.draw(g2);
        player.draw(g2);
    
        g2.dispose();
    }
    
}