/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.javaquiz.model.Question;

/**
 *
 * @author James
 */
public class SummaryCell extends CharmListCell<Question> {

    private final ListTile tile;

    public SummaryCell() {
        this.tile = new ListTile();
        //tile.setPrimaryGraphic(MaterialDesignIcon.ASSIGNMENT.graphic());
        setText(null);
    }

    @Override
    public void updateItem(Question item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getChapter_id()+"."+item.getSection_id()+"."+item.getQuestion_id() 
                    + " : " + item.getStatus());            
            tile.setStyle("-fx-text-fill: red;");
        }
        setGraphic(tile);
    }
}
