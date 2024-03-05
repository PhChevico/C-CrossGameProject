package be.kdg.ccross.view.authenticationscreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AuthenticationView extends BorderPane {
    private Button button;
    private TextField textField;

    protected Button getButton() {
        return button;
    }

    public AuthenticationView() {
        initialiseNodes();
        layoutNodes();

    }

    public void initialiseNodes(){
        button = new Button("Click on me");

    }
    public void layoutNodes(){
        setBottom(button);
        setAlignment(button, Pos.CENTER_LEFT);
        setPadding(new Insets(10));
        setMargin(button,new Insets(10));
    }
}

