package model;

public class DefenseUpItem extends Item {

	public DefenseUpItem() {
		super("방어업", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(Character c) {
		c.def*=2;
	}

	@Override
	public void rollback(Character c) {
		c.def/=2;
	}

}
