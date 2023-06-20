/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Kouki
 */
public class SuiviRegime {
    private int id; 
    private String titre;
    private String remarque;
    private int note; 
    private int iduser;
    private int idregime;

    public SuiviRegime() {
    }

    public SuiviRegime(String titre,String remarque, int note) {
        this.titre = titre;
        this.remarque = remarque;
        this.note = note;
    }
    
    public SuiviRegime(String titre,String remarque, int note, int iduser, int idregime) {
        this.titre = titre;
        this.remarque = remarque;
        this.note = note;
        this.iduser = iduser;
        this.idregime = idregime;
    }

    public SuiviRegime(int id,String titre, String remarque, int note, int iduser, int idregime) {
        this.id = id;
        this.titre = titre;
        this.remarque = remarque;
        this.note = note;
        this.iduser = iduser;
        this.idregime = idregime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdregime() {
        return idregime;
    }

    public void setIdregime(int idregime) {
        this.idregime = idregime;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    
    @Override
    public String toString() {
        return "SuiviRegime{" + "id=" + id + ", titre=" + titre + ", remarque=" + remarque + ", note=" + note + ", iduser=" + iduser + ", idregime=" + idregime + '}';
    }
    
    
    
    
    
    
}
