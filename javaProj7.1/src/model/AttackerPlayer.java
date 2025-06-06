
package model;

public class AttackerPlayer extends Player {

	public AttackerPlayer(String name) {
		super(name);
	}
	
	@Override
	public void initstats() {
		this.hp=30;
		this.atk=10;
		this.def=4;
	}
}

