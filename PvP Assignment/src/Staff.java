
public class Staff extends Weapon{
	
	int attack = 100;
	int block = 15;
	
	public int getAttack(String selection) {
		if(selection == "Mage"){
			return (attack * 2);
		}//end if
		else{
			return attack;
		}
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getBlock(String selection) {
		if(selection == "Mage"){
			return (block * 2);
		}//end if
		else{
			return block;
		}//end else
	}
	public void setBlock(int block) {
		this.block = block;
	}
	
	public void upgrade(){
		attack += 50;
		block += 50;
		
	}//end upgrade

}