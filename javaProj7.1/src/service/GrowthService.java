
package service;

import model.*;

public class GrowthService {
	public int r1, r2, r3;
	public String res;
	
	public void statUp(Player p, int atk, int def, int hp) {
	    p.atkup(atk);
	    p.defup(def);
	    p.hpup(hp);

	    res = "<html>";
	    res += "ATK: " + (p.getatk() - atk) + " -> " + p.getatk() + " (+" + atk + ")<br/>";
	    res += "DEF: " + (p.getdef() - def) + " -> " + p.getdef() + " (+" + def + ")<br/>";
	    res += "HP: " + (p.gethp() - hp) + " -> " + p.gethp() + " (+" + hp + ")<br/>";
	    res += "</html>";
	}

	public String previewStatUp(Player p, int atkUp, int defUp, int hpUp) {
        int newAtk = p.getatk() + atkUp;
        int newDef = p.getdef() + defUp;
        int newHp  = p.gethp() + hpUp;

        StringBuilder sb = new StringBuilder("<html>");
        sb.append("ATK: ").append(p.getatk()).append(" -> ").append(newAtk).append(" (+" + atkUp + ")<br/>");
        sb.append("DEF: ").append(p.getdef()).append(" -> ").append(newDef).append(" (+" + defUp + ")<br/>");
        sb.append("HP: ").append(p.gethp()).append(" -> ").append(newHp).append(" (+" + hpUp + ")<br/>");
        sb.append("</html>");
        return sb.toString();
    }
	
	
	
	public String showInfo() {
		return res;
	}
	
}
