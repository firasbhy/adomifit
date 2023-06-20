/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commentaire;
import com.mycompany.services.ServiceCommentaire;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class AjouterCommentaireForm extends BaseForm{
    
    Form current;
    
    public AjouterCommentaireForm(Resources res)
    {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current =this;
          setToolbar(tb);
        
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Commentaire");
        getContentPane().setScrollVisible(false);
        TextField type = new TextField("","entrer type");
        type.setUIID("TextFieldBlack");
        addStringValue("type",type);
        
        
         TextField contenu = new TextField("","entrer contenu");
        contenu .setUIID("TextFieldBlack");
        addStringValue("contenu",contenu);
        
        
        Button btnAjouter= new Button("Ajouter");
        addStringValue("",btnAjouter);
        
        btnAjouter.addActionListener((e)->{
            try{
                if(type.getText()==""||contenu.getText()=="")
                {
                     Dialog.show("Veuillez verfier les données","" ,"Annuler", "Ok");
                }
                else 
                {
                    InfiniteProgress ip= new InfiniteProgress();
                    final Dialog idialog = ip.showInfiniteBlocking();
                     SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
                     Commentaire c = new Commentaire(String.valueOf(contenu.getText()).toString(),
                                                     String.valueOf(type.getText()).toString(),
                                                     1,3      
                                                     );
                     System.out.println("data commentaire");
                     
                     //appelle mth  ajouterCommentaire m service
                     
                     ServiceCommentaire.getInstance().ajoutCommentaire(c);
                    
                    // Dialog.show("success", "Régime Ajouté ! ", new Command("ok"));
                    idialog.dispose();//na7i loading ba3ed il ajout
                    
                     refreshTheme();//actualisation
                    
                      
                     //new ListCommentaireForm(res).show();
                }
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        
    
    }

    private void addStringValue(String type,Component v) 
    {
       add(BorderLayout.west(new Label(type,"PaddedLabel"))
               .add(BorderLayout.CENTER,v));
       add(createLineSeparator(0xeeeeee));
    }
    
}
