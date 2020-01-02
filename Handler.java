import java.util.ArrayList;
public class Handler {
    /**
     * @ this class will execute the commands and communicate with other class
     */

    /**
     * this method will execute commands in the "turn"
     * @param command
     */
    String[] plantsName = {"Peashooter" , "Snow Pea" , "Cabbage-pult" , "Repeater" , "Threepeater" , "Cactus" , "Gatling Pea" ,
    "Scaredy-shroom" , "Kernel-pult" , "Split Pea" , "Explode-o-nut" , "Melon-pult" , "Lily Pad" , "Winter Melon" , "Wall-nut"
    , "Tangle Kelp" , "Tall-nut" , "Cattail" , "Potato Mine" , "Cherry Bomb" , "Magnet-shroom" , "Sunflower" , "Twin Sunflower"
    , "Jalapeno"};
    // this data member will determine the turns that needs to be passed for generating another sun
    int currentSunGenerationPeriod;
    // this data member will determine the number of last turn in which a sun has been generated
    int lastTurnSunGenerated;
    // this boolean data member will indicate whether the game is continued or not;
    boolean gameIsFinished;
    ArrayList<Cards> reachedPlantCards = new ArrayList<Cards>();
    ArrayList<Cards> selectedPlantCards = new ArrayList<Cards>();
    ArrayList<Zombie> killedZombies = new ArrayList<Zombie>();
    //should be implement the rest time and price
    Cards SunFlower = new Cards("SunFlower", 1, 2);
    Cards CherryBomb = new Cards("CherryBomb", 2, 4);
    Cards PeaShooter = new Cards("PeaShooter", 2, 2);
    Cards SnowPea = new Cards("SnowPea", 3, 3);
    Cards ScaredyShroom = new Cards("ScaredyShroom",1, 2 );
    Cards Kernelplut = new Cards("Kernelplut",3, 3 );
    Cards WallNut = new Cards("WallNut", 2, 4 );
    {
        reachedPlantCards.add(CherryBomb);
        reachedPlantCards.add(PeaShooter);
        reachedPlantCards.add(SnowPea);
        reachedPlantCards.add(ScaredyShroom);
        reachedPlantCards.add(Kernelplut);
        reachedPlantCards.add(WallNut);
    }
    ArrayList<Cards> reachedZombieCards = new ArrayList<Cards>();
    ArrayList<Cards> selectedZombieCards = new ArrayList<Cards>();
    Cards Zombie = new Cards(2 ,  2, "Zombie");
    Cards FootballZombie = new Cards(3,  4,"FootballZombie");
    Cards ConeheadZombie = new Cards( 2, 3, "ConeheadZombie");
    Cards BungeeZombie = new Cards( 0, 3, "BungeeZombie");
    Cards BalloonZombie = new Cards(2, 3 , "BalloonZombie");
    Cards Zomboni = new Cards(2, 3,"Zomboni" );
    Cards ScreenDoorZombie = new Cards(2, 2, "ScreenDoorZombie" );
    {
        reachedZombieCards.add(Zombie);
        reachedZombieCards.add(FootballZombie);
        reachedZombieCards.add(ConeheadZombie);
        reachedZombieCards.add(BungeeZombie);
        reachedZombieCards.add(BalloonZombie);
        reachedZombieCards.add(Zomboni);
        reachedZombieCards.add(ScreenDoorZombie);
    }

    Cards currentCard = new Cards(); // THIS IS THE CARD WHICH PLAYER SELECT TO PLANT IN GARDEN
    String name; // the name of gametype
    public Handler(String name){
        this.name = name;
    }

