/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ithinkdisco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.RadioButton;
import javax.imageio.ImageIO;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;



/**
 *
 * @author Fernando
 */
public class Disco extends javax.swing.JFrame  implements ActionListener ,KeyListener {

    /**
     * Creates new form Disco
     */
     DefaultListModel model = new DefaultListModel();
     DefaultListModel modelart = new DefaultListModel();
     
     
     public String IPHOST;
     public String IPHOSTM;
    public String POS;
    public Color myW = new Color(60, 63, 65);
  //  [255,102,0]
    public Color myWF = new Color(255, 102, 0);
   // [255,153,0]
    
     public Color myWF1 = new Color(255, 153, 0);
     public    boolean reserva = false;
     public    boolean dia = false;
     public    boolean guest = false;
     public    boolean vip = false;
      
         int delay = 5000;   // delay for 5 sec.
         int period = 1000;  // repeat every sec.
       


    public Disco() throws IOException {
        initComponents();
        
             Timer timer = new Timer();
            
        timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            getrealtdata();
           
            realtimepp();
            
            
        }
    }, delay, period);
       
         
         
          rbbreserva.addActionListener(sliceActionListener);
        rbbdia.addActionListener(sliceActionListener);
        rbbvip.addActionListener(sliceActionListener);
        rbbguest.addActionListener(sliceActionListener);
                
         if(rbbreserva.isSelected()){
             reserva=true;
         }    
      
        
       jButton44.setBorderPainted(false);
       jButton44.setBorder(null);
       jButton44.setMargin(new Insets(0, 0, 0, 0));
       jButton44.setContentAreaFilled(false);

       jButton43.setBorderPainted(false);
       jButton43.setBorder(null);
       jButton43.setMargin(new Insets(0, 0, 0, 0));
       jButton43.setContentAreaFilled(false);

        btndel.setBorderPainted(false);
        btndel.setBorder(null);
        btndel.setMargin(new Insets(0, 0, 0, 0));
        btndel.setContentAreaFilled(false);
        
         jButton17.setBorderPainted(false);
        jButton17.setBorder(null);
        jButton17.setMargin(new Insets(0, 0, 0, 0));
        jButton17.setContentAreaFilled(false);
      
        POSMain.hide();
        Artigos.hide();
        familias.hide();
        Ivas.hide();
        SplashScreen.hide();
        Menu.hide();
        PasswordPrompt.hide();
        series.hide();
        listanegra.hide();
        Fechos.hide();
        Exploracao.hide();
        Pagamento.hide();
        transfer.hide();
        txtcod.setEditable(false);
        MenuSuper.hide();
        Camarotes.hide();
        fPrivate.show();
        
      //  lbmb.setVisible(false);
        lbdin.setVisible(false);
        lbdinft.setVisible(false);
        lbmisto.setVisible(false);
        IPHOST = Files.readAllLines(Paths.get("C:/iThink/config/config.txt"),StandardCharsets.ISO_8859_1).get(1);
         IPHOSTM = Files.readAllLines(Paths.get("C:/iThink/config/config.txt"),StandardCharsets.ISO_8859_1).get(2);
         System.out.println(IPHOSTM);
        POS = Files.readAllLines(Paths.get("C:/iThink/config/config.txt"),StandardCharsets.ISO_8859_1).get(5);
      //   JLabel background1 = new JLabel(new ImageIcon("/images/fundo-cinza-brilhante_53876-94004.jpg"));
  //    JFrame f = new JFrame();
   //   f.getContentPane().add(new JPanelWithBackground("C:/Users/Fernando/Desktop/disco img/fundo-cinza-brilhante_53876-94004.jpg"));
        
    }
    
     artigoclass newart = new artigoclass();
    
     ActionListener sliceActionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton aButton = (AbstractButton) actionEvent.getSource();
        System.out.println("Selected: " + aButton.getName());
        if ("blreserva".equals(aButton.getName())){
        if(rbbreserva.isSelected()){
           System.out.println("efbe");
          rbbreserva.setBackground(myWF);
          rbbreserva.setForeground(Color.DARK_GRAY);
         
       
            reserva=true;
            dia=false;
            vip=false;
            guest=false;
            
         //    rbbreserva.setBackground(Color.black);
             rbbdia.setBackground(myW);
              rbbdia.setForeground(myWF1);
              
             rbbvip.setBackground(myW);
              rbbvip.setForeground(myWF1);
             
             rbbguest.setBackground(myW);
              rbbguest.setForeground(myWF1);
             
        }
        }
        if ("bldia".equals(aButton.getName())){
        if(rbbdia.isSelected()){
           System.out.println("efbe");
          rbbdia.setBackground(myWF);
          rbbdia.setForeground(Color.DARK_GRAY);
            reserva=false;
            dia=true;
            vip=false;
            guest=false;
            
             rbbreserva.setBackground(myW);
            rbbreserva.setForeground(myWF1);
           //  rbbdia.setBackground(Color.black);
             rbbvip.setBackground(myW);
             rbbvip.setForeground(myWF1);
             rbbguest.setBackground(myW);
             rbbguest.setForeground(myWF1);
        }
        
        
        
        }
        
         if ("blvip".equals(aButton.getName())){
        if(rbbvip.isSelected()){
           System.out.println("efbe");
          rbbvip.setBackground(myWF);
          rbbvip.setForeground(Color.DARK_GRAY);
        
             reserva=false;
            dia=false;
            vip=true;
            guest=false;
             rbbreserva.setBackground(myW);
             rbbreserva.setForeground(myWF1);
             rbbdia.setBackground(myW);
               rbbdia.setForeground(myWF1);
            // rbbvip.setBackground(Color.black);
             rbbguest.setBackground(myW);
              rbbguest.setForeground(myWF1);
        }
        }
         
          if ("blguest".equals(aButton.getName())){
        if(rbbguest.isSelected()){
           System.out.println("efbe");
          rbbguest.setBackground(myWF);
          rbbguest.setForeground(Color.DARK_GRAY);
            reserva=false;
            dia=false;
            vip=false;
            guest=true;
             rbbreserva.setBackground(myW);
               rbbreserva.setForeground(myWF1);
             rbbdia.setBackground(myW);
              rbbdia.setForeground(myWF1);
             rbbvip.setBackground(myW);
              rbbvip.setForeground(myWF1);
           //  rbbguest.setBackground(Color.black);
        }
        }
        
      
      }
    };
       
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        POSMain = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemadded = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btndel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        pnart = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btvodka = new javax.swing.JButton();
        btcervejas = new javax.swing.JButton();
        ctsnack = new javax.swing.JButton();
        btvinhos = new javax.swing.JButton();
        btwisky = new javax.swing.JButton();
        btespumantes = new javax.swing.JButton();
        btgins = new javax.swing.JButton();
        btconhaques = new javax.swing.JButton();
        btaguas = new javax.swing.JButton();
        btcocktails = new javax.swing.JButton();
        bttonicas = new javax.swing.JButton();
        btcafetaria = new javax.swing.JButton();
        btsumos = new javax.swing.JButton();
        btbebidasbrancas = new javax.swing.JButton();
        cardinfo = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        SplashScreen = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cardinput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Artigos = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdesc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pvp1 = new javax.swing.JTextField();
        pvp2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pvp3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jcfamilia = new javax.swing.JComboBox<>();
        jcivas = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        retroceder_pass5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        listart = new javax.swing.JList<>();
        familias = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        retroceder_pass4 = new javax.swing.JLabel();
        Ivas = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        retroceder_pass3 = new javax.swing.JLabel();
        PasswordPrompt = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        senha = new javax.swing.JTextField();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        be = new javax.swing.JButton();
        b0 = new javax.swing.JButton();
        bc = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        retroceder_pass7 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        retroceder_pass6 = new javax.swing.JLabel();
        series = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel23 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton38 = new javax.swing.JButton();
        retroceder_pass8 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        listanegra = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        retroceder_pass9 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jButton40 = new javax.swing.JButton();
        Fechos = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        tttotal = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ttaberto = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        ttfechado = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();
        retroceder_pass10 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        Exploracao = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jButton42 = new javax.swing.JButton();
        Pagamento = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        PTotal = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        niff = new javax.swing.JTextField();
        b10 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b17 = new javax.swing.JButton();
        b18 = new javax.swing.JButton();
        be1 = new javax.swing.JButton();
        b19 = new javax.swing.JButton();
        bc1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        lbmb = new javax.swing.JLabel();
        lbdin = new javax.swing.JLabel();
        lbmisto = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbdinft = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listpag = new javax.swing.JList<>();
        jLabel38 = new javax.swing.JLabel();
        valcons = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        transfer = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        MenuSuper = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        retroceder_pass12 = new javax.swing.JLabel();
        Camarotes = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        retroceder_pass13 = new javax.swing.JLabel();
        fPrivate = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        rbbguest = new javax.swing.JRadioButton();
        rbbreserva = new javax.swing.JRadioButton();
        rbbvip = new javax.swing.JRadioButton();
        rbbdia = new javax.swing.JRadioButton();
        jLabel53 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        niff2 = new javax.swing.JTextField();
        b40 = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        b42 = new javax.swing.JButton();
        b43 = new javax.swing.JButton();
        b44 = new javax.swing.JButton();
        b45 = new javax.swing.JButton();
        b46 = new javax.swing.JButton();
        b47 = new javax.swing.JButton();
        b48 = new javax.swing.JButton();
        be4 = new javax.swing.JButton();
        b49 = new javax.swing.JButton();
        bc5 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        ttGuest = new javax.swing.JLabel();
        ttReserva = new javax.swing.JLabel();
        ttDia = new javax.swing.JLabel();
        ttVip = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        ttTotal = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        ttTicketLine = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        trGuest = new javax.swing.JLabel();
        trReserva = new javax.swing.JLabel();
        trDia = new javax.swing.JLabel();
        trVip = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        trLTot = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        trTL = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setName("iThink_Disco_Pag"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        POSMain.setBackground(new java.awt.Color(153, 153, 153));
        POSMain.setMaximumSize(new java.awt.Dimension(1024, 768));
        POSMain.setMinimumSize(new java.awt.Dimension(1024, 768));
        POSMain.setPreferredSize(new java.awt.Dimension(1024, 768));

        itemadded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(itemadded);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoiot.png"))); // NOI18N

        btndel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button.png"))); // NOI18N
        btndel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btndel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/penhalogo2.jpg"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        saldo.setText("SALDO ");
        saldo.setFont(new java.awt.Font("Tahoma", 3, 34)); // NOI18N
        saldo.setForeground(new java.awt.Color(255, 255, 255));

        pnart.setBackground(new java.awt.Color(153, 153, 153));
        pnart.setMinimumSize(new java.awt.Dimension(800, 400));
        pnart.setPreferredSize(new java.awt.Dimension(800, 400));

        javax.swing.GroupLayout pnartLayout = new javax.swing.GroupLayout(pnart);
        pnart.setLayout(pnartLayout);
        pnartLayout.setHorizontalGroup(
            pnartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnartLayout.setVerticalGroup(
            pnartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        btvodka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vodka.absolut.png"))); // NOI18N
        btvodka.setText("Vodka");
        btvodka.setName("Vodka"); // NOI18N
        btvodka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btvodkaActionPerformed(evt);
            }
        });

        btcervejas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerveja.bohemia.png"))); // NOI18N
        btcervejas.setText("Cervejas");
        btcervejas.setBackground(new java.awt.Color(255, 255, 255));
        btcervejas.setIconTextGap(0);
        btcervejas.setName("Cervejas"); // NOI18N
        btcervejas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcervejasActionPerformed(evt);
            }
        });

        ctsnack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/comida.hamburguer.png"))); // NOI18N
        ctsnack.setText("Snack");
        ctsnack.setName("Snack"); // NOI18N
        ctsnack.setToolTipText("");
        ctsnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctsnackActionPerformed(evt);
            }
        });

        btvinhos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vinho.tinto.png"))); // NOI18N
        btvinhos.setText("Vinhos");
        btvinhos.setName("Vinhos"); // NOI18N
        btvinhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btvinhosActionPerformed(evt);
            }
        });

        btwisky.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/whisky.redlabel.png"))); // NOI18N
        btwisky.setText("Wisky");
        btwisky.setName("Wisky"); // NOI18N
        btwisky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btwiskyActionPerformed(evt);
            }
        });

        btespumantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/espumante.MoetChandon.png"))); // NOI18N
        btespumantes.setText("Espumantes");
        btespumantes.setName("Espumantes"); // NOI18N
        btespumantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btespumantesActionPerformed(evt);
            }
        });

        btgins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gin.beefeater.png"))); // NOI18N
        btgins.setText("Gin");
        btgins.setName("Gin"); // NOI18N
        btgins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btginsActionPerformed(evt);
            }
        });

        btconhaques.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bebida.portoferreira.png"))); // NOI18N
        btconhaques.setText("Conhaques");
        btconhaques.setName("Conhaques"); // NOI18N
        btconhaques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btconhaquesActionPerformed(evt);
            }
        });

        btaguas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agua.luso.png"))); // NOI18N
        btaguas.setText("Aguas");
        btaguas.setBackground(new java.awt.Color(255, 255, 255));
        btaguas.setIconTextGap(0);
        btaguas.setName("Aguas"); // NOI18N
        btaguas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaguasActionPerformed(evt);
            }
        });

        btcocktails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cafe.shaker.png"))); // NOI18N
        btcocktails.setText("Cocktails");
        btcocktails.setBackground(new java.awt.Color(255, 255, 255));
        btcocktails.setIconTextGap(0);
        btcocktails.setName("Cocktails"); // NOI18N
        btcocktails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcocktailsActionPerformed(evt);
            }
        });

        bttonicas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bebida.schwep.png"))); // NOI18N
        bttonicas.setText("Tonicas");
        bttonicas.setBackground(new java.awt.Color(255, 255, 255));
        bttonicas.setIconTextGap(0);
        bttonicas.setName("Tonicas"); // NOI18N
        bttonicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttonicasActionPerformed(evt);
            }
        });

        btcafetaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cafe.cafe.png"))); // NOI18N
        btcafetaria.setText("Cafetaria");
        btcafetaria.setBackground(new java.awt.Color(255, 153, 0));
        btcafetaria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btcafetaria.setForeground(new java.awt.Color(255, 255, 255));
        btcafetaria.setIconTextGap(0);
        btcafetaria.setName("Cafetaria"); // NOI18N
        btcafetaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcafetariaActionPerformed(evt);
            }
        });

        btsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bebida.sumol.png"))); // NOI18N
        btsumos.setText("Sumos");
        btsumos.setBackground(new java.awt.Color(255, 153, 0));
        btsumos.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btsumos.setForeground(new java.awt.Color(255, 255, 255));
        btsumos.setIconTextGap(0);
        btsumos.setName("Sumos"); // NOI18N
        btsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsumosActionPerformed(evt);
            }
        });

        btbebidasbrancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bebida.malibu.png"))); // NOI18N
        btbebidasbrancas.setText("Bebidas Brancas");
        btbebidasbrancas.setActionCommand("Bebidas  Brancas");
        btbebidasbrancas.setBackground(new java.awt.Color(255, 153, 0));
        btbebidasbrancas.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btbebidasbrancas.setForeground(new java.awt.Color(255, 255, 255));
        btbebidasbrancas.setIconTextGap(0);
        btbebidasbrancas.setName("Bebidas Brancas"); // NOI18N
        btbebidasbrancas.setToolTipText("");
        btbebidasbrancas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbebidasbrancasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btcervejas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcocktails, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bttonicas, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(btvodka, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ctsnack, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btaguas, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btvinhos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btbebidasbrancas, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(btespumantes, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btgins, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btwisky, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btcafetaria, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btconhaques, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btvodka, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctsnack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btvinhos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btwisky, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btespumantes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btgins, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcervejas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btconhaques, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcafetaria, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbebidasbrancas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btaguas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttonicas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcocktails, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardinfo.setText("Cartão - ");
        cardinfo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (1).png"))); // NOI18N
        jButton17.setBackground(new java.awt.Color(0, 51, 255));
        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngeggnoimp.png"))); // NOI18N
        jButton44.setBackground(new java.awt.Color(153, 153, 153));
        jButton44.setBorder(null);
        jButton44.setBorderPainted(false);
        jButton44.setOpaque(false);
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngegg.png"))); // NOI18N
        jButton43.setBackground(new java.awt.Color(153, 153, 153));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mbway.jpg"))); // NOI18N
        jButton1.setText("MBWAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (3).png"))); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout POSMainLayout = new javax.swing.GroupLayout(POSMain);
        POSMain.setLayout(POSMainLayout);
        POSMainLayout.setHorizontalGroup(
            POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, POSMainLayout.createSequentialGroup()
                .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnart, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(POSMainLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(49, 49, 49)
                        .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(POSMainLayout.createSequentialGroup()
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(POSMainLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(cardinfo)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, POSMainLayout.createSequentialGroup()
                        .addComponent(btndel)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(saldo)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, POSMainLayout.createSequentialGroup()
                            .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, POSMainLayout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap()))))
            .addGroup(POSMainLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addContainerGap())
        );
        POSMainLayout.setVerticalGroup(
            POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(POSMainLayout.createSequentialGroup()
                .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(POSMainLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, POSMainLayout.createSequentialGroup()
                                .addComponent(cardinfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnart, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(POSMainLayout.createSequentialGroup()
                        .addComponent(saldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(POSMainLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(POSMainLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addGroup(POSMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logopenhagr.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        cardinput.setBackground(new java.awt.Color(51, 51, 51));
        cardinput.setBorder(null);
        cardinput.setForeground(new java.awt.Color(51, 51, 51));
        cardinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardinputActionPerformed(evt);
            }
        });
        cardinput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cardinputKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cardinputKeyTyped(evt);
            }
        });

        jLabel5.setText("Passe Cartão no Leitor");
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(278, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(289, 289, 289))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(cardinput, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(263, 263, 263))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(cardinput, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout SplashScreenLayout = new javax.swing.GroupLayout(SplashScreen);
        SplashScreen.setLayout(SplashScreenLayout);
        SplashScreenLayout.setHorizontalGroup(
            SplashScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SplashScreenLayout.setVerticalGroup(
            SplashScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Artigos.setBackground(new java.awt.Color(153, 153, 153));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel6.setText("Codigo");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setText("Descrição");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtdesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdescMouseClicked(evt);
            }
        });

        jLabel8.setText("Familia");
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setText("Iva");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel10.setText("PVP 1");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        pvp1.setText("0");
        pvp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvp1MouseClicked(evt);
            }
        });
        pvp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvp1ActionPerformed(evt);
            }
        });

        pvp2.setText("0");
        pvp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvp2MouseClicked(evt);
            }
        });

        jLabel11.setText("PVP 2");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        pvp3.setText("0");
        pvp3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvp3MouseClicked(evt);
            }
        });

        jLabel12.setText("PVP 3");
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jcfamilia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcfamiliaMouseClicked(evt);
            }
        });
        jcfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcfamiliaActionPerformed(evt);
            }
        });

        jcivas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcivasMouseClicked(evt);
            }
        });

        jButton9.setText("Gravar");
        jButton9.setBackground(new java.awt.Color(51, 153, 255));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Novo");
        jButton10.setBackground(new java.awt.Color(51, 153, 255));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Apagar");
        jButton11.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pvp2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pvp1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pvp3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(98, 98, 98))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jcivas, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jcfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jcivas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(pvp1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(pvp2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(pvp3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setText("Artigos");
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        retroceder_pass5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass5.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass5MouseClicked(evt);
            }
        });

        listart.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Existentes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        listart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listartMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(listart);

        javax.swing.GroupLayout ArtigosLayout = new javax.swing.GroupLayout(Artigos);
        Artigos.setLayout(ArtigosLayout);
        ArtigosLayout.setHorizontalGroup(
            ArtigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArtigosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass5)
                .addGap(379, 379, 379)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ArtigosLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addGap(47, 47, 47))
        );
        ArtigosLayout.setVerticalGroup(
            ArtigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArtigosLayout.createSequentialGroup()
                .addGroup(ArtigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ArtigosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ArtigosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel13)))
                .addGroup(ArtigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ArtigosLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArtigosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );

        familias.setBackground(new java.awt.Color(153, 153, 153));

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel14.setText("Descrição");
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel15.setText("Codigo");
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton2.setText("Gravar");

        jButton4.setText("Apagar");

        jButton5.setText("Novo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel16.setText("FAMILIAS");
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        retroceder_pass4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass4.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout familiasLayout = new javax.swing.GroupLayout(familias);
        familias.setLayout(familiasLayout);
        familiasLayout.setHorizontalGroup(
            familiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familiasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(familiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(familiasLayout.createSequentialGroup()
                        .addGap(0, 243, Short.MAX_VALUE)
                        .addGroup(familiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, familiasLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(240, 240, 240))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, familiasLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(453, 453, 453))))
                    .addGroup(familiasLayout.createSequentialGroup()
                        .addComponent(retroceder_pass4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        familiasLayout.setVerticalGroup(
            familiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familiasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        Ivas.setBackground(new java.awt.Color(153, 153, 153));

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        jLabel17.setText("Descrição");
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel18.setText("Codigo");
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton6.setText("Gravar");
        jButton6.setBackground(new java.awt.Color(51, 153, 255));

        jButton7.setText("Apagar");
        jButton7.setBackground(new java.awt.Color(51, 153, 255));

        jButton8.setText("Novo");
        jButton8.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel19.setText("IVAS");
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        retroceder_pass3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass3.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout IvasLayout = new javax.swing.GroupLayout(Ivas);
        Ivas.setLayout(IvasLayout);
        IvasLayout.setHorizontalGroup(
            IvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IvasLayout.createSequentialGroup()
                .addGroup(IvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IvasLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(IvasLayout.createSequentialGroup()
                        .addGap(477, 477, 477)
                        .addComponent(jLabel19))
                    .addGroup(IvasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass3)))
                .addContainerGap(308, Short.MAX_VALUE))
        );
        IvasLayout.setVerticalGroup(
            IvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IvasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(72, 72, 72)
                .addComponent(jLabel19)
                .addGap(28, 28, 28)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
        );

        PasswordPrompt.setBackground(new java.awt.Color(153, 153, 153));

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

        jLabel20.setText("Introduza a senha de Administrador");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));

        senha.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        senha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        senha.setBorder(null);
        senha.setCaretColor(new java.awt.Color(255, 255, 255));

        b1.setText("1");
        b1.setBackground(new java.awt.Color(51, 153, 255));
        b1.setBorder(null);
        b1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setToolTipText("");
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b1MouseClicked(evt);
            }
        });
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setText("2");
        b2.setBackground(new java.awt.Color(51, 153, 255));
        b2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setToolTipText("");
        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b2MouseClicked(evt);
            }
        });

        b3.setText("3");
        b3.setActionCommand("1");
        b3.setBackground(new java.awt.Color(51, 153, 255));
        b3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setToolTipText("");
        b3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b3MouseClicked(evt);
            }
        });
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b6.setText("6");
        b6.setBackground(new java.awt.Color(51, 153, 255));
        b6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b6.setForeground(new java.awt.Color(255, 255, 255));
        b6.setToolTipText("");
        b6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b6MouseClicked(evt);
            }
        });

        b5.setText("5");
        b5.setBackground(new java.awt.Color(51, 153, 255));
        b5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setToolTipText("");
        b5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b5MouseClicked(evt);
            }
        });

        b4.setText("4");
        b4.setBackground(new java.awt.Color(51, 153, 255));
        b4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setToolTipText("");
        b4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b4MouseClicked(evt);
            }
        });

        b7.setText("7");
        b7.setBackground(new java.awt.Color(51, 153, 255));
        b7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b7.setForeground(new java.awt.Color(255, 255, 255));
        b7.setToolTipText("");
        b7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b7MouseClicked(evt);
            }
        });

        b8.setText("8");
        b8.setBackground(new java.awt.Color(51, 153, 255));
        b8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b8.setForeground(new java.awt.Color(255, 255, 255));
        b8.setToolTipText("");
        b8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b8MouseClicked(evt);
            }
        });
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        b9.setText("9");
        b9.setBackground(new java.awt.Color(51, 153, 255));
        b9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b9.setForeground(new java.awt.Color(255, 255, 255));
        b9.setToolTipText("");
        b9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b9MouseClicked(evt);
            }
        });

        be.setText("E");
        be.setBackground(new java.awt.Color(51, 153, 255));
        be.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        be.setForeground(new java.awt.Color(255, 255, 255));
        be.setToolTipText("");
        be.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beMouseClicked(evt);
            }
        });

        b0.setText("0");
        b0.setBackground(new java.awt.Color(51, 153, 255));
        b0.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b0.setForeground(new java.awt.Color(255, 255, 255));
        b0.setToolTipText("");
        b0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b0MouseClicked(evt);
            }
        });

        bc.setText("C");
        bc.setBackground(new java.awt.Color(51, 153, 255));
        bc.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        bc.setForeground(new java.awt.Color(255, 255, 255));
        bc.setToolTipText("");
        bc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bcMouseClicked(evt);
            }
        });

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(bc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b0, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(be, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel31)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(20, 20, 20)
                        .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b0, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(be, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        retroceder_pass7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass7.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PasswordPromptLayout = new javax.swing.GroupLayout(PasswordPrompt);
        PasswordPrompt.setLayout(PasswordPromptLayout);
        PasswordPromptLayout.setHorizontalGroup(
            PasswordPromptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PasswordPromptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        PasswordPromptLayout.setVerticalGroup(
            PasswordPromptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PasswordPromptLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(PasswordPromptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Menu.setBackground(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jButton13.setText("Familias");
        jButton13.setBackground(new java.awt.Color(51, 153, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Ivas");
        jButton14.setBackground(new java.awt.Color(51, 153, 255));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Operadores");
        jButton15.setBackground(new java.awt.Color(51, 153, 255));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));

        jButton18.setText("Artigos");
        jButton18.setBackground(new java.awt.Color(51, 153, 255));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Cartões");
        jButton19.setBackground(new java.awt.Color(51, 153, 255));
        jButton19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Lista Negra");
        jButton20.setBackground(new java.awt.Color(51, 153, 255));
        jButton20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Movimentos Periodo");
        jButton21.setBackground(new java.awt.Color(51, 153, 255));
        jButton21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton22.setText("Fechar Periodo");
        jButton22.setBackground(new java.awt.Color(51, 153, 255));
        jButton22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel21.setText("Menu Principal");
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        jButton23.setText("Transferência Cartões");
        jButton23.setBackground(new java.awt.Color(51, 153, 255));
        jButton23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel21)
                .addGap(62, 62, 62)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        retroceder_pass6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass6.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(retroceder_pass6)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(retroceder_pass6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setText("Inicio");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel22.setText("Fim");
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jScrollPane2.setViewportView(jList1);

        jLabel23.setText("Series Existentes");
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton35.setText("Apagar Serie");
        jButton35.setBackground(new java.awt.Color(51, 153, 255));
        jButton35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 255, 255));

        jButton36.setText("Inserir serie");
        jButton36.setBackground(new java.awt.Color(51, 153, 255));
        jButton36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 255, 255));

        jLabel24.setText("Cartão Gerentes");
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton37.setText("Inserir");
        jButton37.setBackground(new java.awt.Color(51, 153, 255));
        jButton37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton37.setForeground(new java.awt.Color(255, 255, 255));

        jLabel25.setText("Cartões  Existentes");
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jScrollPane3.setViewportView(jList2);

        jButton38.setText("Apagar Cartão");
        jButton38.setBackground(new java.awt.Color(51, 153, 255));
        jButton38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 255, 255));

        retroceder_pass8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass8.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass8MouseClicked(evt);
            }
        });

        jLabel26.setText("Series de Cartões");
        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton35)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton36)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel22))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(120, 120, 120)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(28, 28, 28)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton37)
                            .addComponent(jButton38)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(retroceder_pass8)
                        .addGap(267, 267, 267)
                        .addComponent(jLabel26)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(retroceder_pass8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel26)))
                .addGap(34, 34, 34)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton36)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton35))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton38)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout seriesLayout = new javax.swing.GroupLayout(series);
        series.setLayout(seriesLayout);
        seriesLayout.setHorizontalGroup(
            seriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        seriesLayout.setVerticalGroup(
            seriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        jLabel27.setText("Lista Negra Cartões");
        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        retroceder_pass9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass9.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass9MouseClicked(evt);
            }
        });

        jLabel28.setText("Cartão");
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton39.setText("Inserir");
        jButton39.setBackground(new java.awt.Color(51, 153, 255));
        jButton39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 255, 255));

        jLabel29.setText("Cartões em Lista Negra");
        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jScrollPane4.setViewportView(jList3);

        jButton40.setText("Retirar Cartão");
        jButton40.setBackground(new java.awt.Color(51, 153, 255));
        jButton40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton40.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(retroceder_pass9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addGap(28, 28, 28)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(76, 76, 76))
                        .addComponent(jButton39)
                        .addComponent(jButton40)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel27))
                .addGap(355, 355, 355))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel27))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(retroceder_pass9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jButton39)
                .addGap(29, 29, 29)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton40)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout listanegraLayout = new javax.swing.GroupLayout(listanegra);
        listanegra.setLayout(listanegraLayout);
        listanegraLayout.setHorizontalGroup(
            listanegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        listanegraLayout.setVerticalGroup(
            listanegraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(153, 153, 153));

        jLabel30.setText("Total Periodo - ");
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        tttotal.setText("999€");
        tttotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(table);

        jLabel33.setText("Cartões em aberto");
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel34.setText("Total Em Aberto - ");
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        ttaberto.setText("999€");
        ttaberto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel36.setText("Total Fechado - ");
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        ttfechado.setText("999€");
        ttfechado.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jButton41.setText("Fecho");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        retroceder_pass10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass10.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass10MouseClicked(evt);
            }
        });

        jLabel32.setText("Painel Bordo");
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(ttaberto, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(455, 455, 455))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(51, 51, 51)
                                        .addComponent(tttotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(43, 43, 43)
                                        .addComponent(ttfechado, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(retroceder_pass10)
                                .addGap(279, 279, 279)
                                .addComponent(jLabel32)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass10))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel32)))
                .addGap(68, 68, 68)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(ttfechado))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(ttaberto))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(tttotal)))
                    .addComponent(jButton41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout FechosLayout = new javax.swing.GroupLayout(Fechos);
        Fechos.setLayout(FechosLayout);
        FechosLayout.setHorizontalGroup(
            FechosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FechosLayout.setVerticalGroup(
            FechosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(153, 153, 153));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(table1);

        datePicker1.setBackground(java.awt.SystemColor.activeCaptionBorder);

        jButton42.setText("Carregar Dia");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton42)
                .addGap(26, 26, 26)
                .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jButton42))
                .addGap(187, 187, 187)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        javax.swing.GroupLayout ExploracaoLayout = new javax.swing.GroupLayout(Exploracao);
        Exploracao.setLayout(ExploracaoLayout);
        ExploracaoLayout.setHorizontalGroup(
            ExploracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ExploracaoLayout.setVerticalGroup(
            ExploracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));

        jLabel37.setText("Valor Consumido");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        PTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PTotal.setText("999 €");
        PTotal.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        PTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel14.setBackground(new java.awt.Color(153, 153, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contribuinte", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        niff.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        niff.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        niff.setBorder(null);
        niff.setCaretColor(new java.awt.Color(255, 255, 255));

        b10.setText("1");
        b10.setBackground(new java.awt.Color(51, 153, 255));
        b10.setBorder(null);
        b10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b10.setForeground(new java.awt.Color(255, 255, 255));
        b10.setToolTipText("");
        b10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b10MouseClicked(evt);
            }
        });
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        b11.setText("2");
        b11.setBackground(new java.awt.Color(51, 153, 255));
        b11.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b11.setForeground(new java.awt.Color(255, 255, 255));
        b11.setToolTipText("");
        b11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b11MouseClicked(evt);
            }
        });
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });

        b12.setText("3");
        b12.setActionCommand("1");
        b12.setBackground(new java.awt.Color(51, 153, 255));
        b12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b12.setForeground(new java.awt.Color(255, 255, 255));
        b12.setToolTipText("");
        b12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b12MouseClicked(evt);
            }
        });
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });

        b13.setText("6");
        b13.setBackground(new java.awt.Color(51, 153, 255));
        b13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b13.setForeground(new java.awt.Color(255, 255, 255));
        b13.setToolTipText("");
        b13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b13MouseClicked(evt);
            }
        });
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });

        b14.setText("5");
        b14.setBackground(new java.awt.Color(51, 153, 255));
        b14.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b14.setForeground(new java.awt.Color(255, 255, 255));
        b14.setToolTipText("");
        b14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b14MouseClicked(evt);
            }
        });
        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14ActionPerformed(evt);
            }
        });

        b15.setText("4");
        b15.setBackground(new java.awt.Color(51, 153, 255));
        b15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b15.setForeground(new java.awt.Color(255, 255, 255));
        b15.setToolTipText("");
        b15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b15MouseClicked(evt);
            }
        });
        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15ActionPerformed(evt);
            }
        });

        b16.setText("7");
        b16.setBackground(new java.awt.Color(51, 153, 255));
        b16.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b16.setForeground(new java.awt.Color(255, 255, 255));
        b16.setToolTipText("");
        b16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b16MouseClicked(evt);
            }
        });
        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16ActionPerformed(evt);
            }
        });

        b17.setText("8");
        b17.setBackground(new java.awt.Color(51, 153, 255));
        b17.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b17.setForeground(new java.awt.Color(255, 255, 255));
        b17.setToolTipText("");
        b17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b17MouseClicked(evt);
            }
        });
        b17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b17ActionPerformed(evt);
            }
        });

        b18.setText("9");
        b18.setBackground(new java.awt.Color(51, 153, 255));
        b18.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b18.setForeground(new java.awt.Color(255, 255, 255));
        b18.setToolTipText("");
        b18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b18MouseClicked(evt);
            }
        });
        b18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b18ActionPerformed(evt);
            }
        });

        be1.setText("E");
        be1.setBackground(new java.awt.Color(51, 153, 255));
        be1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        be1.setForeground(new java.awt.Color(255, 255, 255));
        be1.setToolTipText("");
        be1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                be1MouseClicked(evt);
            }
        });
        be1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                be1ActionPerformed(evt);
            }
        });

        b19.setText("0");
        b19.setBackground(new java.awt.Color(51, 153, 255));
        b19.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b19.setForeground(new java.awt.Color(255, 255, 255));
        b19.setToolTipText("");
        b19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b19MouseClicked(evt);
            }
        });
        b19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b19ActionPerformed(evt);
            }
        });

        bc1.setText("C");
        bc1.setBackground(new java.awt.Color(51, 153, 255));
        bc1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        bc1.setForeground(new java.awt.Color(255, 255, 255));
        bc1.setToolTipText("");
        bc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bc1MouseClicked(evt);
            }
        });
        bc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bc1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(bc1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b17, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(be1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(b12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b18, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(niff, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(niff, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(b13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b15, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b19, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bc1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(be1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(153, 153, 153));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Meio Pagamento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        lbmb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (6).png"))); // NOI18N
        lbmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbmbMouseClicked(evt);
            }
        });

        lbdin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (8).png"))); // NOI18N
        lbdin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbdinMouseClicked(evt);
            }
        });

        lbmisto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (16).png"))); // NOI18N
        lbmisto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbmistoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbmisto)
                    .addComponent(lbmb)
                    .addComponent(lbdin))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbdin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lbmb)
                .addGap(27, 27, 27)
                .addComponent(lbmisto)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(153, 153, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impressão de Ticket", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        lbdinft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngegg.png"))); // NOI18N
        lbdinft.setBackground(new java.awt.Color(153, 153, 153));
        lbdinft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbdinftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbdinft, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbdinft, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane8.setViewportView(listpag);

        jLabel38.setText("Valor a Pagar");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        valcons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valcons.setText("999 €");
        valcons.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        valcons.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (13).png"))); // NOI18N
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(215, 215, 215)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valcons, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(114, 114, 114)
                        .addComponent(jLabel41)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 415, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(353, 353, 353))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel40))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(PTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(valcons))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel42)))))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout PagamentoLayout = new javax.swing.GroupLayout(Pagamento);
        Pagamento.setLayout(PagamentoLayout);
        PagamentoLayout.setHorizontalGroup(
            PagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PagamentoLayout.setVerticalGroup(
            PagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(153, 153, 153));

        jLabel39.setText("Transferência de Cartões");
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        jLabel43.setText("Transferir para:");
        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel44.setText("Transferir de:");
        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button (9).png"))); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addGap(193, 193, 193))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel39))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jLabel45)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel39)
                .addGap(198, 198, 198)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addComponent(jLabel45)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout transferLayout = new javax.swing.GroupLayout(transfer);
        transfer.setLayout(transferLayout);
        transferLayout.setHorizontalGroup(
            transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        transferLayout.setVerticalGroup(
            transferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(153, 153, 153));

        jButton24.setText("Camarotes");
        jButton24.setBackground(new java.awt.Color(51, 153, 255));
        jButton24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton27.setText("Pag. Cartão Extraviado");
        jButton27.setBackground(new java.awt.Color(51, 153, 255));
        jButton27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel47.setText("Menu Supervisor");
        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        retroceder_pass12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass12.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass12)
                .addGap(301, 301, 301)
                .addComponent(jLabel47)
                .addContainerGap(389, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(389, 389, 389)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(389, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel47))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass12)))
                .addContainerGap(682, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(209, 209, 209)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(141, 141, 141)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(209, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout MenuSuperLayout = new javax.swing.GroupLayout(MenuSuper);
        MenuSuper.setLayout(MenuSuperLayout);
        MenuSuperLayout.setHorizontalGroup(
            MenuSuperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuSuperLayout.setVerticalGroup(
            MenuSuperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(153, 153, 153));

        jButton29.setText("SOUND Direito");
        jButton29.setBackground(new java.awt.Color(51, 153, 255));
        jButton29.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setText("SOUND Esquerdo");
        jButton30.setBackground(new java.awt.Color(51, 153, 255));
        jButton30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setText("DANCE Esquerdo");
        jButton31.setBackground(new java.awt.Color(51, 153, 255));
        jButton31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setText("DANCE Direito");
        jButton32.setBackground(new java.awt.Color(51, 153, 255));
        jButton32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setText("DRINK Esquerdo");
        jButton33.setBackground(new java.awt.Color(51, 153, 255));
        jButton33.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setText("DRINK Trás");
        jButton34.setBackground(new java.awt.Color(51, 153, 255));
        jButton34.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton45.setText("DRINK Direito");
        jButton45.setBackground(new java.awt.Color(51, 153, 255));
        jButton45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton45.setForeground(new java.awt.Color(255, 255, 255));
        jButton45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jLabel48.setText("CAMAROTES");
        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        retroceder_pass13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass13.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass13)))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 148, Short.MAX_VALUE))))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel48))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
        );

        javax.swing.GroupLayout CamarotesLayout = new javax.swing.GroupLayout(Camarotes);
        Camarotes.setLayout(CamarotesLayout);
        CamarotesLayout.setHorizontalGroup(
            CamarotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CamarotesLayout.setVerticalGroup(
            CamarotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fPrivate.setMaximumSize(new java.awt.Dimension(1024, 768));
        fPrivate.setMinimumSize(new java.awt.Dimension(1024, 768));
        fPrivate.setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel20.setBackground(new java.awt.Color(51, 51, 51));
        jPanel20.setMaximumSize(new java.awt.Dimension(1024, 768));
        jPanel20.setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));

        jLabel46.setText("GESTÃO ENTRADAS COM PRÉ-PAGAMENTO");
        jLabel46.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));

        buttonGroup2.add(rbbguest);
        rbbguest.setSelected(true);
        rbbguest.setText("<html> &nbsp; &nbsp;Bilhete Guest<br><br> &nbsp;&nbsp;   </html>");
        rbbguest.setActionCommand("<html>Bilhete Guest<br> &nbsp;&nbsp; - Valor     25 € -   </html>");
        rbbguest.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        rbbguest.setBorderPainted(true);
        rbbguest.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        rbbguest.setForeground(new java.awt.Color(255, 153, 0));
        rbbguest.setName("blguest"); // NOI18N

        buttonGroup2.add(rbbreserva);
        rbbreserva.setText("<html> &nbsp; &nbsp;Bilhete Reserva<br><br> &nbsp; &nbsp;Valor     20 €    </html>");
        rbbreserva.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        rbbreserva.setBorderPainted(true);
        rbbreserva.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        rbbreserva.setForeground(new java.awt.Color(255, 153, 0));
        rbbreserva.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rbbreserva.setName("blreserva"); // NOI18N
        rbbreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbbreservaActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbbvip);
        rbbvip.setText("<html> &nbsp; &nbsp;Bilhete V.I.P.<br><br> &nbsp;&nbsp;  Valor    50 €    </html>");
        rbbvip.setActionCommand("<html> &nbsp; &nbsp;Bilhete V.I.P.<br><br> &nbsp;&nbsp; - Valor    40 € -   </html>");
        rbbvip.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        rbbvip.setBorderPainted(true);
        rbbvip.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        rbbvip.setForeground(new java.awt.Color(255, 153, 0));
        rbbvip.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rbbvip.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rbbvip.setName("blvip"); // NOI18N

        buttonGroup2.add(rbbdia);
        rbbdia.setText("<html> &nbsp; &nbsp;Bilhete Dia<br><br> &nbsp;&nbsp; Valor     25 €    </html>");
        rbbdia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        rbbdia.setBorderPainted(true);
        rbbdia.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        rbbdia.setForeground(new java.awt.Color(255, 153, 0));
        rbbdia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rbbdia.setName("bldia"); // NOI18N

        jLabel53.setText("SAIR");
        jLabel53.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel53))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(rbbreserva, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(rbbguest, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(rbbdia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(rbbvip, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rbbdia, rbbguest, rbbreserva, rbbvip});

        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel53)
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rbbreserva, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbbguest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbbdia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbbvip, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel21Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rbbdia, rbbguest, rbbreserva, rbbvip});

        jPanel25.setBackground(new java.awt.Color(51, 51, 51));

        jPanel42.setBackground(new java.awt.Color(51, 51, 51));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NIF", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Semibold", 0, 24), new java.awt.Color(255, 153, 0))); // NOI18N

        niff2.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        niff2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        niff2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        niff2.setCaretColor(new java.awt.Color(255, 255, 255));

        b40.setText("1");
        b40.setBackground(new java.awt.Color(51, 51, 51));
        b40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b40.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b40.setForeground(new java.awt.Color(51, 51, 51));
        b40.setToolTipText("");
        b40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b40MouseClicked(evt);
            }
        });
        b40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b40ActionPerformed(evt);
            }
        });

        b41.setText("2");
        b41.setBackground(new java.awt.Color(51, 51, 51));
        b41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b41.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b41.setForeground(new java.awt.Color(255, 255, 255));
        b41.setToolTipText("");
        b41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b41MouseClicked(evt);
            }
        });
        b41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b41ActionPerformed(evt);
            }
        });

        b42.setText("3");
        b42.setActionCommand("1");
        b42.setBackground(new java.awt.Color(51, 51, 51));
        b42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b42.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b42.setForeground(new java.awt.Color(255, 255, 255));
        b42.setToolTipText("");
        b42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b42MouseClicked(evt);
            }
        });
        b42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b42ActionPerformed(evt);
            }
        });

        b43.setText("6");
        b43.setBackground(new java.awt.Color(51, 51, 51));
        b43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b43.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b43.setForeground(new java.awt.Color(255, 255, 255));
        b43.setToolTipText("");
        b43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b43MouseClicked(evt);
            }
        });
        b43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b43ActionPerformed(evt);
            }
        });

        b44.setText("5");
        b44.setBackground(new java.awt.Color(51, 51, 51));
        b44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b44.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b44.setForeground(new java.awt.Color(255, 255, 255));
        b44.setToolTipText("");
        b44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b44MouseClicked(evt);
            }
        });
        b44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b44ActionPerformed(evt);
            }
        });

        b45.setText("4");
        b45.setBackground(new java.awt.Color(51, 51, 51));
        b45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b45.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b45.setForeground(new java.awt.Color(255, 255, 255));
        b45.setToolTipText("");
        b45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b45MouseClicked(evt);
            }
        });
        b45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b45ActionPerformed(evt);
            }
        });

        b46.setText("7");
        b46.setBackground(new java.awt.Color(51, 51, 51));
        b46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b46.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b46.setForeground(new java.awt.Color(255, 255, 255));
        b46.setToolTipText("");
        b46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b46MouseClicked(evt);
            }
        });
        b46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b46ActionPerformed(evt);
            }
        });

        b47.setText("8");
        b47.setBackground(new java.awt.Color(51, 51, 51));
        b47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b47.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b47.setForeground(new java.awt.Color(255, 255, 255));
        b47.setToolTipText("");
        b47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b47MouseClicked(evt);
            }
        });
        b47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b47ActionPerformed(evt);
            }
        });

        b48.setText("9");
        b48.setBackground(new java.awt.Color(51, 51, 51));
        b48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b48.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b48.setForeground(new java.awt.Color(255, 255, 255));
        b48.setToolTipText("");
        b48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b48MouseClicked(evt);
            }
        });
        b48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b48ActionPerformed(evt);
            }
        });

        be4.setText("E");
        be4.setBackground(new java.awt.Color(51, 51, 51));
        be4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        be4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        be4.setForeground(new java.awt.Color(255, 255, 255));
        be4.setToolTipText("");
        be4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                be4MouseClicked(evt);
            }
        });
        be4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                be4ActionPerformed(evt);
            }
        });

        b49.setText("0");
        b49.setBackground(new java.awt.Color(51, 51, 51));
        b49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        b49.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b49.setForeground(new java.awt.Color(255, 255, 255));
        b49.setToolTipText("");
        b49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b49MouseClicked(evt);
            }
        });
        b49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b49ActionPerformed(evt);
            }
        });

        bc5.setText("C");
        bc5.setBackground(new java.awt.Color(51, 51, 51));
        bc5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 2));
        bc5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        bc5.setForeground(new java.awt.Color(255, 255, 255));
        bc5.setToolTipText("");
        bc5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bc5MouseClicked(evt);
            }
        });
        bc5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bc5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(niff2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addComponent(bc5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b49, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b46, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b47, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(be4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(b42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b48, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(niff2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(b43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b45, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b44, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b46, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b48, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b49, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bc5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(be4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAIS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Semibold", 1, 18), new java.awt.Color(255, 153, 0))); // NOI18N

        jLabel49.setText("GUEST ");
        jLabel49.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 153, 0));

        jLabel50.setText("BILHETE DIA ");
        jLabel50.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 153, 0));

        jLabel51.setText("RESERVA");
        jLabel51.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 153, 0));

        jLabel52.setText("V.I.P.");
        jLabel52.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 153, 0));

        ttGuest.setText("999");
        ttGuest.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ttGuest.setForeground(new java.awt.Color(255, 255, 255));

        ttReserva.setText("999");
        ttReserva.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ttReserva.setForeground(new java.awt.Color(255, 255, 255));

        ttDia.setText("999");
        ttDia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ttDia.setForeground(new java.awt.Color(255, 255, 255));

        ttVip.setText("999");
        ttVip.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ttVip.setForeground(new java.awt.Color(255, 255, 255));

        jLabel57.setText("TOTAL ENTRADAS");
        jLabel57.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 153, 0));

        ttTotal.setText("999");
        ttTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ttTotal.setForeground(new java.awt.Color(255, 255, 255));

        jLabel69.setText("TicketLine");
        jLabel69.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 153, 0));

        ttTicketLine.setText("999");
        ttTicketLine.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ttTicketLine.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttTicketLine, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttVip, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttDia, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(ttGuest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(ttReserva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(ttDia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(ttVip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(ttTicketLine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(ttTotal))
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(51, 51, 51));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ANÁLISE TEMPO REAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Semibold", 1, 18), new java.awt.Color(255, 153, 0))); // NOI18N

        jLabel59.setText("GUEST ");
        jLabel59.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 153, 0));

        jLabel60.setText("BILHETE DIA ");
        jLabel60.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 153, 0));

        jLabel61.setText("RESERVA");
        jLabel61.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 153, 0));

        jLabel62.setText("V.I.P.");
        jLabel62.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 153, 0));

        trGuest.setText("999");
        trGuest.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        trGuest.setForeground(new java.awt.Color(255, 255, 255));

        trReserva.setText("999");
        trReserva.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        trReserva.setForeground(new java.awt.Color(255, 255, 255));

        trDia.setText("999");
        trDia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        trDia.setForeground(new java.awt.Color(255, 255, 255));

        trVip.setText("999");
        trVip.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        trVip.setForeground(new java.awt.Color(255, 255, 255));

        jLabel67.setText("LOTAÇÃO ATUAL");
        jLabel67.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 153, 0));

        trLTot.setText("999");
        trLTot.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        trLTot.setForeground(new java.awt.Color(255, 255, 255));

        jLabel71.setText("TicketLine");
        jLabel71.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 153, 0));

        trTL.setText("999");
        trTL.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        trTL.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trTL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(trLTot, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trVip, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trDia, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(trGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(trGuest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(trReserva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(trDia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(trVip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(trTL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(trLTot))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(51, 51, 51));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGAMENTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Semibold", 1, 20), new java.awt.Color(255, 153, 0))); // NOI18N

        jButton26.setText("DINHEIRO");
        jButton26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 22)); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton28.setText("MULTIBANCO");
        jButton28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 22)); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(51, 51, 51));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TicketLine", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Semibold", 1, 24), new java.awt.Color(255, 153, 0))); // NOI18N

        jButton3.setText("REGISTO ");
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel73.setText("Software de Gestão de Entradas em Modo Pré Pagamento | Faturação emitida através de api programática Vendus. Software certificado com o Nº 2230 ");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout fPrivateLayout = new javax.swing.GroupLayout(fPrivate);
        fPrivate.setLayout(fPrivateLayout);
        fPrivateLayout.setHorizontalGroup(
            fPrivateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        fPrivateLayout.setVerticalGroup(
            fPrivateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(POSMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SplashScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Artigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(familias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Ivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PasswordPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(series, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(listanegra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Fechos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exploracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Pagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(transfer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(MenuSuper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(Camarotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fPrivate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(POSMain, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SplashScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Artigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(familias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Ivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PasswordPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(series, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(listanegra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Fechos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exploracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Pagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(transfer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(MenuSuper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(Camarotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fPrivate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
      POSMain.hide();
      PasswordPrompt.show();
      senha.grabFocus();
      
      /*  model.addElement("5€   | Super Bock");
    itemadded.setModel(model);*/
    }//GEN-LAST:event_jLabel2MouseClicked

    
    
    public void realtimepp(){
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            String lbDia= "Dia";
            String lbReserva= "Reserva";
            String lbVip = "VIP";
            String lbTL= "TicketLine";
            String lbGuest="Guest";
            int ppDia=0;
            int ppVip=0;
            int ppReserva=0;
            int ppTL=0;
            int ppGuest = 0;
            
            String count = "SELECT T.ticket_type,  COUNT(*) FROM (SELECT  ticket_type from entradas_private where ticket_type like 'Reserva' and entradas_private.saida like '0' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'Dia' and entradas_private.saida like '0' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'TicketLine' and entradas_private.saida like '0' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'Guest' and entradas_private.saida like '0' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'VIP' and entradas_private.saida like '0') as T group by T.ticket_type order by T.ticket_type";
            
            
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
           
           while (count_ResultSet.next()){
               
              if (count_ResultSet.getString(1).equals(lbDia)){
                  
                  ppDia=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbReserva)){
                  
                  ppReserva=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbVip)){
                  
                  ppVip=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbTL)){
                  
                  ppTL=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbGuest)){
                  
                  ppGuest=count_ResultSet.getInt(2);
              } 
            
                
            //peop=count_ResultSet.getInt(1);
            
             
            }

        trReserva.setText(String.valueOf(ppReserva));
        trDia.setText(String.valueOf(ppDia));
        trVip.setText(String.valueOf(ppVip));
        trGuest.setText(String.valueOf(ppGuest));
        trTL.setText(String.valueOf(ppTL));
        
        int totalpp = ppReserva+ppDia+ppVip+ppGuest+ppTL;
        
        trLTot.setText(String.valueOf(totalpp));
        
        

        }catch(SQLException ex){
             System.out.println(ex);
        }
        
        
        
        
        
        
        
    }
    
    
    public void getrealtdata(){
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            String lbDia= "Dia";
            String lbReserva= "Reserva";
            String lbVip = "VIP";
            String lbTL= "TicketLine";
            String lbGuest="Guest";
            int ppDia=0;
            int ppVip=0;
            int ppReserva=0;
            int ppTL=0;
            int ppGuest = 0;
            
            String count = "SELECT T.ticket_type, COUNT(*) FROM (SELECT  ticket_type from entradas_private where ticket_type like 'Reserva' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'Dia' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'TicketLine' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'Guest' UNION ALL SELECT ticket_type FROM entradas_private where ticket_type like 'VIP') as T group by T.ticket_type order by T.ticket_type";
            
          
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
       
         
           while (count_ResultSet.next()){
               
              if (count_ResultSet.getString(1).equals(lbDia)){
                  
                  ppDia=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbReserva)){
                  
                  ppReserva=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbVip)){
                  
                  ppVip=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbTL)){
                  
                  ppTL=count_ResultSet.getInt(2);
              } 
               if (count_ResultSet.getString(1).equals(lbGuest)){
                  
                  ppGuest=count_ResultSet.getInt(2);
              } 
            
                
            //peop=count_ResultSet.getInt(1);
            
             
            }

        ttReserva.setText(String.valueOf(ppReserva));
        ttDia.setText(String.valueOf(ppDia));
        ttVip.setText(String.valueOf(ppVip));
        ttGuest.setText(String.valueOf(ppGuest));
        ttTicketLine.setText(String.valueOf(ppTL));
        
        int totalpp = ppReserva+ppDia+ppVip+ppGuest+ppTL;
        
        ttTotal.setText(String.valueOf(totalpp));
        
        

        }catch(SQLException ex){
             System.out.println(ex);
        }
        
        
        
        
        
        
    }
    
    
    public void write_init(String card){
    
     try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into card_list (Data,cartao,init,Pago,offer) values (?,?,?,?,?)";
       
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setInt (2,Integer.valueOf(card));
                 preparedStmt2.setDouble (3,1);
                 preparedStmt2.setDouble (4,0);
                  preparedStmt2.setInt (5,0); 
                 
                 
                 
                 
                 preparedStmt2.execute();
        con.close();
        }catch(SQLException ex){
        }
    }
    
    
    
    private void btcervejasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcervejasActionPerformed
      
        System.out.println(btcervejas.getName());
        pnart.removeAll();
        
        carregaart(btcervejas.getName());
    }//GEN-LAST:event_btcervejasActionPerformed

    private void btvodkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btvodkaActionPerformed
        System.out.println(btvodka.getName());
        pnart.removeAll();
        carregaart(btvodka.getName());
    }//GEN-LAST:event_btvodkaActionPerformed

    
    
    public static java.sql.Timestamp convertJavaDateToSqlDate(java.util.Date date)
	{
		return new java.sql.Timestamp(date.getTime());
	} 
    
    
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

        
        model.removeAllElements();
        itemadded.setModel(model);
        Saldoaberto=0;
        POSMain.hide();
        SplashScreen.show();
        cardinput.setText("");
        cardinput.grabFocus();
        saldo.setText("SALDO -  €"); 
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void retroceder_pass3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass3MouseClicked
        Ivas.hide();
        Menu.show();

    }//GEN-LAST:event_retroceder_pass3MouseClicked

    private void retroceder_pass4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass4MouseClicked
       familias.hide();
       Menu.show();
    }//GEN-LAST:event_retroceder_pass4MouseClicked

    private void retroceder_pass5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass5MouseClicked
       Artigos.hide();
       Menu.show();
        txtcod.setText("");
      txtdesc.setText("");
      pvp1.setText("");
      pvp2.setText("0");
      pvp3.setText("0");
      txtdesc.setEditable(true);
      
      
    }//GEN-LAST:event_retroceder_pass5MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        
        
               try {
            String dbUrl7="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER7="sa";
            String SQLPASS7 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con7 = DriverManager.getConnection(dbUrl7,SQLUSER7,SQLPASS7);
            
            
            Statement count_Statement7 = con7.createStatement();
            
            String codat = txtcod.getText();
            
            String count7 = "SELECT * FROM artigos where codigo = '" + codat + "'";
        
            System.out.println(count7);
            
             
            ResultSet count_ResultSet7 = count_Statement7.executeQuery(count7);
            
           
           if(count_ResultSet7.next()){
               
               
                  try{

           String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            String query = " UPDATE artigos SET Descricao=?,pvp1=?,pvp2=?,pvp3=?,iva=?,familia=? WHERE codigo='" + codat+ "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, txtdesc.getText());
           preparedStmt.setDouble (2, Double.valueOf(pvp1.getText()));
            preparedStmt.setDouble (3, Double.valueOf(pvp2.getText()));
             preparedStmt.setDouble (4, Double.valueOf(pvp3.getText()));
              preparedStmt.setInt (5, Integer.valueOf(jcivas.getModel().getSelectedItem().toString()));
               preparedStmt.setString (6,jcfamilia.getModel().getSelectedItem().toString());
            preparedStmt.execute();
            con.close();
            
        }catch(SQLException ex){
            System.out.println(ex);
             AutoDismiss.showOptionDialog(rootPane, "ERRO ao  Atualizar Artigo", "ATENÇÃO" ,3000);
        }
        AutoDismiss.showOptionDialog(rootPane, "Artigo Atualizado com Sucesso", "ATENÇÃO" ,3000);
       
               
           }else{
               
               
               
        
    
     try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into artigos (codigo,Descricao,pvp1,pvp2,pvp3,iva,familia) values (?,?,?,?,?,?,?)";
        
    
        
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setInt (1, Integer.valueOf(txtcod.getText()));
                 preparedStmt2.setString (2,txtdesc.getText());
                 preparedStmt2.setDouble (3,Double.valueOf(pvp1.getText()));
                 preparedStmt2.setDouble (4,Double.valueOf(pvp2.getText()));
                 preparedStmt2.setDouble (5,Double.valueOf(pvp3.getText()));
                 preparedStmt2.setInt (6, Integer.valueOf(jcivas.getSelectedItem().toString()));
                 preparedStmt2.setString (7, (jcfamilia.getSelectedItem().toString()));
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
      AutoDismiss.showOptionDialog(rootPane, "ERRO ao  Criar Artigo", "ATENÇÃO" ,3000);
        }
     
     
      AutoDismiss.showOptionDialog(rootPane, "Artigo Criado com Sucesso", "ATENÇÃO" ,3000);
      txtcod.setText("");
      txtdesc.setText("");
      pvp1.setText("");
      pvp2.setText("0");
      pvp3.setText("0");
      txtdesc.setEditable(true);
      
      
        
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT MAX(codigo) FROM artigos";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
                 
                
            int cod = Integer.valueOf(count_ResultSet.getString(1));
            cod = cod + 1;
            
            txtcod.setText(String.valueOf(cod));    
                System.out.println(cod);
             
            }

        

        }catch(SQLException ex){
        }
       
               
               
           }

        

        }catch(SQLException ex){
        }
       
        
        
        
        
        
        
        
      
      
      
    }//GEN-LAST:event_jButton9ActionPerformed

    
    
    String codigo="";
    String codigon="";
    private void jcfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcfamiliaActionPerformed
       
        
       
    }//GEN-LAST:event_jcfamiliaActionPerformed

    private void jcfamiliaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcfamiliaMouseClicked
      
      
    }//GEN-LAST:event_jcfamiliaMouseClicked

    private void jcivasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcivasMouseClicked
     
    }//GEN-LAST:event_jcivasMouseClicked

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked

        senha.setText(senha.getText()+ "*");
        codigo += "1";
    }//GEN-LAST:event_b1MouseClicked

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void b2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b2MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "2";
    }//GEN-LAST:event_b2MouseClicked

    private void b3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b3MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "3";
    }//GEN-LAST:event_b3MouseClicked

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void b6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b6MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "6";
    }//GEN-LAST:event_b6MouseClicked

    private void b5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b5MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "5";
    }//GEN-LAST:event_b5MouseClicked

    private void b4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b4MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "4";
    }//GEN-LAST:event_b4MouseClicked

    private void b7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b7MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "7";
    }//GEN-LAST:event_b7MouseClicked

    private void b8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b8MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "8";
    }//GEN-LAST:event_b8MouseClicked

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b8ActionPerformed

    private void b9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b9MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "9";
    }//GEN-LAST:event_b9MouseClicked

    private void beMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beMouseClicked

    
        System.out.println(codigo);

        if("999".equals(codigo)){
          
            System.exit(1);
            return;
        }

         try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM funcionarios WHERE codigo = '" + codigo + "' OR cartao = '" + codigo + "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
             Menu.show();
             PasswordPrompt.hide();
                
            }
          

        }catch(SQLException ex){
            System.out.println(ex);
        }
    
        senha.setText("");
        codigo="";
    }//GEN-LAST:event_beMouseClicked

    private void b0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b0MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "0";
    }//GEN-LAST:event_b0MouseClicked

    private void bcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bcMouseClicked
        codigo = "";
        senha.setText("");
    }//GEN-LAST:event_bcMouseClicked

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
       Menu.hide();
       Artigos.show();
       jcfamilia.removeAllItems();
        jcivas.removeAllItems();
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM familias";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            jcfamilia.addItem(count_ResultSet.getString(2));
                
                
                
             
            }

        

        }catch(SQLException ex){
        }
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM ivas";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            jcivas.addItem(count_ResultSet.getString(2));
                
                
                
             
            }

        

        }catch(SQLException ex){
        }
      
        
          
          
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT MAX(codigo) FROM artigos";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
                 
                
            int cod = Integer.valueOf(count_ResultSet.getString(1));
            cod = cod + 1;
            
            txtcod.setText(String.valueOf(cod));    
                System.out.println(cod);
             
            }

        

        }catch(SQLException ex){
        }
       
             try {
            String dbUrl5="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER5="sa";
            String SQLPASS5 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con5 = DriverManager.getConnection(dbUrl5,SQLUSER5,SQLPASS5);
            
            
            Statement count_Statement5 = con5.createStatement();
            
            
            
            String count5 = "SELECT * FROM artigos" ;
        
            System.out.println(count5);
            
             
            ResultSet count_ResultSet5 = count_Statement5.executeQuery(count5);
            
           
            while (count_ResultSet5.next()){
          
             modelart.addElement( count_ResultSet5.getString(1)+ " | " +count_ResultSet5.getString(2));    
          //  model.addElement(count_ResultSet.getString(8));
              listart.setModel(modelart);  
       //  return;
            }             
                    
                     }catch(SQLException ex){
        }
         
          
        POSMain.hide();
        Artigos.show();
        
        

       
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Menu.hide();
        familias.show();
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       Menu.hide();
       Ivas.show();
       
    }//GEN-LAST:event_jButton14ActionPerformed

    private void retroceder_pass6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass6MouseClicked
       Menu.hide();
       POSMain.show();
       
    }//GEN-LAST:event_retroceder_pass6MouseClicked

    private void retroceder_pass7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass7MouseClicked
        PasswordPrompt.hide();
        POSMain.show();
    }//GEN-LAST:event_retroceder_pass7MouseClicked

    private void ctsnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctsnackActionPerformed
        pnart.removeAll();
        
        carregaart(ctsnack.getName());
    }//GEN-LAST:event_ctsnackActionPerformed

    private void btvinhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btvinhosActionPerformed
        pnart.removeAll();
        
        carregaart(btvinhos.getName());
    }//GEN-LAST:event_btvinhosActionPerformed

    private void btwiskyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btwiskyActionPerformed
        pnart.removeAll();
        
        carregaart(btwisky.getName());
    }//GEN-LAST:event_btwiskyActionPerformed

    private void btespumantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btespumantesActionPerformed
        pnart.removeAll();
        
        carregaart(btespumantes.getName());
    }//GEN-LAST:event_btespumantesActionPerformed

    private void btginsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btginsActionPerformed
        pnart.removeAll();
        
        carregaart(btgins.getName());
    }//GEN-LAST:event_btginsActionPerformed

    private void btconhaquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btconhaquesActionPerformed
        pnart.removeAll();
        
        carregaart(btconhaques.getName());
    }//GEN-LAST:event_btconhaquesActionPerformed

    private void btaguasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaguasActionPerformed
        pnart.removeAll();
        
        carregaart(btaguas.getName());
    }//GEN-LAST:event_btaguasActionPerformed

    private void btcocktailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcocktailsActionPerformed
        pnart.removeAll();
        
        carregaart(btcocktails.getName());
    }//GEN-LAST:event_btcocktailsActionPerformed

    private void bttonicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttonicasActionPerformed
        pnart.removeAll();
        
        carregaart(bttonicas.getName());
    }//GEN-LAST:event_bttonicasActionPerformed

    private void btcafetariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcafetariaActionPerformed
        pnart.removeAll();
        
        carregaart(btcafetaria.getName());
    }//GEN-LAST:event_btcafetariaActionPerformed

    private void btsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsumosActionPerformed
        pnart.removeAll();
        
        carregaart(btsumos.getName());
    }//GEN-LAST:event_btsumosActionPerformed

    private void btbebidasbrancasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbebidasbrancasActionPerformed
        pnart.removeAll();
        
        carregaart(btbebidasbrancas.getName());
    }//GEN-LAST:event_btbebidasbrancasActionPerformed

    private void retroceder_pass8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass8MouseClicked
       series.hide();
       Menu.show();
       
    }//GEN-LAST:event_retroceder_pass8MouseClicked

    private void retroceder_pass9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass9MouseClicked
       listanegra.hide();
       Menu.show();
       
    }//GEN-LAST:event_retroceder_pass9MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       Menu.hide();
       series.show();
       
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
       Menu.hide();
       listanegra.show();
       
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
System.exit(0);        
        
   
        
        
    }//GEN-LAST:event_jLabel4MouseClicked
