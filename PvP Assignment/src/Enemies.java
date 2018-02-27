
public abstract class Enemies {

	int health = 0;
	String description = null;
	String name = null;
	int damage = 0;
	int blockRating = 0;
	String status = "Alive";
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public abstract int attack();

	
	
	
}//end Enemies
