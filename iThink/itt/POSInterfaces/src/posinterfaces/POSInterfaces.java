/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posinterfaces;

/**
 *
 * @author Fernando
 */


import java.io.*; 
import java.text.*; 
import java.net.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import iThink_app_V10.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;





public class POSInterfaces implements Runnable 
{
 DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
 //   final Socket s; 
    evento evListener;
   /* 
     private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
     private final Date now = new Date();
     private static final SimpleDateFormat dff = new SimpleDateFormat("ddMMyyyy");
    private final Date dffnow = new Date();
    String horadff = dff.format(dffnow);
    
        File file =new File("/iThink/log/Ithink_ERROR"+horadff+".log");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        String hora = df.format(now);   
     */  
  
    // Constructor 
    public POSInterfaces(evento el) throws IOException  
    { 
        evListener = el;
        
        System.out.println("Thread Start!!"); 
     
        dis = null;
        dos=null;
       // s = null;

    } 
  double vp = 0;
  
    
    String IPHOST = Files.readAllLines(Paths.get("C:/iThink/config/config.txt"),StandardCharsets.ISO_8859_1).get(1);
  

  
  
  
    @Override
 @SuppressWarnings("empty-statement")
    public synchronized void run()  
    { 
       String tipo = null; String Fact = null;
       
        double v1=0;
        double v2=0;
        double v3 = 0;
        String FTnum = "";
        ServerSocket ss = null ;
     try {
         ss = new ServerSocket(5057);
     } catch (IOException ex) {
      //   errorlog("Aplicação ja em execução" + ex);
         System.exit(-1);
     }
        
        
        
         do  
        { 
            
            
            Socket s ; 
              
            try 
            { 
               
                s = ss.accept();
                  
                System.out.println("A new client is connected : " + s); 
                
              //  File file = new File("C:\\log\\test.txt");
             //   FileInputStream istream = new FileInputStream(file);
	   //     ObjectInputStream p = new ObjectInputStream(istream);
                
                pagamentopos newpay;
                
                ObjectInputStream objIn = new ObjectInputStream(s.getInputStream()); 
               
               
                
                System.out.println("aqui");
                       
                        
                
               // FileInputStream fileIn = new FileInputStream("file.txt");
                try {
                    
                   // newpay = new pagamentopos(tipo,Fact,v1);
                  newpay = (pagamentopos) objIn.readObject();
                   
                String FT = newpay.Fact;
                        
                        
                        
                        objIn.close();
                    
                    
                    
                    if("unsafejam".equals(newpay.tipo)){
                        
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_NovoPago_UNSAFEJAM , 0,""); 
                        
                        
                    }
                    
                      if("unsafejamclear".equals(newpay.tipo)){
                        
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_NovoPago_UNSAFEJAMCLEAR , 0,""); 
                        
                        
                    }
                    
                
                    
                    if ("valorintroduzido".equals(newpay.tipo)){
                        System.out.println("introduzido");
                        v1 = newpay.v1;
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_Pago_Vintroduzido, v1,"");
                        
                        
                    }
                    
                    if ("valorapagarMB".equals(newpay.tipo)){
                        System.out.println("apagar");
                        FTnum = newpay.Fact;
                        v1 = newpay.v1;
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_PagoMB, v1,FTnum);
                    }
                    
                    if ("MBFail".equals(newpay.tipo)){
                        System.out.println("apagar");
                        FTnum = newpay.Fact;
                        v1 = newpay.v1;
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_PagoMBFAIL, v1,FTnum);
                    }
                    
                    if ("valorapagar".equals(newpay.tipo)){
                        System.out.println("apagar");
                        FTnum = newpay.Fact;
                        v1 = newpay.v1;
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_Pago, v1,FTnum);
                        
                    }   if ("troco".equals(newpay.tipo)) {
                        System.out.println("troco");
                        v1 = newpay.v1;
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_Pago_Troco, v1,"");
                    }
                    
                    if ("faltapagar".equals(newpay.tipo)) {
                        System.out.println("faltapagar");
                        v1 = newpay.v1;
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_Pago_faltapagar, v1,"");
                    }
                    
                    if ("limpa".equals(newpay.tipo)) {
                        System.out.println("faltapagar");
                        
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_Novo_Pago_limpa, 0,FT);
                    }
                    
                    if ("SemTroco".equals(newpay.tipo)) {
                        System.out.println("Semtroco");
                        
                        evListener.evento_pagamento_ocurrido(eventosenum.ev_NovoPago_SemTroco, 0,"");
                        
                        
                    }if ("Sucesso".equals(newpay.tipo)) {
                        
                    
                        
                           try
             
			        { 
                                    
                                    
                                    
					InetAddress ip = InetAddress.getByName(IPHOST); 
			                Socket sk = new Socket(ip, 5056); 
			                DataOutputStream dos = new DataOutputStream(sk.getOutputStream()); 
			                String vpp = "bOK#0#0#0";
			                dos.writeUTF(vpp); 
			                dos.flush();
			                dos.close(); 
			                s.close();
			                
			              
			                
			                
			        }catch(IOException e1){ 
			                e1.printStackTrace(); 
			        } 
                        
                    }
                    
                    
            
            }catch (EOFException e){
                    System.out.println(e + "kadbcjwdv");
                    objIn.close();
                    s.close();
                   
            }
            
        }catch (IOException e){ System.out.println(e);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(POSInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } while (true);       
        } 
    } 