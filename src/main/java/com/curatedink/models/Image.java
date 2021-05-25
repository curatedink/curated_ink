package com.curatedink.models;

import javax.persistence.*;
import java.util.List;

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

    @Column
    private String studioName;

    @Column
    private String creditedArtist;

    @Column(nullable = false)
    private boolean isCanvas;

    @Column(nullable = false)
    private boolean isProfileImage;

    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="image_style",
            joinColumns={@JoinColumn(name="image_id")},
            inverseJoinColumns={@JoinColumn(name="style_id")}
    )
    private List<Style> styles;


    // Constructors

    public Image() {
    }

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image(String imageUrl, String comment, String studioName, String creditedArtist, boolean isCanvas, boolean isProfileImage) {
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.studioName = studioName;
        this.creditedArtist = creditedArtist;
        this.isCanvas = isCanvas;
        this.isProfileImage = isProfileImage;
    }


    // Getters & Setters

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

    public boolean getIsProfileImage() {
        return isProfileImage;
    }

    public void setIsProfileImage(boolean isProfileImage) {
        this.isProfileImage = isProfileImage;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

}