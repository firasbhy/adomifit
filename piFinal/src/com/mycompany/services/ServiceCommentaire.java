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
import com.mycompany.entities.Commentaire;
import com.mycompany.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceCommentaire {
    public static boolean resultOk = true;
    public static ServiceCommentaire instance= null;
    private ConnectionRequest req;
    public static ServiceCommentaire getInstance() 
    {
        if(instance==null)
            instance =new ServiceCommentaire();
        return instance;
    }

    public ServiceCommentaire() {
        req =new ConnectionRequest();
    }
    
    public void ajoutCommentaire(Commentaire c)
    {
         String url =Statics.url+"ajouter_commentaire_mobile?type="+c.getType()+"&contenu="+c.getContenu()+"&forum="+c.getForum()+"&user="+c.getUser();
         
       req.setUrl(url);
       req.addResponseListener((e) -> {
       String str =new String(req.getResponseData());
       System.out.println("data== "+str);
       
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
       }
    
    public ArrayList<Commentaire>affichageCommentaire()
    { 
     ArrayList<Commentaire> res= new ArrayList<>();
     String url = Statics.url+"displaycmnts";
     req.setUrl(url);
     req.addResponseListener(new ActionListener<NetworkEvent>(){

         @Override
         public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp= new JSONParser(); 
            try { 
                Map<String,Object>mapCommentaire=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapCommentaire.get("root");
                  
                
                for(Map<String, Object> obj: listOfMaps)
                {
                    Commentaire cmt=new Commentaire();
                    
                   float id =Float.parseFloat(obj.get("id").toString());
                     String  type = obj.get("type").toString();
                     String contenu =obj.get("contenu").toString();
                         cmt.setId((int) id);
                         cmt.setType(type);
                         cmt.setContenu(contenu);
                         res.add(cmt);
                }
            }catch(Exception e)
            { e.printStackTrace();}
         }
     });
    
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
     
        return res;
    
    }     
    public boolean modifierCommentaire(Commentaire c)
    {
         String url = Statics.url +"updatecmnt?id="+c.getId()+"&contenu="+c.getContenu()+"&type="+c.getType()+"&vus="+c.getVus();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              // Code response Http 200 ok 
               resultOk = req.getResponseCode() == 200 ; 
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
     return resultOk;
    }
    
     //Delete 
    public boolean deleteCommentaire(int id ) {
        String url = Statics.url+"deleteCommentaireMobile?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
     
    
   
}
