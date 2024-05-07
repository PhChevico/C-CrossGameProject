package be.kdg.ccross.view.authenticationscreens;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class AuthenticationView extends VBox {
    //make all them private
    private Label screenTitle;
    private Label name;
    private Label password;
    private TextField usernamefield;
    private PasswordField passwordField;
    private Button createacc;
    private Button cancel;
    private Button log_in;
    private HBox HBOXLOGIN;
    private HBox HBOXPASSWORD;
    private HBox buttonsConfirm;

    public AuthenticationView() {
        initialiseNodes();
        layoutNodes();

    }
    public void initialiseNodes(){
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));


        //if i dont getter for the attributes i put them inside layout nodes.
        usernamefield = new TextField("");
        passwordField = new PasswordField();
        createacc = new Button("Create an account?");
        cancel= new Button("Cancel");
        log_in = new Button("Log in");

    }
    private void layoutNodes(){
        screenTitle = new Label("Login ");
        screenTitle.setFont(Font.font("Login", FontWeight.BOLD, FontPosture.ITALIC,30));
        name = new Label("Username: ");
        name.setFont(Font.font("Arial",15));
        HBOXLOGIN = new HBox(5);
        HBOXPASSWORD = new HBox(5);
        buttonsConfirm = new HBox(5);
        password = new Label("Password: ");
        password.setFont(Font.font("Arial",15));
        usernamefield.setStyle("-fx-background-color: rgba(65,255,12,0.27)");
        passwordField.setStyle("-fx-background-color: rgba(65,255,12,0.27)");
        name.setStyle("-fx-text-fill: rgb(79,46,0)");
        password.setStyle("-fx-text-fill: rgb(79,46,0)");
        screenTitle.setStyle("-fx-text-fill: rgb(79,46,0)");
        createacc.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        cancel.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        log_in.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        //putting here the nodes init nodes that are not used with getters
        usernamefield.setMaxHeight(20);
        usernamefield.setMaxWidth(120);
        passwordField.setMaxHeight(20);
        passwordField.setMaxWidth(120);
        HBOXLOGIN.getChildren().addAll(name, usernamefield);
        HBOXPASSWORD.getChildren().addAll(password, passwordField);
        buttonsConfirm.getChildren().addAll(createacc,cancel,log_in);
        HBOXLOGIN.setAlignment(Pos.CENTER);
        HBOXPASSWORD.setAlignment(Pos.CENTER);
        buttonsConfirm.setAlignment(Pos.CENTER);
        super.getChildren().addAll(screenTitle, HBOXLOGIN, HBOXPASSWORD, buttonsConfirm);
        setAlignment(Pos.CENTER);
        createacc.setMinSize(80,15);
        cancel.setMinSize(90,15);
        log_in.setMinSize(90,15);
        setSpacing(25);



    }
    Button getcreateacc(){return createacc;}
    Button getCancel(){return cancel;}
    Button getLog_in(){return log_in;}

    TextField getUsernamefield() {
        return usernamefield;
    }

    PasswordField getPasswordField() {
        return passwordField;
    }

}