    public void execute(String command, ArrayList<Plant> plants, Garden garden, ArrayList<Zombie> zombies, Console console, CommandScanner scanner){
        String[] splitedCommand = new String[10];
        splitedCommand = command.split(" ");
        ArrayList<Cards> reachedCards = new ArrayList<Cards>(); // this arraylist is for the all cards that player has
      //  ArrayList<Cards> selectedCards = new ArrayList<Cards>();// this arraylist is for the all 7 selected cards that player has
        // if the command is "select", then :
        /*if (splitedCommand[0].equals("Collection") && splitedCommand[1].equals("Plants")){
            setCollectionPlants(selectedPlantCards , reachedPlantCards);
        }*/
        if (command.equals("Show hand")){
            getSelectedCard(selectedPlantCards, console);
        }
        else if (splitedCommand[0].equals("Select") && !this.name.equals("Rail")){
            for (int counter = 0 ; counter < selectedPlantCards.size() ; counter++){
                if (splitedCommand[1].equals(selectedPlantCards.get(counter).getName())){
                    // implement a method to check out the price and rest time of plants against the sunsvalue

                       currentCard = selectedPlantCards.get(counter);


                }
            }
        }
        else if (splitedCommand[0].equals("Plant")){
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            planting(row, column, garden, console);
        }
        else if (splitedCommand[0].equals("Remove")){
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            remove(row, column, plants, garden);
        }
        else if(command.equals("Show lawn")){
           console.printCommand("EXISTED PLANTS");
            System.out.println("NO." + "        " + "PLANT NAME" + "        " + "HEALTH");
            for (int counter = 0 ; counter < plants.size() ; counter++){
                console.printCommand(counter + 1 + "      " + plants.get(counter).getName() + "       " + plants.get(counter).getHealth());
            }
           console.printCommand("EXISTED ZOMBIES");
            console.printCommand("NO." + "        " + "ZOMBIE NAME" + "        " + "HEALTH");
            for (int counter = 0 ; counter < plants.size() ; counter++){
                console.printCommand(counter + 1 + "      " + zombies.get(counter).getName() + "       " + zombies.get(counter).getHealth());
            }
        }
        if (this.name.equals("Day")){
            // this part is "Collection" for "Day"
            int j = 0 ;
            if(j == 0){
                collectionPartOfDay(scanner, console);
                j = 1;
            }
        }
        else if (this.name.equals("Water")){
            int j = 0 ;
            if(j == 0){
                collectionPartOfDay(scanner, console);
                j = 1;
            }
        }
        else if (this.name.equals("Rail")){
            ArrayList<Cards> railCards = new ArrayList<Cards>();
            if (command.equals("List")){
                for (int counter = 0 ; counter < railCards.size() ; counter++){
                   console.printCommand(railCards.get(counter).getName() + " */*/* ");
                }
            }
            else if(splitedCommand[0].equals("Select")){
                currentCard = railCards.get(Integer.parseInt(splitedCommand[1]));
                railCards.remove(Integer.parseInt(splitedCommand[1]));
            }
            else if(command.equals("Record")){
              console.printCommand("No.of killed Zombies : ");
                // should "sout" the num of kiiled zombies
            }
        }
        else if (this.name.equals("Zombie")){
            int j = 0 ;
            if(j == 0){
                collectionPartOfZombie(scanner, console);
                j = 1;
            }
            if (splitedCommand[0].equals("Put")){
                int rowNo = Integer.parseInt(splitedCommand[3]);
                /// check the money and row
                boolean moneyIsEnough = false;
                int zombieCounterOnRow = 0;
                boolean twoZombieIsOnRow = false; // this boolean used for checking that two zombie is on row or not
                for (int counter = 0 ; counter < garden.getGarden()[rowNo].length ; counter++){
                    if (garden.getGarden()[rowNo][counter].contains("Zomb")){
                        zombieCounterOnRow += 1;
                    }
                    if (zombieCounterOnRow == 2){
                        twoZombieIsOnRow = true;
                        break;
                    }
                }
                if (twoZombieIsOnRow){
                    console.printCommand("there is two zombie in this row :(");
                }
                if (!twoZombieIsOnRow && moneyIsEnough){
                    if (garden.getGarden()[rowNo][20].equals("mt")){
                        planting(rowNo, 20, garden, console);
                    }
                    else if (garden.getGarden()[rowNo][21].equals("mt")){
                        planting(rowNo, 21, garden, console);
                    }
                    else{
                        console.printCommand("can not put :( ");
                    }

                }
            }
        }
        else if (this.name.equals("PvP")){

        }
    }
    public void collectionPartOfDay(CommandScanner scanner, Console console){
        String tempCommand = new String();
        String[] tempCommandSplit = new String[10];
        int i = 0;
        if (i == 0){
            console.printCommand("You are in Day/Water Collection part *+*");
            i = 1;
        }
        scanner.scanCommands();
        tempCommand = scanner.getCommand();

        while (selectedPlantCards.size() < 8 || !tempCommand.equals("Play")){

            tempCommandSplit = tempCommand.split(" ");

            if (tempCommand.equals("Show hand")){
                collectionShowHand(selectedPlantCards, console);
            }
            else if(tempCommand.equals("Show collection")){
                collectionShowCollection(selectedPlantCards, reachedPlantCards, console);
            }
            else if(tempCommandSplit[0].equals("select")){
                String cardName = new String();
                cardName = tempCommandSplit[1];
                setCollectionPlants(selectedPlantCards, reachedPlantCards, cardName, console);
            }
            scanner.scanCommands();
            tempCommand = scanner.getCommand();
        }

    }
    public void collectionPartOfZombie(CommandScanner scanner, Console console){
        String tempCommand = new String();
        String[] tempCommandSplit = new String[10];
        int i = 0;
        if (i == 0){
            console.printCommand("You are in Zombie Collection part *+*");
            i = 1;
        }
        scanner.scanCommands();
        tempCommand = scanner.getCommand();

        while (selectedZombieCards.size() < 8 || !tempCommand.equals("Play")){

            tempCommandSplit = tempCommand.split(" ");

            if (tempCommand.equals("Show hand")){
                collectionShowHand(selectedZombieCards, console);
            }
            else if(tempCommand.equals("Show collection")){
                collectionShowCollection(selectedZombieCards, reachedZombieCards, console);
            }
            else if(tempCommandSplit[0].equals("select")){
                String cardName;
                cardName = tempCommandSplit[1];
                setCollectionPlants(selectedZombieCards, reachedZombieCards, cardName, console);
            }
            scanner.scanCommands();
            tempCommand = scanner.getCommand();
        }

    }
    // this method is for showing the unselectedCards
    public void collectionShowCollection(ArrayList<Cards> selectedPlantCards, ArrayList<Cards> reachedPlantCards, Console console){
        ArrayList<Cards> tempreached = new ArrayList<Cards>();
        ArrayList<Cards> unselected = new ArrayList<Cards>();
        for (int counter = 0 ; counter < reachedPlantCards.size() ; counter++){
            tempreached.add(reachedPlantCards.get(counter));
        }
        for (int counter = 0 ; counter < tempreached.size() ; counter++){
            if (!tempreachedIsselected(tempreached.get(counter), selectedPlantCards)){
                unselected.add(tempreached.get(counter));
            }
        }
        console.printCommand("Unselected cards are : ");
        for (int counter = 0 ; counter < unselected.size() ; counter++){
            console.printCommand( (counter + 1) + "  #*#  " + unselected.get(counter).getName() );
        }
    }
    // this method is used in collectionShowCollection
    public boolean tempreachedIsselected(Cards card, ArrayList<Cards> selectedPlantCards){
        for (int counter = 0 ; counter < selectedPlantCards.size() ; counter++){
            if (card.getName().equals(selectedPlantCards.get(counter).getName())){
                return true;
            }
        }
        return false;
    }
    // this method is for showing the selectedPlantCard
    public void collectionShowHand(ArrayList<Cards> selectedPlantCards, Console console){
        for (int counter = 0 ; counter < selectedPlantCards.size() ; counter++){
            console.printCommand(selectedPlantCards.get(counter).getName() + "   #*# ");
        }
    }
    // the common commands betweengame :
    boolean locationIsFilled(Garden garden, int row, int column){
        if(garden.getGarden()[row][column] != "mt"){
            return true;
        }
        return false;
    }
    void setCollectionPlants(ArrayList<Cards> selectedPlantCards, ArrayList<Cards> reachedPlantCards, String cardName,Console console){
       boolean cardIsExist = false;
       boolean cardWasSelected = true;
        for (int counter = 0 ; counter < reachedPlantCards.size(); counter++){
            if (cardName.equals(reachedPlantCards.get(counter).getName())){
                 cardIsExist = true;
                 currentCard = new Cards(reachedPlantCards.get(counter).getName(), reachedPlantCards.get(counter).getPrice(), reachedPlantCards.get(counter).getRestTime());
                 break;
            }
        }
        for (int counter = 0 ; counter < selectedPlantCards.size() ; counter++){
            if (cardName.equals(selectedPlantCards.get(counter))){
                cardWasSelected = false;
                break;
            }
        }
        if (!cardIsExist){
            console.printCommand("You do not have this card :( ");
        }
        else if(cardWasSelected){
            console.printCommand("You select this card previously :( ");
        }
        else if(cardIsExist && !cardWasSelected){
            selectedPlantCards.add(currentCard);
        }

    }
    void getSelectedCard(ArrayList<Cards>cards, Console console){
        for (int counter = 0 ; counter < cards.size() ; counter++){
            console.printCommand("Card name :  " + cards.get(counter).getName() + " needed suns :  " +  cards.get(counter).getPrice() + " rest time :  " + cards.get(counter).getRestTime());
        }
    }
    public void remove(int row, int column, ArrayList<Plant> plants, Garden garden){
      for (int counter = 0 ; counter < plants.size() ; counter++){
          if (row == plants.get(counter).getLocation_x() && column == plants.get(counter).getLocation_y()){
              plants.remove(counter);
              garden.getGarden()[row][column] = "mt";
              break;
          }
      }
    }
    public void planting(int row, int column, Garden garden, Console console){
        boolean cardIsNULL = false;
        boolean locationIsFilled = false;
        if (currentCard == null){
            cardIsNULL = true;
        }
        if (locationIsFilled(garden, row, column)){
            locationIsFilled = true;
        }
        if (cardIsNULL == true){
            console.printCommand("YOU DID NOT SELECT ANY CARD :(");
        }
        if (locationIsFilled == true){
           console.printCommand("location Is Filled :( ");
        }

    }
    void showLawn(){

    }
    /**
     * this method will update garden when the "turn" is over
     * @param garden
     */
    public void updateGarden(Garden garden , ArrayList<Plant> plants, ArrayList<Zombie> zombies , ArrayList<Bullet> bullets , ArrayList<LawnMower> lawnMowers , ArrayList<Sun> suns , int turn){
        // this loop every  plant that have a special activity will do it's activity
        for (Plant plant : plants)
            plant.activity(turn , plant.getName() , zombies , suns);
        // in this loop every plant will shoot when it should
        for (Plant plant : plants)
            plant.shoot(turn , bullets , zombies);
        for (Zombie zombie : zombies)
            zombie.walk(bullets , plants , zombies , turn);
        // in this loop every zombie that has been frozen will be back to normal speed
        for (Zombie zombie : zombies){
            if(zombie.isFrozen())
                zombie.setFrozen(false);
        }
        for (Bullet bullet : bullets){
            if (bullet.getIsForward())
                bullet.moveForward(zombies);
            else
                bullet.moveBackward(zombies);
        }
        // this loop reduces the health of plants
        for (Plant plant : plants){
            for (Zombie zombie : zombies){
                if (locationIsEqual(plant , zombie )) {
                    plant.hurt(zombie , zombies , turn);
                    if (plant.getName().equals("Tangle Kelp"))
                        plants.remove(plant);
                    else if (plant.getName().equals("Potato Mine") && turn - plant.getInsertionTurn() >= 2)
                        plants.remove(plant);
                }
            }
        }
        // this loop reduces the health of zombies
        for (Bullet bullet : bullets){
            for (Zombie zombie : zombies){
                if (locationIsEqual(bullet , zombie)) {
                    zombie.hurt(bullet , bullets);
                    bullets.remove(bullet);
                }
            }
        }
        // this loop will kill the dead plants
        for (Plant plant : plants){
            if (plant.getHealth() <= 0)
                plants.remove(plant);
        }
        // this loop will kill the dead zombies except zombonie
        for (Zombie zombie : zombies){
            if (zombie.getHealth() <= 0){
                if (!zombie.getName().contains("Zombonie")) {
                    killedZombies.add(zombie);
                    zombies.remove(zombie);
                }
                else {
                    zombies.add(new Zombie("Zombie" , turn));
                    zombies.get(zombies.size()-1).setLocation_x(zombie.getLocation_x());
                    zombies.get(zombies.size()-1).setLocation_y(zombie.getLocation_y());
                    zombies.remove(zombie);
                }
            }
        }
        makeSun(suns , turn , lastTurnSunGenerated , currentSunGenerationPeriod);
        // this method will check the lawnmowers and if it is necessary will use them
        checkLawnMower(lawnMowers , zombies);
        gameIsFinished = checkGameIsFinished(zombies , lawnMowers);
        updateLawn(garden , plants , zombies);
    }

