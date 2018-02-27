
public class Tarrasque extends Enemies{
	
	
	int health = 5000;
	String description = "The tarrasque is a gigantic lizard-like\n"
			+ "creature which exists only to eat, kill\n"
			+ " and destroy.";
	int damage = 2000;
	int blockRating = 1000;
	String status = "Alive";
	String name = "The Tarrasque";
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getBlockRating() {
		return blockRating;
	}

	public void setBlockRating(int blockRating) {
		this.blockRating = blockRating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int attack() {
		
		return damage;
		
	}//end attack


	

}//end Tarrasque
