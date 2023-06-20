/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author sonicmaster
 */
public class Programme {
    private int id;
    private int categorie_programme_id;
    private int abn;
    private int  jaime;
    private int jaimepas;
    private String titre ; 
    private String description;
    private String image;

    public Programme() {}

    public Programme(int categorie_programme_id, int abn, int jaime, int jaimepas, String titre, String description, String image) {
        this.categorie_programme_id = categorie_programme_id;
        this.abn = abn;
        this.jaime = jaime;
        this.jaimepas = jaimepas;
        this.titre = titre;
        this.description = description;
        this.image = image;
    }
    

    public Programme(int id, int categorie_programme_id, int abn, int jaime, int jaimepas, String titre, String description, String image) {
        this.id = id;
        this.categorie_programme_id = categorie_programme_id;
        this.abn = abn;
        this.jaime = jaime;
        this.jaimepas = jaimepas;
        this.titre = titre;
        this.description = description;
        this.image = image;
        
    }
    

    @Override
    public String toString() {
        return "Programme{" + "id=" + id + ", categorie_programme_id=" + categorie_programme_id + ", abn=" + abn + ", jaime=" + jaime + ", jaimepas=" + jaimepas + ", titre=" + titre + ", description=" + description + ", image=" + image + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_programme_id() {
        return categorie_programme_id;
    }

    public void setCategorie_programme_id(int categorie_programme_id) {
        this.categorie_programme_id = categorie_programme_id;
    }

    public int getAbn() {
        return abn;
    }

    public void setAbn(int abn) {
        this.abn = abn;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public int getJaimepas() {
        return jaimepas;
    }

    public void setJaimepas(int jaimepas) {
        this.jaimepas = jaimepas;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   

    
}
