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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author myria
 */
public class ServiceEvenement {
    public static boolean resultOK=true;
    public ArrayList<Evenement> evenements;
    public static ServiceEvenement instance=null;
     private ConnectionRequest req;

    public ServiceEvenement() {
         req = new ConnectionRequest();
    }
    public static ServiceEvenement  getInstance() {
          if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
         
    }
    
    
    
  
      
       
       
       
       
       
       
       
       
       
       
       
       
       
       
        public void addEvenement(Evenement evenement) {
    
     
       String url = Statics.BASE_URL + "/addEvenementJSON/new?titre=" + evenement.getTitre()+ "&description=" + evenement.getDescription() + "&longitude="+evenement.getLongitude() + "&matitude="+evenement.getLatitude() +"&image=" +evenement.getImage() + "&horraire="+ evenement.getHorraire()+"&categorie_evenement_id="+evenement.getCategorie_evenement_id();
       req.setUrl(url);
       
       req.addResponseListener((e) -> {
           String str = new String(req.getResponseData());
           System.out.println("data =="+str);
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    
    
    
        
         public boolean deleteEvenement(int id){
         String url = Statics.BASE_URL + "/deleteEvenementJSON/" +id;
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
     
        
     
     
          public boolean updateEvenement(Evenement evenement){
         String url =Statics.BASE_URL + "/updateEvenementJSON/"+  evenement.getId()+ "&titre=" + evenement.getTitre()+ "&description=" + evenement.getDescription() + "&longitude="+evenement.getLongitude()+"&latitude="+evenement.getLatitude()+ "&image="+evenement.getImage()+"&categorie_evenement_id="+evenement.getCategorie_evenement_id();
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


    public Evenement DetailEvenement( int id) {
        
        String url = Statics.BASE_URL+"/AllEvenements/"+id;
        req.setUrl(url);
        req.setPost(false);
       
         Evenement categorie = new Evenement();
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            
            try {
               String str  = new String(req.getResponseData());
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
               
                categorie.setId(id);       
                categorie.setTitre(obj.get("libelle").toString());
                categorie.setDescription(obj.get("description").toString());
                categorie.setHorraire(obj.get("horraire").toString());
                
                
                
            }
            catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return categorie;
        
        
    }     
     
    public ArrayList<Evenement>affichageEvenement(){
        ArrayList<Evenement> result = new ArrayList<>();
        String url=Statics.BASE_URL+"/AllEvenement";
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
                        Evenement cat = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        String titre = obj.get("titre").toString();
                         String description = obj.get("description").toString();
//                          String image = obj.get("image").toString();
                        
                      
                         cat.setId((int)id);
                        
                         cat.setTitre(titre);
                         cat.setDescription(description);
                         
 //                        cat.setImage(obj.get("image").toString());
                
//            String DateConverter = obj.get("horraire").toString().substring(obj.get("horraire").toString().indexOf("timestamp")+10, obj.get("horraire").toString().lastIndexOf("}"));
               // Date currentTime = new Date(Double.valueOf(DateConverter).longValue()+1000);
                 // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                  //String dateString = formatter.format(currentTime);
                   //cat.setHorraire(obj.get("dateString").toString());
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
}
