/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Objects;

/**
 *
 * @author Kouki
 */
public class Regime {
    
    private int id; 
    private String type; 
    private String description; 
    private String image; 
    private String dificulte; 
    private float prix; 
    
    
    CategorieRegime categorieRegime;
    private int categorie_regime_id;
    private int iduser;
    
   

    public Regime() {
    }

    public Regime(int id, String type, String description, String image, String dificulte, float prix, int categorie_regime_id ,CategorieRegime categorieRegime , int iduser ) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
         this.categorie_regime_id = categorie_regime_id;
         this.categorieRegime = categorieRegime;
         this.iduser = iduser;
    }
      public Regime(String type, String description, String image, String dificulte, float prix, int categorie_regime_id ,CategorieRegime categorieRegime , int iduser ) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
         this.categorie_regime_id = categorie_regime_id;
         this.categorieRegime = categorieRegime;
         this.iduser = iduser;
    }


    public Regime(String type, String description, String image, String dificulte, float prix , CategorieRegime categorieRegime , int categorie_regime_id) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
        this.categorieRegime = categorieRegime;
        this.categorie_regime_id = categorie_regime_id;
    }

    public Regime(String type, String description,String dificulte , String image , float prix ,  int categorie_regime_id) {
        this.type = type;
        this.description = description;
        this.dificulte = dificulte;
        this.image = image;
        this.prix = prix;
        this.categorie_regime_id = categorie_regime_id;
    
    }
     public Regime(String type, String description,String dificulte , String image , float prix ,  int categorie_regime_id,int iduser) {
        this.type = type;
        this.description = description;
        this.dificulte = dificulte;
        this.image = image;
        this.prix = prix;
        this.categorie_regime_id = categorie_regime_id;
         this.iduser = iduser;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDificulte() {
        return dificulte;
    }

    public void setDificulte(String dificulte) {
        this.dificulte = dificulte;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
     public CategorieRegime getCategorieRegime() {
        return categorieRegime;
    }

    public void setCategorieRegime(CategorieRegime categorieRegime) {
        this.categorieRegime = categorieRegime;
    }
     public int getCategorie_regime_id() {
        return categorie_regime_id;
    }

    public void setCategorie_regime_id(int categorie_regime_id) {
        this.categorie_regime_id = categorie_regime_id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    
    

    @Override
    public String toString() {
        return "Regime{" + "id=" + id + ", type=" + type + ", description=" + description +
                ", image=" + image + ", dificulte=" + dificulte + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Regime other = (Regime) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.categorie_regime_id != other.categorie_regime_id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.categorieRegime, other.categorieRegime)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
