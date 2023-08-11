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




public class sqllistenerZS implements Runnable {
    
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

    
     public sqllistenerZS(evento_pagamento el) throws IOException  
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
        try {
            String dbUrl="jdbc:sqlserver://"+IPHOST+":1433;"
                    + "databaseName="+EMPRESA;
            
            
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM documentos_audit WHERE pagamento = '1';";
        
            
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
                
       //     turno = Integer.valueOf(count_ResultSet.getString(6));
            
        //    if (turno == 0){
              double vp = Double.valueOf(count_ResultSet.getString(6));
           String doc = count_ResultSet.getString(2)+ count_ResultSet.getString(1);
           String docnum = count_ResultSet.getString(1);
           evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago_Sql, vp, docnum);  
                
       //     }else{
                
                 String del = "delete  FROM documentos_audit;";
      
           PreparedStatement preparedStmt  = con.prepareStatement(del);
           try {
                preparedStmt.execute();
                
            } catch (SQLException ex) {
                Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
                
               
           
           
            }  catch (ClassNotFoundException ex) {
                   Logger.getLogger(sqllistenerZS.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(sqllistenerZS.class.getName()).log(Level.SEVERE, null, ex);
               } catch (InstantiationException ex) {
                   Logger.getLogger(sqllistenerZS.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IllegalAccessException ex) {
                   Logger.getLogger(sqllistenerZS.class.getName()).log(Level.SEVERE, null, ex);
               }
        try {
              Thread.sleep(500);
            }
            catch (InterruptedException e) {
            }
        
   
       
        
          
          
}while (true);

}

    
    
}



    
        
        
    
        
        
        
        
    
    
    
        