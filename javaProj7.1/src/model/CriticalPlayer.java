
package model;

public class CriticalPlayer extends Player {

	public CriticalPlayer(String name) {
		super(name);
	}

	@Override
	public void initstats() {
		this.hp=25;
		this.atk=8;
		this.def=4;
		this.critRate = (float) 0.7;
	}
}
