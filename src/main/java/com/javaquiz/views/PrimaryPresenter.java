package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.CHAPTER_VIEW;
import static com.javaquiz.Javaquiz.SUMMARY_VIEW;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryPresenter {

    public static String user;

    @FXML
    private View primary;

    @FXML
    private Label label;

    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setStyle("-fx-background-color: #ff516e; -fx-box-shadow: none;");
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
                appBar.setTitleText("Home");
                // appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                //        System.out.println("Search")));
            }
        });

        // set up main view
        Label welcome_label = new Label("Java Quiz: 10th Ed.");
        Image book_img = new Image(getClass().getResourceAsStream("/book.jpg"));
        BackgroundImage bi = new BackgroundImage(book_img, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //ImageView book_view = new ImageView(book_img);

        BorderPane home_view = new BorderPane();
        home_view.setBackground(new Background(bi));

        TextField userName = new TextField();
        userName.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        VBox fields = new VBox();
        fields.getChildren().addAll(userName, passwordField);

        Button register = new Button("Register");
        register.setOnAction(e -> registerUser(userName.getText(), passwordField.getText()));
        Button logIn = new Button("Log In");
        logIn.setOnAction(e -> logIn(userName.getText(), passwordField.getText()));
        HBox buttonHB = new HBox();
        buttonHB.getChildren().addAll(register, logIn);
        fields.getChildren().add(buttonHB);

        home_view.setCenter(fields);
        // home_view.getChildren().add(welcome_label);
//        home_view.getChildren().add(book_view);

        primary.setCenter(home_view);
    }

    void registerUser(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String addUser = "INSERT INTO User (userId, password) VALUES ('" + user + "','" + password + "')";
            stmt.executeUpdate(addUser);
            this.user = user;
            MobileApplication.getInstance().switchView(SUMMARY_VIEW);
        } catch (ClassNotFoundException | SQLException ex) {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR, "That user already exists,"
                    + " please try a different username");
            alert.showAndWait();
            ex.printStackTrace();
            
        }
    }

    void logIn(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getUser = "select * from User where userId = '"+user+"' and password = '" + password + "'";
            stmt.executeQuery(getUser);
            this.user = user;
            MobileApplication.getInstance().switchView(SUMMARY_VIEW);
        } catch (ClassNotFoundException | SQLException ex) {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR, "That username doesnt exist, "
                    + "please register as a new user.");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }

    @FXML
    void buttonClick() {
        label.setText("Hello JavaFX Universe!");
    }

}
