/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author myria
 */
public class CategorieEvenement {
    private int id; 
    private String libelle; 
    private String description; 
    private String image;

    public CategorieEvenement() {
    }

    public CategorieEvenement(String libelle, String description, String image) {
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public CategorieEvenement(int id, String libelle, String description, String image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    @Override
    public String toString() {
        return " libelle=" + libelle  ;
    }
    
    
}
