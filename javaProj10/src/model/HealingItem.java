package model;

public class HealingItem extends Item {

	public HealingItem() {
		super("상처약", 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(Character c) {
		c.hpup(30);
	}

	@Override
	public void rollback(Character c) {
	}

}
