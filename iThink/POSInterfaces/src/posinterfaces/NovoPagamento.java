/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posinterfaces;

import iThink_app_V10.AutoDismiss;
import iThink_app_V10.MainActivity;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Fernando
 */
public final class NovoPagamento extends JFrame implements  evento{

    /**
     * Creates new form NovoPagamento
     */
    
    
    Thread Interfaces;
    POSInterfaces interfaces;
    
    Boolean conetado = false; 
    
    
    
    public String IPHOST;
    public String codsuper;
    
    
    public NovoPagamento() throws IOException {
       initComponents();
      
        mbpayment.setVisible(false);
        jButton2.setVisible(false);
        jButton5.setVisible(false);
        jButton4.setVisible(false);
        
        
        if (!conetado){conetar();}
            
  //   IPHOST="192.168.1.15";
  //   codsuper="1234";
       IPHOST = Files.readAllLines(Paths.get("C:/iThink/config/config.txt"),StandardCharsets.ISO_8859_1).get(1);
       codsuper = Files.readAllLines(Paths.get("C:/iThink/config/config.txt"),StandardCharsets.ISO_8859_1).get(6);
       
       
         
    }

    
    public void conetar() throws IOException{
        
       
        conetado = true;
        interfaces = new POSInterfaces(this);
        Interfaces = new Thread(interfaces);
        Interfaces.start();
        
    }
    public String FTF="";
    @Override
     
     
     public void evento_pagamento_ocurrido(eventosenum ev, double v1, String doc) 
    {
        switch (ev){
            
            case ev_Novo_Pago:
              setState(JFrame.NORMAL);
        //       setUndecorated( true );
              setLocationRelativeTo(null);
              setAlwaysOnTop( true );
              setVisible(true);
              ecraPagamentos.show();
              ecramb.hide();
                
              apagarlabel.setText(String.format("%.2f",v1));
              apagarlabel.setText(v1 + " EUR");
              FTF=doc;  
               
          
            break;

            
            case ev_Novo_PagoMB:
              setState(JFrame.NORMAL);
        //       setUndecorated( true );
              setLocationRelativeTo(null);
              setAlwaysOnTop( true );
              setVisible(true);
              ecraPagamentos.hide();
              ecramb.show();
                
              mblabel.setText(String.format("%.2f",v1));
              mblabel.setText(v1 + " EUR");
             break;   
             
              case ev_Novo_PagoMBFAIL:
              setState(JFrame.NORMAL);
        //       setUndecorated( true );
              setLocationRelativeTo(null);
              setAlwaysOnTop( true );
              setVisible(true);
              ecraPagamentos.hide();
              ecramb.show();
                
              mblabel.setText(String.format("%.2f",v1));
              mblabel.setText(v1 + " EUR");
              
              
             break;   
              
            case ev_NovoPago_UNSAFEJAM:
                
              //  alertlb.setText("Nota encravada a Entrada do Bezel!!");
           //   JOptionPane.showMessageDialog(this, "Nota encravada a Entrada do Bezel!!","ATENÇÃO",JOptionPane.ERROR_MESSAGE);
              
                
                
                break;
              
              case ev_NovoPago_UNSAFEJAMCLEAR:
                
               // alertlb.setText("");
              //   JOptionPane.showMessageDialog(this, "Nota Desencravada");
              
                
                
                break;   
                
            case ev_Novo_Pago_Vintroduzido:
                setState(JFrame.NORMAL);
          //       setUndecorated( true );
                setLocationRelativeTo(null);
                setAlwaysOnTop( true );
                setVisible(true);
              ecraPagamentos.show();
              ecramb.hide();
              
          
                pagolabel2.setText(String.format("%.2f",v1));
                pagolabel2.setText(v1 + " EUR");
                
            break;
            
            case ev_Novo_Pago_Troco:
                setState(JFrame.NORMAL);
          //       setUndecorated( true );
              setLocationRelativeTo(null);
              setAlwaysOnTop( true );
              setVisible(true);
              ecraPagamentos.show();
              ecramb.hide();
              trocolabel.setText(String.format("%.2f",v1));
              trocolabel.setText(v1 + " EUR");
                
            

            break;
            
            case ev_Novo_Pago_faltapagar:
                setState(JFrame.NORMAL);
         //     setUndecorated( true );
                setLocationRelativeTo(null);
                setAlwaysOnTop( true );
                setVisible(true);
              ecraPagamentos.show();
              ecramb.hide();
              
          
                faltapagarlabel.setText(String.format("%.2f",v1));
                faltapagarlabel.setText(v1 + " EUR");
                
            break;
            
            case ev_Novo_Pago_limpa:
                setState(JFrame.ICONIFIED);
               // setLocationRelativeTo(null);
                setAlwaysOnTop( false );
                setVisible(false);
              ecraPagamentos.show();
              ecramb.hide();
              faltapagarlabel.setText("0 EUR");
              pagolabel2.setText("0 EUR");
              apagarlabel.setText("0 EUR");
              trocolabel.setText("0 EUR");
              ecraPagamentos.hide();
              ecramb.hide();
              
              
         /*     if(!"".equals(doc)){
                File file2 =new File("C:/Inforcavado/FeijaoVerde/CashDro/OUT/"+doc+".ok");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file2,true);
        } catch (IOException ex) {
            Logger.getLogger(NovoPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        System.out.println(file2);
        pw.println("0");
     //   pw.write("\n");
        pw.println("0");
       // pw.write("\n");
        
              
        pw.flush(); 
        pw.close();
     
              }*/
              
              
                
            break;
            
            case ev_NovoPago_SemTroco:
                
                trocolabel.setText("SEM TROCO");
                
                break;
            
            
        }
        
    
        
        
        
        
        
        
    }
     
     
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ecraPagamentos = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        horas = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        moeda1cimg = new javax.swing.JLabel();
        nota5img = new javax.swing.JLabel();
        nota10img = new javax.swing.JLabel();
        moeda2cimg = new javax.swing.JLabel();
        moeda5centimg = new javax.swing.JLabel();
        nota20img = new javax.swing.JLabel();
        moeda10cimg = new javax.swing.JLabel();
        nota50img = new javax.swing.JLabel();
        moeda20cimg = new javax.swing.JLabel();
        nota100img = new javax.swing.JLabel();
        moeda50cimg = new javax.swing.JLabel();
        moeda1img = new javax.swing.JLabel();
        nota200img = new javax.swing.JLabel();
        nota500img = new javax.swing.JLabel();
        moeda2img = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        apagarlabel = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        pagolabel2 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        faltapagarlabel = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        trocolabel = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        mbpayment = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        alertlb = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        ecramb = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        mblabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        falhamb = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAutoRequestFocus(false);
        setUndecorated(true);

