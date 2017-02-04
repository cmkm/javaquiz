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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Javaquiz extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";
    public static final String CHAPTER_VIEW = "Chapter View";
    public static final String SECTION_VIEW = "Section View";
    public static final String QUESTION_VIEW = "Question View";
    public static final String MENU_LAYER = "Side Menu";

    @Override
    public void init() {
        initializeDB();
        addViewFactory(PRIMARY_VIEW, () -> new PrimaryView(PRIMARY_VIEW).getView());
        addViewFactory(SECONDARY_VIEW, () -> new SecondaryView(SECONDARY_VIEW).getView());
        addViewFactory(CHAPTER_VIEW, () -> new ChapterView(CHAPTER_VIEW).getView());
        addViewFactory(SECTION_VIEW, () -> new SectionView(SECTION_VIEW).getView());
        addViewFactory(QUESTION_VIEW, () -> new QuestionView(QUESTION_VIEW).getView());

        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    public void initializeDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "phillip", "abc123");
            Statement stmt = connection.createStatement();
            String getChapters = "Select * from chapter";
            ResultSet rset = stmt.executeQuery(getChapters);
            while (rset.next()) {
                Chapters.chapterList.add(new Chapter(rset.getString(1), String.valueOf(rset.getObject(2))));
            }
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
