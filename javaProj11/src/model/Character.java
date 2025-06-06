
package model;

import java.util.Random;

public abstract class Character {
	public String name;
	protected int hp;
	protected int atk;
	protected int def;
	public String type;
	public int point;
	public Item tempItem=null;
	
	protected boolean attackAddDamage = false;
	protected boolean criticalAddDamage = false; 
	
	public float cri = 1.5f;
	public float critRate = 0.3f;
	private static final Random rand = new Random();
	
	public String log;
	public int dice1, dice2;
	
	public abstract void initstats();
	
	public abstract void getType();
	
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
	
	public float getcrit() {
		return critRate;
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
		float res = dicesum * atk;
		
		log = "주사위 값: " + dice1 + " + " + dice2 + " = " +  dicesum + " ";
		
		if (attackAddDamage) {
			log += "(강화 공격!) ";
			res *= 1.5;
		}
		if (criticalAddDamage) {
			log += "(강화 크리티컬!)" + System.lineSeparator();
			res *= 2;
			return (int) res;
		}
		
	    if (isCritical()) {
	    	log += "(크리티컬!)" + System.lineSeparator();
	        return (int) (res * cri);
	    }
	    
	    log += System.lineSeparator();
	    
	    return (int) res;
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

	public void earnPoint(int amount) {
        point += amount;
    }

    public boolean spendPoint(int amount) {
        if (point >= amount) {
            point -= amount;
            return true;
        }
        return false;
    }

    public int getPoint() {
        return point;
    }

}
