
package service;

import model.Player;
import model.Monster;

public class BattleService {
	
	private final Player player;
	private Monster monster;
	
	public String log;
	public int turn = 0;
	public int dice1, dice2;
	
	public static boolean reflectDamage = false;
	
	public BattleService(Player player) {
		this.player = player;
	}
	
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	
	public boolean nextTurn() {
		if (monster == null || !monster.isAlive() || !player.isAlive()) {
			return false;
		}
		
		int temp_hp;
		
		if (turn == 0) {
			temp_hp = monster.gethp();
			int dmg = player.attackRoll();
			dice1 = player.getDice1();
			dice2 = player.getDice2();
			dmg /= monster.getdef();
			monster.takeDamage(dmg);

			log = "--------------------" + System.lineSeparator();
			log += player.getLog();
			log += "플레이어의 공격 " + dmg + System.lineSeparator();
			log += monster.getname() + " HP: " + temp_hp + " -> " + monster.gethp() + System.lineSeparator();
			log += "--------------------" + System.lineSeparator() + System.lineSeparator();
			
			turn = 1;

			if (!monster.isAlive()) {
				return false;
			}
			
		}
		
		else {
			temp_hp = player.gethp();
			int counter = monster.attackRoll();
			dice1 = monster.getDice1();
			dice2 = monster.getDice2();
			counter /= player.getdef();
			player.takeDamage(counter);
			
			log = "--------------------" + System.lineSeparator();
			log += monster.getLog();
			log += monster.getname() + "의 반격 " + counter + System.lineSeparator();
			log += "플레이어 HP: " + temp_hp + " -> " + player.gethp() + System.lineSeparator();
			if (reflectDamage) {
				int temp_hp2 = monster.gethp();
				int reflect = counter / 2;
				monster.takeDamage(reflect);
				log += "반사 데미지 발동!: " + reflect + System.lineSeparator();
				log += monster.getname() + " HP: " + temp_hp2 + " -> " + monster.gethp() + System.lineSeparator();
				reflectDamage = false;
			}
			log += "--------------------";
			log += System.lineSeparator() + System.lineSeparator();
			
			turn = 0;
			
			if (!player.isAlive() || !monster.isAlive()) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public String getLog() {
		return log;
	}
	
	public int getDice1() {
		return dice1;
	}
	
	public int getDice2() {
		return dice2;
	}
	
	public void setManualDice(int d1, int d2) {
	    this.dice1 = d1;
	    this.dice2 = d2;
	}
	
	public int getTurn() {
		return turn;
	}

	
 }
