package com.nramos.mimoflix.model.localcompany;

import com.nramos.mimoflix.model.localgenre.LocalGenre;

import java.io.Serializable;

public class LocalCompany implements Serializable {

    private int id;
    private int logo;
    private String name;

    public LocalCompany(int id, int logo, String name) {
        this.id = id;
        this.logo = logo;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
