/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.SuiviRegime;
import com.mycompany.services.serviceSuiviRegime;

/**
 *
 * @author Kouki
 */
public class UpdateSuiviForm extends BaseForm {
 Form current;
    public UpdateSuiviForm(Resources res ,SuiviRegime suiv) {
        super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Modifier Régime");
        
          getContentPane().setScrollVisible(false);
          
           super.addSideMenu(res);
           
           
            TextField note = new TextField(String.valueOf(suiv.getNote()),"Note :",20,TextField.ANY);
            TextField titre = new TextField(suiv.getTitre(),"Titre :",20,TextField.ANY);
            TextField remarque = new TextField(suiv.getRemarque(),"Remarque :",20,TextField.ANY);
            
            note.setUIID("NewsTopLine2");
            titre.setUIID("NewsTopLine2");
            remarque.setUIID("NewsTopLine2");
            
             note.setSingleLineTextArea(true);
           titre.setSingleLineTextArea(true);
           remarque.setSingleLineTextArea(true);
           
           Button btnModifier = new Button("Modifier");
           btnModifier.setUIID("Button");
             btnModifier.addPointerPressedListener((e) -> {
           
               suiv.setRemarque(remarque.getText());
               suiv.setTitre(titre.getText());
               
        suiv.setNote(Integer.parseInt(note.getText()));
           
           //appel au service fun update cat
           
           if(serviceSuiviRegime.getInstance().updateSuivi(suiv)){
               new MesRegimes(res).show();
           }
              });
           
           
           
             Button btnAnnuler = new Button("Annuler");
           btnAnnuler.addActionListener((e) -> {
               new MesRegimes(res).show();
           });
           
           
                  Label l2 = new Label("");
           Label l3 = new Label("");
           Label l4 = new Label("");
           Label l5 = new Label("");
           Label l1 = new Label("");
           Container content = BoxLayout.encloseY(
                   
                  l1, l2, 
                   new FloatingHint(note),
                   createLineSeparator(),
                    new FloatingHint(titre),
                   createLineSeparator(),
                    new FloatingHint(remarque),
                   createLineSeparator(),
                   l4,l5,
                   btnModifier,
                   btnAnnuler
           
           
           );
           add(content);
           show();
           
           
           
    }
    
    
    
}
