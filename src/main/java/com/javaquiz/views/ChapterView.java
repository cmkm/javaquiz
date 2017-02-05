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
import com.javaquiz.ChapterCell;
import com.javaquiz.Javaquiz;
import com.javaquiz.model.Chapter;
import com.javaquiz.model.Chapters;
import java.util.Comparator;

public class ChapterView extends View {
    Comparator<Chapter> byChapter =
	(Chapter o1, Chapter o2)->Integer.parseInt(o1.getChapter_id()) - Integer.parseInt(o2.getChapter_id());
    public static CharmListView<Chapter, Integer> charmListView;

    public ChapterView(String name) {
        super(name);   
        Chapters.chapterList.sort(byChapter);
        charmListView = new CharmListView<>(Chapters.chapterList);
        charmListView.setCellFactory(p -> new ChapterCell());
        setCenter(charmListView);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                -> MobileApplication.getInstance().switchToPreviousView()));
        appBar.setTitleText("Chapters");

    }

    public View getView(String name) {
        return new ChapterView(name);
    }

    public View getView() {
        return new ChapterView("Chapters");
    }

}


