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
public class Forum {
     private int id;
 private  String titre;
private int nbrparticipants;


private int commentaires;


    public Forum() {
    }
    
  public Forum(String titre, int nbrparticipants) {
        this.titre = titre;
        this.nbrparticipants = nbrparticipants;
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

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }

    public int getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(int commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", titre=" + titre + ", nbrparticipants=" + nbrparticipants + ", commentaires=" + commentaires + '}';
    }

    

}
