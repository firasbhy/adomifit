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
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class serviceProduit {
    
    public static boolean resultOK=true;
    public ArrayList<Produit> Produit;
    public static serviceProduit instance=null;
     private ConnectionRequest req;
     
     public serviceProduit() {
           req = new ConnectionRequest();
    }
     
     public static serviceProduit getInstance() {
        if (instance == null) {
            instance = new serviceProduit();
        }
        return instance;
    }
     public ArrayList<Produit> parseProduit(String jsonText){
       
        try {
              Produit=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
           
             Map<String,Object> ProduitJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
              
              
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitJson.get("root");
            
              for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Produit ct = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                ct.setId((int)id);
                ct.setNom(obj.get("nom").toString());
                ct.setDescription(obj.get("description").toString());
               // ct.setDescription(obj.get("description").toString());
                ct.setImage(obj.get("image").toString());
                
             
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                Produit.add(ct);
            }
      
        } catch (IOException ex) {
          
        }
        
        return Produit;
    
    }
     public ArrayList<Produit> getAllProduit(){
        String url = Statics.BASE_URL+"/AllProduit/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produit = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produit;
    }
     
      public void addProduit(Produit categorie) {
    
     
       String url = Statics.BASE_URL + "/addProduitRs?nom=" + categorie.getNom()+ "&description=" + categorie.getDescription()+ "&image=" + categorie.getImage();
       req.setUrl(url);
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
      
      public boolean deleteProduit(int id){
         String url = Statics.BASE_URL + "/deleteProdR?id=" +id;
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
      
      public boolean updateProduit (Produit produit) {
       String url = Statics.BASE_URL + "/updateProdR?id="+produit.getId()+ "&nom=" + produit.getNom()+ "&description=" + produit.getDescription()+ "&image=" + produit.getImage();
         req.setUrl(url);

req.addResponseListener(new ActionListener<NetworkEvent>() {
@Override
public void actionPerformed(NetworkEvent evt) {
        resultOK = req.getResponseCode() == 200 ; 
        req.removeResponseListener(this);
}
});
NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
}
      
      
      
      
      public boolean jaime(Produit categorie) {
       String url = "http://127.0.0.1:8000/likeevent/"+categorie.getId();
         req.setUrl(url);

req.addResponseListener(new ActionListener<NetworkEvent>() {
@Override
public void actionPerformed(NetworkEvent evt) {
        resultOK = req.getResponseCode() == 200 ; 
        req.removeResponseListener(this);
}
});
NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
}
      
       public boolean jaimepas(Produit categorie) {
       String url = "http://127.0.0.1:8000/dislikeevent/"+categorie.getId();
         req.setUrl(url);

req.addResponseListener(new ActionListener<NetworkEvent>() {
@Override
public void actionPerformed(NetworkEvent evt) {
        resultOK = req.getResponseCode() == 200 ; 
        req.removeResponseListener(this);
}
});
NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
}

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
}