    private boolean thereIsZombieInRow(Plant plant, ArrayList<Zombie> zombies) {
        for (Zombie zombie : zombies){
            if (zombie.getLocation_y() == plant.getLocation_y())
                return true;
        }
        return false;
    }

    private void makeSun(ArrayList<Sun> suns, int turn , int lastTurnSunGenerated , int currentSunGenerationPeriod) {
        if (turn - lastTurnSunGenerated == currentSunGenerationPeriod){
            suns.add(new Sun());
            this.lastTurnSunGenerated = turn;
            this.currentSunGenerationPeriod = generateRandom(2 , 4);
        }
    }
    // this method will generate random numbers between min adn max
    private int generateRandom(int min, int max) {
        double x = Math.random()*((max - min)+1)+min;
        int randomNumber = (int) x;
        return randomNumber;
    }
    private void checkLawnMower(ArrayList<LawnMower> lawnMowers, ArrayList<Zombie> zombies) {
        for (LawnMower lawnMower : lawnMowers){
            if (!lawnMower.getIsUsed() && findZombie(lawnMower.getRow(),lawnMower.getColumn(),zombies)!=null)
                lawnMower.killAllZombiesInRow(zombies);
        }
    }
    private boolean checkGameIsFinished(ArrayList<Zombie> zombies , ArrayList<LawnMower> lawnMowers) {
        for (Zombie zombie : zombies){
            if (zombie.getLocation_y() == 0){
                if (findLawnMower(zombie.getLocation_x() , lawnMowers).getIsUsed() == true)
                    return  true;
            }
        }
        return false;
    }
    //this method should update the string array of object
    private void updateLawn(Garden garden , ArrayList<Plant> plants , ArrayList<Zombie> zombies) {
       // for ()
    }
    private boolean locationIsEqual(Plant plant, Zombie zombie) {
        if (plant.getLocation_x() == zombie.getLocation_x() && plant.getLocation_y() == zombie.getLocation_y())
            return  true;
        return false;
    }
    private boolean locationIsEqual(Bullet bullet, Zombie zombie) {
        if (bullet.getLocation_x() == zombie.getLocation_x() + 1 && bullet.getLocation_y() == zombie.getLocation_y())
            return  true;
        return false;
    }
    public LawnMower findLawnMower(int row , ArrayList<LawnMower> lawnMowers){
        for (LawnMower lawnMower : lawnMowers){
            if (lawnMower.getRow() == row)
                return lawnMower;
        }
        return null;
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
    public boolean isPlant (String name , String[]plantsName){
        int counter = 0;
        for (int i = 0 ; i <plantsName.length ; i++){
            if (plantsName[i].equals(name))
                counter++;
        }
        if (counter != 0)
            return true;
        return false;
    }
    public boolean isZombie (String name){
        if (name.contains("Zomb"))
            return true;
        return false;
    }
}
