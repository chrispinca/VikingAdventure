package firstgame.main;

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
        Screen.createScreen(gamePanel);
        gamePanel.startGameThread();
    }
}
