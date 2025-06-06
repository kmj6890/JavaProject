package model;

public class BigGoblin extends Monster {

	public BigGoblin() {
		super("Big Goblin");
	}

	@Override
	public void initstats() {
		this.hp=75;
		this.atk=17;
		this.def=9;

	}
	@Override
	public void getType() {
		this.type = "Goblin";
	}

}
