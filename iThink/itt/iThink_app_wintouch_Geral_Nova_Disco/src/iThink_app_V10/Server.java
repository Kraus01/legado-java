/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import java.io.*; 
import java.text.*; 
import java.net.*; 
import java.nio.file.Files;
import java.nio.file.Paths;
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
  

public class Server implements Runnable 
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    evento_pagamento evListener;
     
  
    // Constructor 
    public Server(evento_pagamento el) throws IOException  
    { 
        evListener = el;
        System.out.println("Thread Start!!");      
        dis = null;
        dos=null;
        s = null;
         
          
    } 
     String IPHOST = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(1);
     
     
      public void apagaft(String cardno){
  
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOST+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                   
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
              String queryy = "INSERT INTO movimentos_gerais_piscina (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc,periodo) SELECT movimentos_piscina.data,movimentos_piscina.ndoc,movimentos_piscina.valor,movimentos_piscina.cartao,movimentos_piscina.artigo,movimentos_piscina.funcionario,movimentos_piscina.POS,movimentos_piscina.Descricao,movimentos_piscina.nlanc,0  FROM movimentos_piscina where cartao = '" + cardno+ "'";
            
        
         
                 PreparedStatement preparedStmt2 = con.prepareStatement(queryy);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }


      
       
  
       }
      
      public boolean apagamov(String Cartao){
     try {
            String dbUrl2="jdbc:sqlserver://"+IPHOST+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER2="sa";
            String SQLPASS2 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                     } catch (InstantiationException ex) {
                        System.out.println(ex);
                     } catch (IllegalAccessException ex) {
                         System.out.println(ex);
                     }
            
            Connection con2 = DriverManager.getConnection(dbUrl2,SQLUSER2,SQLPASS2);
            
            
            Statement count_Statement2 = con2.createStatement();
            
            
            
              String queryy2 = "delete from movimentos_piscina where cartao = '" + Cartao+ "'";
        
         
                 PreparedStatement preparedStmt2 = con2.prepareStatement(queryy2);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
                 con2.close();
        return true;
        
        }catch(SQLException ex){
            
            System.out.println(ex);
     return false;
        }
     
    
    
    
}
      
  double vp = 0;
  String nif="";
  StringBuilder sb = new StringBuilder();
    @Override
    public synchronized void run()  
    { 
        String received; 
        String toreturn;
        
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5056);
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
               
  
              String Cartaoativo="";
                
               String values[] = received.split("#");
               List<String> list = Arrays.asList(values);
