package com.javaquiz.model;

public class Question {

    private String question_id;
    private String chapter_id;
    private String section_id;
    private String text;
    private String hString;
    private String keyLetter;
    private String status;

    public Question(String question_id, String chapter_id, String section_id, String text, String hString, String keyLetter, String status) {
        this.question_id = question_id;
        this.chapter_id = chapter_id;
        this.section_id = section_id;
        this.text = text;
        this.hString = hString;
        this.keyLetter = keyLetter;
        this.status = status;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
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

    @Override
    public String toString() {
        return question_id + ": " + text;
    }
}
