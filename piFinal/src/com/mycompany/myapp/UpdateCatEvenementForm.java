/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieEvenement;
import com.mycompany.services.ServiceCatEvenement;

/**
 *
 * @author myria
 */
public class UpdateCatEvenementForm extends BaseForm {
    Form current;
    public UpdateCatEvenementForm(Resources res , CategorieEvenement cat){
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolBar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Catégorie");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        TextField libelle = new TextField(cat.getLibelle(),"Libelle",20,TextField.ANY);
        TextField description = new TextField(cat.getDescription(),"Description",20,TextField.ANY);
        
        libelle.setUIID("NewTopLine");
        description.setUIID("NewTopLine");
        
        libelle.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
        //btnModifier.setUIID("button");
        
        btnModifier.addPointerPressedListener(l ->{
            cat.setLibelle(libelle.getText());
            cat.setDescription(description.getText());
           
            if(ServiceCatEvenement.getInstance().modifierCatEvenement(cat)){
                new ListEForm(res).show();
            }
              });
            Button btnAnnuler = new Button ("Annuler");
            btnAnnuler.addActionListener(e ->{
                new ListEForm(res).show();
            });
            
            Label la = new Label("");
            Label lb = new Label("");
            Label lc = new Label("");
            Label ld = new Label("");
            Label le = new Label("");
            Container content = BoxLayout.encloseY(la,lb,new FloatingHint(libelle),createLineSeparator(),new FloatingHint(description),createLineSeparator(),lc,ld,btnModifier,btnAnnuler );
        
add(content);
show();
    }
}
