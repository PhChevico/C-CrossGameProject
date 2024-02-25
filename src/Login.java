import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private Connection connection;

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
