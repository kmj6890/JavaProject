package model;

public class AttackUpItem extends Item {

	public AttackUpItem() {
		super("공격력업", 10);
	}

	@Override
	public void apply(Character c) {
		c.atk *=2;
	}

	@Override
	public void rollback(Character c)
	{
		c.atk/=2;
	}
	
	

}
