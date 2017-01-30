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
import javax.swing.event.DocumentEvent.EventType;
import javax.swing.event.HyperlinkEvent;


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
                tile.setOnMouseClicked(e -> {
            System.out.println("HERE");
        });
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
