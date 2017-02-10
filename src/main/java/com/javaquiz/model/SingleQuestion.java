/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.model;

import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;

/**
 *
 * @author James
 */
public class SingleQuestion {
    private String question_id;
    private String chapter_id;
    private String section_id;
    private String text;
    private String hString;
    private String keyLetter;
    private ToggleButtonGroup tbg;
    private ArrayList<RadioButton> tbs;
    private ObservableList radioList;

    public SingleQuestion(String question_id, String chapter_id, String section_id, String text, 
            String hString, String keyLetter, ToggleButtonGroup tbg, ArrayList<RadioButton> tbs) {
        this.question_id = question_id;
        this.chapter_id = chapter_id;
        this.section_id = section_id;
        this.text = text;
        this.hString = hString;
        this.keyLetter = keyLetter;
        this.tbg = tbg;
        this.tbs = tbs;
    }  

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }
    
    
    
    public String getSectionId() {
        return section_id;
    }
    
    public void setSectionId(String section_id) {
        this.section_id = section_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String gethString() {
        return hString;
    }

    public void sethString(String hString) {
        this.hString = hString;
    }

    public String getKeyLetter() {
        return keyLetter;
    }

    public void setKeyLetter(String keyLetter) {
        this.keyLetter = keyLetter;
    }
    
    public String getHString() {
        return hString;
    }
    
    public void setHString(String hString) {
        this.hString = hString;
    }
    
    public ToggleButtonGroup getTBG() {
        return tbg;
    }
    
    public void setTBG(ToggleButtonGroup tbg) {
        this.tbg = tbg;
    }

        public ArrayList<RadioButton> getTBS() {
        return tbs;
    }
    
    public void setTBS(ArrayList<RadioButton> tbs) {
        this.tbs = tbs;
    }
    
            public ObservableList getRadioList() {
        return radioList;
    }
    
    public void setTBS(ObservableList tbs) {
        this.radioList = radioList;
    }
    
    @Override
    public String toString() {
        return question_id + ": " + text;
    }
    

}
