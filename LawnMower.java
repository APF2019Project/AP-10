import java.util.ArrayList;

public class LawnMower {
    private  int row;
    private  int column;
    private  boolean isUsed = false;

    public LawnMower(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public void killAllZombiesInRow(ArrayList<Zombie> zombies){
        for (Zombie zombie : zombies){
            if (this.row == zombie.getLocation_y())
                zombies.remove(zombie);
        }
        this.setIsUsed(true);
    }
}
