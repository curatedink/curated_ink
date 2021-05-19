package com.curatedink.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "styles")
public class Style {

    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementing
    @Column(columnDefinition = "INT UNSIGNED")
    private long id;

    @Column(nullable = false)
    private String style;

    @ManyToMany(mappedBy = "styles")
    private List<Style> styles;

    // ------------------------------------------------------ Constructors:

    public Style() {
    }

    public Style(String style) {
        this.style = style;
    }

    // ------------------------------------------------------ Getters & Setters:


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
