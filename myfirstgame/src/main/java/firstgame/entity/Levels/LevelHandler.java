package firstgame.entity.Levels;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.BufferedReader;
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
    protected Tile[] tile;
    
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
        tile = new Tile[10];
        getTileImage();
    }
    

    public void getTileImage() {
            // Load the image file
    try {

        tile[0] = new Tile(); //grass tile
        tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_11.png"));
        tile[0].collision = true;

        tile[1] = new Tile(); //Dirt + rock tile
        tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_02.png"));
        tile[1].collision = true;

        tile[2] = new Tile(); //Dirt tile
        tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_06.png"));
        tile[2].collision = true;

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
                    g.drawImage(tile[2].image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                } else if (tileType == 14) {
                    g.drawImage(tile[0].image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                } else if (tileType == 19) {
                    g.drawImage(tile[1].image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                } else if (tileType == 3) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                } else if (tileType == 0) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                }
            }
        }
    }
}