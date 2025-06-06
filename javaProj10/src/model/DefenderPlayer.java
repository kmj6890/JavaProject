
package model;

import service.BattleService;

public class DefenderPlayer extends Player {
	
	public DefenderPlayer(String name) {
		super(name);
	}
	
	@Override
	public void initstats() {
		this.hp=41;
		this.atk=6;
		this.def=7;
	}

	@Override
	public void useSkill() {
		BattleService.reflectDamage = true;
	}

	@Override
	public void rollback() {
		
	}

	@Override
	public String getSkill() {
		return "데미지 50% 반사";
	}
}
