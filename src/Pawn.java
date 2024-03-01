import java.util.Scanner;

public class Pawn {
    private String color;
    public enum COLORS{
        WHITE, BLACK , GREY , RED , BLUE ,PURPLE ,ORANGE , GREEN ,BROWN, YELLOW , CYAN;
    }
    private COLORS colors;
    private Scanner scanner;

    public Pawn() {
        scanner = new Scanner(System.in);
        colors = COLORS.WHITE; // or any default color
    }

    public String pawnColor(){ //we use this to ask the user to enter a color from the ENUM list COLORS
        System.out.println("Enter a color from the list in CAPS:"+ colors.toString());
        color = scanner.next();
        for(COLORS col : COLORS.values()){
            if(color.equals(col.name())) {
                return color;
            }
        }
        return "ERROR";
    }

    @Override
    public String toString() { //to show the list of colors so that the user can choose properly
        return "color = " + colors.name() + '\n';
    }

}

