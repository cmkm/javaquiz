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
public class Questions {
        public static ObservableList<Question> questionList = FXCollections.observableArrayList(
            new Question("1", "1", "1", "Question one?", false, "Here's a hint", ""), 
            new Question("2", "1", "1", "Question one?", false, "Here's a hint", "")
    );
}
