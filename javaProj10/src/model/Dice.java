
package model;

import java.util.Random;

public class Dice {
	
	int dice1, dice2;
	Random rand = new Random();
	
	public int rollDice() {
		dice1 = rand.nextInt(6) + 1;
		dice2 = rand.nextInt(6) + 1;
		
		return dice1 + dice2;
	}
	
	public int getDice1() {
		return dice1;
	}
	
	public int getDice2() {
		return dice2;
	}
	
}