String cardno="";
    private void cardinputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardinputActionPerformed
       
    
       
    }//GEN-LAST:event_cardinputActionPerformed
String Cartaoativo="";


  


    private void cardinputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardinputKeyTyped
      
        
       
        
        
        
        
    }//GEN-LAST:event_cardinputKeyTyped

    
    public void carregaartigos(String cat){
        Saldoaberto=0;
         try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM movimentos WHERE cartao = '" + cat+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
               int idd = count_ResultSet.getInt(10);
               int size =  itemadded.getModel().getSize();
             size = size + 1;
            model.addElement(size + " | " + count_ResultSet.getString(8));    
          //  model.addElement(count_ResultSet.getString(8));
            itemadded.setModel(model);   
            
            Saldoartigo =   Double.valueOf(count_ResultSet.getDouble(3));
            Saldoaberto = Saldoaberto+Saldoartigo;
                  
            
            updateartigo(size,idd);
            
            
            
            
            
            
            }
             saldo.setText("SALDO - "+Saldoaberto+" €"); 
            
         } 
            catch(SQLException ex){
        }
     
      
        
        
    }
    
    
    
    public void updateartigo(int siz,int idt){
        
        
        
          try{

           String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            String query = " UPDATE movimentos SET nlanc=? WHERE id='" + idt+ "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, siz);
          
            preparedStmt.execute();
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        
        
    }
    private void btndelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelActionPerformed
     
        int selectedIndex = itemadded.getSelectedIndex();
       if (selectedIndex != -1) {
           
       String na = itemadded.getSelectedValue();
       String s="";
       
       
    //   String clean = na.replaceAll("\\D+","");
      Pattern p = Pattern.compile("(\\d+)");
      Matcher m = p.matcher(na);
      
      
      
      m.find();
      
      System.out.println(m.group(0)+"este");
      s=(m.group(0));
      
      // int i = Integer.parseInt(clean);
    //   System.out.println(s);
       
       model.remove(selectedIndex);
    
        try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM movimentos WHERE nlanc = '" + s + "' AND cartao ='"+Cartaoativo+"'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            
             
            
            Saldoartigo =   Double.valueOf(count_ResultSet.getDouble(3));
            Saldoaberto = Saldoaberto-Saldoartigo;
            saldo.setText("SALDO - "+Saldoaberto+" €");        
            
            
            
            }
            
            
         } 
            catch(SQLException ex){
        }
     
      
       
       
       
            try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "delete from movimentos WHERE nlanc = '" + s + "' AND cartao ='"+Cartaoativo+"'";
            
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }
      
    
               
    
    
}
        
    }//GEN-LAST:event_btndelActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
          
        Menu.hide();
        Fechos.show();
        
        
        DefaultTableModel model = new DefaultTableModel(new String[]{"Data", "Valor","Cartão","Artigo ","Descrição","POS"}, 0);

        try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM movimentos";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
            while (rs.next()){
            
                String data = dateFormat.format(rs.getTimestamp(1));

              
                String valor = String.valueOf(rs.getDouble(3))+" €";

                String cartao = String.valueOf(rs.getInt(4));

                String Artigo = String.valueOf(rs.getInt(5));

                String Descrição = rs.getString(8);
                
                String POS =  String.valueOf(rs.getInt(7));
                

                model.addRow(new Object[]{ data, valor, cartao,Artigo,Descrição,POS }) ;

            }
            table.setFillsViewportHeight(true);
            table.setModel(model);

            rs.close();
           

        }catch(SQLException ex){
        }
        
        double ttab=0;
        double ttfe=0;
        double ttto=0;
        
         try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "select sum(valor) from  movimentos";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
           
            while (rs.next()){
                
              ttab = rs.getDouble(1);

                ttab = ttab *100;

               ttab = Math.round(ttab);
                ttab = ttab /100;

                ttaberto.setText(String.valueOf(ttab)+ " EUR");
                
            }
      
          }catch(SQLException ex){
        }
         
       
             
         try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "select sum(valor) from  movimentos_gerais where periodo =0";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
           
            while (rs.next()){
                
                 ttfe = rs.getDouble(1);

                ttfe = ttfe *100;

                ttfe = Math.round(ttfe);
                ttfe = ttfe /100;

                ttfechado.setText(String.valueOf(ttfe)+ " EUR");
                
            }
      
          }catch(SQLException ex){
        }
         
       
         ttto = ttab+ttfe;
          tttotal.setText(String.valueOf(ttto)+ " EUR");
                
        

    }//GEN-LAST:event_jButton22ActionPerformed

    private void retroceder_pass10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass10MouseClicked
       Fechos.hide();
       Menu.show();
       
               
    }//GEN-LAST:event_retroceder_pass10MouseClicked

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
       
        String envio="";
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "select sum(valor) from  movimentos where cartao = '" + Cartaoativo+ "'";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
           
            while (rs.next()){
                
                 double totalf = rs.getDouble(1);

                totalf = totalf *100;

                totalf = Math.round(totalf);
                totalf = totalf /100;

                envio= String.valueOf(totalf);
                
            }
      
          }catch(SQLException ex){
        }
           
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
              String queryy = "INSERT INTO movimentos_gerais (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) SELECT movimentos.data,movimentos.ndoc,movimentos.valor,movimentos.cartao,movimentos.artigo,movimentos.funcionario,movimentos.POS,movimentos.Descricao,movimentos.nlanc  FROM movimentos where cartao = '" + Cartaoativo+ "'";
            
        
         
                 PreparedStatement preparedStmt2 = con.prepareStatement(queryy);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }
         
          
           
       //  
          model.removeAllElements();
        
         try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5056)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             String vpp = envio + "#" + Cartaoativo;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
             
      
        POSMain.hide();
        SplashScreen.show();
        Cartaoativo="";
        cardinput.grabFocus();
        saldo.setText("SALDO -  €");       
                
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        String envio="";
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "select sum(valor) from  movimentos where cartao = '" + Cartaoativo+ "'";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
           
            while (rs.next()){
                
                 double totalf = rs.getDouble(1);

                totalf = totalf *100;

                totalf = Math.round(totalf);
                totalf = totalf /100;

                envio= String.valueOf(totalf);
                
            }
      
          }catch(SQLException ex){
        }
           
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
              String queryy = "INSERT INTO movimentos_gerais (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) SELECT movimentos.data,movimentos.ndoc,movimentos.valor,movimentos.cartao,movimentos.artigo,movimentos.funcionario,movimentos.POS,movimentos.Descricao,movimentos.nlanc  FROM movimentos where cartao = '" + Cartaoativo+ "'";
            
        
         
                 PreparedStatement preparedStmt2 = con.prepareStatement(queryy);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }
         
          
           
       //  model.removeAllElements();
        
        
         try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5054)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             String vpp = envio + "#" + Cartaoativo;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
             
        
        POSMain.hide();
        SplashScreen.show();
        Cartaoativo="";
        cardinput.grabFocus();
        model.removeAllElements();
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String envio="";
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "select sum(valor) from  movimentos where cartao = '" + Cartaoativo+ "'";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
           
            while (rs.next()){
                
                 double totalf = rs.getDouble(1);

                totalf = totalf *100;

                totalf = Math.round(totalf);
                totalf = totalf /100;

                envio= String.valueOf(totalf);
                
            }
      
          }catch(SQLException ex){
        }
           
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
              String queryy = "INSERT INTO movimentos_gerais (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc,periodo) SELECT movimentos.data,movimentos.ndoc,movimentos.valor,movimentos.cartao,movimentos.artigo,movimentos.funcionario,movimentos.POS,movimentos.Descricao,movimentos.nlanc,0  FROM movimentos where cartao = '" + Cartaoativo+ "'";
            
        
         
                 PreparedStatement preparedStmt2 = con.prepareStatement(queryy);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }
         
          
           try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5055)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             String vpp = envio + "#" + Cartaoativo;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
             
        
        POSMain.hide();
        SplashScreen.show();
        Cartaoativo="";
        cardinput.grabFocus();
        model.removeAllElements();
        

        
        
        
        

    }//GEN-LAST:event_jButton1ActionPerformed
