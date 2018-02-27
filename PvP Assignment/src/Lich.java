
public class Lich extends Enemies{

	int health = 2500;
	String description = "A type of undead creature. Often \n"
						+ "such a creature is the result of \n"
						+ "a transformation, as a powerful  \n"
						+ "magician or king striving for eternal life \n"
						+ "uses spells or rituals to bind his  \n"
						+ "intellect and soul to his phylactery \n"
						+ "and thereby achieve a form of \n"
						+ "immortality.";
	int damage = 500;
	int blockRating = 450;
	String status = "Alive";
	String name = "Lich Ebonsoul";
	
	
	
	
	
	
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


	
	
}//end Lich
