package model;

public class BigSlime extends Monster {

	public BigSlime() {
		super("Big Slime");
	}

	@Override
	public void initstats() {
		this.hp=60;
		this.atk=14;
		this.def=7;
	}

}
