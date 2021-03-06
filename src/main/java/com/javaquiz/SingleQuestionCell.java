package com.javaquiz;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.javaquiz.model.SingleQuestion;

public class SingleQuestionCell extends CharmListCell<SingleQuestion> {

    private final ListTile tile;

    // TODO: custom completion graphics
    public SingleQuestionCell() {
        this.tile = new ListTile();
        //tile.setPrimaryGraphic(MaterialDesignIcon.ASSIGNMENT.graphic());
        setText(null);
    }

    @Override
    public void updateItem(SingleQuestion item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {            
            tile.textProperty().setAll(item.getText());
            if (item.getKeyLetter().length() > 1) {
                tile.textProperty().add(" Select all that apply.");
            }
            tile.wrapTextProperty().set(true);
            setGraphic(tile);
        }
    }
}
