package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.SECTION_VIEW;
import com.javaquiz.QuestionCell;
import com.javaquiz.model.Question;
import com.javaquiz.model.Questions;
import javafx.scene.layout.VBox;

public class QuestionView extends View {

    public static CharmListView<Question, Integer> questionListView;

    public QuestionView(String name) {
        super(name);

        VBox container = new VBox();
        questionListView = new CharmListView<>(Questions.questionList);
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
            appBar.setTitleText(SectionView.charmListView.getSelectedItem().getChapter_id() + "." +
                    SectionView.charmListView.getSelectedItem().getSection_id());
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
