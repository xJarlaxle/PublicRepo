import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AppFrame extends JFrame{
	
	//region Declarations
	private JPanel contentPane;
	private JPanel panelTitle;
	private JPanel panelToon;
	private JTextField txtCharacterName;
	private JLabel lblChooseYourClass;
	private JLabel lblPicDisplay;
	private JLabel lblHealthPts;
	private JRadioButton rdbtnKnight;
	private JRadioButton rdbtnMage;
	private JRadioButton rdbtnRanger;
	private JPanel panelEquipment;
	private JLabel lblCharacter;
	private JLabel lblWeaponDisplay;
	private JLabel lblWeaponDisplay2;
	private JButton btnSwordUpgrade;
	private JRadioButton rdbtnBow;
	private JRadioButton rdbtnStaff;
	private JButton btnStaffUpgrade;
	private JButton btnBowUpgrade;
	private JRadioButton rdbtnSword;
	private JTextArea txtCombatArea;
	private JPanel panelFight;
	private JLabel lblTitlescreen;
	private int buttonCount = 0;
	private int battleCount;
	int bossNumber = 0;
	int fightManager = 0;
	private String weaponSelect = null;
	private int bonusGoldButton;
	private final int COMBAT_DELAY = 3000;
	private Timer timer = new Timer(COMBAT_DELAY, new TimerHandler());
	Toon toonObj =  new Toon() {

		@Override
		public String attack() {
			return selection;
		}

	};//end abstract object instantiation for Toon
	Enemies enemyObj = new Enemies() {

		@Override
		public int attack() {
			return damage;}

	};
	Sword swObj = new Sword();
	Bow bowObj = new Bow();
	Staff staffObj = new Staff();
	Knight knightObj = new Knight();
	Mage mageObj = new Mage();
	Ranger rangerObj = new Ranger();
	Dragon dragonObj = new Dragon();
	Lich lichObj = new Lich();
	Tarrasque tarrasqueObj = new Tarrasque();
	Game gameObj = new Game();
	private JLabel lblEquipmentBonuses;
	private JLabel lblBonuses;
	private JLabel lblBonuses2;
	private JLabel lblBonuses3;
	private JLabel lblGoldAmount;
	JLabel lblCurrentgold;
	private JLabel lblPlayer;
	JLabel lblPlayerHealth;
	private JLabel lblWeapon;
	private JLabel lblArmor;
	private JLabel lblEnemy;
	JLabel lblEnemyhealth;
	private JTextArea txtEnemydescription;
	private JButton btnStartBattle;
	private JScrollPane scrollPane;
	private JPanel panelDead;
	private JLabel lblSuck;
	private JLabel lblBackgroundPic;
	private JLabel lblBackgroundPicture;
	private JPanel panelVictory;
	private JLabel lblBg;
	private JButton btnBonusGold;
	private int inProgress = 0;
	private boolean cont = true;
	//endregion Fold

	
	
	//region Icons
	private final String[] fileNames = {"Knight.png","Mage.png","Ranger.png","titleScreen.jpg","warbanner.png","bg.png","victory.png"};
	private Icon[] icons = {
		new ImageIcon(getClass().getResource(fileNames[0])),
		new	ImageIcon(getClass().getResource(fileNames[1])),
		new	ImageIcon(getClass().getResource(fileNames[2])),
		new	ImageIcon(getClass().getResource(fileNames[3])),
		new	ImageIcon(getClass().getResource(fileNames[4])),
		new	ImageIcon(getClass().getResource(fileNames[5])),
		new	ImageIcon(getClass().getResource(fileNames[6]))
	};//end icon
	//endregion Icons

	
	//Launch Application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
	}//end main

	//Constructor AppFrame
	public AppFrame() {
		//Set up of all GUI components
		setTitle("Doragon Sureiya");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 680, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panelTitle, "name_417101060518559");
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Doragon Sureiy\u0101");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblTitle.setBounds(12, 162, 179, 24);
		panelTitle.add(lblTitle);
		
		
		JButton btnEnter = new JButton("Press Here To Continue");
		btnEnter.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		//shows the character screen panel and hides all others
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelTitle.setVisible(false);
				panelToon.setVisible(true);
				panelEquipment.setVisible(false);
				panelFight.setVisible(false);
				panelDead.setVisible(false);
				
			}
		});
		btnEnter.setBounds(261, 400, 179, 25);
		panelTitle.add(btnEnter);
		
		lblTitlescreen = new JLabel("");
		lblTitlescreen.setForeground(Color.BLACK);
		lblTitlescreen.setBounds(0, 0, 672, 443);
		panelTitle.add(lblTitlescreen);
		lblTitlescreen.setIcon(icons[3]);
		
		panelToon = new JPanel();
		contentPane.add(panelToon, "name_418517754174214");
		panelToon.setLayout(null);
		
		JLabel lblCharacterCreation = new JLabel("Character Creation");
		lblCharacterCreation.setForeground(Color.LIGHT_GRAY);
		lblCharacterCreation.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblCharacterCreation.setBounds(249, -12, 197, 63);
		panelToon.add(lblCharacterCreation);
		
		txtCharacterName = new JTextField();
		txtCharacterName.setBounds(12, 64, 150, 22);
		panelToon.add(txtCharacterName);
		txtCharacterName.setColumns(10);
		
		JLabel lblMana = new JLabel("Mana");
		lblMana.setForeground(Color.LIGHT_GRAY);
		lblMana.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMana.setBounds(12, 280, 56, 16);
		panelToon.add(lblMana);
		
		JLabel lblDex = new JLabel("Dex");
		lblDex.setForeground(Color.LIGHT_GRAY);
		lblDex.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDex.setBounds(12, 320, 56, 16);
		panelToon.add(lblDex);
		
		JLabel lblIntPts = new JLabel("iNT");
		lblIntPts.setForeground(Color.LIGHT_GRAY);
		lblIntPts.setBounds(79, 340, 45, 16);
		panelToon.add(lblIntPts);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(12, 35, 56, 16);
		panelToon.add(lblName);
		
		JLabel lblStrPts = new JLabel("Strength");
		lblStrPts.setForeground(Color.LIGHT_GRAY);
		lblStrPts.setBounds(79, 300, 45, 16);
		panelToon.add(lblStrPts);
		
		JLabel lblDexPts = new JLabel("Dex");
		lblDexPts.setForeground(Color.LIGHT_GRAY);
		lblDexPts.setBounds(79, 320, 45, 16);
		panelToon.add(lblDexPts);
		
		JLabel lblInt = new JLabel("Int");
		lblInt.setForeground(Color.LIGHT_GRAY);
		lblInt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInt.setBounds(12, 340, 64, 16);
		panelToon.add(lblInt);
		
		JLabel lblStr = new JLabel("Str");
		lblStr.setForeground(Color.LIGHT_GRAY);
		lblStr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStr.setBounds(12, 300, 56, 16);
		panelToon.add(lblStr);
		
		JLabel lblManaPts = new JLabel("Mana");
		lblManaPts.setForeground(Color.LIGHT_GRAY);
		lblManaPts.setBounds(79, 280, 45, 16);
		panelToon.add(lblManaPts);
		
		lblChooseYourClass = new JLabel("Choose Your Class");
		lblChooseYourClass.setForeground(Color.LIGHT_GRAY);
		lblChooseYourClass.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblChooseYourClass.setBounds(12, 105, 150, 16);
		panelToon.add(lblChooseYourClass);
		
		rdbtnKnight = new JRadioButton("Knight");
		rdbtnKnight.setForeground(Color.LIGHT_GRAY);
		rdbtnKnight.setBounds(8, 130, 127, 25);
		panelToon.add(rdbtnKnight);
		rdbtnKnight.setOpaque(false);
		//selects the knight to play and gets all stat info for it and sends it to labels
		rdbtnKnight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPicDisplay.setIcon(icons[0]);
				rdbtnKnight.setSelected(true);
				rdbtnMage.setSelected(false);
				rdbtnRanger.setSelected(false);
				Knight kObj = new Knight();
				String hp = Integer.toString(kObj.getHealth());
				lblHealthPts.setText(hp);
				String mana = Integer.toString(kObj.getMana());
				lblManaPts.setText(mana);
				String str = Integer.toString(kObj.getStr());
				lblStrPts.setText(str);
				String dex = Integer.toString(kObj.getDex());
				lblDexPts.setText(dex);
				String intel = Integer.toString(kObj.getIntel());
				lblIntPts.setText(intel);
				toonObj.setSelection("Knight");

			}


		});
		
		rdbtnMage = new JRadioButton("Mage");
		rdbtnMage.setForeground(Color.LIGHT_GRAY);
		rdbtnMage.setBounds(8, 156, 127, 25);
		panelToon.add(rdbtnMage);
		rdbtnMage.setOpaque(false);
		//selects the mage to play and gets all stat info for it and sends it to labels
		rdbtnMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPicDisplay.setIcon(icons[1]);
				rdbtnKnight.setSelected(false);
				rdbtnMage.setSelected(true);
				rdbtnRanger.setSelected(false);
				Mage mObj = new Mage();
				String hp = Integer.toString(mObj.getHealth());
				lblHealthPts.setText(hp);
				String mana = Integer.toString(mObj.getMana());
				lblManaPts.setText(mana);
				String str = Integer.toString(mObj.getStr());
				lblStrPts.setText(str);
				String dex = Integer.toString(mObj.getDex());
				lblDexPts.setText(dex);
				String intel = Integer.toString(mObj.getIntel());
				lblIntPts.setText(intel);
				toonObj.setSelection("Mage");
				
			}
		});
		
		
		JButton btnEquipment = new JButton("Go To Equipment");
		//grabs gold information and sets the label, switches to the shop screen, makes sure name and class have been selected
		btnEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtCharacterName.getText();
				toonObj.setName(name);
				lblCurrentgold.setText(Integer.toString(toonObj.getGoldAmount()));
				if(toonObj.getSelection() == null){
				
					JOptionPane.showMessageDialog(null, "Please select a class before continuing!");
					
				}
				else if(toonObj.getName().length() < 1){
					
					JOptionPane.showMessageDialog(null, "Please name your character!");
					
				}
				else{
					getCharacterPicture();
				panelTitle.setVisible(false);
				panelToon.setVisible(false);
				panelEquipment.setVisible(true);
				panelFight.setVisible(false);
				panelDead.setVisible(false);
				}
			}
		});
		btnEquipment.setBounds(520, 416, 140, 25);
		panelToon.add(btnEquipment);
		
		rdbtnRanger = new JRadioButton("Ranger");
		rdbtnRanger.setForeground(Color.LIGHT_GRAY);
		rdbtnRanger.setBounds(8, 182, 127, 25);
		panelToon.add(rdbtnRanger);
		rdbtnRanger.setOpaque(false);
		//selects the ranger to play and gets all stat info for it and sends it to labels
		rdbtnRanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPicDisplay.setIcon(icons[2]);
				rdbtnKnight.setSelected(false);
				rdbtnMage.setSelected(false);
				rdbtnRanger.setSelected(true);
				Ranger rObj = new Ranger();
				String hp = Integer.toString(rObj.getHealth());
				lblHealthPts.setText(hp);
				String mana = Integer.toString(rObj.getMana());
				lblManaPts.setText(mana);
				String str = Integer.toString(rObj.getStr());
				lblStrPts.setText(str);
				String dex = Integer.toString(rObj.getDex());
				lblDexPts.setText(dex);
				String intel = Integer.toString(rObj.getIntel());
				lblIntPts.setText(intel);
				toonObj.setSelection("Ranger");
			}
		});
		
		JLabel lblStatPointDistribution = new JLabel("SP Distribution");
		lblStatPointDistribution.setForeground(Color.LIGHT_GRAY);
		lblStatPointDistribution.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblStatPointDistribution.setBounds(12, 221, 150, 22);
		panelToon.add(lblStatPointDistribution);
		
		JLabel lblHealth = new JLabel("Health");
		lblHealth.setForeground(Color.LIGHT_GRAY);
		lblHealth.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHealth.setBounds(12, 260, 56, 16);
		panelToon.add(lblHealth);
		
		lblPicDisplay = new JLabel("");
		lblPicDisplay.setBounds(192, 64, 300, 340);
		panelToon.add(lblPicDisplay);
		
		lblHealthPts = new JLabel("Health");
		lblHealthPts.setForeground(Color.LIGHT_GRAY);
		lblHealthPts.setBounds(79, 260, 41, 16);
		panelToon.add(lblHealthPts);
		
		JLabel lblDescription = new JLabel("");
		lblDescription.setBounds(520, 64, 130, 341);
		panelToon.add(lblDescription);
		lblDescription.setIcon(icons[4]);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 660, 441);
		panelToon.add(lblBackground);
		lblBackground.setIcon(icons[5]);
		
		panelEquipment = new JPanel();
		contentPane.add(panelEquipment, "name_499587014919529");
		panelEquipment.setLayout(null);
		
		JLabel lblEquipment = new JLabel("       Equipment");
		lblEquipment.setForeground(Color.LIGHT_GRAY);
		lblEquipment.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEquipment.setBounds(249, -12, 197, 63);
		panelEquipment.add(lblEquipment);
		
		JLabel lblChooseYourWeapon = new JLabel("Choose Your Weapon:");
		lblChooseYourWeapon.setForeground(Color.LIGHT_GRAY);
		lblChooseYourWeapon.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblChooseYourWeapon.setBounds(12, 35, 142, 16);
		panelEquipment.add(lblChooseYourWeapon);
		
		JLabel lblnoteUpgradesCost = new JLabel("*Note: Upgrades Cost 50 gold each");
		lblnoteUpgradesCost.setForeground(Color.LIGHT_GRAY);
		lblnoteUpgradesCost.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		lblnoteUpgradesCost.setBounds(12, 155, 162, 16);
		panelEquipment.add(lblnoteUpgradesCost);
		
		lblCharacter = new JLabel("");
		lblCharacter.setBounds(350, 37, 300, 340);
		panelEquipment.add(lblCharacter);
		
		lblGoldAmount = new JLabel("Gold:");
		lblGoldAmount.setForeground(Color.LIGHT_GRAY);
		lblGoldAmount.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblGoldAmount.setBounds(189, 60, 41, 16);
		panelEquipment.add(lblGoldAmount);

		
		rdbtnSword = new JRadioButton("Sword");
		rdbtnSword.setForeground(Color.LIGHT_GRAY);
		rdbtnSword.setBounds(8, 54, 65, 25);
		panelEquipment.add(rdbtnSword);
		rdbtnSword.setOpaque(false);
		//selects the sword to use and gets all stat info for it and sends it to labels
		rdbtnSword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSword.setSelected(true);
				rdbtnBow.setSelected(false);
				rdbtnStaff.setSelected(false);
				lblBonuses.setText("Knights will receive a 2x attack");
				lblBonuses2.setText("and blocking bonus with a");
				lblBonuses3.setText("sword.");
				weaponSelect = "Sword";
				if(toonObj.getSelection() == "Knight"){
					int atk = swObj.getAttack("Knight");
					int blk = swObj.getBlock("Knight");
					lblWeaponDisplay.setText("Attack:  " + atk + " (Bonus Included)");
					lblWeaponDisplay2.setText("Block:  " + blk + " (Bonus Included)");
				}//end if
				else{
					
				lblWeaponDisplay.setText("Attack:  " + swObj.getAttack(toonObj.getSelection()));
				lblWeaponDisplay2.setText("Block:  " + swObj.getBlock(toonObj.getSelection()));
				}//end else
			}//end actionPerformed
		});
		
		lblCurrentgold = new JLabel("");
		lblCurrentgold.setForeground(Color.LIGHT_GRAY);
		lblCurrentgold.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblCurrentgold.setBounds(229, 60, 87, 16);
		panelEquipment.add(lblCurrentgold);
		
		
		
		btnSwordUpgrade = new JButton("+ Upgrade");
		btnSwordUpgrade.setBounds(80, 56, 97, 25);
		panelEquipment.add(btnSwordUpgrade);
		//Upgrades the sword, checks gold to make sure sufficient and updates satats/labels
		btnSwordUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int goldAmt = toonObj.getGoldAmount();
				if(goldAmt >= 50){
					
					swObj.upgrade();
				}//end if

				upgradeCost();
				if(toonObj.getSelection() == "Knight"){
					int atk = swObj.getAttack("Knight");
					int blk = swObj.getBlock("Knight");
					lblWeaponDisplay.setText("Attack:  " + atk + " (Bonus Included)");
					lblWeaponDisplay2.setText("Block:  " + blk + " (Bonus Included)");
				}//end if
				else{
					
				lblWeaponDisplay.setText("Attack:  " + swObj.getAttack(toonObj.getSelection()));
				lblWeaponDisplay2.setText("Block:  " + swObj.getBlock(toonObj.getSelection()));
				}//end else
			}
		});
		
		rdbtnBow = new JRadioButton("Bow");
		rdbtnBow.setForeground(Color.LIGHT_GRAY);
		rdbtnBow.setBounds(8, 90, 53, 25);
		panelEquipment.add(rdbtnBow);
		rdbtnBow.setOpaque(false);
		//Selects the bow to use and updates the labels and calculates bonuses
		rdbtnBow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSword.setSelected(false);
				rdbtnBow.setSelected(true);
				rdbtnStaff.setSelected(false);
				lblBonuses.setText("Rangers will receive a 2x attack");
				lblBonuses2.setText("and blocking bonus with a");
				lblBonuses3.setText("bow.");
				weaponSelect = "Bow";
				if(toonObj.getSelection() == "Ranger"){
					int atk = bowObj.getAttack("Ranger");
					int blk = bowObj.getBlock("Ranger");
					lblWeaponDisplay.setText("Attack:  " + atk + " (Bonus Included)");
					lblWeaponDisplay2.setText("Block:  " + blk + " (Bonus Included)");
				}//end if
				else{
					
				lblWeaponDisplay.setText("Attack:  " + bowObj.getAttack(toonObj.getSelection()));
				lblWeaponDisplay2.setText("Block:  " + bowObj.getBlock(toonObj.getSelection()));
				}//end else
			}//end actionPerformed
		});
		
		lblBonuses2 = new JLabel("");
		lblBonuses2.setForeground(Color.LIGHT_GRAY);
		lblBonuses2.setBounds(40, 374, 180, 25);
		panelEquipment.add(lblBonuses2);
		
		lblBonuses = new JLabel("");
		lblBonuses.setForeground(Color.LIGHT_GRAY);
		lblBonuses.setBounds(40, 344, 185, 25);
		panelEquipment.add(lblBonuses);
		
		lblBonuses3 = new JLabel("");
		lblBonuses3.setForeground(Color.LIGHT_GRAY);
		lblBonuses3.setBounds(40, 400, 180, 25);
		panelEquipment.add(lblBonuses3);
		
		lblEquipmentBonuses = new JLabel("Equipment Bonuses:");
		lblEquipmentBonuses.setForeground(Color.LIGHT_GRAY);
		lblEquipmentBonuses.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEquipmentBonuses.setBounds(12, 311, 142, 25);
		panelEquipment.add(lblEquipmentBonuses);
		
		JButton btnGoFight = new JButton("Go Fight!");
		//Checks the class selected and sets character label up appropriately, also grabs name for label, sets up enemy name and description as well as health
		btnGoFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buttonCount += 1;
				panelTitle.setVisible(false);
				panelToon.setVisible(false);
				panelEquipment.setVisible(false);
				panelFight.setVisible(true);
				panelDead.setVisible(false);
				lblPlayer.setText(toonObj.getName());
				String classSelect = toonObj.getSelection();
				if(classSelect == "Knight"){
					lblPlayerHealth.setText("Health:  " + Integer.toString(knightObj.getHealth()));
					lblWeapon.setText(weaponSelect);
					lblArmor.setText("Plate Armor");
					
				}//end if
				else if(classSelect == "Mage"){
					lblPlayerHealth.setText("Health:  " + Integer.toString(mageObj.getHealth()));
					lblWeapon.setText(weaponSelect);
					lblArmor.setText("Cloth Armor");
					
				}//end else If
				else{
					lblPlayerHealth.setText("Health:  " + Integer.toString(rangerObj.getHealth()));
					lblWeapon.setText(weaponSelect);
					lblArmor.setText("Leather Armor");
					
				}//end else
				
				if(buttonCount == 1){
					
					lblEnemyhealth.setText("Health: " + Integer.toString(dragonObj.getHealth()));
					txtEnemydescription.setText(dragonObj.getDescription());
					lblEnemy.setText(dragonObj.getName());				
				}//end if
				else if(buttonCount >= 2 && dragonObj.getStatus() == "Dead" && lichObj.getStatus() =="Alive"){
					
					lblEnemyhealth.setText("Health: " + Integer.toString(lichObj.getHealth()));
					txtEnemydescription.setText(lichObj.getDescription());
					lblEnemy.setText(lichObj.getName());
					
				}//else if
				else if(buttonCount >= 3 && lichObj.getStatus() == "Dead"){
					
					lblEnemyhealth.setText("Health: " + Integer.toString(tarrasqueObj.getHealth()));
					txtEnemydescription.setText(tarrasqueObj.getDescription());
					lblEnemy.setText(tarrasqueObj.getName());
										
				}//end else if
		
			}//end ActionPerformed
		});
		btnGoFight.setBounds(560, 405, 97, 25);
		panelEquipment.add(btnGoFight);
		
		rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setForeground(Color.LIGHT_GRAY);
		rdbtnStaff.setBounds(8, 126, 55, 25);
		panelEquipment.add(rdbtnStaff);
		rdbtnStaff.setOpaque(false);
		//Selects the staff to use and updates labels and calculates bonuses
		rdbtnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSword.setSelected(false);
				rdbtnBow.setSelected(false);
				rdbtnStaff.setSelected(true);
				lblBonuses.setText("Mages will receive a 2x attack");
				lblBonuses2.setText("and blocking bonus with a");
				lblBonuses3.setText("staff.");
				weaponSelect = "Staff";
				if(toonObj.getSelection() == "Mage"){
					int atk = staffObj.getAttack("Mage") ;
					int blk = staffObj.getBlock("Mage") ;
					lblWeaponDisplay.setText("Attack:  " + atk + " (Bonus Included)");
					lblWeaponDisplay2.setText("Block:  " + blk + " (Bonus Included)");
				}//end if
				else{
					
				lblWeaponDisplay.setText("Attack:  " + staffObj.getAttack(toonObj.getSelection()));
				lblWeaponDisplay2.setText("Block:  " + staffObj.getBlock(toonObj.getSelection()));
				}//end else
			}//end actionPerformed
		});
		
		btnBonusGold = new JButton("Bonus Gold");
		//Bonus gold button, calls the random number generator and multiplies the result by 50 to add bonus gold
		btnBonusGold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(bonusGoldButton == 1){
					JOptionPane.showMessageDialog(null, "Don't be greedy, only one click allowed.");
				}//end if
				else{
				bonusGoldButton = 1;
				int result = gameObj.randNumber();
				int addGold = result * 50;
				JOptionPane.showMessageDialog(null, "You rolled a " + Integer.toString(result) + " so you get " + Integer.toString(addGold) + " gold.");
				toonObj.setGoldAmount(toonObj.getGoldAmount() + addGold);
				lblCurrentgold.setText(Integer.toString(toonObj.getGoldAmount()));
				}//end else
			}
		});
		btnBonusGold.setBounds(221, 245, 117, 25);
		panelEquipment.add(btnBonusGold);
		
		btnStaffUpgrade = new JButton("+ Upgrade");
		btnStaffUpgrade.setBounds(80, 126, 97, 25);
		panelEquipment.add(btnStaffUpgrade);
		//Upgrades the staff, checks gold to make sure sufficient and updates satats/labels
		btnStaffUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int goldAmt = toonObj.getGoldAmount();
				if(goldAmt >= 50){
					
					staffObj.upgrade();
				}//end if
				upgradeCost();
				if(toonObj.getSelection() == "Mage"){
					int atk = staffObj.getAttack("Mage") ;
					int blk = staffObj.getBlock("") ;
					lblWeaponDisplay.setText("Attack:  " + atk + " (Bonus Included)");
					lblWeaponDisplay2.setText("Block:  " + blk + " (Bonus Included)");
				}//end if
				else{
					
				lblWeaponDisplay.setText("Attack:  " + staffObj.getAttack(toonObj.getSelection()));
				lblWeaponDisplay2.setText("Block:  " + staffObj.getBlock(toonObj.getSelection()));
				}//end else
				
			}
		});
		
		btnBowUpgrade = new JButton("+ Upgrade");
		btnBowUpgrade.setBounds(80, 90, 97, 25);
		panelEquipment.add(btnBowUpgrade);
		//Bow Upgrade Button - checks gold amount and if sufficient upgrades the stats, deducts gold, and upgrades the label
		btnBowUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int goldAmt = toonObj.getGoldAmount();
				if(goldAmt >= 50){
					
					bowObj.upgrade();
				}//end if
				upgradeCost();
				if(toonObj.getSelection() == "Ranger"){
					int atk = bowObj.getAttack("Ranger");
					int blk = bowObj.getBlock(toonObj.getSelection());
					lblWeaponDisplay.setText("Attack:  " + atk + " (Bonus Included)");
					lblWeaponDisplay2.setText("Block:  " + blk + " (Bonus Included)");
				}//end if
				else{
					
				lblWeaponDisplay.setText("Attack:  " + bowObj.getAttack(toonObj.getSelection()));
				lblWeaponDisplay2.setText("Block:  " + bowObj.getBlock(toonObj.getSelection()));
				}//end else
				
			}
		});
		
		lblWeaponDisplay2 = new JLabel("");
		lblWeaponDisplay2.setForeground(Color.LIGHT_GRAY);
		lblWeaponDisplay2.setBounds(40, 270, 180, 25);
		panelEquipment.add(lblWeaponDisplay2);
		
		lblWeaponDisplay = new JLabel("");
		lblWeaponDisplay.setForeground(Color.LIGHT_GRAY);
		lblWeaponDisplay.setBounds(40, 245, 180, 25);
		panelEquipment.add(lblWeaponDisplay);
		
		JLabel lblWeaponStats = new JLabel("Weapon Stats:");
		lblWeaponStats.setForeground(Color.LIGHT_GRAY);
		lblWeaponStats.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWeaponStats.setBounds(12, 220, 117, 16);
		panelEquipment.add(lblWeaponStats);
		
		lblBackgroundPic = new JLabel("");
		lblBackgroundPic.setBounds(0, 0, 660, 441);
		panelEquipment.add(lblBackgroundPic);
		lblBackgroundPic.setIcon(icons[5]);
		
		panelFight = new JPanel();
		contentPane.add(panelFight, "name_5347773159555");
		panelFight.setLayout(null);
		
		JLabel lblBattle = new JLabel("Battle!");
		lblBattle.setForeground(Color.LIGHT_GRAY);
		lblBattle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblBattle.setBounds(300, -12, 71, 63);
		panelFight.add(lblBattle);
		
		lblPlayer = new JLabel("Player");
		lblPlayer.setForeground(Color.LIGHT_GRAY);
		lblPlayer.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblPlayer.setBounds(12, 48, 153, 26);
		panelFight.add(lblPlayer);
		
		lblEnemyhealth = new JLabel("EnemyHealth");
		lblEnemyhealth.setForeground(Color.LIGHT_GRAY);
		lblEnemyhealth.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblEnemyhealth.setBounds(30, 232, 90, 18);
		panelFight.add(lblEnemyhealth);
		
		lblArmor = new JLabel("Armor");
		lblArmor.setForeground(Color.LIGHT_GRAY);
		lblArmor.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblArmor.setBounds(30, 120, 120, 18);
		panelFight.add(lblArmor);
		
		lblEnemy = new JLabel("Enemy");
		lblEnemy.setForeground(Color.LIGHT_GRAY);
		lblEnemy.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEnemy.setBounds(12, 195, 230, 26);
		panelFight.add(lblEnemy);
		
		txtEnemydescription = new JTextArea();
		txtEnemydescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtEnemydescription.setText("EnemyDescription");
		txtEnemydescription.setBounds(12, 258, 220, 140);
		panelFight.add(txtEnemydescription);
		
		lblWeapon = new JLabel("Weapon");
		lblWeapon.setForeground(Color.LIGHT_GRAY);
		lblWeapon.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblWeapon.setBounds(30, 100, 90, 18);
		panelFight.add(lblWeapon);
		
		lblPlayerHealth = new JLabel("Health");
		lblPlayerHealth.setForeground(Color.LIGHT_GRAY);
		lblPlayerHealth.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblPlayerHealth.setBounds(30, 80, 90, 18);
		panelFight.add(lblPlayerHealth);
		
		btnStartBattle = new JButton("Start Battle");
		//starts the combat and calls the combat method, also stops the pressing of the button if its already been clicked for the fight
		btnStartBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fightManager == 0){
					fightManager += 1;
					int theCount = battleCounter();
					String name = toonObj.getName();
					combat(theCount, name);		
				}//end if
				else{
					JOptionPane.showMessageDialog(null, "ARE YOU TRYING TO BREAK MY GAME RUSSELL?!?");				
				}//end else
				
			}//end actionPerformed
		});
		btnStartBattle.setBounds(30, 155, 105, 25);
		panelFight.add(btnStartBattle);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 48, 409, 350);
		panelFight.add(scrollPane);
		
		txtCombatArea = new JTextArea();
		scrollPane.setViewportView(txtCombatArea);
		
		JButton btnNextFight = new JButton("Next Fight");
		//Moves the user to the next boss fight if the previous boss has been defeated
		btnNextFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inProgress == 1){
					JOptionPane.showMessageDialog(null, "You cannot proceed before beating this boss.");
				}//end if
				else{
				fightManager = 0;
				buttonCount += 1;
				panelTitle.setVisible(false);
				panelToon.setVisible(false);
				panelEquipment.setVisible(false);
				panelFight.setVisible(true);
				panelDead.setVisible(false);
				lblPlayer.setText(toonObj.getName());
				txtCombatArea.setText("");
				String classSelect = toonObj.getSelection();
				if(classSelect == "Knight"){
					lblPlayerHealth.setText("Health:  " + Integer.toString(knightObj.getHealth()));
					lblWeapon.setText(weaponSelect);
					lblArmor.setText("Plate Armor");
					
				}//end if
				else if(classSelect == "Mage"){
					lblPlayerHealth.setText("Health:  " + Integer.toString(mageObj.getHealth()));
					lblWeapon.setText(weaponSelect);
					lblArmor.setText("Cloth Armor");
					
				}//end else If
				else{
					lblPlayerHealth.setText("Health:  " + Integer.toString(rangerObj.getHealth()));
					lblWeapon.setText(weaponSelect);
					lblArmor.setText("Leather Armor");
					
				}//end else
				
				if(buttonCount == 1){
					
					lblEnemyhealth.setText("Health: " + Integer.toString(dragonObj.getHealth()));
					txtEnemydescription.setText(dragonObj.getDescription());
					lblEnemy.setText(dragonObj.getName());				
				}//end if
				else if(buttonCount >= 2 && dragonObj.getStatus() == "Dead" && lichObj.getStatus() =="Alive"){
					
					lblEnemyhealth.setText("Health: " + Integer.toString(lichObj.getHealth()));
					txtEnemydescription.setText(lichObj.getDescription());
					lblEnemy.setText(lichObj.getName());
					
				}//else if
				else if(buttonCount >= 3 && lichObj.getStatus() == "Dead"){
					
					lblEnemyhealth.setText("Health: " + Integer.toString(tarrasqueObj.getHealth()));
					txtEnemydescription.setText(tarrasqueObj.getDescription());
					lblEnemy.setText(tarrasqueObj.getName());
										
				}//end else if
				else{
					
					JOptionPane.showMessageDialog(null, "You cannot proceed before beating this boss.");
					
				}//else
				}//end else
				
				
				
				
			}//end Action Performed
		});
		btnNextFight.setBounds(558, 411, 97, 25);
		panelFight.add(btnNextFight);
		
		JButton btnGoBack = new JButton("Equipment");
		//Takes the user back to the equipment screen, if not in battle
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inProgress == 1){
					
					JOptionPane.showMessageDialog(null, "You cannot flee a boss fight!");
					
				}//end if
				else{
				
				fightManager = 0;
				txtCombatArea.setText("");
				panelTitle.setVisible(false);
				panelToon.setVisible(false);
				panelEquipment.setVisible(true);
				panelFight.setVisible(false);
				panelDead.setVisible(false);
				}
			}
		});
		btnGoBack.setBounds(444, 411, 97, 25);
		panelFight.add(btnGoBack);
		
		lblBackgroundPicture = new JLabel("");
		lblBackgroundPicture.setBounds(0, 0, 660, 441);
		panelFight.add(lblBackgroundPicture);
		lblBackgroundPicture.setIcon(icons[5]);
		
		panelDead = new JPanel();
		panelDead.setBackground(Color.BLACK);
		contentPane.add(panelDead, "name_77431275676444");
		panelDead.setLayout(null);

		
		JLabel lblDead = new JLabel("You Are Dead");
		lblDead.setForeground(Color.RED);
		lblDead.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblDead.setBounds(232, 121, 200, 55);
		panelDead.add(lblDead);
		
		lblSuck = new JLabel("Try Not Sucking Next Time");
		lblSuck.setForeground(Color.WHITE);
		lblSuck.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblSuck.setBounds(232, 177, 196, 23);
		panelDead.add(lblSuck);
		
		JButton btnQuit = new JButton("Quit");
		//Closes the application
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		btnQuit.setBounds(279, 259, 97, 25);
		panelDead.add(btnQuit);
		
		panelVictory = new JPanel();
		contentPane.add(panelVictory, "name_335508355696000");
		panelVictory.setLayout(null);
		
		lblBg = new JLabel("");
		lblBg.setBounds(0, 0, 660, 441);
		panelVictory.add(lblBg);
		lblBg.setIcon(icons[6]);
	}//End of AppFrame

	//  Gets the character picture for the class selection
	private void getCharacterPicture(){
		//region
		if(toonObj.getSelection() == "Knight"){
			lblCharacter.setIcon(icons[0]);
		}
		else if(toonObj.getSelection() == "Mage"){
			lblCharacter.setIcon(icons[1]);
			
		}
		else{
			lblCharacter.setIcon(icons[2]);
		}
		
		
	}//end getCharacterPicture
	//endregion
	
	//Checks the amount of gold player is trying to use to upgrade and if sufficient reduces it by 50 and updates the label.  if not sufficient displays message
	private void upgradeCost(){
		
		
		int gold = toonObj.getGoldAmount();
		if(gold < 50){
			JOptionPane.showMessageDialog(null, "You do not have enough gold to do this. \n"
					+ "Try fighing some monsters to get more");
		}//end if
		else{
					
			toonObj.setGoldAmount(gold - 50);
			int newGold = toonObj.getGoldAmount();
			lblCurrentgold.setText(Integer.toString(newGold));

		}//end else
		
	}//end upgradeCost
	
	//Combat method that handles the battle/Multi-Parameter constructor
	public void combat(int theCount, String name){
		
		//Object arrays to minimize combat code
		Toon toonArray[] =  new Toon[3];
		toonArray[0] = knightObj;
		toonArray[1] = mageObj;
		toonArray[2] = rangerObj;
		
		Weapon weaponArray[] = new Weapon[3];
		weaponArray[0] = swObj;
		weaponArray[1] = staffObj;
		weaponArray[2] = bowObj;
		
		Enemies enemyArray[] = new Enemies[3];
		enemyArray[0] = dragonObj;
		enemyArray[1] = lichObj;
		enemyArray[2] = tarrasqueObj;
		
		//instantiation of variable the array calls will use
		int classHold = 0;
		int weaponHold = 0;
		
		
		
		//Class Selection modifier
		if(toonObj.getSelection() == "Mage"){
			classHold = 1;			
		}//end if
		else if(toonObj.getSelection() == "Ranger"){
			classHold = 2;
		}//end else if
		
		//Weapon Selection Modifier
		if(weaponSelect == "Staff"){
			weaponHold = 1;
			
		}//end if
		else if(weaponSelect == "Bow"){
			weaponHold = 2;
			
		}//end else if
		String attacking = toonArray[classHold].attack();


		
		//TimerStart
		if (timer.isRunning()){
			
		}
		else{
			timer.start();
		}
		
		
			//Starts the combat by checking the health of each combatant
			if(toonArray[classHold].getHealth() > 0 && enemyArray[bossNumber].getHealth() > 0 ){
				//Where blows are dealt and health is recalculated
				inProgress = 1;
				txtCombatArea.append(enemyArray[bossNumber].getName() + " attacks " + toonObj.getName() + " for " + Integer.toString(enemyArray[bossNumber].attack()) + " damage. \n");
				txtCombatArea.append(toonObj.getName() + " blocks for " + weaponArray[weaponHold].getBlock(toonObj.getSelection()) + ". \n");
				int exchange = (toonArray[classHold].getHealth() + weaponArray[weaponHold].getBlock(toonObj.getSelection())) - enemyArray[bossNumber].attack();
				if(enemyArray[bossNumber].attack() < weaponArray[weaponHold].getBlock(toonObj.getSelection())){
					toonArray[classHold].setHealth(toonArray[classHold].getHealth());
				}//end if
				else{
					
					lblPlayerHealth.setText("Health:  " + Integer.toString(exchange));
					toonArray[classHold].setHealth(exchange);
					
				}//end else
				if(enemyArray[bossNumber].attack() < weaponArray[weaponHold].getBlock(toonObj.getSelection())){
					txtCombatArea.append(toonObj.getName() + " takes no damage.\n\n");
				}//end if
				else{
					txtCombatArea.append(toonObj.getName() + "'s health is now " + Integer.toString(exchange) + ". \n\n");
	
					
				}//end else
	
				txtCombatArea.append(toonObj.getName() + attacking + enemyArray[bossNumber].getName() + " for " + Integer.toString(toonArray[classHold].getMainStat() + weaponArray[weaponHold].getAttack(toonObj.getSelection())) + ". \n");
				exchange = (toonArray[classHold].getMainStat() + weaponArray[weaponHold].getAttack(toonObj.getSelection()) - enemyArray[bossNumber].getBlockRating());
				txtCombatArea.append(enemyArray[bossNumber].getName() + " blocks for " + Integer.toString(enemyArray[bossNumber].getBlockRating()) + ". \n");

				if(enemyArray[bossNumber].getHealth() - exchange <= 0){
					
					txtCombatArea.append(toonObj.getName() + " has slain " + enemyArray[bossNumber].getName());
					lblEnemyhealth.setText("Health: Dead");
				}//end if
				else{
					lblEnemyhealth.setText("Health:  " + Integer.toString(enemyArray[bossNumber].getHealth() - exchange));
					txtCombatArea.append(enemyArray[bossNumber].getName() +  "'s health is now " + Integer.toString(enemyArray[bossNumber].getHealth() - exchange) + ". \n\n");
					
				}//end else
				enemyArray[bossNumber].setHealth((enemyArray[bossNumber].getHealth() - exchange));
			}//end if
			if(enemyArray[bossNumber].getBlockRating() > (toonArray[classHold].getMainStat() + weaponArray[weaponHold].getAttack(toonObj.getSelection()))){
				txtCombatArea.append(enemyArray[bossNumber].getName() + " takes no damage.\n\n");
			}//end if

			//Checking if the boss is dead before going another round
			if(enemyArray[bossNumber].getHealth() <= 0){
				//If the final boss is defeated a message is displayed with a choice				
				if(enemyArray[bossNumber].getName() == "The Tarrasque"){
			        //http://stackoverflow.com/questions/13334198/java-custom-buttons-in-showinputdialog
					timer.stop();
					Object[] options1 = { "Go To The Moon", "Quit" };
			        
					JPanel panel = new JPanel();
			        panel.add(new JLabel("You beat the Tarrasque!"
			        					+ "  Now what?"));

					
					int result = JOptionPane.showOptionDialog(null, panel, "Victory",
			                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
			                null, options1, null);
			        if (result == JOptionPane.YES_OPTION){

				        
						panelTitle.setVisible(false);
						panelToon.setVisible(false);
						panelEquipment.setVisible(false);
						panelFight.setVisible(false);
						panelDead.setVisible(false);
						panelVictory.setVisible(false);
						panelVictory.setVisible(true);
				       }
			        else{
			        	System.exit(0);
			        }
				}//end if
				else{
					//If it is not the final boss, labels the boss as dead and moves on
					inProgress = 0;
					enemyArray[bossNumber].setStatus("Dead");
					bossNumber += 1;
					txtCombatArea.append("\n\n" + toonObj.getName() + " WINS!\n" + toonObj.getName() + " receives 100 gold.");
					int gold = toonObj.getGoldAmount();
					toonObj.setGoldAmount((gold + 100));
					lblCurrentgold.setText(Integer.toString(toonObj.getGoldAmount()));
					timer.stop();
				}//end else
				
				
			}//end else if
			else if(toonArray[classHold].getHealth() <= 0){
				
				lblPlayerHealth.setText("Dead");
				txtCombatArea.append("\n\n" + toonObj.getName() + " is dead. Game Over.");
				timer.stop();
				
				
				Object[] options1 = { "Ok" };
		        
				JPanel panel = new JPanel();
		        panel.add(new JLabel("Whelp, looks like you ended up dead. Might as well just accept it."));

				
				int result = JOptionPane.showOptionDialog(null, panel, "Defeat",
		                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
		                null, options1, null);
		        if (result == JOptionPane.YES_OPTION){
		        	
					panelTitle.setVisible(false);
					panelToon.setVisible(false);
					panelEquipment.setVisible(false);
					panelFight.setVisible(false);
		        	panelDead.setVisible(true);
		        	
		        }
				
			}//end else
	}//combat
			
	//Battle counting method to see how many bosses you have fought
	private int battleCounter(){
		
		battleCount += 1;
		
		return battleCount;
		
	}//end battleCounter

	//Timer Handler Class
	public class TimerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			combat(COMBAT_DELAY, weaponSelect);
			
		}
	}// end class TimerHandler
}//end AppFrame
