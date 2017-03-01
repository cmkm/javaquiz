package com.javaquiz;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import com.gluonhq.charm.glisten.visual.GlistenStyleClasses;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
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
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class QuestionCell extends CharmListCell<Question> {

    private final ListTile tile;

    // TODO: custom completion graphics
    public QuestionCell() {
        this.tile = new ListTile();
        tile.setPrimaryGraphic(MaterialDesignIcon.QUESTION_ANSWER.graphic());
        setText(null);
        tile.setOnMouseClicked(e -> {
            String sectionId = super.itemProperty().getValue().getSection_id();
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
            tile.textProperty().setAll(item.getChapter_id() + "." + item.getSection_id() + "." +item.getQuestion_id());
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
                RadioButton rb = new RadioButton();
                //altering rb shape not working
                Rectangle rec = new Rectangle();
                
                rb.setShape(rec);
//                rb.setMaxWidth(MobileApplication.getInstance().getScreenWidth());
//                rb.setMinWidth(MobileApplication.getInstance().getScreenWidth());
                rb.setMaxWidth(300);
//              text wrapping not working
                rb.setWrapText(true);
                rb.wrapTextProperty().set(true);
                rb.setTextOverrun(OverrunStyle.CLIP);
                rb.setText(choices.charAt(i) + ": " + String.valueOf(rset.getObject(5)));
                tbs.add(rb);
                SingleQuestions.radioList.add(rb);
                tbg.getToggles().add(rb);
                i++;
            }
            SingleQuestions.questionList.add(new SingleQuestion(questionId, chapterId, sectionId, qText,
                    hint, key, tbg));
            SingleQuestions.hintList.clear();
            try {
                if (hint.length() > 0) {
                    
                    Button hintButton = new Button("", new ImageView(light));
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
