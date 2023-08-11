package iThink_app_V10;

import java.io.*; 
import java.text.*; 
import java.net.*; 
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sun.misc.IOUtils;

  

public class serverSifarma implements Runnable 
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    evento_pagamento evListener;
      
  
    // Constructor 
    public serverSifarma(evento_pagamento el) throws IOException  
    { 
        evListener = el;
        System.out.println("Thread Start!!");      
        dis = null;
        dos=null;
        s = null;
    } 
  double vp = 0;
  public static final int DEFAULT_BUFFER_SIZE = 8192;
  
  private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {

      String newLine = System.getProperty("line.separator");
      String result;
      try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
          result = lines.collect(Collectors.joining(newLine));
      }

      return result;

  }
    @Override
    public synchronized void run()  
    { 
       
        String toreturn;
        
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5096);
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
                
               
                  
               
                StringBuilder  sb = new StringBuilder();
                //try (DataInputStream datainputmobile = new DataInputStream(s.getInputStream())) 
             //  BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
              // InputStream in = s.getInputStream();
          InputStream is = s.getInputStream();
          DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            
            int i = 0;
            
            for (byte b: buffer) {
                
                if (i % 10 == 0) {
                   // System.out.println();
                }
                
                System.out.printf("%02x ", b);
                System.out.println((char)b);
                sb.append((char)b);
                i++;
                
                
            }
    //    is.close();
        String input = sb.toString();
        
        System.out.println(input);
    
            
    

                  
                                      
                   System.out.println("12s"); 
               
            
                
                if ("#INIT#".equals(input)) {
                       System.out.println("3");
                       byte[] chars = {'#','O','K','#'};
                       
                       dos.write(chars);
                       dos.flush();
               //  s.close();
               
            }
                
               else if (input.startsWith("#VD#")){
                     System.out.println("3w");
                     
                    String[] parts = input.split("\\#"); // String array, each element is text between dots

                    String beforeFirstDot = parts[4]; 
                     System.out.println(beforeFirstDot);
                     Double d = Double.parseDouble(beforeFirstDot);
                     String de = beforeFirstDot;
                     
                     d = d/100;
                     evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago_Sql, d, "ft");  
                    
                     String dpp = "#0#"+de+""
                             + "#0#0#0#";
                     System.out.println(dpp);
                     
                     byte[] chars = dpp.getBytes();
                     
                     // byte[] chars = {'#','0','#','1','5','0','#','0','#','0','#','0','#'};
                       
                       dos.write(chars);
                       dos.flush();  
                    
               }else if ("#E#".equals(input)){
                   
                     byte[] chars = {'#','0','#'};
                       
                       dos.write(chars);
                       dos.flush();
                 
                   
               
               
               }else {
                     System.out.println(input);
                   
               } 
                
                s.close();
               
            }
            catch (IOException e){ 
               
            }
            
        }
        
while (true);       
        } 
    } 
    
     
 
/*  if ("PaymentCancelled".equals(received)){                   
                 evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, -99999,"cancelado");                       
                }else if ("push".equals(received)){
                    evListener.evento_pagamento_ocurrido(eventos.ev_android_request, 0,"request");
                    
                    try (DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {
                        dos.writeUTF("chegou");
                        
                        dos.flush();
                        dos.close();
                    }
                }else{
                vp = Double.parseDouble(received);
                evListener.evento_pagamento_ocurrido(eventos.ev_Novo_Pago, vp,"Manual");
               }   */