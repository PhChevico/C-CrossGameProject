package be.kdg.ccross.view.registerscreens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class RegisterView extends VBox {
    private Label screenTitle;
    private Label name;
    private TextField nameField;
    private Label password;
    private PasswordField passwordField;
    private Label confirmPassword;
    private PasswordField confirmFiled;
    private Button log_in;
    private Button register;
    private Button cancel;
    private HBox nameBox;
    private HBox passwordBox;
    private HBox confirmBox;
    private HBox buttonsBox;

    public RegisterView() {
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


        nameField = new TextField();
        passwordField = new PasswordField();
        confirmFiled = new PasswordField();
        log_in = new Button("Log in");
        register = new Button("Register");
        cancel = new Button("Cancel");

    }
    public void layoutNodes(){
        screenTitle = new Label("Register");
        screenTitle.setFont(Font.font("Register", FontWeight.BOLD, FontPosture.ITALIC,30));
        name = new Label("Username: ");
        name.setFont(Font.font("Arial", 15));
        password = new Label("Password: ");
        password.setFont(Font.font("Arial", 15));
        confirmPassword = new Label("Confirm Password:  ");
        confirmPassword.setFont(Font.font("Arial",15));

        name.setStyle("-fx-text-fill: rgb(79,46,0)");
        password.setStyle("-fx-text-fill: rgb(79,46,0)");
        screenTitle.setStyle("-fx-text-fill: rgb(79,46,0)");
        confirmPassword.setStyle("-fx-text-fill: rgb(79,46,0)");

        register.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        cancel.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");
        log_in.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");

        nameField.setStyle("-fx-background-color: rgba(65,255,12,0.27)");
        passwordField.setStyle("-fx-background-color: rgba(65,255,12,0.27)");
        confirmFiled.setStyle("-fx-background-color: rgba(65,255,12,0.27)");

        nameBox = new HBox(5);
        passwordBox = new HBox(5);
        confirmBox = new HBox();
        buttonsBox = new HBox(5);
        //putting here the nodes init nodes that are not used with getters
        nameField.setMaxHeight(20);
        nameField.setMaxWidth(120);
        passwordField.setMaxHeight(20);
        passwordField.setMaxWidth(120);
        confirmFiled.setMaxHeight(20);
        confirmFiled.setMaxWidth(120);
        nameBox.getChildren().addAll(name, nameField);
        passwordBox.getChildren().addAll(password,passwordField);
        confirmBox.getChildren().addAll(confirmPassword,confirmFiled);
        buttonsBox.getChildren().addAll(log_in,cancel,register);
        nameBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        confirmBox.setAlignment(Pos.CENTER);
        confirmBox.setPadding(new Insets(0,0,0,-55));
        buttonsBox.setAlignment(Pos.CENTER);
        super.getChildren().addAll(screenTitle,nameBox,passwordBox,confirmBox,buttonsBox);
        setAlignment(Pos.CENTER);
        log_in.setMinSize(90, 15);
        cancel.setMinSize(90, 15);
        register.setMinSize(90, 15);
        setSpacing(25);
        setMinHeight(600);
        setMinWidth(600);

    }

    public TextField getNameField() {
        return nameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public PasswordField getConfirmFiled() {
        return confirmFiled;
    }

    public Button getLog_in() {
        return log_in;
    }

    public Button getRegister() {
        return register;
    }

    public Button getCancel() {
        return cancel;
    }

}