        ecraPagamentos.setBackground(java.awt.SystemColor.activeCaptionBorder);
        ecraPagamentos.setPreferredSize(new java.awt.Dimension(800, 480));
        ecraPagamentos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ecraPagamentosFocusGained(evt);
            }
        });

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        jPanel25.setBackground(new java.awt.Color(102, 102, 102));

        horas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        horas.setText("iThink - Soluções de Pagamentos Automáticos");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel32.setBackground(java.awt.SystemColor.activeCaptionBorder);

        moeda1cimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1cent.png"))); // NOI18N
        moeda1cimg.setText("jLabel39");

        nota5img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir.png"))); // NOI18N
        nota5img.setText("jLabel2");

        nota10img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/IMGBIN_10-euro-note-euro-banknotes-500-euro-note-png_UZ4awMxC.png"))); // NOI18N
        nota10img.setText("jLabel21");

        moeda2cimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2cent.png"))); // NOI18N
        moeda2cimg.setText("jLabel32");

        moeda5centimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5cent.png"))); // NOI18N
        moeda5centimg.setText("jLabel38");

        nota20img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (1).png"))); // NOI18N
        nota20img.setText("jLabel13");

        moeda10cimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10cent.png"))); // NOI18N
        moeda10cimg.setText("jLabel37");
        moeda10cimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moeda10cimgMouseClicked(evt);
            }
        });

        nota50img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (2).png"))); // NOI18N
        nota50img.setText("jLabel20");

        moeda20cimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20cent.png"))); // NOI18N
        moeda20cimg.setText("jLabel36");

        nota100img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (3).png"))); // NOI18N
        nota100img.setText("jLabel22");

        moeda50cimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50cent.png"))); // NOI18N
        moeda50cimg.setText("jLabel33");

        moeda1img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1eur.png"))); // NOI18N
        moeda1img.setText("jLabel35");

        nota200img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (4).png"))); // NOI18N
        nota200img.setText("jLabel25");

        nota500img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (5).png"))); // NOI18N
        nota500img.setText("jLabel31");

        moeda2img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2eur.png"))); // NOI18N
        moeda2img.setText("jLabel34");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(moeda1img, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(moeda50cimg, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(moeda20cimg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(moeda5centimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moeda2cimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moeda1cimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moeda10cimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moeda2img, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nota100img, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nota500img, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(nota200img, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nota50img, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nota20img, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(nota10img, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nota5img, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(moeda1cimg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moeda2cimg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moeda5centimg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moeda10cimg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moeda20cimg, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(moeda50cimg, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moeda1img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moeda2img))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(nota5img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nota10img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nota20img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nota50img, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nota100img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nota200img)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nota500img))))
        );

        jPanel40.setBackground(java.awt.SystemColor.activeCaptionBorder);
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VALOR A PAGAR", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        apagarlabel.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        apagarlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        apagarlabel.setText("0 EUR");
        apagarlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apagarlabelMouseClicked(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_cash_in_hand_64.png"))); // NOI18N

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(apagarlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(apagarlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel31)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel39.setBackground(java.awt.SystemColor.activeCaptionBorder);
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INTRODUZIDO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        pagolabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pagolabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pagolabel2.setText("0 EUR");

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_request_money_48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(33, 33, 33)
                .addComponent(pagolabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagolabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel30.setBackground(java.awt.SystemColor.activeCaptionBorder);
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EM FALTA", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        faltapagarlabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        faltapagarlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        faltapagarlabel.setText("0 EUR");

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_payment_history_48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(faltapagarlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(faltapagarlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel33.setBackground(java.awt.SystemColor.activeCaptionBorder);
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TROCO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        trocolabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        trocolabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trocolabel.setText("0 EUR");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(trocolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trocolabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );

        mbpayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_multibanco_32.png"))); // NOI18N
        mbpayment.setText("Pagamento por Multibanco");
        mbpayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mbpaymentMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        alertlb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        alertlb.setForeground(new java.awt.Color(255, 0, 51));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Fechar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ecraPagamentosLayout = new javax.swing.GroupLayout(ecraPagamentos);
        ecraPagamentos.setLayout(ecraPagamentosLayout);
        ecraPagamentosLayout.setHorizontalGroup(
            ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alertlb, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ecraPagamentosLayout.createSequentialGroup()
                        .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                .addGap(0, 13, Short.MAX_VALUE)
                                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                        .addComponent(mbpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ecraPagamentosLayout.setVerticalGroup(
            ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel26)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mbpayment)
                        .addGap(26, 26, 26)
                        .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(alertlb, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel104.setText("Pagamento por Multibanco");

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel107.setText("Valor a Pagar -> ");

        mblabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mblabel.setText("999 EUR");
        mblabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mb.png"))); // NOI18N

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CANCELAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        falhamb.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        falhamb.setText("FALHA NO PAGAMENTO - REENVIAR");
        falhamb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                falhambMouseClicked(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 153, 255));
        jButton4.setText("Dinheiro");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 153, 255));
        jButton5.setText("Multibanco");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(falhamb)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel104))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(224, 224, 224)
                                    .addComponent(jLabel107)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(mblabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104)
                    .addComponent(jLabel1))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(mblabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(falhamb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout ecrambLayout = new javax.swing.GroupLayout(ecramb);
        ecramb.setLayout(ecrambLayout);
        ecrambLayout.setHorizontalGroup(
            ecrambLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ecrambLayout.setVerticalGroup(
            ecrambLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ecraPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ecramb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ecraPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ecramb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moeda10cimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moeda10cimgMouseClicked

   
    }//GEN-LAST:event_moeda10cimgMouseClicked

    private void apagarlabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apagarlabelMouseClicked
       
    }//GEN-LAST:event_apagarlabelMouseClicked

    
    
      private PopUpKeyboard2 keyboard;
    
     private class PopUpKeyboard2 extends JDialog implements ActionListener
    {
        private final JLabel cc1;

        public PopUpKeyboard2(JLabel c1)
        {
            this.cc1 = c1;
           
       

           setLayout(new GridLayout(4, 3));
           for(int i = 1; i <= 9; i++) createButton(Integer.toString(i));
           createButton("Del");
           createButton(Integer.toString(0));
           createButton("Ent");
          // setState(JFrame.NORMAL);
          //    setLocationRelativeTo(null);
        //      setAlwaysOnTop( true );
          //    setVisible(true);
            pack();
        }

        private void createButton(String label)
        {
            JButton btn = new JButton(label);
            btn.addActionListener(this);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(100, 100));
            java.awt.Font font = btn.getFont();
            float size = font.getSize() + 15.0f;
            btn.setFont(font.deriveFont(size));
            add(btn);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String txt = e.getActionCommand();
            
            if (txt.equalsIgnoreCase("Del")) {
               cc1.setText("");
            }

            else if (txt.equalsIgnoreCase("Ent")) {
                
                if ("".equals(cc1.getText())){
                    
                   AutoDismiss.showOptionDialog(rootPane,"Vazio", "Insira valor pf" ,2000);
                    
                   return;
                    
                }
                keyboard.dispose();
             //   keyboard.hide();
            }else{
                
                 cc1.setText(cc1.getText() + txt);
            
                if (codsuper.equals(cc1.getText())) {
             
       try
             
			        { 
                                    
                                    
                                    
					InetAddress ip = InetAddress.getByName(IPHOST); 
			                Socket s = new Socket(ip, 5056); 
			                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			                String vpp = "-9999#0#0#0";
			                dos.writeUTF(vpp); 
			                dos.flush();
			                dos.close(); 
			                s.close();
			                
			              
			                
			                
			        }catch(IOException e1){ 
			                e1.printStackTrace(); 
			        } 
       
/*           File file2 =new File("C:/Inforcavado/FeijaoVerde/CashDro/OUT/"+FTF+".ko");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file2,true);
        } catch (IOException ex) {
            Logger.getLogger(NovoPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        System.out.println(file2);
        pw.println("0");
     //   pw.write("\n");
        pw.println("0");
       // pw.write("\n");
        
             
        pw.flush(); 
        pw.close();
       
       
        */      setState(JFrame.NORMAL);
              setLocationRelativeTo(null);
              setAlwaysOnTop( true );
              setVisible(true);
            
         faltapagarlabel.setText("CANCELADO");
              pagolabel2.setText("CANCELADO");
              apagarlabel.setText("CANCELADO");
              trocolabel.setText("CANCELADO");
        keyboard.dispose();
        
        
         }  
            
            
            }
        }

        
    }
    
    private void ecraPagamentosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ecraPagamentosFocusGained

    }//GEN-LAST:event_ecraPagamentosFocusGained

    private void mbpaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mbpaymentMouseClicked
       
         try
             
			        { 
                                    
                                    
                                    
					InetAddress ip = InetAddress.getByName(IPHOST); 
			                Socket s = new Socket(ip, 5056); 
			                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			                String vpp = "-9999";
			                dos.writeUTF(vpp); 
			                dos.flush();
			                dos.close(); 
			                s.close();
			                
			              
			                
			                
			        }catch(IOException e1){ 
			                e1.printStackTrace(); 
			        } 	
        
        
    }//GEN-LAST:event_mbpaymentMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JLabel c20 = new JLabel();
        c20.setText("");
        
                keyboard = new NovoPagamento.PopUpKeyboard2(c20);
            //    keyboard.setDefaultCloseOperation(keyboard.CLOSE);
                keyboard.setAlwaysOnTop(true);
                keyboard.setVisible(true);
        
            /*  setState(JFrame.NORMAL);
              setLocationRelativeTo(null);
              setAlwaysOnTop( false );
              setVisible(false);
        */       
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setState(JFrame.ICONIFIED);
               // setLocationRelativeTo(null);
                setAlwaysOnTop( false );
                setVisible(false);
              
              faltapagarlabel.setText("0 EUR");
              pagolabel2.setText("0 EUR");
              apagarlabel.setText("0 EUR");
              trocolabel.setText("0 EUR");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
  try
             
			        { 
                                    
                                    
                                    
					InetAddress ip = InetAddress.getByName(IPHOST); 
			                Socket s = new Socket(ip, 5056); 
			                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			                String vpp = "-7777";
			                dos.writeUTF(vpp); 
			                dos.flush();
			                dos.close(); 
			                s.close();
			                
			              
			                
			                
			        }catch(IOException e1){ 
			                e1.printStackTrace(); 
			        }         
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void falhambMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_falhambMouseClicked
        
       
        
    }//GEN-LAST:event_falhambMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         try
             
			        { 
                                    
                                    
                                    
					InetAddress ip = InetAddress.getByName(IPHOST); 
			                Socket s = new Socket(ip, 5056); 
			                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			                String vpp = "-5555";
			                dos.writeUTF(vpp); 
			                dos.flush();
			                dos.close(); 
			                s.close();
			                
			              
			                
			                
			        }catch(IOException e1){ 
			                e1.printStackTrace(); 
			        }   
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try
             
			        { 
                                    
                                    
                                    
					InetAddress ip = InetAddress.getByName(IPHOST); 
			                Socket s = new Socket(ip, 5056); 
			                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			                String vpp = "-4444";
			                dos.writeUTF(vpp); 
			                dos.flush();
			                dos.close(); 
			                s.close();
			                
			              
			                
			                
			        }catch(IOException e1){ 
			                e1.printStackTrace(); 
			        }   
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NovoPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
           // public double v1 = 0;
            
            
        {
            try {
            NovoPagamento NPagamento = new NovoPagamento();
            
                NPagamento.setState(ICONIFIED);
               
                
                
            } catch (IOException ex) {
                Logger.getLogger(NovoPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
               // NPagamento.setVisible(true);
               // NPagamento.setAlwaysOnTop( true );
                
            
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertlb;
    private javax.swing.JLabel apagarlabel;
    private javax.swing.JPanel ecraPagamentos;
    private javax.swing.JPanel ecramb;
    private javax.swing.JLabel falhamb;
    private javax.swing.JLabel faltapagarlabel;
    private javax.swing.JLabel horas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JLabel mblabel;
    private javax.swing.JLabel mbpayment;
    private javax.swing.JLabel moeda10cimg;
    private javax.swing.JLabel moeda1cimg;
    private javax.swing.JLabel moeda1img;
    private javax.swing.JLabel moeda20cimg;
    private javax.swing.JLabel moeda2cimg;
    private javax.swing.JLabel moeda2img;
    private javax.swing.JLabel moeda50cimg;
    private javax.swing.JLabel moeda5centimg;
    private javax.swing.JLabel nota100img;
    private javax.swing.JLabel nota10img;
    private javax.swing.JLabel nota200img;
    private javax.swing.JLabel nota20img;
    private javax.swing.JLabel nota500img;
    private javax.swing.JLabel nota50img;
    private javax.swing.JLabel nota5img;
    private javax.swing.JLabel pagolabel2;
    private javax.swing.JLabel trocolabel;
    // End of variables declaration//GEN-END:variables
}
