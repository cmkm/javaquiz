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
public class Answer {
    
    private int id;
    private int question_id;
    private String text;
    private Boolean correct;
    private Boolean selected;
    
    public Answer(int id, int question_id, String text, Boolean correct, Boolean selected) {
        this.id = id;
        this.question_id = question_id;
        this.text = text;
        this.correct = correct;
        this.selected = selected;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getQuestionId() {
        return question_id;
    }
    
    public void setQuestionId(int question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Boolean isCorrect() {
        return correct;
    }
    
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
    
    public Boolean isSelected() {
        return selected;
    }
    
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return id + ": " + text;
    }
    
}
