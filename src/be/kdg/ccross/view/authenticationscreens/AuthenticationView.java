package be.kdg.ccross.view.authenticationscreens;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class AuthenticationView extends VBox {
    Label screenTitle;
    Label name;
    Label password;
    TextField usernamefield;
    PasswordField passwordField;
    Button createacc;
    Button cancel;
    Button log_in;
    HBox HBOXLOGIN;
    HBox HBOXPASSWORD;
    HBox buttonsConfirm;

    public AuthenticationView() {
        initialiseNodes();
        layoutNodes();
        this.setStyle("-fx-background-color: #e5d9c7");
    }
    public void initialiseNodes(){
        screenTitle = new Label("Login ");
        screenTitle.setFont(Font.font("Login", FontWeight.BOLD, FontPosture.ITALIC,30));
        name = new Label("Username: ");
        name.setFont(Font.font("Arial",15));
        usernamefield = new TextField("");
        password = new Label("Password: ");
        password.setFont(Font.font("Arial",15));
        passwordField = new PasswordField();
        createacc = new Button("Create an account?");
        cancel= new Button("Cancel");
        log_in = new Button("Log in");
        HBOXLOGIN = new HBox(5);
        HBOXPASSWORD = new HBox(5);
        buttonsConfirm = new HBox(5);
    }
    private void layoutNodes(){
        //screenTitle.setTextAlignment(TextAlignment.CENTER);
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
    public Button getcreateacc(){return createacc;}
    public Button getCancel(){return cancel;}
    public Button getLog_in(){return log_in;}

    public Label getScreenTitle() {
        return screenTitle;
    }

    public Label getName() {
        return name;
    }

    public Label getPassword() {
        return password;
    }

    public TextField getUsernamefield() {
        return usernamefield;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public HBox getHBOXLOGIN() {
        return HBOXLOGIN;
    }

    public HBox getHBOXPASSWORD() {
        return HBOXPASSWORD;
    }

    public HBox getButtonsConfirm() {
        return buttonsConfirm;
    }
}
