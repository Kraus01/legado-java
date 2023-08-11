/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Fernando
 */
public class Mails implements Runnable  {
    
    public static final long serialVersionUID = 6529685098267757690L;
    
    public int reais;
   
    
    public   String denom;
    
    boolean enviomail;
    
     String Destinatarios = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(11);
     String MachineName = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(17);
        evento_pagamento evListener;
     public Mails(evento_pagamento el) throws IOException  
    {
      
          evListener = el;
        
    }  
    
     public void recebemail(String Denom){
         
         System.out.println(reais + "jwdhfkçwrugfkwfsjvhbçwfohvçwrouhf");
         this.denom = Denom;
      // this.reais = reais;
       enviomail=true;
         
     }
     
     
     public void enviamails(String denom) throws UnsupportedEncodingException{
         
       
          
    Properties props = new Properties();   
    /** Parâmetros de conexão com serviizzato.ibername.comdor Gmail */
    props.put("mail.smtp.host", "mail.ithinkiot.pt");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", 
    "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
 
    Session session = Session.getDefaultInstance(props,       
      new javax.mail.Authenticator() {
          
          
          
           @Override
           protected PasswordAuthentication getPasswordAuthentication() 
                   
                   
           {
                 return new PasswordAuthentication("fernando@ithinkiot.pt",       
                 "Ph*i=Jq!FB+b");
           }
      });
 
    /** Ativa Debug para sessão */
  //  session.setDebug(true);
 
    try {
 
      Message message = new MimeMessage(session); 
      message.setFrom(new InternetAddress("fernando@ithinkiot.pt", "iThink - IoT")); 
      //Remetente
     
      Address[] toUser = InternetAddress //Destinatário(s)
                 .parse(Destinatarios);  
 
      message.setRecipients(Message
     .RecipientType.TO, toUser);
      message.setSubject("iThink Machine ->" + MachineName);//Assunto
  //    message.setText("Reporte diário iThink!");
      /**Método para enviar a mensagem criada*/
      message.setText(denom);
   //   BodyPart messageBodyPart = new MimeBodyPart();
     // messageBodyPart.setText("Aviso de Niveis Minimos atingidos!" + "/n" + "A Denominação "+ denom + "atingiu o nivel definido como minimo. Quantidades atuais - " + reais);
  /*    Multipart multipart = new MimeMultipart();

         // Set text message part
        multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = FILE2;
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         */
        // multipart.addBodyPart(messageBodyPart);

      //   Send the complete message parts
      //   message.setContent(messageBodyPart);
  
         Transport.send(message);
      enviomail=false;
 
      
 
     } catch (MessagingException e) {
         
        throw new RuntimeException(e);
    }
       
       
       
       
   
    
         
     }
    
            
    @Override
    public void run()  
    {
                 
    
         while (true) {
          
           if (enviomail){
               
               try {
                   enviamails(denom);
               } catch (UnsupportedEncodingException ex) {
                   Logger.getLogger(Mails.class.getName()).log(Level.SEVERE, null, ex);
               }
           }  
             
           try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }   
         
         }  
    }

     
       
         
         }  
    
    
    
    

   
    
    
       

