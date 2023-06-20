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
public class CategorieProgramme {
    
      private int id;
      private String libelle ;
      private String description;
      private String image;
      private int  jaime;
      private int jaimepas;

   

     
      @Override
    public String toString() {
        return "CategorieProgrammes{" + "libelle=" + libelle + ", description=" + description + ", image=" + image +    ", jaime=" + jaime +  ", jaimepas=" + jaimepas +     '}';
    }
     
     
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public CategorieProgramme() {}

    public CategorieProgramme(String libelle, String description, String image) {
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public CategorieProgramme(int id, String libelle, String description, String image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }
    
    
    public CategorieProgramme(int id, String libelle, String description, String image, int jaime, int jaimepas) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.jaime = jaime;
        this.jaimepas = jaimepas;
    }
    public CategorieProgramme( String libelle, String description, String image, int jaime, int jaimepas) {
        
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.jaime = jaime;
        this.jaimepas = jaimepas;
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
      
    
}
