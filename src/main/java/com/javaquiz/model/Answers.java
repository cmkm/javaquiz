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
public class Answers {
    public static ObservableList<Answer> answerList = FXCollections.observableArrayList(
            new Answer("1", "1", "1", "1", "", false, false), 
            new Answer("2", "1", "1", "1", "", false, false)
    );
}
