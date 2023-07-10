package firstgame;

import firstgame.GameFramework.GamePanel;
import firstgame.UI.Screen;

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
 