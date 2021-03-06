package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.ChapterCell;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.SUMMARY_VIEW;
import com.javaquiz.model.Chapter;
import com.javaquiz.model.Chapters;

public class ChapterView extends View {

    public static CharmListView<Chapter, Integer> charmListView;

    public ChapterView(String name) {
        super(name);   
        charmListView = new CharmListView<>(Chapters.chapterList);
        charmListView.setCellFactory(p -> new ChapterCell());
        setCenter(charmListView);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                -> MobileApplication.getInstance().switchView(SUMMARY_VIEW)));
        appBar.setTitleText("Chapters");

    }

    public View getView(String name) {
        return new ChapterView(name);
    }

    public View getView() {
        return new ChapterView("Chapters");
    }
}


