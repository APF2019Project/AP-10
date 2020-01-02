import java.util.ArrayList;

public class Handler {
    /**
     * @ this class will execute the commands and communicate with other class
     */

    /**
     * this method will execute commands in the "turn"
     *
     * @param command
     */
    String[] plantsName = {"Peashooter", "Snow Pea", "Cabbage-pult", "Repeater", "Threepeater", "Cactus", "Gatling Pea",
            "Scaredy-shroom", "Kernel-pult", "Split Pea", "Explode-o-nut", "Melon-pult", "Lily Pad", "Winter Melon", "Wall-nut"
            , "Tangle Kelp", "Tall-nut", "Cattail", "Potato Mine", "Cherry Bomb", "Magnet-shroom", "Sunflower", "Twin Sunflower"
            , "Jalapeno"};
    ArrayList<Cards> railCards = new ArrayList<Cards>();
    ArrayList<Cards> reachedPlantCards = new ArrayList<Cards>();
    ArrayList<Cards> selectedPlantCards = new ArrayList<Cards>();
    //should be implement the rest time and price
    Cards SunFlower = new Cards("SunFlower", 1, 2);
    Cards CherryBomb = new Cards("CherryBomb", 2, 4);
    Cards PeaShooter = new Cards("PeaShooter", 2, 2);
    Cards SnowPea = new Cards("SnowPea", 3, 3);
    Cards ScaredyShroom = new Cards("ScaredyShroom", 1, 2);
    Cards Kernelplut = new Cards("Kernelplut", 3, 3);
    Cards WallNut = new Cards("WallNut", 2, 4);

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
    Cards Zombie = new Cards(2, 2, "Zombie");
    Cards FootballZombie = new Cards(3, 4, "FootballZombie");
    Cards ConeheadZombie = new Cards(2, 3, "ConeheadZombie");
    Cards BungeeZombie = new Cards(0, 3, "BungeeZombie");
    Cards BalloonZombie = new Cards(2, 3, "BalloonZombie");
    Cards Zomboni = new Cards(2, 3, "Zomboni");
    Cards ScreenDoorZombie = new Cards(2, 2, "ScreenDoorZombie");

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

    public Handler(String name) {
        this.name = name;
    }

