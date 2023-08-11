/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import java.io.*; 
import java.text.*; 
import java.net.*; 
import static java.util.Arrays.stream;
import java.util.logging.Level;
import java.util.logging.Logger;
  

public class Pda implements Runnable 
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    evento_pagamento evListener;
      
  
    // Constructor 
    public Pda(evento_pagamento el) throws IOException  
    { 
        evListener = el;
        System.out.println("Thread Start!!");      
        dis = null;
        dos=null;
        s = null;
    } 
  double vp = 0;
  String user="";
    @Override
    public synchronized void run()  
    { 
        String received; 
        String toreturn;
        
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5059);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }       
         do  
        { 
            Socket s = null;             
            try 
            { 
                s = ss.accept();     
                System.out.println("A new client is connected : " + s); 
               
                try (DataInputStream datainputmobile = new DataInputStream(s.getInputStream())) 
                {
                    received = datainputmobile.readUTF();                   
                    System.out.println(received);
                }
               user=received;
               
               
               if ("-9999".equals(user)){
                   
                   evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, -9987,user);
               }else{
                    
                    evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, -9988,user);
                    
               }
            } 
            catch (IOException e){ 
                try { 
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        } while (true);       
        } 
    } 
    
     
 
