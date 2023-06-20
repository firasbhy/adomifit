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
import com.mycompany.entities.CategorieProgramme;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class serviceCategorieProgramme {
    
    public static boolean resultOK=true;
    public ArrayList<CategorieProgramme> Produit;
    public static serviceCategorieProgramme instance=null;
     private ConnectionRequest req;
     
     public serviceCategorieProgramme() {
           req = new ConnectionRequest();
    }
     
     public static serviceCategorieProgramme getInstance() {
        if (instance == null) {
            instance = new serviceCategorieProgramme();
        }
        return instance;
    }
     public ArrayList<CategorieProgramme> parseProduit(String jsonText){
       
        try {
              Produit=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
             Map<String,Object> ProduitJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitJson.get("root");
              for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CategorieProgramme ct = new CategorieProgramme();
                float id = Float.parseFloat(obj.get("id").toString());
                ct.setId((int)id);
                ct.setLibelle(obj.get("libelle").toString());
                ct.setDescription(obj.get("description").toString());
                ct.setImage(obj.get("image").toString());
                float jaime = Float.parseFloat(obj.get("jaime").toString());
                if (obj.get("jaime")!=null) {
                     jaime = Float.parseFloat(obj.get("jaime").toString());
                  } else 
                      jaime = 0;
                float jaimepas;
                  if (obj.get("jaimepas")!=null) {
                     jaimepas = Float.parseFloat(obj.get("jaimepas").toString());
                  } else 
                      jaimepas = 0;
                ct.setJaime(Math.round(jaime));
                ct.setJaimepas(Math.round(jaimepas));
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                Produit.add(ct);
            }
      
        } catch (IOException ex) {
          
        }
        
        return Produit;
    
    }
     public ArrayList<CategorieProgramme> getAllProduit(){
        String url = Statics.BASE_URL+"/listJson";
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
     
     
      public void addProduit(CategorieProgramme categorie) {
    
   
        String url = Statics.BASE_URL + "/newJson?" + "image=" +categorie.getImage()+ "&libelle=" +categorie.getLibelle()+ "&description=" + categorie.getDescription();
      req.setUrl(url); 
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
      
      public boolean deleteProduit(int id){
         String url = Statics.BASE_URL + "/delete/"+id;
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
      
      public boolean updateProduit (CategorieProgramme categorie) {
       String url = Statics.BASE_URL + "/editJason/"+categorie.getId()+ "?image=" + categorie.getImage()+ "&description=" + categorie.getDescription()+ "&libelle=" +categorie.getLibelle();
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
      
       
  
      
       
       
      public boolean jaime(CategorieProgramme categorie) {
       String url = "http://127.0.0.1:8000/likeeventCt/"+categorie.getId();
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
      
       public boolean jaimepas(CategorieProgramme categorie) {
       String url = "http://127.0.0.1:8000/dislikeeventCt/"+categorie.getId();
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

