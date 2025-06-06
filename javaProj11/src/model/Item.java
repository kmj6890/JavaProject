package model;

public abstract class Item implements Purchasable{

	protected String name;
	public int price;
	
	public Item(String name,int price)
	{
		this.name=name;
		this.price=price;
	}
	@Override
	public int getPrice()
	{
		return price;
	}

	@Override
	public String getName() {
		return name;
	}
	

	@Override
	abstract public void apply(Character c);

	
}
