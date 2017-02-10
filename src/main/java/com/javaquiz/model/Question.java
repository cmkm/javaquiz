/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaquiz.model;

/**
 *
 * @author cmkm
 */
public class Question {
    private String question_id;
    private String chapter_id;
    private String section_id;
    private String text;
    private Boolean multi;
    private String hString;
    private String keyLetter;

    public Question(String question_id, String chapter_id, String section_id, String text, Boolean multi, String hString, String keyLetter) {
        this.question_id = question_id;
        this.chapter_id = chapter_id;
        this.section_id = section_id;
        this.text = text;
        this.multi = multi;
        this.hString = hString;
        this.keyLetter = keyLetter;
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
    
    public Boolean isMulti() {
        return multi;
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
    
    public void setMulti(Boolean multi) {
        this.multi = multi;
    }
    
    public String getHString() {
        return hString;
    }
    
    public void setHString(String hString) {
        this.hString = hString;
    }

    @Override
    public String toString() {
        return question_id + ": " + text;
    }
}
