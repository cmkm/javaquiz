package com.javaquiz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sections {
    public static ObservableList<Section> sectionList = FXCollections.observableArrayList(
            new Section("0", "0", "No chapter selected")
    );
}
