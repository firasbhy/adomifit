   
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
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kouki
 */
public class serviceCategorieRegime {
    public static boolean resultOK=true;
    public ArrayList<CategorieRegime> CatRegimes;
    public static serviceCategorieRegime instance=null;
     private ConnectionRequest req;

    public serviceCategorieRegime() {
           req = new ConnectionRequest();
    }
     public static serviceCategorieRegime getInstance() {
        if (instance == null) {
            instance = new serviceCategorieRegime();
        }
        return instance;
    }
    
    
    public ArrayList<CategorieRegime> parseCatRegime(String jsonText){
       
        try {
              CatRegimes=new ArrayList<>();
              
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
             Map<String,Object> CatRegimesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)CatRegimesListJson.get("root");
              for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CategorieRegime ct = new CategorieRegime();
                float id = Float.parseFloat(obj.get("id").toString());
                ct.setId((int)id);
                ct.setLibelle(obj.get("libelle").toString());
                ct.setDescription(obj.get("description").toString());
                ct.setDescription(obj.get("description").toString());
                ct.setStatcolor(obj.get("statcolor").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                CatRegimes.add(ct);
            }
      
        } catch (IOException ex) {
          
        }
        
        return CatRegimes;
    
    }
    
    
     public ArrayList<CategorieRegime> getAllCatRegimes(){
        String url = Statics.BASE_URL+"/AllCatRgimes/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CatRegimes = parseCatRegime(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CatRegimes;
    }
    
     
     public void addCategorieRegime(CategorieRegime categorie) {
    
     
       String url = Statics.BASE_URL + "/addCategorieR?libelle=" + categorie.getLibelle() + "&description=" + categorie.getDescription() + "&statcolor="+categorie.getStatcolor();
       req.setUrl(url);
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
     
        
     
     public boolean deleteCategorie(int id){
         String url = Statics.BASE_URL + "/deleteCatR?id=" +id;
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
     
     public boolean updateCategorie(CategorieRegime categorie){
         String url = Statics.BASE_URL + "/updateCategorieR?id=" + categorie.getId()+ "&libelle=" + categorie.getLibelle() + "&description=" + categorie.getDescription() + "&statcolor="+categorie.getStatcolor();
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
     
     
     
     
     
     
     
     
     
      public CategorieRegime DetailCategorie( int id) { 
        String url = Statics.BASE_URL+"/detailCat?id="+id;
        req.setUrl(url);
        req.setPost(false);   
         CategorieRegime categorie = new CategorieRegime();
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();           
            try {
               String str  = new String(req.getResponseData());
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));              
                categorie.setId(id);                      
                categorie.setLibelle(obj.get("libelle").toString());
                categorie.setDescription(obj.get("description").toString());
                categorie.setStatcolor(obj.get("statcolor").toString());
            }
            catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
        }));
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
              return categorie; 
    }
     
      
      
      
       public CategorieRegime DetailCategorie2(int id){
       String url = Statics.BASE_URL+"/detailCat?id="+id;
             req.setUrl(url);
              CategorieRegime categorie = new CategorieRegime();
             req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
                  JSONParser jsonp = new JSONParser();           
            try {
               String str  = new String(req.getResponseData());
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));              
                categorie.setId(id);                      
                categorie.setLibelle(obj.get("libelle").toString());
                categorie.setDescription(obj.get("description").toString());
                categorie.setStatcolor(obj.get("statcolor").toString());
            }
            catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
                 req.removeResponseListener(this);
                 
             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
         return categorie;
     }
     
     
}