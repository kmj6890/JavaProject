
package model;

public abstract class Player extends Character{

	public Player(String name) {
		super(name);
	}

	@Override
	abstract public void initstats();

}
