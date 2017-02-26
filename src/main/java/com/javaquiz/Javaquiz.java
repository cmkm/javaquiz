package com.javaquiz;

import com.javaquiz.views.PrimaryView;
import com.javaquiz.views.SecondaryView;
import com.javaquiz.views.ChapterView;
import com.javaquiz.views.SectionView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.Swatch;
import com.javaquiz.model.Chapter;
import com.javaquiz.model.Chapters;
import com.javaquiz.views.QuestionView;
import com.javaquiz.views.SingleQuestionView;
import com.javaquiz.views.SummaryView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Javaquiz extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";
    public static final String CHAPTER_VIEW = "Chapter View";
    public static final String SECTION_VIEW = "Section View";
    public static final String QUESTIONS_VIEW = "Questions View";
    public static final String QUESTION_VIEW = "Question View";
    public static final String SUMMARY_VIEW = "Summary View";
    public static final String MENU_LAYER = "Side Menu";
        Comparator<Chapter> byChapter =
	(Chapter o1, Chapter o2)->Integer.parseInt(o1.getChapter_id()) - Integer.parseInt(o2.getChapter_id());

    @Override
    public void init() {
        initializeDB();
        addViewFactory(PRIMARY_VIEW, () -> new PrimaryView(PRIMARY_VIEW).getView());
        addViewFactory(SECONDARY_VIEW, () -> new SecondaryView(SECONDARY_VIEW).getView());
        addViewFactory(CHAPTER_VIEW, () -> new ChapterView(CHAPTER_VIEW).getView());
        addViewFactory(SECTION_VIEW, () -> new SectionView(SECTION_VIEW).getView());
        addViewFactory(QUESTIONS_VIEW, () -> new QuestionView(QUESTIONS_VIEW).getView());
        addViewFactory(QUESTION_VIEW, () -> new SingleQuestionView(QUESTION_VIEW).getView());
        addViewFactory(SUMMARY_VIEW, () -> new SummaryView(SUMMARY_VIEW).getView());
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    public void initializeDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getChapters = "Select * from chapter";
            ResultSet rset = stmt.executeQuery(getChapters);
            Chapters.cb.getItems().add("All");
            while (rset.next()) {
                Chapters.chapterList.add(new Chapter(rset.getString(1), String.valueOf(rset.getObject(2))));         
            } 
            Chapters.chapterList.sort(byChapter);
            for (int i = 0; i < Chapters.chapterList.size(); i++) {
                Chapters.cb.getItems().add(Chapters.chapterList.get(i));
            }
            Chapters.cb.setValue("All");
            Chapters.cbList.add(Chapters.cb);
            System.out.println(Chapters.cbList.size());
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.PINK.assignTo(scene);
        scene.getStylesheets().add(Javaquiz.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(Javaquiz.class.getResourceAsStream("/icon.png")));
    }
}
