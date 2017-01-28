package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import com.javaquiz.model.Chapter;
import com.javaquiz.model.Chapters;
import com.javaquiz.model.Section;
import com.javaquiz.model.Sections;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class SectionView extends View {

    private final CharmListView<Section, Integer> charmListView;

    public SectionView(String name) {
        super(name);
        
        charmListView = new CharmListView<>(Sections.sectionList);
        setCenter(charmListView);
    }
    
    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
            MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e -> 
            MobileApplication.getInstance().switchToPreviousView()));
        appBar.setTitleText("Ch 1: Sections");
                
    }
    
    public View getView(String name) {
        return new SectionView(name);
    }

    public View getView() {
        return new SectionView("Sections");
    }
    
}