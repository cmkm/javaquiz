/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.model;

import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import static com.javaquiz.views.SingleQuestionView.tbg;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author James
 */
public class SingleQuestions {

    public static ToggleButtonGroup tbg = new ToggleButtonGroup();
    public static ArrayList<RadioButton> tbs = new ArrayList<>();
    public static final Button SUBMIT = new Button("Submit");    
    public static final Button NEXT = new Button("Next");    
    public static ObservableList<RadioButton> radioList = FXCollections.observableArrayList();
    public static ObservableList<SingleQuestion> questionList = FXCollections.observableArrayList(
            new SingleQuestion("0", "0", "0", "No Question Selected", "Here's a hint", "Here's a keyletter", tbg, tbs)
    //            new Question("2", "1", "1", "Question one?", false, "Here's a hint", "")
    );
    public static ObservableList<Button> buttonList = FXCollections.observableArrayList(
            SUBMIT, NEXT);
        public static ObservableList<Button> hintList = FXCollections.observableArrayList();
//    public void SingleQuestions() {
//        SUBMIT.setOnAction(e -> checkAnswer());
//    }
        
    }

