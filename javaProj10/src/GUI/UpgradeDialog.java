package GUI;

import javax.swing.*;
import java.awt.*;
import model.*;
import service.*;

@SuppressWarnings("serial")
public class UpgradeDialog extends JDialog {
    private int atk = 0, def = 0, hp = 0;
    private JLabel atkLabel, defLabel, hpLabel;
    private JLabel resultLabel;
    private GrowthService gs = new GrowthService();
    private Player player;
    private int sp = 12;
    private JLabel spLabel;
    private JButton atkPlusBtn, atkMinusBtn;
    private JButton defPlusBtn, defMinusBtn;
    private JButton hpPlusBtn, hpMinusBtn;


    public UpgradeDialog(JFrame parent, Player player) {
        super(parent, "Stage Clear!", true);
        this.player = player;
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        setSize(350, 320);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel statPanel = new JPanel(new GridLayout(3, 1));

        atkLabel = createStatRow(statPanel, "ATK", 1);
        defLabel = createStatRow(statPanel, "DEF", 1);
        hpLabel = createStatRow(statPanel, "HP", 5);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        updateStatText(); // 초기값 표시

        JButton reset = new JButton("초기화");
        reset.addActionListener(e -> {
            atk = 0;
            def = 0;
            hp = 0;
            sp = 12;
            updateLabels();
            updateStatText();
        });

        JButton confirm = new JButton("계속");
        confirm.addActionListener(e -> {
            gs.statUp(player, atk, def, hp); // 진짜 적용
            setVisible(false);               
        });


        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(reset);
        btnPanel.add(confirm);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        spLabel = new JLabel("SP: " + sp, SwingConstants.CENTER);
        topPanel.add(spLabel, BorderLayout.NORTH);
        topPanel.add(statPanel, BorderLayout.CENTER);

        add(topPanel, "North");
        add(resultLabel, "Center");
        add(btnPanel, "South");
    }

    private JLabel createStatRow(JPanel parent, String stat, int step) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(stat + ": 0");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");

        plus.addActionListener(e -> {
            if (sp > 0) {
                modifyStat(stat, step);
                sp--;
                updateLabels();
            }
        });

        minus.addActionListener(e -> {
            if (getStatValue(stat) > 0) {
                modifyStat(stat, -step);
                sp++;
                updateLabels();
            }
        });

        panel.add(new JLabel(stat));
        panel.add(minus);
        panel.add(label);
        panel.add(plus);

        parent.add(panel);

        // 버튼 저장
        switch (stat) {
            case "ATK" -> { atkLabel = label; atkPlusBtn = plus; atkMinusBtn = minus; }
            case "DEF" -> { defLabel = label; defPlusBtn = plus; defMinusBtn = minus; }
            case "HP"  -> { hpLabel = label; hpPlusBtn = plus; hpMinusBtn = minus; }
        }

        return label;
    }

    private void modifyStat(String stat, int delta) {
        switch (stat) {
            case "ATK" -> atk = Math.max(0, atk + delta);
            case "DEF" -> def = Math.max(0, def + delta);
            case "HP" -> hp = Math.max(0, hp + delta);
        }
        updateLabels();
        updateStatText();
    }

    private void updateStatText() {
        resultLabel.setText(gs.previewStatUp(player, atk, def, hp));  // 복제 없이 미리보기만!
    }

    private int getStatValue(String stat) {
        return switch (stat) {
            case "ATK" -> atk;
            case "DEF" -> def;
            case "HP" -> hp;
            default -> 0;
        };
    }

    private void updateLabels() {
        atkLabel.setText("ATK: " + atk);
        defLabel.setText("DEF: " + def);
        hpLabel.setText("HP: " + hp);
        spLabel.setText("SP: " + sp);

        // SP 제약에 따라 버튼 활성/비활성
        boolean canPlus = sp > 0;

        atkPlusBtn.setEnabled(canPlus);
        defPlusBtn.setEnabled(canPlus);
        hpPlusBtn.setEnabled(canPlus);

        atkMinusBtn.setEnabled(atk > 0);
        defMinusBtn.setEnabled(def > 0);
        hpMinusBtn.setEnabled(hp > 0);
    }

}
