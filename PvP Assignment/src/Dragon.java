
public class Dragon extends Enemies{

	
	int health = 1500;
	String description = "Despite their large size,\n"
			+ "deep dragons were slender and agile,\n"
			+ "allowing them to crawl through the \n"
			+ "tunnels of the Underdark. In the light,\n"
			+ "the dragons' scales were the color of \n"
			+ "amethyst, but in the dark, they \n"
			+ "appeared nearly black.";
	String name = "Deep Dragon Pharx";
	int damage = 300;
	int blockRating = 250;
	String type = "dragonObj";
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	@Override
	public int attack() {
		
		return damage;
		
	}//end attack



}//end Dragon