    private int turn;
    static int Di = 0;
    static int Dj = 0;
    static int Zj = 0;
    static int Rj = 0;
    static int nextTurn = 0;
    public void execute(String command, ArrayList<Plant> plants, Garden garden, ArrayList<Zombie> zombies, Console console, CommandScanner scanner, int turn) {
        this.turn = turn;
        String[] splitedCommand = new String[10];
        splitedCommand = command.split(" ");
        ArrayList<Cards> reachedCards = new ArrayList<Cards>(); // this arraylist is for the all cards that player has
        if (this.name.equals("Day")) {
            // this part is "Collection" for "Day"
            if (Dj == 0) {
                collectionPartOfDay(scanner, console);
                Dj = 1;
            }
            dayAndWaterCommandHandler(command, splitedCommand, plants, zombies, console, garden);
        }


        else if (this.name.equals("Water")) {
            if (Dj == 0) {
                collectionPartOfDay(scanner, console);
                Dj += 1;
            }
            dayAndWaterCommandHandler(command, splitedCommand, plants, zombies, console, garden);
        }


        else if (this.name.equals("Rail")) {
            railCommandHandler(command, splitedCommand, railCards, plants, zombies, console, garden);
            addNewCard(railCards, this.turn);
        }

        else if (this.name.equals("Zombie")) {
            if (Zj == 0) {
                collectionPartOfZombie(scanner, console);
                Zj = 1;
            }
            zombieCommandHandler(command, splitedCommand, plants, zombies, console, garden);
        } else if (this.name.equals("PvP")) {
            if (Dj == 0) {
                collectionPartOfDay(scanner, console);
                collectionPartOfZombie(scanner, console);
               Dj = 1;
            }
            dayAndWaterCommandHandler(command, splitedCommand, plants, zombies, console, garden);
            if (command.equals("Ready")){
               // zombieCommandHandler(command, splitedCommand, plants, zombies, console, garden);
            }
             }
    }
        public void zombieCommandHandler(String command, String[] splitedCommand,ArrayList<Plant> plants, ArrayList<Zombie> zombies, Console console, Garden garden){
            if (splitedCommand[0].equals("Put")) {
                /// check the money and row
                String[] zombieproperty = splitedCommand[1].split(",");
                String zombieName = zombieproperty[0];
                int row = Integer.parseInt(zombieproperty[1]);
                boolean moneyIsEnough = true;
                boolean isInSelected = false;
                int zombieCounterOnRow = 0;
                boolean twoZombieIsOnRow = false; // this boolean used for checking that two zombie is on row or not
                for (int counter = 0; counter < garden.getGarden()[row].length; counter++) {
                    if (garden.getGarden()[row][counter].contains("Zomb")) {
                        zombieCounterOnRow += 1;
                    }
                    if (zombieCounterOnRow == 2) {
                        twoZombieIsOnRow = true;
                        break;
                    }
                }
                for (int counter = 0 ; counter < selectedZombieCards.size() ; counter++){
                    if (zombieName.equals(selectedZombieCards.get(counter).getName())){
                        isInSelected = true;
                        break;
                    }
                }
                if (twoZombieIsOnRow) {
                    console.printCommand("there is two zombie in this row :(");
                }
                else if (!isInSelected){
                    console.printCommand("This card is not in your Selected cards");
                }
                if (!twoZombieIsOnRow && moneyIsEnough) {
                    if (garden.getGarden()[row][20].equals("mt") && isInSelected) {
                        putZombie(row, 20, garden, console, zombies, selectedZombieCards, zombieName, turn);
                    } else if (garden.getGarden()[row][21].equals("mt") && isInSelected) {
                        putZombie(row, 21, garden, console, zombies, selectedZombieCards, zombieName, turn);
                    } else {
                        console.printCommand("can not put :( ");
                    }

                }
            }
        }
        public void putZombie(int row, int column, Garden garden, Console console, ArrayList<Zombie> zombies, ArrayList<Cards> selectedZombieCards, String zombieName, int turn){
        Zombie zombie = new Zombie(zombieName, turn);
        zombie.setLocation_x(column);
        zombie.setLocation_y(row);
        zombies.add(zombie);
        if (garden.getGarden()[row][column] == "mt"){
            garden.getGarden()[row][column] = zombieName;
        }
        else {
            garden.getGarden()[row][column].concat(zombieName);
        }
        console.printCommand("You put " + zombieName + " in (" + row +", " + column +") successfully!!!");
    }
    public void addNewCard(ArrayList<Cards> railCards,int turn){
        if(railCards.size() < 10){
            int random;
            int anotherRandom;
            if (Rj == 0){
                random = (int) ((Math.random() * 100) % 3) ;
                nextTurn = 2 + random + turn;
                Rj = 1;
            }
            if (turn == nextTurn){
                anotherRandom = (int) ((Math.random() * 100) % 25) ;
                Cards cards = new Cards();
                cards.setName(plantsName[anotherRandom]);//implemennt tne price and
                railCards.add(cards);
                Rj = 0;
            }
        }
    }
    public void railCommandHandler(String command, String[] splitedCommand, ArrayList<Cards> railCards, ArrayList<Plant> plants, ArrayList<Zombie> zombies, Console console, Garden garden){
        if (command.equals("List")) {
            for (int counter = 0; counter < railCards.size(); counter++) {
                console.printCommand(railCards.get(counter).getName() + " */*/* ");
            }
        } else if (splitedCommand[0].equals("Select")) {
            currentCard = railCards.get(Integer.parseInt(splitedCommand[1]) - 1);
            railCards.remove(Integer.parseInt(splitedCommand[1]) - 1);
        } else if (command.equals("Record")) {
            console.printCommand("Number of killed Zombies : ");
            // should "sout" the num of kiiled zombies
        }
        else if (splitedCommand[0].equals("Plant")){
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            planting(row, column, garden, console, plants, currentCard);
        }
        else if (splitedCommand[0].equals("Remove")){
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            remove(row, column, plants, zombies, garden, "p", console);
        }
        else if (command.equals("Show lawn")) {
            console.printCommand("EXISTED PLANTS");
            System.out.println("NO." + "        " + "PLANT NAME" + "        " + "HEALTH");
            for (int counter = 0; counter < plants.size(); counter++) {
                console.printCommand(counter + 1 + "      " + plants.get(counter).getName() + "       " + plants.get(counter).getHealth());
            }
            console.printCommand("EXISTED ZOMBIES");
            console.printCommand("NO." + "        " + "ZOMBIE NAME" + "        " + "HEALTH");
            for (int counter = 0; counter < plants.size(); counter++) {
                console.printCommand(counter + 1 + "      " + zombies.get(counter).getName() + "       " + zombies.get(counter).getHealth());
            }
        }

    }


