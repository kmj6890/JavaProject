package GUI;

import javax.swing.*;

import GUI.BattlePanel.ItemDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.Random;

import model.*;
import model.Character;
import service.*;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel {
	private Image backgroundImage;
	private JLabel playerHpTextLabel, monsterHpTextLabel;
    private JProgressBar playerHpBar, monsterHpBar;
    private JLabel playerLabel, monsterLabel;
    private JLabel turnLabel;
    private JTextArea battleLog;
    private JButton rollButton;
    private JButton skillButton;
    private JLabel playerNameLabel;
    private JLabel dice1TextLabel, dice1ValueLabel;
    private JLabel dice2TextLabel, dice2ValueLabel;
    private Font diceFont;
    
    private BattleService battleService;
    private StageService stageService = new StageService();
    private Player player;
    private int playerMaxHp, monsterMaxHp;
    private int skillCount = 1;
    private boolean skillTF = true;

    public BattlePanel(MainFrame frame) {
        setLayout(null); // 절대 위치
        setFocusable(true); // 키 입력 받도록 설정
        
        // 키 바인딩 추가
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('i'), "openStatus");
        getActionMap().put("openStatus", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatusDialog(frame, player).setVisible(true);
            }
        });

        backgroundImage = new ImageIcon("img/Blue_Nebula_08-1024x1024.png").getImage(); // 경로는 실제 배경 이미지 파일명에 맞게 수정

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Dicier-Flat-Heavy.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            diceFont = baseFont.deriveFont(48f); // 크기 지정
            
        } 
        catch (Exception e) {
            diceFont = new Font("Arial", Font.BOLD, 28); // 오류 시 대체
            e.printStackTrace();
        }
        
        playerNameLabel = new JLabel(); // 처음엔 비워둠
        playerNameLabel.setBounds(50, 330, 150, 50); // 이미지 위에 살짝 띄우는 느낌
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 크기와 스타일

        playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerNameLabel);
        
        // 플레이어 이미지
        playerLabel = new JLabel(new ImageIcon("img/플레이어1.png"));
        playerLabel.setBounds(50, 350, 150, 150);
        add(playerLabel);

        // 플레이어 체력바
        playerHpBar = new JProgressBar(0, 100);
        playerHpBar.setBounds(50, 510, 150, 20);
        playerHpBar.setStringPainted(false);
        add(playerHpBar);
        
        // 플레이어 체력 수치 텍스트
        playerHpTextLabel = new JLabel();
        playerHpTextLabel.setBounds(210, 510, 100, 20);
        add(playerHpTextLabel);

        // 몬스터 이미지
        monsterLabel = new JLabel(new ImageIcon("img/플레이어1.png"));
        monsterLabel.setBounds(550, 50, 150, 150);
        add(monsterLabel);

        // 몬스터 체력바
        monsterHpBar = new JProgressBar(0, 100);
        monsterHpBar.setBounds(550, 210, 150, 20);
        monsterHpBar.setStringPainted(false);
        add(monsterHpBar);

        // 몬스터 체력 수치 텍스트
        monsterHpTextLabel = new JLabel();
        monsterHpTextLabel.setBounds(710, 210, 100, 20);
        add(monsterHpTextLabel);

        // Dice 1
        dice1TextLabel = new JLabel("Dice 1:");
        dice1TextLabel.setBounds(70, 115, 100, 30);
        dice1TextLabel.setFont(new Font("Arial", Font.BOLD, 27));
        add(dice1TextLabel);

        dice1ValueLabel = new JLabel("0");
        dice1ValueLabel.setBounds(180, 100, 50, 70);
        dice1ValueLabel.setFont(diceFont); // 주사위 폰트 적용
        add(dice1ValueLabel);

        // Dice 2
        dice2TextLabel = new JLabel("Dice 2:");
        dice2TextLabel.setBounds(70, 187, 100, 30);
        dice2TextLabel.setFont(new Font("Arial", Font.BOLD, 27));
        add(dice2TextLabel);

        dice2ValueLabel = new JLabel("0");
        dice2ValueLabel.setBounds(180, 170, 50, 70);
        dice2ValueLabel.setFont(diceFont); // 주사위 폰트 적용
        add(dice2ValueLabel);

        // 롤 버튼
        rollButton = new JButton("Roll!");
        rollButton.setBounds(350, 470, 80, 30);
        rollButton.addActionListener(e -> onRoll(frame));
        add(rollButton);
        
        // 스킬 사용 버튼
        skillButton  = new JButton("S");
        skillButton.setBounds(290, 470, 50, 30);
        skillButton.addActionListener(e -> skill());
        add(skillButton);
        

        setFocusable(true);                // 키보드 이벤트 받을 수 있도록 설정
        requestFocusInWindow();           // 포커스를 이 패널로 강제 설정

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) { 
                    new ItemDialog((JFrame) SwingUtilities.getWindowAncestor(BattlePanel.this), player);
                }
            }
        });


        // 전투 로그
        battleLog = new JTextArea(8, 20);
        battleLog.setEditable(false);
        JScrollPane logScroll = new JScrollPane(battleLog);
        logScroll.setBounds(450, 400, 300, 150);
        add(logScroll);
        
        // 턴 표시 (플레이어 / 몬스터)
        turnLabel = new JLabel("");
        turnLabel.setBounds(80, 70, 180, 30);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(turnLabel);
        
        // 글씨 색상 설정 (주황색 예시)
        Color orange = new Color(255, 128, 0);

        playerHpTextLabel.setForeground(orange);
        monsterHpTextLabel.setForeground(orange);

        dice1TextLabel.setForeground(orange);
        dice1ValueLabel.setForeground(orange);

        dice2TextLabel.setForeground(orange);
        dice2ValueLabel.setForeground(orange);
        
        playerNameLabel.setForeground(orange);
        turnLabel.setForeground(orange);
        
        stageService.setList();
    }
    public class ItemDialog extends JDialog {
        public ItemDialog(JFrame parent, Character player) {
            super(parent, "아이템 상점", true);
            setLayout(new BorderLayout());

            JLabel pointLabel = new JLabel("보유 포인트: " + player.getPoint(), SwingConstants.CENTER);
            add(pointLabel, BorderLayout.NORTH);

            JPanel itemPanel = new JPanel(new GridLayout(4, 1));

            Item[] items = {
                new HealingItem(),
                new AttackUpItem(),
                new DefenseUpItem(),
                new CritUpItem()
            };

            for (Item item : items) {
                JButton btn = new JButton(item.getName() + " (" + item.getPrice() + "P)");
                btn.addActionListener(e -> {
                    String input = JOptionPane.showInputDialog(this, "몇 개 구매할까요?");
                    if (input == null) return;

                    try {
                        int quantity = Integer.parseInt(input.trim());
                        int totalCost = item.getPrice() * quantity;

                        if (player.spendPoint(totalCost)) {
                            for (int i = 0; i < quantity; i++) {
                                player.tempItem = item;  // 한 턴 아이템 (1개만 적용)
                            }
                            JOptionPane.showMessageDialog(this, "구매 성공!");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "포인트가 부족합니다.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "숫자를 입력하세요.");
                    }
                });

                itemPanel.add(btn);
            }

            add(itemPanel, BorderLayout.CENTER);
            setSize(300, 300);
            setLocationRelativeTo(parent);
            setVisible(true);
        }
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void startBattle(Player p) {
    	 {
    	    this.player = p;
    	    battleService = new BattleService(p);
    	    Monster monster = stageService.spawnMonster();
    	    battleService.setMonster(monster);

    	    playerMaxHp = player.gethp();
    	    monsterMaxHp = monster.gethp();
    	    
    	    // 이름
    	    playerNameLabel.setText(player.getname());
    	    
    	    // 체력 이미지 및 수치 업데이트
    	    updateHpLabels();
    	    battleLog.setText("Battle starts with " + monster.getname() + "!\n");
    	    
    	    String monsterImgPath;
    	    if (monster instanceof MiniSlime) {
    	        monsterImgPath = "img/slime.png";
    	    } 
    	    else if (monster instanceof MiniGoblin) {
    	        monsterImgPath = "img/goblin.png";
    	    }
    	    else if (monster instanceof BigSlime) {
    	    	monsterImgPath = "img/slime.png";
    	    }
    	    else if (monster instanceof BigGoblin) {
    	    	monsterImgPath = "img/goblin.png";
    	    }
    	    else if (monster instanceof BossDragon) {
    	        monsterImgPath = "img/bossdragon.png";
    	    } 
    	    else {
    	        monsterImgPath = "img/monster.png"; // 기본 이미지
    	    }
    	    monsterLabel.setIcon(new ImageIcon(monsterImgPath));
    	    // 🎯 이미지 선택: 클래스에 따라 분기
    	    if (p instanceof AttackerPlayer) {
    	        playerLabel.setIcon(null); // 아이콘 제거
    	        playerLabel.setText("<html><img src='file:img/Attacker.gif' width='150' height='150'></html>");
    	    }
    	    else if (p instanceof DefenderPlayer) {
    	    	playerLabel.setIcon(null); // 아이콘 제거
    	        playerLabel.setText("<html><img src='file:img/Defender.gif' width='150' height='150'></html>");
    	    }
    	    else if (p instanceof CriticalPlayer) {
    	    	playerLabel.setIcon(null); // 아이콘 제거
    	        playerLabel.setText("<html><img src='file:img/Critical.gif' width='150' height='150'></html>");
    	    }
    	    else {
    	        String imgPath;
    	        imgPath = "img/player.png";
    	        
    	        playerLabel.setText(null); // 텍스트 제거
    	        playerLabel.setIcon(new ImageIcon(imgPath)); // 이미지 아이콘 설정
    	    }

    	    updateHpLabels();
    	    battleLog.setText("Battle starts with " + monster.getname() + "!\n");
    	    refreshTurnLabel();
    	}

    }

    private void onRoll(MainFrame frame) {
        rollButton.setEnabled(false); // 중복 클릭 방지
        skillButton.setEnabled(false); // 주사위 굴리는 중에는 스킬 사용 금지
        
        Timer diceTimer = new Timer(20, null);
        final int[] step = {0};
        final int[] delay = {20};
        final Random rand = new Random();
        final int maxStep = 20;

        diceTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	refreshTurnLabel();
            	
                int dice1 = rand.nextInt(6) + 1;
                int dice2 = rand.nextInt(6) + 1;

                dice1ValueLabel.setText(String.valueOf(dice1));
                dice2ValueLabel.setText(String.valueOf(dice2));

                step[0]++;
                if (step[0] < maxStep) {
                    delay[0] += 10;
                    if (step[0] > 17) delay[0] += 750;
                    diceTimer.setDelay(delay[0]);
                } 
                else {
                    diceTimer.stop();

                    // 🎯 전투 실행
                    battleService.setManualDice(dice1, dice2);
                    boolean ongoing = battleService.nextTurn();
                    dice1ValueLabel.setText(String.valueOf(battleService.getDice1()));
                    dice2ValueLabel.setText(String.valueOf(battleService.getDice2()));

                    updateHpLabels();
                    battleLog.append(battleService.getLog());

                    if (!ongoing) {
                        if (!player.isAlive()) {
                            battleLog.append(">> Game Over!\n>> You were defeated.\n");
                            rollButton.setEnabled(false);
                            skillButton.setEnabled(false);
                            return;
                        } 
                        else {
                            battleLog.append(">> Victory!\n");
                            stageService.nextStage();
                            if (stageService.getCurrentStage() > 5) {
                                rollButton.setEnabled(false);
                                skillButton.setEnabled(false);
                                return;
                            } 
                            else {
                            	new UpgradeDialog(frame, player).setVisible(true);
                            	skillCount = 1;
                            	skillButton.setEnabled(true);
                                startBattle(player);
                            }
                        }
                    }

                    rollButton.setEnabled(true); // 다시 활성화
                    if (!skillTF) {
                    	player.rollback();
                    	skillTF = true;
                    }
                    if (skillCount == 1 && battleService.getTurn() == 0) {
                    	skillButton.setEnabled(true);
                    }
                    
                }
            }
        });

        diceTimer.start();
    }

    private void updateHpLabels() {
    	 {
    	    int playerCur = player.gethp();
    	    int monsterCur = battleService.getMonster().gethp();

    	    // 퍼센트로 변환해서 bar에 적용
    	    playerHpBar.setValue((int)((double)playerCur / playerMaxHp * 100));
    	    monsterHpBar.setValue((int)((double)monsterCur / monsterMaxHp * 100));

    	    // 옆에 숫자로 표시
    	    playerHpTextLabel.setText(playerCur + " / " + playerMaxHp);
    	    monsterHpTextLabel.setText(monsterCur + " / " + monsterMaxHp);
    	}

    }
    
    private void refreshTurnLabel() {
    	String text = (battleService.getTurn() == 0) ? "<Player's Turn>" : "<Monster's Turn>";
    	
    	turnLabel.setText(text);
    	turnLabel.revalidate();
    	turnLabel.repaint();
    }
    
    private void skill() {
    	if (skillCount == 1) {
    		skillCount--;
    		skillTF = false;
    		skillButton.setEnabled(false);
    		player.useSkill();
    	}
    	else {
    		return;
    	}
    }
}
