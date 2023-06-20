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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.myapp.NewsfeedForm;
import com.mycompany.myapp.ProfileForm;
import com.mycompany.myapp.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author Asus
 */
public class ServiceUtilisateur {
    //singleton
    public static ServiceUtilisateur instance = null;
    public static boolean resultOK=true;
    private ConnectionRequest req;
    public ArrayList<User> utilisateur ; 
    String json ; 
     public boolean result ; 
    public String r;
    public static ServiceUtilisateur getInstance(){
    if (instance ==null )
    instance  = new ServiceUtilisateur();
    return instance;
    
    
    }
    public ServiceUtilisateur(){
    req = new ConnectionRequest();
    }
    //inscription
    public void  inscription(TextField email, TextField password, TextField confirmPassword ,TextField nom,TextField prenom,
            ComboBox<String> roles, Resources res){
    //role 
    
    
      String url=Statics.Base_URL+"/inscriptionUser?email="+email.getText().toString()+"&password="+password.getText().toString()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString();
      req.setUrl(url);
      if(email.getText().equals(" ")&&password.getText().equals(" ")){
      Dialog.show("Erreur","Veuillez remplir  ","ok",null);
      
      }
      req.addResponseListener((e)->{
                byte[]data = (byte[]) e.getMetaData();
                String responseData= new String (data);
                System.out.println("data ==>"+responseData);
                
                
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
      
      
    }
    public String signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.Base_URL+"/loginM?email="+email.getText().toString()+"&password="+password.getText().toString();
         //req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
          req.setPost(false);
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
                
            }else if(json.equals("passowrd not found")){Dialog.show("Echec", " password incorrecte ou compte desactiver", "OK",null) ; }
               
              else{ 
                       try {
                Map<String,Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
                 if(obj.size() > 0){
                 System.out.println(obj.get("email").toString());
                 
                      User u = new User() ; 
                      float id = Float.parseFloat(obj.get("id").toString());
              u.setId((int)id); 
                u.setNom(obj.get("nom").toString()) ; 
                u.setPrenom(obj.get("prenom").toString());
                u.setRole(obj.get("role").toString());
               
             
                
                u.setPassword(obj.get("password").toString());
                //Session 
                
               
                
                   connect(u);
                   Dialog.show("success", "welcome", "OK",null) ;
                     System.out.println(u.getRole());
                      r =u.getRole();
            
                 }
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
                      }
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
               return r;
    }
     //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs ) {
        
      
        String url = Statics.Base_URL+"/resetpasswordM?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
public void connect(User p){
        
              StaticValue.user = p ; }




 
    
    public boolean addUtil(User u){
      
     String url = Statics.Base_URL+"/ajouterUser?nom=" + u.getNom()+  "&prenom=" + u.getPrenom()+ "&email=" + u.getEmail()+ "&password=" + u.getPassword()+ "&role="+u.getRole()+"&image="+u.getImage();
            req.setUrl(url) ; 
            req.addResponseListener(new ActionListener<NetworkEvent>(){
                
                @Override 
                public void actionPerformed(NetworkEvent evt){
                
                    result = req.getResponseCode()==200 ; 
                    req.removeResponseListener(this) ; 
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return result ; 
            }
    
    public ArrayList<User> pareseUtilisateur(String jsonText){
        try {
            utilisateur = new ArrayList<>();
            JSONParser j = new JSONParser() ; 
            Map<String,Object> utillistJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray())) ;
            List<Map<String,Object>> list = (List<Map<String,Object>>)utillistJson.get("root") ; 
            for(Map<String,Object> obj : list){
                User u = new User() ; 
                float id = Float.parseFloat(obj.get("id").toString()) ; 
                u.setId((int)id); 
                u.setNom(obj.get("nom").toString()) ; 
                u.setPrenom(obj.get("prenom").toString());
                
                u.setEmail(obj.get("email").toString());
               
                
                
                utilisateur.add(u) ;
               
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateur ; 
        
    }
    
    public ArrayList<User> getallusers(){
   
            String url = Statics.Base_URL+"/afficherUser" ;
            req.setUrl(url);
            req.setPost(false);
            req.addResponseListener(new ActionListener<NetworkEvent>(){
            
                @Override
                public void actionPerformed(NetworkEvent evt){
                    utilisateur = pareseUtilisateur(new String(req.getResponseData())) ;
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req); 
            return utilisateur ;
        
    }
    
    public boolean supputil(int id){
        String url = Statics.Base_URL+"/supprimerUser/"+id; 
         req.setUrl(url) ;
         req.setPost(false);
          NetworkManager.getInstance().addToQueueAndWait(req);
          return true ; 
    }
    
    
    
    public String getPasswordByEmail(String email){
        
        String url = Statics.Base_URL+"/user/getPasswordByEmail?email="+email ; 
        req = new ConnectionRequest(url,false) ; 
        req.setUrl(url);
        
        req.addResponseListener((e)->{
        
            JSONParser j = new JSONParser() ; 
             String json = new String(req.getResponseData()) + "" ; 
             
             try{
             
                 Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray())) ;
             }catch(Exception ex) {
             
                 ex.printStackTrace();
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json ; 
    }
    
    public boolean editUtilisateur(User u) {
        String url = Statics.Base_URL + "/user/editUser?id="+u.getId()+"&nom=" + u.getNom()+  "&prenom=" + u.getPrenom()+ "&email=" + u.getEmail();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
