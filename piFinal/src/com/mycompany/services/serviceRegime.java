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
public class serviceRegime {
     public static boolean resultOK=true;
    public ArrayList<Regime> regimes;
    public static serviceRegime instance=null;
     private ConnectionRequest req;

    public serviceRegime() {
         req = new ConnectionRequest();
    }
    public static serviceRegime  getInstance() {
          if (instance == null) {
            instance = new serviceRegime();
        }
        return instance;
         
    }
    
    
    
     public ArrayList<Regime> parseRegime(String jsonText){
       
        try {
              regimes=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
             Map<String,Object> regimesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)regimesListJson.get("root");
              for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Regime ct = new Regime();
                float id = Float.parseFloat(obj.get("id").toString());
                ct.setId((int)id);
               
                float prix = Float.parseFloat(obj.get("prix").toString());
                ct.setPrix((float)prix);
                float user_id = Float.parseFloat(obj.get("user_id").toString());
                ct.setIduser((int)user_id);
                float idCat = Float.parseFloat(obj.get("categorie_regime_id").toString());
                ct.setCategorie_regime_id((int)idCat);
                /*
                  CategorieRegime cat = serviceCategorieRegime.getInstance().DetailCategorie((int)idCat);
                  ct.setCategorieRegime(cat);
                 */
                  ct.setType(obj.get("type").toString());
                ct.setDescription(obj.get("description").toString());
                ct.setImage(obj.get("image").toString());
                ct.setDificulte(obj.get("dificulte").toString());
            
                //Ajouter la tâche extraite de la réponse Json à la liste
                regimes.add(ct);
                
            }
      
        } catch (IOException ex) {
          
        }
        
        return regimes;
    
    }
     
     
     
     
     
       public ArrayList<Regime> getAllRegimes(){
        String url = Statics.BASE_URL+"/AllRgimes/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                regimes = parseRegime(new String(req.getResponseData()));
          
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return regimes;
    }
       
        public void addRegime(Regime regime) {
    
     
       String url = Statics.BASE_URL + "/ajoutRegime?type=" + regime.getType()+ "&description=" + regime.getDescription() + "&dificulte="+regime.getDificulte() + "&image=" +regime.getImage() + "&prix="+ regime.getPrix()+"&categorie_regime_id="+regime.getCategorie_regime_id()+"&user="+regime.getIduser();
       req.setUrl(url);
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    
    
    
        
         public boolean deleteRegime(int id){
         String url = Statics.BASE_URL + "/suppRegime?id=" +id;
         req.setUrl(url);
         
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
               req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
     }
     
        
     
     
          public boolean updateRegime(Regime regime){
         String url = Statics.BASE_URL + "/modifRegime?id=" + regime.getId()+ "&type=" + regime.getType()+ "&description=" + regime.getDescription() + "&dificulte="+regime.getDificulte()+ "&image="+regime.getImage()+"&prix="+regime.getPrix()+"&categorie_regime_id="+regime.getCategorie_regime_id();
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


      /*   
     
    public Regime DetailRegime( int id) {
        
        String url = Statics.BASE_URL+"/detailReg?id="+id;
        req.setUrl(url);
        req.setPost(false);
       
         Regime reg = new Regime();
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            
            try {
               String str  = new String(req.getResponseData());
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
               
                reg.setId(id);     
                reg.setType(obj.get("type").toString());
                reg.setDescription(obj.get("description").toString());
                reg.setDificulte(obj.get("dificulte").toString());
                reg.setImage(obj.get("image").toString());
                
             
                
                float prix = Float.parseFloat(obj.get("prix").toString());
                reg.setPrix((float)prix);
                float user_id = Float.parseFloat(obj.get("user_id").toString());
                reg.setIduser((int)user_id);
                float idCat = Float.parseFloat(obj.get("categorie_regime_id").toString());
                reg.setCategorie_regime_id((int)idCat);
                  CategorieRegime cat = serviceCategorieRegime.getInstance().DetailCategorie((int)idCat);
                  reg.setCategorieRegime(cat);
                
              
                
                
            }
            catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return reg;
        
        
    }
    
    
    */
      public ArrayList<Regime> getAllRegimesUser(int id){
        String url = Statics.BASE_URL+"/AllRgimesUser?idUser="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                regimes = parseRegime(new String(req.getResponseData()));
          
                req.removeResponseListener(this);
                  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
       
        return regimes;
    }
      
      
       public ArrayList<Regime> chercherRegime(String q){
        String url = Statics.BASE_URL+"/searchReg?q="+q;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("sqdsqddddddddddddddd data    =  "+new String(req.getResponseData()));
                regimes = parseRegime(new String(req.getResponseData()));
          
                req.removeResponseListener(this);
                  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
       
        return regimes;
    }
    

    
}