    public void dayAndWaterCommandHandler(String command, String[] splitedCommand, ArrayList<Plant> plants, ArrayList<Zombie> zombies, Console console, Garden garden) {
        if (command.equals("Show hand")) {
            getSelectedCard(selectedPlantCards, console);
        } else if (splitedCommand[0].equals("Select") && !this.name.equals("Rail")) {
            for (int counter = 0; counter < selectedPlantCards.size(); counter++) {
                if (splitedCommand[1].equals(selectedPlantCards.get(counter).getName())) {
                    // implement a method to check out the price and rest time of plants against the sunsvalue
                    currentCard = selectedPlantCards.get(counter);
                    console.printCommand("You select " + currentCard.getName() + " successfully!!!");
                }
            }
        } else if (splitedCommand[0].equals("Plant") && this.name.equals("Day")) {
            System.out.println("salam az planting1");
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            planting(row, column, garden, console, plants, currentCard);
        } else if (splitedCommand[0].equals("Plant") && this.name.equals("Water")) {
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            boolean thereIsLilypad = false;
            boolean thisIsMarinePlant = false;
            for (int counter = 0; counter < plants.size(); counter++) {
                if (plants.get(counter).getName().equals("Lily Pad")) {
                    thereIsLilypad = true;
                }
            }
            if (currentCard.getName().equals("Lily Pad") || currentCard.getName().equals("Tangle Kelp")) {
                thisIsMarinePlant = true;
            }
            if ((row == 2 || row == 3) && !thereIsLilypad) {
                console.printCommand("There is not Lily Pad there :( ");
            }
            if ((row == 2 || row == 3) && thereIsLilypad) {
                planting(row, column, garden, console, plants, currentCard);
            }
            if ((row != 2 && row != 3) && thisIsMarinePlant) {
                console.printCommand("You can not plant marine plant in garden :(");
            }
            if ((row == 2 || row == 3) && thisIsMarinePlant) {
                planting(row, column, garden, console, plants, currentCard);
            }
            if ((row != 2 && row != 3) && !thisIsMarinePlant) {
                planting(row, column, garden, console, plants, currentCard);
            }

        } else if (splitedCommand[0].equals("Remove")) {
            int row = Integer.parseInt(splitedCommand[1]);
            int column = Integer.parseInt(splitedCommand[2]);
            remove(row, column, plants, zombies, garden, "p", console);
        } else if (command.equals("Show lawn")) {
            console.printCommand("EXISTED PLANTS");
            System.out.println("NO." + "        " + "PLANT NAME" + "        " + "HEALTH");
            for (int counter = 0; counter < plants.size(); counter++) {
                console.printCommand(counter + 1 + "      " + plants.get(counter).getName() + "       " + plants.get(counter).getHealth());
            }
            console.printCommand("EXISTED ZOMBIES");
            console.printCommand("NO." + "        " + "ZOMBIE NAME" + "        " + "HEALTH");
            for (int counter = 0; counter < plants.size(); counter++) {
                console.printCommand(counter + 1 + "      " + zombies.get(counter).getName() + "       " + zombies.get(counter).getHealth());
            }
        }

    }

    public void collectionPartOfDay(CommandScanner scanner, Console console) {
        String tempCommand = new String();
        String[] tempCommandSplit = new String[10];
        int i = 0;
        if (i == 0) {
            console.printCommand("You are in Day/Water Collection part *+*");
            i = 1;
        }
        scanner.scanCommands();
        tempCommand = scanner.getCommand();

        while (selectedPlantCards.size() < 8 || !tempCommand.equals("Play")) {

            tempCommandSplit = tempCommand.split(" ");

            if (tempCommand.equals("Show hand")) {
                collectionShowHand(selectedPlantCards, console);
            } else if (tempCommand.equals("Show collection")) {
                collectionShowCollection(selectedPlantCards, reachedPlantCards, console);
            } else if (tempCommandSplit[0].equals("select")) {
                String cardName = new String();
                cardName = tempCommandSplit[1];
                setCollectionPlants(selectedPlantCards, reachedPlantCards, cardName, console);
            }
            scanner.scanCommands();
            tempCommand = scanner.getCommand();
            if (tempCommand.equals("Play")) {
                console.printCommand("Lets Start Game!!!");
                break;
            }
        }

    }

