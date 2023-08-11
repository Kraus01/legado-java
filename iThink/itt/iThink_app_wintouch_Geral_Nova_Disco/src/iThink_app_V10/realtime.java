/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

/**
 *
 * @author Fernando
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


    


public class realtime implements Runnable {

   evento_pagamento evListener;
    
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");


    
     public realtime(evento_pagamento el) throws IOException  
    {
        evListener = el;
        
    }  
    
     
     
     
     
      
            
    @Override
    public void run()  
    {
        
         while (true) {
            Date date = getDate();
            String dateString = simpleDateFormat.format(date);
            evListener.evento_pagamento_ocurrido(eventos.ev_novahora, 1,dateString);
            try {
              Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            }
         
         }  
    }

     

    public static java.util.Date getDate() {
      java.util.Date date = new java.util.Date();
      return date;
    }
       
    
    
       
}

    
        