package com.javaquiz.model;

public class Section {
    
    private String section_id;
    private String chapter_id;
    private String name;

    public Section(String section_id, String chapter_id, String name) {
        this.section_id = section_id;
        this.chapter_id = chapter_id;
        this.name = name;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
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
        return section_id + ": " + name;
    }    
}