    public void collectionPartOfZombie(CommandScanner scanner, Console console) {
        String tempCommand = new String();
        String[] tempCommandSplit = new String[10];
        if (Di == 0) {
            console.printCommand("You are in Zombie Collection part *+*");
            Di = 1;
        }
        scanner.scanCommands();
        tempCommand = scanner.getCommand();

        while (selectedZombieCards.size() < 8 || !tempCommand.equals("Play")) {

            tempCommandSplit = tempCommand.split(" ");

            if (tempCommand.equals("Show hand")) {
                collectionShowHand(selectedZombieCards, console);
            } else if (tempCommand.equals("Show collection")) {
                collectionShowCollection(selectedZombieCards, reachedZombieCards, console);
            } else if (tempCommandSplit[0].equals("select")) {
                String cardName = new String();
                cardName = tempCommandSplit[1];
                setCollectionPlants(selectedZombieCards, reachedZombieCards, cardName, console);
            }
            scanner.scanCommands();
            tempCommand = scanner.getCommand();
            if (tempCommand.equals("Play")){
                console.printCommand("Lets start the Game!!!");
                break;
            }
        }

    }

    // this method is for showing the unselectedCards
    public void collectionShowCollection(ArrayList<Cards> selectedPlantCards, ArrayList<Cards> reachedPlantCards, Console console) {
        ArrayList<Cards> tempreached = new ArrayList<Cards>();
        ArrayList<Cards> unselected = new ArrayList<Cards>();
        for (int counter = 0; counter < reachedPlantCards.size(); counter++) {
            tempreached.add(reachedPlantCards.get(counter));
        }
        for (int counter = 0; counter < tempreached.size(); counter++) {
            if (!tempreachedIsselected(tempreached.get(counter), selectedPlantCards)) {
                unselected.add(tempreached.get(counter));
            }
        }
        console.printCommand("Unselected cards are : ");
        for (int counter = 0; counter < unselected.size(); counter++) {
            console.printCommand((counter + 1) + "  #*#  " + unselected.get(counter).getName());
        }
    }

    // this method is used in collectionShowCollection
    public boolean tempreachedIsselected(Cards card, ArrayList<Cards> selectedPlantCards) {
        for (int counter = 0; counter < selectedPlantCards.size(); counter++) {
            if (card.getName().equals(selectedPlantCards.get(counter).getName())) {
                return true;
            }
        }
        return false;
    }

    // this method is for showing the selectedPlantCard
    public void collectionShowHand(ArrayList<Cards> selectedPlantCards, Console console) {
        for (int counter = 0; counter < selectedPlantCards.size(); counter++) {
            console.printCommand("#*#  " + selectedPlantCards.get(counter).getName());
        }
    }

    // the common commands betweengame :
    boolean locationIsFilled(Garden garden, int row, int column) {
        if (garden.getGarden()[row][column] != "mt") {
            return true;
        }
        return false;
    }

    void setCollectionPlants(ArrayList<Cards> selectedPlantCards, ArrayList<Cards> reachedPlantCards, String cardName, Console console) {
        boolean cardIsExist = false;
        boolean cardWasSelected = false;
        for (int counter = 0; counter < reachedPlantCards.size(); counter++) {
            if (cardName.equals(reachedPlantCards.get(counter).getName())) {
                cardIsExist = true;
                currentCard = new Cards(reachedPlantCards.get(counter).getName(), reachedPlantCards.get(counter).getPrice(), reachedPlantCards.get(counter).getRestTime());
                break;
            }
        }
        for (int counter = 0; counter < selectedPlantCards.size(); counter++) {
            if (cardName.equals(selectedPlantCards.get(counter))) {
                cardWasSelected = true;
                break;
            }
        }
        if (!cardIsExist) {
            console.printCommand("You do not have this card :( ");
        } else if (cardWasSelected) {
            console.printCommand("You select this card previously :( ");
        } else if (cardIsExist && !cardWasSelected) {
            selectedPlantCards.add(currentCard);
            System.out.println("You select " + currentCard.getName() + " successfully");
        }

    }

