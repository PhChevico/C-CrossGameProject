//class takes care of the input for login on the user's side.
public class HandleInput {
    private String input;

    public boolean lengthOfLogin(String login){
        return login.length() <= 15;
    }


    public boolean lengthOfPassword(String password){
        return password.length() <= 25;
    }




}
