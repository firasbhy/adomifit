/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.StaticValue;

import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class profile extends BaseForm{

    public profile(Form previous,Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
           setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("profil");
           super.addSideMenu(res);
         getContentPane().setScrollVisible(false);
        User ul = StaticValue.user ; 
        try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\pi\\public\\images\\aliments\\"+ul.getImage()+"").scaledHeight(400);
                                add(imagee);
                            } catch (IOException ex) {

                            }
        Label tfname = new Label(ul.getNom()) ; 
        Label tfprename = new Label(ul.getPrenom()) ;
        Label tfimage = new Label(ul.getImage()) ;
        Label tfemail = new Label(ul.getEmail()) ;
        Label tfusername = new Label(ul.getNom()) ;
        addAll(tfname,tfprename,tfimage,tfemail,tfusername) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ;
    }
    
    
        
        
        
        
    
}