    void getSelectedCard(ArrayList<Cards> cards, Console console) {
        for (int counter = 0; counter < cards.size(); counter++) {
            console.printCommand("Card name :  " + cards.get(counter).getName() + "// needed suns :  " + cards.get(counter).getPrice() + "//  rest time :  " + cards.get(counter).getRestTime());
        }
    }

    public void remove(int row, int column, ArrayList<Plant> plants, ArrayList<Zombie> zombies, Garden garden, String type, Console console) {
        if (type.equals("p")) {
            System.out.println("salam az remove");
            for (int counter = 0; counter < plants.size(); counter++) {
                if (row == plants.get(counter).getLocation_y() && column == plants.get(counter).getLocation_x()) {
                    System.out.println("salamAZ DAKHEL");
                    plants.remove(counter);
                    garden.getGarden()[row][column] = "mt";
                    console.printCommand("Plant removed successfully!!!");
                    break;
                }
            }
        } else if (type.equals("z")) {
            for (int counter = 0; counter < zombies.size(); counter++) {
                if (row == zombies.get(counter).getLocation_x() && column == zombies.get(counter).getLocation_y()) {
                    zombies.remove(counter);
                    garden.getGarden()[row][column] = "mt";
                    console.printCommand("Zombie removed successfully!!!");
                    break;
                }
            }
        }

    }

    public void planting(int row, int column, Garden garden, Console console, ArrayList<Plant> plants, Cards currentCard) {
        System.out.println("salam az planting");
        boolean cardIsNULL = false;
        boolean locationIsFilled = false;
        if (currentCard == null) {
            cardIsNULL = true;
        }
        if (locationIsFilled(garden, row, column)) {
            locationIsFilled = true;
        }
        if (cardIsNULL == true) {
            console.printCommand("YOU DID NOT SELECT ANY CARD :(");
            return;
        } else if (locationIsFilled == true) {
            console.printCommand("location Is Filled :( ");
            return;
        }
        if (column != 0 && column % 2 == 0) {
            for (int counter = 0; counter < selectedPlantCards.size(); counter++) {
                if (currentCard.getName().equals(selectedPlantCards.get(counter).getName())) {
                    Plant newPlant = new Plant(currentCard.getName(), turn);
                    newPlant.setLocation_x(column);
                    newPlant.setLocation_y(row);
                    plants.add(newPlant);
                    if (garden.getGarden()[row][column] == "mt") {
                        garden.getGarden()[row][column] = (currentCard.getName());
                    } else {
                        garden.getGarden()[row][column].concat(currentCard.getName());
                    }
                    console.printCommand("You plant" + currentCard.getName() + "  in location  (" + row + ", " + column + ")");
                }
            }
        }
        if (column % 2 != 0) {
            console.printCommand("Choose the fucking even column number :(");
        }

    }

    void showLawn() {

    }

    public void endTurn() {
        //it should break the game "while"
    }

    /**
     * this method will update garden after the "turn" is over
     *
     * @param garden
     */
    public void updateGarden(Garden garden, ArrayList<Plant> plants, ArrayList<Zombie> zombies, ArrayList<Bullet> bullets, ArrayList<LawnMower> lawnMowers, ArrayList<Sun> suns, boolean gameIsContinued, int turn) {
        // in this loop every zombie or plant that have a special activity will do it's activity
        for (int i = 0; i < garden.getGardenRow(); i++) {
            for (int j = 0; j < garden.getGardenColumn(); j++) {
                if (isPlant(garden.getGarden()[garden.getGardenRow()][garden.getGardenColumn()], plantsName)) {
                    findPlant(garden.getGarden()[garden.getGardenRow()][garden.getGardenColumn()], plants).activity(garden.getGarden()[garden.getGardenRow()][garden.getGardenColumn()], zombies, turn);
                } else if (isZombie(garden.getGarden()[garden.getGardenRow()][garden.getGardenColumn()])) {
                    findZombie(garden.getGarden()[garden.getGardenRow()][garden.getGardenColumn()], zombies).activity(garden.getGarden()[garden.getGardenRow()][garden.getGardenColumn()]);
                }
            }
        }
        for (Plant plant : plants) {
            for (Zombie zombie : zombies) {
                if (locationIsEqual(plant, zombie)) {
                    plant.hurt(zombie);
                    if (plant.getName().equals("Cactus")) {
                        // reduce one unit of zombie's health
                    }
                }
            }
        }
        for (Bullet bullet : bullets) {
            for (Zombie zombie : zombies) {
                if (locationIsEqual(bullet, zombie))
                    zombie.hurt(bullet);
            }
        }
        for (Bullet bullet : bullets) {
            if (bullet.getIsForward())
                bullet.moveForward(zombies);
            else
                bullet.moveBackward(zombies);
        }
        for (Plant plant : plants)
            plant.shoot(turn, bullets, zombies);
        for (Zombie zombie : zombies)
            zombie.walk();
        //makeSun(suns , turn);
        // this method will check the lawnmowers and if it is necessary will use them
        checkLawnMower(lawnMowers, zombies);
        //gameIsContinued = checkGameIsFinished(zombies , lawnMowers);
        updateLawn(garden, plants, zombies);
    }

