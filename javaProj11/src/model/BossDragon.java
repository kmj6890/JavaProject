package model;

public class BossDragon extends Monster {

	public BossDragon() {
		super("Boss Dragon");
	}

	@Override
	public void initstats() {
		this.hp=100;
		this.atk=20;
		this.def=11;
	}
	@Override
	public void getType() {
		this.type = "BossDragon";
	}

	

}
