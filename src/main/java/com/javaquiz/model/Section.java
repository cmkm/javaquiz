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
public class Section {
    
    private int id;
    private int chapter_id;
    private String name;
    
    public Section(int id, int chapter_id, String name) {
        this.id = id;
        this.chapter_id = chapter_id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getChapterId() {
        return chapter_id;
    }
    
    public void setChapterId(int chapter_id) {
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
        return id + ": " + name;
    }
    
}
