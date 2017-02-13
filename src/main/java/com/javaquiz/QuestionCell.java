/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import com.gluonhq.charm.glisten.visual.GlistenStyleClasses;
import static com.javaquiz.Javaquiz.QUESTION_VIEW;
import com.javaquiz.model.Question;
import com.javaquiz.model.SingleQuestion;
import com.javaquiz.model.SingleQuestions;
import static com.javaquiz.model.SingleQuestions.questionList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

/**
 *
 * @author cmkm
 */
public class QuestionCell extends CharmListCell<Question> {

    private final ListTile tile;

    // TODO: custom completion graphics
    public QuestionCell() {
        this.tile = new ListTile();
        //tile.setPrimaryGraphic(MaterialDesignIcon.ASSIGNMENT.graphic());
        setText(null);
        tile.setOnMouseClicked(e -> {
            String sectionId = super.itemProperty().getValue().getSectionId();
            String chapterId = super.itemProperty().getValue().getChapter_id();
            String questionId = super.itemProperty().getValue().getQuestion_id();
            String qText = super.itemProperty().getValue().getText();
            String key = super.itemProperty().getValue().getKeyLetter();
            String hint = super.itemProperty().getValue().getHString();
            System.out.println(sectionId + ": " + chapterId);
            Image light = new Image(getClass().getResourceAsStream("/light.png"));
            Image bigLight = new Image(getClass().getResourceAsStream("/bigLight.png"));
            popSpecificQuestion(sectionId, chapterId, questionId, qText, key, hint, light, bigLight);
            MobileApplication.getInstance().switchView(QUESTION_VIEW);
        });
    }

    @Override
    public void updateItem(Question item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getSectionId() + ": " + item.getQuestion_id());
        }
        setGraphic(tile);
    }

    public static void popSpecificQuestion(String sectionId, String chapterId, String questionId,
            String qText, String key, String hint, Image light, Image bigLight) {
        try {
            ToggleButtonGroup tbg = new ToggleButtonGroup();
            if (key.length() > 1) {
                tbg.setSelectionType(SelectionMode.MULTIPLE);
            } else {
                tbg.setSelectionType(SelectionMode.SINGLE);
            }
            ArrayList<RadioButton> tbs = new ArrayList<>();
            String choices = "abcdefghijklmnopqrstuvwxyz";
            SingleQuestions.questionList.clear();
            SingleQuestions.radioList.clear();
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getSections = "Select * from answer where chapterId = " + chapterId + " and sectionId = '" + sectionId + "' and questionId = " + questionId;
            ResultSet rset = stmt.executeQuery(getSections);
            int i = 0;
            while (rset.next()) {
                RadioButton tg = new RadioButton();
                tg.setText(choices.charAt(i) + ": " + String.valueOf(rset.getObject(5)));
                tbs.add(tg);
                SingleQuestions.radioList.add(tg);
                tbg.getToggles().add(tg);
                i++;
            }
            SingleQuestions.questionList.add(new SingleQuestion(questionId, chapterId, sectionId, qText,
                    hint, key, tbg, tbs));
            SingleQuestions.hintList.clear();
            try {
                if (hint.length() > 0) {
                    
                    Button hintButton = new Button("", new ImageView(light));
                    // hintButton.setStyle("-fx-background-color: #ffffff");
                    GlistenStyleClasses.applyStyleClass(hintButton, GlistenStyleClasses.BUTTON_FLAT);
                    hintButton.setOnAction(e -> showHint(bigLight));
                    SingleQuestions.hintList.add(hintButton);
                }
            } catch (Exception ex) {
                System.out.println("No hint avaliable");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }

    public static void showHint(Image light) {
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, questionList.get(0).getHString());
        alert.setGraphic(new ImageView(light));
        alert.showAndWait();
    }
}
