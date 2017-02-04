/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import com.javaquiz.model.Chapter;
import com.javaquiz.model.Section;
import com.javaquiz.model.Sections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cmkm
 */
public class ChapterCell extends CharmListCell<Chapter> {

    private final ListTile tile;

    // TODO: custom numbering graphics
    public ChapterCell() {
        this.tile = new ListTile();
        tile.setPrimaryGraphic(MaterialDesignIcon.BOOK.graphic());
        setText(null);
        //need to find way to not populate sectionView multiple times
        //if (Sections.sectionList.size() < 1) {
            tile.setOnMouseClicked(e -> {
                //populateSections(this.getUserData());
                int id = super.itemProperty().getValue().getId();
                System.out.println(super.itemProperty().getValue().getId());
                populateSections(id);
            });
        //}
    }

    public void populateSections(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "james", "abc123");
            Statement stmt = connection.createStatement();
            String getSections = "Select * from section where chapterId = " + id;
            ResultSet rset = stmt.executeQuery(getSections);
            while (rset.next()) {
                Sections.sectionList.add(new Section(rset.getInt(1), rset.getInt(2), String.valueOf(rset.getObject(3))));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }

    @Override
    public void updateItem(Chapter item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getId() + ": " + item.getName());
        }
        setGraphic(tile);
    }
}
