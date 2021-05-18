package com.curatedink.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementing
    @Column(columnDefinition = "INT UNSIGNED")
    private long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = true)
    private String studioName;

    @Column(nullable = true)
    private String creditedArtist;

    @Column(nullable = false)
    private boolean isCanvas;

    @ManyToOne
    private User user;


    // ------------------------------------------------------ Constructors:


    public Image() {
    }

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image(String imageUrl, String comment, String studioName, String creditedArtist, boolean isCanvas, User user) {
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.studioName = studioName;
        this.creditedArtist = creditedArtist;
        this.isCanvas = isCanvas;
        this.user = user;
    }


    // ------------------------------------------------------ Getters & Setters:


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getCreditedArtist() {
        return creditedArtist;
    }

    public void setCreditedArtist(String creditedArtist) {
        this.creditedArtist = creditedArtist;
    }

    public boolean getIsCanvas() {
        return isCanvas;
    }

    public void setIsCanvas(boolean isCanvas) {
        this.isCanvas = isCanvas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}