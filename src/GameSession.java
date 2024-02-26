import java.awt.font.GlyphMetrics;

public class GameSession {
    private String time;
    Authentication authentication = new Authentication();
    Board board = new Board();
    Database database = new Database();
    Player player = new Player();
    Screen screen = new Screen();
    public void initgame(){
        if(screen.LoginScreen()==2){
        authentication.registerUser();}
        else{
            authentication.isLoginCorrect();
        }
    }
    public void game(){}
    public void start(){
        initgame();
        while () {
            game();
        }
    }


}