double vmini=0;
    private void cardinputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardinputKeyPressed
      
         System.out.println(cardinput.getText()+"2");
        int key = evt.getKeyCode();
            if (key == KeyEvent.VK_ENTER){
                
               cardno=cardinput.getText();
        System.out.println(cardinput.getText()+"3");
      
          try {
                System.out.println(cardinput.getText()+"4");
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            java.security.Security.setProperty("jdk.tls.disabledAlgorithms","");
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"8");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                          System.out.println(ex+"6");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                           System.out.println(ex+"5");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
             System.out.println(cardinput.getText()+"5");
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM card_series WHERE '" + cardno+ "'>= serie_start AND'"   + cardno+ "' <=serie_end" ;
         
             String count5 = "SELECT * FROM card_list where cartao = " + cardno;
             
             String count3 = "SELECT * FROM funcionarios where cartao = " + cardno;
             
            System.out.println(count);
            
            
            
              ResultSet count_ResultSet3 = count_Statement.executeQuery(count3);
            
            if (count_ResultSet3.next()){
                
                SplashScreen.hide();
                MenuSuper.show();
                cardinput.setText("");
                return;
            }
            
            
             ResultSet count_ResultSet5 = count_Statement.executeQuery(count5);
            
            if(!count_ResultSet5.next()){
            AutoDismiss.showOptionDialog(rootPane, "Cartão NÃO INICIALIZADO - Dirija-se a Portaria", "ATENÇÃO" ,3000);
            cardinput.setText("");
            con.close();
            return;
            }    
            
            
              System.out.println(cardinput.getText()+"6");
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
            System.out.println(cardinput.getText()+"7");
            while (count_ResultSet.next()){
            String hascard=(count_ResultSet.getString(2));
            System.out.println(cardno);
             System.out.println(cardinput.getText()+"8");
                  
             
             
         SplashScreen.hide();
       Pagamento.show();
   
        
         cardinput.setText("");
         Cartaoativo=cardno;
         cardinfo.setText("Cartão - "+Cartaoativo);
       //  carregaartigos(Cartaoativo);
       carregaartpag(Cartaoativo);
        carregaval(Cartaoativo);
            
          
              System.out.println(cardinput.getText()+"6");
            
          
         plaf=(count_ResultSet.getString(4));
            

            double pf = Double.valueOf(plaf);
            if (pf>TTTotal){
                 vmini=pf-TTTotal;
                TTTotal=pf;
               
                
            }else{
                
                vmini=0;
            }
valcons.setText(TTTotal + " €");
            
                   
        
        return;
            }             
                    
                     }catch(SQLException ex){
                           System.out.println(ex+"4");
        }
       System.out.println("cartaao inexistente");
       AutoDismiss.showOptionDialog(rootPane, "Cartão Fora de Série", "ATENÇÃO" ,3000);
       System.out.println(cardinput.getText());
       cardinput.setText("");
       
       }
    }//GEN-LAST:event_cardinputKeyPressed
