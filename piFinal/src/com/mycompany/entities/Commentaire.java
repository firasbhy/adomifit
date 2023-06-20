/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;


/**
 *
 * @author Asus
 */
public class Commentaire {
     private int id;
 private  String contenu;
private String type;

private int forum;
private int user;
 private int vus;
  private Date temps;

    public Commentaire(String contenu, String type, int forum,int  user ) {
        this.contenu = contenu;
        this.type = type;
        this.forum = forum;
        this.user = user;
        
    }

    public Commentaire(String contenu, String type, Date temps) {
        this.contenu = contenu;
        this.type = type;
        this.temps = temps;
    }

    public Commentaire(String contenu, String type) {
        this.contenu = contenu;
        this.type = type;
    }

    public Commentaire(int id, String contenu, String type, int forum, int user, int vus, Date temps) {
        this.id = id;
        this.contenu = contenu;
        this.type = type;
        this.forum = forum;
        this.user = user;
        this.vus = vus;
        this.temps = temps;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getForum() {
        return forum;
    }

    public void setForum(int forum) {
        this.forum = forum;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getVus() {
        return vus;
    }

    public void setVus(int vus) {
        this.vus = vus;
    }

    public Date getTemps() {
        return temps;
    }

    public void setTemps(Date temps) {
        this.temps = temps;
    }
    

}
