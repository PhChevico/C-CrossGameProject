import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private Connection connection;


    public boolean registerUser(String name, String password){
        String createUserDatabase = "INSERT INTO logs_info (name, password) VALUES (?,?)";

        if(isUsernameInDatabase(name)){
            System.out.println("Username already exists, try another name.");
            return false;
        }

        try(PreparedStatement statement = connection.prepareStatement(createUserDatabase)){
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return true;
    }




    public boolean isUsernameInDatabase(String login){

        String verifyUsername = "SELECT login FROM logs_info WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(verifyUsername)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If there is a next result, the login exists
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isLoginCorrect(String login, String password){
        String verifyLogin = "SELECT password FROM logs_info WHERE login = ?";
        if(!isUsernameInDatabase(login))
            return false;

        try(PreparedStatement statement = connection.prepareStatement(verifyLogin)){
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {

                resultSet.next(); //perhaps I don't need this
                String pass = resultSet.getString("password");
                return password.equals(pass);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