double TTTotal=0;


public void carregaartpag(String Card){
    
     Saldoaberto=0;
         try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM movimentos WHERE cartao = '" + Card+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
               int idd = count_ResultSet.getInt(10);
               int size =  itemadded.getModel().getSize();
             size = size + 1;
            model.addElement(size + " | " + count_ResultSet.getString(8));    
          //  model.addElement(count_ResultSet.getString(8));
            listpag.setModel(model);   
            
         //   Saldoartigo =   Double.valueOf(count_ResultSet.getDouble(3));
         //   Saldoaberto = Saldoaberto+Saldoartigo;
                  
            
         //   updateartigo(size,idd);
            
            
            
            
            
            
            }
           //  saldo.setText("SALDO - "+Saldoaberto+" €"); 
            
         } 
            catch(SQLException ex){
        }
     
      
        
    
}
    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    public void carregaval(String Card){
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "select sum(valor) from  movimentos where cartao = '" + Cartaoativo+ "'";
        
            System.out.println(count);
            
             
            ResultSet rs = count_Statement.executeQuery(count);
            
           
            if (rs.next()){
                
                 double totalf = rs.getDouble(1);

                totalf = totalf *100;

                totalf = Math.round(totalf);
                totalf = totalf /100;

                PTotal.setText(String.valueOf(totalf)+ " €");
                TTTotal = totalf;
            }else{
                PTotal.setText("Cartão sem movimentos pendentes");
                
                
            }
      
          }catch(SQLException ex){
        }
        
        
        
    }
    
    
    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
  try {
            String dbUrl2="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER2="sa";
            String SQLPASS2 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con2 = DriverManager.getConnection(dbUrl2,SQLUSER2,SQLPASS2);
            
            
            Statement count_Statement2 = con2.createStatement();
            
            
            
            String count2 = "SELECT * FROM movimentos";
        
            System.out.println(count2);
            
             
            ResultSet count_ResultSet2 = count_Statement2.executeQuery(count2);
            
           
            if (!count_ResultSet2.next()){
           
                updatemov();
                
            }else {
            
             AutoDismiss.showOptionDialog(rootPane, "Existem cartões abertos!!!", "ATENÇÃO" ,3000);
            }
                
             
            

        

        }catch(SQLException ex){
        }
            
        
 
        
        
      
        
    }//GEN-LAST:event_jButton41ActionPerformed

    private void b10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b10MouseClicked

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
         niff.setText(niff.getText()+ "1");
        codigon += "1";
    }//GEN-LAST:event_b10ActionPerformed

    private void b11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b11MouseClicked

    private void b12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b12MouseClicked

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
         niff.setText(niff.getText()+ "3");
        codigon += "3";
    }//GEN-LAST:event_b12ActionPerformed

    private void b13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b13MouseClicked

    private void b14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b14MouseClicked

    private void b15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b15MouseClicked

    private void b16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b16MouseClicked

    private void b17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b17MouseClicked

    private void b17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b17ActionPerformed
         niff.setText(niff.getText()+ "8");
        codigon += "8";
    }//GEN-LAST:event_b17ActionPerformed

    private void b18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b18MouseClicked

    private void be1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_be1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_be1MouseClicked

    private void b19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b19MouseClicked

    private void bc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bc1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bc1MouseClicked

    private void lbdinftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbdinftActionPerformed
  press = press+1;
      
      if(press!=1){
          return;
          
      }  
            if(extravio){
            
          
    cardno="18000";
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("998"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Extravio Cartao");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
            
            
        }
        if(Camarote){
    
    

    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("999"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Camarote");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
    
    
    
}         
        
        if(vmini!=0 && !Camarote && !extravio){
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
            System.out.println(count);
    
            java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,vmini);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("92"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setInt (7, Integer.valueOf(1));
                 preparedStmt2.setString (8,"Consumo Minimo");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
        }
             
        
         
                 try {
                System.out.println(cardinput.getText()+"4");
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            java.security.Security.setProperty("jdk.tls.disabledAlgorithms","");
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"8");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                          System.out.println(ex+"6");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                           System.out.println(ex+"5");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
             System.out.println(cardinput.getText()+"5");
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM card_series WHERE '" + cardno+ "'>= serie_start AND'"   + cardno+ "' <=serie_end" ;
        
            System.out.println(count);
            
              System.out.println(cardinput.getText()+"6");
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
            System.out.println(cardinput.getText()+"7");
            while (count_ResultSet.next()){
         plaf=(count_ResultSet.getString(4));
            }

            double pf = Double.valueOf(plaf);
            if (pf>TTTotal){
                
                TTTotal=pf;
            }

            
                     }catch(SQLException ex){
                           System.out.println(ex+"4");
        }
                 String nif="";
          if (!"".equals(niff.getText())){
              nif=niff.getText();
          }else{
              nif="0";
              
          }
              
           try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5056)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             String vpp = TTTotal + "#" + Cartaoativo+"#"+nif;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
             
        
   //     Pagamento.hide();
  //      SplashScreen.show();
    //    Cartaoativo="";
        cardinput.grabFocus();
        model.removeAllElements();
        

        
        
    }//GEN-LAST:event_lbdinftActionPerformed

    private void listartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listartMouseClicked
        
        
        
        String na = listart.getSelectedValue();
         String s="";
       
       
    //   String clean = na.replaceAll("\\D+","");
         Pattern p = Pattern.compile("(\\d+)");
         Matcher m = p.matcher(na);
      
      
      
         m.find();
      
         System.out.println(m.group(0)+"este");
         s=(m.group(0));
         
         
            try {
            String dbUrl5="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER5="sa";
            String SQLPASS5 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con5 = DriverManager.getConnection(dbUrl5,SQLUSER5,SQLPASS5);
            
            
            Statement count_Statement5 = con5.createStatement();
            
            
            
            String count5 = "SELECT * FROM artigos where codigo = '" + s + "'";
        
            System.out.println(count5);
            
             
            ResultSet count_ResultSet5 = count_Statement5.executeQuery(count5);
            
           
            while (count_ResultSet5.next()){
          
              txtcod.setText(count_ResultSet5.getString(1));
              txtdesc.setText(count_ResultSet5.getString(2));
               pvp1.setText(count_ResultSet5.getString(3));
              pvp2.setText(count_ResultSet5.getString(4));
              
             pvp3.setText(count_ResultSet5.getString(5));
             jcfamilia.getModel().setSelectedItem(count_ResultSet5.getString(7));
             jcivas.getModel().setSelectedItem(count_ResultSet5.getString(6));
              
                
                
                
                
                
                
                
            }             
                    
                     }catch(SQLException ex){
        }
         
         txtdesc.setEditable(false);
         
         
    }//GEN-LAST:event_listartMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       jcfamilia.removeAllItems();
        jcivas.removeAllItems();
        txtdesc.setText("");
        pvp1.setText("");
        pvp2.setText("");
        pvp3.setText("");
        txtdesc.setEditable(true);
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM familias";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            jcfamilia.addItem(count_ResultSet.getString(2));
                
                
                
             
            }

        

        }catch(SQLException ex){
        }
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM ivas";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            jcivas.addItem(count_ResultSet.getString(2));
                
                
                
             
            }

        

        }catch(SQLException ex){
        }
      
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT MAX(codigo) FROM artigos";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
                 
                
            int cod = Integer.valueOf(count_ResultSet.getString(1));
            cod = cod + 1;
            
            txtcod.setText(String.valueOf(cod));    
                System.out.println(cod);
             
            }

        

        }catch(SQLException ex){
        }
              
              
    }//GEN-LAST:event_jButton10ActionPerformed

    private void pvp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvp1ActionPerformed
        
       
    }//GEN-LAST:event_pvp1ActionPerformed

    private void pvp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvp1MouseClicked
       
       
    }//GEN-LAST:event_pvp1MouseClicked

    private void pvp2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvp2MouseClicked
     
       
    }//GEN-LAST:event_pvp2MouseClicked

    private void pvp3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvp3MouseClicked
       
       
    }//GEN-LAST:event_pvp3MouseClicked

    private void txtdescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdescMouseClicked
      
       
    }//GEN-LAST:event_txtdescMouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
      POSMain.hide();
      Pagamento.show();
      
    }//GEN-LAST:event_jLabel35MouseClicked
