/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieRegime;
import com.mycompany.entities.Regime;
import com.mycompany.entities.SuiviRegime;
import com.mycompany.services.serviceCategorieRegime;
import com.mycompany.services.serviceRegime;
import com.mycompany.services.serviceSuiviRegime;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Kouki
 */
public class ListRegimesForm extends BaseForm {
  EncodedImage enc;
      Image imgs;
      ImageViewer imgv;
    public ListRegimesForm(Resources res) {
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste des régimes");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Liste des regimes", barGroup);
        all.setUIID("SelectBar");
     
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
      
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
          TextField cherche = new TextField("", "Rcherche Regime");
          cherche.setUIID("TextFieldBlack");
          addStringValue("Recherche",cherche);
          
          Button btnChercher = new Button("Chercher");
          addStringValue("",btnChercher);
          
          btnChercher.addActionListener((e) -> {
              try {
                  if(cherche.getText() == ""){
                   Dialog.show("Champ recherche est vide ! ","","Annuler","OK");
                  }else{
                    
                      ArrayList<Regime> ListRecherche = serviceRegime.getInstance().chercherRegime(cherche.getText());
                      if(ListRecherche.size() == 0){ 
                          Dialog.show("Pas des regime  ! ","","Annuler","OK");
                           refreshTheme();
                      }else{
                        new RechercheRegime(res , ListRecherche).show();
                      }
                    
                      
                  }
                  
              } catch (Exception ex) {
                    ex.printStackTrace();
              }
              
          });
        
        
        ArrayList<Regime> List = serviceRegime.getInstance().getAllRegimes();
        ArrayList<CategorieRegime> Cate = serviceCategorieRegime.getInstance().getAllCatRegimes();
        CategorieRegime catereg = new CategorieRegime();
        for (Regime reg : List){
           
              /* ArrayList<CategorieRegime> cat = serviceCategorieRegime.getInstance().getAllCatRegimes();
               for(CategorieRegime cc : cat){
                   if (cc.getId() == reg.getCategorie_regime_id()){
                       catereg = cc;
                   }
               }
               */
         //   catereg = serviceCategorieRegime.getInstance().DetailCategorie(reg.getCategorie_regime_id());   
           
            for(CategorieRegime c : Cate){
                if(c.getId()== reg.getCategorie_regime_id()){     catereg = c ;}
           
            }
         
       addButton(res.getImage("news-item-4.jpg"), reg,catereg.getLibelle() ,res,false, 11, 9);
                
        }
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
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
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
   private void addButton(Image img,Regime reg,String categorie,Resources res,boolean liked, int likeCount, int commentCount) {
     
          
          
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
        try {
            enc = EncodedImage.create("/load.jpg");
        } catch (Exception e) {
        }
         imgs = URLImage.createToStorage(enc, Statics.URL_Image+"/"+reg.getImage(), Statics.URL_Image+"/"+reg.getImage(),URLImage.RESIZE_SCALE);
             imgv = new ImageViewer(imgs);
       Button imgv = new Button(imgs.fill(width, height));
       imgv.setUIID("Label");
       Container cnt = BorderLayout.west(imgv);
       cnt.setLeadComponent(imgv);
       
        
       
       
        Button ach = new Button("acheter");
       ach.setUIID("Button");
       
       
       TextArea ta = new TextArea(reg.getType());
       ta.setUIID("NewsTopLine2");
       ta.setEditable(false);
       
       TextArea td = new TextArea(reg.getDescription());
       td.setUIID("NewsTopLine");
       td.setEditable(false);
       
      
    
       Label tdif = new Label(" Dificulté :   " + reg.getDificulte() , "NewsBottomLine");
       Label tp = new Label(" Prix :   " + reg.getPrix() , "NewsBottomLine");
       
       
       
       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,td,
                       BoxLayout.encloseX(tdif, tp),
                       BoxLayout.encloseX(likes, comments)
               ));
       
       
         
       addAll(cnt,ach);
     imgv.addActionListener(e -> ToastBar.showMessage("Categorie :" + categorie , FontImage.MATERIAL_INFO));
     ach.addActionListener((evt) -> {
          
           
           //variable session
           // SuiviRegime suivi = serviceSuiviRegime.getInstance().detailSuiviUser(4);
     
           ArrayList<SuiviRegime> listsuivi = serviceSuiviRegime.getInstance().getAllSuivisRegime();
           boolean exist = false;
           for(SuiviRegime s : listsuivi){
   
                //variable session remplace client id 4
               if( s.getIduser() == 71 ){
                   System.out.println("vbn,;:"+s.getIduser());
                  exist = true; 
               }            
           }
           
           if(exist == true){
          
                
           Dialog.show("Suivi Déja Existe", "vous avez deja un suivis pour un régime ", "OK", "Cancel");
           new showSuiviRegF().show();
       
           }else{
              
           InfiniteProgress ip = new InfiniteProgress();
           final Dialog iDialog = ip.showInfiniteBlocking();
           SuiviRegime suivisRegime = new SuiviRegime();
           suivisRegime.setIdregime(reg.getId());
           suivisRegime.setIduser(4); // variable session
           serviceSuiviRegime.getInstance().addSuivisRegime(suivisRegime);
           Dialog.show("success", "Suivi Ajouté ! ", new Command("ok"));
           iDialog.dispose(); //close loading
           new ListRegimesForm(res).show();
           }
           
           
           
           /*
           try {
           InfiniteProgress ip = new InfiniteProgress();
           final Dialog iDialog = ip.showInfiniteBlocking();
           SuiviRegime suivisRegime = new SuiviRegime();
           suivisRegime.setIdregime(reg.getId());
           suivisRegime.setIduser(4); // variable session
           serviceSuiviRegime.getInstance().addSuivisRegime(suivisRegime);
           Dialog.show("success", "Suivi Ajouté ! ", new Command("ok"));
           iDialog.dispose(); //close loading
           new ListRegimesForm(res).show();
           refreshTheme();//Actualiser
           } catch (Exception e) {
           System.out.println("suivi ===="+suivi);
           Dialog.show("Suivi Déja Existe", "vous avez deja un suivis pour un régime ", "OK", "Cancel");
           new ListRegimesForm(res).show();
           }*/
           /*
           if(suivi != null){
           InfiniteProgress ip = new InfiniteProgress();
           final Dialog iDialog = ip.showInfiniteBlocking();
           SuiviRegime suivisRegime = new SuiviRegime();
           suivisRegime.setIdregime(reg.getId());
           suivisRegime.setIduser(4); // variable session
           serviceSuiviRegime.getInstance().addSuivisRegime(suivisRegime);
           Dialog.show("success", "Régime Ajouté ! ", new Command("ok"));
           iDialog.dispose(); //close loading
           new ListRegimesForm(res).show();
           refreshTheme();//Actualiser
           }else{
           Dialog.show("Suivi Déja Existe", "vous avez deja un suivis pour un régime ", "OK", "Cancel");
           new ListRegimesForm(res).show(); 
           }
            */
           
           
       
           
       
     });
   
   
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}
