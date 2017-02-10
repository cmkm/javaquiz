/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.SECTION_VIEW;
import com.javaquiz.QuestionCell;
import com.javaquiz.model.Answer;
import com.javaquiz.model.Answers;
import com.javaquiz.model.Question;
import com.javaquiz.model.Questions;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmkm
 */
public class QuestionView extends View {

    public static CharmListView<Question, Integer> questionListView;
    //private final CharmListView<Answer, Integer> answerListView;

    public QuestionView(String name) {
        super(name);

        VBox container = new VBox();
        questionListView = new CharmListView<>(Questions.questionList);
        //answerListView = new CharmListView<>(Answers.answerList);
        //container.getChildren().addAll(questionListView, answerListView);
        container.getChildren().addAll(questionListView);
        questionListView.setCellFactory(p -> new QuestionCell());
        setCenter(container);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                -> MobileApplication.getInstance().switchView(SECTION_VIEW)));
        try {
            appBar.setTitleText(SectionView.charmListView.getSelectedItem().toString());
        } catch (Exception ex) {
            appBar.setTitleText("Questions");
        }
    }

    public View getView(String name) {
        return new QuestionView(name);
    }

    public View getView() {
        return new QuestionView("Questions");
    }
}