String plaf="";


int press=0;


    private void lbdinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdinMouseClicked

      press = press+1;
      
      if(press!=1){
          return;
          
      }  
        
        
        if(extravio){
            
          
    cardno="18000";
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("998"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Extravio Cartao");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
            
            
        }
        
        
        
        
        
        
        if(Camarote){
    
    
   
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("999"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Camarote");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
    
    
    
}
        
        
        if(vmini!=0 && !Camarote && !extravio){       
      
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,vmini);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("92"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setInt (7, Integer.valueOf(1));
                 preparedStmt2.setString (8,"Consumo Minimo");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
}

             try {
                System.out.println(cardinput.getText()+"4");
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            java.security.Security.setProperty("jdk.tls.disabledAlgorithms","");
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"8");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                          System.out.println(ex+"6");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                           System.out.println(ex+"5");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
             System.out.println(cardinput.getText()+"5");
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM card_series WHERE '" + cardno+ "'>= serie_start AND'"   + cardno+ "' <=serie_end" ;
        
            System.out.println(count);
            
              System.out.println(cardinput.getText()+"6");
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
            System.out.println(cardinput.getText()+"7");
            while (count_ResultSet.next()){
         plaf=(count_ResultSet.getString(4));
            }

            double pf = Double.valueOf(plaf);
            if (pf>TTTotal & pf!=0){
                
                TTTotal=pf;
            }
                            
            
                     }catch(SQLException ex){
                           System.out.println(ex+"4");
        }
           
       //  
          model.removeAllElements();
          String nif="";
          if (!"".equals(niff.getText())){
              nif=niff.getText();
          }else{
              nif="0";
              
          }
         try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5054)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             String vpp = String.valueOf(TTTotal);// + "#" + Cartaoativo+"#"+nif;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
             
      
     //   Pagamento.hide();
      //  SplashScreen.show();
        
   //     Cartaoativo="";
        cardinput.grabFocus();
        saldo.setText("SALDO -  €");       
                
    }//GEN-LAST:event_lbdinMouseClicked

    private void lbmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmbMouseClicked
  press = press+1;
      
      if(press!=1){
          return;
          
      }  
        
            if(extravio){
            
          
    cardno="18000";
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("998"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Extravio Cartao");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
            
            
        }
        if(Camarote){
    
    
  
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("999"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Camarote");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
    
    
    
}
        
        if(vmini!=0 && !Camarote && !extravio){
        
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,vmini);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("92"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setInt (7, Integer.valueOf(1));
                 preparedStmt2.setString (8,"Consumo Minimo");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
    
}   
        
                 try {
                System.out.println(cardinput.getText()+"4");
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            java.security.Security.setProperty("jdk.tls.disabledAlgorithms","");
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"8");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                          System.out.println(ex+"6");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                           System.out.println(ex+"5");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
             System.out.println(cardinput.getText()+"5");
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM card_series WHERE '" + cardno+ "'>= serie_start AND'"   + cardno+ "' <=serie_end" ;
        
            System.out.println(count);
            
              System.out.println(cardinput.getText()+"6");
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
            System.out.println(cardinput.getText()+"7");
            while (count_ResultSet.next()){
         plaf=(count_ResultSet.getString(4));
            }

            double pf = Double.valueOf(plaf);
            if (pf>TTTotal){
                
                TTTotal=pf;
            
            
            }

            
                     }catch(SQLException ex){
                           System.out.println(ex+"4");
        }
                 
          if (!"".equals(niff.getText())){
              fnif=niff.getText();
          }else{
              fnif="0";
              
          }
              
         	    
        
             
        
   //     Pagamento.hide();
   //     SplashScreen.show();
     //   Cartaoativo="";
    //    cardinput.grabFocus();
      //    model.removeAllElements();
        
         try {
             printfat(cardno, 81752629);
         } catch (ProtocolException ex) {
      System.out.println(ex);
         } catch (IOException ex) {
      System.out.println(ex + "2");
         }
        
        
    }//GEN-LAST:event_lbmbMouseClicked

    String fnif="";
    
     public void printfat(String Card,int paytypee) throws MalformedURLException, ProtocolException, IOException{
       StringBuilder sb = new StringBuilder(); 
                try {
           String dbUrl="jdbc:sqlserver://"+IPHOST+":1433;"
                    + "databaseName=Disco";
            
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                    
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
                     
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM movimentos Where cartao ='" + Card+ "'";
        
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
       //     System.out.println("sudo php /var/www/html/ftt.php " + c1+ "   '" + c2+ "' '" + c3+ "'  '" + c4+ "' '" + c5+ "' '" + c6+ "'");
            
            
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
        File file =new File("Y:\\Desktop/fter.php");
        FileWriter fw = new FileWriter(file,false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        SimpleDateFormat dffa = new SimpleDateFormat("yyyy-MM-dd");
        Date dffnowa = new Date();
        String fre = dffa.format(dffnowa);            
            
           pw.println("<?php");
          
           pw.println("$url     = 'https://www.vendus.pt/ws/v1.1/documents/';");
           pw.println("$apiKey  = 'c7d2a17a2ef22e701445f1df1884a41f';");
           pw.println("$method  = 'POST';");
           pw.println("$params  = array(");
           pw.println("'register_id'            => '0',");
           pw.println("'type'                   => 'FS',");
           pw.println("'output'                 => 'escpos',");
           pw.println(" 'return_qrcode'          => 1,");
           if(!"0".equals(fnif)){
           pw.println( "   'client'                 => array(");
           
           pw.println("'fiscal_id'          => '"+fnif+"', ");
      
           pw.println(" ),");
           }
            
           pw.println("'payments'               => array(");
         pw.println("array(");
            pw.println("'id'       => "+paytypee+","); 
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
                           Thread.sleep(1500);
                       }  catch (InterruptedException ex) {
        System.out.println("44"+ex);
                       }
              
              
              
              
              
              
 String url = ("http://" + "192.168.1.189" + ":" + "80" + "/fter.php");
 URL obj = new URL(url);
 HttpURLConnection con3 = (HttpURLConnection) obj.openConnection();
 
 con3.setRequestMethod("POST");
 con3.setRequestProperty("Accept-Language", "UTF-8");
 
 con3.setDoOutput(true);
 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con3.getOutputStream());
 outputStreamWriter.flush();
 
 int responseCode = con3.getResponseCode();
 System.out.println("Response Code : " + responseCode);
 
 
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
             System.out.println("23"+ ex);
        }   
                
        
        
        
    }
    
    
    
    
    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        niff.setText(niff.getText()+ "2");
        codigon += "2";
    }//GEN-LAST:event_b11ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
         niff.setText(niff.getText()+ "4");
        codigon += "4";
    }//GEN-LAST:event_b15ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
        niff.setText(niff.getText()+ "5");
        codigon += "5";
    }//GEN-LAST:event_b14ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
         niff.setText(niff.getText()+ "6");
        codigon += "6";
    }//GEN-LAST:event_b13ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
       niff.setText(niff.getText()+ "7");
        codigon += "7";
    }//GEN-LAST:event_b16ActionPerformed

    private void b18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b18ActionPerformed
        niff.setText(niff.getText()+ "9");
        codigon += "9";
    }//GEN-LAST:event_b18ActionPerformed

    private void b19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b19ActionPerformed
        niff.setText(niff.getText()+ "0");
        codigon += "0";
    }//GEN-LAST:event_b19ActionPerformed

    private void bc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc1ActionPerformed
         niff.setText("");
        codigon = "";
    }//GEN-LAST:event_bc1ActionPerformed

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        
        Pagamento.hide();
        SplashScreen.show();
            press=0;
        niff.setText("");
        cardinput.grabFocus();
        model.removeAllElements();
