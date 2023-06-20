/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
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
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUtilisateur;

import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class EditUtiForml extends BaseForm {
  Form current ; 
     public EditUtiForml(Resources res,Form previous,User u) {
     super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Modifier utilisateurs");
        
          getContentPane().setScrollVisible(false);
          
           super.addSideMenu(res);
         Vector<String> vectorRole; 
        vectorRole = new Vector() ; 
        vectorRole.add("Client") ; 
        vectorRole.add("ADMIN") ;
    ComboBox<String>tfroles = new ComboBox<>(vectorRole) ; 
        setTitle("Modifier l Utilisateur"+" "+u.getNom()) ;
        setLayout(BoxLayout.y()) ; 
        TextField tfname = new TextField("","nom") ; 
        TextField tfprename = new TextField("","prenom") ;
        //TextField tfimage = new TextField("","image") ;
        TextField tfemail = new TextField("","email",20,TextField.EMAILADDR) ;
        TextField tfusername = new TextField("","username") ;
        //TextField tfpassword = new TextField("","password",20,TextField.PASSWORD) ;
        //TextField tfverif = new TextField("","Verifier password",20,TextField.PASSWORD) ;
      
        //TextField tfactivation = new TextField("","activation_token") ;
        //TextField tfreset = new TextField("","reset_token") ;
        
        Button btnValid = new Button("Edit User") ; 
        
        btnValid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
              
               if(tfname.getText().equals("")&&  tfemail.getText().equals("")&& tfprename.getText().equals("")){
                   Dialog.show("Erreur", "remplir les champs", "ok",null) ; 
              
               //u.setPassword(tfpassword.getText());
               }
              else if(new ServiceUtilisateur().editUtilisateur(u)){
                    
               u.setNom(tfname.getText());
               u.setPrenom(tfprename.getText());
               //u.setImage(tfimage.getText()); 
               u.setEmail(tfemail.getText());
               //u.setRole(tfroles.getSelectedItem().toString());
               
                   Dialog.show("Success", "Modification accepted", new Command("ok")) ; 
                   new ListUtilForm(previous,res).show();
               }
          
                    
            }
        
        });
        addAll(tfname,tfprename,tfemail,tfusername,tfroles,btnValid) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
    }
    
     
    
}
