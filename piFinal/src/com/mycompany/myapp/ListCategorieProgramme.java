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
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.mycompany.entities.CategorieProgramme;
import com.mycompany.services.serviceCategorieProgramme;
import com.mycompany.utils.Statics;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Programme;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ListCategorieProgramme extends BaseForm {
    
     Form current;
     
     EncodedImage enc;
      Image imgs;
      ImageViewer imgv;
      
     
     
     
     
    public ListCategorieProgramme(Resources res) {
        super("NewsFeed",BoxLayout.y()); //heritage du formulaire 
        //searchcode
       
          
          
          Toolbar tb = new Toolbar(true);
          
          current = this;
          setToolBar(tb);
          getTitleArea().setUIID("Container");
          setTitle("Liste des  Categories");
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
        RadioButton mesListes = RadioButton.createToggle("Liste Categorie", barGroup);
        mesListes.setUIID("SelectBar");
     
        RadioButton partage = RadioButton.createToggle("Ajouter Categorie", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
         RadioButton programme = RadioButton.createToggle("Ajouter Programme", barGroup);
        programme.setUIID("SelectBar");

        mesListes.addActionListener(e-> new ListCategorieProgramme(res).show());
        partage.addActionListener(e-> new AjoutCategorieProgrammeForm(res).show());
        programme.addActionListener(e-> new AjoutProgrammeForm(res).show());


        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, partage,programme),
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
        ArrayList<CategorieProgramme> List = serviceCategorieProgramme.getInstance().getAllProduit();
        for (CategorieProgramme cat : List){
                addButton(cat,res);
       
                
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

    private void addButton( CategorieProgramme cat, Resources res) {
        

      
         
          ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("afficher", barGroup);
        mesListes.setUIID("SelectBar");
       



       TextArea ta = new TextArea(cat.getLibelle());
       TextArea tb = new TextArea(cat.getDescription());
       
       try {
            enc = EncodedImage.create("/load.jpg");
        } catch (Exception e) {
        }
        imgs = URLImage.createToStorage(enc, Statics.URL_REP_IMAGES+"/"+cat.getImage(), Statics.URL_REP_IMAGES+"/"+cat.getImage(),URLImage.RESIZE_SCALE);
             imgv = new ImageViewer(imgs);
         
    
       ta.setUIID("NewTopLine2");
       tb.setUIID("NewTopLine2");
       imgv.setUIID("NewTopLine2");


      

         
    
       ta.setUIID("NewTopLine2");
       tb.setUIID("NewTopLine2");
       
          
        ta.setEditable(false);
        tb.setEditable(false);
        
         //show button2
         
              Label btnaff2 = new Label("SHOW LIST");
           btnaff2.setUIID("NewsBottomLine");
           Style affiche = new Style(btnaff2.getUnselectedStyle());
           affiche.setFgColor(0xf7ad02);
           
           FontImage affiched = FontImage.createMaterial(FontImage.MATERIAL_ADD_TO_HOME_SCREEN, affiche);
           btnaff2.setIcon(affiched);
           btnaff2.setTextPosition(LEFT);
           
           btnaff2.addPointerPressedListener((e) -> {
               new ListProgramme(res,cat,cat.getId()).show();
           
           });
        
        
         //show button
         
              Label btnaff = new Label("AFFICH");
           btnaff.setUIID("NewsBottomLine");
           Style modifStyled = new Style(btnaff.getUnselectedStyle());
           modifStyled.setFgColor(0xf7ad02);
           
           FontImage modifImaged = FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, modifStyled);
           btnaff.setIcon(modifImaged);
           btnaff.setTextPosition(LEFT);
           
           btnaff.addPointerPressedListener((e) -> {
               //new ListProgramme(res,cat,cat.getId()).show();

               new StatistiquePieForm(res,cat).show();
           });
           
           
           
         
           
       
          
        
        //modif button
             
              Label btnModif = new Label("MODIF");
           btnModif.setUIID("NewsBottomLine");
           Style modifStyle = new Style(btnModif.getUnselectedStyle());
           modifStyle.setFgColor(0xf7ad02);
           
           FontImage modifImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifStyle);
           btnModif.setIcon(modifImage);
           btnModif.setTextPosition(LEFT);
           
           btnModif.addPointerPressedListener((e) -> {
               new ModifierCategorieProgrammeForm(res,cat).show();
           });
           //dislike button
         Label btndislike = new Label(" ");
           btndislike.setUIID("NewsBottomLine");
           Style suppStylesx = new Style(btndislike.getUnselectedStyle());
           suppStylesx.setFgColor(0xf21f1f);
           
           FontImage likeImages = FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, suppStylesx);
           btndislike.setIcon(likeImages);
           btndislike.setTextPosition(RIGHT);
    
           btndislike.addPointerPressedListener((e) -> {
               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Dislike :( "," vous  n'aimez pas ce programme ?" , "non" , "OUI")){
                   dig.dispose();
               }else{
                   dig.dispose();
                   if(serviceCategorieProgramme.getInstance().jaimepas(cat)){
                       new ListCategorieProgramme(res).show();
                   }
                    }
               
           });
           
        //like button
         Label btnlike = new Label(" ");
           btnlike.setUIID("NewsBottomLine");
           Style suppStyles = new Style(btnlike.getUnselectedStyle());
           suppStyles.setFgColor(0x96db06);
           
           FontImage likeImage = FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, suppStyles);
           btnlike.setIcon(likeImage);
           btnlike.setTextPosition(LEFT);
         
    
           btnlike.addPointerPressedListener((e) -> {
               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Like !","avez vous vraiment aimez ce programme ?" , "non" , "OUI")){
                   dig.dispose();
               }else{
                   dig.dispose();
                   if(serviceCategorieProgramme.getInstance().jaime(cat)){
                      // cat.setJaime(cat.getJaime()+1);
                       new ListCategorieProgramme(res).show();
                   }
                    }
               
           });
     
           //supp button
           Label btnSupp = new Label("DELETE");
           btnSupp.setUIID("NewsBottomLine");
           Style suppStyle = new Style(btnSupp.getUnselectedStyle());
           suppStyle.setFgColor(0xf21f1f);
           
           FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
           btnSupp.setIcon(suppImage);
           btnSupp.setTextPosition(LEFT);
    
           btnSupp.addPointerPressedListener((e) -> {
               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Suppression Categorie !","Voulez vous vraiment supprimer cette categorie ?" , "Annuler" , "OK")){
                   dig.dispose();
               }else{
                   dig.dispose();
                   if(serviceCategorieProgramme.getInstance().deleteProduit(cat.getId())){
                       new ListCategorieProgramme(res).show();
                   }
                    }
               
           });
       
       addAll(ta,tb,imgv,btnaff2,btnlike,btndislike,btnSupp,btnModif,btnaff);

    }

   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