//        System.exit(0);
        Cartaoativo="";
        Camarote=false;
        extravio=false;
        vmini=0;
        
    }//GEN-LAST:event_jLabel40MouseClicked
public boolean apagamov(String Cartao){
     try {
            String dbUrl2="jdbc:sqlserver://"+IPHOSTM+":1433;"
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
            
            
            
              String queryy2 = "delete from movimentos where cartao = '" + Cartao+ "'";
        
         
                 PreparedStatement preparedStmt2 = con2.prepareStatement(queryy2);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
                 con2.close();
        return true;
        
        }catch(SQLException ex){
            
            System.out.println(ex);
     return false;
        }
     
    
    
    
}
    private void lbmistoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmistoMouseClicked
  press = press+1;
      
      if(press!=1){
          return;
          
      }  
        String s = JOptionPane.showInputDialog("Insira Valor em Dinheiro");
        
        
        
        
            if(extravio){
            
          
    cardno="18000";
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("998"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Extravio Cartao");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
            
            
        }
        if(Camarote){
    
    
  
    Cartaoativo=cardno;
       try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,TTTotal);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("999"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                preparedStmt2.setString (7, (POS));
                 preparedStmt2.setString (8,"Camarote");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
    
    
    
}
        
        if(vmini!=0 && !Camarote && !extravio){       
      
        
        
        
          try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,vmini);
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf("92"));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setInt (7, Integer.valueOf(1));
                 preparedStmt2.setString (8,"Consumo Minimo");
                 preparedStmt2.setInt (9, Integer.valueOf(1));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
        
         
}

             try {
                System.out.println(cardinput.getText()+"4");
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            java.security.Security.setProperty("jdk.tls.disabledAlgorithms","");
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"8");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                          System.out.println(ex+"6");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                           System.out.println(ex+"5");
                         Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
             System.out.println(cardinput.getText()+"5");
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM card_series WHERE '" + cardno+ "'>= serie_start AND'"   + cardno+ "' <=serie_end" ;
        
            System.out.println(count);
            
              System.out.println(cardinput.getText()+"6");
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
            System.out.println(cardinput.getText()+"7");
            while (count_ResultSet.next()){
         plaf=(count_ResultSet.getString(4));
            }

            double pf = Double.valueOf(plaf);
            if (pf>TTTotal & pf!=0){
                
                TTTotal=pf;
            }

            
                     }catch(SQLException ex){
                           System.out.println(ex+"4");
        }
           
             
             
              String nif="";
          if (!"".equals(niff.getText())){
              nif=niff.getText();
          }else{
              nif="0";
              
          }
       //  
          model.removeAllElements();
     
          double din = Double.valueOf(s);
          
          
          
         try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket ss = new Socket(ip, 5054)) {
             DataOutputStream dos = new DataOutputStream(ss.getOutputStream());
             String vpp = String.valueOf(din);
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
             
      	    
          try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket ssss = new Socket(ip, 5055)) {
             DataOutputStream dos = new DataOutputStream(ssss.getOutputStream());
             String vpp = TTTotal + "#" + Cartaoativo+"#"+nif;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
              
   ////    Pagamento.hide();
    //    SplashScreen.show();
        
    //    Cartaoativo="";
        cardinput.grabFocus();
        saldo.setText("SALDO -  €");       
    
        
        
        
        
        
    }//GEN-LAST:event_lbmistoMouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
