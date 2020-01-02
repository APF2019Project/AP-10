import java.util.ArrayList;
public class Game {

    public static void main(String[] args) {
        /**
         * @the turn of the game is calculated with "turn"
         */
         // the row and column of garden
        int gardenRow = 6;
        int gardenColumn = 22;// the 20'th and 21st column is for putting zombies
        int turn = 0;
        // the garden is this object
        Garden garden = new Garden(gardenRow, gardenColumn);
        // the existing elements of the game are gather here
        //this is an ArrayList for keeping the mowers;
        ArrayList<LawnMower> lawnMowers = new ArrayList<LawnMower>();
        for (int i = 0 ; i < 6 ; i++){
            lawnMowers.add(new LawnMower(i , 0));
        }
        //this variable determines the last turn that a sun has been generated
        //int lastTurnSunGenerated = 0;
        //this variable determines the number of turn that should be passed to generate another sun
        //int currentRandomNumber = generateRandom(2,4);
        ArrayList<Sun> suns = new ArrayList<Sun>();
        ArrayList<Plant> plants = new ArrayList<Plant>();
        ArrayList<Zombie> zombies = new ArrayList<Zombie>();
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        ArrayList<LawnMower> lawnMovers = new ArrayList<LawnMower>();
        // the commands will scan with this object
        CommandScanner scanner = new CommandScanner();
        // the results will print with this object
        Console console = new Console();
        //the message for select the game type
        console.printCommand("PoWWWW!!!");
        console.printCommand("Enter the Game name : ");
        console.printCommand("Day   or  Water  or  Zombie  or Rail or PvP");
        // the game handler will start with getting a command
        scanner.scanCommands();
        String gameName = scanner.getCommand();
        // object game will execute the commands
        Handler handler = new Handler(gameName);
        // the collection of 7 cards that player has during the game


        /**
         * @ the game will start in this while with first command
         */
        boolean gameIsContinued = true; // when we want end game this parameter will set "false"
        while(gameIsContinued){
            while (!scanner.getCommand().equals("End turn")){
                // execute the given commands with object game
                handler.execute(scanner.getCommand(), plants, garden, zombies, console, scanner, turn);
                // this method will print the garden after updating it
                scanner.scanCommands();
            }
            // update the garden with this method of object game
           // handler.updateGarden(garden , plants , zombies , bullets , lawnMowers ,suns , gameIsContinued , turn);
            printGarden(garden.getGarden(), gardenRow, gardenColumn);
            turn++;
            console.printCommand("turn is " + turn);
            scanner.scanCommands();
            if (scanner.getCommand().equals("end")){
                System.out.println("the game is end");
                gameIsContinued = false;
            }

        }

    }
    public static void printGarden(String[][] garden, int row, int column){
        for (int rowCounter = 0 ; rowCounter < row ; rowCounter++){
            for (int columnCounter = 0 ; columnCounter < column ; columnCounter++ ){
                System.out.print(garden[rowCounter][columnCounter] + " ");
            }
            System.out.println("");
        }
    }
    // this method will generate random numbers between min adn max
    public static int generateRandom(int min, int max) {
        double x = Math.random()*((max - min)+1)+min;
        int randomNumber = (int) x;
        return randomNumber;
    }
}
