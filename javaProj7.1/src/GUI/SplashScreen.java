
package GUI;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class SplashScreen extends JPanel {
    public SplashScreen(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Dice Battle Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> frame.showPanel("menu"));

        add(title, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }
}
