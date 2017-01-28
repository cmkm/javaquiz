/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.views;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.model.Chapter;
import com.javaquiz.model.Chapters;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class ChapterView extends View {

    private final CharmListView<Chapter, Integer> charmListView;

    public ChapterView(String name) {
        super(name);
        
        charmListView = new CharmListView<>(Chapters.chapterList);
        setCenter(charmListView);
    }
    
    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.STAR.button());
        appBar.setTitleText("Chapters");
    }
    
}