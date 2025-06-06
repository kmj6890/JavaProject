
package main;

import javax.swing.SwingUtilities;
import GUI.*;

public class GameMain {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
