/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.mycompany.entities.Programme;

import com.mycompany.services.serviceCategorieProgramme;
import com.mycompany.services.serviceProgramme;
import com.mycompany.utils.Statics;

import java.io.IOException;



/**
 *
 * @author sonicmaster
 */
public class AjoutProgrammeForm extends BaseForm{
    Form current;
    public AjoutProgrammeForm(Resources res){
          super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Ajout Produit");
        
          getContentPane().setScrollVisible(false);
            
           super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
          tb.addSearchCommand((e) -> {
              
          });
          
          Tabs swipe = new Tabs();
          
          Label s1 = new Label();
           Label s2 = new Label();
            Label s3 = new Label();
          
               addTab(swipe ,s1, res.getImage("logoa.png"),"","",res);
            
               
               swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2 , s3);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Liste Categories", barGroup);
        mesListes.setUIID("SelectBar");
     
        RadioButton partage = RadioButton.createToggle("Ajouter Categorie", barGroup);
        partage.setUIID("SelectBar");
        
         RadioButton programme = RadioButton.createToggle("Ajouter Programme", barGroup);
        programme.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener(e-> new ListCategorieProgramme(res).show());
        partage.addActionListener(e-> new AjoutCategorieProgrammeForm(res).show());
       programme.addActionListener(e-> new AjoutCategorieProgrammeForm(res).show());

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3,mesListes,partage,programme),
                FlowLayout.encloseBottom(arrow)
        ));
        
//pointie sur le button on question

        programme.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(programme, arrow);
        });
        
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(partage, arrow);
        bindButtonSelection(programme, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        //************************endTemplate******************************************\\
        
        
        
         TextField titre = new TextField("", "entrer Titre !");
          titre.setUIID("TextFieldBlack");
          addStringValue("Titre",titre);
          
          TextField description = new TextField("", "entrer Description !");
          description.setUIID("TextFieldBlack");
          addStringValue("Description",description);
          
          TextField categ = new TextField("", "ID de Categorie !");
          categ.setUIID("TextFieldBlack");
          addStringValue("Categorie",categ);
          
          
          
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
          
          
          
          
          Button btnAjouter = new Button("Ajouter");
          addStringValue("",btnAjouter);
          
                
          
          btnAjouter.addActionListener((e) -> {
              try {
                  
                  
                  if(titre.getText().equals(" ")||categ.getText().equals(" ")||isNumeric(categ.getText())||description.getText().equals(" ") ){
                      Dialog.show("Vérifier les données ! ","","Annuler","OK");
                  }else{
                      InfiniteProgress ip = new InfiniteProgress(); //refresh apres linsert
                      final Dialog iDialog = ip.showInfiniteBlocking();
                      
            Programme cat = new Programme(Integer. parseInt(categ.getText()),0,0,0,String.valueOf(titre.getText().toString()),
                    String.valueOf(description.getText().toString()),String.valueOf(upimage.getText().toString()+ ".jpg") 
                                    );
                           
            
                      System.out.println("data produit =="+cat);
                      
                      
                      //appel service 
                      serviceProgramme.getInstance().addProduit(cat);
                     
                          
                    
                          
                         Dialog.show("success", "Programme Ajouté ! ", new Command("ok"));
                      iDialog.dispose(); //close loading
                        new ListCategorieProgramme(res).show();
                      
                      refreshTheme();//Actualiser
                   
                      
                     
                  }
              } catch (Exception ex) {
                  ex.printStackTrace();
                  
              }
              
          });
          
        
        
        
        
        
        
        
        
    }   
     //************************endForm******************************************\\
private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
             int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size){
            image = image.scaledHeight(size); 
        }
        if(image.getHeight() > Display.getInstance().getDisplayHeight()/2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight()/2); 
        }
        
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
         imageScale.setUIID("Container");
         imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
         
         Label overLay = new Label("","ImageOverlay");
         
          Container page1 =
                   LayeredLayout.encloseIn(
                           imageScale,
                                    overLay,
                                    BorderLayout.south(
                                    BoxLayout.encloseY(
                                    new SpanLabel(text, "LargeWhiteText"),
                                            spacer
                                            )));
         swipe.addTab("", res.getImage("back-logo.png"),page1);
    }
    
    
       public void bindButtonSelection(Button btn , Label l){
    btn.addActionListener((e) -> {
        if(btn.isSelected()){
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
       l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
       l.getParent().repaint();
    }
    public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return true;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return true;
    }
    return false;
}
    
    
}