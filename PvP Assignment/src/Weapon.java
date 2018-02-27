
public abstract class Weapon extends Game{

	int attack = 0;
	int block = 0;
	
	
	public int getAttack(String selection) {
		return attack;
	}


	public void setAttack(int attack) {
		this.attack = attack;
	}


	public int getBlock(String selection) {
		return block;
	}


	public void setBlock(int block) {
		this.block = block;
	}


	public abstract void upgrade();
	
	
}
