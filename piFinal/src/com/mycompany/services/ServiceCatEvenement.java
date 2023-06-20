/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.CategorieEvenement;
import com.mycompany.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author myria
 */

public class ServiceCatEvenement {
    public static ServiceCatEvenement instance = null;
    public static boolean resultOK = true;
    
    private ConnectionRequest req;
    public static ServiceCatEvenement getInstance(){
        if(instance == null)
            instance = new ServiceCatEvenement();
            return instance;
    }
     public ServiceCatEvenement(){
         req = new ConnectionRequest();
         
         
     }
    

//ajout
     public void ajoutCatEvenement (CategorieEvenement cat){
         String url=Statics.BASE_URL+"/addCatEvenementJSON/new?libelle="+cat.getLibelle()+"&description="+cat.getDescription()+"&image="+cat.getImage();
          req.setUrl(url);
          req.addResponseListener((e)->{
              String str = new String(req.getResponseData());
          });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
     }
     



//affichage
    public ArrayList<CategorieEvenement>affichageCatEvenement(){
        ArrayList<CategorieEvenement> result = new ArrayList<>();
        String url=Statics.BASE_URL+"/AllCatEvenement";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object>mapCatEvenement = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCatEvenement.get("root");
                    for(Map<String,Object> obj : listOfMaps){
                        CategorieEvenement cat = new CategorieEvenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        String libelle = obj.get("libelle").toString();
                         String description = obj.get("description").toString();
                        
                      
                         cat.setId((int)id);
                        
                         cat.setLibelle(libelle);
                         cat.setDescription(description);
                         
                         
                        result.add(cat);
                         
                        
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        
     NetworkManager.getInstance().addToQueueAndWait(req);
     
     return result;
}
    public boolean deleteCatEvenement( int id){
        String url = Statics.BASE_URL+"/deleteCatEvenementJSON/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean modifierCatEvenement(CategorieEvenement cat ){
        String url = Statics.BASE_URL+"/updateCatEvenementJSON/"+cat.getId()+"?libelle="+cat.getLibelle()+"&description="+cat.getDescription();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
                NetworkManager.getInstance().addToQueueAndWait(req);
                return resultOK;

    }
    
    
}
