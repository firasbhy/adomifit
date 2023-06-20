/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUtilisateur;
import com.mycompany.services.serviceProduit;

import java.io.IOException;
import java.util.List;


/**
 *
 * @author ASUS
 */
public class ListUtilForm extends BaseForm {
        Form current ; 
       
    public ListUtilForm(Form previous,Resources res) {
        current = this ; 
        setTitle(" List des Utilisateur") ;
        Component[] result = new Component[50];
        ServiceUtilisateur sp = new ServiceUtilisateur();
         Toolbar.setGlobalToolbar(true);
         super.addSideMenu(res);
        add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                        List<User> listerec = sp.getallusers();
                        for(User p : listerec)
                        {
                           
                            
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Nom:"+p.getNom());
                            m.setTextLine2("email:"+p.getEmail());
                            m.setTextLine3("role:"+p.getRole());
                           
                            add(m);
                            
//                             m.addPointerReleasedListener(new ActionListener() {
//                                    @Override
//                                    public void actionPerformed(ActionEvent evt) {
//                                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
//                                            
//                                               
//                                        }
//                                    
//                                        else{ 
//                                                 
//                                               new EditUtiForml(current,p).show() ;   
//                                        }
//                                        Dialog dig = new Dialog("Suppression");
//               
//               if(dig.show("Suppression user !","Voulez vous vraiment supprimer cette user ?" , "Annuler" , "OK")){
//                   dig.dispose();
//               }else{
//                   dig.dispose();
//                           sp.supputil(p.getId());
//                           new ListUtilForm(previous,res).show();
//                             
//                    
//                   
//                    }
//                                    }
//                                    
//                                });
            Label btnupdate = new Label(" ");
           btnupdate.setUIID("NewsBottomLine");
           Style updateStyle = new Style(btnupdate.getUnselectedStyle());
           updateStyle.setFgColor(0xf21f1f);
           
           FontImage updateImage = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, updateStyle);
           btnupdate.setIcon(updateImage);
           btnupdate.setTextPosition(RIGHT);
    
           m.addPointerPressedListener((e) -> {
               Dialog dig = new Dialog("update");
               
               if(dig.show("update user !","Voulez vous vraiment modifeir cette user ?" , "Annuler" , "OK")){
                   dig.dispose();
               }else{
                  new EditUtiForml(res,current,p).show() ;   
                             
                    
                   
                    }
               
           });
                             Label btnSupp = new Label(" ");
           btnSupp.setUIID("NewsBottomLine");
           Style suppStyle = new Style(btnSupp.getUnselectedStyle());
           suppStyle.setFgColor(0xf21f1f);
           
           FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
           btnSupp.setIcon(suppImage);
           btnSupp.setTextPosition(RIGHT);
    
           m.addPointerPressedListener((e) -> {
               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Suppression user !","Voulez vous vraiment supprimer cette user ?" , "Annuler" , "OK")){
                   dig.dispose();
               }else{
                   dig.dispose();
                           sp.supputil(p.getId());
                           new ListUtilForm(previous,res).show();
                             
                    
                   
                    }
               
           });
       
         
        
    }
                     revalidate() ;    
});
                    
                    });
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
        
    }
    
     
        
    
    
}
