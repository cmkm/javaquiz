/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.javaquiz.model.Answer;

/**
 *
 * @author cmkm
 */
public class Answers {
    public static ObservableList<Answer> answerList = FXCollections.observableArrayList(
            new Answer(1, 1, "Answer one", false, false), 
            new Answer(2, 1, "Answer two", false, false)
    );
}
