
public class Ranger extends Toon{

	int health = 225;
	int mana =30;
	int dex = 50;
	int intel = 15;
	int str = 20;
	private int mainStat = 50;
	
	public int getMainStat() {
		return mainStat;
	}
	public void setMainStat(int mainStat) {
		this.mainStat = mainStat;
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getIntel() {
		return intel;
	}
	public void setIntel(int intel) {
		this.intel = intel;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	@Override
	public String attack() {
		String attacking = " aims, shoots and hits ";
		return attacking;
	}

	
	
}// end Ranger
