/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class sqllistenerSage implements Runnable {
    
    evento_pagamento evListener;
    public String IPHOST;
    public String SQLPATH;
    public String SQLUSER;
    public String SQLPASS; 
    public String EMPRESA;
    public String Codpagamento;
    
        private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        private final java.util.Date now = new java.util.Date();
        String hora = df.format(now);
        private static final SimpleDateFormat dff = new SimpleDateFormat("ddMMyyyy");
        private final java.util.Date dffnow = new java.util.Date();
        String horadff = dff.format(dffnow);
        File file2 =new File("/home/pi/log/Ithink_ERROR_"+horadff+".log");
        FileWriter fw2 = new FileWriter(file2,true);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        PrintWriter pw2 = new PrintWriter(bw2);

    
     public sqllistenerSage(evento_pagamento el) throws IOException  
    {
        evListener = el;
        
        
    }
     public void errorlog (String event){
      
     pw2.println("- " + hora + " | "+ event);
     pw2.flush(); 
      
      
  }
  
        
  

     
     
       
       
     public void lerfileconfig() throws IOException{
    
    
    

            IPHOST = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(1);
            System.out.println(IPHOST);
            SQLPATH = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(3);
            System.out.println(SQLPATH);
            SQLUSER = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(5);      
            System.out.println(SQLUSER);
            SQLPASS = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(7); 
            System.out.println(SQLPASS);
            EMPRESA = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(9); 
            System.out.println(EMPRESA);
            Codpagamento = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(13);
             System.out.println(Codpagamento);
            

}
     
     
        
            
       @Override
    public void run()  
    {
       
           try {
               lerfileconfig();
           } catch (IOException ex) {
               errorlog("Falha ao ler ficheiro config");
           }
           
           int turno = 0;
           
          do{ 
        
    
            try{
          
        Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
        
         Statement query_Statement = con2.createStatement();
      
          String query = " Select * from FTList WHERE OnScreen LIKE '0' ";
          
           ResultSet query_ResultSet = query_Statement.executeQuery(query);
          System.out.println("linhasd");
             
          while(query_ResultSet.next()){
                 
           
              
              
           double vp = Double.valueOf(query_ResultSet.getString(3));
           vp = vp/100;
           String docnum = query_ResultSet.getString(2);
           evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago_Sql, vp, docnum);  
            System.out.println(vp);    
                 
                
            }
                
              
              
         
           
           
            }  catch (SQLException ex) {
                   Logger.getLogger(sqllistenerSage.class.getName()).log(Level.SEVERE, null, ex);
               }
            
               
            
        try {
              Thread.sleep(500);
            }
            catch (InterruptedException e) {
            }
       
       
               }while (true);
          }

    
    
}



    
        
        
    
        
        
        
        
    
    
    
        