/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cmkm
 */
public class Sections {
    public static ObservableList<Section> sectionList = FXCollections.observableArrayList(
            new Section(1, 1, "1.1 First section"), 
            new Section(1, 2, "1.2 Second section")
    );
}
