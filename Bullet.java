import java.util.ArrayList;

public class Bullet {
    private int speed = 3;
    private int power;
    private int location_x;
    private int location_y;
    //this boolean data member determines the type of bullet (nokhod , partabe , yakhi)
    private String bulletType;
    private boolean isForward;

    public Bullet(int location_x, int location_y, String bulletType , boolean isForward , int power) {
        this.location_x = location_x;
        this.location_y = location_y;
        this.bulletType = bulletType;
        this.isForward = isForward;
        this.power = power;
    }

    public boolean getIsForward() {
        return isForward;
    }

    public int getPower() {
        return power;
    }

    public int getLocation_x() {
        return location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public String getBulletType() {
        return bulletType;
    }

    public void setLocation_x(int location_x) {
        this.location_x = location_x;
    }

    public void setLocation_y(int location_y) {
        this.location_y = location_y;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }

    public void moveForward(ArrayList<Zombie> zombies){
        for (int i = 0 ; i < this.speed ; i++){
            if (findZombie(location_x + 1 , location_y , zombies) == null) {
                this.setLocation_x(location_x + 1);
            }
            else {
                if (findZombie(location_x + 1 , location_y , zombies).getName().contains("Ballon")){
                    this.setLocation_x(location_x + 1);
                    if (this.getBulletType().equals("partabeh"))
                        break;
                }
                else{
                    this.setLocation_x(location_x + 1);
                    break;
                }
            }
        }
    }
    public void moveBackward(ArrayList<Zombie> zombies){
        for (int i = 0 ; i < this.speed ; i++){
            if (findZombie(location_x - 1 , location_y , zombies) == null) {
                this.setLocation_x(location_x - 1);
            }
            else {
                this.setLocation_x(location_x - 1);
                break;
            }
        }
    }
    public Zombie findZombie(int x , int y , ArrayList<Zombie> Zombies){
        for (Zombie zombie : Zombies) if (zombie.getLocation_y() == y && zombie.getLocation_x() == x) return zombie;
        return null;
    }
}
