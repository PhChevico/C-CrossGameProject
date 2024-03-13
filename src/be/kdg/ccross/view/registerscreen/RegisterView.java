package be.kdg.ccross.view.registerscreen;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class RegisterView extends VBox {
    Label screenTitle;
    Label name;
    Label password;
    TextField userName;
    PasswordField passwd;
    Button register;
    Button cancel;
    Button log_in;
    HBox HBOXLOGIN;
    HBox HBOXPASSWORD;
    HBox buttonsConfirm;

    public RegisterView() {
        initialiseNodes();
        layoutNodes();
    }
    public void initialiseNodes(){
        screenTitle = new Label("Register");
        screenTitle.setFont(Font.font("Register", FontWeight.BOLD,10));
        name = new Label("Username: ");
        userName = new TextField("");
        password = new Label("Password: ");
        passwd = new PasswordField();
        register = new Button("Register");
        cancel= new Button("Cancel");
        log_in = new Button("Log in");
        HBOXLOGIN = new HBox();
        HBOXPASSWORD = new HBox();
        buttonsConfirm = new HBox();
    }
    private void layoutNodes(){
        //screenTitle.setTextAlignment(TextAlignment.CENTER);
        userName.setMaxHeight(20);
        userName.setMaxWidth(100);
        passwd.setMaxHeight(20);
        passwd.setMaxWidth(100);
        HBOXLOGIN.getChildren().addAll(name, userName);
        HBOXPASSWORD.getChildren().addAll(password, passwd);
        buttonsConfirm.getChildren().addAll(register,cancel,log_in);
        HBOXLOGIN.setAlignment(Pos.CENTER);
        HBOXPASSWORD.setAlignment(Pos.CENTER);
        buttonsConfirm.setAlignment(Pos.CENTER);
        super.getChildren().addAll(screenTitle, HBOXLOGIN, HBOXPASSWORD, buttonsConfirm);
        setAlignment(Pos.CENTER);
        register.setMinSize(80,15);
        cancel.setMinSize(80,15);
        log_in.setMinSize(80,15);
        setSpacing(20);



    }
    public Button getRegister(){return register;}
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

    public TextField getUserName() {
        return userName;
    }

    public PasswordField getPasswd() {
        return passwd;
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
