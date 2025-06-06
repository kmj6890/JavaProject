package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import model.*;

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

        // 보유 아이템 텍스트
        JLabel itemLabel = new JLabel("보유 아이템");
        itemLabel.setBounds(30, 170, 100, 20);
        add(itemLabel);
        
        JButton useItem1 = new JButton("사용");
        useItem1.setBounds(100, 230, 80, 20);
        add(useItem1);
        
        JButton useItem2 = new JButton("사용");
        useItem2.setBounds(100, 270, 80, 20);
        add(useItem2);
        
        JButton useItem3 = new JButton("사용");
        useItem3.setBounds(260, 230, 80, 20);
        add(useItem3);
        
        JButton useItem4 = new JButton("사용");
        useItem4.setBounds(260, 270, 80, 20);
        add(useItem4);
        
        JLabel numItem1 = new JLabel("0");
        numItem1.setBounds(75, 230, 20, 20);
        add(numItem1);
        
        JLabel numItem2 = new JLabel("0");
        numItem2.setBounds(75, 270, 20, 20);
        add(numItem2);
        
        JLabel numItem3 = new JLabel("0");
        numItem3.setBounds(235, 230, 20, 20);
        add(numItem3);
        
        JLabel numItem4 = new JLabel("0");
        numItem4.setBounds(235, 270, 20, 20);
        add(numItem4);
        
        JLabel imageItem1 = new JLabel(new ImageIcon("img/물약1.png"));
        imageItem1.setBounds(35, 222, 35, 35);
        add(imageItem1);
        
        JLabel imageItem2 = new JLabel(new ImageIcon("img/물약2.png"));
        imageItem2.setBounds(35, 262, 35, 35);
        add(imageItem2);
        
        JLabel imageItem3 = new JLabel(new ImageIcon("img/물약3.png"));
        imageItem3.setBounds(195, 222, 35, 35);
        add(imageItem3);
        
        JLabel imageItem4 = new JLabel(new ImageIcon("img/물약4.png"));
        imageItem4.setBounds(195, 262, 35, 35);
        add(imageItem4);
        
        JLabel nameItem1 = new JLabel("[HP portion]");
        nameItem1.setBounds(35, 200, 80, 20);
        add(nameItem1);
        
        JLabel nameItem2 = new JLabel("[ATK portion]");
        nameItem2.setBounds(35, 295, 80, 20);
        add(nameItem2);
        
        JLabel nameItem3 = new JLabel("[DEF portion]");
        nameItem3.setBounds(195, 200, 80, 20);
        add(nameItem3);
        
        JLabel nameItem4 = new JLabel("[CRIT portion]");
        nameItem4.setBounds(195, 295, 120, 20);
        add(nameItem4);
    }
    
    
}

