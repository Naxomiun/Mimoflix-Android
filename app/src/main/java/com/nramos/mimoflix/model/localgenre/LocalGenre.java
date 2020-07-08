package com.nramos.mimoflix.model.localgenre;

import java.io.Serializable;

public class LocalGenre implements Serializable {

    private int id;
    private int name;
    private int color;
    private int image;

    public LocalGenre(int id, int name, int color, int image) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
