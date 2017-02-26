package com.javaquiz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Chapters {
    public static ObservableList<Chapter> chapterList = FXCollections.observableArrayList();       
    public static ObservableList<ComboBox> cbList = FXCollections.observableArrayList();
    public static ComboBox cb = new ComboBox(); 
}
