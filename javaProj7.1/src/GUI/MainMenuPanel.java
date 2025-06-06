
package GUI;

import model.*;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
    private JComboBox<String> classBox;
    private JTextField nameField;
    private JButton startButton;
    private JLabel previewLabel;
    private Image backgroundImage;

    public MainMenuPanel(MainFrame frame) {
        setLayout(null);
        backgroundImage = new ImageIcon("img/Background 5 (Bonus).png").getImage(); // 경로는 실제 배경 이미지 파일명에 맞게 수정

        JLabel titleLabel = new JLabel("Choose your type");
        titleLabel.setBounds(120, 30, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel);

        classBox = new JComboBox<>(new String[]{"Attacker", "Defender", "Critical"});
        classBox.setBounds(80, 100, 100, 30);
        add(classBox);

        startButton = new JButton("Start Battle");
        startButton.setBounds(190, 70, 120, 30);
        add(startButton);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setBounds(80, 120, 150, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(190, 120, 120, 30);
        add(nameField);

        previewLabel = new JLabel();
        previewLabel.setBounds(120, 330, 200, 200);
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setText("<html><img src='file:img/Attacker.gif' width='150' height='150'></html>");
        
        // 색
        Color orange = new Color(255, 128, 0);
        titleLabel.setForeground(orange);
        nameLabel.setForeground(orange);
        
        

        add(previewLabel);

        // Hover에 따라 이미지 바꾸기
        classBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                JList<?> list = getPopupList(classBox);
                if (list != null) {
                    list.addMouseMotionListener(new MouseMotionAdapter() {
                        public void mouseMoved(MouseEvent e) {
                            int index = list.locationToIndex(e.getPoint());
                            if (index >= 0) {
                                String item = (String) list.getModel().getElementAt(index);
               
                                switch (item) {
                                    case "Attacker" -> previewLabel.setText("<html><img src='file:img/Attacker.gif' width='150' height='150'></html>");
                                    case "Defender" -> previewLabel.setText("<html><img src='file:img/Defender.gif' width='150' height='150'></html>");
                                    case "Critical" -> previewLabel.setText("<html><img src='file:img/Critical.gif' width='150' height='150'></html>");
                                }
                            }
                        }
                    });
                }
            }

            @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            @Override public void popupMenuCanceled(PopupMenuEvent e) {}
        });

        startButton.addActionListener(e -> {
            String selected = (String) classBox.getSelectedItem();
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name!");
                return;
            }

            Player player;
            switch (selected) {
                case "Defender" -> player = new DefenderPlayer(name);
                case "Critical" -> player = new CriticalPlayer(name);
                default -> player = new AttackerPlayer(name);
            }
            player.initstats();
            frame.startBattle(player);
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private JList<?> getPopupList(JComboBox<?> comboBox) {
        Object comp = comboBox.getUI().getAccessibleChild(comboBox, 0);
        if (comp instanceof JPopupMenu popup) {
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            return (JList<?>) scrollPane.getViewport().getView();
        }
        return null;
    }
}
