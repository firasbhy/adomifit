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
import com.mycompany.entities.Programme;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class serviceProgramme {
    
    public static boolean resultOK=true;
    public ArrayList<Programme> Produit;
    public static serviceProgramme instance=null;
     private ConnectionRequest req;
     
     public serviceProgramme() {
           req = new ConnectionRequest();
    }
     
     public static serviceProgramme getInstance() {
        if (instance == null) {
            instance = new serviceProgramme();
        }
        return instance;
    }
     public ArrayList<Programme> parseProduit(String jsonText){
       
        try {
              Produit=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
             Map<String,Object> ProduitJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitJson.get("root");
              for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Programme ct = new Programme();
                float id = Float.parseFloat(obj.get("id").toString());
                
                ct.setId((int)id);
                ct.setTitre(obj.get("titre").toString());
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
                  
                  float abn ;
                if (obj.get("abn")!=null) {
                     abn = Float.parseFloat(obj.get("abn").toString());
                  } else 
                      abn = 0;
                
                //float categorie_programme_id = Float.parseFloat(obj.get("categorieProgramme").toString());
                
                
                
                ct.setJaime(Math.round(jaime));
                ct.setJaimepas(Math.round(jaimepas));
                ct.setAbn(Math.round(abn));
              //  ct.setCategorie_programme_id(Math.round(categorie_programme_id));


                
                //Ajouter la tâche extraite de la réponse Json à la liste
                Produit.add(ct);
            }
      
        } catch (IOException ex) {System.out.println("parse moch mriguel");
          
        }
        
        return Produit;
    
    }
     public ArrayList<Programme> getProgrammes(int prog){
        String url = Statics.BASE_URL+"/listProgrammeF/"+prog;
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
     
     
      public void addProduit(Programme categorie) {
    
   
        String url = Statics.BASE_URL + "/addProgramme?" + "image=" +categorie.getImage()+ "&titre=" +categorie.getTitre()+ "&description=" + categorie.getDescription()+ "&categorie_programme_id=" + categorie.getCategorie_programme_id()+"&user_id="+66;
      req.setUrl(url); 
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
      
      public boolean deleteProgramme(int id){
         String url = Statics.BASE_URL + "/deleteProgramme/"+id;
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
      
      
      public boolean updateProduit (Programme categorie) {
       String url = Statics.BASE_URL + "/updateProgramme/"+categorie.getId()+ "?image=" + categorie.getImage()+ "&description=" + categorie.getDescription()+ "&titre=" +categorie.getTitre();
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
      
       
  
      
       
       
      public boolean jaime(Programme categorie) {
       String url = "http://127.0.0.1:8000/likeeventPr/"+categorie.getId();
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
      
       public boolean jaimepas(Programme categorie) {
       String url = "http://127.0.0.1:8000/dislikeeventPr/"+categorie.getId();
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
        public boolean abn(Programme categorie) {
       String url = "http://127.0.0.1:8000/programme_show/"+categorie.getId();
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

