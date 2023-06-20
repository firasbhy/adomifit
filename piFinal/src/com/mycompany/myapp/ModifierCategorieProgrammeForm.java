/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieProgramme;
import com.mycompany.services.serviceCategorieProgramme;
import com.mycompany.utils.Statics;
import java.io.IOException;

/**
 *
 * @author MSI
 */
public class ModifierCategorieProgrammeForm extends BaseForm {
    
    
         Form current;

    public ModifierCategorieProgrammeForm(Resources res,CategorieProgramme cat){
          super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Modifier Produit");
        
          getContentPane().setScrollVisible(false);
          
           super.addSideMenu(res);
           
           TextField nom = new TextField(cat.getLibelle(),"Libelle",20,TextField.ANY);
           TextField description = new TextField(cat.getDescription(),"Description",20,TextField.ANY);
           
           TextField upimage = new TextField("", "entrer Image !");
          Button btnUpload = new Button("Upload");
             btnUpload.addActionListener((e) -> {
                 if(!"".equals(upimage.getText())){
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
                     cr.setFilename("file", upimage.getText() + ".jpg");   
                    
                     InfiniteProgress prog = new InfiniteProgress();
                     Dialog dlg = prog.showInifiniteBlocking();
                     cr.setDisposeOnCompletion(dlg);
                     NetworkManager.getInstance().addToQueueAndWait(cr);
                    
                     Dialog.show("success", "Image uploaded", "OK",null);
                 }else{
                  Dialog.show("Error", "Invalid uploaded name", "OK",null);
                 }
             });
           
              addStringValue("Image",upimage);
              addStringValue("",btnUpload);
            
          
     
          
    
           nom.setUIID("NewsTopLine");
           description.setUIID("NewsTopLine");
           upimage.setUIID("NewsTopLine");
           
           
           
           nom.setSingleLineTextArea(true);
           description.setSingleLineTextArea(true);
            upimage.setSingleLineTextArea(true);

           
           
           Button btnModif = new Button("Modifier");
           btnModif.setUIID("Button");
           
           //on click enevt
           
           btnModif.addPointerPressedListener((e) -> {
               cat.setLibelle(nom.getText());
               cat.setDescription(description.getText());
               cat.setImage(upimage.getText().toString()+ ".jpg");
               

               
               
               
        
           
           //appel au service fun update cat
           
           if(serviceCategorieProgramme.getInstance().updateProduit(cat)){
               new ListCategorieProgramme(res).show();
           }
              });
           Button btnAnnuler = new Button("Annuler");
           btnAnnuler.addActionListener((e) -> {
               new ListCategorieProgramme(res).show();
           });
           
           
           Label l2 = new Label("");
           Label l3 = new Label("");
           Label l4 = new Label("");
           Label l5 = new Label("");
           Label l1 = new Label("");
           
           Container content = BoxLayout.encloseY(
                   
                  l1, l2, 
                   new FloatingHint(nom),
                   createLineSeparator(),
                    new FloatingHint(description),
                   createLineSeparator(),
                   l4,l5,
                   btnModif,
                   btnAnnuler
           
           
           );
           add(content);
           show();
           
    
    } 
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}}
