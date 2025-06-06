
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

	@Override
	public void useSkill() {
		this.attackAddDamage = true;
	}

	@Override
	public void rollback() {
		this.attackAddDamage = false;
	}

	@Override
	public String getSkill() {
		return "데미지 2배";
	}

}

