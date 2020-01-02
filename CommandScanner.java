
import java.util.Scanner;
class CommandScanner {
    Scanner scanner = new Scanner(System.in);
    String command;
    public void scanCommands(){
        command = scanner.nextLine();
    }
    public String getCommand(){
        return command;
    }
}
