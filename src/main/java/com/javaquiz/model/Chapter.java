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
public class Chapter {

    private String chapter_id;
    private String name;

    
    public Chapter(String chapter_id, String name) {
        this.chapter_id = chapter_id;
        this.name = name;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }
   
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return chapter_id + ": " + name;
    }

}
