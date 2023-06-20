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
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieRegime;
import com.mycompany.entities.Regime;
import com.mycompany.services.serviceCategorieRegime;
import com.mycompany.services.serviceRegime;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kouki
 */
public class updateRegimeForm extends BaseForm{
    Form current;

    public updateRegimeForm(Resources res , Regime regime) {
        super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Modifier Régime");
        
          getContentPane().setScrollVisible(false);
          
           super.addSideMenu(res);
           
           TextField type = new TextField(regime.getType(),"Type :",20,TextField.ANY);
           TextField description = new TextField(regime.getDescription(),"Description :",20,TextField.ANY);
           TextField dificulte = new TextField(regime.getDificulte(),"Dificulté : ",20,TextField.ANY);
           
           TextField image = new TextField(regime.getImage(),"Image",20,TextField.ANY);
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
           
           Float p = regime.getPrix();
           TextField prix = new TextField( p.toString() ,"Prix",20,TextField.NUMERIC);
           
           
      ArrayList<CategorieRegime> listcat = serviceCategorieRegime.getInstance().getAllCatRegimes();  
            ComboBox SujetCombo = new ComboBox();
          
          for (CategorieRegime cat : listcat){
               SujetCombo.addItem(cat); 
             
              if(cat.getId() == regime.getCategorie_regime_id()){
              SujetCombo.setSelectedItem(cat);
              }
            
          }
          
          
      
           TextField cc = new TextField( String.valueOf(regime.getCategorie_regime_id()) ,"CatId",20,TextField.NUMERIC);
           
            
           
       
           
           
           
           
           type.setUIID("NewsTopLine");
           description.setUIID("NewsTopLine");
           dificulte.setUIID("NewsTopLine");
           image.setUIID("NewsTopLine");
           prix.setUIID("NewsTopLine");
           SujetCombo.setUIID("NewsTopLine");
           SujetCombo.setUIID("NewsTopLine");
           
         
           
           type.setSingleLineTextArea(true);
           description.setSingleLineTextArea(true);
           dificulte.setSingleLineTextArea(true);
           image.setSingleLineTextArea(true);
           prix.setSingleLineTextArea(true);
           cc.setSingleLineTextArea(true);
         
          
           
           Button btnModifier = new Button("Modifier");
           btnModifier.setUIID("Button");
           
           //on click enevt
           
           btnModifier.addPointerPressedListener((e) -> {
               regime.setType(type.getText());
               regime.setDescription(description.getText());
               regime.setDificulte(dificulte.getText());
                regime.setImage(image.getText().toString()+ ".jpg");
               regime.setPrix(Float.parseFloat(prix.getText().toString()));
               
               CategorieRegime c =  (CategorieRegime)SujetCombo.getSelectedItem();
               regime.setCategorie_regime_id(c.getId());
           
               
        
           
           //appel au service fun update reg
           
           if(serviceRegime.getInstance().updateRegime(regime)){
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
                   new FloatingHint(type),
                   createLineSeparator(),
                    new FloatingHint(description),
                   createLineSeparator(),
                    new FloatingHint(dificulte),
                   createLineSeparator(),
                   new FloatingHint(image),
                   createLineSeparator(),
                   btnUpload,
                   createLineSeparator(),
                   new FloatingHint(prix),
                   createLineSeparator(),
                    
                 
             
                   l4,l5,
                    SujetCombo,
                   createLineSeparator(),
                   btnModifier,
                   btnAnnuler
           
           
           );
           add(content);
           show();
    
           
    }
    
    
    
}
