/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author MSI
 */
public class Produit {
    
    private int id,jaime,jaimepas; 
    private String nom,description,image; 

    public Produit() {
    }

    public Produit(int id, String nom, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Produit(String nom, String description, String image) {
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Produit(int id, int jaime, int jaimepas, String nom, String description, String image) {
        this.id = id;
        this.jaime = jaime;
        this.jaimepas = jaimepas;
        this.nom = nom;
        this.description = description;
        this.image = image;
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
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        return "Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + '}';
    }
    
    
   
    
    
    
    
}
