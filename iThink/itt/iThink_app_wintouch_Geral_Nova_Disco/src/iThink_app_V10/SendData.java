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
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class SendData implements Runnable {
    
      public static final long serialVersionUID = 6529685098267757690L;
      
      
    
      private static final SimpleDateFormat dff = new SimpleDateFormat("ddMMyyyy");
      private final Date dffnow = new Date();
      public String IPHOST2;  
      
      SimpleDateFormat dfff = new SimpleDateFormat("HH:mm:ss");
      Date now2 = new Date();
      String horadff = dff.format(dffnow);
    
      File file =new File("/home/pi/log/Ithink_"+horadff+".log");
      FileWriter fw = new FileWriter(file,true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
        
      File file2 =new File("/home/pi/log/Ithink_ERROR_"+horadff+".log");
      FileWriter fw2 = new FileWriter(file2,true);
      BufferedWriter bw2 = new BufferedWriter(fw2);
      PrintWriter pw2 = new PrintWriter(bw2);
      
      public String Tipo;
      public String fact;
      public double V1;
      boolean dadospos = false;
 evento_pagamento evListener;
      public SendData(evento_pagamento el) throws IOException { 
              
              
         evListener = el;
         
      IPHOST2 = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(3);
     
      
      
      
      
  //    enviardados(Tipo,Fact,V1);
      
      

}
      public void recebedadospos (String tipo, String Fact,double v1 ){
          
       this.Tipo = tipo;
       this.fact = Fact;
       this.V1 = v1;
        
          
          
          dadospos = true;
          
      }
      

 public void log (String event){
    
     SimpleDateFormat dfff = new SimpleDateFormat("HH:mm:ss");
      Date now2 = new Date();
      String horadff = dff.format(dffnow);
    
    String hora22 = dfff.format(now2);
     pw.println("- " + hora22 + " | "+ event);
     pw.flush(); 
      
  }
     
    public void enviardados(String tipo, String Fact,double v1 ){
       
        
        pagamentopos ed = new pagamentopos(tipo,Fact,v1);
        
        try
			        { 
                        		InetAddress ip = InetAddress.getByName(IPHOST2); //IP do POS
                                        Socket sok = new Socket(IPHOST2, 5057);
                                        sok.setTcpNoDelay(true);
			                log("Ligado a sistema iThink com sucesso");
                                        ObjectOutputStream objOut = new ObjectOutputStream(sok.getOutputStream());
                                        System.out.println(ed.tipo + " - isto");
                                       
                                        System.out.println(ed.v1 + " - v1");
                                        System.out.println(ed.Fact+ " - Fact");         
                                        objOut.writeObject(new pagamentopos(tipo,Fact,v1));
                                        log("dados enviados " + v1 + " EUR" + "Tipo - " + tipo + "| Fat -  " + Fact);
			                objOut.flush();
			                objOut.close(); 
			                sok.close();
                                       
                                        
			        }catch(IOException e){ 
                                    
                                    log("Erro na ligação ao POS" + e);
                                    
			        } 
    // atualizacontadores();
     
      
       
    }
   //     ).start();
        
        
   @Override
    public void run()  
    {
                 
    
         while (true) {
          
           if (dadospos){
               dadospos=false;
              
               
               enviardados(Tipo,fact,V1);
              
           
                        }
             
           try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }   
         
         }  
    }
    
}



    
    
