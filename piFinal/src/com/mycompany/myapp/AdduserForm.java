/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
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
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class AdduserForm extends BaseForm {
        

    public AdduserForm(Form previous,Resources res) {
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         Button retour = new Button("retour") ; 
                   retour.addActionListener(e -> new NewsfeedForm(res).show());
        setTitle("Add a new Utilisateur") ;
        setLayout(BoxLayout.y()) ; 
        super.addSideMenu(res);
        try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\images\\add.png").scaledHeight(600);
                                add(imagee);
                            } catch (IOException ex) {

                            }
        TextField tfname = new TextField("","nom") ; 
        TextField tfprename = new TextField("","prenom") ;
        //TextField tfimage = new TextField("","image") ;
        TextField tfemail = new TextField("","email",20,TextField.EMAILADDR) ;
        
        TextField tfpassword = new TextField("","password",20,TextField.PASSWORD) ;
        TextField tfverif = new TextField("","Verifier password",20,TextField.PASSWORD) ;

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
               
                 u.setRole("client");
               u.setRoles("ROLE_CLIENT");
               
               u.setPassword(tfpassword.getText());
               
               if(new ServiceUtilisateur().addUtil(u)){
                   Dialog.show("Success", "user est ajouter", new Command("ok")) ; 
                   previous.showBack();
               }
               else 
                   Dialog.show("ERROR", "server error", new Command("ok")) ; 
            }
        
        });
        addAll(tfname,tfprename,tfemail,tfpassword,tfverif,btnValid) ; 
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
