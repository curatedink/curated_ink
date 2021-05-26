package com.curatedink.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private long id;

    @Column
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false)
    private boolean isArtist;

    @Column
    private String studioName;

    @Column
    private String biography;

    @Column
    private int zipcode;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Image> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_style",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "style_id")}
    )
    private List<Style> styles;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private User user;

    @OneToMany
    @JoinTable(
            name = "followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "followed_id")}
    )
    private List<User> followingList;

    // Constructors
    public User() {
    }

    // Copy Constructor
    // Used for the authentication process (login/logout):
    // Used in fulfill the the contract defined by the interfaces in the security package
    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        displayName = copy.displayName;
        isArtist = copy.isArtist;
        studioName = copy.studioName;
        biography = copy.biography;
        zipcode = copy.zipcode;
        email = copy.email;
        images = copy.images;
    }

    public User(long id, String username, String password, String displayName, boolean isArtist, String studioName, String biography, int zipcode, String email, List<Image> images, List<User> followingList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.isArtist = isArtist;
        this.studioName = studioName;
        this.biography = biography;
        this.zipcode = zipcode;
        this.email = email;
        this.images = images;
        this.followingList = followingList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean getIsArtist() {
        return isArtist;
    }

    public void setIsArtist(boolean isArtist) {
        this.isArtist = isArtist;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<User> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<User> followingList) {
        this.followingList = followingList;
    }
}
