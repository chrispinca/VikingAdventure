package main.java.firstgame.UI;
import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class Screen 
{
    public static void createScreen()
    {
        JFrame window = new JFrame();
        window.setSize(1200,800);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Game Screen");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
