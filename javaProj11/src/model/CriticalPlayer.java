
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

	@Override
	public void useSkill() {
		this.critRate = 1;
		this.criticalAddDamage = true;
	}

	@Override
	public void rollback() {
		this.critRate = (float) 0.7;
		this.criticalAddDamage = false;
	}

	@Override
	public void getType() {
		this.type = "Critical";
	}
	
}
