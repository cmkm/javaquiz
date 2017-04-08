package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.CHAPTER_VIEW;
import com.javaquiz.SectionCell;
import com.javaquiz.model.Section;
import com.javaquiz.model.Sections;

public class SectionView extends View {

    public static CharmListView<Section, Integer> charmListView;

    public SectionView(String name) {
        super(name);
        //Sections.sectionList.sort(bySection);
        charmListView = new CharmListView<>(Sections.sectionList);
        charmListView.setCellFactory(p -> new SectionCell());
        setCenter(charmListView);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                -> MobileApplication.getInstance().switchView(CHAPTER_VIEW)));
        try {
            appBar.setTitleText(ChapterView.charmListView.getSelectedItem().getChapter_id()+"."+ChapterView.charmListView.getSelectedItem().toString());
        } catch (Exception ex) {
            appBar.setTitleText("Sections");
        }
    }

    public View getView(String name) {
        return new SectionView(name);
    }

    public View getView() {
        return new SectionView("Sections");
    }
}
