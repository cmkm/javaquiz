package com.javaquiz;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import static com.javaquiz.Javaquiz.SECTION_VIEW;

import com.javaquiz.model.Chapter;
import com.javaquiz.model.Section;
import com.javaquiz.model.Sections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;

public class ChapterCell extends CharmListCell<Chapter> {

    Comparator<Section> bySection =
	(Section o1, Section o2)->{
            String id1 = o1.getSection_id();
            String id2 = o2.getSection_id();
            String[] tokens1 = id1.split("-");
            String[] tokens2 = id2.split("-");
            
            return Integer.parseInt(tokens1[0]) - Integer.parseInt(tokens2[0]);
        };
        
    private final ListTile tile;

    // TODO: custom numbering graphics
    public ChapterCell() {
        this.tile = new ListTile();
        tile.setPrimaryGraphic(MaterialDesignIcon.BOOK.graphic());
        setText(null);
        tile.setOnMouseClicked(e -> {
            String id = super.itemProperty().getValue().getChapter_id();
            System.out.println(super.itemProperty().getValue().getChapter_id());
            populateSections(id);
            MobileApplication.getInstance().switchView(SECTION_VIEW);
        });
    }

    public void populateSections(String id) {
        try {
            Sections.sectionList.clear();
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://liang.armstrong.edu:3306/team5", "team5", "tiger");
            Statement stmt = connection.createStatement();
            String getSections = "Select * from section where chapterId = " + id;
            ResultSet rset = stmt.executeQuery(getSections);
            while (rset.next()) {
                Sections.sectionList.add(new Section(rset.getString(1), rset.getString(2), String.valueOf(rset.getObject(3))));
            }
            Sections.sectionList.sort(bySection);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }

    @Override
    public void updateItem(Chapter item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getChapter_id() + ": " + item.getName());
        }
        setGraphic(tile);
    }
}
