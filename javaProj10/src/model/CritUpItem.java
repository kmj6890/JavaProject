package model;

public class CritUpItem extends Item {

	public CritUpItem() {
		super("급소율업", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(Character c) {
		c.cri = 2f;
		if(c.type !="Critical")
		{
			c.critRate = 0.5f;
		}
		else
		{
			c.critRate = 0.8f;
		}
	}

	@Override
	public void rollback(Character c) {
		c.cri=1.5f;
		if(c.type !="Critical")
		{
			c.critRate = 0.3f;
		}
		else
		{
			c.critRate = 0.7f;
		}
	}

}
