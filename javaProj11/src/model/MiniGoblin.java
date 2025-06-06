package model;

public class MiniGoblin extends Monster {

	public MiniGoblin() {
		super("Mini Goblin");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initstats() {
		this.hp=35;
		this.atk=9;
		this.def=5;
	}

	@Override
	public void getType() {
		this.type = "Goblin";
	}
}
