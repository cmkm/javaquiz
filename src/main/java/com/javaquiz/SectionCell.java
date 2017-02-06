/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import static com.javaquiz.Javaquiz.QUESTION_VIEW;
import static com.javaquiz.Javaquiz.SECTION_VIEW;
import com.javaquiz.model.Question;
import com.javaquiz.model.Questions;
import com.javaquiz.model.Section;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cmkm
 */
public class SectionCell extends CharmListCell<Section> {

    private final ListTile tile;

    // TODO: custom completion graphics
    public SectionCell() {
        this.tile = new ListTile();
        //tile.setPrimaryGraphic(MaterialDesignIcon.ASSIGNMENT.graphic());
        setText(null);
        tile.setOnMouseClicked(e -> {
            String sectionId = super.itemProperty().getValue().getSection_id();
            String chapterId = super.itemProperty().getValue().getChapter_id();
            System.out.println(sectionId + ": " + chapterId);
            populateQuestions(sectionId, chapterId);
            MobileApplication.getInstance().switchView(QUESTION_VIEW);
        });
    }

    @Override
    public void updateItem(Section item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            if (item.getName().equals("")) {
                tile.textProperty().setAll(item.getSection_id());
            } else {
                tile.textProperty().setAll(item.getSection_id() + ": " + item.getName());
            }
        }
        setGraphic(tile);
    }
    
    public void populateQuestions(String sectionId, String chapterId) {
        try {
            Questions.questionList.clear();
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "root", "catfood");
            Statement stmt = connection.createStatement();
            String getSections = "Select * from question where chapterId = " + chapterId + " and sectionId = " + sectionId;
            ResultSet rset = stmt.executeQuery(getSections);
            while (rset.next()) {
                Questions.questionList.add(new Question(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
                        false, rset.getString(6), rset.getString(5)));
                //System.out.println(rset);
                //Sections.sectionList.add(new Section(rset.getString(1), rset.getString(2), String.valueOf(rset.getObject(3))));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }
}
