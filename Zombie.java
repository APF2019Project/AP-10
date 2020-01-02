
public class Zombie {
	String[][] garden = new String[6][19];
	public int turn;// it should be defined in main
	private String name;
	private int insertionTurn;
	private int health;
	private int location_x;
	private int location_y;
	private int price;
	private int speed;
	private int power;
	// if the zombie has armor the hasArmor is true and if not it is false
	private boolean hasArmor;
	// if the zombie has bucketHead this variable is true and if not it is false
	private boolean hasBucketHead;
	// if the zombie is Zomboni hasMachine & isStrong will be true
	// if the zombie is Captual Zombie hasMachine & isWeak will be true
	private boolean hasMachine;
	private boolean isStrong;
	private boolean isWeak;
	// if the zombie is Bungee Zombie
	private boolean isBungee;
	private boolean hasBallon;

	public Zombie (String name , int insertoinTurn) {
		this.insertionTurn = insertoinTurn;
		this.name = name;
		
	}
	public int getHealth() {
		return health;
	}
	public String getName() {
		return name;
	}

	public int getInsertionTurn() {
		return insertionTurn;
	}

	public int getLocation_x() {
		return location_x;
	}

	public int getLocation_y() {
		return location_y;
	}

	public int getPrice() {
		return price;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPower() {
		return power;
	}

	public boolean isHasArmor() {
		return hasArmor;
	}

	public boolean isHasBucketHead() {
		return hasBucketHead;
	}

	public boolean isHasMachine() {
		return hasMachine;
	}

	public boolean isStrong() {
		return isStrong;
	}

	public boolean isWeak() {
		return isWeak;
	}

	public boolean isBungee() {
		return isBungee;
	}

	public boolean isHasBallon() {
		return hasBallon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInsertionTurn(int insertionTurn) {
		this.insertionTurn = insertionTurn;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setLocation_x(int location_x) {
		this.location_x = location_x;
	}

	public void setLocation_y(int location_y) {
		this.location_y = location_y;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setHasArmor(boolean hasArmor) {
		this.hasArmor = hasArmor;
	}

	public void setHasBucketHead(boolean hasBucketHead) {
		this.hasBucketHead = hasBucketHead;
	}

	public void setHasMachine(boolean hasMachine) {
		this.hasMachine = hasMachine;
	}

	public void setStrong(boolean strong) {
		isStrong = strong;
	}

	public void setWeak(boolean weak) {
		isWeak = weak;
	}

	public void setBungee(boolean bungee) {
		isBungee = bungee;
	}

	public void setHasBallon(boolean hasBallon) {
		this.hasBallon = hasBallon;
	}

	public void hurt(Bullet bullet){
		if (bullet.getBulletType() == "partabe"){

		}
		if (bullet.getBulletType() == "nokhod"){

		}
		if (bullet.getBulletType() == "yakhi"){

		}
	}
	public static void walk() {
		
	}
	public void activity (String name) {
		
		if (name.equals("Zombie")) {
			
			
		}
		else if (name.equals("FootballZombie")) {
			
		}
		else if (name.equals("BucketheadZombie")) {
			
		}
		else if (name.equals("ConeheadZombie")) {
			
		}
		else if (name.equals("Zomboni)")) {
			
		}
		else if (name.equals("CatapultZombie")) {
			
		}
		else if (name.equals("BungeeZombie")) {
			
		}
		else if (name.equals("BalloonZombie")) {
			
		}
		else if (name.equals("NewspaperZombie")) {
			
		}
		else if (name.equals("TargetZombie")) {
			
		}
		else if (name.equals("ScreenDoorZombie")) {
			
		}
		else if (name.equals("Giga-gargantuarZombie")) {
			
		}
		else if (name.equals("PogoZombie")) {
			
		}
		else if (name.equals("SnorkelZombie")) {
			
		}
		else if (name.equals("DolphinRiderZombie")) {
			
		}
	}



	private void setProperties(String name) {
		if (name.equals("Zombie")) {
			
		}
		else if (name.equals("FootballZombie")) {
			
		}
		else if (name.equals("BucketheadZombie")) {
			
		}
		else if (name.equals("ConeheadZombie")) {
			
		}
		else if (name.equals("Zomboni)")) {
			
		}
		else if (name.equals("CatapultZombie")) {
			
		}
		else if (name.equals("BungeeZombie")) {
			
		}
		else if (name.equals("BalloonZombie")) {
			
		}
		else if (name.equals("NewspaperZombie")) {
			
		}
		else if (name.equals("TargetZombie")) {
			
		}
		else if (name.equals("ScreenDoorZombie")) {
			
		}
		else if (name.equals("Giga-gargantuar")) {
			
		}
		else if (name.equals("PogoZombie")) {
			
		}
		else if (name.equals("SnorkelZombie")) {
			
		}
		else if (name.equals("DolphinRiderZombie")) {
			
		}
		
		
	}
	
	
}
