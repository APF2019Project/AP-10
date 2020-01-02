import java.util.ArrayList;

import static java.lang.Math.pow;

public class Plant {
    private String name;
    private int insertionTurn;// the turn which we plant the flower
    private int health;
    private int bulletPower;//indicate how much is hurt zombies
    private int location_x;
    private int location_y;
    private int power = 0;
    private int price;
    private int restTime;
    private int shootPeriod;
    private int numOfShoots;
    //this boolean data member determines the type of bullet (nokhod , partabe , yakhi)
    //we use this when we want to instantiate a bullet in shoot method
    private  String bulletType;
    public int getInsertionTurn() {
        return insertionTurn;
    }

    public int getLocation_y() {
        return location_y;
    }
    public int getLocation_x() {
        return location_x;
    }
    public int getHealth() {
        return health;
    }
    public String getName() {
        return name;
    }

    public Plant(String name, int insertionTurn){
        this.name = name;
        this.insertionTurn = insertionTurn;
        setProperties(name);
    }

    public void  hurt(Zombie zombie , ArrayList<Zombie> zombies , int turn){
        if (this.getName().equals("Tangle Kelp")){
            zombies.remove(zombie);
        }
        else if (this.getName().equals("Potato Mine") && turn - this.getInsertionTurn() >= 2){
            zombies.remove(zombie);
        }
        else {
            if (zombie.getName().equals("Football Zombie"))
                zombie.setHealth(zombie.getHealth() - this.power);
            this.health -= zombie.getPower();
        }

    }
    public  void shoot(int turn , ArrayList<Bullet>bullets , ArrayList<Zombie> zombies){
        if (name.equals("PeaShooter")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("SnowPea")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("Cabbage_Plut")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("Repeater")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("Threepeater")){
            if (turn - insertionTurn % shootPeriod == 0){
                bullets.add( new Bullet(this.location_x , this.location_y , this.bulletType , true , this.bulletPower));
                if (this.location_x + 1 <= 5){
                    bullets.add( new Bullet(this.location_x + 1 , this.location_y , this.bulletType , true , this.bulletPower));
                }
                if (this.location_x - 1 >= 0){
                    bullets.add( new Bullet(this.location_x -1, this.location_y , this.bulletType, true , this.bulletPower));
                }
            }
        }
        else if (name.equals("Cactus")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("GatlingPea")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }

        }
        else if (name.equals("Scaredy_Shroom")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                if (findZombie(location_x  , location_y + 1, zombies ) == null && findZombie(location_x  , location_y + 2, zombies ) == null){
                    bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
                }
            }
        }
        else if (name.equals("KernelPlut")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("SplitPea")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, false , this.bulletPower));
            }
        }
        else if (name.equals("Explode_O_Nut")){
            //does not shoot
        }
        else if (name.equals("Melon_Plut")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("LilyPad")){
            //does not shoot
        }
        else if (name.equals("Winter_Melon")){
            for (int i = 0 ; i < this.numOfShoots ; i++) {
                bullets.add(new Bullet(this.location_x, this.location_y, this.bulletType, true , this.bulletPower));
            }
        }
        else if (name.equals("Wall_Nut")){
            //does not shoot
        }
    }
    private void setProperties(String name){
        if (name.equals("PeaShooter")){
            this.price = 2;
            this.restTime = 2;
            this.health = 2;
            this.shootPeriod = 2;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 1;

        }
        else if (name.equals("SnowPea")){
            this.price = 3;
            this.restTime = 3;
            this.health = 3;
            this.shootPeriod = 3;
            this.bulletType = "yakhi";
            this.numOfShoots = 1;
        }
        else if (name.equals("Cabbage_Plut")){
            this.price = 2;
            this.restTime = 3;
            this.health = 2;
            this.shootPeriod = 2;
            this.bulletType = "partabe";
            this.bulletPower = 2;
            this.numOfShoots = 1;

        }
        else if (name.equals("Repeater")){
            this.price = 3;
            this.restTime = 4;
            this.health = 4;
            this.shootPeriod = 2;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 2;
        }
        else if (name.equals("Threepeater")){
            this.price = 4;
            this.restTime = 4;
            this.health = 5;
            this.shootPeriod = 4;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 1;

        }
        else if (name.equals("Cactus")){
            this.price = 4;
            this.restTime = 5;
            this.health = 5;
            this.shootPeriod = 2;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 1;
            this.power = 1;

        }
        else if (name.equals("GatlingPea")){
            this.price = 5;
            this.restTime = 4;
            this.health = 3;
            this.shootPeriod = 5;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 4;

        }
        else if (name.equals("Scaredy_Shroom")){
            this.price = 1;
            this.restTime = 2;
            this.health = 1;
            this.shootPeriod = 2;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 1;

        }
        else if (name.equals("KernelPlut")){
            this.price = 3;
            this.restTime = 3;
            this.health = 2;
            this.shootPeriod = 4;
            this.bulletType = "partabe";
            this.bulletPower = 1;
            this.numOfShoots = 1;

        }
        else if (name.equals("SplitPea")){
            this.price = 4;
            this.restTime = 4;
            this.health = 3;
            this.shootPeriod = 2;
            this.bulletType = "nokhod";
            this.bulletPower = 1;
            this.numOfShoots = 1;

        }
        else if (name.equals("Explode_O_Nut")){
            this.price = 4;
            this.restTime = 5;
            this.health = 3;
            this.power = 1;

        }
        else if (name.equals("Melon_Plut")){
            this.price = 3;
            this.restTime = 3;
            this.health = 3;
            this.shootPeriod = 4;
            this.bulletType = "partabe";
            this.bulletPower = 3;
            this.numOfShoots = 1;

        }
        else if (name.equals("LilyPad")){
            this.price = 0;
            this.restTime = 1;
            this.health = 1;

        }
        else if (name.equals("Winter_Melon")){
            this.price = 4;
            this.restTime = 5;
            this.health = 3;
            this.shootPeriod = 2;
            this.bulletType = "yakhi";
            this.bulletPower = 1;
            this.numOfShoots = 1;

        }
        else if (name.equals("Wall_Nut")){
            this.price = 2;
            this.restTime = 4;
            this.health = 4;

        }
        else if (name.equals("TangleKelp")){
            this.price = 3;
            this.restTime = 3;
            this.health = 0;

        }
        else if (name.equals("Tall_Nut")){
            this.price = 4;
            this.restTime = 6;
            this.health = 6;

        }
        else if (name.equals("Cattail")){
            this.price = 5;
            this.restTime = 5;
            this.health = 3;

        }
        else if (name.equals("PotatoMine")){
            this.price = 2;
            this.restTime = 3;
            this.health = 1;

        }
        else if (name.equals("CherryBomb")){
            this.price = 2;
            this.restTime = 4;
            this.health = 0;

        }
        else if (name.equals("Magnet_Shroom")){
            this.price = 4;
            this.restTime = 4;
            this.health = 2;

        }
        else if (name.equals("Sunflower")){
            this.price = 1;
            this.restTime = 2;
            this.health = 2;

        }
        else if (name.equals("TwinSunflower")){
            this.price = 3;
            this.restTime = 5;
            this.health = 2;

        }
        else if (name.equals("Jalapeno")){
            this.price = 4;
            this.restTime = 5;
            this.health = 0;

        }
    }
    public void activity(int turn , String name , ArrayList<Zombie> zombies , ArrayList<Sun> suns){
        if (name.equals("CherryBomb")){
            zombies.removeIf(this::zombieIsNeighbourOfPlant);
        }
        else if (name.equals("Magnet_Shroom")){

        }
        else if (name.equals("Sunflower")){
            if (turn - insertionTurn % 2 == 0){
                suns.add(new Sun());
            }
        }
        else if (name.equals("TwinSunflower")){
            if (turn - insertionTurn % 2 == 0){
                suns.add(new Sun());
                suns.add(new Sun());
            }
        }
        else if (name.equals("Jalapeno")){
            zombies.removeIf(zombie -> zombie.getLocation_x() == location_x);
        }

    }

    private boolean zombieIsNeighbourOfPlant(Zombie zombie) {
        if (zombie.getLocation_x() == this.location_x && zombie.getLocation_y() == this.location_y)
            return true;
        else if (zombie.getLocation_x() == this.location_x + 1 && zombie.getLocation_y() == this.location_y)
            return true;
        else if (zombie.getLocation_x() == this.location_x + 1 && zombie.getLocation_y() == this.location_y + 1)
            return true;
        else if (zombie.getLocation_x() == this.location_x  && zombie.getLocation_y() == this.location_y + 1)
            return true;
        else if (zombie.getLocation_x() == this.location_x - 1 && zombie.getLocation_y() == this.location_y + 1)
            return true;
        else if (zombie.getLocation_x() == this.location_x - 1 && zombie.getLocation_y() == this.location_y)
            return true;
        else if (zombie.getLocation_x() == this.location_x - 1 && zombie.getLocation_y() == this.location_y - 1)
            return true;
        else if (zombie.getLocation_x() == this.location_x  && zombie.getLocation_y() == this.location_y - 1)
            return true;
        else if (zombie.getLocation_x() == this.location_x + 1 && zombie.getLocation_y() == this.location_y - 1)
            return true;
        return false;
    }

    public Plant findPlant(String name , ArrayList<Plant> plants){
        for (Plant plant : plants){
            if (plant.getName().equals(name)){
                return plant;
            }
        }
        return null;
    }
    //this method will find the zombie by getting a name
    public Zombie findZombie(String name , ArrayList<Zombie> Zombies){
        for (Zombie zombie : Zombies){
            if (zombie.getName().equals(name)){
                return zombie;
            }
        }
        return null;
    }
    //this method will find zombie by getting a location
    public Zombie findZombie(int x , int y , ArrayList<Zombie> Zombies){
        for (Zombie zombie : Zombies){
            if (zombie.getLocation_y() == y && zombie.getLocation_x() == x){
                return zombie;
            }
        }
        return null;
    }
    private double distance (int x1 , int y1 , int x2 , int y2){
        double sqrt = Math.sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
        return sqrt ;
    }



}
