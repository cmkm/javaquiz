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
    private String chapter_id;
    private String answer_id;
    private String question_id;
    private String section_id;
    private String text;
    private Boolean correct;
    private Boolean selected;

    public Answer(String chapter_id, String answer_id, String question_id, String section_id, String text, Boolean correct, Boolean selected) {
        this.chapter_id = chapter_id;
        this.answer_id = answer_id;
        this.question_id = question_id;
        this.section_id = section_id;
        this.text = text;
        this.correct = correct;
        this.selected = selected;
    }
    
    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }
    
    
    public String getQuestionId() {
        return question_id;
    }
    
    public void setQuestionId(String question_id) {
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
        return answer_id + ": " + text;
    }
    
}
