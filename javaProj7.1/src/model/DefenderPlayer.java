
package model;

public class DefenderPlayer extends Player{
	
	public DefenderPlayer(String name) {
		super(name);
	}
	
	@Override
	public void initstats() {
		this.hp=41;
		this.atk=6;
		this.def=7;
	}
}
