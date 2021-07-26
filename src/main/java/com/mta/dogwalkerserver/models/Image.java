package com.mta.dogwalkerserver.models;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="image_Id")
    private Long image_Id;

    @Lob
    private byte[] content;

    private String name;


    // Getters and Setters
    public Long getImage_Id() {
        return image_Id;
    }
    public void setImage_Id(Long image_Id) {
        this.image_Id = image_Id;
    }
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
