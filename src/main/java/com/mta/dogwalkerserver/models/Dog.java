package com.mta.dogwalkerserver.models;


import javax.persistence.*;

@Entity
@Table(name = "dog", schema="dog_walker_app")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_Id")
    private int dog_Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BREED")
    private String breed;

    @Column(name = "VACCINATED")
    private boolean vaccinated;

    @Column(name = "CASTRATED")
    private boolean castrated;

    @Column(name = "SOCIAL_PEOPLE")
    private boolean socialPeople;

    @Column(name = "SOCIAL_DOG")
    private boolean socialDog;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "image")
    private Image image;



    //CONSTRUCTOR
    public Dog() {
    }

    public Dog(String name, String breed, boolean vaccinated, boolean castrated, boolean socialPeople, boolean socialDog, Gender gender, Image image) {
        this.name = name;
        this.breed = breed;
        this.vaccinated = vaccinated;
        this.castrated = castrated;
        this.socialPeople = socialPeople;
        this.socialDog = socialDog;
        this.gender = gender;
        this.image = image;
    }

    //get and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public boolean isVaccinated() {
        return vaccinated;
    }
    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
    public boolean isCastrated() {
        return castrated;
    }
    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }
    public boolean isSocialPeople() {
        return socialPeople;
    }
    public void setSocialPeople(boolean socialPeople) {
        this.socialPeople = socialPeople;
    }
    public boolean isSocialDog() {
        return socialDog;
    }
    public void setSocialDog(boolean socialDog) {
        this.socialDog = socialDog;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getDog_Id() {
        return dog_Id;
    }

    public void setDog_Id(int dog_Id) {
        this.dog_Id = dog_Id;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