    /*private void makeSun(ArrayList<Sun> suns, int turn , int currentRandomNumber , int lastTurnSunGenerated) {
        if (turn - lastTurnSunGenerated == currentRandomNumber){
            suns.add(new Sun());
        }

    }*/
    // this method will generate random numbers between min adn max
    private int generateRandom(int min, int max) {
        double x = Math.random() * ((max - min) + 1) + min;
        int randomNumber = (int) x;
        return randomNumber;
    }

    private void checkLawnMower(ArrayList<LawnMower> lawnMowers, ArrayList<Zombie> zombies) {
        for (LawnMower lawnMower : lawnMowers) {
            if (!lawnMower.getIsUsed() && findZombie(lawnMower.getRow(), lawnMower.getColumn(), zombies) != null)
                lawnMower.killAllZombiesInRow(zombies);
        }
    }

    private boolean checkGameIsFinished(ArrayList<Zombie> zombies, ArrayList<LawnMower> lawnMowers) {
        for (Zombie zombie : zombies) {
            if (zombie.getLocation_y() == 0) {
                if (findLawnMower(zombie.getLocation_x(), lawnMowers).getIsUsed() == true)
                    return false;
            }
        }
        return true;
    }

    //this method should update the string array of object
    private void updateLawn(Garden garden, ArrayList<Plant> plants, ArrayList<Zombie> zombies) {
        // for ()
    }

    private boolean locationIsEqual(Plant plant, Zombie zombie) {
        if (plant.getLocation_x() == zombie.getLocation_x() && plant.getLocation_y() == zombie.getLocation_y())
            return true;
        return false;
    }

    private boolean locationIsEqual(Bullet bullet, Zombie zombie) {
        if (bullet.getLocation_x() == zombie.getLocation_x() && bullet.getLocation_y() == zombie.getLocation_y())
            return true;
        return false;
    }

    public LawnMower findLawnMower(int row, ArrayList<LawnMower> lawnMowers) {
        for (LawnMower lawnMower : lawnMowers) {
            if (lawnMower.getRow() == row)
                return lawnMower;
        }
        return null;
    }

    public Plant findPlant(String name, ArrayList<Plant> plants) {
        for (Plant plant : plants) {
            if (plant.getName().equals(name)) {
                return plant;
            }
        }
        return null;
    }

    //this method will find the zombie by getting a name
    public Zombie findZombie(String name, ArrayList<Zombie> Zombies) {
        for (Zombie zombie : Zombies) {
            if (zombie.getName().equals(name)) {
                return zombie;
            }
        }
        return null;
    }

    //this method will find zombie by getting a location
    public Zombie findZombie(int x, int y, ArrayList<Zombie> Zombies) {
        for (Zombie zombie : Zombies) {
            if (zombie.getLocation_y() == y && zombie.getLocation_x() == x) {
                return zombie;
            }
        }
        return null;
    }

    public boolean isPlant(String name, String[] plantsName) {
        int counter = 0;
        for (int i = 0; i < plantsName.length; i++) {
            if (plantsName[i].equals(name))
                counter++;
        }
        if (counter != 0)
            return true;
        return false;
    }

    public boolean isZombie(String name) {
        if (name.contains("Zomb"))
            return true;
        return false;
    }
}
