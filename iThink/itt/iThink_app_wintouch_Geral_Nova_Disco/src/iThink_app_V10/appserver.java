/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class appserver  implements Runnable {
    
     DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    evento_pagamento evListener;
    
      public appserver(evento_pagamento el) throws IOException  
    { 
        evListener = el;
        System.out.println("Thread Start!!");      
        dis = null;
        dos=null;
        s = null;
    } 
     double vp = 0;
  
      @Override
    public synchronized void run()  
    { 
        String received; 
        String toreturn;
        
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(6076);
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
                    received = datainputmobile.readLine();                   
                    System.out.println(received);
                }
                vp = Double.parseDouble(received);
                
                if(vp == -99999){
                     evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, vp,"FECHO"); 
                }
                
                if (vp == -88888){
                    
                    
                    evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, vp,"WinR"); 
                    
                    
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
