/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.QUESTIONS_VIEW;
import com.javaquiz.SingleQuestionCell;
import com.javaquiz.model.SingleQuestion;
import com.javaquiz.model.SingleQuestions;
import static com.javaquiz.model.SingleQuestions.questionList;
import java.util.ArrayList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author James
 */
public class SingleQuestionView extends View {

    public static CharmListView<SingleQuestion, Integer> questionView;
    public static CharmListView<RadioButton, Integer> radioView;
    public static ListView<Button> buttonView;
    public static ListView<Button> hintView;
    public static ToggleButtonGroup tbg;
    String choices = "abcdefghijklmnopqrstuvwxyz";

    //private final CharmListView<Answer, Integer> answerListView;
    public SingleQuestionView(String name) {
        super(name);
        VBox container = new VBox();
        ArrayList<RadioButton> tbs = new ArrayList<>();
        //container.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        questionView = new CharmListView<>(SingleQuestions.questionList);
        questionView.setMaxHeight(150);
        questionView.setMinHeight(150);
        //answerListView = new CharmListView<>(Answers.answerList);
        //container.getChildren().addAll(questionListView, answerListView);
        container.getChildren().add(questionView);
        //Cell Factory isn't necessary but provides easy word wrap option for text
        questionView.setCellFactory(p -> new SingleQuestionCell());
        tbg = SingleQuestions.questionList.get(0).getTBG();
        container.getChildren().add(tbg);
        radioView = new CharmListView<>(SingleQuestions.radioList);
        container.getChildren().add(radioView);
        buttonView = new ListView<>(SingleQuestions.buttonList);
        buttonView.setOrientation(Orientation.HORIZONTAL);
        buttonView.getItems().get(0).setOnAction(e -> checkAnswer());
        buttonView.getItems().get(1).setOnAction(e -> getNextQuestion());
       // hintView = new ListView<>(SingleQuestions.hintList);
        //hintView.getItems().get(0).setOnAction(e -> showHint());
        container.getChildren().add(buttonView);
        setCenter(container);
    }

    public void showHint() {
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, questionList.get(0).getHString());
        Image light_img = new Image(getClass().getResourceAsStream("/bigLight.png"));
        alert.setGraphic(new ImageView(light_img));
        alert.showAndWait();
    }
    public void checkAnswer() {
        Alert alert = null;
        String answer = questionList.get(0).getKeyLetter();
        boolean correct = true;
        for (int i = 0; i < answer.length(); i++) {
            int choice = choices.lastIndexOf(answer.charAt(i));
            if (!questionList.get(0).getTBG().getToggles().get(choice).isSelected()) {
                correct = false;;
            }
        }
        if (correct) {
            alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Correct!");
//                add custom icon
            Image check_img = new Image(getClass().getResourceAsStream("/check.png"));
            alert.setGraphic(new ImageView(check_img));
            alert.showAndWait();
        } else {
            alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR, "Try again.");
            alert.showAndWait();
        }
    }

    public void getNextQuestion() {
        System.out.println("TESTING HERE");
        System.out.println(QuestionView.questionListView.selectedItemProperty().getValue());
//        QuestionView.questionListView.setSelectedItem(40);        
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                -> MobileApplication.getInstance().switchView(QUESTIONS_VIEW)));
        try {
            appBar.setTitleText(QuestionView.questionListView.getSelectedItem().getQuestion_id());
        } catch (Exception ex) {
            appBar.setTitleText("Question");
        }
    }

    public View getView(String name) {
        return new SingleQuestionView(name);
    }

    public View getView() {
        return new SingleQuestionView("Question");
    }
}
