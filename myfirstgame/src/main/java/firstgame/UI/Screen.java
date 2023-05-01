package firstgame.UI;
import java.awt.Component;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class Screen 
{
    public static void createScreen(Component _panel)
    {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Game Screen");
        
        window.add(_panel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
