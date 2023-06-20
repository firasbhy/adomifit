/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;



import com.codename1.components.SpanLabel;


import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CN;

import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUtilisateur;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;


/**
 *
 * @author ASUS
 */
public class AddUtilForm extends BaseForm {

    private Resources res;

    public AddUtilForm(Form previous,Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        Button retour = new Button("retour") ; 
        super.addSideMenu(res);
                   retour.addActionListener(e -> new NewsfeedForm(res).show());

         Vector<String> vectorRole; 
        vectorRole = new Vector() ; 
        vectorRole.add("client") ; 
        vectorRole.add("admin");
            vectorRole.add("nutrisioniste") ;
                vectorRole.add("coach") ;
    ComboBox<String>tfroles = new ComboBox<>(vectorRole) ; 
        setTitle("Add a new Utilisateur") ;
        setLayout(BoxLayout.y()) ; 
        TextField tfname = new TextField("","nom") ; 
        TextField tfprename = new TextField("","prenom") ;
        //TextField tfimage = new TextField("","image") ;
        TextField tfemail = new TextField("","email",20,TextField.EMAILADDR) ;
      
        TextField tfpassword = new TextField("","password",20,TextField.PASSWORD) ;
        TextField tfverif = new TextField("","Verifier password",20,TextField.PASSWORD) ;
        
        Button img = new Button("chooseFile") ; 
        CheckBox multiSelect = new CheckBox("Multi-select");
        //TextField tfactivation = new TextField("","activation_token") ;
        //TextField tfreset = new TextField("","reset_token") ;
        User u = new User(); 
        Button btnValid = new Button("add User") ; 
        
        
        
        
        
      
        
        
        
        
        
        
        
        
        
        btnValid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                
              
               
               if(tfname.getText().equals("")&& tfprename.getText().equals("")&& tfpassword.getText().equals(""))
                   Dialog.show("Erreur", "remplir les champs", "ok",null) ; 
               if (!tfpassword.getText().equals(tfverif.getText()))
                   Dialog.show("Erreur", "verifer password", "ok",null) ; 
               u.setNom(tfname.getText());
               u.setPrenom(tfprename.getText());
               
               u.setEmail(tfemail.getText());
               u.setRole(tfroles.getSelectedItem().toString());
              
               u.setPassword(tfpassword.getText());
               
               if(new ServiceUtilisateur().addUtil(u))
                   Dialog.show("Success", "Connection accepted", new Command("ok")) ; 
               else 
                   Dialog.show("ERROR", "server error", new Command("ok")) ; 
            }
        
        });
        

        addAll(tfname,tfprename,tfemail,tfpassword,tfverif,tfroles,img,btnValid) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
    }
    
     
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
    
}
