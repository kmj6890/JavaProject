package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.*;
import model.Player;

@SuppressWarnings("serial")
public class StatusDialog extends JDialog {
    public StatusDialog(JFrame parent, Player player) {
        super(parent, "상태창", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(null);

        // ESC로 닫기
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("ESCAPE"), "closeDialog");
        getRootPane().getActionMap().put("closeDialog", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // 플레이어 이미지
        JLabel playerImgLabel = new JLabel();
        playerImgLabel.setBounds(-5, 20, 150, 150); // 위치와 크기

        String html = "<html><img src='file:";

        if (player instanceof AttackerPlayer) {
            html += "img/Attacker.gif";
        } else if (player instanceof DefenderPlayer) {
            html += "img/Defender.gif";
        } else if (player instanceof CriticalPlayer) {
            html += "img/Critical.gif";
        } else {
            html += "img/player.png"; // 기본 이미지
        }

        html += "' width='150' height='150'></html>";

        playerImgLabel.setText(html);
        add(playerImgLabel);

        // 스탯 영역
        JPanel statPanel = new JPanel(new GridLayout(4, 1));
        statPanel.setBounds(200, 40, 100, 100);
        
        JLabel atkLabel = new JLabel("ATK: " + player.getatk(), SwingConstants.CENTER);
        JLabel defLabel = new JLabel("DEF: " + player.getdef(), SwingConstants.CENTER);
        JLabel hpLabel  = new JLabel("HP: " + player.gethp(), SwingConstants.CENTER);
        JLabel critLabel = new JLabel("CRIT: " + player.getcrit(), SwingConstants.CENTER);
        
        atkLabel.setVerticalAlignment(SwingConstants.CENTER);
        defLabel.setVerticalAlignment(SwingConstants.CENTER);
        hpLabel.setVerticalAlignment(SwingConstants.CENTER);
        critLabel.setVerticalAlignment(SwingConstants.CENTER);

        statPanel.add(atkLabel);
        statPanel.add(defLabel);
        statPanel.add(hpLabel);
        statPanel.add(critLabel);

        add(statPanel);        
        add(statPanel);

        // 보유 아이템 텍스트
        JLabel itemLabel = new JLabel("보유 아이템");
        itemLabel.setBounds(30, 170, 100, 20);
        add(itemLabel);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(30, 200, 320, 100);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(itemPanel);
    }
}

