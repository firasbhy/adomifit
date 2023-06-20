/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Asus
 */
public class User {
    private  int poid,taille,id;
    private  String  nom,prenom,email,password,sexe;
    private String roles,role ;
      private String image ;
          private String activationToken ;
    private String resetToken ;
    public User(int poid, int taille, String nom, String prenom, String email, String password, String sexe) {
        this.id= id;
        this.poid = poid;
        this.taille = taille;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
      
        
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public User(int poid, int taille, int id, String nom, String prenom, String email, String password, String sexe, String roles, String role, String image, String activationToken, String resetToken) {
        this.poid = poid;
        this.taille = taille;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
        this.roles = roles;
        this.role = role;
        this.image = image;
        this.activationToken = activationToken;
        this.resetToken = resetToken;
    }

    public User() {
    }

    public User(int id, int poid ,String roles , int taille, String nom, String prenom, String email, String password) {
        this.id = id;
        this.poid = poid;
        this.taille = taille;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
       this.roles = roles;
        this.role = role;
    }
     public User(   String nom, String prenom, String email, String password) {
       
        
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
       
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public int getPoid() {
        return poid;
    }

    public int getTaille() {
        return taille;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

 

    public void setId(int id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

 

    public void setRoles(String roles) {
        this.roles = roles;
    }
    public void setPoid(int poid) {
        this.poid = poid;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSexe() {
        return sexe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 
    
    
    
}
