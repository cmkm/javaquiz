package com.javaquiz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Questions {
        public static ObservableList<Question> questionList = FXCollections.observableArrayList(
            new Question("0", "0", "0", "No Section Selected", "Here's a hint", "") 
    );
}
