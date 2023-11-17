package com.viking_game.Levels;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.viking_game.Levels.Tile;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;

public class LevelHandler {
    public int tileSize;
    public int[][] grid;
    public Tile[][] tileMap;
    private int numRows;
    private int numCols;
    protected Tile[] tile;

    public LevelHandler(String filename) {
        tile = new Tile[10];
        loadGrid(filename);
        getTileImage();
        loadTileMap();
    }

    // Loads the json map grid into a 2D array of integers
    public void loadGrid(String filename) {
        try {
            // Load the map data from a JSON file
            InputStream inputStream = getClass().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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
            tileMap = new Tile[numRows][numCols];

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
    }

    public void getTileImage() {
        // Load the image file for each tile and set collision value
        try {

            tile[0] = new Tile(false); // grass tile
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_11.png"));
            tile[0].collision = false;

            tile[1] = new Tile(true); // Dirt + rock tile
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_02.png"));
            tile[1].collision = true;

            tile[2] = new Tile(true); // Dirt tile
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_06.png"));
            tile[2].collision = true;

            tile[3] = new Tile(true); // Dirt tile
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Platformer/Ground_06.png"));
            tile[3].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads a 2D array of tiles for collision checking and creates hitboxes for
    // each one,
    public void loadTileMap() {
        // Populate tileMap and set each tile to a particular type of tile
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int x = numCols * tileSize;
                int y = numRows * tileSize;
                int tileType = grid[i][j];
                if (tileType == 1) {
                    tileMap[i][j] = tile[1];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                } else if (tileType == 2) {
                    tileMap[i][j] = tile[2];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                } else if (tileType == 14) {
                    tileMap[i][j] = tile[1];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                } else if (tileType == 19) {
                    tileMap[i][j] = tile[1];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                } else if (tileType == 3) {
                    tileMap[i][j] = tile[3];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                } else if (tileType == 0) {
                    tileMap[i][j] = tile[0];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                } else {
                    tileMap[i][j] = tile[1];
                    tileMap[i][j].initTileHitbox(x, y, tileSize, tileSize);
                }
            }
        }
    }

    public int getTileSize() {
        return tileSize;
    }

    // draws the tiles based on the number and location in the grid
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
        /* 
        // testing for hitboxes
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (tileMap[row][col].collision == true) {
                    int x = col * tileSize;
                    int y = row * tileSize;
                    g.setColor(Color.GREEN);
                    g.drawRect(x, y, tileSize, tileSize);
                }
            }
        } */
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getTileType(int row, int col) {
        return grid[row][col];
    }
}