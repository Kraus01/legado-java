package iThink_app_V10;

import java.io.*; 
import java.text.*; 
import java.net.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import static java.util.Arrays.stream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
  

public class Server1 implements Runnable 
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    evento_pagamento evListener;
     
  
    // Constructor 
    public Server1(evento_pagamento el) throws IOException  
    { 
        evListener = el;
        System.out.println("Thread Start!!");      
        dis = null;
        dos=null;
        s = null;
    } 
    
    
  double vp = 0;
  StringBuilder sb = new StringBuilder();
    @Override
    public synchronized void run()  
    { 
        String received; 
        String toreturn;
        
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5054);
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
                vp = Double.parseDouble(received);
                if (vp<0 & vp!=-9999){   
                     vp = Math.abs(vp);
               //  evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, vp,"pagapremio");                       
                }else if ("-9999".equals(received)){
                    
                    
                    
                    evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, -9999,"Pagamento Cancelado");
                    
                    try (DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {
                        dos.writeUTF("chegou");
                        
                        dos.flush();
                        dos.close();
                    }
                }else{
               // vp = Double.parseDouble(received);
               // System.out.println(vp);
                evListener.evento_server(eventos.ev_Novo_Pago_Server, received, "FT");
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
    