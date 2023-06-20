/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author Asus
 */
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commentaire;
import com.mycompany.services.ServiceCommentaire;

import java.util.ArrayList;

/**
 *
 * @author Mezen Bayounes
 */
public class ListCommentaireForm extends BaseForm {
    
     Form current;
    public ListCommentaireForm (Resources res) {
    
     super ("Newsfeed",BoxLayout.y());
    Toolbar tb = new Toolbar(true);
    current = this;
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    //setTitle("choissisez une competition");
    getContentPane().setScrollVisible(false);
    
    
    tb.addSearchCommand(e ->{
    
    });
    
    Tabs swipe = new Tabs();
    
    Label s1 = new Label();
    Label s2 = new Label();
    addTab(swipe,s1,res.getImage("dog.jpg"),"","",res);
    
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
        RadioButton Competition = RadioButton.createToggle("Commentaire", barGroup);
        Competition.setUIID("SelectBar");
        RadioButton pub = RadioButton.createToggle("", barGroup);
        pub.setUIID("SelectBar");
        RadioButton ajout = RadioButton.createToggle("Ajouter ", barGroup);
       ajout.setUIID("SelectBar");
    
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        ajout.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
        new AjouterCommentaireForm(res).show();
     
        refreshTheme();
        
    });
        
        
        
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3,Competition,pub, ajout),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Competition.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Competition, arrow);
        });
        bindButtonSelection(Competition, arrow);
        bindButtonSelection(pub, arrow);
        bindButtonSelection(ajout, arrow);
       
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
    // appel affichage 
    ArrayList<Commentaire> list = ServiceCommentaire.getInstance().affichageCommentaire();
    
    for(Commentaire p : list ) {
        
        
    String urlImage= ("dog.jpg");
    Image PlaceHolder= Image.createImage(120,90);
    EncodedImage enc=EncodedImage.createFromImage(PlaceHolder, false);
    URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
            
            
    addButton(urlim,p,res);
    
    ScaleImageLabel image = new ScaleImageLabel(urlim);
    Container containerImg = new Container();
    image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    
    
    }
    
    }
    
    
     private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size =Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        
        if (image.getHeight()< size ) {
        
        image=image.scaledHeight(size);
        
        }
        
        
        if( image.getHeight()> Display.getInstance().getDisplayHeight() / 2 ) {
        
        image =image.scaledHeight(Display.getInstance().getDisplayHeight() / 2 );
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label ("","ImageOverLay");
        
        Container page1 =
                LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                         new SpanLabel(text,"LargeWhiteText"),
                    
                        
                                spacer
                                )
                        )
                );
                        
                  swipe.addTab("",res.getImage("dog.jpg"),page1 )    ;  
    }
    
    public void bindButtonSelection(Button btn,Label l){
    
    
    btn.addActionListener(e ->  {
    
        if(btn.isSelected()) {
        
        updateArrowPosition(btn,l);
        
        }
    
    
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT,btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2  );
        l.getParent().repaint();
        
        
        
    }

    private void addButton(Image img,Commentaire p,Resources res) {
        int height =Display.getInstance().convertToPixels(11.5f);
        
        int width =Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
     Label nomText = new Label ("Titre: "+p.getType(),"NewsTopLine2");
      Label descriptionText = new Label ("Contenu: "+p.getContenu(),"NewsTopLine2");
       //Label prixText = new Label ("Prix:"+p.getPrix(),"NewsTopLine2");
       //Label dateText = new Label ("date:"+p.getDate(),"NewsTopLine2");

        createLineSeparator();
 
        
       
        
             // supprimer button 
             
             Label lSupprimer= new Label(" ");
             lSupprimer.setUIID("NewsTopLine2");
             Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
             supprimerStyle.setFgColor(0xf21f1f);
             
             FontImage supprimerImage =FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
             lSupprimer.setIcon(supprimerImage);
             lSupprimer.setTextPosition(RIGHT);
             
             
             // appel service
             lSupprimer.addPointerPressedListener(l -> {
             
             Dialog dig = new Dialog("suppression ");
             if (dig.show("suppression","vouslez-vous supprimer cette Competition?","Annuler","oui")){
             
             dig.dispose();
              ServiceCommentaire.getInstance().deleteCommentaire(p.getId()); 
                     new ListCommentaireForm(res).show();
             }
             else {
                       dig.dispose();
                     ServiceCommentaire.getInstance().deleteCommentaire(p.getId()); 
                     new ListCommentaireForm(res).show();
                    
                     
                     
                     
                     }
                     
             
             
             
             
             });
             
             // update 
             
             
                Label lModifier= new Label(" ");
           lModifier.setUIID("NewsTopLine2");
             Style modifierStyle = new Style(lModifier.getUnselectedStyle());
             modifierStyle.setFgColor(0xf7ad02);
             
             FontImage mFontImage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
                 lModifier.setIcon(mFontImage);
             lModifier.setTextPosition(LEFT);
             
                   // appel service modifier 
             lModifier.addPointerPressedListener(l -> {
             
             System.out.println("modifier");
            new ModifierCommentaireForm(res,p).show();
             
             
             
             });
             
             
             
              cnt.add(BorderLayout.CENTER,BoxLayout.encloseY
        (BoxLayout.encloseX(nomText,lModifier,lSupprimer),
                BoxLayout.encloseX(descriptionText)
              

        ));
                     
        add(cnt);
    }
    
    
    
}