System.out.println(Cartaoativo);
        

             try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
              String queryy = "INSERT INTO movimentos_gerais (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc,periodo) SELECT movimentos.data,movimentos.ndoc,movimentos.valor,movimentos.cartao,movimentos.artigo,movimentos.funcionario,movimentos.POS,movimentos.Descricao,movimentos.nlanc,0  FROM movimentos where cartao = '" + Cartaoativo+ "'";
            
        
         
                 PreparedStatement preparedStmt2 = con.prepareStatement(queryy);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }


        if(apagamov(Cartaoativo)){
       
        AutoDismiss.showOptionDialog(rootPane, "Aguarde Confirmação!!!", "ATENÇÃO" ,3000);
        
      //  Pagamento.hide();
      //  SplashScreen.show();
        niff.setText("");
        cardinput.grabFocus();
        model.removeAllElements();
        updatepago(Cartaoativo);
        
        
      
      
       
       AutoDismiss.showOptionDialog(rootPane, "Cartão Liquidado!!!", "ATENÇÃO" ,3000);
       
        Pagamento.hide();
        SplashScreen.show();
        press=0;
        niff.setText("");
        cardinput.grabFocus();
        model.removeAllElements();
        
        Cartaoativo="";
        Camarote=false;
        extravio=false;
        vmini=0;
        TTTotal=0;
        
     //  System.exit(0);
       }else{
           
      AutoDismiss.showOptionDialog(rootPane, "Erro ao Liquidar Cartão!!!", "ATENÇÃO" ,3000);
           
           
       }    
    }//GEN-LAST:event_jLabel42MouseClicked
public void updatepago(String card){
    
    
     try{

           String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            String query = " UPDATE card_list SET Pago=?  WHERE cartao ='" + card+ "' " ;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 1);
          
            preparedStmt.execute();
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        
    
}
    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        MenuSuper.hide();
        Camarotes.show();

    }//GEN-LAST:event_jButton24ActionPerformed

    private void retroceder_pass12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass12MouseClicked
        MenuSuper.hide();
        SplashScreen.show();
       // ative=false;
        cardinput.setText("");
        cardinput.grabFocus();
    }//GEN-LAST:event_retroceder_pass12MouseClicked

    private void retroceder_pass13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass13MouseClicked
        Camarotes.hide();
        MenuSuper.show();
    }//GEN-LAST:event_retroceder_pass13MouseClicked

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
         
       TTTotal=450;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="10000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
       Camarote=true;
      
         
         
         
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
         TTTotal=450;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="11000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
             Camarote=true;
      
        
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        TTTotal=350;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="12000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
             Camarote=true;
        
        
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
          TTTotal=350;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="13000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
             Camarote=true;
     
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
         TTTotal=250;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="14000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
             Camarote=true;
      
        
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        TTTotal=250;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="15000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
    Camarote=true;
    }//GEN-LAST:event_jButton34ActionPerformed
boolean Camarote = false;
    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        TTTotal=250;
                 
       Camarotes.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="16000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
        Camarote=true;
     
        
    }//GEN-LAST:event_jButton45ActionPerformed
boolean extravio=false;
    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
       
         TTTotal=250;
                 
       MenuSuper.hide();
       Pagamento.show();
       cardinput.setText("");
       //  Cartaoativo=cardno;
       cardno="17000";
       valcons.setText( TTTotal+" €");
       PTotal.setText("0 €");  
        //Camarote=true;
        extravio=true;
       
        
        
    }//GEN-LAST:event_jButton27ActionPerformed
String codigon2;
    private void b40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b40MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b40MouseClicked

    private void b40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b40ActionPerformed
        niff2.setText(niff2.getText()+ "1");
        codigon2 += "1";
    }//GEN-LAST:event_b40ActionPerformed

    private void b41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b41MouseClicked

    private void b41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b41ActionPerformed
        niff2.setText(niff2.getText()+ "2");
        codigon2 += "2";
    }//GEN-LAST:event_b41ActionPerformed

    private void b42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b42MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b42MouseClicked

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
       niff2.setText(niff2.getText()+ "3");
        codigon2 += "3";
    }//GEN-LAST:event_b42ActionPerformed

    private void b43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b43MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b43MouseClicked

    private void b43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b43ActionPerformed
        niff2.setText(niff2.getText()+ "6");
        codigon2 += "6";
    }//GEN-LAST:event_b43ActionPerformed

    private void b44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b44MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b44MouseClicked

    private void b44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b44ActionPerformed
         niff2.setText(niff2.getText()+ "5");
        codigon2 += "5";
    }//GEN-LAST:event_b44ActionPerformed

    private void b45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b45MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b45MouseClicked

    private void b45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b45ActionPerformed
        niff2.setText(niff2.getText()+ "4");
        codigon2 += "4";
    }//GEN-LAST:event_b45ActionPerformed

    private void b46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b46MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b46MouseClicked

    private void b46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b46ActionPerformed
        niff2.setText(niff2.getText()+ "7");
        codigon2 += "7";
    }//GEN-LAST:event_b46ActionPerformed

    private void b47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b47MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b47MouseClicked

    private void b47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b47ActionPerformed
         niff2.setText(niff2.getText()+ "8");
        codigon2 += "8";
    }//GEN-LAST:event_b47ActionPerformed

    private void b48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b48MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b48MouseClicked

    private void b48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b48ActionPerformed
        niff2.setText(niff2.getText()+ "9");
        codigon2 += "9";
    }//GEN-LAST:event_b48ActionPerformed

    private void be4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_be4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_be4MouseClicked

    private void b49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b49MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b49MouseClicked

    private void b49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b49ActionPerformed
        niff2.setText(niff2.getText()+ "0");
        codigon2 += "0";
    }//GEN-LAST:event_b49ActionPerformed

    private void bc5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bc5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bc5MouseClicked

    private void bc5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc5ActionPerformed
        niff2.setText("");
        codigon2 = "";
    }//GEN-LAST:event_bc5ActionPerformed

    private void be1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_be1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_be1ActionPerformed
String nifff;
    private void be4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_be4ActionPerformed
         nifff=niff2.getText();
        
        if (validaNif(nifff)){
            
              AutoDismiss.showOptionDialog(rootPane, "NIF Válido! ", "ATENÇÃO" ,2000);
        
       
         
            
        }else{
            
            AutoDismiss.showOptionDialog(rootPane, "NIF Invalido! Introduza um Nif válido", "ATENÇÃO" ,2000);
            niff2.setText("");
            
        } 
        
                
        
        
        
        
    }                                

    
     public static boolean validaNif(String nif) 
    {
  		try
        {
		final int max=9;
		if (!nif.matches("[0-9]+") || nif.length()!=max) return false;
		int checkSum=0;
		//calcula a soma de controlo
		for (int i=0; i<max-1; i++){
			checkSum+=(nif.charAt(i)-'0')*(max-i);
		}
		int checkDigit=11-(checkSum % 11);
		if (checkDigit>=10) checkDigit=0;
		return checkDigit==nif.charAt(max-1)-'0';
        }
  catch (Exception e)
  {
	return false;
  }
  finally 
                {
                    }
    
    }//GEN-LAST:event_be4ActionPerformed

    private void rbbreservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbbreservaActionPerformed
       
    }//GEN-LAST:event_rbbreservaActionPerformed
