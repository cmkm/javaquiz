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
    
    private int id;
    private int section_id;
    private String text;
    private Boolean multi;
    private String hint;
    
    public Question(int id, int section_id, String text, Boolean multi, String hint) {
        this.id = id;
        this.section_id = section_id;
        this.text = text;
        this.multi = multi;
        this.hint = hint;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getSectionId() {
        return section_id;
    }
    
    public void setSectionId(int section_id) {
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
    
    public void setMulti(Boolean multi) {
        this.multi = multi;
    }
    
    public String getHint() {
        return hint;
    }
    
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return id + ": " + text;
    }
    

}
