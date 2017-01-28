package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PrimaryPresenter {

    @FXML
    private View primary;

    @FXML
    private Label label;

    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
                appBar.setTitleText("Home");
                // appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                //        System.out.println("Search")));
            }
        });
        
        // set up main view
        Label welcome_label = new Label("Java Quiz: 10th Ed.");
        Image book_img = new Image(getClass().getResourceAsStream("/book.jpg"));
        ImageView book_view = new ImageView(book_img);
        
        VBox home_view = new VBox();
        // home_view.getChildren().add(welcome_label);
        home_view.getChildren().add(book_view);
        
        primary.setCenter(home_view);
    }
    
    @FXML
    void buttonClick() {
        label.setText("Hello JavaFX Universe!");
    }
    
}
