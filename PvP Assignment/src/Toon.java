
public abstract class Toon extends Game{

	private int health = 0;
	private int mana = 0;
	private int dex = 0;
	private int intel = 0;
	private int str = 0;
	private int goldAmount = 100;
	private int mainStat = 0;
			
	public int getMainStat() {
		return mainStat;
	}
	public void setMainStat(int mainStat) {
		this.mainStat = mainStat;
	}
	public int getGoldAmount() {
		return goldAmount;
	}
	public void setGoldAmount(int goldAmount) {
		this.goldAmount = goldAmount;
	}

	String selection = null;
	
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
		
	}
	
	private String name = null;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	

	

	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setHealth(int exchange) {
		// TODO Auto-generated method stub
		
	}
	public int getStr() {
		// TODO Auto-generated method stub
		return str;
	}

	
	public abstract String attack();
	
		
		
		
	}//end class Toon

