package firstgame.entity.Levels;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import firstgame.entity.Player;

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

public class LevelHandler {
    private int tileSize;
    private int[][] grid;
    private int numRows;
    private int numCols;
    private Image grass;
    
    public LevelHandler(String filename) {
        try {
            // Load the map data from a JSON file
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();
    
            // Parse the JSON data
            JSONObject map = new JSONObject(json.toString());
            tileSize = map.getInt("tileSize");
            JSONArray gridArray = map.getJSONArray("tiles");
            numRows = gridArray.length();
            numCols = gridArray.getJSONArray(0).length();
            grid = new int[numRows][numCols];
    
            // Convert the JSON array to a 2D integer array
            for (int i = 0; i < numRows; i++) {
                JSONArray rowArray = gridArray.getJSONArray(i);
                for (int j = 0; j < numCols; j++) {
                    grid[i][j] = rowArray.getInt(j);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the image file
    try {
        grass = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_11.png")); // replace with your image path
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    

    
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int tileType = grid[i][j];
                if (tileType == 1) {
                    // Draw a solid tile
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                } else if (tileType == 2) {
                    g.setColor(Color.PINK);
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                } else if (tileType == 14) {
                    g.drawImage(grass, j * tileSize, i * tileSize, tileSize, tileSize, null);
                } else if (tileType == 19) {
                    g.setColor(Color.RED);
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                } else if (tileType == 3) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                } else if (tileType == 5) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                }
            }
        }
    }
}