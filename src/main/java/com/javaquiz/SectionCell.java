/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.model.Section;

/**
 *
 * @author cmkm
 */
public class SectionCell extends CharmListCell<Section> {

    private final ListTile tile;

    // TODO: custom completion graphics
    public SectionCell() {
        this.tile = new ListTile();
        tile.setPrimaryGraphic(MaterialDesignIcon.ASSIGNMENT.graphic());
        setText(null);
        tile.setOnMouseClicked(e -> {
            String id = super.itemProperty().getValue().getSection_id();
            System.out.println(id);
            //populateQuestions(id);
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
}
