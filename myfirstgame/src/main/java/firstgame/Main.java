package firstgame;
import javax.swing.JFrame;
import main.java.firstgame.UI.Screen;

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
    }
}
