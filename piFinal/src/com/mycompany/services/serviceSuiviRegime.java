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
import com.mycompany.entities.CategorieRegime;

import com.mycompany.entities.Regime;
import com.mycompany.entities.SuiviRegime;
import static com.mycompany.services.serviceCategorieRegime.resultOK;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Kouki
 */
public class serviceSuiviRegime  {
     public static boolean resultOK=true;
    public ArrayList<SuiviRegime> suiviRegime;
    public static serviceSuiviRegime  instance=null;
     private ConnectionRequest req;

    public serviceSuiviRegime() {
         req = new ConnectionRequest();
    }
    public static serviceSuiviRegime  getInstance() {
          if (instance == null) {
            instance = new serviceSuiviRegime();
        }
        return instance;
         
    }
    
    
    
     public ArrayList<SuiviRegime> parseServiceSuiviRegime(String jsonText){
       
        try {
              suiviRegime=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
             Map<String,Object> suivisListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)suivisListJson.get("root");
              for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                SuiviRegime ct = new SuiviRegime();
                float id = Float.parseFloat(obj.get("id").toString());
                ct.setId((int)id);
                
                float user_id = Float.parseFloat(obj.get("user_id").toString());
                ct.setIduser((int)user_id);
                
                float regime_id = Float.parseFloat(obj.get("regime_id").toString());
                ct.setIdregime((int)regime_id);
               
                 ct.setTitre(obj.get("titre").toString());
                  
                float note = Float.parseFloat(obj.get("note").toString());
                ct.setNote((int)note);
                
                ct.setRemarque(obj.get("remarque").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                suiviRegime.add(ct);
            }
      
        } catch (IOException ex) {
          
        }
        
        return suiviRegime;
    
    }
     
     
     
     
      public void addSuivisRegime(SuiviRegime suivisRegime) {
    
     
       String url = Statics.BASE_URL + "/ajoutSuiviRegimeDirect?idUser=" + suivisRegime.getIduser()+ "&idRegime=" + suivisRegime.getIdregime();
       req.setUrl(url);
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
      
      
       public ArrayList<SuiviRegime> getAllSuivisRegime(){
        String url = Statics.BASE_URL+"/AllSuivis";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                suiviRegime = parseServiceSuiviRegime(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return suiviRegime;
    }
     
       
       
       
         public SuiviRegime DetailSuivisRegime( int id) {
        
        String url = Statics.BASE_URL+"/detailSuivi?id="+id;
        req.setUrl(url);
        req.setPost(false);
       
         SuiviRegime suivisRegime = new SuiviRegime();
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            
            try {
               String str  = new String(req.getResponseData());
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
               
                suivisRegime.setId(id);
                
                float idRegime = Float.parseFloat(obj.get("idRegime").toString());
                suivisRegime.setIdregime((int)idRegime);
                
                float idUser = Float.parseFloat(obj.get("idUser").toString());
                suivisRegime.setIduser((int)idUser);
                
                float note = Float.parseFloat(obj.get("note").toString());
                suivisRegime.setNote((int)note);
                
                suivisRegime.setTitre(obj.get("titre").toString());
                suivisRegime.setRemarque(obj.get("remarque").toString());
                
                
                
            }
            catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return suivisRegime;
        
        
    }
         
         
         
         
         public SuiviRegime detailSuiviUser( int idUser) {
        
        String url = Statics.BASE_URL+"/detailSuiviUser?idUser="+idUser;
        req.setUrl(url);
        req.setPost(false);
       
         SuiviRegime suivisRegime = new SuiviRegime();
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();         
            try {
              String str  = new String(req.getResponseData());
            
                         Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                           

                         
                    float id = Float.parseFloat(obj.get("id").toString());
                    suivisRegime.setId((int) id);
                    
                    float idRegime = Float.parseFloat(obj.get("regime_id").toString());
                    suivisRegime.setIdregime((int)idRegime);
                    
                    
                    suivisRegime.setIduser((int)idUser);
                    
                    float note = Float.parseFloat(obj.get("note").toString());
                    suivisRegime.setNote((int)note);
                    
                    suivisRegime.setTitre(obj.get("titre").toString());
                    suivisRegime.setRemarque(obj.get("remarque").toString());          
               
            } catch (Exception e) {
                System.out.println("Suivi est null ");
              
               
            }
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return suivisRegime;
        
        
    }
         
         //liste suivis d'un régime
          public ArrayList<SuiviRegime> getListSuivRégime(int id_regime){
        String url = Statics.BASE_URL+"/ListeSuivs?idRegime="+id_regime;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                suiviRegime = parseServiceSuiviRegime(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return suiviRegime;
    }
     
    
      
          
          
          ///updateSuiv?id=44&note=14&titre=modifjs&remarque=nsdnsnsn
          
             public boolean updateSuivi(SuiviRegime suiv){
         String url = Statics.BASE_URL + "/updateSuiv?id=" + suiv.getId()+ "&note=" + suiv.getNote()+ "&remarque=" + suiv.getRemarque()+ "&titre="+suiv.getRemarque();
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
