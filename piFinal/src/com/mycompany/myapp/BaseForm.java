/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    private Form current;

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
      //malek
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());
         tb.addMaterialCommandToSideMenu("list utilisateurs", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListUtilForm(current,res).show());
           tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_EXIT_TO_APP, e -> new profile(current,res).show());
           
           
           tb.addMaterialCommandToSideMenu("Ajouter utilisateur", FontImage.MATERIAL_EXIT_TO_APP, e -> new AddUtilForm(current,res).show());
        //aboudd
        tb.addMaterialCommandToSideMenu("Ajout Produit", FontImage.MATERIAL_SETTINGS, e -> new AjoutProduitForm(res).show());
        tb.addMaterialCommandToSideMenu("Produit", FontImage.MATERIAL_SETTINGS, e -> new ListProduit(res).show());
        
        
        //khalil
         tb.addMaterialCommandToSideMenu("Catégories Programme", FontImage.MATERIAL_SETTINGS, e -> new ListCategorieProgramme(res).show());
   
        //houssem
          tb.addMaterialCommandToSideMenu("Liste Régimes", FontImage.MATERIAL_SETTINGS, e -> new ListRegimesForm(res).show());
        tb.addMaterialCommandToSideMenu("Catégories Régimes", FontImage.MATERIAL_SETTINGS, e -> new ListCatR(res).show());
        tb.addMaterialCommandToSideMenu("Mes Régimes", FontImage.MATERIAL_SETTINGS, e -> new MesRegimes(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
        tb.addMaterialCommandToSideMenu("Statistique ", FontImage.MATERIAL_EXIT_TO_APP, e -> new StatRegime(res).show());
    //mariem 
          tb.addMaterialCommandToSideMenu("Catégorie", FontImage.MATERIAL_SETTINGS, e -> new ListEForm(res).show());
        tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_SETTINGS, e -> new ListEvForm(res).show());
        //firas
         tb.addMaterialCommandToSideMenu("forum", FontImage.MATERIAL_SETTINGS, e -> new ListForumForm(res).show());
         tb.addMaterialCommandToSideMenu("forum", FontImage.MATERIAL_SETTINGS, e -> new ListCommentaireForm(res).show());
    }
    
    
    
    
}
