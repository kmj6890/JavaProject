
package GUI;

import javax.swing.*;

import model.Player;

import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private MainMenuPanel mainMenuPanel;
    private BattlePanel battlePanel;

    public MainFrame() {
        setTitle("Dice Battle Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        SplashScreen splashScreen = new SplashScreen(this);
        mainMenuPanel = new MainMenuPanel(this);
        battlePanel = new BattlePanel(this);

        cardPanel.add(splashScreen, "splash");
        cardPanel.add(mainMenuPanel, "menu");
        cardPanel.add(battlePanel, "battle");

        add(cardPanel);
        showPanel("splash");
    }

    public BattlePanel getBattlePanel() {
        return battlePanel;
    }


    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

	public void startBattle(Player player) {
		battlePanel.startBattle(player);   // 플레이어 정보 전달
        showPanel("battle");               // 전투 화면으로 전환
	}
}
