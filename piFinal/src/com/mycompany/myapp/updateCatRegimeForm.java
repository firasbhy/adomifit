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
import com.mycompany.entities.CategorieRegime;
import com.mycompany.services.serviceCategorieRegime;

/**
 *
 * @author Kouki
 */
public class updateCatRegimeForm extends BaseForm{
    Form current;
    public updateCatRegimeForm(Resources res,CategorieRegime categorie) {
          super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Modifier Categorie");
        
          getContentPane().setScrollVisible(false);
          
           super.addSideMenu(res);
           
           TextField libelle = new TextField(categorie.getLibelle(),"Libelle",20,TextField.ANY);
           TextField description = new TextField(categorie.getDescription(),"Description",20,TextField.ANY);
           TextField statcolor = new TextField(categorie.getStatcolor(),"StatColor",20,TextField.ANY);
    
           libelle.setUIID("NewsTopLine");
           description.setUIID("NewsTopLine");
           statcolor.setUIID("NewsTopLine");
           
           libelle.setSingleLineTextArea(true);
           description.setSingleLineTextArea(true);
           statcolor.setSingleLineTextArea(true);
           
           Button btnModifier = new Button("Modifier");
           btnModifier.setUIID("Button");
           
           //on click enevt
           
           btnModifier.addPointerPressedListener((e) -> {
               categorie.setLibelle(libelle.getText());
               categorie.setDescription(description.getText());
               categorie.setStatcolor(statcolor.getText());
               
        
           
           //appel au service fun update cat
           
           if(serviceCategorieRegime.getInstance().updateCategorie(categorie)){
               new ListCatR(res).show();
           }
              });
           
           Button btnAnnuler = new Button("Annuler");
           btnAnnuler.addActionListener((e) -> {
               new ListCatR(res).show();
           });
           
           Label l2 = new Label("");
           Label l3 = new Label("");
           Label l4 = new Label("");
           Label l5 = new Label("");
           Label l1 = new Label("");
           
           Container content = BoxLayout.encloseY(
                   
                  l1, l2, 
                   new FloatingHint(libelle),
                   createLineSeparator(),
                    new FloatingHint(description),
                   createLineSeparator(),
                    new FloatingHint(statcolor),
                   createLineSeparator(),
                   l4,l5,
                   btnModifier,
                   btnAnnuler
           
           
           );
           add(content);
           show();
    
    }    
    
    
}
