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
        JFrame screentest = new JFrame("Test Window");
        screentest.setSize(1200,800);
        screentest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screentest.setVisible(true);
    }
}
