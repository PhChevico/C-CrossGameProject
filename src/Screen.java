import java.util.Scanner;

public class Screen {
    private Scanner scanner = new Scanner(System.in);
    public int LoginScreen(){ //we ask the user if they want to either login or register and we return the choice
        int choice;
        System.out.println("enter \n1-LOGIN \n2-REGISTER");
        choice = scanner.nextInt();
        return choice;
    }
    public boolean askToExit() { //ask the user at the end of the game if he wants to exit the game
        System.out.println("Do you want to exit the game? (yes/no)");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes") || answer.equals("y");
    }
}
