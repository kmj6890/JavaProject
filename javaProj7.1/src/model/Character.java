
package model;

import java.util.Random;

public abstract class Character {
	public String name;
	protected int hp;
	protected int atk;
	protected int def;
	
	static final float cri = 1.5f;
	public float critRate = 0.3f;
	private static final Random rand = new Random();
	
	public String log;
	public int dice1, dice2;
	
	public abstract void initstats();
	
	public Character(String name) {
		this.name = name;
	}

	public String getname() {
		return name;
	}
	
	public int gethp() {
		return hp;
	}
	
	public int getatk() {
		return atk;
	}
	
	public int getdef() {
		return def;
	}
	
	public void hpup(int hp) {
		this.hp +=hp;
	}
	
	public void atkup(int atk) {
		this.atk +=atk;
	}
	
	public void defup(int def) {
		this.def +=def;
	}

	public void takeDamage(int damage)
	{
		hp -= damage;
		if(hp < 0)
		{
			hp = 0;
		}
	}
	
	public boolean isCritical() {
		return rand.nextInt(100) < critRate * 100;
	}
	
	public int calculateDamage() {
		int dicesum;
		
		Dice dice = new Dice();
		dicesum = dice.rollDice();
		dice1 = dice.getDice1();
		dice2 = dice.getDice2();
		
		log = "주사위 값: " + dice1 + " + " + dice2 + " = " +  dicesum + " ";
		
	    if (isCritical()) {
	    	log += "(크리티컬!)" + System.lineSeparator();
	        return (int) (dicesum * atk * cri);
	    }
	    
	    log += System.lineSeparator();
	    
	    return (int) (dicesum * atk);
	}

	public int attackRoll() {
	    return calculateDamage();
	}
	
	public boolean isAlive() {
		   if (this.hp <= 0) {
			   return false;
		   }
		   else {
			   return true;
		   }
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

}
