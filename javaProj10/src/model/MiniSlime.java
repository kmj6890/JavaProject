package model;

public class MiniSlime extends Monster {

	public MiniSlime() {
		super("Mini Slime");
	}

	@Override
	public void initstats() {
		this.hp=20;
		this.atk=6;
		this.def=3;
	}

}