double valorentrada;
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

        
        
      /*   String s = JOptionPane.showInputDialog("Passe o Cartão pf");
                
                if(s == null ){
                    
                    JOptionPane.showMessageDialog(null, "Cartao nao lido - Tente Novamente");
                    return;
                    
                } */
        
     /*    try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM entradas_private WHERE cartao = '" + s+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            if (count_ResultSet.next()){
            
            JOptionPane.showMessageDialog(null, "O Cartão já está Ativo!!!!");
                    return;
            }

        }catch(SQLException ex){
        }
      
        
        */
        
        
        
        
        
       
        String ticket_T = null;
        String cod_art="";
        
        if(reserva){
            ticket_T="Reserva";
            valorentrada=20;
            cod_art="304";
        }
        if(dia){
             ticket_T="Dia";
            valorentrada=25;
            cod_art="306";
        }
        
        if(vip){
             ticket_T="VIP";
            valorentrada=50;
            cod_art="307";
        }
        if(guest){
            return;
        }
        
       
                
         try {
            String dbUrl2="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER2="sa";
            String SQLPASS2 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"5");
                         } catch (InstantiationException ex) {
                          System.out.println(ex+"5");
                         } catch (IllegalAccessException ex) {
                          System.out.println(ex+"5");
                        }
            
            Connection con2 = DriverManager.getConnection(dbUrl2,SQLUSER2,SQLPASS2);
            
            
            Statement count_Statement2 = con2.createStatement();
            
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
            
            String count2 = "insert into entradas_private (cartao,valor,Data,ticket_type,saida,artigo) values (?,?,?,?,?,?)";
        
    
        
        
                 PreparedStatement preparedStmt22 = con2.prepareStatement(count2);
                 preparedStmt22.setInt (1, Integer.valueOf("01"));
                 preparedStmt22.setDouble (2, valorentrada);
                 preparedStmt22.setTimestamp (3, sqlTimeStamp);
                 preparedStmt22.setString (4, ticket_T);
                 preparedStmt22.setInt (5, 0);
                  preparedStmt22.setString (6, cod_art);
                 
           
                 preparedStmt22.execute();
        
        }catch(SQLException ex){
    
        }
       //  write_init(s);
         
         
         
         
         
         String nc = niff2.getText();
        if("".equals(nc)){
            
            send_num("",valorentrada,cod_art,"0");
            
            
        }else{
            
           send_num_nif("",valorentrada,cod_art,nc);
            
        } 
         
         
         
        
        
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
/*
        
        
        
           try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM entradas_private WHERE cartao = '" + s+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            if (count_ResultSet.next()){
            
            JOptionPane.showMessageDialog(null, "O Cartão já está Ativo!!!!");
                    return;
            }

        }catch(SQLException ex){
        }
   
        */
        
        String ticket_T = null;
        
      
        String cod_art="";
        
        if(reserva){
            ticket_T="Reserva";
            valorentrada=20;
            cod_art="304";
        }
        if(dia){
             ticket_T="Dia";
            valorentrada=25;
            cod_art="306";
        }
        
        if(vip){
             ticket_T="VIP";
            valorentrada=50;
            cod_art="307";
        }
        if(guest){
            return;
        }
       
                
                    try {
            String dbUrl2="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER2="sa";
            String SQLPASS2 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"5");
                         } catch (InstantiationException ex) {
                          System.out.println(ex+"5");
                         } catch (IllegalAccessException ex) {
                          System.out.println(ex+"5");
                        }
            
            Connection con2 = DriverManager.getConnection(dbUrl2,SQLUSER2,SQLPASS2);
            
            
            Statement count_Statement2 = con2.createStatement();
            
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
            
              String count2 = "insert into entradas_private (cartao,valor,Data,ticket_type,saida,artigo) values (?,?,?,?,?,?)";
        
    
        
        
                 PreparedStatement preparedStmt22 = con2.prepareStatement(count2);
                 preparedStmt22.setInt (1, Integer.valueOf("02"));
                 preparedStmt22.setDouble (2, valorentrada);
                 preparedStmt22.setTimestamp (3, sqlTimeStamp);
                 preparedStmt22.setString (4, ticket_T);
                 preparedStmt22.setInt (5, 0);
                  preparedStmt22.setString (6, cod_art);
                 preparedStmt22.execute();
        
        }catch(SQLException ex){
    System.out.println(ex+"5");
        }
        //  write_init(s);
         String nc = niff2.getText();
         
        
        if("".equals(nc)){
            
            send_mb_nif("",valorentrada,cod_art,"0");
            
            
        }else{
            
            send_mb_nif("",valorentrada,cod_art,nc);
            
        }
        
            
    

        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        
        
        String ticket_T="TicketLine";
        
        valorentrada=0;
        /*
          String s = JOptionPane.showInputDialog("Passe o Cartão pf");
                
                if(s == null ){
                    
                    JOptionPane.showMessageDialog(null, "Cartao nao lido - Tente Novamente");
                    return;
                    
                }*/
                
                    try {
            String dbUrl2="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER2="sa";
            String SQLPASS2 = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                          System.out.println(ex+"5");
                         } catch (InstantiationException ex) {
                          System.out.println(ex+"5");
                         } catch (IllegalAccessException ex) {
                          System.out.println(ex+"5");
                        }
            
            Connection con2 = DriverManager.getConnection(dbUrl2,SQLUSER2,SQLPASS2);
            
            
            Statement count_Statement2 = con2.createStatement();
            
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
            
              String count2 = "insert into entradas_private (cartao,valor,Data,ticket_type,saida) values (?,?,?,?,?)";
        
    
        
        
                 PreparedStatement preparedStmt22 = con2.prepareStatement(count2);
                 preparedStmt22.setInt (1, Integer.valueOf("00"));
                 preparedStmt22.setDouble (2, valorentrada);
                 preparedStmt22.setTimestamp (3, sqlTimeStamp);
                 preparedStmt22.setString (4, ticket_T);
                 preparedStmt22.setInt (5, 0);
           
                 preparedStmt22.execute();
        
        }catch(SQLException ex){
    
        }
                    
        
        
                   //  write_init(s);
                     
     //   JOptionPane.showMessageDialog(null, "Cartao "+s+" Registado com Sucesso");
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
       System.exit(0);
    }//GEN-LAST:event_jLabel53MouseClicked
    
    public void send_num(String s1,double v1,String art,String nif){
        
        
           try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5056)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             String vpp = String.valueOf(v1) + "#" +s1+"#"+nif+"#"+art;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
                                     System.out.println(ex);
			               
			        } 	    
        
        
        
        
            }
     public void send_num_nif(String s1,double v1,String art,String nif){
        
        
           try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket s = new Socket(ip, 5056)) {
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String vpp = String.valueOf(v1) + "#" +s1+"#"+nif+"#"+art;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 	    
        
        
        
        
            }
    public void send_mb_nif(String s1,double v1,String art,String nif){
   
        try
			        { 
					InetAddress ip = InetAddress.getByName(IPHOST); 
         try (Socket sk = new Socket(ip, 5055)) {
             DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
            String vpp = String.valueOf(v1) + "#" +s1+"#"+nif+"#"+art;
             dos.writeUTF(vpp);
             dos.flush();
             dos.close();
             
             
         }
		                
			        }catch(IOException ex){ 
			                ex.printStackTrace(); 
			        } 
    }
    
    public void updatemov(){
        
          
       try{

           String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            String query = " UPDATE movimentos_gerais SET periodo=? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, 1);
          
            preparedStmt.execute();
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
        
        
        
    }
    public void carregaart(String familia){
    
     try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM artigos WHERE familia = '" + familia+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            
            
            
         //   BoxLayout boxLayout = new BoxLayout( pnart, BoxLayout.PAGE_AXIS );
            pnart.setLayout(new GridLayout(4, 8));
            pnart.setSize(800, 400);
            pnart.setPreferredSize(new Dimension(800,400));
            
          ///  pnart.setUndecorated(true);
            pnart.getRootPane().setOpaque(false);
           
         //   pnart.getContentPane ().setBackground (new Color (255, 255, 255));
            pnart.setBackground(new Color(153,153,153));
            JButton btn = new JButton(count_ResultSet.getString(1));
            btn.setName(count_ResultSet.getString(1));
            btn.setText(count_ResultSet.getString(2));
            btn.addActionListener(this);
            btn.setFocusPainted(false);
            btn.setSize(100,100);
            btn.setPreferredSize(new Dimension(100, 100));
          //  btn.setLocation(100,100);
            btn.setBackground(new Color(51,153,255));
            btn.setForeground(new Color(255,255,255));
            java.awt.Font font = btn.getFont();
            float size = font.getSize() + 8.0f;
            btn.setFont(font.deriveFont(size));
            btn.setVisible(true);
          //  pnart.setLayout(null);
            pnart.add(btn);
      //      pnart.revalidate();
        //    pnart.updateUI();
         //   pack();
           
    
                
                
                
             
            }

          pnart.updateUI();

        }catch(SQLException ex){
        }
      
        
       
    
    
    
    
}
    
    double Saldoaberto=0;
    double Saldoartigo;
@Override
        public void actionPerformed(ActionEvent e)
        {
            String txt = e.getActionCommand();
            
             JButton o = (JButton)e.getSource();
             String name = o.getName();
            
           int size =  itemadded.getModel().getSize();
           size = size + 1;
            model.addElement(size + " | " + txt);
           // model.setElementAt("new text", index);
            
            itemadded.setModel(model);
             System.out.println(name);
             
            try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "SELECT * FROM artigos WHERE codigo = '" + name+ "'";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(3));
            Saldoartigo =   Double.valueOf(count_ResultSet.getString(3));
            Saldoaberto = Saldoaberto+Saldoartigo;
            saldo.setText("SALDO - "+Saldoaberto+" €");
            
            
            }
            
            con.close();
            
            
               }catch(SQLException ex){
        }
      
         
         
            try {
            String dbUrl="jdbc:sqlserver://"+IPHOSTM+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                     try {
                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InstantiationException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IllegalAccessException ex) {
                         Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                     }
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
            String count = "insert into movimentos (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc) values (?,?,?,?,?,?,?,?,?)";
        System.out.println(count);
    
         java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
        
                 PreparedStatement preparedStmt2 = con.prepareStatement(count);
                 preparedStmt2.setTimestamp (1, sqlTimeStamp);
                 preparedStmt2.setString (2,"doc");
                 preparedStmt2.setDouble (3,Double.valueOf(Saldoartigo));
                 preparedStmt2.setInt (4,Integer.valueOf(Cartaoativo));
                 preparedStmt2.setString (5,String.valueOf(name));
                 preparedStmt2.setInt (6, Integer.valueOf(1));
                 preparedStmt2.setInt (7, Integer.valueOf(1));
                 preparedStmt2.setString (8, String.valueOf(txt));
                 preparedStmt2.setInt (9, Integer.valueOf(size));
                 
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }
      
    
                    
            
            
            
            
             
        }
    
        
        
        
        
        

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        public class JPanelWithBackground extends JPanel {

  private Image backgroundImage;

  // Some code to initialize the background image.
  // Here, we use the constructor to load the image. This
  // can vary depending on the use case of the panel.
  public JPanelWithBackground(String fileName) throws IOException {
    backgroundImage = ImageIO.read(new File(fileName));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw the background image.
    g.drawImage(backgroundImage, 0, 0, this);
  }
}
        
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
  
   // try {
  //     UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
  //    } catch (UnsupportedLookAndFeelException e) {
  //    e.printStackTrace();
  //  }
   


 UIManager.setLookAndFeel (new MaterialLookAndFeel ());
                        
                        JMarsDarkTheme df = new JMarsDarkTheme();
                        
                      
                        
		MaterialLookAndFeel.changeTheme(df);
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
           
                
                    new Disco().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Artigos;
    private javax.swing.JPanel Camarotes;
    private javax.swing.JPanel Exploracao;
    private javax.swing.JPanel Fechos;
    private javax.swing.JPanel Ivas;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel MenuSuper;
    private javax.swing.JPanel POSMain;
    private javax.swing.JLabel PTotal;
    private javax.swing.JPanel Pagamento;
    private javax.swing.JPanel PasswordPrompt;
    private javax.swing.JPanel SplashScreen;
    private javax.swing.JButton b0;
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b18;
    private javax.swing.JButton b19;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b40;
    private javax.swing.JButton b41;
    private javax.swing.JButton b42;
    private javax.swing.JButton b43;
    private javax.swing.JButton b44;
    private javax.swing.JButton b45;
    private javax.swing.JButton b46;
    private javax.swing.JButton b47;
    private javax.swing.JButton b48;
    private javax.swing.JButton b49;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton bc;
    private javax.swing.JButton bc1;
    private javax.swing.JButton bc5;
    private javax.swing.JButton be;
    private javax.swing.JButton be1;
    private javax.swing.JButton be4;
    private javax.swing.JButton btaguas;
    private javax.swing.JButton btbebidasbrancas;
    private javax.swing.JButton btcafetaria;
    private javax.swing.JButton btcervejas;
    private javax.swing.JButton btcocktails;
    private javax.swing.JButton btconhaques;
    private javax.swing.JButton btespumantes;
    private javax.swing.JButton btgins;
    private javax.swing.JButton btndel;
    private javax.swing.JButton btsumos;
    private javax.swing.JButton bttonicas;
    private javax.swing.JButton btvinhos;
    private javax.swing.JButton btvodka;
    private javax.swing.JButton btwisky;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel cardinfo;
    private javax.swing.JTextField cardinput;
    private javax.swing.JButton ctsnack;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JPanel fPrivate;
    private javax.swing.JPanel familias;
    private javax.swing.JList<String> itemadded;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<String> jcfamilia;
    private javax.swing.JComboBox<String> jcivas;
    private javax.swing.JLabel lbdin;
    private javax.swing.JButton lbdinft;
    private javax.swing.JLabel lbmb;
    private javax.swing.JLabel lbmisto;
    private javax.swing.JPanel listanegra;
    private javax.swing.JList<String> listart;
    private javax.swing.JList<String> listpag;
    private javax.swing.JTextField niff;
    private javax.swing.JTextField niff2;
    private javax.swing.JPanel pnart;
    private javax.swing.JTextField pvp1;
    private javax.swing.JTextField pvp2;
    private javax.swing.JTextField pvp3;
    private javax.swing.JRadioButton rbbdia;
    private javax.swing.JRadioButton rbbguest;
    private javax.swing.JRadioButton rbbreserva;
    private javax.swing.JRadioButton rbbvip;
    private javax.swing.JLabel retroceder_pass10;
    private javax.swing.JLabel retroceder_pass12;
    private javax.swing.JLabel retroceder_pass13;
    private javax.swing.JLabel retroceder_pass3;
    private javax.swing.JLabel retroceder_pass4;
    private javax.swing.JLabel retroceder_pass5;
    private javax.swing.JLabel retroceder_pass6;
    private javax.swing.JLabel retroceder_pass7;
    private javax.swing.JLabel retroceder_pass8;
    private javax.swing.JLabel retroceder_pass9;
    private javax.swing.JLabel saldo;
    private javax.swing.JTextField senha;
    private javax.swing.JPanel series;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JLabel trDia;
    private javax.swing.JLabel trGuest;
    private javax.swing.JLabel trLTot;
    private javax.swing.JLabel trReserva;
    private javax.swing.JLabel trTL;
    private javax.swing.JLabel trVip;
    private javax.swing.JPanel transfer;
    private javax.swing.JLabel ttDia;
    private javax.swing.JLabel ttGuest;
    private javax.swing.JLabel ttReserva;
    private javax.swing.JLabel ttTicketLine;
    private javax.swing.JLabel ttTotal;
    private javax.swing.JLabel ttVip;
    private javax.swing.JLabel ttaberto;
    private javax.swing.JLabel ttfechado;
    private javax.swing.JLabel tttotal;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JLabel valcons;
    // End of variables declaration//GEN-END:variables
}

