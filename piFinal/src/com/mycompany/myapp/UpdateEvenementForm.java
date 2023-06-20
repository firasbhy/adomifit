/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieEvenement;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceCatEvenement;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author myria
 */
public class UpdateEvenementForm extends BaseForm {
    
    Form current;
    public UpdateEvenementForm(Resources res , Evenement ev){
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolBar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Catégorie");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        TextField titre = new TextField(ev.getTitre(),"Titre",20,TextField.ANY);
        TextField description = new TextField(ev.getDescription(),"Description",20,TextField.ANY);
         TextField image = new TextField(ev.getImage(),"Image",20,TextField.ANY);
             Button btnUpload = new Button("Upload");
             btnUpload.addActionListener((e) -> {
                 if(!"".equals(image.getText())){
                     MultipartRequest cr = new MultipartRequest();
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
            cr.setUrl(Statics.URL_UPLOAD);
            cr.setPost(true);
            String mime ="image/jpeg";
                     try {
                         cr.addData("file", filePath, mime); 
                     } catch (IOException ex) {
                         Dialog.show("Error", ex.getMessage(), "ok",null);
                     }
                     cr.setFilename("file", image.getText() + ".jpg");   
                    
                     InfiniteProgress prog = new InfiniteProgress();
                     Dialog dlg = prog.showInifiniteBlocking();
                     cr.setDisposeOnCompletion(dlg);
                     NetworkManager.getInstance().addToQueueAndWait(cr);
                    
                     Dialog.show("success", "Image uploaded", "OK",null);
                 }else{
                  Dialog.show("Error", "Invalid uploaded name", "OK",null);
                 }
             });
           
           
     
            ComboBox SujetCombo = new ComboBox();
          ArrayList<CategorieEvenement> listcat = ServiceCatEvenement.getInstance().affichageCatEvenement();
          for (CategorieEvenement cat : listcat){
               SujetCombo.addItem(cat); 
               
              if(cat.getId() == ev.getCategorie_evenement_id()){
              SujetCombo.setSelectedItem(cat);
              }
            
          }
          
          
      
           TextField cc = new TextField( String.valueOf(ev.getCategorie_evenement_id()) ,"CatId",20,TextField.NUMERIC);
        
        
        
        
        
        titre.setUIID("NewTopLine");
        description.setUIID("NewTopLine");
        image.setUIID("NewTopLine");
        SujetCombo.setUIID("NewTopLine");
        
        titre.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        image.setSingleLineTextArea(true);
        cc.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
        //btnModifier.setUIID("button");
        
        btnModifier.addPointerPressedListener(l ->{
            ev.setTitre(titre.getText());
            ev.setDescription(description.getText());
            
           
            if(ServiceEvenement.getInstance().updateEvenement(ev)){
                new ListEvForm(res).show();
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
            Container content = BoxLayout.encloseY(la,lb,new FloatingHint(titre),createLineSeparator(),new FloatingHint(description),createLineSeparator(),lc,ld,btnModifier,btnAnnuler );
        
add(content);
show();
    }
    
}
