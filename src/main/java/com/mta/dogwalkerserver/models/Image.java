package com.mta.dogwalkerserver.models;

import javax.persistence.*;


//https://www.baeldung.com/java-db-storing-files
//https://spring.io/guides/gs/uploading-files/
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_Id")
    private Long image_Id;

    @Lob
    private byte[] content;

    private String name;

    String location;

    // public Image(String imageName, String location);    צריך את הקונסטרקטור הזה?



    // Getters
    public Long getImage_Id() {
        return image_Id;
    }
    public byte[] getContent() {
        return content;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }

    // Setters
    public void setImage_Id(Long image_Id) {
        this.image_Id = image_Id;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
