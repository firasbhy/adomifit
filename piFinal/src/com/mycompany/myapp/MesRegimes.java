/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
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
public class MesRegimes extends BaseForm  {
    Form current;
     EncodedImage enc;
      Image imgs;
      ImageViewer imgv;
    MesRegimes(Resources res) {
         super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
          
          Toolbar tb = new Toolbar(true);
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Liste de Mes régimes ");
         getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
          getContentPane().setScrollVisible(false);
            
          
          tb.addSearchCommand((e) -> {
              
          });
          
          
          Tabs swipe = new Tabs();
          
          Label s1 = new Label();
           Label s2 = new Label();
            Label s3 = new Label();
          
               addTab(swipe ,s1, res.getImage("logoa.png"),"","",res);
          
          
          
           //
            
            
            
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
        RadioButton mesListes = RadioButton.createToggle("Mes Régimes", barGroup);
        mesListes.setUIID("SelectBar");
     
        RadioButton partage = RadioButton.createToggle("Ajouter Régime", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener(e-> new MesRegimes(res).show());
       partage.addActionListener(e-> new AjoutRegimeForm(res).show());

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(mesListes, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
        //appel aafichage methode
     
             //variable session nutt connecté remplace 2
         ArrayList<Regime> ListReg = serviceRegime.getInstance().getAllRegimesUser(71);
        ArrayList<CategorieRegime> catreg = serviceCategorieRegime.getInstance().getAllCatRegimes();
        CategorieRegime c = new CategorieRegime();
        for (Regime reg : ListReg){
            //variable session nutt connecté remplace 2
            
            // CategorieRegime catreg = serviceCategorieRegime.getInstance().DetailCategorie(reg.getCategorie_regime_id());
         for (CategorieRegime cat : catreg){ 
             if(cat.getId() == reg.getCategorie_regime_id()){
                 c = cat;
             }
         }
               addButton(reg,c,res);
           
       
                
        }
      
            
         
            
            //
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

    private void addButton( Regime reg,CategorieRegime categorie ,Resources res   ) {
         
          
       
       TextArea ta = new TextArea(reg.getType());
       TextArea tb = new TextArea(reg.getDescription());
          try {
            enc = EncodedImage.create("/load.jpg");
        } catch (Exception e) {
        }
        imgs = URLImage.createToStorage(enc, Statics.URL_Image+"/"+reg.getImage(), Statics.URL_Image+"/"+reg.getImage(),URLImage.RESIZE_SCALE);
             imgv = new ImageViewer(imgs);
  Label dif = new Label("Dificulte : "+ reg.getDificulte());
  Label prix = new Label("Prix : "+ reg.getPrix());
  
  
      
        
      
    
          
             //reg.getCategorie_regime_id()
   Label catRegId = new Label("Catégorie : "+categorie.getLibelle());
  
        
       
 
  
  createLineSeparator();
  createLineSeparator();
  createLineSeparator();
       ta.setUIID("NewTopLine2");
       tb.setUIID("NewTopLine2");
       dif.setUIID("NewTopLine2");
       prix.setUIID("NewTopLine2");
       catRegId.setUIID("NewTopLine2");
       imgv.setUIID("NewTopLine2");
       
          
        
        ta.setEditable(false);
        tb.setEditable(false);
        
         //supp button
           Label btnSupp = new Label(" ");
           btnSupp.setUIID("NewsBottomLine");
           Style suppStyle = new Style(btnSupp.getUnselectedStyle());
           suppStyle.setFgColor(0xf21f1f);
           
           FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
           btnSupp.setIcon(suppImage);
           btnSupp.setTextPosition(RIGHT);
    
           btnSupp.addPointerPressedListener((e) -> {
               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Suppression Regime !","Voulez vous vraiment supprimer cette regime ?" , "Annuler" , "OK")){
                   dig.dispose();
               }else{
                   dig.dispose();
                   if(serviceRegime.getInstance().deleteRegime(reg.getId())){
                       new MesRegimes(res).show();
                   }
                    }
               
           });
           
           //update icon 
           Label btnUpdate = new Label(" ");
           btnUpdate.setUIID("NewsBottomLine");
           Style updateStyle = new Style(btnUpdate.getUnselectedStyle());
           updateStyle.setFgColor(0xf7ad02);
           
           FontImage upImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, updateStyle);
           btnUpdate.setIcon(upImage);
           btnUpdate.setTextPosition(LEFT);
           
           btnUpdate.addPointerPressedListener((evt) -> {
                
             new updateRegimeForm(res, reg ).show();
           });
           
         
           
             Button btnListeSuivi = new Button("lISTE SUIVI");
             btnListeSuivi.setUIID("Button");
             btnListeSuivi.addActionListener((evt) -> {
                 ArrayList<SuiviRegime> Allsuivi = serviceSuiviRegime.getInstance().getListSuivRégime(reg.getId());
     
        if(Allsuivi.isEmpty()) {
             Dialog.show("Cher Nutritionniste","Vous navez pas encore des suivis pour ce regime", "OK", "Cancel");
         
           
                  
			
		}else{
              new ListSuiviRegime(res , Allsuivi).show();
                    }
                 
                 
                 
               
             });
        
       addAll(ta,tb,dif,prix,catRegId,imgv,btnSupp ,btnUpdate,btnListeSuivi);

    }
    
}
