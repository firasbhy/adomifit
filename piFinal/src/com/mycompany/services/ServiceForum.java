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
import com.mycompany.entities.Forum;
import com.mycompany.utils.Statics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceForum {
    public static boolean resultOk = true;
    public static ServiceForum instance= null;
    private ConnectionRequest req;
    public static ServiceForum getInstance() 
    {
        if(instance==null)
            instance =new ServiceForum();
        return instance;
    }

    public ServiceForum() {
        req =new ConnectionRequest();
    }
    
    public void ajoutForum(Forum f)
    {
         String url =Statics.url+"ajouter_forum_mobile?titre="+f.getTitre();
         
       req.setUrl(url);
       req.addResponseListener((e) -> {
       String str =new String(req.getResponseData());
       System.out.println("data== "+str);
       
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       
       }
    
    public ArrayList<Forum>affichageForum()
    { 
     ArrayList<Forum> res= new ArrayList<>();
     String url = Statics.url+"displayforums";
     req.setUrl(url);
     req.addResponseListener(new ActionListener<NetworkEvent>(){

         @Override
         public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp= new JSONParser(); 
            try { 
                Map<String,Object>mapForum=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapForum.get("root");
                  
                
                for(Map<String, Object> obj: listOfMaps)
                {
                    Forum cmt=new Forum();
                    
                   float id =Float.parseFloat(obj.get("id").toString());
                     String  type = obj.get("titre").toString();
                    float nbr =Float.parseFloat(obj.get("nbrParticipant").toString());
                         cmt.setId((int) id);
                         cmt.setTitre(type);
                         cmt.setNbrparticipants((int) nbr);
                         res.add(cmt);
                }
            }catch(Exception e)
            { e.printStackTrace();}
         }
     });
    
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
     
        return res;
    
    }     
    public boolean modifierForum(Forum c)
    {
         String url = Statics.url +"updateforum?id="+c.getId()+"&titre="+c.getTitre();
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
    public boolean deleteForum(int id ) {
        String url =Statics.url+"deleteForumMobile?id="+id;
        
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
