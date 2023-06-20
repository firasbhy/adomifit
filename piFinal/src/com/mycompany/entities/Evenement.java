/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author myria
 */
public class Evenement {
    
     private int id; 
    private String titre; 
    private String description; 
    private String image; 
    private String horraire; 
    private float longitude;
     private float latitude;
    
    
    
    CategorieEvenement categorieEvenement;
    private int categorie_evenement_id;

    public Evenement() {
    }

    public Evenement(int id, String titre, String description, String image, String horraire, float longitude, float latitude, CategorieEvenement categorieEvenement, int categorie_evenement_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.horraire = horraire;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categorieEvenement = categorieEvenement;
        this.categorie_evenement_id = categorie_evenement_id;
    }

    public Evenement(String titre, String description, String image, String horraire, float longitude, float latitude, CategorieEvenement categorieEvenement, int categorie_evenement_id) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.horraire = horraire;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categorieEvenement = categorieEvenement;
        this.categorie_evenement_id = categorie_evenement_id;
    }

    public Evenement(String titre, String description, float longitude, float latitude) {
        this.titre = titre;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Evenement(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Evenement(String titre, String description, String horraire) {
        this.titre = titre;
        this.description = description;
        this.horraire = horraire;
    }

    public Evenement(String titre, String description,String image, String horraire, float longitude, float latitude,int categorie_evenement_id) {
         this.titre = titre;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
         this.horraire = horraire;
          
            this.categorie_evenement_id = categorie_evenement_id;
       }

   
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHorraire() {
        return horraire;
    }

    public void setHorraire(String horraire) {
        this.horraire = horraire;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public CategorieEvenement getCategorieEvenement() {
        return categorieEvenement;
    }

    public void setCategorieEvenement(CategorieEvenement categorieEvenement) {
        this.categorieEvenement = categorieEvenement;
    }

    public int getCategorie_evenement_id() {
        return categorie_evenement_id;
    }

    public void setCategorie_evenement_id(int categorie_evenement_id) {
        this.categorie_evenement_id = categorie_evenement_id;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + ", horraire=" + horraire + ", longitude=" + longitude + ", latitude=" + latitude + ", categorieEvenement=" + categorieEvenement + ", categorie_evenement_id=" + categorie_evenement_id + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.titre);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.image);
        hash = 29 * hash + Objects.hashCode(this.horraire);
        hash = 29 * hash + Float.floatToIntBits(this.longitude);
        hash = 29 * hash + Float.floatToIntBits(this.latitude);
        hash = 29 * hash + Objects.hashCode(this.categorieEvenement);
        hash = 29 * hash + this.categorie_evenement_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.longitude) != Float.floatToIntBits(other.longitude)) {
            return false;
        }
        if (Float.floatToIntBits(this.latitude) != Float.floatToIntBits(other.latitude)) {
            return false;
        }
        if (this.categorie_evenement_id != other.categorie_evenement_id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.horraire, other.horraire)) {
            return false;
        }
        if (!Objects.equals(this.categorieEvenement, other.categorieEvenement)) {
            return false;
        }
        return true;
    }

   
   
    
}
