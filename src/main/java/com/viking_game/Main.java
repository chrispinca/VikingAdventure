package com.viking_game;

import com.viking_game.GameFramework.GamePanel;
import com.viking_game.UI.Screen;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread();
        Screen.createScreen(gamePanel);
    }
} 
 