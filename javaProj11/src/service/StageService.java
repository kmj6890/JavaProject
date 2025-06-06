
package service;

import java.util.ArrayList;
import model.*;

public class StageService {
	
	private int currentStage = 1;
	ArrayList<Monster> list = new ArrayList<>();
	
	public Monster spawnMonster() {
		Monster m;
		m = list.get(currentStage - 1);
		if (m != null) {
			m.initstats();
		}
		
		return m;
		
	}
	
	public void setList() {
		list.add(new MiniSlime());
		list.add(new MiniGoblin());
		list.add(new BigSlime());
		list.add(new BigGoblin());
		list.add(new BossDragon());
	}
	
	public void nextStage() {
		currentStage++;
	}
	
	public int getCurrentStage() {
		return currentStage;
	}
	
}
