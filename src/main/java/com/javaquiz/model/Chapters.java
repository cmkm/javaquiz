/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.model;

import com.javaquiz.model.Chapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cmkm
 */
public class Chapters {
    public static ObservableList<Chapter> chapterList = FXCollections.observableArrayList(
            new Chapter(1, "Chapter One"), 
            new Chapter(2, "Chapter Two")
    );
}
