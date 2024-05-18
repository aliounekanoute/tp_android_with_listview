package com.example.exemplewithlistview;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MangaCharacter implements Serializable {
    private Integer id;
    private String image;
    private String name;
    private String title;
    private String description;

    public MangaCharacter(String image, String name, String title, String description) {
        this(null, image, name, title, description);
    }

    public MangaCharacter(Integer id, String image, String name, String title, String description) {
        this.image = image;
        this.name = name;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "MangaCharacter(id="+id+", name="+name+")";
    }
}