//Reverse the list so that longitude and latitude are the first two elements
              // Collections.reverse(list);

            System.out.println("0"+list.get(0));   
            System.out.println("1"+list.get(1));   
            System.out.println("2"+list.get(2));   
               
               
            Cartaoativo = (list.get(1));
           vp = Double.valueOf(list.get(0));
           nif=list.get(2);
              
           
           
           System.out.println(Cartaoativo);
           
           System.out.println(vp);
            System.out.println(nif);
           
             //   vp = Double.parseDouble(vp);
                if (vp<0 & vp!=-9999){   
                     vp = Math.abs(vp);
              
                     
//  evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, vp,"pagapremio");                       
                }else if (vp==-9999){
                    
                    
                    evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, -9999,"Pagamento Cancelado");
                    
                  
                }else{
               // vp = Double.parseDouble(received);
                System.out.println(vp);
               System.out.println(Cartaoativo);
               
                evListener.evento_server(eventos.ev_Novo_Pago_Server, String.valueOf(vp), "FT");
                
                if (!"0".equals(nif)){
                    
                    
                
                 try {
            String dbUrl="jdbc:sqlserver://192.168.1.10:1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
                     
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM movimentos_piscina Where cartao ='" + Cartaoativo+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
            sb.setLength(0);
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            
            String c1 ="'"+count_ResultSet.getString(5)+"'";
            String c2 =count_ResultSet.getString(2);
            String c3 =count_ResultSet.getString(2);
            String c4 =count_ResultSet.getString(3);
            String c5 =count_ResultSet.getString(6);
            String c6 =count_ResultSet.getString(3);
            
            
            if ("23".equals(c5)){
                
                c5="NOR";
            }else if ("13".equals(c5)){
                
                c5="INT";
            }else if ("6".equals(c5)){
                c5="RED";
                
            }
            System.out.println("sudo php /var/www/html/ftt.php " + c1+ "   '" + c2+ "' '" + c3+ "'  '" + c4+ "' '" + c5+ "' '" + c6+ "'");
            
            
            int cc1 = Integer.parseInt(c1.replaceAll("[\\D]", ""));
           
            sb.append("array( ").append(System.getProperty("line.separator"));
            sb.append("'reference'   => ").append(cc1).append(",").append(System.getProperty("line.separator"));
            sb.append("'qty'     => 1,").append(System.getProperty("line.separator"));
       //     if(cc1==92){
             sb.append("'gross_price'         => ").append(c4).append(", ").append(System.getProperty("line.separator"));   
                
         //   }
           // if(cc1==999){
          //   sb.append("'gross_price'         => ").append(c4).append(", ").append(System.getProperty("line.separator"));   
                
          //  }
           //  if(cc1==998){
           //  sb.append("'gross_price'         => ").append(c4).append(", ").append(System.getProperty("line.separator"));   
                
          //  }
            sb.append("), ").append(System.getProperty("line.separator"));
            
       /*    Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo php /var/www/html/ftt.php '" + c1+ "'   '" + c2+ "' '" + c3+ "'  " + c4+ " " + c5+ " " + c6+ "");
            System.out.println(pr);
            
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } 
            */        
            
          }    
       File file =new File("/var/www/html/ft.php");
        FileWriter fw = new FileWriter(file,false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
            
            
           pw.println("<?php");
          
           pw.println("$url     = 'https://www.vendus.pt/ws/v1.1/documents/';");
           pw.println("$apiKey  = 'c7d2a17a2ef22e701445f1df1884a41f';");
           pw.println("$method  = 'POST';");
           pw.println("$params  = array(");
           pw.println("'register_id'            => '99466760',");
           pw.println("'type'                   => 'FS',");
           pw.println("'output'                 => 'escpos',");
           pw.println(" 'return_qrcode'          => 1,");
           if(!"0".equals(nif)){
           pw.println( "   'client'                 => array(");
           
           pw.println("'fiscal_id'          => '"+nif+"', ");
      
           pw.println(" ),");
           }
            pw.println("'payments'               => array(");
         pw.println("array(");
            pw.println("'id'       => 81752628,"); 
         //   pw.println("'amount'   => "+ammountpay+","); 
        //    pw.println("'date_due' => "+"'"+fre+"'"+","); 
           pw.println(" ),");
           pw.println(" ),");        
           pw.println("'items'                  => array(");
           pw.println(sb.toString());
           pw.println(" ),");
           pw.println(");");
           pw.println("$content = json_encode($params);");
           pw.println("$curl = curl_init($url);");
           pw.println("curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);");
           pw.println("curl_setopt($curl, CURLOPT_USERPWD, $apiKey);");     
           pw.println("curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);");
           pw.println("curl_setopt($curl, CURLOPT_POSTFIELDS, $content);");     
           pw.println("curl_setopt($curl, CURLOPT_CUSTOMREQUEST, $method);");  
           pw.println("curl_setopt($curl, CURLOPT_HTTPHEADER,");
           pw.println(" array(");
           pw.println(" 'Content-type: application/json',");
           pw.println(" 'Content-Length: ' . strlen($content),");
           pw.println("  )");
           pw.println(");");
           pw.println("$result = curl_exec($curl);");
           pw.println("echo $result;");
           pw.println("$array = array_column(array_chunk(explode('\"', $result), 2), 1);");
           pw.println("$str2 = $array[18];");
           pw.println("$str3 = base64_decode($str2);");
           pw.println("echo $str3;");
           pw.println("$printer='/dev/usb/lp0';");
           pw.println("$fp=fopen($printer, 'w');");
           pw.println("fwrite($fp,$str3);");
           pw.println("fclose($fp);");
           pw.println("?>");
           pw.flush();
           pw.close();
         
           System.out.println(sb.toString());
        
            
            
              try {
                           Thread.sleep(500);
                       }  catch (InterruptedException ex) {
                           Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
                       }
 String url = ("http://" + "127.0.0.1" + ":" + "80" + "/ft.php");
 URL obj = new URL(url);
 HttpURLConnection con3 = (HttpURLConnection) obj.openConnection();
 
 con3.setRequestMethod("POST");
 con3.setRequestProperty("Accept-Language", "UTF-8");
 
 con3.setDoOutput(true);
 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con3.getOutputStream());
 outputStreamWriter.flush();
 
 int responseCode = con3.getResponseCode();
 System.out.println("Response Code : " + responseCode);
                 
 //apagaft(Cartaoativo);
 //apagamov(Cartaoativo);
 
       
  
  /*
                try {
            String dbUrl2="jdbc:sqlserver://192.168.1.10:1433;"
                    + "databaseName=Disco";
            
            String SQLUSER2="sa";
            String SQLPASS2 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con2 = DriverManager.getConnection(dbUrl2,SQLUSER2,SQLPASS2);
            
            
            Statement count_Statement2 = con.createStatement();
            
            
            
              String queryy2 = "delete from movimentos where cartao = '" + Cartaoativo+ "'";
        
         
                 PreparedStatement preparedStmt2 = con2.prepareStatement(queryy2);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }
  */
                 
                 
        con.close();
         }catch(SQLException ex){
        }   
                
               }
                apagaft(Cartaoativo);
                apagamov(Cartaoativo);
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
    
     
 
