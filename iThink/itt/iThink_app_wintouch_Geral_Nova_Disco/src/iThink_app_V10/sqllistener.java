/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import static iThink_app_V10.MainActivity.convertJavaDateToSqlDate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class sqllistener implements Runnable {
    
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

    
     public sqllistener(evento_pagamento el) throws IOException  
    {
        evListener = el;
        
        
    }
     public void errorlog (String event){
      
     pw2.println("- " + hora + " | "+ event);
     pw2.flush(); 
      
      
  }
  
     
       public void parseFile(String fileName,String searchStr) throws FileNotFoundException, IOException, InterruptedException{
         
     
           System.out.println(fileName);
     
            File file = new File(fileName);
            String line = "";
            try (Scanner input = new Scanner(file,"ISO-8859-1")) {
            while (input.hasNextLine()) {
             line = input.nextLine().toLowerCase();
            //    System.out.println(line);
                if(line.contains(searchStr)){
                    System.out.println(line);
                    
                 break;   
                }
            }
             input.close();      
        String[] s = line.split(" ");
        Pattern p = Pattern.compile("(\\d)+\\.(\\d)+");
        double d = 0;
                    for (String item : s) {
                        Matcher m = p.matcher(item);
                        if(m.find())
                            d = Double.parseDouble(m.group());
                    }
        System.out.println("valor " + d);
         evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago_Sql, d, "ft");  
        
        
          Runtime rt2 = Runtime.getRuntime();
          //   Process pr2 = rt2.exec("sudo rm /var/spool/cups/d* ");
          //   pr2.waitFor();
          
          
          java.lang.Runtime.getRuntime().exec("sudo rm -f " + file.getAbsolutePath());
          Thread.sleep(200);
          System.out.println("apagado?");
          
          
          
        
    }
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
     
     String docnum = "";
     String doctype ="";
     String ftt = "";
     String DocAT="";
        String NumAT="";
            
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
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM meiospagamento_audit WHERE meiopagamento = 'NUME';";
        
          //  System.out.println("liga");
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
                
       //     turno = Integer.valueOf(count_ResultSet.getString(6));
            
        //    if (turno == 0){
              double vp = Double.valueOf(count_ResultSet.getString(5));
              
              System.out.println(vp);
           String docnum = count_ResultSet.getString(2)+ count_ResultSet.getString(3);
          // String docnum = count_ResultSet.getString(1);
           evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago_Sql, vp, docnum);  
                
       //     }else{
                
                 String del = "delete  FROM meiospagamento_audit;";
                 
           
           PreparedStatement preparedStmt  = con.prepareStatement(del);
           try {
                preparedStmt.execute();
                
            } catch (SQLException ex) {
                Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
            }
    
                
                
                
             
            }

          

        }catch(SQLException ex){
        }
      
        
                
                
           
                
                
                
        
              
              
              
              
              
              try {
                           Thread.sleep(500);
                       }  catch (InterruptedException ex) {
                           Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                   
        
          }
while (true);
          
          
}


}
          


