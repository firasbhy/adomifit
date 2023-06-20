/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CategorieEvenement;
import com.mycompany.services.ServiceCatEvenement;
import java.util.ArrayList;

/**
 *
 * @author myria
 */
public class ListEForm extends BaseForm  {
    Form current; 
     public ListEForm(Resources res){
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolBar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Catégorie");
        getContentPane().setScrollVisible(false);
         
        tb.addSearchCommand(e->{
            
        });
        
        Tabs swipe = new Tabs();
        Label s1 =  new Label();
        Label s2 =  new Label();
         Label s3 =  new Label();
        
        addTab(swipe,s1, res.getImage("logoa.png"),"","",res);
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

        Component.setSameSize(radioContainer, s1, s2,s3 );
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Liste Catégories", barGroup);
        mesListes.setUIID("SelectBar");
     
        RadioButton partage = RadioButton.createToggle("Ajouter Catégorie", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e)-> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDIg = ip.showInifiniteBlocking();
            refreshTheme();
        });
       

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);

        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
         ArrayList<CategorieEvenement>list = ServiceCatEvenement.getInstance().affichageCatEvenement();
       for(CategorieEvenement cat : list){
            
            String  urlImage="log.png";
            Image placeHolder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToFileSystem(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            
           addButton(urlim,cat,res);
           
           ScaleImageLabel image = new ScaleImageLabel(urlim);
           Container containerImg = new Container();
           image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                 
          
       }       


    }
     
     
     
     private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() <  size){
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image); 
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("","ImageOverlay");
        Container page1 = 
                LayeredLayout.encloseIn(imageScale,
                            overLay,
                            BorderLayout.south(BoxLayout.encloseY(
                            new SpanLabel(text,"LargeWhiteText"),
                                        
                                        spacer
                                        
                                )
                        )
                );
        swipe.addTab("",res.getImage("logoa.png"), page1);
        
        
        
         }
    
    public void bindButtonSelection(Button btn , Label l){
        btn.addActionListener(e->{
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
            
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX()+ btn.getWidth()/2 -l.getWidth() /2);
        l.getParent().repaint();
        }

    private void addButton(Image img, CategorieEvenement cat, Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button imge = new Button (img.fill(width, height));
        imge.setUIID("Label");
        Container cnt = BorderLayout.west(imge);
        
        
        Label libelleTxt = new Label("Libelle : "+cat.getLibelle(),"NewTopLine2");
        Label descriptionTxt = new Label("Description : "+cat.getDescription(),"NewTopLine2");
        createLineSeparator();
        //TextArea ta = new TextArea(libelle);
        //TextArea td = new TextArea(description);
       //ta.setUIID("NewTopLine2");
       //td.setUIID("NewTopLine2");
        //ta.setEditable(false);
        //ta.setEditable(false);
         
       
       Label lSupprimer = new Label (" ");
       lSupprimer.setUIID("NewTopLine");
       Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
       supprimerStyle.setFgColor(0xf21f1f);
       
       
       FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
       lSupprimer.setIcon(supprimerImage);
       lSupprimer.setTextPosition(RIGHT);
       
       //
       lSupprimer.addPointerPressedListener(l->{
           Dialog dig = new Dialog("Supprimer");
           if(dig.show("Supression","Vous voulez supprimer cette catégorie?","Annuler","Oui")){
               dig.dispose();
           }else{
               dig.dispose();
               if(ServiceCatEvenement.getInstance().deleteCatEvenement(cat.getId())){
                   new ListEForm(res).show();
               }
           }
       });
       
       
       Label lModifier = new Label (" ");
       lModifier.setUIID("NewTopLine");
       Style modifierStyle = new Style(lModifier.getUnselectedStyle());
       supprimerStyle.setFgColor(0xf7ad02);
       
       FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
       lModifier.setIcon(mFontImage);
       lModifier.setTextPosition(LEFT);
       
       lModifier.addPointerPressedListener(l -> {
           //System.out.println("Modification Categorie");
           new UpdateCatEvenementForm(res,cat).show();
       });
        Label lAjouter = new Label (" ");
       lAjouter.setUIID("NewTopLine");
       Style ajouterStyle = new Style(lAjouter.getUnselectedStyle());
       ajouterStyle.setFgColor(0xf7ad02);
       
       FontImage aFontImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, ajouterStyle);
       lAjouter.setIcon(mFontImage);
       lAjouter.setTextPosition(LEFT);
       
       lAjouter.addPointerPressedListener(l -> {
           //System.out.println("Modification Categorie");
           new AjoutCatEvenementForm(res).show();
       }); 
       
       cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(libelleTxt),BoxLayout.encloseX(descriptionTxt,lModifier,lSupprimer,lAjouter))); 
       
       add(cnt); 
    }
}

