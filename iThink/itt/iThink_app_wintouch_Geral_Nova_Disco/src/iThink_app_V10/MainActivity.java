/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import ITL_SCS_SPO.*;
import com.github.lgooddatepicker.components.DatePicker;
import gnu.io.CommPortIdentifier;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.BodyPart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.SendFailedException;



/**
 *
 * @author Fernando
 */
public final class MainActivity extends javax.swing.JFrame implements SCS_SPO_event_listener, evento_pagamento,SCS_SPO_update_log {
    

    /**
     * Creates new form MainActivity
     
     */
    
   
     
    SCS_SPO scs_spo;
    Thread sysThread;
    SPO_SETUP spoSetup;
    SCS_SETUP scsSetup;
    DefaultListModel listModelSCS;
    DefaultListModel listModelSPO;
    Thread serverthread;
    Server server;
    Pda pda;
    Thread pdathread;
    Thread serverapp;
    appserver AppServer;
    Thread sqlthread;
    sqllistener sqll;
    Thread RThoras;
    realtime Realtime;
    Mails enviamailniveis;
    Thread MailThread;
    Thread sqlthreadZS;
    sqllistenerZS sqllZS;
    SendData Send_a;
    Thread Senddata;
    Thread sqlsage;
    sqllistenerSage sqlSage;
    serverSifarma serversifarma;
    Thread serversithread;
    Thread sql1sage;
    sqllistener1SageG serverSageG;
    Thread serverthread1;
    Server1 server1;
     Thread serverthread2;
    Server2 server2;
    
    
    
   private static final  String FILE = "/home/pi/log/dailyreport.pdf";
   private static final  String FILE2 = "/home/pi/log/offlinereport.pdf";
    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static final Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL, BaseColor.GREEN);
    
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyLED", PinState.LOW);

    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    private final Date now = new Date();
    String hora = df.format(now);
    private static final SimpleDateFormat dff = new SimpleDateFormat("ddMMyyyy");
    private final Date dffnow = new Date();
    String horadff = dff.format(dffnow);
    
        File file =new File("/home/pi/log/Ithink_"+horadff+".log");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        
        File file2 =new File("/home/pi/log/Ithink_ERROR_"+horadff+".log");
        FileWriter fw2 = new FileWriter(file2,true);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        PrintWriter pw2 = new PrintWriter(bw2);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    
    public MainActivity (String usr2) throws SAXException, IOException, ParserConfigurationException  {
        super("iThink - IoT");
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       // setExtendedState(JFrame.MAXIMIZED_BOTH); 
        ecraPagamentos.hide();
        pag_rasp.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
        configgeral.hide();
        EndPayment.hide();
        RetirarFundos.hide();      
        ConsultaCofre.hide();
        DefinirFManeio.hide();
        EstadoGeral.hide();
        Niveis_Minimos.hide();
        Jampanel.hide();
        CarregaV2.hide();
        ConsultaV2.hide();
        ecraconfigV2.hide();
        Channels.hide();
         Denomination.hide();
         
         
         
         
         
         
         
         
         
        
     //   fundomaneiodef.setVisible(false);
        
    //    totalmaquinafecho.setVisible(false);
        
     //   valorperiodo.setVisible(false);
        
    //    totalcofrefecho.setVisible(false);
        
      Un1c.setMinimumSize(new Dimension(30, 22));
       Un2c.setMinimumSize(new Dimension(30, 22));
       Un5c.setMinimumSize(new Dimension(30, 22));
       Un10c.setMinimumSize(new Dimension(30, 22));
       Un20c.setMinimumSize(new Dimension(30, 22));
       Un50c.setMinimumSize(new Dimension(30, 22));
       Un1e.setMinimumSize(new Dimension(30, 22));
       Un2e.setMinimumSize(new Dimension(30, 22));
       
       Un5e.setMinimumSize(new Dimension(30, 22));
       Un10e.setMinimumSize(new Dimension(30, 22));
       Un20e.setMinimumSize(new Dimension(30, 22));
       Un50e.setMinimumSize(new Dimension(30, 22));
       Un100e.setMinimumSize(new Dimension(30, 22));
       Un200e.setMinimumSize(new Dimension(30, 22));
       
    
        listModelSCS = new DefaultListModel();
        listModelSPO = new DefaultListModel();
       
          Enumeration portList = CommPortIdentifier.getPortIdentifiers();
          int i = 0;
          while (!portList.hasMoreElements()){
          
               
               portList = CommPortIdentifier.getPortIdentifiers();
              if (i<1){ 
             log("Sem Ligação com validadores, chamar tecnico qualificado pela iThink p.f.");
              i = 1;
              }
              if (portList.hasMoreElements()){
            
                   try {
                       conetar();
                   } catch (IOException | java.lang.InterruptedException ex) {
                       Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                   }
          } 
      }
        
        try {
            conetar();
        } catch (IOException | java.lang.InterruptedException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //   sok = new Socket(IPHOST2, 5057);
         
       
     
         
    }
 // Socket sok;
  
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PasswordPrompt = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
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
        retroceder_pass = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        ecraconfig_old = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        but_trocos = new javax.swing.JButton();
        but_consultas = new javax.swing.JButton();
        But_painelbordo = new javax.swing.JButton();
        but_pagamentos = new javax.swing.JButton();
        but_supervisor = new javax.swing.JButton();
        but_carregamentos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        but_float = new javax.swing.JButton();
        but_emptyall = new javax.swing.JButton();
        retroceder_pass2 = new javax.swing.JLabel();
        but_float1 = new javax.swing.JButton();
        but_fmaneiodef = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        but_float2 = new javax.swing.JButton();
        but_float3 = new javax.swing.JButton();
        scs_spo_consultas = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        moeda005 = new javax.swing.JLabel();
        moeda010 = new javax.swing.JLabel();
        moeda001 = new javax.swing.JLabel();
        moeda002 = new javax.swing.JLabel();
        moeda020 = new javax.swing.JLabel();
        moeda050 = new javax.swing.JLabel();
        moeda100 = new javax.swing.JLabel();
        moeda200 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nota20 = new javax.swing.JLabel();
        nota50 = new javax.swing.JLabel();
        nota5 = new javax.swing.JLabel();
        nota10 = new javax.swing.JLabel();
        nota100 = new javax.swing.JLabel();
        nota200 = new javax.swing.JLabel();
        nota500 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        totalmoedasnotas = new javax.swing.JLabel();
        retroceder_pass1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        Pagamentos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        pagamentovalor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        b51 = new javax.swing.JButton();
        b52 = new javax.swing.JButton();
        b53 = new javax.swing.JButton();
        b54 = new javax.swing.JButton();
        b55 = new javax.swing.JButton();
        b56 = new javax.swing.JButton();
        b57 = new javax.swing.JButton();
        b58 = new javax.swing.JButton();
        b59 = new javax.swing.JButton();
        be2 = new javax.swing.JButton();
        b60 = new javax.swing.JButton();
        bc2 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        W = new javax.swing.JButton();
        Q = new javax.swing.JButton();
        E = new javax.swing.JButton();
        R = new javax.swing.JButton();
        T = new javax.swing.JButton();
        Y = new javax.swing.JButton();
        U = new javax.swing.JButton();
        O = new javax.swing.JButton();
        I = new javax.swing.JButton();
        P = new javax.swing.JButton();
        A = new javax.swing.JButton();
        Ç = new javax.swing.JButton();
        L = new javax.swing.JButton();
        K = new javax.swing.JButton();
        J = new javax.swing.JButton();
        H = new javax.swing.JButton();
        G = new javax.swing.JButton();
        F = new javax.swing.JButton();
        D = new javax.swing.JButton();
        S = new javax.swing.JButton();
        Z = new javax.swing.JButton();
        TRAC = new javax.swing.JButton();
        PONT = new javax.swing.JButton();
        VIRG = new javax.swing.JButton();
        M = new javax.swing.JButton();
        N = new javax.swing.JButton();
        B = new javax.swing.JButton();
        V = new javax.swing.JButton();
        C = new javax.swing.JButton();
        X = new javax.swing.JButton();
        SPACE = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descritivo = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        Q1 = new javax.swing.JButton();
        Q2 = new javax.swing.JButton();
        Q3 = new javax.swing.JButton();
        Q4 = new javax.swing.JButton();
        Q5 = new javax.swing.JButton();
        Q6 = new javax.swing.JButton();
        Q7 = new javax.swing.JButton();
        Q8 = new javax.swing.JButton();
        Q9 = new javax.swing.JButton();
        Q10 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        retroceder_pass13 = new javax.swing.JLabel();
        Carregamentos = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        nmoeda005 = new javax.swing.JLabel();
        nmoeda010 = new javax.swing.JLabel();
        nmoeda001 = new javax.swing.JLabel();
        nmoeda002 = new javax.swing.JLabel();
        nmoeda020 = new javax.swing.JLabel();
        nmoeda050 = new javax.swing.JLabel();
        nmoeda100 = new javax.swing.JLabel();
        nmoeda200 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        nnota20 = new javax.swing.JLabel();
        nnota50 = new javax.swing.JLabel();
        nnota5 = new javax.swing.JLabel();
        nnota10 = new javax.swing.JLabel();
        nnota100 = new javax.swing.JLabel();
        nnota200 = new javax.swing.JLabel();
        nnota500 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ntotalmoedasnotas = new javax.swing.JLabel();
        retroceder_pass5 = new javax.swing.JLabel();
        totalcarregadolb = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        Supervisor = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        AcCarregamentos = new javax.swing.JRadioButton();
        AcExistencias = new javax.swing.JRadioButton();
        AcPainelbordo = new javax.swing.JRadioButton();
        AcFundomaneio = new javax.swing.JRadioButton();
        AcFechoperiodo = new javax.swing.JRadioButton();
        AcPagamentos = new javax.swing.JRadioButton();
        AcNiveisminimos = new javax.swing.JRadioButton();
        AcSupervisor = new javax.swing.JRadioButton();
        AcEstadoGeral = new javax.swing.JRadioButton();
        AcReinicio = new javax.swing.JRadioButton();
        AcDefnotas = new javax.swing.JRadioButton();
        AcConsultacofre = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        retroceder_pass3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        operpass = new javax.swing.JLabel();
        superpass = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        spofwr = new javax.swing.JLabel();
        spodts = new javax.swing.JLabel();
        scsfwr = new javax.swing.JLabel();
        scsdts = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        ecratransacoes = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        retroceder_consulta = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        fundomaneio = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        totalfaturado = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        totalcofre = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        totalpagamentos = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        totalcarregamentos = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jButton6 = new javax.swing.JButton();
        cofre_removido = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        valorsaque = new javax.swing.JTextField();
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
        retroceder_pass4 = new javax.swing.JLabel();
        SplashScreen = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        ecraPagamentos = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        horas = new javax.swing.JLabel();
        horas1 = new javax.swing.JLabel();
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
        jLabel28 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        pagolabel2 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        faltapagarlabel = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        trocolabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        RetirarFundos = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        retroceder_pass7 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        totalmaquinafecho = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        fundomaneiodef = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        valorperiodo = new javax.swing.JLabel();
        totalcofrefecho = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        DefinirFManeio = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        c1 = new javax.swing.JLabel();
        c10 = new javax.swing.JLabel();
        c50 = new javax.swing.JLabel();
        c20 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        e1 = new javax.swing.JLabel();
        e2 = new javax.swing.JLabel();
        n5 = new javax.swing.JLabel();
        n10 = new javax.swing.JLabel();
        n20 = new javax.swing.JLabel();
        n50 = new javax.swing.JLabel();
        n100 = new javax.swing.JLabel();
        n200 = new javax.swing.JLabel();
        n500 = new javax.swing.JLabel();
        fmaneiodef = new javax.swing.JLabel();
        retroceder_pass11 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        ConsultaCofre = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        cc5 = new javax.swing.JLabel();
        cc10 = new javax.swing.JLabel();
        cc20 = new javax.swing.JLabel();
        cc50 = new javax.swing.JLabel();
        cc100 = new javax.swing.JLabel();
        cc200 = new javax.swing.JLabel();
        cc500 = new javax.swing.JLabel();
        retroceder_consulta1 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        ccvalortotal = new javax.swing.JLabel();
        EstadoGeral = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        panel2 = new java.awt.Panel();
        panel3 = new java.awt.Panel();
        panel4 = new java.awt.Panel();
        jLabel43 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        fundomaneiodef1 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        totalmaquinafecho1 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        valorperiodo1 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        totalcofrefecho1 = new javax.swing.JLabel();
        panel5 = new java.awt.Panel();
        EndPayment = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        troco2 = new javax.swing.JLabel();
        quantia = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        troconotas = new javax.swing.JLabel();
        trocomoedas = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        pag_rasp = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        raspeur = new javax.swing.JLabel();
        Niveis_Minimos = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        nv1c = new javax.swing.JLabel();
        nv10c = new javax.swing.JLabel();
        nv50c = new javax.swing.JLabel();
        nv20c = new javax.swing.JLabel();
        nv2c = new javax.swing.JLabel();
        nv1e = new javax.swing.JLabel();
        nv2e = new javax.swing.JLabel();
        nv5e = new javax.swing.JLabel();
        nv10e = new javax.swing.JLabel();
        nv20e = new javax.swing.JLabel();
        nv50e = new javax.swing.JLabel();
        nv100e = new javax.swing.JLabel();
        nv200e = new javax.swing.JLabel();
        retroceder_pass12 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        nv5c = new javax.swing.JLabel();
        Jampanel = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jammessage = new javax.swing.JLabel();
        jammessage2 = new javax.swing.JLabel();
        CarregaV2 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        pb1c = new javax.swing.JProgressBar();
        pb2c = new javax.swing.JProgressBar();
        pb5c = new javax.swing.JProgressBar();
        pb10c = new javax.swing.JProgressBar();
        pb20c = new javax.swing.JProgressBar();
        pb50c = new javax.swing.JProgressBar();
        pb1e = new javax.swing.JProgressBar();
        pb2e = new javax.swing.JProgressBar();
        jLabel56 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        pb5e = new javax.swing.JProgressBar();
        pb10e = new javax.swing.JProgressBar();
        pb20e = new javax.swing.JProgressBar();
        pb50e = new javax.swing.JProgressBar();
        pb100e = new javax.swing.JProgressBar();
        pb200e = new javax.swing.JProgressBar();
        retroceder_pass8 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        lblcarregaV2 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        totcarregav2 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        totatual = new javax.swing.JLabel();
        Un1c = new javax.swing.JLabel();
        Un2c = new javax.swing.JLabel();
        Un5c = new javax.swing.JLabel();
        Un10c = new javax.swing.JLabel();
        Un20c = new javax.swing.JLabel();
        Un50c = new javax.swing.JLabel();
        Un1e = new javax.swing.JLabel();
        Un2e = new javax.swing.JLabel();
        Un50e = new javax.swing.JLabel();
        Un100e = new javax.swing.JLabel();
        Un200e = new javax.swing.JLabel();
        Un5e = new javax.swing.JLabel();
        Un10e = new javax.swing.JLabel();
        Un20e = new javax.swing.JLabel();
        ConsultaV2 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        cpb1c = new javax.swing.JProgressBar();
        cpb2c = new javax.swing.JProgressBar();
        cpb5c = new javax.swing.JProgressBar();
        cpb10c = new javax.swing.JProgressBar();
        cpb20c = new javax.swing.JProgressBar();
        cpb50c = new javax.swing.JProgressBar();
        cpb1e = new javax.swing.JProgressBar();
        cpb2e = new javax.swing.JProgressBar();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        cpb5e = new javax.swing.JProgressBar();
        cpb10e = new javax.swing.JProgressBar();
        cpb20e = new javax.swing.JProgressBar();
        cpb50e = new javax.swing.JProgressBar();
        cpb100e = new javax.swing.JProgressBar();
        cpb200e = new javax.swing.JProgressBar();
        retroceder_pass9 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        ctotatual = new javax.swing.JLabel();
        ecraconfigV2 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        but_float5 = new javax.swing.JButton();
        but_consultas2 = new javax.swing.JButton();
        But_painelbordo1 = new javax.swing.JButton();
        but_float6 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        but_emptyall1 = new javax.swing.JButton();
        but_trocos1 = new javax.swing.JButton();
        but_fmaneiodef1 = new javax.swing.JButton();
        but_supervisor1 = new javax.swing.JButton();
        but_fmaneiodef2 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        but_carregamentos1 = new javax.swing.JButton();
        but_pagamentos1 = new javax.swing.JButton();
        but_float4 = new javax.swing.JButton();
        retroceder_pass10 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        Channels = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        Ch50eur = new javax.swing.JToggleButton();
        Ch20eur = new javax.swing.JToggleButton();
        Ch10eur = new javax.swing.JToggleButton();
        Ch5eur = new javax.swing.JToggleButton();
        Ch500eur = new javax.swing.JToggleButton();
        Ch200eur = new javax.swing.JToggleButton();
        Ch100eur = new javax.swing.JToggleButton();
        retroceder_pass14 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        configgeral = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        retroceder_pass6 = new javax.swing.JLabel();
        spo5e = new javax.swing.JLabel();
        spo20e = new javax.swing.JLabel();
        spo10e = new javax.swing.JLabel();
        spo100e = new javax.swing.JLabel();
        spo200e = new javax.swing.JLabel();
        spo50e = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        Denomination = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        retroceder_pass15 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        txtdenom = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtdemonatuais = new javax.swing.JLabel();
        b20 = new javax.swing.JButton();
        b21 = new javax.swing.JButton();
        b22 = new javax.swing.JButton();
        b23 = new javax.swing.JButton();
        b24 = new javax.swing.JButton();
        b25 = new javax.swing.JButton();
        b26 = new javax.swing.JButton();
        b27 = new javax.swing.JButton();
        b28 = new javax.swing.JButton();
        b29 = new javax.swing.JButton();
        bc3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(50, 191, 231));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(800, 480));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(800, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 480));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Introduza a senha de Administrador");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        senha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        senha.setBorder(null);
        senha.setCaretColor(new java.awt.Color(255, 255, 255));
        senha.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N

        b1.setText("1");
        b1.setBackground(new java.awt.Color(51, 153, 255));
        b1.setBorder(null);
        b1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        be.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        b0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
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
        bc.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        bc.setToolTipText("");
        bc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bcMouseClicked(evt);
            }
        });

        retroceder_pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_passMouseClicked(evt);
            }
        });

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(retroceder_pass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(bc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b0, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(be, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel31)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(retroceder_pass))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20)
                                .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b0, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(be, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PasswordPromptLayout = new javax.swing.GroupLayout(PasswordPrompt);
        PasswordPrompt.setLayout(PasswordPromptLayout);
        PasswordPromptLayout.setHorizontalGroup(
            PasswordPromptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PasswordPromptLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PasswordPromptLayout.setVerticalGroup(
            PasswordPromptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(PasswordPrompt);

        jPanel6.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        but_trocos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_exchange_euro_48.png"))); // NOI18N
        but_trocos.setText("Niveis Minimos");
        but_trocos.setBackground(new java.awt.Color(255, 255, 255));
        but_trocos.setBorder(null);
        but_trocos.setContentAreaFilled(false);
        but_trocos.setDisabledIcon(null);
        but_trocos.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_trocos.setForeground(new java.awt.Color(0, 153, 255));
        but_trocos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_trocos.setOpaque(true);
        but_trocos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_trocosActionPerformed(evt);
            }
        });

        but_consultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_information_48.png"))); // NOI18N
        but_consultas.setText("Existências");
        but_consultas.setBackground(new java.awt.Color(255, 255, 255));
        but_consultas.setBorder(null);
        but_consultas.setContentAreaFilled(false);
        but_consultas.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_consultas.setForeground(new java.awt.Color(0, 153, 255));
        but_consultas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_consultas.setOpaque(true);
        but_consultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_consultasActionPerformed(evt);
            }
        });

        But_painelbordo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_combo_chart_48.png"))); // NOI18N
        But_painelbordo.setText("Painel Bordo");
        But_painelbordo.setBackground(new java.awt.Color(255, 255, 255));
        But_painelbordo.setBorder(null);
        But_painelbordo.setContentAreaFilled(false);
        But_painelbordo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        But_painelbordo.setForeground(new java.awt.Color(51, 153, 255));
        But_painelbordo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        But_painelbordo.setOpaque(true);
        But_painelbordo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But_painelbordoActionPerformed(evt);
            }
        });

        but_pagamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_receive_cash_48.png"))); // NOI18N
        but_pagamentos.setText("Pagamentos");
        but_pagamentos.setBackground(new java.awt.Color(255, 255, 255));
        but_pagamentos.setBorder(null);
        but_pagamentos.setContentAreaFilled(false);
        but_pagamentos.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_pagamentos.setForeground(new java.awt.Color(51, 153, 255));
        but_pagamentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_pagamentos.setOpaque(true);
        but_pagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_pagamentosActionPerformed(evt);
            }
        });

        but_supervisor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_administrator_male_48.png"))); // NOI18N
        but_supervisor.setText("Supervisor");
        but_supervisor.setBackground(new java.awt.Color(255, 255, 255));
        but_supervisor.setBorder(null);
        but_supervisor.setContentAreaFilled(false);
        but_supervisor.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_supervisor.setForeground(new java.awt.Color(51, 153, 255));
        but_supervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_supervisor.setOpaque(true);
        but_supervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_supervisorActionPerformed(evt);
            }
        });

        but_carregamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_request_money_48.png"))); // NOI18N
        but_carregamentos.setText("Carregamentos");
        but_carregamentos.setBackground(new java.awt.Color(255, 255, 255));
        but_carregamentos.setBorder(null);
        but_carregamentos.setContentAreaFilled(false);
        but_carregamentos.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_carregamentos.setForeground(new java.awt.Color(51, 153, 255));
        but_carregamentos.setHideActionText(true);
        but_carregamentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_carregamentos.setOpaque(true);
        but_carregamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_carregamentosActionPerformed(evt);
            }
        });

        jLabel4.setText("CONSULTAS E CONFIGURAÇÃO DO SISTEMA");
        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N

        but_float.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_safe_out_48.png"))); // NOI18N
        but_float.setText("Fecho Periodo");
        but_float.setBackground(new java.awt.Color(255, 255, 255));
        but_float.setBorder(null);
        but_float.setContentAreaFilled(false);
        but_float.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float.setForeground(new java.awt.Color(51, 153, 255));
        but_float.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float.setOpaque(true);
        but_float.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_floatActionPerformed(evt);
            }
        });

        but_emptyall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_settings_48.png"))); // NOI18N
        but_emptyall.setText("Definições  Notas");
        but_emptyall.setBackground(new java.awt.Color(255, 255, 255));
        but_emptyall.setBorder(null);
        but_emptyall.setContentAreaFilled(false);
        but_emptyall.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_emptyall.setForeground(new java.awt.Color(51, 153, 255));
        but_emptyall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_emptyall.setOpaque(true);
        but_emptyall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_emptyallActionPerformed(evt);
            }
        });

        retroceder_pass2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass2.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass2MouseClicked(evt);
            }
        });

        but_float1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_information_48.png"))); // NOI18N
        but_float1.setText("Consulta Cofre");
        but_float1.setBackground(new java.awt.Color(255, 255, 255));
        but_float1.setBorder(null);
        but_float1.setContentAreaFilled(false);
        but_float1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float1.setForeground(new java.awt.Color(51, 153, 255));
        but_float1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float1.setOpaque(true);
        but_float1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_float1ActionPerformed(evt);
            }
        });

        but_fmaneiodef.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_settings_48.png"))); // NOI18N
        but_fmaneiodef.setText("Fundo de Maneio");
        but_fmaneiodef.setBackground(new java.awt.Color(255, 255, 255));
        but_fmaneiodef.setBorder(null);
        but_fmaneiodef.setContentAreaFilled(false);
        but_fmaneiodef.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_fmaneiodef.setForeground(new java.awt.Color(51, 153, 255));
        but_fmaneiodef.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_fmaneiodef.setOpaque(true);
        but_fmaneiodef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_fmaneiodefActionPerformed(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        but_float2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_statistics_48.png"))); // NOI18N
        but_float2.setText("Estado Geral");
        but_float2.setBackground(new java.awt.Color(255, 255, 255));
        but_float2.setBorder(null);
        but_float2.setContentAreaFilled(false);
        but_float2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float2.setForeground(new java.awt.Color(51, 153, 255));
        but_float2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float2.setOpaque(true);
        but_float2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_float2ActionPerformed(evt);
            }
        });

        but_float3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_home_32.png"))); // NOI18N
        but_float3.setText("Reinicio Programa");
        but_float3.setBackground(new java.awt.Color(255, 255, 255));
        but_float3.setBorder(null);
        but_float3.setContentAreaFilled(false);
        but_float3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float3.setForeground(new java.awt.Color(51, 153, 255));
        but_float3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float3.setIconTextGap(12);
        but_float3.setOpaque(true);
        but_float3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_float3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(but_pagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(but_float1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(but_fmaneiodef, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(but_trocos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(but_carregamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(But_painelbordo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(but_consultas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(but_emptyall, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(but_supervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(but_float, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(but_float2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(but_float3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(retroceder_pass2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_carregamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But_painelbordo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_supervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(but_emptyall, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(but_trocos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(but_consultas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(but_float1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(but_fmaneiodef, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(but_pagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(but_float, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_float2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_float3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ecraconfig_oldLayout = new javax.swing.GroupLayout(ecraconfig_old);
        ecraconfig_old.setLayout(ecraconfig_oldLayout);
        ecraconfig_oldLayout.setHorizontalGroup(
            ecraconfig_oldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ecraconfig_oldLayout.setVerticalGroup(
            ecraconfig_oldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(ecraconfig_old);

        jPanel8.setBackground(java.awt.SystemColor.activeCaptionBorder);

        moeda005.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda005.setText("jLabel6");
        moeda005.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "0.05 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda005.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda010.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda010.setText("jLabel6");
        moeda010.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "0.10 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda010.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda001.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda001.setText("jLabel6");
        moeda001.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "0.01 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda001.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda002.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda002.setText("jLabel6");
        moeda002.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "0.02 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda002.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda020.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda020.setText("jLabel6");
        moeda020.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "0.20 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda020.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda050.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda050.setText("jLabel6");
        moeda050.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "0.50 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda050.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda100.setText("jLabel6");
        moeda100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1.00 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda100.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        moeda200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moeda200.setText("jLabel6");
        moeda200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2.00 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        moeda200.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel14.setText("Existências | Valor");
        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        nota20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota20.setText("jLabel6");
        nota20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nota50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota50.setText("jLabel6");
        nota50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota50.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nota5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota5.setText("jLabel6");
        nota5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nota10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota10.setText("jLabel6");
        nota10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nota100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota100.setText("jLabel6");
        nota100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "100 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota100.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nota200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota200.setText("jLabel6");
        nota200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "200 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota200.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nota500.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota500.setText("jLabel6");
        nota500.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "500 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nota500.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel23.setText("Existências | Valor");
        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel15.setText("NOTAS");
        jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel16.setText("MOEDAS");
        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        totalmoedasnotas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalmoedasnotas.setText("jLabel6");
        totalmoedasnotas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL EM CAIXA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        totalmoedasnotas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        retroceder_pass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass1.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass1MouseClicked(evt);
            }
        });

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(retroceder_pass1)
                        .addGap(70, 70, 70)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(159, 159, 159))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(moeda001, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(moeda020, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(moeda010, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(moeda005, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(moeda002, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(moeda100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(moeda200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(moeda050, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(totalmoedasnotas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nota5, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(nota20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nota50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nota10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nota100, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(nota500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nota200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23))
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nota5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(nota100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nota10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota200, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nota500, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nota50, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(moeda020, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(moeda001, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(moeda002, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(moeda050, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(moeda100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(moeda005, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(moeda010, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(moeda200, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalmoedasnotas, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout scs_spo_consultasLayout = new javax.swing.GroupLayout(scs_spo_consultas);
        scs_spo_consultas.setLayout(scs_spo_consultasLayout);
        scs_spo_consultasLayout.setHorizontalGroup(
            scs_spo_consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        scs_spo_consultasLayout.setVerticalGroup(
            scs_spo_consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(scs_spo_consultas);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Valor a Pagar");
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        b51.setText("1");
        b51.setBackground(new java.awt.Color(51, 153, 255));
        b51.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b51.setForeground(new java.awt.Color(255, 255, 255));
        b51.setToolTipText("");
        b51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b51MouseClicked(evt);
            }
        });
        b51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b51ActionPerformed(evt);
            }
        });

        b52.setText("2");
        b52.setBackground(new java.awt.Color(51, 153, 255));
        b52.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b52.setForeground(new java.awt.Color(255, 255, 255));
        b52.setToolTipText("");
        b52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b52MouseClicked(evt);
            }
        });
        b52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b52ActionPerformed(evt);
            }
        });

        b53.setText("3");
        b53.setActionCommand("1");
        b53.setBackground(new java.awt.Color(51, 153, 255));
        b53.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b53.setForeground(new java.awt.Color(255, 255, 255));
        b53.setToolTipText("");
        b53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b53MouseClicked(evt);
            }
        });
        b53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b53ActionPerformed(evt);
            }
        });

        b54.setText("6");
        b54.setBackground(new java.awt.Color(51, 153, 255));
        b54.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b54.setForeground(new java.awt.Color(255, 255, 255));
        b54.setToolTipText("");
        b54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b54MouseClicked(evt);
            }
        });
        b54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b54ActionPerformed(evt);
            }
        });

        b55.setText("5");
        b55.setBackground(new java.awt.Color(51, 153, 255));
        b55.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b55.setForeground(new java.awt.Color(255, 255, 255));
        b55.setToolTipText("");
        b55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b55MouseClicked(evt);
            }
        });
        b55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b55ActionPerformed(evt);
            }
        });

        b56.setText("4");
        b56.setBackground(new java.awt.Color(51, 153, 255));
        b56.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b56.setForeground(new java.awt.Color(255, 255, 255));
        b56.setToolTipText("");
        b56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b56MouseClicked(evt);
            }
        });
        b56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b56ActionPerformed(evt);
            }
        });

        b57.setText("7");
        b57.setBackground(new java.awt.Color(51, 153, 255));
        b57.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b57.setForeground(new java.awt.Color(255, 255, 255));
        b57.setToolTipText("");
        b57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b57MouseClicked(evt);
            }
        });
        b57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b57ActionPerformed(evt);
            }
        });

        b58.setText("8");
        b58.setBackground(new java.awt.Color(51, 153, 255));
        b58.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b58.setForeground(new java.awt.Color(255, 255, 255));
        b58.setToolTipText("");
        b58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b58MouseClicked(evt);
            }
        });
        b58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b58ActionPerformed(evt);
            }
        });

        b59.setText("9");
        b59.setBackground(new java.awt.Color(51, 153, 255));
        b59.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b59.setForeground(new java.awt.Color(255, 255, 255));
        b59.setToolTipText("");
        b59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b59MouseClicked(evt);
            }
        });
        b59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b59ActionPerformed(evt);
            }
        });

        be2.setText(",");
        be2.setBackground(new java.awt.Color(51, 153, 255));
        be2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        be2.setForeground(new java.awt.Color(255, 255, 255));
        be2.setToolTipText("");
        be2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                be2MouseClicked(evt);
            }
        });
        be2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                be2ActionPerformed(evt);
            }
        });

        b60.setText("0");
        b60.setBackground(new java.awt.Color(51, 153, 255));
        b60.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        b60.setForeground(new java.awt.Color(255, 255, 255));
        b60.setToolTipText("");
        b60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b60MouseClicked(evt);
            }
        });
        b60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b60ActionPerformed(evt);
            }
        });

        bc2.setText("C");
        bc2.setBackground(new java.awt.Color(51, 153, 255));
        bc2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bc2.setForeground(new java.awt.Color(255, 255, 255));
        bc2.setToolTipText("");
        bc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bc2MouseClicked(evt);
            }
        });
        bc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bc2ActionPerformed(evt);
            }
        });

        jButton14.setText("PAGAR");
        jButton14.setBackground(new java.awt.Color(51, 153, 255));
        jButton14.setBorder(null);
        jButton14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        W.setText("W");
        W.setBackground(new java.awt.Color(51, 153, 255));
        W.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        W.setForeground(new java.awt.Color(255, 255, 255));
        W.setToolTipText("");
        W.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WMouseClicked(evt);
            }
        });
        W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WActionPerformed(evt);
            }
        });

        Q.setText("Q");
        Q.setBackground(new java.awt.Color(51, 153, 255));
        Q.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q.setForeground(new java.awt.Color(255, 255, 255));
        Q.setToolTipText("");
        Q.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QMouseClicked(evt);
            }
        });
        Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QActionPerformed(evt);
            }
        });

        E.setText("E");
        E.setBackground(new java.awt.Color(51, 153, 255));
        E.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        E.setForeground(new java.awt.Color(255, 255, 255));
        E.setToolTipText("");
        E.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EMouseClicked(evt);
            }
        });
        E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EActionPerformed(evt);
            }
        });

        R.setText("R");
        R.setBackground(new java.awt.Color(51, 153, 255));
        R.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        R.setForeground(new java.awt.Color(255, 255, 255));
        R.setToolTipText("");
        R.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RMouseClicked(evt);
            }
        });
        R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RActionPerformed(evt);
            }
        });

        T.setText("T");
        T.setBackground(new java.awt.Color(51, 153, 255));
        T.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        T.setForeground(new java.awt.Color(255, 255, 255));
        T.setToolTipText("");
        T.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TMouseClicked(evt);
            }
        });
        T.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TActionPerformed(evt);
            }
        });

        Y.setText("Y");
        Y.setBackground(new java.awt.Color(51, 153, 255));
        Y.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Y.setForeground(new java.awt.Color(255, 255, 255));
        Y.setToolTipText("");
        Y.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                YMouseClicked(evt);
            }
        });
        Y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YActionPerformed(evt);
            }
        });

        U.setText("U");
        U.setBackground(new java.awt.Color(51, 153, 255));
        U.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        U.setForeground(new java.awt.Color(255, 255, 255));
        U.setToolTipText("");
        U.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UMouseClicked(evt);
            }
        });
        U.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UActionPerformed(evt);
            }
        });

        O.setText("O");
        O.setBackground(new java.awt.Color(51, 153, 255));
        O.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        O.setForeground(new java.awt.Color(255, 255, 255));
        O.setToolTipText("");
        O.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OMouseClicked(evt);
            }
        });
        O.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OActionPerformed(evt);
            }
        });

        I.setText("I");
        I.setBackground(new java.awt.Color(51, 153, 255));
        I.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        I.setForeground(new java.awt.Color(255, 255, 255));
        I.setToolTipText("");
        I.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IMouseClicked(evt);
            }
        });
        I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IActionPerformed(evt);
            }
        });

        P.setText("P");
        P.setBackground(new java.awt.Color(51, 153, 255));
        P.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P.setForeground(new java.awt.Color(255, 255, 255));
        P.setToolTipText("");
        P.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PMouseClicked(evt);
            }
        });
        P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PActionPerformed(evt);
            }
        });

        A.setText("A");
        A.setBackground(new java.awt.Color(51, 153, 255));
        A.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        A.setForeground(new java.awt.Color(255, 255, 255));
        A.setToolTipText("");
        A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AMouseClicked(evt);
            }
        });
        A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AActionPerformed(evt);
            }
        });

        Ç.setText("Ç");
        Ç.setBackground(new java.awt.Color(51, 153, 255));
        Ç.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Ç.setForeground(new java.awt.Color(255, 255, 255));
        Ç.setToolTipText("");
        Ç.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ÇMouseClicked(evt);
            }
        });
        Ç.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ÇActionPerformed(evt);
            }
        });

        L.setText("L");
        L.setBackground(new java.awt.Color(51, 153, 255));
        L.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        L.setForeground(new java.awt.Color(255, 255, 255));
        L.setToolTipText("");
        L.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LMouseClicked(evt);
            }
        });
        L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LActionPerformed(evt);
            }
        });

        K.setText("K");
        K.setBackground(new java.awt.Color(51, 153, 255));
        K.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        K.setForeground(new java.awt.Color(255, 255, 255));
        K.setToolTipText("");
        K.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KMouseClicked(evt);
            }
        });
        K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KActionPerformed(evt);
            }
        });

        J.setText("J");
        J.setBackground(new java.awt.Color(51, 153, 255));
        J.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        J.setForeground(new java.awt.Color(255, 255, 255));
        J.setToolTipText("");
        J.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JMouseClicked(evt);
            }
        });
        J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JActionPerformed(evt);
            }
        });

        H.setText("H");
        H.setBackground(new java.awt.Color(51, 153, 255));
        H.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        H.setForeground(new java.awt.Color(255, 255, 255));
        H.setToolTipText("");
        H.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HMouseClicked(evt);
            }
        });
        H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HActionPerformed(evt);
            }
        });

        G.setText("G");
        G.setBackground(new java.awt.Color(51, 153, 255));
        G.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        G.setForeground(new java.awt.Color(255, 255, 255));
        G.setToolTipText("");
        G.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GMouseClicked(evt);
            }
        });
        G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GActionPerformed(evt);
            }
        });

        F.setText("F");
        F.setBackground(new java.awt.Color(51, 153, 255));
        F.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        F.setForeground(new java.awt.Color(255, 255, 255));
        F.setToolTipText("");
        F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FMouseClicked(evt);
            }
        });
        F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FActionPerformed(evt);
            }
        });

        D.setText("D");
        D.setBackground(new java.awt.Color(51, 153, 255));
        D.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        D.setForeground(new java.awt.Color(255, 255, 255));
        D.setToolTipText("");
        D.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DMouseClicked(evt);
            }
        });
        D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DActionPerformed(evt);
            }
        });

        S.setText("S");
        S.setBackground(new java.awt.Color(51, 153, 255));
        S.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        S.setForeground(new java.awt.Color(255, 255, 255));
        S.setToolTipText("");
        S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SMouseClicked(evt);
            }
        });
        S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SActionPerformed(evt);
            }
        });

        Z.setText("Z");
        Z.setBackground(new java.awt.Color(51, 153, 255));
        Z.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Z.setForeground(new java.awt.Color(255, 255, 255));
        Z.setToolTipText("");
        Z.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZMouseClicked(evt);
            }
        });
        Z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZActionPerformed(evt);
            }
        });

        TRAC.setText("-");
        TRAC.setBackground(new java.awt.Color(51, 153, 255));
        TRAC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TRAC.setForeground(new java.awt.Color(255, 255, 255));
        TRAC.setToolTipText("");
        TRAC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TRACMouseClicked(evt);
            }
        });
        TRAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRACActionPerformed(evt);
            }
        });

        PONT.setText(".");
        PONT.setBackground(new java.awt.Color(51, 153, 255));
        PONT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PONT.setForeground(new java.awt.Color(255, 255, 255));
        PONT.setToolTipText("");
        PONT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PONTMouseClicked(evt);
            }
        });
        PONT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PONTActionPerformed(evt);
            }
        });

        VIRG.setText(",");
        VIRG.setBackground(new java.awt.Color(51, 153, 255));
        VIRG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        VIRG.setForeground(new java.awt.Color(255, 255, 255));
        VIRG.setToolTipText("");
        VIRG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VIRGMouseClicked(evt);
            }
        });
        VIRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIRGActionPerformed(evt);
            }
        });

        M.setText("M");
        M.setBackground(new java.awt.Color(51, 153, 255));
        M.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        M.setForeground(new java.awt.Color(255, 255, 255));
        M.setToolTipText("");
        M.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MMouseClicked(evt);
            }
        });
        M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MActionPerformed(evt);
            }
        });

        N.setText("N");
        N.setBackground(new java.awt.Color(51, 153, 255));
        N.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        N.setForeground(new java.awt.Color(255, 255, 255));
        N.setToolTipText("");
        N.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NMouseClicked(evt);
            }
        });
        N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NActionPerformed(evt);
            }
        });

        B.setText("B");
        B.setBackground(new java.awt.Color(51, 153, 255));
        B.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        B.setForeground(new java.awt.Color(255, 255, 255));
        B.setToolTipText("");
        B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BMouseClicked(evt);
            }
        });
        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });

        V.setText("V");
        V.setBackground(new java.awt.Color(51, 153, 255));
        V.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        V.setForeground(new java.awt.Color(255, 255, 255));
        V.setToolTipText("");
        V.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VMouseClicked(evt);
            }
        });
        V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VActionPerformed(evt);
            }
        });

        C.setText("C");
        C.setBackground(new java.awt.Color(51, 153, 255));
        C.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        C.setForeground(new java.awt.Color(255, 255, 255));
        C.setToolTipText("");
        C.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMouseClicked(evt);
            }
        });
        C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CActionPerformed(evt);
            }
        });

        X.setText("X");
        X.setBackground(new java.awt.Color(51, 153, 255));
        X.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        X.setForeground(new java.awt.Color(255, 255, 255));
        X.setToolTipText("");
        X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XMouseClicked(evt);
            }
        });
        X.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XActionPerformed(evt);
            }
        });

        SPACE.setText("Espaço");
        SPACE.setBackground(new java.awt.Color(51, 153, 255));
        SPACE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SPACE.setForeground(new java.awt.Color(255, 255, 255));
        SPACE.setToolTipText("");
        SPACE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SPACEMouseClicked(evt);
            }
        });
        SPACE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPACEActionPerformed(evt);
            }
        });

        descritivo.setColumns(20);
        descritivo.setRows(5);
        jScrollPane1.setViewportView(descritivo);

        jLabel9.setText("Descritivo do Pagamento");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Q1.setText("2");
        Q1.setBackground(new java.awt.Color(51, 153, 255));
        Q1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q1.setForeground(new java.awt.Color(255, 255, 255));
        Q1.setToolTipText("");
        Q1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q1MouseClicked(evt);
            }
        });
        Q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q1ActionPerformed(evt);
            }
        });

        Q2.setText("1");
        Q2.setBackground(new java.awt.Color(51, 153, 255));
        Q2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q2.setForeground(new java.awt.Color(255, 255, 255));
        Q2.setToolTipText("");
        Q2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q2MouseClicked(evt);
            }
        });
        Q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q2ActionPerformed(evt);
            }
        });

        Q3.setText("4");
        Q3.setBackground(new java.awt.Color(51, 153, 255));
        Q3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q3.setForeground(new java.awt.Color(255, 255, 255));
        Q3.setToolTipText("");
        Q3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q3MouseClicked(evt);
            }
        });
        Q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q3ActionPerformed(evt);
            }
        });

        Q4.setText("3");
        Q4.setBackground(new java.awt.Color(51, 153, 255));
        Q4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q4.setForeground(new java.awt.Color(255, 255, 255));
        Q4.setToolTipText("");
        Q4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q4MouseClicked(evt);
            }
        });
        Q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q4ActionPerformed(evt);
            }
        });

        Q5.setText("6");
        Q5.setBackground(new java.awt.Color(51, 153, 255));
        Q5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q5.setForeground(new java.awt.Color(255, 255, 255));
        Q5.setToolTipText("");
        Q5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q5MouseClicked(evt);
            }
        });
        Q5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q5ActionPerformed(evt);
            }
        });

        Q6.setText("5");
        Q6.setBackground(new java.awt.Color(51, 153, 255));
        Q6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q6.setForeground(new java.awt.Color(255, 255, 255));
        Q6.setToolTipText("");
        Q6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q6MouseClicked(evt);
            }
        });
        Q6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q6ActionPerformed(evt);
            }
        });

        Q7.setText("7");
        Q7.setBackground(new java.awt.Color(51, 153, 255));
        Q7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q7.setForeground(new java.awt.Color(255, 255, 255));
        Q7.setToolTipText("");
        Q7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q7MouseClicked(evt);
            }
        });
        Q7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q7ActionPerformed(evt);
            }
        });

        Q8.setText("8");
        Q8.setBackground(new java.awt.Color(51, 153, 255));
        Q8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q8.setForeground(new java.awt.Color(255, 255, 255));
        Q8.setToolTipText("");
        Q8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q8MouseClicked(evt);
            }
        });
        Q8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q8ActionPerformed(evt);
            }
        });

        Q9.setText("9");
        Q9.setBackground(new java.awt.Color(51, 153, 255));
        Q9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q9.setForeground(new java.awt.Color(255, 255, 255));
        Q9.setToolTipText("");
        Q9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q9MouseClicked(evt);
            }
        });
        Q9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q9ActionPerformed(evt);
            }
        });

        Q10.setText("0");
        Q10.setBackground(new java.awt.Color(51, 153, 255));
        Q10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Q10.setForeground(new java.awt.Color(255, 255, 255));
        Q10.setToolTipText("");
        Q10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q10MouseClicked(evt);
            }
        });
        Q10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q10ActionPerformed(evt);
            }
        });

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        retroceder_pass13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass13.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(pagamentovalor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(b56, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b55, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b54, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(b51, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b57, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b58, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b52, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b53, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b59, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(bc2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b60, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(be2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(Z, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(X, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(V, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(VIRG, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(SPACE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PONT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TRAC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(G, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(J, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(Q2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Q1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Y, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(U, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(Q4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Q3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Q6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Q5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Q7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Ç, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(Q8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Q9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Q10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(193, 193, 193))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(retroceder_pass13)
                        .addGap(235, 235, 235)
                        .addComponent(jLabel71)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Q1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(W, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Q, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Y, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(U, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(S, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(G, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(J, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Ç, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(X, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Z, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(V, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(VIRG, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PONT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TRAC, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SPACE, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(retroceder_pass13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pagamentovalor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b52, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b53, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(b51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b54, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b55, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b56, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b57, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b58, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b59, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(be2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bc2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b60, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PagamentosLayout = new javax.swing.GroupLayout(Pagamentos);
        Pagamentos.setLayout(PagamentosLayout);
        PagamentosLayout.setHorizontalGroup(
            PagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PagamentosLayout.setVerticalGroup(
            PagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(Pagamentos);

        jPanel12.setBackground(java.awt.SystemColor.activeCaptionBorder);

        nmoeda005.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda005.setText("jLabel6");
        nmoeda005.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 5 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda005.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda010.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda010.setText("jLabel6");
        nmoeda010.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 10 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda010.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda001.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda001.setText("jLabel6");
        nmoeda001.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 1 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda001.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda002.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda002.setText("jLabel6");
        nmoeda002.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 2 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda002.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda020.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda020.setText("jLabel6");
        nmoeda020.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 20 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda020.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda050.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda050.setText("jLabel6");
        nmoeda050.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 50 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda050.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda100.setText("jLabel6");
        nmoeda100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 1.00 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda100.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nmoeda200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nmoeda200.setText("jLabel6");
        nmoeda200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 2.00 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nmoeda200.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel17.setText("Existências | Valor");
        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        nnota20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota20.setText("jLabel6");
        nnota20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 20 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nnota50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota50.setText("jLabel6");
        nnota50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 50 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota50.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nnota5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota5.setText("jLabel6");
        nnota5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 5 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nnota10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota10.setText("jLabel6");
        nnota10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 10 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nnota100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota100.setText("jLabel6");
        nnota100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 100 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota100.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nnota200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota200.setText("jLabel6");
        nnota200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 200 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota200.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nnota500.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nnota500.setText("jLabel6");
        nnota500.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qt | 500 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        nnota500.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel24.setText("Existências | Valor");
        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel18.setText("NOTAS");
        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        jLabel19.setText("MOEDAS");
        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N

        ntotalmoedasnotas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ntotalmoedasnotas.setText("jLabel6");
        ntotalmoedasnotas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL EM CAIXA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N
        ntotalmoedasnotas.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        retroceder_pass5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass5.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass5MouseClicked(evt);
            }
        });

        totalcarregadolb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalcarregadolb.setText("jLabel6");
        totalcarregadolb.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL CARREGADO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(0, 0, 204))); // NOI18N
        totalcarregadolb.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalcarregadolb.setForeground(new java.awt.Color(0, 0, 204));

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(retroceder_pass5)
                            .addGap(40, 40, 40)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nmoeda005, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(nmoeda002, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nmoeda010, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nmoeda001, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nmoeda050, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nmoeda020, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(nmoeda200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nmoeda100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(ntotalmoedasnotas, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35))
                    .addComponent(totalcarregadolb, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nnota20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nnota10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nnota5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nnota50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nnota100, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(nnota200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nnota500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel24)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(retroceder_pass5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nnota5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(nmoeda020, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nnota100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nmoeda001, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nnota10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nmoeda002, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nmoeda050, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nnota200, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nmoeda005, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(nmoeda100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nmoeda010, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(nmoeda200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nnota500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nnota20, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nnota50, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ntotalmoedasnotas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalcarregadolb, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout CarregamentosLayout = new javax.swing.GroupLayout(Carregamentos);
        Carregamentos.setLayout(CarregamentosLayout);
        CarregamentosLayout.setHorizontalGroup(
            CarregamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CarregamentosLayout.setVerticalGroup(
            CarregamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(Carregamentos);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        AcCarregamentos.setText("Carregamentos");
        AcCarregamentos.setBackground(new java.awt.Color(255, 255, 255));
        AcCarregamentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcExistencias.setText("Existências");
        AcExistencias.setBackground(new java.awt.Color(255, 255, 255));
        AcExistencias.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcPainelbordo.setText("Painel de Bordo");
        AcPainelbordo.setBackground(new java.awt.Color(255, 255, 255));
        AcPainelbordo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcFundomaneio.setText("Fundo de Maneio");
        AcFundomaneio.setBackground(new java.awt.Color(255, 255, 255));
        AcFundomaneio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AcFundomaneio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcFundomaneioActionPerformed(evt);
            }
        });

        AcFechoperiodo.setText("Fecho de Periodo");
        AcFechoperiodo.setBackground(new java.awt.Color(255, 255, 255));
        AcFechoperiodo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcPagamentos.setText("Pagamentos");
        AcPagamentos.setBackground(new java.awt.Color(255, 255, 255));
        AcPagamentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcNiveisminimos.setText("Niveis Minimos");
        AcNiveisminimos.setBackground(new java.awt.Color(255, 255, 255));
        AcNiveisminimos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcSupervisor.setText("Supervisor");
        AcSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        AcSupervisor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcEstadoGeral.setText("Estado Geral");
        AcEstadoGeral.setBackground(new java.awt.Color(255, 255, 255));
        AcEstadoGeral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcReinicio.setText("Reinicio");
        AcReinicio.setBackground(new java.awt.Color(255, 255, 255));
        AcReinicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcDefnotas.setText("Def Notas");
        AcDefnotas.setBackground(new java.awt.Color(255, 255, 255));
        AcDefnotas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        AcConsultacofre.setText("Consulta Cofre");
        AcConsultacofre.setBackground(new java.awt.Color(255, 255, 255));
        AcConsultacofre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(AcFechoperiodo)
                                .addGap(50, 50, 50)
                                .addComponent(AcSupervisor)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AcExistencias)
                                    .addComponent(AcCarregamentos)
                                    .addComponent(AcPainelbordo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AcEstadoGeral)
                                    .addComponent(AcDefnotas)
                                    .addComponent(AcConsultacofre))))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(AcPagamentos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(AcNiveisminimos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(AcFundomaneio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AcReinicio)
                        .addGap(91, 91, 91))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcCarregamentos)
                    .addComponent(AcDefnotas))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcExistencias)
                    .addComponent(AcConsultacofre))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcPainelbordo)
                    .addComponent(AcEstadoGeral))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcFundomaneio)
                    .addComponent(AcReinicio))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcFechoperiodo)
                    .addComponent(AcSupervisor))
                .addGap(18, 18, 18)
                .addComponent(AcPagamentos)
                .addGap(14, 14, 14)
                .addComponent(AcNiveisminimos)
                .addContainerGap())
        );

        jLabel2.setText("Acessos para o Operador");
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setText("Operador");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        retroceder_pass3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass3.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass3MouseClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_administrator_male_64.png"))); // NOI18N

        jLabel21.setText("Supervisor");
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton5.setText("Aplicar");
        jButton5.setBackground(new java.awt.Color(51, 153, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        operpass.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        operpass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        operpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operpassMouseClicked(evt);
            }
        });

        superpass.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        superpass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        superpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                superpassMouseClicked(evt);
            }
        });

        jButton1.setText("Esvaziar Reciclador Notas");
        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setText("Esvaziar Reciclador Moedas");
        jButton9.setBackground(new java.awt.Color(51, 153, 255));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        spofwr.setText("jLabel6");

        spodts.setText("jLabel7");

        scsfwr.setText("jLabel100");

        scsdts.setText("jLabel101");

        jButton3.setText("Misturar Moedas");
        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(208, 208, 208))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(retroceder_pass3)
                        .addGap(259, 259, 259)
                        .addComponent(jLabel20)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spofwr)
                            .addComponent(spodts)
                            .addComponent(scsfwr)
                            .addComponent(scsdts)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(superpass, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(operpass, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(retroceder_pass3)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(spofwr)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spodts)
                            .addGap(9, 9, 9)
                            .addComponent(scsfwr))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(scsdts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(superpass, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel21)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel5))
                            .addComponent(operpass, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SupervisorLayout = new javax.swing.GroupLayout(Supervisor);
        Supervisor.setLayout(SupervisorLayout);
        SupervisorLayout.setHorizontalGroup(
            SupervisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SupervisorLayout.setVerticalGroup(
            SupervisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(Supervisor);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, "", null, null, null}
            },
            new String [] {
                "Data", "Hora", "Documento", "Pago", "Valor", "Troco"
            }
        ));
        table.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(table);

        jLabel10.setText("Painel de Bordo Diário");
        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N

        retroceder_consulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_consultaMouseClicked(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fundo Maneio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel10.setForeground(new java.awt.Color(0, 255, 0));

        fundomaneio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fundomaneio.setText("EUR");
        fundomaneio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fundomaneio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(fundomaneio, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fundomaneio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Faturado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        totalfaturado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalfaturado.setText("EUR");
        totalfaturado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalfaturado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(totalfaturado, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalfaturado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Cofre Notas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        totalcofre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalcofre.setText("EUR");
        totalcofre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalcofre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(totalcofre, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalcofre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Pagamentos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        totalpagamentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalpagamentos.setText("EUR");
        totalpagamentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalpagamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(totalpagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalpagamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Carregamentos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        totalcarregamentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalcarregamentos.setText("EUR");
        totalcarregamentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalcarregamentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(totalcarregamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalcarregamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        datePicker1.setBackground(java.awt.SystemColor.activeCaptionBorder);

        jButton6.setText("Carregar Dia");
        jButton6.setActionCommand("");
        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(retroceder_consulta)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(datePicker1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_consulta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout ecratransacoesLayout = new javax.swing.GroupLayout(ecratransacoes);
        ecratransacoes.setLayout(ecratransacoesLayout);
        ecratransacoesLayout.setHorizontalGroup(
            ecratransacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ecratransacoesLayout.setVerticalGroup(
            ecratransacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(ecratransacoes);

        jPanel20.setBackground(java.awt.SystemColor.activeCaptionBorder);

        jLabel11.setText("Cofre Removido!!!");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Saque de Notas?");
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        valorsaque.setBackground(java.awt.SystemColor.activeCaptionBorder);
        valorsaque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Introduza Pass Supervisor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        valorsaque.setCaretColor(new java.awt.Color(255, 255, 255));

        b10.setText("1");
        b10.setBackground(new java.awt.Color(255, 255, 255));
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
        b11.setBackground(new java.awt.Color(255, 255, 255));
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
        b12.setBackground(new java.awt.Color(255, 255, 255));
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
        b13.setBackground(new java.awt.Color(255, 255, 255));
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
        b14.setBackground(new java.awt.Color(255, 255, 255));
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
        b15.setBackground(new java.awt.Color(255, 255, 255));
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
        b16.setBackground(new java.awt.Color(255, 255, 255));
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
        b17.setBackground(new java.awt.Color(255, 255, 255));
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
        b18.setBackground(new java.awt.Color(255, 255, 255));
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
        be1.setBackground(new java.awt.Color(255, 255, 255));
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
        b19.setBackground(new java.awt.Color(255, 255, 255));
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
        bc1.setBackground(new java.awt.Color(255, 255, 255));
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

        retroceder_pass4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass4.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorsaque, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(b15, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bc1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b19, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(be1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b18, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(91, 91, 91))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel11))
                    .addComponent(retroceder_pass4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(116, 116, 116)
                        .addComponent(valorsaque, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b15, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b18, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bc1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b19, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(be1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))))
        );

        javax.swing.GroupLayout cofre_removidoLayout = new javax.swing.GroupLayout(cofre_removido);
        cofre_removido.setLayout(cofre_removidoLayout);
        cofre_removidoLayout.setHorizontalGroup(
            cofre_removidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cofre_removidoLayout.setVerticalGroup(
            cofre_removidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(cofre_removido);

        SplashScreen.setPreferredSize(new java.awt.Dimension(836, 508));
        SplashScreen.setLayout(new java.awt.CardLayout());

        jPanel19.setBackground(new java.awt.Color(0, 0, 0));

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iotgr.png"))); // NOI18N
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        jLabel57.setText("Soluções de Pagamentos Automáticos");
        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));

        jLabel37.setText("Stay Safe ");
        jLabel37.setFont(new java.awt.Font("Arial Nova", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(53, 148, 197));

        jLabel36.setText("Stay Secure");
        jLabel36.setFont(new java.awt.Font("Arial Nova", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/QRCODE.png"))); // NOI18N
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(279, 279, 279))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addGap(115, 115, 115))
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(103, 103, 103)
                        .addComponent(jLabel49)
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel57)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36))
                        .addGap(0, 64, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel49)))
                .addContainerGap())
        );

        SplashScreen.add(jPanel19, "card2");

        getContentPane().add(SplashScreen);

        ecraPagamentos.setBackground(new java.awt.Color(255, 255, 255));
        ecraPagamentos.setMaximumSize(new java.awt.Dimension(800, 480));
        ecraPagamentos.setPreferredSize(new java.awt.Dimension(800, 480));
        ecraPagamentos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ecraPagamentosFocusGained(evt);
            }
        });

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        jPanel25.setBackground(new java.awt.Color(102, 102, 102));

        horas.setText("iThink - Soluções de Pagamentos Automáticos");
        horas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        horas1.setText("Sabado, 2 Fevereiro 2020, 14:25");
        horas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horas1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(horas1)
                .addGap(23, 23, 23))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horas1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        moeda1cimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1cent.png"))); // NOI18N
        moeda1cimg.setText("jLabel39");

        nota5img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir.png"))); // NOI18N
        nota5img.setText("jLabel2");

        nota10img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10eur.png"))); // NOI18N
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

        nota500img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/500Nochange.png"))); // NOI18N
        nota500img.setText("jLabel31");

        moeda2img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2eur.png"))); // NOI18N
        moeda2img.setText("jLabel34");
        moeda2img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moeda2imgMouseClicked(evt);
            }
        });

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

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VALOR A PAGAR", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        apagarlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        apagarlabel.setText("38.25 EUR");
        apagarlabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        apagarlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apagarlabelMouseClicked(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_cash_in_hand_64.png"))); // NOI18N

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
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
                .addComponent(jLabel28)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INTRODUZIDO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        pagolabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pagolabel2.setText("0 EUR");
        pagolabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_request_money_48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
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
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EM FALTA", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        faltapagarlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        faltapagarlabel.setText("0 EUR");
        faltapagarlabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_payment_history_48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(faltapagarlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(faltapagarlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TROCO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        trocolabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trocolabel.setText("0 EUR");
        trocolabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(trocolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trocolabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("CANCELAR");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ecraPagamentosLayout = new javax.swing.GroupLayout(ecraPagamentos);
        ecraPagamentos.setLayout(ecraPagamentosLayout);
        ecraPagamentosLayout.setHorizontalGroup(
            ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                        .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel26)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ecraPagamentosLayout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(ecraPagamentosLayout.createSequentialGroup()
                        .addGroup(ecraPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ecraPagamentosLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(ecraPagamentos);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        retroceder_pass7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass7.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass7MouseClicked(evt);
            }
        });

        jLabel61.setText("Fecho de Periodo");
        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel64.setText("Total na Maquina           >");
        jLabel64.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        totalmaquinafecho.setText("0 EUR");
        totalmaquinafecho.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        jLabel74.setText("Fundo Maneio Definido  > ");
        jLabel74.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        fundomaneiodef.setText("0 EUR");
        fundomaneiodef.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        jLabel76.setText("Valor do Periodo             >");
        jLabel76.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        valorperiodo.setText("0 EUR");
        valorperiodo.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        totalcofrefecho.setText("0 EUR");
        totalcofrefecho.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        jLabel77.setText("Total em Cofre                >");
        jLabel77.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/imgfechovalor.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fechoDenom.png"))); // NOI18N
        jLabel97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel97MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fundomaneiodef)
                            .addComponent(totalmaquinafecho)
                            .addComponent(valorperiodo)
                            .addComponent(totalcofrefecho))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel97))
                        .addContainerGap(78, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(retroceder_pass7)
                        .addGap(186, 186, 186)
                        .addComponent(jLabel61)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(0, 105, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(43, 43, 43)
                .addComponent(jLabel97)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_pass7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel61)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(fundomaneiodef))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(totalmaquinafecho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(valorperiodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(totalcofrefecho))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout RetirarFundosLayout = new javax.swing.GroupLayout(RetirarFundos);
        RetirarFundos.setLayout(RetirarFundosLayout);
        RetirarFundosLayout.setHorizontalGroup(
            RetirarFundosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RetirarFundosLayout.setVerticalGroup(
            RetirarFundosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(RetirarFundos);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        c1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        c1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        c1.setText("50");
        c1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        c1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c1MouseClicked(evt);
            }
        });

        c10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        c10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        c10.setText("50");
        c10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        c10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c10MouseClicked(evt);
            }
        });

        c50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        c50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        c50.setText("50");
        c50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        c50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c50MouseClicked(evt);
            }
        });

        c20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        c20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        c20.setText("50");
        c20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        c20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c20MouseClicked(evt);
            }
        });

        c2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        c2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        c2.setText("50");
        c2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        c2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c2MouseClicked(evt);
            }
        });

        e1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        e1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        e1.setText("50");
        e1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        e1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        e1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                e1MouseClicked(evt);
            }
        });

        e2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        e2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        e2.setText("50");
        e2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        e2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        e2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                e2MouseClicked(evt);
            }
        });

        n5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n5.setText("   50");
        n5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n5MouseClicked(evt);
            }
        });

        n10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n10.setText("   50");
        n10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n10MouseClicked(evt);
            }
        });

        n20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n20.setText("   50");
        n20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n20MouseClicked(evt);
            }
        });

        n50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n50.setText("   50");
        n50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n50MouseClicked(evt);
            }
        });

        n100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n100.setText("   50");
        n100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "100 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n100MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                n100MouseEntered(evt);
            }
        });

        n200.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n200.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n200.setText("   50");
        n200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "200 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n200.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n200.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n200MouseClicked(evt);
            }
        });

        n500.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        n500.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        n500.setText("   50");
        n500.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "500 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        n500.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        n500.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n500MouseClicked(evt);
            }
        });

        fmaneiodef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fmaneiodef.setText("0 EUR");
        fmaneiodef.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FUNDO MANEIO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        fmaneiodef.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        retroceder_pass11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass11.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass11MouseClicked(evt);
            }
        });

        jLabel60.setText("DEFINIR FUNDO de MANEIO");
        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        c5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        c5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        c5.setText("50");
        c5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        c5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(retroceder_pass11)
                        .addGap(185, 185, 185)
                        .addComponent(jLabel60)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c50, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c20, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(n50, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(n10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(n20, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(n500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fmaneiodef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(n200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(n100, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder_pass11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel60)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n100, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n200, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c50, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n500, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n50, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fmaneiodef, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DefinirFManeioLayout = new javax.swing.GroupLayout(DefinirFManeio);
        DefinirFManeio.setLayout(DefinirFManeioLayout);
        DefinirFManeioLayout.setHorizontalGroup(
            DefinirFManeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DefinirFManeioLayout.setVerticalGroup(
            DefinirFManeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(DefinirFManeio);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel62.setText("Consulta de Valores em Cofre");
        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        cc5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc5.setText("50 | 250 EUR");
        cc5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cc10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc10.setText("50 | 250 EUR");
        cc10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cc20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc20.setText("50 | 250 EUR");
        cc20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cc50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc50.setText("50 | 250 EUR");
        cc50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cc100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc100.setText("50 | 250 EUR");
        cc100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "100 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cc200.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc200.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc200.setText("50 | 250 EUR");
        cc200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "200 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc200.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cc500.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cc500.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        cc500.setText("50 | 250 EUR");
        cc500.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "500 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        cc500.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        retroceder_consulta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_consulta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_consulta1MouseClicked(evt);
            }
        });

        jButton39.setText("Retirar Fundos");
        jButton39.setBackground(new java.awt.Color(51, 153, 255));
        jButton39.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        ccvalortotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ccvalortotal.setText("0 EUR");
        ccvalortotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N
        ccvalortotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_consulta1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel62)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cc5, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(cc10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cc20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cc50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cc100, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(cc200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cc500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ccvalortotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel62)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cc5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cc100, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cc200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cc10, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cc20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cc500, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ccvalortotal, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cc50, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(retroceder_consulta1)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ConsultaCofreLayout = new javax.swing.GroupLayout(ConsultaCofre);
        ConsultaCofre.setLayout(ConsultaCofreLayout);
        ConsultaCofreLayout.setHorizontalGroup(
            ConsultaCofreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ConsultaCofreLayout.setVerticalGroup(
            ConsultaCofreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(ConsultaCofre);

        panel1.setBackground(new java.awt.Color(0, 0, 0));

        panel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel3.setBackground(new java.awt.Color(51, 51, 51));
        panel3.setMaximumSize(new java.awt.Dimension(396, 43));
        panel3.setMinimumSize(new java.awt.Dimension(396, 43));
        panel3.setPreferredSize(new java.awt.Dimension(396, 43));

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel4.setBackground(java.awt.SystemColor.activeCaptionBorder);

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_next_page_48.png"))); // NOI18N
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });
        jLabel43.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel43KeyPressed(evt);
            }
        });

        jLabel75.setText("Fundo Maneio Definido");
        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(53, 148, 197));

        fundomaneiodef1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fundomaneiodef1.setText("0 EUR");
        fundomaneiodef1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fundomaneiodef1.setForeground(new java.awt.Color(53, 148, 197));

        jLabel65.setText("Total na Maquina");
        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(53, 148, 197));

        totalmaquinafecho1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalmaquinafecho1.setText("0 EUR");
        totalmaquinafecho1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalmaquinafecho1.setForeground(new java.awt.Color(53, 148, 197));

        jLabel78.setText("Valor do Periodo");
        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(53, 148, 197));

        valorperiodo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        valorperiodo1.setText("0 EUR");
        valorperiodo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valorperiodo1.setForeground(new java.awt.Color(53, 148, 197));

        jLabel79.setText("Total em Cofre");
        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(53, 148, 197));

        totalcofrefecho1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalcofrefecho1.setText("0 EUR");
        totalcofrefecho1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalcofrefecho1.setForeground(new java.awt.Color(53, 148, 197));

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addComponent(jLabel65)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79))
                        .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalcofrefecho1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valorperiodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fundomaneiodef1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalmaquinafecho1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel75)
                    .addComponent(fundomaneiodef1))
                .addGap(18, 18, 18)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(totalmaquinafecho1))
                .addGap(18, 18, 18)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(valorperiodo1))
                .addGap(18, 18, 18)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalcofrefecho1)
                    .addComponent(jLabel79))
                .addGap(22, 22, 22)
                .addComponent(jLabel43)
                .addContainerGap())
        );

        panel5.setBackground(new java.awt.Color(51, 51, 51));
        panel5.setMaximumSize(new java.awt.Dimension(396, 43));
        panel5.setMinimumSize(new java.awt.Dimension(396, 43));
        panel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout EstadoGeralLayout = new javax.swing.GroupLayout(EstadoGeral);
        EstadoGeral.setLayout(EstadoGeralLayout);
        EstadoGeralLayout.setHorizontalGroup(
            EstadoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EstadoGeralLayout.setVerticalGroup(
            EstadoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(EstadoGeral);

        EndPayment.setBackground(new java.awt.Color(255, 255, 255));
        EndPayment.setPreferredSize(new java.awt.Dimension(800, 480));

        jLabel39.setText("Pagamento Concluido com Sucesso");
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel41.setText("Agradecemos a sua Preferência");
        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        troco2.setText("0 EUR");
        troco2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        quantia.setText("Retire o seu troco p.f.");
        quantia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trocot.png"))); // NOI18N

        troconotas.setText("0 EUR");
        troconotas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        troconotas.setForeground(new java.awt.Color(51, 153, 255));

        trocomoedas.setText("0 EUR");
        trocomoedas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        trocomoedas.setForeground(new java.awt.Color(51, 153, 255));

        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_64.png"))); // NOI18N

        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N

        javax.swing.GroupLayout EndPaymentLayout = new javax.swing.GroupLayout(EndPayment);
        EndPayment.setLayout(EndPaymentLayout);
        EndPaymentLayout.setHorizontalGroup(
            EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EndPaymentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(troco2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(EndPaymentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addGroup(EndPaymentLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103)
                            .addComponent(troconotas))))
                .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EndPaymentLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quantia)
                        .addContainerGap(296, Short.MAX_VALUE))
                    .addGroup(EndPaymentLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel39))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(EndPaymentLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(116, 116, 116)
                        .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trocomoedas)
                            .addComponent(jLabel102))
                        .addGap(75, 75, 75))))
        );
        EndPaymentLayout.setVerticalGroup(
            EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EndPaymentLayout.createSequentialGroup()
                .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EndPaymentLayout.createSequentialGroup()
                        .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EndPaymentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel38))
                            .addGroup(EndPaymentLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel39)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EndPaymentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EndPaymentLayout.createSequentialGroup()
                                .addComponent(jLabel102)
                                .addGap(50, 50, 50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EndPaymentLayout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addGap(48, 48, 48)))
                        .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(troconotas)
                            .addComponent(trocomoedas))
                        .addGap(10, 10, 10)))
                .addGroup(EndPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(quantia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(troco2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addContainerGap())
        );

        getContentPane().add(EndPayment);

        jPanel13.setBackground(java.awt.SystemColor.activeCaptionBorder);

        jLabel44.setText("Pagamento de Premio de Raspadinha");
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/novoseculo.jpg"))); // NOI18N

        jLabel46.setText("O Novo Seculo Agradece a sua Preferência");
        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        raspeur.setText("jLabel48");
        raspeur.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel44))
                    .addComponent(jLabel47)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel45)
                        .addGap(47, 47, 47)
                        .addComponent(raspeur, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel46)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(raspeur, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pag_raspLayout = new javax.swing.GroupLayout(pag_rasp);
        pag_rasp.setLayout(pag_raspLayout);
        pag_raspLayout.setHorizontalGroup(
            pag_raspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pag_raspLayout.setVerticalGroup(
            pag_raspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pag_rasp);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        nv1c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv1c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv1c.setText("50");
        nv1c.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv1c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv1c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv1cMouseClicked(evt);
            }
        });

        nv10c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv10c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv10c.setText("50");
        nv10c.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv10c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv10c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv10cMouseClicked(evt);
            }
        });

        nv50c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv50c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv50c.setText("50");
        nv50c.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv50c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv50c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv50cMouseClicked(evt);
            }
        });

        nv20c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv20c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv20c.setText("50");
        nv20c.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv20c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv20c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv20cMouseClicked(evt);
            }
        });

        nv2c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv2c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv2c.setText("50");
        nv2c.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv2c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv2c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv2cMouseClicked(evt);
            }
        });

        nv1e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv1e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv1e.setText("50");
        nv1e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv1e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv1e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv1eMouseClicked(evt);
            }
        });

        nv2e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv2e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv2e.setText("50");
        nv2e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv2e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv2e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv2eMouseClicked(evt);
            }
        });

        nv5e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv5e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        nv5e.setText("   50");
        nv5e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv5e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv5e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv5eMouseClicked(evt);
            }
        });

        nv10e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv10e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        nv10e.setText("   50");
        nv10e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv10e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv10e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv10eMouseClicked(evt);
            }
        });

        nv20e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv20e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        nv20e.setText("   50");
        nv20e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv20e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv20e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv20eMouseClicked(evt);
            }
        });

        nv50e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv50e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        nv50e.setText("   50");
        nv50e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv50e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv50e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv50eMouseClicked(evt);
            }
        });

        nv100e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv100e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        nv100e.setText("   50");
        nv100e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "100 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv100e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv100e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv100eMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nv100eMouseEntered(evt);
            }
        });

        nv200e.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv200e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_48.png"))); // NOI18N
        nv200e.setText("   50");
        nv200e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "200 EUR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv200e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv200e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv200eMouseClicked(evt);
            }
        });

        retroceder_pass12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass12.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass12MouseClicked(evt);
            }
        });

        jLabel63.setText("Niveis Minimos Recicladores");
        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        nv5c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nv5c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_euro_48.png"))); // NOI18N
        nv5c.setText("50");
        nv5c.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 CENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        nv5c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nv5c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nv5cMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(retroceder_pass12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel63)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(0, 41, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nv2c, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nv1c, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nv50c, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nv1e, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nv20c, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nv5c, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nv10c, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(nv2e, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nv50e, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nv10e, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nv5e, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nv20e, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nv200e, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nv100e, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder_pass12)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv20c, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv5e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv100e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv1c, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv200e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv10e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv50c, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv2c, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv20e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv1e, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv5c, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nv50e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv2e, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nv10c, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Niveis_MinimosLayout = new javax.swing.GroupLayout(Niveis_Minimos);
        Niveis_Minimos.setLayout(Niveis_MinimosLayout);
        Niveis_MinimosLayout.setHorizontalGroup(
            Niveis_MinimosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Niveis_MinimosLayout.setVerticalGroup(
            Niveis_MinimosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(Niveis_Minimos);

        Jampanel.setPreferredSize(new java.awt.Dimension(836, 508));
        Jampanel.setLayout(new java.awt.CardLayout());

        jPanel22.setBackground(java.awt.SystemColor.activeCaptionBorder);

        jammessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jammessage.setText("jLabel13");
        jammessage.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jammessage.setForeground(new java.awt.Color(255, 0, 51));

        jammessage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jammessage2.setText("jLabel6");
        jammessage2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jammessage2.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jammessage, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jammessage2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jammessage)
                .addGap(132, 132, 132)
                .addComponent(jammessage2)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        Jampanel.add(jPanel22, "card2");

        getContentPane().add(Jampanel);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setMinimumSize(new java.awt.Dimension(800, 480));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1CENTR.png"))); // NOI18N

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2CENTR.png"))); // NOI18N

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5CENTR.png"))); // NOI18N

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10CENTR.png"))); // NOI18N

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20CENTR.png"))); // NOI18N

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50CENTR.png"))); // NOI18N

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1EURR.png"))); // NOI18N

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2eurnova.png"))); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jLabel54)
                    .addComponent(jLabel53)
                    .addComponent(jLabel52)
                    .addComponent(jLabel51)
                    .addComponent(jLabel22)
                    .addComponent(jLabel48)
                    .addComponent(jLabel50))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pb1c.setOrientation(1);
        pb1c.setValue(30);
        pb1c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green));
        pb1c.setBorderPainted(false);
        pb1c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb1c.setStringPainted(true);

        pb2c.setOrientation(1);
        pb2c.setValue(48);
        pb2c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb2c.setBorderPainted(false);
        pb2c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb2c.setStringPainted(true);

        pb5c.setOrientation(1);
        pb5c.setValue(90);
        pb5c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb5c.setBorderPainted(false);
        pb5c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb5c.setStringPainted(true);

        pb10c.setOrientation(1);
        pb10c.setValue(25);
        pb10c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb10c.setBorderPainted(false);
        pb10c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb10c.setStringPainted(true);

        pb20c.setOrientation(1);
        pb20c.setValue(40);
        pb20c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb20c.setBorderPainted(false);
        pb20c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb20c.setStringPainted(true);

        pb50c.setOrientation(1);
        pb50c.setValue(55);
        pb50c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb50c.setBorderPainted(false);
        pb50c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb50c.setStringPainted(true);
        pb50c.setToolTipText("");

        pb1e.setOrientation(1);
        pb1e.setValue(8);
        pb1e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb1e.setBorderPainted(false);
        pb1e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb1e.setStringPainted(true);

        pb2e.setOrientation(1);
        pb2e.setValue(12);
        pb2e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb2e.setBorderPainted(false);
        pb2e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb2e.setStringPainted(true);

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5EURR.png"))); // NOI18N

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10EURR.png"))); // NOI18N

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20EURR.png"))); // NOI18N

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50EURR.png"))); // NOI18N

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iThink_app_V10/100EURR.png"))); // NOI18N
        jLabel68.setToolTipText("");

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iThink_app_V10/200EURR.png"))); // NOI18N

        pb5e.setOrientation(1);
        pb5e.setValue(50);
        pb5e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb5e.setBorderPainted(false);
        pb5e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb5e.setStringPainted(true);

        pb10e.setOrientation(1);
        pb10e.setValue(70);
        pb10e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb10e.setBorderPainted(false);
        pb10e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb10e.setStringPainted(true);

        pb20e.setOrientation(1);
        pb20e.setValue(40);
        pb20e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb20e.setBorderPainted(false);
        pb20e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb20e.setStringPainted(true);

        pb50e.setOrientation(1);
        pb50e.setValue(10);
        pb50e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb50e.setBorderPainted(false);
        pb50e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb50e.setStringPainted(true);

        pb100e.setOrientation(1);
        pb100e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb100e.setBorderPainted(false);
        pb100e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb100e.setStringPainted(true);

        pb200e.setOrientation(1);
        pb200e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pb200e.setBorderPainted(false);
        pb200e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pb200e.setStringPainted(true);

        retroceder_pass8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass8.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass8MouseClicked(evt);
            }
        });

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        lblcarregaV2.setText("Carregamento de Valores");
        lblcarregaV2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel72.setText("Total Caixa");

        totcarregav2.setText("999 EUR");
        totcarregav2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totcarregav2.setForeground(new java.awt.Color(0, 204, 51));

        jLabel73.setText("Total Carregado");

        totatual.setText("999 EUR");
        totatual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Un1c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un1c.setText("999");
        Un1c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un1c.setForeground(new java.awt.Color(0, 0, 255));

        Un2c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un2c.setText("999");
        Un2c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un2c.setForeground(new java.awt.Color(0, 0, 255));

        Un5c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un5c.setText("999");
        Un5c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un5c.setForeground(new java.awt.Color(0, 0, 255));

        Un10c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un10c.setText("999");
        Un10c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un10c.setForeground(new java.awt.Color(0, 0, 255));

        Un20c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un20c.setText("999");
        Un20c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un20c.setForeground(new java.awt.Color(0, 0, 255));

        Un50c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un50c.setText("999");
        Un50c.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un50c.setForeground(new java.awt.Color(0, 0, 255));

        Un1e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un1e.setText("999");
        Un1e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un1e.setForeground(new java.awt.Color(0, 0, 255));

        Un2e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un2e.setText("999");
        Un2e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un2e.setForeground(new java.awt.Color(0, 0, 255));

        Un50e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un50e.setText("999");
        Un50e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un50e.setForeground(new java.awt.Color(0, 0, 255));

        Un100e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un100e.setText("999");
        Un100e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un100e.setForeground(new java.awt.Color(0, 0, 255));

        Un200e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un200e.setText("999");
        Un200e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un200e.setForeground(new java.awt.Color(0, 0, 255));

        Un5e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un5e.setText("999");
        Un5e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un5e.setForeground(new java.awt.Color(0, 0, 255));

        Un10e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un10e.setText("999");
        Un10e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un10e.setForeground(new java.awt.Color(0, 0, 255));

        Un20e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Un20e.setText("999");
        Un20e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Un20e.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(retroceder_pass8)
                        .addGap(27, 27, 27)
                        .addComponent(lblcarregaV2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totcarregav2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totatual, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel70)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 28, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(pb1c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb2c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb5c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb10c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb20c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb50c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb1e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb2e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(pb5e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pb10e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(pb20e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(jLabel56)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel59)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel66))
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(jLabel67)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel68)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel69)))
                                .addContainerGap(24, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pb50e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Un5e))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(Un10e)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Un20e))
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(pb100e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pb200e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54))))))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Un1c)
                .addGap(21, 21, 21)
                .addComponent(Un2c)
                .addGap(20, 20, 20)
                .addComponent(Un5c)
                .addGap(21, 21, 21)
                .addComponent(Un10c)
                .addGap(22, 22, 22)
                .addComponent(Un20c)
                .addGap(21, 21, 21)
                .addComponent(Un50c)
                .addGap(22, 22, 22)
                .addComponent(Un1e)
                .addGap(23, 23, 23)
                .addComponent(Un2e)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Un50e)
                .addGap(78, 78, 78)
                .addComponent(Un100e)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Un200e)
                .addGap(57, 57, 57))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblcarregaV2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(retroceder_pass8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel72)
                                    .addComponent(totatual))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel73)
                                    .addComponent(totcarregav2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pb1c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb2c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb5c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb10c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb20c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb50c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb1e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb2e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pb10e, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(pb5e, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(pb20e, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel66)
                            .addComponent(jLabel56)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Un5e)
                                    .addComponent(Un10e))
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                                .addComponent(Un20e)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pb50e, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb100e, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pb200e, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68))))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Un1c)
                            .addComponent(Un2c)
                            .addComponent(Un5c)
                            .addComponent(Un10c)
                            .addComponent(Un20c)
                            .addComponent(Un50c)
                            .addComponent(Un1e)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Un50e)
                            .addComponent(Un100e)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Un200e))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(Un2e)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CarregaV2Layout = new javax.swing.GroupLayout(CarregaV2);
        CarregaV2.setLayout(CarregaV2Layout);
        CarregaV2Layout.setHorizontalGroup(
            CarregaV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CarregaV2Layout.setVerticalGroup(
            CarregaV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(CarregaV2);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setMinimumSize(new java.awt.Dimension(800, 480));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1CENTR.png"))); // NOI18N

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2CENTR.png"))); // NOI18N

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5CENTR.png"))); // NOI18N

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10CENTR.png"))); // NOI18N

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20CENTR.png"))); // NOI18N

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50CENTR.png"))); // NOI18N

        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1EURR.png"))); // NOI18N

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2eurnova.png"))); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel87)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87)
                    .addComponent(jLabel86)
                    .addComponent(jLabel85)
                    .addComponent(jLabel84)
                    .addComponent(jLabel83)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cpb1c.setOrientation(1);
        cpb1c.setValue(30);
        cpb1c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green));
        cpb1c.setBorderPainted(false);
        cpb1c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb1c.setStringPainted(true);
        cpb1c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb1cMouseClicked(evt);
            }
        });

        cpb2c.setOrientation(1);
        cpb2c.setValue(48);
        cpb2c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb2c.setBorderPainted(false);
        cpb2c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb2c.setStringPainted(true);
        cpb2c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb2cMouseClicked(evt);
            }
        });

        cpb5c.setOrientation(1);
        cpb5c.setValue(90);
        cpb5c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb5c.setBorderPainted(false);
        cpb5c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb5c.setStringPainted(true);
        cpb5c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb5cMouseClicked(evt);
            }
        });

        cpb10c.setOrientation(1);
        cpb10c.setValue(25);
        cpb10c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb10c.setBorderPainted(false);
        cpb10c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb10c.setStringPainted(true);
        cpb10c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb10cMouseClicked(evt);
            }
        });

        cpb20c.setOrientation(1);
        cpb20c.setValue(40);
        cpb20c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb20c.setBorderPainted(false);
        cpb20c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb20c.setStringPainted(true);
        cpb20c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb20cMouseClicked(evt);
            }
        });

        cpb50c.setOrientation(1);
        cpb50c.setValue(55);
        cpb50c.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb50c.setBorderPainted(false);
        cpb50c.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb50c.setStringPainted(true);
        cpb50c.setToolTipText("");
        cpb50c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb50cMouseClicked(evt);
            }
        });

        cpb1e.setOrientation(1);
        cpb1e.setValue(8);
        cpb1e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb1e.setBorderPainted(false);
        cpb1e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb1e.setStringPainted(true);
        cpb1e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb1eMouseClicked(evt);
            }
        });

        cpb2e.setOrientation(1);
        cpb2e.setValue(12);
        cpb2e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb2e.setBorderPainted(false);
        cpb2e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb2e.setStringPainted(true);
        cpb2e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb2eMouseClicked(evt);
            }
        });

        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5EURR.png"))); // NOI18N

        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10EURR.png"))); // NOI18N

        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20EURR.png"))); // NOI18N

        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50EURR.png"))); // NOI18N

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iThink_app_V10/100EURR.png"))); // NOI18N
        jLabel92.setToolTipText("");

        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iThink_app_V10/200EURR.png"))); // NOI18N

        cpb5e.setOrientation(1);
        cpb5e.setValue(50);
        cpb5e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb5e.setBorderPainted(false);
        cpb5e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb5e.setStringPainted(true);
        cpb5e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb5eMouseClicked(evt);
            }
        });

        cpb10e.setOrientation(1);
        cpb10e.setValue(70);
        cpb10e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb10e.setBorderPainted(false);
        cpb10e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb10e.setStringPainted(true);
        cpb10e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb10eMouseClicked(evt);
            }
        });

        cpb20e.setOrientation(1);
        cpb20e.setValue(40);
        cpb20e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb20e.setBorderPainted(false);
        cpb20e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb20e.setStringPainted(true);
        cpb20e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb20eMouseClicked(evt);
            }
        });

        cpb50e.setOrientation(1);
        cpb50e.setValue(10);
        cpb50e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb50e.setBorderPainted(false);
        cpb50e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb50e.setStringPainted(true);
        cpb50e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb50eMouseClicked(evt);
            }
        });

        cpb100e.setOrientation(1);
        cpb100e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb100e.setBorderPainted(false);
        cpb100e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb100e.setStringPainted(true);
        cpb100e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb100eMouseClicked(evt);
            }
        });

        cpb200e.setOrientation(1);
        cpb200e.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpb200e.setBorderPainted(false);
        cpb200e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cpb200e.setStringPainted(true);
        cpb200e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpb200eMouseClicked(evt);
            }
        });

        retroceder_pass9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass9.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass9MouseClicked(evt);
            }
        });

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        jLabel96.setText("Total Caixa");
        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        ctotatual.setText("999 EUR");
        ctotatual.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ctotatual.setForeground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(cpb1c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb2c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb5c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb10c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb20c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb50c, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb1e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpb2e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(cpb50e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(cpb100e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cpb200e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(cpb5e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cpb10e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cpb20e, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel91)
                                    .addComponent(jLabel88))
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel92)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel93))
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel89)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel90)))
                                .addGap(31, 47, Short.MAX_VALUE))))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(retroceder_pass9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel94)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel96)
                        .addGap(18, 18, 18)
                        .addComponent(ctotatual, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder_pass9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel96)
                            .addComponent(ctotatual))
                        .addComponent(jLabel94)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpb1c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb2c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb5c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb10c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb20c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb50c, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb1e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb2e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cpb5e, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb10e, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb20e, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel90)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpb50e, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb100e, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpb200e, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel93)
                            .addComponent(jLabel91)
                            .addComponent(jLabel92))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ConsultaV2Layout = new javax.swing.GroupLayout(ConsultaV2);
        ConsultaV2.setLayout(ConsultaV2Layout);
        ConsultaV2Layout.setHorizontalGroup(
            ConsultaV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ConsultaV2Layout.setVerticalGroup(
            ConsultaV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(ConsultaV2);

        ecraconfigV2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(51, 153, 255))); // NOI18N

        but_float5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_information_48.png"))); // NOI18N
        but_float5.setText("Consulta Cofre");
        but_float5.setBackground(new java.awt.Color(255, 255, 255));
        but_float5.setBorder(null);
        but_float5.setContentAreaFilled(false);
        but_float5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float5.setForeground(new java.awt.Color(51, 153, 255));
        but_float5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float5.setOpaque(true);
        but_float5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_float5ActionPerformed(evt);
            }
        });

        but_consultas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_information_48.png"))); // NOI18N
        but_consultas2.setText("Existências");
        but_consultas2.setBackground(new java.awt.Color(255, 255, 255));
        but_consultas2.setBorder(null);
        but_consultas2.setContentAreaFilled(false);
        but_consultas2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_consultas2.setForeground(new java.awt.Color(0, 153, 255));
        but_consultas2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_consultas2.setOpaque(true);
        but_consultas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_consultas2ActionPerformed(evt);
            }
        });

        But_painelbordo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_combo_chart_48.png"))); // NOI18N
        But_painelbordo1.setText("Painel Bordo");
        But_painelbordo1.setBackground(new java.awt.Color(255, 255, 255));
        But_painelbordo1.setBorder(null);
        But_painelbordo1.setContentAreaFilled(false);
        But_painelbordo1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        But_painelbordo1.setForeground(new java.awt.Color(51, 153, 255));
        But_painelbordo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        But_painelbordo1.setOpaque(true);
        But_painelbordo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But_painelbordo1ActionPerformed(evt);
            }
        });

        but_float6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_statistics_48.png"))); // NOI18N
        but_float6.setText("Estado Geral");
        but_float6.setBackground(new java.awt.Color(255, 255, 255));
        but_float6.setBorder(null);
        but_float6.setContentAreaFilled(false);
        but_float6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float6.setForeground(new java.awt.Color(51, 153, 255));
        but_float6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float6.setOpaque(true);
        but_float6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_float6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(but_float6, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_float5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But_painelbordo1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_consultas2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(But_painelbordo1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(but_consultas2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(but_float5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(but_float6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configurações", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(51, 153, 255))); // NOI18N

        but_emptyall1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_settings_48.png"))); // NOI18N
        but_emptyall1.setText("Definições  Notas");
        but_emptyall1.setBackground(new java.awt.Color(255, 255, 255));
        but_emptyall1.setBorder(null);
        but_emptyall1.setContentAreaFilled(false);
        but_emptyall1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_emptyall1.setForeground(new java.awt.Color(51, 153, 255));
        but_emptyall1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_emptyall1.setOpaque(true);
        but_emptyall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_emptyall1ActionPerformed(evt);
            }
        });

        but_trocos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_exchange_euro_48.png"))); // NOI18N
        but_trocos1.setText("Niveis Minimos");
        but_trocos1.setBackground(new java.awt.Color(255, 255, 255));
        but_trocos1.setBorder(null);
        but_trocos1.setContentAreaFilled(false);
        but_trocos1.setDisabledIcon(null);
        but_trocos1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_trocos1.setForeground(new java.awt.Color(0, 153, 255));
        but_trocos1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_trocos1.setOpaque(true);
        but_trocos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_trocos1ActionPerformed(evt);
            }
        });

        but_fmaneiodef1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_settings_48.png"))); // NOI18N
        but_fmaneiodef1.setText("Fundo de Maneio");
        but_fmaneiodef1.setBackground(new java.awt.Color(255, 255, 255));
        but_fmaneiodef1.setBorder(null);
        but_fmaneiodef1.setContentAreaFilled(false);
        but_fmaneiodef1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_fmaneiodef1.setForeground(new java.awt.Color(51, 153, 255));
        but_fmaneiodef1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_fmaneiodef1.setOpaque(true);
        but_fmaneiodef1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_fmaneiodef1ActionPerformed(evt);
            }
        });

        but_supervisor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_administrator_male_48.png"))); // NOI18N
        but_supervisor1.setText("Supervisor");
        but_supervisor1.setBackground(new java.awt.Color(255, 255, 255));
        but_supervisor1.setBorder(null);
        but_supervisor1.setContentAreaFilled(false);
        but_supervisor1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_supervisor1.setForeground(new java.awt.Color(51, 153, 255));
        but_supervisor1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_supervisor1.setOpaque(true);
        but_supervisor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_supervisor1ActionPerformed(evt);
            }
        });

        but_fmaneiodef2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_settings_48.png"))); // NOI18N
        but_fmaneiodef2.setText("Notas Aceites");
        but_fmaneiodef2.setBackground(new java.awt.Color(255, 255, 255));
        but_fmaneiodef2.setBorder(null);
        but_fmaneiodef2.setContentAreaFilled(false);
        but_fmaneiodef2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_fmaneiodef2.setForeground(new java.awt.Color(51, 153, 255));
        but_fmaneiodef2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_fmaneiodef2.setOpaque(true);
        but_fmaneiodef2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_fmaneiodef2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but_emptyall1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(but_trocos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(but_fmaneiodef1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(but_supervisor1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(but_fmaneiodef2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(but_trocos1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(but_emptyall1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(but_fmaneiodef2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(but_fmaneiodef1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(but_supervisor1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimentos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(51, 153, 255))); // NOI18N

        but_carregamentos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_request_money_48.png"))); // NOI18N
        but_carregamentos1.setText("Carregamentos");
        but_carregamentos1.setBackground(new java.awt.Color(255, 255, 255));
        but_carregamentos1.setBorder(null);
        but_carregamentos1.setContentAreaFilled(false);
        but_carregamentos1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_carregamentos1.setForeground(new java.awt.Color(51, 153, 255));
        but_carregamentos1.setHideActionText(true);
        but_carregamentos1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_carregamentos1.setOpaque(true);
        but_carregamentos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_carregamentos1ActionPerformed(evt);
            }
        });

        but_pagamentos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_receive_cash_48.png"))); // NOI18N
        but_pagamentos1.setText("Pagamentos");
        but_pagamentos1.setBackground(new java.awt.Color(255, 255, 255));
        but_pagamentos1.setBorder(null);
        but_pagamentos1.setContentAreaFilled(false);
        but_pagamentos1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_pagamentos1.setForeground(new java.awt.Color(51, 153, 255));
        but_pagamentos1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_pagamentos1.setOpaque(true);
        but_pagamentos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_pagamentos1ActionPerformed(evt);
            }
        });

        but_float4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_safe_out_48.png"))); // NOI18N
        but_float4.setText("Fecho Periodo");
        but_float4.setBackground(new java.awt.Color(255, 255, 255));
        but_float4.setBorder(null);
        but_float4.setContentAreaFilled(false);
        but_float4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        but_float4.setForeground(new java.awt.Color(51, 153, 255));
        but_float4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        but_float4.setOpaque(true);
        but_float4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_float4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but_float4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(but_pagamentos1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(but_carregamentos1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(but_carregamentos1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(but_pagamentos1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(but_float4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        retroceder_pass10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass10.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass10MouseClicked(evt);
            }
        });

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ecraconfigV2Layout = new javax.swing.GroupLayout(ecraconfigV2);
        ecraconfigV2.setLayout(ecraconfigV2Layout);
        ecraconfigV2Layout.setHorizontalGroup(
            ecraconfigV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecraconfigV2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ecraconfigV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ecraconfigV2Layout.createSequentialGroup()
                        .addComponent(retroceder_pass10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel99))
                    .addGroup(ecraconfigV2Layout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        ecraconfigV2Layout.setVerticalGroup(
            ecraconfigV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecraconfigV2Layout.createSequentialGroup()
                .addGroup(ecraconfigV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(retroceder_pass10)
                    .addComponent(jLabel99))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ecraconfigV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(ecraconfigV2);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas Aceites", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(51, 153, 255))); // NOI18N

        Ch50eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50Nochange.png"))); // NOI18N
        Ch50eur.setText("Nota de 50 €");
        Ch50eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch50eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/50Nochange.png"))); // NOI18N
        Ch50eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (2).png"))); // NOI18N
        Ch50eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (2).png"))); // NOI18N
        Ch50eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch50eurMouseClicked(evt);
            }
        });

        Ch20eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20Nochange.png"))); // NOI18N
        Ch20eur.setText("Nota de 20 €");
        Ch20eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch20eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/20Nochange.png"))); // NOI18N
        Ch20eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (1).png"))); // NOI18N
        Ch20eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (1).png"))); // NOI18N
        Ch20eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch20eurMouseClicked(evt);
            }
        });

        Ch10eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10Nochange.png"))); // NOI18N
        Ch10eur.setText("Nota de 10 €");
        Ch10eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch10eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10Nochange.png"))); // NOI18N
        Ch10eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10eur.png"))); // NOI18N
        Ch10eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/10eur.png"))); // NOI18N
        Ch10eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch10eurMouseClicked(evt);
            }
        });

        Ch5eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5Nochange.png"))); // NOI18N
        Ch5eur.setText("Nota de 5 €");
        Ch5eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch5eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/5Nochange.png"))); // NOI18N
        Ch5eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir.png"))); // NOI18N
        Ch5eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir.png"))); // NOI18N
        Ch5eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch5eurMouseClicked(evt);
            }
        });

        Ch500eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/500Nochange.png"))); // NOI18N
        Ch500eur.setText("Nota de 500 €");
        Ch500eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch500eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/500Nochange.png"))); // NOI18N
        Ch500eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (5).png"))); // NOI18N
        Ch500eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (5).png"))); // NOI18N
        Ch500eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch500eurMouseClicked(evt);
            }
        });

        Ch200eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/200Nochange.png"))); // NOI18N
        Ch200eur.setText("Nota de 200 €");
        Ch200eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch200eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/200Nochange.png"))); // NOI18N
        Ch200eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (4).png"))); // NOI18N
        Ch200eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (4).png"))); // NOI18N
        Ch200eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch200eurMouseClicked(evt);
            }
        });

        Ch100eur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/100Nochange.png"))); // NOI18N
        Ch100eur.setText("Nota de 100 €");
        Ch100eur.setBackground(new java.awt.Color(255, 255, 255));
        Ch100eur.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/100Nochange.png"))); // NOI18N
        Ch100eur.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (3).png"))); // NOI18N
        Ch100eur.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transferir (3).png"))); // NOI18N
        Ch100eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ch100eurMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(Ch50eur)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Ch10eur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ch20eur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ch5eur, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Ch200eur)
                            .addComponent(Ch100eur)
                            .addComponent(Ch500eur))
                        .addGap(20, 20, 20))))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(Ch5eur)
                        .addGap(37, 37, 37)
                        .addComponent(Ch10eur)
                        .addGap(37, 37, 37)
                        .addComponent(Ch20eur))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(Ch100eur)
                        .addGap(38, 38, 38)
                        .addComponent(Ch200eur)
                        .addGap(37, 37, 37)
                        .addComponent(Ch500eur)))
                .addGap(31, 31, 31)
                .addComponent(Ch50eur)
                .addContainerGap())
        );

        retroceder_pass14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass14.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass14MouseClicked(evt);
            }
        });

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(retroceder_pass14)
                .addGap(26, 26, 26)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel100))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(retroceder_pass14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChannelsLayout = new javax.swing.GroupLayout(Channels);
        Channels.setLayout(ChannelsLayout);
        ChannelsLayout.setHorizontalGroup(
            ChannelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ChannelsLayout.setVerticalGroup(
            ChannelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(Channels);

        configgeral.setPreferredSize(new java.awt.Dimension(800, 480));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

        jLabel58.setText("Configurações Reciclador Notas");
        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        retroceder_pass6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass6.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass6MouseClicked(evt);
            }
        });

        spo5e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spo5e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N
        spo5e.setText("50");
        spo5e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas de 5 €", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        spo5e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spo5e.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        spo5e.setToolTipText("");
        spo5e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spo5eMouseClicked(evt);
            }
        });

        spo20e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spo20e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N
        spo20e.setText("50");
        spo20e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas de 20 €", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        spo20e.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        spo20e.setToolTipText("");
        spo20e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spo20eMouseClicked(evt);
            }
        });

        spo10e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spo10e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N
        spo10e.setText("50");
        spo10e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas de 10 €", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        spo10e.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        spo10e.setToolTipText("");
        spo10e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spo10eMouseClicked(evt);
            }
        });

        spo100e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spo100e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N
        spo100e.setText("50");
        spo100e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas de 100 €", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        spo100e.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        spo100e.setToolTipText("");
        spo100e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spo100eMouseClicked(evt);
            }
        });

        spo200e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spo200e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N
        spo200e.setText("50");
        spo200e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas de 200 €", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        spo200e.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        spo200e.setToolTipText("");
        spo200e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spo200eMouseClicked(evt);
            }
        });

        spo50e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spo50e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_money_64.png"))); // NOI18N
        spo50e.setText("50");
        spo50e.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas de 50 €", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        spo50e.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        spo50e.setToolTipText("");
        spo50e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spo50eMouseClicked(evt);
            }
        });

        jLabel25.setText("Máximo de notas no reciclador | Se definir zero a denominação não será reciclada");
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel34.setText("A soma de todas as denominações não pode exceder as 80 unidades");
        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spo5e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spo10e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spo20e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spo50e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spo200e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spo100e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(retroceder_pass6)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel58))
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel34))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder_pass6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spo5e, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spo20e, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spo100e, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spo10e, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spo200e, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spo50e, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout configgeralLayout = new javax.swing.GroupLayout(configgeral);
        configgeral.setLayout(configgeralLayout);
        configgeralLayout.setHorizontalGroup(
            configgeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        configgeralLayout.setVerticalGroup(
            configgeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(configgeral);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        jLabel95.setText("Retirar Valores por Denominação");
        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        retroceder_pass15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_back_to_64.png"))); // NOI18N
        retroceder_pass15.setBackground(new java.awt.Color(255, 255, 0));
        retroceder_pass15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retroceder_pass15MouseClicked(evt);
            }
        });

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo iot flat.png"))); // NOI18N

        txtdenom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdenom.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quantidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        txtdenom.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jButton2.setText("Retirar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtdemonatuais.setText("Selecionou a denominação 1 EUR - Existencia Atual - 200");
        txtdemonatuais.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtdemonatuais.setForeground(new java.awt.Color(0, 153, 255));

        b20.setText("1");
        b20.setBackground(new java.awt.Color(51, 153, 255));
        b20.setBorder(null);
        b20.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b20.setForeground(new java.awt.Color(255, 255, 255));
        b20.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b20.setToolTipText("");
        b20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b20MouseClicked(evt);
            }
        });
        b20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b20ActionPerformed(evt);
            }
        });

        b21.setText("2");
        b21.setBackground(new java.awt.Color(51, 153, 255));
        b21.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b21.setForeground(new java.awt.Color(255, 255, 255));
        b21.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b21.setToolTipText("");
        b21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b21MouseClicked(evt);
            }
        });
        b21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b21ActionPerformed(evt);
            }
        });

        b22.setText("3");
        b22.setActionCommand("1");
        b22.setBackground(new java.awt.Color(51, 153, 255));
        b22.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b22.setForeground(new java.awt.Color(255, 255, 255));
        b22.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b22.setToolTipText("");
        b22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b22MouseClicked(evt);
            }
        });
        b22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b22ActionPerformed(evt);
            }
        });

        b23.setText("6");
        b23.setBackground(new java.awt.Color(51, 153, 255));
        b23.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b23.setForeground(new java.awt.Color(255, 255, 255));
        b23.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b23.setToolTipText("");
        b23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b23MouseClicked(evt);
            }
        });
        b23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b23ActionPerformed(evt);
            }
        });

        b24.setText("5");
        b24.setBackground(new java.awt.Color(51, 153, 255));
        b24.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b24.setForeground(new java.awt.Color(255, 255, 255));
        b24.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b24.setToolTipText("");
        b24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b24MouseClicked(evt);
            }
        });
        b24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b24ActionPerformed(evt);
            }
        });

        b25.setText("4");
        b25.setBackground(new java.awt.Color(51, 153, 255));
        b25.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b25.setForeground(new java.awt.Color(255, 255, 255));
        b25.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b25.setToolTipText("");
        b25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b25MouseClicked(evt);
            }
        });
        b25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b25ActionPerformed(evt);
            }
        });

        b26.setText("7");
        b26.setBackground(new java.awt.Color(51, 153, 255));
        b26.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b26.setForeground(new java.awt.Color(255, 255, 255));
        b26.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b26.setToolTipText("");
        b26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b26MouseClicked(evt);
            }
        });
        b26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b26ActionPerformed(evt);
            }
        });

        b27.setText("8");
        b27.setBackground(new java.awt.Color(51, 153, 255));
        b27.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b27.setForeground(new java.awt.Color(255, 255, 255));
        b27.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b27.setToolTipText("");
        b27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b27MouseClicked(evt);
            }
        });
        b27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b27ActionPerformed(evt);
            }
        });

        b28.setText("9");
        b28.setBackground(new java.awt.Color(51, 153, 255));
        b28.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b28.setForeground(new java.awt.Color(255, 255, 255));
        b28.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b28.setToolTipText("");
        b28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b28MouseClicked(evt);
            }
        });
        b28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b28ActionPerformed(evt);
            }
        });

        b29.setText("0");
        b29.setBackground(new java.awt.Color(51, 153, 255));
        b29.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        b29.setForeground(new java.awt.Color(255, 255, 255));
        b29.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        b29.setToolTipText("");
        b29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b29MouseClicked(evt);
            }
        });
        b29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b29ActionPerformed(evt);
            }
        });

        bc3.setText("C");
        bc3.setBackground(new java.awt.Color(51, 153, 255));
        bc3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        bc3.setForeground(new java.awt.Color(255, 255, 255));
        bc3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_down_arrow_16.png"))); // NOI18N
        bc3.setToolTipText("");
        bc3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bc3MouseClicked(evt);
            }
        });
        bc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bc3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retroceder_pass15)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel95)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel98))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(txtdemonatuais)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtdenom, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(b25, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b24, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b23, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b26, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b29, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addComponent(b27, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b28, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(b20, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b21, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b22, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder_pass15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel95)
                            .addComponent(jLabel98))
                        .addGap(18, 18, 18)
                        .addComponent(txtdemonatuais)))
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtdenom, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b25, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b24, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b23, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b26, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b27, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b28, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bc3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b29, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout DenominationLayout = new javax.swing.GroupLayout(Denomination);
        Denomination.setLayout(DenominationLayout);
        DenominationLayout.setHorizontalGroup(
            DenominationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DenominationLayout.setVerticalGroup(
            DenominationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(Denomination);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   public double valor = 0;
   
  public DatePicker getDatePicker() {
    return datePicker1;
   }
  
  public void log (String event){
    SimpleDateFormat dfff = new SimpleDateFormat("HH:mm:ss");
    Date now2 = new Date();
    
    String hora22 = dfff.format(now2);
     pw.println("- " + hora22 + " | "+ event);
     pw.flush(); 
      
  }
  
  public void createdoc() throws FileNotFoundException{
      
      try {
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
        } catch (DocumentException e) {
            
        }     
  }
  private static void addMetaData(Document document) {
        document.addTitle("Relatório Diário iThink");
        document.addSubject("daily report");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Fernando Marinho | iThink");
        document.addCreator("Fernando Marinho | iThink ");
    }
  
  public  void addTitlePage(Document document)
            throws DocumentException {
      
      double totalsafe = 0;
      double totalcharges= 0;
      double totalpays = 0;
      double totalperiodo = 0;
      
       SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
          Date date = new Date();
          String horadff = time.format(date);
          
          
       
      try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

      
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;
                totalsafe=totalf2;
               

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(pago_com) from  ith_periodo ");

         
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;
                totalcharges = totalf2;
                
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select sum(valor_dispensado) from  ith_periodo where Documento like 'Pgt Div%' ";
            
            rss2 = stt2.executeQuery(queryy);
            
            
        
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;
                totalpays = totalf2;
               }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        double totalcaixaspo = 0;
        double totalcaixascs = 0;

        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
           
            switch (strNote){

                case "5.0 EUR" :
                
                total = Math.round(strStored*5*100);
                total = total /100;
                totalcaixaspo += total;
                
                break;

                case "10.0 EUR" :
               
                total = Math.round(strStored*10*100);
                total = total /100;
                totalcaixaspo += total;
                
                break;

                case "20.0 EUR" :
                
                total = Math.round(strStored*20*100);
                total = total /100;
                totalcaixaspo += total;
               
                break;

                case "50.0 EUR" :
                
                total = Math.round(strStored*50*100);
                total = total /100;
                totalcaixaspo += total;
                
                break;

                case "100.0 EUR" :
                
                total = Math.round(strStored*100*100);
                total = total /100;
                totalcaixaspo += total;
               
                break;

                case "200.0 EUR" :
               
                total = Math.round(strStored*200*100);
                total = total /100;
                totalcaixaspo += total;
                
                break;

                case "500.0 EUR" :
                
                total = Math.round(strStored*500*100);
                total = total /100;
                totalcaixaspo += total;
               
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
            switch (strcoin){

                case "0.01 EUR" :

                total = Math.round(strStored*0.01*100);
                total = total /100;
                totalcaixascs += total;
                

                break;

                case "0.02 EUR" :
                
                total = Math.round(strStored*0.02*100);
                total = total /100;
                totalcaixascs += total;
               
                break;

                case "0.05 EUR" :
               
                total = Math.round(strStored*0.05*100);
                total = total /100;
                totalcaixascs += total;
                
                break;

                case "0.1 EUR" :
                
                total = Math.round(strStored*0.10*100);
                total = total /100;
                totalcaixascs += total;
                
                break;

                case "0.2 EUR" :
                
                total = Math.round(strStored*0.20*100);
                total = total /100;
                totalcaixascs += total;
               
                break;

                case "0.5 EUR" :
                
                total = Math.round(strStored*0.50*100);
                total = total /100;
                totalcaixascs += total;
               
                break;

                case "1.0 EUR" :
                
                total = Math.round(strStored*1*100);
                total = total /100;
                totalcaixascs += total;
              
                break;

                case "2.0 EUR" :
                
                total = Math.round(strStored*2*100);
                total = total /100;
                totalcaixascs += total;
               
                break;

            }
            totalcaixaspo = Math.round(totalcaixaspo*100);
            totalcaixaspo = totalcaixaspo /100;

            totalcaixascs = Math.round(totalcaixascs*100);
            totalcaixascs = totalcaixascs /100;

        }
        
       try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select sum(valor_doc) from  ith_periodo";
            
            rss2 = stt2.executeQuery(queryy);
            
            
        
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;
                totalperiodo = totalf2;
                
               

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
       
      
      Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Relatório Diário iThink", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Relatório gerado automáticamente por iThink,  "  + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Resumo de Valores ",
                greenFont));
        addEmptyLine(preface, 1); 
    /*    preface.add(new Paragraph(
                "Valor em Cofre  - " + totalsafe + " EUR",
                smallBold));
        addEmptyLine(preface, 1); 
        preface.add(new Paragraph(
                "Valor em Recicladore de Notas - " + totalcaixaspo + " EUR",
                smallBold));
        addEmptyLine(preface, 1); 
        preface.add(new Paragraph(
                "Valor em Recicladore de Moedas - " + totalcaixascs + " EUR",
                smallBold));
        addEmptyLine(preface, 1); */
        preface.add(new Paragraph(
                "Valor em Pagamentos - " + totalpays + " EUR",
                smallBold));
        addEmptyLine(preface, 1); 
        preface.add(new Paragraph(
                "Valor Periodo - " + totalperiodo + " EUR",
                smallBold));
        addEmptyLine(preface, 1); 
        preface.add(new Paragraph(
                "Resumo de Transações",
                smallBold));
        addEmptyLine(preface, 1);
        
        

        document.add(preface);
        // Start a new page
       // document.newPage();
    }
  
  private static void addContent(Document document) throws DocumentException {
       
        createTable(document);

        
       // document.add(catPart);

    }

  
  
  private static void createTable(Document document)
            throws BadElementException, DocumentException {
       
          SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        
     
       DefaultTableModel model = new DefaultTableModel(new String[]{"Data", "Documento", "Valor Introduzido","Valor Documento","Valor Dispensado ","Utilizador"}, 0);
       try{
       Statement st;
            ResultSet rs;
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
            st = con.createStatement();
            rs = st.executeQuery("select * from ith_periodo");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
            while(rs.next()){
                String data = dateFormat.format(rs.getTimestamp(1));
                String documento = rs.getString(2);
                String valordoc = String.valueOf(rs.getDouble(3))+" €";
                String pagocom = String.valueOf(rs.getDouble(4))+" €";
                String dispen = String.valueOf(rs.getDouble(5))+" €";
                String user = rs.getString(6);
                model.addRow(new Object[]{ data, documento, valordoc,pagocom,dispen,user }) ;
            }
            
            PdfPTable tab = new PdfPTable(model.getColumnCount());
        for (int i = 0; i < model.getColumnCount(); i++) {
            tab.addCell(model.getColumnName(i));
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                if (model.getValueAt(i, j) != null) {
                    tab.addCell(model.getValueAt(i, j).toString());
                } else {
                    tab.addCell("-");
                }
            }
        }
        document.add(tab);
         
        
        
            rs.close();
            st.close();
        }catch(SQLException ex){
        }


       

      

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    
    
    public void sendemail () throws UnsupportedEncodingException{
        
        
    Properties props = new Properties();   
    /** Parâmetros de conexão com servidor Gmail */
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
      message.setSubject("iThink Machine -> "+ MachineName);//Assunto
  //    message.setText("Reporte diário iThink!");
      /**Método para enviar a mensagem criada*/
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText("Reporte diário iThink! -> " + MachineName);
      Multipart multipart = new MimeMultipart();

         // Set text message part
        multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "/home/pi/log/dailyreport.pdf";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);
         Transport.send(message);
      
 
      
 
     } catch (MessagingException  e) {
         
        log(e.toString());
    }
    }
    
    
  
   public void errorlog (String event){
     hora = df.format(now);
     pw2.println("- " + hora + " | "+ event);
     pw2.flush(); 
      
      
  }
  
  @Override
    public void SCS_SPO_Log_Updated(String log)
    {
        log("evento updated");
        errorlog(log);
    }
    
    public void conetar() throws IOException, InterruptedException{
 
         /*   Enumeration portList = CommPortIdentifier.getPortIdentifiers();
            while (portList.hasMoreElements()) {
            CommPortIdentifier portIdTmp = (CommPortIdentifier) portList.nextElement();
            if (portIdTmp.getPortType() == CommPortIdentifier.PORT_SERIAL) {
            scs_spo = new SCS_SPO(portIdTmp.getName(), this);
            sysThread = new Thread(scs_spo);
            System.out.println(portIdTmp.getName());
            sysThread.start();
           */
             
             scs_spo = new SCS_SPO(this);
             
             Thread syst = new Thread(scs_spo);
             syst.start();
             
             
             
           //  scs_spo.resumeSystem();
            
            
         
            log("Validadores Conetados");
            enviamailniveis = new Mails(this);
            MailThread = new Thread(enviamailniveis);        
            MailThread.start();
            
            Send_a = new SendData(this);
            Senddata = new Thread(Send_a);
            Senddata.start();
            
            
            System.out.println("Conetado");
            server = new Server(this);
            serverthread = new Thread(server);
      
            serverthread.start();
            
            server1 = new Server1(this);
            serverthread1 = new Thread(server1);
      
            serverthread1.start();
            
             server2 = new Server2(this);
            serverthread2 = new Thread(server2);
      
            serverthread2.start();
            
            pda = new Pda(this);
            pdathread = new Thread(pda);
      
            pdathread.start();
            serversifarma = new serverSifarma(this);
            serversithread = new Thread(serversifarma);
            serversithread.start();
            
            
            AppServer = new appserver(this);
            serverapp = new Thread(AppServer);
      
            serverapp.start();
            
        //    sqlSage = new sqllistenerSage(this);
      //      sqlsage = new Thread(sqlSage);
        //    sqlsage.start();
       //   
    //    sqllZS = new sqllistenerZS(this);
      //     sqlthreadZS = new Thread(sqllZS);
      //      sqlthreadZS.start();
            sqll = new sqllistener(this);
            sqlthread = new Thread(sqll);
            sqlthread.start();
            Realtime = new realtime(this);
            RThoras = new Thread(Realtime);
            RThoras.start();
            lerfileconfig();
            jLabel1.setVisible(false);
         //  jButton2.setVisible(false);        
          // jButton8.setVisible(false);
       //    jButton4.setVisible(false);
       
     //  serverSageG = new sqllistener1SageG(this);
    //   sql1sage = new Thread(serverSageG);
    //   sql1sage.start();
       troconotas.setVisible(false);
       trocomoedas.setVisible(false);
       
       
         Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo chmod 777 /dev/usb/lp0");
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
          Runtime rt2 = Runtime.getRuntime();
      /*  try {
            Process pr = rt2.exec("stty -F /dev/serial0 19200");
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
       */
       
       
       
       
       
       
            }
      //   }   
        
  //  }

    public String IPHOST;
    public String IPHOST2;
    public String SQLUSER;
    public String SQLPASS; 
    public String EMPRESA;
    public String Destinatarios;
    public String Codpagamento;
    public String codsuper;
    public String MachineName;
    
    public void atualizapbs(){
        
        pb1c.setMinimum(0);
        pb2c.setMinimum(0);
        pb5c.setMinimum(0);
        pb10c.setMinimum(0);
        pb20c.setMinimum(0);
        pb50c.setMinimum(0);
        pb1e.setMinimum(0);
        pb2e.setMinimum(0);
       
        
        pb5e.setMinimum(0);
        pb10e.setMinimum(0);
        pb20e.setMinimum(0);
        pb50e.setMinimum(0);
        pb100e.setMinimum(0);
        pb200e.setMinimum(0);
        
        double mx1c=0;
        double mx2c=0;
        double mx5c=0;
        double mx10c=0;
        double mx20c=0;
        double mx50c=0;
        double mx1e=0;
        double mx2e=0;
        
        double mx5e=0;
        double mx10e=0;
        double mx20e=0;
        double mx50e=0;
        double mx100e=0;
        double mx200e=0;
        
        
        try{

            Statement stt3;
            ResultSet rss3;

            Connection conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt3 = conn3.createStatement();
            String queryy3 = "select * from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";

            rss3 = stt3.executeQuery(queryy3);

            while(rss3.next()){

                
                mx1c = (rss3.getDouble(2));
                mx2c = (rss3.getDouble(3));
                mx5c = (rss3.getDouble(4));
                mx10c = (rss3.getDouble(5));
                mx20c = (rss3.getDouble(6));
                mx50c = (rss3.getDouble(7));
                mx1e = (rss3.getDouble(8));
                mx2e = (rss3.getDouble(9));
                
                mx5e = (rss3.getDouble(10));
                mx10e = (rss3.getDouble(11));
                mx20e = (rss3.getDouble(12));
                mx50e = (rss3.getDouble(13));
                mx100e = (rss3.getDouble(14));
                mx200e = (rss3.getDouble(15));
                

            }

            rss3.close();
            stt3.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }
           
        
        int m1c=(int)mx1c;
        int m2c=(int)mx2c;
        int m5c=(int)mx5c;
        int m10c=(int)mx10c;
        int m20c=(int)mx20c;
        int m50c=(int)mx50c;
        int m1e=(int)mx1e;
        int m2e=(int)mx2e;
        
        int m5e=(int)mx5e;
        int m10e=(int)mx10e;
        int m20e=(int)mx20e;
        int m50e=(int)mx50e;
        int m100e=(int)mx100e;
        int m200e=(int)mx200e;
        
        
        pb1c.setMaximum(m1c);
        pb2c.setMaximum(m2c);
     //   System.out.println(m2c + "valor 2c");
        pb5c.setMaximum(m5c);
        pb10c.setMaximum(m10c);
        pb20c.setMaximum(m20c);
        pb50c.setMaximum(m50c);
        pb1e.setMaximum(m1e);
        pb2e.setMaximum(m2e);
        
        pb5e.setMaximum(m5e);
        pb10e.setMaximum(m10e);
        pb20e.setMaximum(m20e);
        pb50e.setMaximum(m50e);
        pb100e.setMaximum(m100e);
        pb200e.setMaximum(m200e);
        
        
        
        
        
        
        
        
    }
    
    
    
public void lerfileconfig() throws IOException{
    
    
    

            IPHOST = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(1);
          
            IPHOST2 = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(3);
        

           
            SQLUSER = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(5);      
            SQLPASS = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(7); 
            EMPRESA = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(9); 
            Destinatarios = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(11);
            Codpagamento = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(13);
            codsuper = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(15);
            MachineName = Files.readAllLines(Paths.get("/home/pi/config.txt")).get(17);
             
             
            
}
   
    public void novovalorapagarsocket() throws InterruptedException, IOException{
        String valor2 = "2";
        valor2 = String.valueOf(valor);
      
       apagarlabel.setText(valor2 + " EUR");
      
       scs_spo.EnableNoteValidator();
       scs_spo.EnableCoinValidator();
       
    }
    public String codigo = "";

    private void SPACEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPACEActionPerformed
       descritivo.setText(descritivo.getText()+ " "); 
    }//GEN-LAST:event_SPACEActionPerformed

    private void SPACEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SPACEMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SPACEMouseClicked

    private void XActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XActionPerformed
       descritivo.setText(descritivo.getText()+ "X"); 
    }//GEN-LAST:event_XActionPerformed

    private void XMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_XMouseClicked

    private void CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CActionPerformed
        descritivo.setText(descritivo.getText()+ "C"); 
    }//GEN-LAST:event_CActionPerformed

    private void CMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CMouseClicked

    private void VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VActionPerformed
        descritivo.setText(descritivo.getText()+ "V"); 
    }//GEN-LAST:event_VActionPerformed

    private void VMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VMouseClicked

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        descritivo.setText(descritivo.getText()+ "B"); 
    }//GEN-LAST:event_BActionPerformed

    private void BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BMouseClicked

    private void NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NActionPerformed
       descritivo.setText(descritivo.getText()+ "N"); 
    }//GEN-LAST:event_NActionPerformed

    private void NMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NMouseClicked

    private void MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MActionPerformed
        descritivo.setText(descritivo.getText()+ "M"); 
    }//GEN-LAST:event_MActionPerformed

    private void MMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MMouseClicked

    private void VIRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIRGActionPerformed
        descritivo.setText(descritivo.getText()+ ","); 
    }//GEN-LAST:event_VIRGActionPerformed

    private void VIRGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VIRGMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VIRGMouseClicked

    private void PONTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PONTActionPerformed
        descritivo.setText(descritivo.getText()+ "."); 
    }//GEN-LAST:event_PONTActionPerformed

    private void PONTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PONTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PONTMouseClicked

    private void TRACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRACActionPerformed
       descritivo.setText(descritivo.getText()+ "-"); 
    }//GEN-LAST:event_TRACActionPerformed

    private void TRACMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TRACMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TRACMouseClicked

    private void ZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZActionPerformed
        descritivo.setText(descritivo.getText()+ "Z"); 
    }//GEN-LAST:event_ZActionPerformed

    private void ZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ZMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ZMouseClicked

    private void SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SActionPerformed
      descritivo.setText(descritivo.getText()+ "S"); 
    }//GEN-LAST:event_SActionPerformed

    private void SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SMouseClicked

    private void DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DActionPerformed
       descritivo.setText(descritivo.getText()+ "D"); 
    }//GEN-LAST:event_DActionPerformed

    private void DMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DMouseClicked

    private void FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FActionPerformed
        descritivo.setText(descritivo.getText()+ "F"); 
    }//GEN-LAST:event_FActionPerformed

    private void FMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FMouseClicked

    private void GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GActionPerformed
       descritivo.setText(descritivo.getText()+ "G"); 
    }//GEN-LAST:event_GActionPerformed

    private void GMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GMouseClicked

    private void HActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HActionPerformed
       descritivo.setText(descritivo.getText()+ "H"); 
    }//GEN-LAST:event_HActionPerformed

    private void HMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_HMouseClicked

    private void JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JActionPerformed
        descritivo.setText(descritivo.getText()+ "J"); 
    }//GEN-LAST:event_JActionPerformed

    private void JMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JMouseClicked

    private void KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KActionPerformed
       descritivo.setText(descritivo.getText()+ "K"); 
    }//GEN-LAST:event_KActionPerformed

    private void KMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_KMouseClicked

    private void LActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LActionPerformed
       descritivo.setText(descritivo.getText()+ "L"); 
    }//GEN-LAST:event_LActionPerformed

    private void LMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LMouseClicked

    private void ÇActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ÇActionPerformed
        descritivo.setText(descritivo.getText()+ "Ç"); 
    }//GEN-LAST:event_ÇActionPerformed

    private void ÇMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ÇMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ÇMouseClicked

    private void AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AActionPerformed
       descritivo.setText(descritivo.getText()+ "A"); 
    }//GEN-LAST:event_AActionPerformed

    private void AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AMouseClicked

    private void PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PActionPerformed
       descritivo.setText(descritivo.getText()+ "P"); 
    }//GEN-LAST:event_PActionPerformed

    private void PMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PMouseClicked

    private void IActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IActionPerformed
        descritivo.setText(descritivo.getText()+ "I"); 
    }//GEN-LAST:event_IActionPerformed

    private void IMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_IMouseClicked

    private void OActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OActionPerformed
        descritivo.setText(descritivo.getText()+ "O"); 
    }//GEN-LAST:event_OActionPerformed

    private void OMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_OMouseClicked

    private void UActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UActionPerformed
        descritivo.setText(descritivo.getText()+ "U"); 
    }//GEN-LAST:event_UActionPerformed

    private void UMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_UMouseClicked

    private void YActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YActionPerformed
       descritivo.setText(descritivo.getText()+ "Y"); 
    }//GEN-LAST:event_YActionPerformed

    private void YMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_YMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_YMouseClicked

    private void TActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TActionPerformed
      descritivo.setText(descritivo.getText()+ "T"); 
    }//GEN-LAST:event_TActionPerformed

    private void TMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TMouseClicked

    private void RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RActionPerformed
       descritivo.setText(descritivo.getText()+ "R"); 
    }//GEN-LAST:event_RActionPerformed

    private void RMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RMouseClicked

    private void EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EActionPerformed
       descritivo.setText(descritivo.getText()+ "E"); 
    }//GEN-LAST:event_EActionPerformed

    private void EMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EMouseClicked
public String descritivopagamento = "";
    private void QActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QActionPerformed

    descritivo.setText(descritivo.getText()+ "Q");   
    }//GEN-LAST:event_QActionPerformed

    private void QMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QMouseClicked

    private void WActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WActionPerformed
descritivo.setText(descritivo.getText()+ "W"); 
// TODO add your handling code here:
    }//GEN-LAST:event_WActionPerformed

    private void WMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_WMouseClicked

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MouseClicked

    private void bc2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bc2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bc2MouseClicked

    private void b60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b60MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b60MouseClicked

    private void be2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_be2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_be2MouseClicked

    private void b59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b59MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b59MouseClicked

    private void b58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b58MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b58MouseClicked

    private void b57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b57MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b57MouseClicked

    private void b56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b56MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b56MouseClicked

    private void b55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b55MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b55MouseClicked

    private void b54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b54MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b54MouseClicked

    private void b53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b53ActionPerformed
        pagamentovalor.setText(pagamentovalor.getText()+ "3"); 
    }//GEN-LAST:event_b53ActionPerformed

    private void b53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b53MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b53MouseClicked

    private void b52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b52MouseClicked

    private void b51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b51ActionPerformed
        pagamentovalor.setText(pagamentovalor.getText()+ "1"); 
    }//GEN-LAST:event_b51ActionPerformed

    private void b51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b51MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b51MouseClicked

    private void Q1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q1MouseClicked

    private void Q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q1ActionPerformed
        descritivo.setText(descritivo.getText()+ "2"); 
    }//GEN-LAST:event_Q1ActionPerformed

    private void Q2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q2MouseClicked

    private void Q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q2ActionPerformed
       descritivo.setText(descritivo.getText()+ "1"); 
    }//GEN-LAST:event_Q2ActionPerformed

    private void Q3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q3MouseClicked

    private void Q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q3ActionPerformed
        descritivo.setText(descritivo.getText()+ "4"); 
    }//GEN-LAST:event_Q3ActionPerformed

    private void Q4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q4MouseClicked

    private void Q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q4ActionPerformed
        descritivo.setText(descritivo.getText()+ "3"); 
    }//GEN-LAST:event_Q4ActionPerformed

    private void Q5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q5MouseClicked

    private void Q5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q5ActionPerformed
       descritivo.setText(descritivo.getText()+ "6"); 
    }//GEN-LAST:event_Q5ActionPerformed

    private void Q6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q6MouseClicked

    private void Q6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q6ActionPerformed
        descritivo.setText(descritivo.getText()+ "5"); 
    }//GEN-LAST:event_Q6ActionPerformed

    private void Q7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q7MouseClicked

    private void Q7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q7ActionPerformed
       descritivo.setText(descritivo.getText()+ "7"); 
    }//GEN-LAST:event_Q7ActionPerformed

    private void Q8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q8MouseClicked

    private void Q8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q8ActionPerformed
       descritivo.setText(descritivo.getText()+ "8"); 
    }//GEN-LAST:event_Q8ActionPerformed

    private void Q9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q9MouseClicked

    private void Q9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q9ActionPerformed
        descritivo.setText(descritivo.getText()+ "9"); 
    }//GEN-LAST:event_Q9ActionPerformed

    private void Q10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Q10MouseClicked

    private void Q10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q10ActionPerformed
       descritivo.setText(descritivo.getText()+ "0"); 
    }//GEN-LAST:event_Q10ActionPerformed

    private void b52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b52ActionPerformed
        pagamentovalor.setText(pagamentovalor.getText()+ "2"); 
    }//GEN-LAST:event_b52ActionPerformed

    private void b56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b56ActionPerformed
        pagamentovalor.setText(pagamentovalor.getText()+ "4"); 
    }//GEN-LAST:event_b56ActionPerformed

    private void b55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b55ActionPerformed
       pagamentovalor.setText(pagamentovalor.getText()+ "5"); 
    }//GEN-LAST:event_b55ActionPerformed

    private void b54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b54ActionPerformed
        pagamentovalor.setText(pagamentovalor.getText()+ "6"); 
    }//GEN-LAST:event_b54ActionPerformed

    private void b57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b57ActionPerformed
       pagamentovalor.setText(pagamentovalor.getText()+ "7"); 
    }//GEN-LAST:event_b57ActionPerformed

    private void b58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b58ActionPerformed
       pagamentovalor.setText(pagamentovalor.getText()+ "8"); 
    }//GEN-LAST:event_b58ActionPerformed

    private void b59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b59ActionPerformed
        pagamentovalor.setText(pagamentovalor.getText()+ "9"); 
    }//GEN-LAST:event_b59ActionPerformed

    private void b60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b60ActionPerformed
       pagamentovalor.setText(pagamentovalor.getText()+ "0"); 
    }//GEN-LAST:event_b60ActionPerformed

    private void be2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_be2ActionPerformed
       pagamentovalor.setText(pagamentovalor.getText()+ "."); 
    }//GEN-LAST:event_be2ActionPerformed

    private void bc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc2ActionPerformed
        pagamentovalor.setText("");
        
    }//GEN-LAST:event_bc2ActionPerformed

    
    boolean pagof = false;
    
    
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       
        
         jButton14.setVisible(false);
       if (pagoativo){
          
           
           Pagamentos.hide();
           ecraPagamentos.show();
           
          return;
          
           
       }   
       pagof = true;
       
       
        CURRENCY pagar = new CURRENCY();
        pagar.value = Double.parseDouble(pagamentovalor.getText());
        pagar.countryCode = "EUR";
        
        scs_spo.PayoutValue(pagar);
        System.out.println("Pago" + pagar);
        
     /* try{
          
          
         
           
                
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd") ;
                java.util.Date utilDate = new java.util.Date();
                
                
                java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
                
                
                
                String query = " insert into ith_periodo (Data,documento,pago_com,valor_doc,valor_dispensado,user,insert_conc,pago_finalizado)"
                        + " values (?, ?, ?, ?, ?, ?,?,?)";
                
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setTimestamp (1, sqlTimeStamp);
                preparedStmt.setString (2, "Pgt Div."+ descritivo.getText());
                preparedStmt.setDouble   (3, 000);
                preparedStmt.setDouble(4, 000);
                preparedStmt.setDouble    (5, pagar.value);
                preparedStmt.setString (6, "Operador");
                preparedStmt.setInt (7, 1);
                
                preparedStmt.setInt (8, 1);
                // execute the preparedstatement
                preparedStmt.execute();
            
                con.close();
            
        
      }catch(SQLException ex){
          
          System.out.println(ex);
         }*/
           
    }//GEN-LAST:event_jButton14ActionPerformed
public static java.sql.Timestamp convertJavaDateToSqlDate(java.util.Date date)
	{
		return new java.sql.Timestamp(date.getTime());
	} 
    private void retroceder_passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_passMouseClicked
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
        configgeral.hide();
         ConsultaV2.hide();
        CarregaV2.hide();
    }//GEN-LAST:event_retroceder_passMouseClicked

    private void bcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bcMouseClicked
        codigo = "";
        senha.setText("");
    }//GEN-LAST:event_bcMouseClicked

    private void b0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b0MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "0";
    }//GEN-LAST:event_b0MouseClicked

    private void beMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beMouseClicked

        int categoria = 2;
        
        System.out.println(codigo);
        
        
        
        if("170882".equals(codigo)){
             AutoDismiss.showOptionDialog(rootPane, "Olá MASTER ;), a fechar a app","Fecho" ,3000);
                  
            
            System.exit(1);
            return;
        }
        
          try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT categoria from `users` WHERE password  LIKE '" + codigo + "'");

         
            while(rss2.next()){

               

               categoria = rss2.getInt(1);
                
            }
            rss2.close();
            stt2.close();
            
               }catch(SQLException ex){
            System.out.println(ex);
        }
        
            
            if (categoria == 0 ){
            codigo = "";
            senha.setText("");
            
            carregaecraconfig("0");
            
                
            }else if (categoria == 1){
             codigo = "";
            senha.setText("");    
            carregaecraconfig("1");
                
            }else{
             codigo = "";
             senha.setText("");    
              AutoDismiss.showOptionDialog(rootPane, "Senha Invalida","Password" ,3000);
              return;
                
            }
    }//GEN-LAST:event_beMouseClicked
     
          public void carregaecraconfig(String username){
              
           boolean Accarrega = false;
           boolean Acexistencias= false;
           boolean Acpagamentos= false;
           boolean Acfecho= false;
           boolean Acniveisminimos= false;
           boolean Acfundomaneio= false;
           boolean AcEstado= false;
           boolean AcSupervisor= false;
           boolean AcCofre= false;
           boolean Acdefnotas= false;
           boolean Acpainelbordo= false;
           boolean Acreinicio= false;
           
          try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT * from `categorias` WHERE categoria  LIKE '" + username + "'");

           System.out.println("adcqedc");
            while(rss2.next()){

                if (rss2.getInt(3) == 0){Accarrega = false;}else{Accarrega = true;}
                 if (rss2.getInt(5) == 0){Acpagamentos = false;}else{Acpagamentos = true;}
                if (rss2.getInt(4) == 0){Acexistencias = false;}else{Acexistencias = true;}
                if (rss2.getInt(6) == 0){Acfecho = false;}else{Acfecho = true;}
                if (rss2.getInt(7) == 0){Acpainelbordo = false;}else{Acpainelbordo = true;}
                if (rss2.getInt(8) == 0){Acdefnotas = false;}else{Acdefnotas = true;}
                if (rss2.getInt(9) == 0){Acfundomaneio = false;}else{Acfundomaneio = true;}
                if (rss2.getInt(10) == 0){Acniveisminimos = false;}else{Acniveisminimos = true;}
                if (rss2.getInt(11) == 0){AcEstado = false;}else{AcEstado = true;}
                if (rss2.getInt(12) == 0){AcSupervisor = false;}else{AcSupervisor = true;}
                if (rss2.getInt(13) == 0){AcCofre = false;}else{AcCofre = true;}
                if (rss2.getInt(14) == 0){Acreinicio = false;}else{Acreinicio = true;}
            }
           
            rss2.close();
            stt2.close();     
              
                 }catch(SQLException ex){
            System.out.println(ex);
        }
        
          if(Accarrega){but_carregamentos1.setVisible(true);}else{but_carregamentos1.setVisible(false);}
          if(Acpagamentos){but_pagamentos1.setVisible(true);}else{but_pagamentos1.setVisible(false);}
          
          if(Acexistencias){but_consultas2.setVisible(true);}else{but_consultas2.setVisible(false);}
          if(Acpainelbordo){But_painelbordo1.setVisible(true);}else{But_painelbordo1.setVisible(false);}
          
          if(Acfundomaneio){but_fmaneiodef1.setVisible(true);}else{but_fmaneiodef1.setVisible(false);}
          if(Acdefnotas){but_emptyall1.setVisible(true);}else{but_emptyall1.setVisible(false);}
          
          if(AcEstado){but_float6.setVisible(true);}else{but_float6.setVisible(false);}
          if(Acreinicio){but_float3.setVisible(true);}else{but_float3.setVisible(false);}
          
          if(AcSupervisor){but_supervisor1.setVisible(true);}else{but_supervisor1.setVisible(false);}
          if(AcCofre){but_float5.setVisible(true);}else{but_float5.setVisible(false);}
          
          if(Acfecho){but_float4.setVisible(true);}else{but_float4.setVisible(false);}
          if(Acniveisminimos){but_trocos1.setVisible(true);}else{but_trocos1.setVisible(false);}
         
        PasswordPrompt.hide();
        ecraconfigV2.show();
        ConsultaV2.hide();
        CarregaV2.hide();
        
        
          }
      
    private void b9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b9MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "9";
    }//GEN-LAST:event_b9MouseClicked

    private void b8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b8MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "8";
    }//GEN-LAST:event_b8MouseClicked

    private void b7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b7MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "7";
    }//GEN-LAST:event_b7MouseClicked

    private void b4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b4MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "4";
    }//GEN-LAST:event_b4MouseClicked

    private void b5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b5MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "5";
    }//GEN-LAST:event_b5MouseClicked

    private void b6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b6MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "6";
    }//GEN-LAST:event_b6MouseClicked

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void b3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b3MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "3";
    }//GEN-LAST:event_b3MouseClicked

    private void b2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b2MouseClicked
        senha.setText(senha.getText()+ "*");
        codigo += "2";
    }//GEN-LAST:event_b2MouseClicked

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked

        senha.setText(senha.getText()+ "*");
        codigo += "1";
    }//GEN-LAST:event_b1MouseClicked

    private void retroceder_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_consultaMouseClicked
      ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.show();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();
        ConsultaV2.hide();
        CarregaV2.hide();
        
    }//GEN-LAST:event_retroceder_consultaMouseClicked

    private void but_emptyallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_emptyallActionPerformed
    
      
      
            lerspodenoms();
            spo5e.setText(Integer.toString(spodenom5e));
            spo10e.setText(Integer.toString(spodenom10e));
            spo20e.setText(Integer.toString(spodenom20e));
            spo50e.setText(Integer.toString(spodenom50e));
            spo100e.setText(Integer.toString(spodenom100e));
            spo200e.setText(Integer.toString(spodenom200e));
            
            
          
      ecraconfigV2.hide();
      configgeral.show();
      
        
        
    }//GEN-LAST:event_but_emptyallActionPerformed

    private void but_floatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_floatActionPerformed
      try{
           Statement stt;
            ResultSet rss;
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_periodo");
            
            while(rss.next()){
               double totalf = rss.getDouble(1);
               totalf = totalf *100;
               totalf = Math.round(totalf);
               totalf = totalf /100;
               valorperiodo.setText(String.valueOf(totalf)+ " EUR");
            }
            rss.close();
            stt.close();
        }catch(SQLException ex){
        }
        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

           

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofrefecho.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        
      atualizacontadores();
      
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select totgeral from  EstadoAtual WHERE id=1");//where id in (1,2,3); ");

           

            while(rss2.next()){
                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalmaquinafecho.setText(String.valueOf(totalf2)+" EUR");
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        System.out.println(ex);
        }
      
           
         
        
          
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select valorfmaneiototal from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";
            
            rss2 = stt2.executeQuery(queryy);

            while(rss2.next()){

               fundomaneiodef.setText((rss2.getString(1)));
               
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            
            System.out.println(ex);
        }
          
      RetirarFundos.show();
      ecraconfigV2.hide();

               

    }//GEN-LAST:event_but_floatActionPerformed
double totalcarregado = 0;
    private void but_carregamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_carregamentosActionPerformed
       
       
   atualizapbs();
   
        scs_spo.Refill();

        
        
        carregaconsulta = true;
        scs_spo.EnableNoteValidator();
        scs_spo.EnableCoinValidator();
        
       
     //   scs_spo.ResetSPO();
            spoSetup = scs_spo.GetSPOSetup();
            scsSetup = scs_spo.GetSCSSetup();
            for(BANKNOTE note : spoSetup.notes){
                 
                 String strNote = note.currency.toString();
                 int strStored =  note.stored;
                 
                 switch (strNote){

                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                 if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                 if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                 if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                break;

                case "200.0 EUR" :
                 if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);}
                break;
                 }}
 
            double totalcaixa = 0;
        
            totalcarregado = 0;
        
        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
            switch (strNote){

                case "5.0 EUR" :
                nnota5.setText(String.valueOf(strStored));
                //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);
                total = Math.round(strStored*5*100);
                total = total /100;
                totalcaixa += total;
                nnota5.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb5e.setValue(strStored);
                if(strStored==0){
                    pb5e.setString("");
                }else{
                pb5e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                } 
                
                break;

                case "10.0 EUR" :
                nnota10.setText(String.valueOf(strStored));
                //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);
                total = Math.round(strStored*10*100);
                total = total /100;
                totalcaixa += total;
                nnota10.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb10e.setValue(strStored);
                 if(strStored==0){
                    pb10e.setString("");
                }else{
                pb10e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                } 
                break;

                case "20.0 EUR" :
                nnota20.setText(String.valueOf(strStored));
                //  scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);
                total = Math.round(strStored*20*100);
                total = total /100;
                totalcaixa += total;
                nnota20.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb20e.setValue(strStored);
                if(strStored==0){
                    pb20e.setString("");
                }else{
                pb20e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                } 
                break;

                case "50.0 EUR" :
                nnota50.setText(String.valueOf(strStored));
                total = Math.round(strStored*50*100);
                total = total /100;
                totalcaixa += total;
                nnota50.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb50e.setValue(strStored);
              if(strStored==0){
                    pb50e.setString("");
                }else{
                pb50e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                }   
                break;

                case "100.0 EUR" :
                nnota100.setText(String.valueOf(strStored));
                total = Math.round(strStored*100*100);
                total = total /100;
                totalcaixa += total;
                nnota100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb100e.setValue(strStored);
                if(strStored==0){
                    pb100e.setString("");
                }else{
                pb100e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                }   
                break;

                case "200.0 EUR" :
                nnota200.setText(String.valueOf(strStored));
                total = Math.round(strStored*200*100);
                total = total /100;
                totalcaixa += total;
                nnota200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb200e.setValue(strStored);
                 if(strStored==0){
                    pb200e.setString("");
                }else{
                pb200e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                }   
                break;

                case "500.0 EUR" :
                nnota500.setText(String.valueOf(strStored));
                total = Math.round(strStored*500*100);
                total = total /100;
                totalcaixa += total;
                nnota500.setText(" "+String.valueOf(strStored)+" | "+total+" EUR");
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
            switch (strcoin){

                case "0.01 EUR" :
                nmoeda001.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.01*100);
                total = total /100;
                totalcaixa += total;
                nmoeda001.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb1c.setValue(strStored);
                 if(strStored==0){
                     pb1c.setString("");
                 }else{
                pb1c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;

                case "0.02 EUR" :
                nmoeda002.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.02*100);
                total = total /100;
                totalcaixa += total;
                nmoeda002.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb2c.setValue(strStored);
             //   System.out.println(strStored);
                 if(strStored==0){
                     pb2c.setString("");
                 }else{
                pb2c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;

                case "0.05 EUR" :
                nmoeda005.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.05*100);
                total = total /100;
                totalcaixa += total;
                nmoeda005.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb5c.setValue(strStored);
                if(strStored==0){
                     pb5c.setString("");
                 }else{
                pb5c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 } break;

                case "0.1 EUR" :
                nmoeda010.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.10*100);
                total = total /100;
                totalcaixa += total;
                nmoeda010.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb10c.setValue(strStored);
               if(strStored==0){
                     pb10c.setString("");
                 }else{
                pb10c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 } 
                break;

                case "0.2 EUR" :
                nmoeda020.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.20*100);
                total = total /100;
                totalcaixa += total;
                nmoeda020.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb20c.setValue(strStored);
                if(strStored==0){
                     pb20c.setString("");
                 }else{
                pb20c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;
                
                case "0.5 EUR" :
                nmoeda050.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.50*100);
                total = total /100;
                totalcaixa += total;
                nmoeda050.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb50c.setValue(strStored);
                 if(strStored==0){
                     pb50c.setString("");
                 }else{
                pb50c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }  
                break;

                case "1.0 EUR" :
                nmoeda100.setText(String.valueOf(strStored));
                total = Math.round(strStored*1*100);
                total = total /100;
                totalcaixa += total;
                nmoeda100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb1e.setValue(strStored);
                if(strStored==0){
                     pb1e.setString("");
                 }else{
                pb1e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;

                case "2.0 EUR" :
                nmoeda200.setText(String.valueOf(strStored));
                total = Math.round(strStored*2*100);
                total = total /100;
                totalcaixa += total;
                nmoeda200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                pb2e.setValue(strStored);
                 if(strStored==0){
                     pb2e.setString("");
                 }else{
                pb2e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 } 
                break;

            }

            totalcaixa = Math.round(totalcaixa*100);
            totalcaixa = totalcaixa /100;

            ntotalmoedasnotas.setText(String.valueOf(totalcaixa)+" EUR");
            totalcarregadolb.setText("0 EUR");
            totcarregav2.setText("0 EUR");
            totatual.setText(String.valueOf(totalcaixa)+" EUR");
           
            ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
    //    Carregamentos.show();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();

ecraconfigV2.hide();
CarregaV2.show();
ConsultaV2.hide();

        }
     
    }//GEN-LAST:event_but_carregamentosActionPerformed

    private void but_supervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_supervisorActionPerformed

        try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select * from categorias where categoria = 1 ");
         
            while(rs.next()){
                
                if (rs.getInt(3)==0){AcCarregamentos.setSelected(false) ;}else{AcCarregamentos.setSelected(true) ;}
                if (rs.getInt(4)==0){AcExistencias.setSelected(false) ;}else{AcExistencias.setSelected(true) ;}
                if (rs.getInt(5)==0){AcPagamentos.setSelected(false) ;}else{AcPagamentos.setSelected(true) ;}
                if (rs.getInt(6)==0){AcFechoperiodo.setSelected(false) ;}else{AcFechoperiodo.setSelected(true) ;}
                if (rs.getInt(7)==0){AcPainelbordo.setSelected(false) ;}else{AcPainelbordo.setSelected(true) ;}
                if (rs.getInt(8)==0){AcDefnotas.setSelected(false) ;}else{AcDefnotas.setSelected(true) ;}
                if (rs.getInt(9)==0){AcFundomaneio.setSelected(false) ;}else{AcFundomaneio.setSelected(true) ;}
                if (rs.getInt(10)==0){AcNiveisminimos.setSelected(false) ;}else{AcNiveisminimos.setSelected(true) ;}
                if (rs.getInt(11)==0){AcEstadoGeral.setSelected(false) ;}else{AcEstadoGeral.setSelected(true) ;}
                if (rs.getInt(12)==0){AcSupervisor.setSelected(false) ;}else{AcSupervisor.setSelected(true) ;}
                if (rs.getInt(13)==0){AcConsultacofre.setSelected(false) ;}else{AcConsultacofre.setSelected(true) ;}
                if (rs.getInt(14)==0){AcReinicio.setSelected(false) ;}else{AcReinicio.setSelected(true) ;}
              
            }
        
          rs.close();
            st.close();

        }catch(SQLException ex){
        } 
        
         try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select password from users where id = 1 ");
         
            while(rs.next()){
                
             superpass.setText(rs.getString(1));   
                
            }
        
          rs.close();
            st.close();

        }catch(SQLException ex){
        } 
        
          
         try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select password from users where id = 2 ");
         
            while(rs.next()){
                
             operpass.setText(rs.getString(1));   
                
            }
        
          rs.close();
            st.close();

        }catch(SQLException ex){
        } 
         
        
        Supervisor.show();
        ecraconfigV2.hide();
        

    }//GEN-LAST:event_but_supervisorActionPerformed

    private void but_pagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_pagamentosActionPerformed

        Pagamentos.show();

        ecraconfigV2.hide();

    }//GEN-LAST:event_but_pagamentosActionPerformed

    private void But_painelbordoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But_painelbordoActionPerformed

        DefaultTableModel model = new DefaultTableModel(new String[]{"Data", "Documento", "Valor Introduzido","Valor Documento","Valor Dispensado ","Utilizador"}, 0);

        try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select * from ith_periodo");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

            while(rs.next()){
                String data = dateFormat.format(rs.getTimestamp(1));

                String documento = rs.getString(2);

                String valordoc = String.valueOf(rs.getDouble(3))+" €";

                String pagocom = String.valueOf(rs.getDouble(4))+" €";

                String dispen = String.valueOf(rs.getDouble(5))+" €";

                String user = rs.getString(6);

                model.addRow(new Object[]{ data, documento, valordoc,pagocom,dispen,user }) ;
              
            }
            table.setFillsViewportHeight(true);
            table.setModel(model);

            rs.close();
            st.close();

        }catch(SQLException ex){
        }

        try{

            Statement stt;
            ResultSet rss;

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_periodo");//where id in (1,2,3); ");

           
            while(rss.next()){

                double totalf = rss.getDouble(1);

                totalf = totalf *100;

                totalf = Math.round(totalf);
                totalf = totalf /100;

                totalfaturado.setText(String.valueOf(totalf)+ " EUR");

            }

            rss.close();
            stt.close();

        }catch(SQLException ex){
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre ");//where id in (1,2,3); ");

        
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofre.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(pago_com) from  ith_periodo");//where id in (1,2,3); ");

           
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcarregamentos.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor_dispensado) from  ith_periodo WHERE Documento = 'Pgt Div' ");//where id in (1,2,3); ");

           

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalpagamentos.setText(String.valueOf(totalf2)+ " EUR");
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
     
        atualizacontadores();
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select totgeral from  EstadoAtual WHERE id = (select max(ID) from EstadoAtual)");//where id in (1,2,3); ");

           

            while(rss2.next()){
                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

               fundomaneio.setText(String.valueOf(totalf2)+" EUR");
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        System.out.println(ex);
        }
        
        ecraconfigV2.hide();
        ecratransacoes.show();

    }//GEN-LAST:event_But_painelbordoActionPerformed

    private void but_consultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_consultasActionPerformed
     
     
     ConsultaV2.show();
     ecraconfigV2.hide();
atualizacontadores();
    }//GEN-LAST:event_but_consultasActionPerformed

    private void b10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b10MouseClicked

    private void b11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b11MouseClicked

    private void b12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b12MouseClicked

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "3";
        }else{
            valorsaque.setText(valorsaque.getText()+ "3");
        }
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

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "1";
        }else{
            valorsaque.setText(valorsaque.getText()+ "1");
        }
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "2";
        }else{
            valorsaque.setText(valorsaque.getText()+ "2");
        }
    }//GEN-LAST:event_b11ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "4";
        }else{
            valorsaque.setText(valorsaque.getText()+ "4");
        }
    }//GEN-LAST:event_b15ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "5";
        }else{
            valorsaque.setText(valorsaque.getText()+ "5");
        }
    }//GEN-LAST:event_b14ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "6";
        }else{
            valorsaque.setText(valorsaque.getText()+ "6");
        }
    }//GEN-LAST:event_b13ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
        if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "7";
        }else{
            valorsaque.setText(valorsaque.getText()+ "7");
        }
    }//GEN-LAST:event_b16ActionPerformed

    private void b17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b17ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "8";
        }else{
            valorsaque.setText(valorsaque.getText()+ "8");
        }
    }//GEN-LAST:event_b17ActionPerformed

    private void b18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b18ActionPerformed
        if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "9";
        }else{
            valorsaque.setText(valorsaque.getText()+ "9");
        }
    }//GEN-LAST:event_b18ActionPerformed

    private void b19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b19ActionPerformed
       if (tec2 == 0){
        valorsaque.setText(valorsaque.getText()+ "*");
        senhacofre += "0";
        }else{
            valorsaque.setText(valorsaque.getText()+ "0");
        }
    }//GEN-LAST:event_b19ActionPerformed

    private void bc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc1ActionPerformed
       valorsaque.setText("");       
    }//GEN-LAST:event_bc1ActionPerformed
public double valorpago2 = 0;
    private void be1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_be1ActionPerformed
       
       
        if("1234".equals(senhacofre)){
            
            
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();
        configgeral.hide();
       
        RetirarFundos.hide();
      
        ConsultaCofre.show();
        DefinirFManeio.hide();
        ConsultaV2.hide();
        CarregaV2.hide();
        
        
        
       }   else{
            
            senhacofre="";
            valorsaque.setText("");    
            
        
        }
       
       
        
        
       
    }//GEN-LAST:event_be1ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
      
               
    }//GEN-LAST:event_jButton6MouseClicked
 LocalDate novadata2  ;
 public String novadata="";
    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      
        getDatePicker();
       novadata2 = datePicker1.getDate();
       
       novadata = novadata2.toString();
       DefaultTableModel model = new DefaultTableModel(new String[]{"Data", "Documento", "Valor Introduzido","Valor Documento","Valor Dispensado ","Utilizador"}, 0);
       try{
       Statement st;
            ResultSet rs;
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
            st = con.createStatement();
            rs = st.executeQuery("select * from ith_transacoes WHERE DATE(Data) = '" + novadata+"'");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
            while(rs.next()){
                String data = dateFormat.format(rs.getTimestamp(1));
                String documento = rs.getString(2);
                String valordoc = String.valueOf(rs.getDouble(3))+" €";
                String pagocom = String.valueOf(rs.getDouble(4))+" €";
                String dispen = String.valueOf(rs.getDouble(5))+" €";
                String user = rs.getString(6);
                model.addRow(new Object[]{ data, documento, valordoc,pagocom,dispen,user }) ;
            }
            table.setFillsViewportHeight(true);
            table.setModel(model);
            rs.close();
            st.close();
        }catch(SQLException ex){
        System.out.println(ex);
        }
        try{
           Statement stt;
            ResultSet rss;
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_transacoes WHERE DATE(Data) = '" + novadata+"'");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
            while(rss.next()){
               double totalf = rss.getDouble(1);
               totalf = totalf *100;
               totalf = Math.round(totalf);
               totalf = totalf /100;
               totalfaturado.setText(String.valueOf(totalf)+ " EUR");
            }
            rss.close();
            stt.close();
        }catch(SQLException ex){
        }
        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofre.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor_carregado) from  Carregamentos WHERE  DATE(reg_date) = '" + novadata+"'");

            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcarregamentos.setText(String.valueOf(totalf2)+ " EUR");
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select sum(valor_dispensado) from  ith_transacoes WHERE documento like 'Pgt Div%' and DATE(data) = '" + novadata+"'";
             
            rss2 = stt2.executeQuery(queryy);
            
            
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalpagamentos.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        
        

        double totalcaixa = 0;

        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
             switch (strNote){

                case "5.0 EUR" :
                nota5.setText(String.valueOf(strStored));
                total = Math.round(strStored*5*100);
                total = total /100;
                totalcaixa += total;
                nota5.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "10.0 EUR" :
                nota10.setText(String.valueOf(strStored));
                total = Math.round(strStored*10*100);
                total = total /100;
                totalcaixa += total;
                nota10.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "20.0 EUR" :
                nota20.setText(String.valueOf(strStored));
                total = Math.round(strStored*20*100);
                total = total /100;
                totalcaixa += total;
                nota20.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "50.0 EUR" :
                nota50.setText(String.valueOf(strStored));
                total = Math.round(strStored*50*100);
                total = total /100;
                totalcaixa += total;
                nota50.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "100.0 EUR" :
                nota100.setText(String.valueOf(strStored));
                total = Math.round(strStored*100*100);
                total = total /100;
                totalcaixa += total;
                nota100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "200.0 EUR" :
                nota200.setText(String.valueOf(strStored));
                total = Math.round(strStored*200*100);
                total = total /100;
                totalcaixa += total;
                nota200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "500.0 EUR" :
                nota500.setText(String.valueOf(strStored));
                total = Math.round(strStored*500*100);
                total = total /100;
                totalcaixa += total;
                nota500.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
           switch (strcoin){

                case "0.01 EUR" :

                total = Math.round(strStored*0.01*100);
                total = total /100;
                totalcaixa += total;
                moeda001.setText(String.valueOf(strStored)+" | "+total+" EUR");

                break;

                case "0.02 EUR" :
                moeda002.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.02*100);
                total = total /100;
                totalcaixa += total;
                moeda002.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "0.05 EUR" :
                moeda005.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.05*100);
                total = total /100;
                totalcaixa += total;
                moeda005.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "0.1 EUR" :
                moeda010.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.10*100);
                total = total /100;
                totalcaixa += total;
                moeda010.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "0.2 EUR" :
                moeda020.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.20*100);
                total = total /100;
                totalcaixa += total;
                moeda020.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "0.5 EUR" :
                moeda050.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.50*100);
                total = total /100;
                totalcaixa += total;
                moeda050.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "1.0 EUR" :
                moeda100.setText(String.valueOf(strStored));
                total = Math.round(strStored*1*100);
                total = total /100;
                totalcaixa += total;
                moeda100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

                case "2.0 EUR" :
                moeda200.setText(String.valueOf(strStored));
                total = Math.round(strStored*2*100);
                total = total /100;
                totalcaixa += total;
                moeda200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                break;

            }
            totalcaixa = Math.round(totalcaixa*100);
            totalcaixa = totalcaixa /100;

            fundomaneio.setText(String.valueOf(totalcaixa)+" EUR");
        }

        ecraconfig_old.hide();
        ecratransacoes.show();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    
    
    
  
        
        
        
        
        
    
    
    
    
    
    
    private void but_trocosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_trocosActionPerformed
   
    
       scs_spo.ResetSPO();
      scs_spo.ResetSCS(); 
      
      int d1c = 0;
      int d2c = 0;
      int d5c= 0;
      int d10c= 0;
      int d20c= 0;
      int d50c= 0;
      int d1e= 0;
      int d2e= 0;
      int d5e= 0;
      int d10e= 0;
      int d20e= 0;
      int d50e= 0;
      int d100e= 0;
      int d200e= 0;
      int d500e= 0;
      
      
      
     spoSetup = scs_spo.GetSPOSetup();
      scsSetup = scs_spo.GetSCSSetup();
      
       for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
            
            switch (strNote){

                case "5.0 EUR" :
                d5e=strStored;
                break;

                case "10.0 EUR" :
                    d10e=strStored;
               break;

                case "20.0 EUR" :
                    d20e=strStored;
               break;

                case "50.0 EUR" :
                    d50e=strStored;
                 break;

                case "100.0 EUR" :
                    d100e=strStored;
               break;

                case "200.0 EUR" :
                    d200e=strStored;
              break;

                case "500.0 EUR" :
                    d500e=strStored;
               break;
            }

        }

        for(COIN coin : scsSetup.coins){
             double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
          switch (strcoin){

                case "0.01 EUR" :
                    d1c=strStored;
                break;

                case "0.02 EUR" :
                    d2c=strStored;
                break;

                case "0.05 EUR" :
                    d5c=strStored;
               break;

                case "0.1 EUR" :
                    d10c=strStored;
              break;

                case "0.2 EUR" :
                    d20c=strStored;
               break;

                case "0.5 EUR" :
                    d50c=strStored;
               break;

                case "1.0 EUR" :
                    d1e=strStored;
                break;

                case "2.0 EUR" :
                    d2e=strStored;
               break;

            }
        }
      
      
    Niveis_Minimos.show();
    ecraconfigV2.hide();

   
                 
              TitledBorder titled = new TitledBorder("<html><nobr><font size='+1'>"+"1 CENT | "+" <font color='red'>"+ d1c + "</font></nobr></html>");
              nv1c.setBorder(titled);  
              
              TitledBorder titled1 = new TitledBorder("<html><nobr><font size='+1'>"+"2 CENT | "+" <font color='red'>"+ d2c + "</font></nobr></html>");
              nv2c.setBorder(titled1);   
              
              TitledBorder titled2 = new TitledBorder("<html><nobr><font size='+1'>"+"5 CENT | "+" <font color='red'>"+ d5c + "</font></nobr></html>");
              nv5c.setBorder(titled2);   
              
              TitledBorder titled3 = new TitledBorder("<html><nobr><font size='+1'>"+"10 CENT | "+" <font color='red'>"+ d10c + "</font></nobr></html>");
              nv10c.setBorder(titled3);   
              
              TitledBorder titled4 = new TitledBorder("<html><nobr><font size='+1'>"+"20 CENT | "+" <font color='red'>"+ d20c + "</font></nobr></html>");
              nv20c.setBorder(titled4);   
              
              TitledBorder titled5 = new TitledBorder("<html><nobr><font size='+1'>"+"50 CENT | "+" <font color='red'>"+ d50c + "</font></nobr></html>");
              nv50c.setBorder(titled5);   
              
              TitledBorder titled6 = new TitledBorder("<html><nobr><font size='+1'>"+"1 EUR | "+" <font color='red'>"+ d1e + "</font></nobr></html>");
              nv1e.setBorder(titled6);   
              
              TitledBorder titled7 = new TitledBorder("<html><nobr><font size='+1'>"+"2 EUR | "+" <font color='red'>"+ d2e + "</font></nobr></html>");
              nv2e.setBorder(titled7);   
              
              TitledBorder titled8 = new TitledBorder("<html><nobr><font size='+1'>"+"5 EUR | "+" <font color='red'>"+ d5e + "</font></nobr></html>");
              nv5e.setBorder(titled8);   
              
              TitledBorder titled9 = new TitledBorder("<html><nobr><font size='+1'>"+"10 EUR | "+" <font color='red'>"+ d10e + "</font></nobr></html>");
              nv10e.setBorder(titled9);   
              
              TitledBorder titled10 = new TitledBorder("<html><nobr><font size='+1'>"+"20 EUR | "+" <font color='red'>"+ d20e + "</font></nobr></html>");
              nv20e.setBorder(titled10);   
              
              TitledBorder titled11 = new TitledBorder("<html><nobr><font size='+1'>"+"50 EUR | "+" <font color='red'>"+ d50e + "</font></nobr></html>");
              nv50e.setBorder(titled11);   
              
              TitledBorder titled12 = new TitledBorder("<html><nobr><font size='+1'>"+"100 EUR | "+" <font color='red'>"+ d100e + "</font></nobr></html>");
              nv100e.setBorder(titled12);   
              
              TitledBorder titled13 = new TitledBorder("<html><nobr><font size='+1'>"+"200 EUR | "+" <font color='red'>"+ d200e + "</font></nobr></html>");
              nv200e.setBorder(titled13);   
              
            
           try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select * from  NiveisMinimos WHERE id=(SELECT MAX(id) FROM NiveisMinimos)";
            
            rss2 = stt2.executeQuery(queryy);
            
            while(rss2.next()){
                
               nv1c.setText(String.valueOf(rss2.getInt(3)));
               nv2c.setText(String.valueOf(rss2.getInt(4)));
               nv5c.setText(String.valueOf(rss2.getInt(5)));
               nv10c.setText(String.valueOf(rss2.getInt(6)));
               nv20c.setText(String.valueOf(rss2.getInt(7)));
               nv50c.setText(String.valueOf(rss2.getInt(8)));
               nv1e.setText(String.valueOf(rss2.getInt(9)));
               nv2e.setText(String.valueOf(rss2.getInt(10)));
               nv5e.setText(String.valueOf(rss2.getInt(11)));
               nv10e.setText(String.valueOf(rss2.getInt(12)));
               nv20e.setText(String.valueOf(rss2.getInt(13)));
               nv50e.setText(String.valueOf(rss2.getInt(14)));
               nv100e.setText(String.valueOf(rss2.getInt(15)));
               nv200e.setText(String.valueOf(rss2.getInt(16)));
              
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            
            System.out.println(ex);
        }
         
        
    }//GEN-LAST:event_but_trocosActionPerformed
int numerovezes = 0;
    private void retroceder_pass5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass5MouseClicked

        scs_spo.Unrefill();
        
        
        troco = 0;
        valorpago=0;
        valorP = 0;
        valortotal = 0;
        ammount=0;
        carregaconsulta = false;
        scs_spo.DisableCoinValidator();
        scs_spo.DisableNoteValidator();
        atualizacontadores();
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.show();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        CarregaV2.hide();
        
        cofre_removido.hide();
      
    }//GEN-LAST:event_retroceder_pass5MouseClicked

    private void retroceder_pass2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass2MouseClicked
       ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
    }//GEN-LAST:event_retroceder_pass2MouseClicked

    private void retroceder_pass7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass7MouseClicked
            ecraconfigV2.show();
            RetirarFundos.hide();
            
         
       
    }//GEN-LAST:event_retroceder_pass7MouseClicked
String valoralevantar="";
   
    
    
    
    private void ecraPagamentosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ecraPagamentosFocusGained

    }//GEN-LAST:event_ecraPagamentosFocusGained

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (pagoativo ){
           
            pagoativo=false;
            scs_spo.DisableNoteValidator();
            scs_spo.DisableCoinValidator();
            AutoDismiss.showOptionDialog(rootPane, "Pagamento Cancelado", "ATENÇÃO" ,5000);
           // pagoativo= false;
           Estado="Pagamento cancelado";
           gravapagamento();
           apagaft();
           limpa();

        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void apagarlabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apagarlabelMouseClicked
/*        try {
            conetar();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_apagarlabelMouseClicked

    private void moeda10cimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moeda10cimgMouseClicked

        numerovezes = numerovezes + 1;

        if (numerovezes == 3) {
            ecraPagamentos.hide();
            PasswordPrompt.show();
            numerovezes=0;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_moeda10cimgMouseClicked

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b8ActionPerformed

    private void retroceder_pass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass1MouseClicked
         ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.show();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();
    }//GEN-LAST:event_retroceder_pass1MouseClicked

    private void retroceder_pass11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass11MouseClicked
       ecraconfigV2.show();
       DefinirFManeio.hide();
       
       
         try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
        
        String query = " insert into fmaneio (c1,c2,c5,c10,c20,c50,e1,e2,n5,n10,n20,n50,n100,n200,n500,valorfmaneiototal)"
        + " values (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble (1, Double.parseDouble(c1.getText()));
      preparedStmt.setDouble (2, Double.parseDouble(c2.getText()));
      preparedStmt.setDouble (3, Double.parseDouble(c5.getText()));
      preparedStmt.setDouble (4, Double.parseDouble(c10.getText()));
      preparedStmt.setDouble (5, Double.parseDouble(c20.getText()));
      preparedStmt.setDouble (6, Double.parseDouble(c50.getText()));
      preparedStmt.setDouble (7, Double.parseDouble(e1.getText()));
      preparedStmt.setDouble (8, Double.parseDouble(e2.getText()));
      preparedStmt.setDouble (9, Double.parseDouble(n5.getText()));
      preparedStmt.setDouble (10, Double.parseDouble(n10.getText()));
      preparedStmt.setDouble (11, Double.parseDouble(n20.getText()));
      preparedStmt.setDouble (12, Double.parseDouble(n50.getText()));
      preparedStmt.setDouble (13, Double.parseDouble(n100.getText()));
      preparedStmt.setDouble (14, Double.parseDouble(n200.getText()));
      preparedStmt.setDouble (15, Double.parseDouble(n500.getText()));
      preparedStmt.setString (16, fmaneiodef.getText());

      // execute the preparedstatement
      preparedStmt.execute();
      
      con.close();
        
        
      }catch(SQLException ex){
          System.out.println(ex);
         }
        
        
       
    }//GEN-LAST:event_retroceder_pass11MouseClicked

    
   // private JTextField txt;
    private PopUpKeyboard keyboard;
    
     private class PopUpKeyboard extends JDialog implements ActionListener
    {
        private final JLabel cc1;

        public PopUpKeyboard(JLabel c1)
        {
            this.cc1 = c1;
           
       

           setLayout(new GridLayout(4, 3));
           
           setUndecorated(true);
           getRootPane().setOpaque(false);
           
           getContentPane ().setBackground (new Color (255, 255, 255));
           setBackground(new Color(255,255,255));
           for(int i = 1; i <= 9; i++) createButton(Integer.toString(i));
           createButton("Del");
           createButton(Integer.toString(0));
           createButton("Ent");
           
            pack();
        }

        private void createButton(String label)
        {
            JButton btn = new JButton(label);
            btn.addActionListener(this);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(100, 100));
            btn.setBackground(new Color(51,153,255));
            btn.setForeground(new Color(255,255,255));
            java.awt.Font font = btn.getFont();
            float size = font.getSize() + 20.0f;
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
            
            
           double fm1c = Double.parseDouble(c1.getText()) * 0.01;
            double fm2c = Double.parseDouble(c2.getText()) * 0.02;
             double fm5c = Double.parseDouble(c5.getText())*0.05;
              double fm10c = Double.parseDouble(c10.getText())*0.10;
               double fm20c = Double.parseDouble(c20.getText())*0.20;
                double fm50c = Double.parseDouble(c50.getText())*0.50;
                 double fm1e = Double.parseDouble(e1.getText())*1;
                  double fm2e = Double.parseDouble(e2.getText())*2;
                  
            double fm5e = Double.parseDouble(n5.getText())*5;
             double fm10e = Double.parseDouble(n10.getText())*10;
              double fm20e = Double.parseDouble(n20.getText())*20;
               double fm50e = Double.parseDouble(n50.getText())*50;
                double fm100e = Double.parseDouble(n100.getText())*100;
                 double fm200e = Double.parseDouble(n200.getText())*200;
                  double fm500e = Double.parseDouble(n500.getText())*500;
       
            
          fmaneiodefinido = fm1c+fm2c+fm5c+fm10c+fm20c+fm50c+fm1e+fm2e+fm5e+fm10e+fm20e+fm50e+fm100e+fm200e+fm500e;
          fmaneiodefinido = fmaneiodefinido * 100;
         
            fmaneiodefinido= Math.round(fmaneiodefinido);
        
            fmaneiodefinido = fmaneiodefinido/100;       
            
            fmaneiodef.setText(String.valueOf(fmaneiodefinido + " EUR"));
       
         }  
          
        }
 
    }
     double fmaneiodefinido = 0;

    private void c1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c1MouseClicked
                c1.setText("");
        
                keyboard = new PopUpKeyboard(c1);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_c1MouseClicked

    private void retroceder_consulta1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_consulta1MouseClicked
       ecraconfigV2.show();
       ConsultaCofre.hide();
       
    }//GEN-LAST:event_retroceder_consulta1MouseClicked

    private void but_float1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_float1ActionPerformed

      
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=5 ");

          
         
            if (rss2.next() == false) {
               cc5.setText("0 | 0 EUR");
            } else {
        do {
           double totalf2 = rss2.getDouble(2);
          int totalint = (int) totalf2;
           cc5.setText(String.valueOf(totalint)+ " |" + totalint*5 + " EUR");
         } while (rss2.next());
        }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`= 10 ");

          
               if (rss2.next() == false) {
               cc10.setText("0 | 0 EUR");
               } else {
               do {
           double totalf2 = rss2.getDouble(2);
           int totalint = (int) totalf2;
           cc10.setText(String.valueOf(totalint)+ "|" + totalint*10 + " EUR");
            } while (rss2.next());
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=20 ");

          
              if (rss2.next() == false) {
               cc20.setText("0 | 0 EUR");
            } else {
        do {
           double totalf2 = rss2.getDouble(2);
           int totalint = (int) totalf2;
           cc20.setText(String.valueOf(totalint)+ "|" + totalint*20 + " EUR");
         } while (rss2.next());
        }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
       
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=50 ");

          
               if (rss2.next() == false) {
               cc50.setText("0 | 0 EUR");
            } else {
        do {
           double totalf2 = rss2.getDouble(2);
           int totalint = (int) totalf2;
           cc50.setText(String.valueOf(totalint)+ "|" + totalint*50 + " EUR");
         } while (rss2.next());
        }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=100 ");

              if (rss2.next() == false) {
               cc100.setText("0 | 0 EUR");
            } else {
        do {
           double totalf2 = rss2.getDouble(2);
           int totalint = (int) totalf2;
           cc100.setText(String.valueOf(totalint)+ "|" + totalint*100 + " EUR");
         } while (rss2.next());
        }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
       
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=200 ");

          
                if (rss2.next() == false) {
               cc200.setText("0 | 0 EUR");
            } else {
        do {
           double totalf2 = rss2.getDouble(2);
           int totalint = (int) totalf2;
           cc200.setText(String.valueOf(totalint)+ "|" + totalint*200 + " EUR");
         } while (rss2.next());
        }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
       
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=500 ");

          
                if (rss2.next() == false) {
               cc500.setText("0 | 0 EUR");
            } else {
        do {
           double totalf2 = rss2.getDouble(2);
           int totalint = (int) totalf2;
           cc500.setText(String.valueOf(totalint)+ "|" + totalint*500 + " EUR");
         } while (rss2.next());
        }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
       
       try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

           
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                ccvalortotal.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
       ConsultaCofre.show();
       ecraconfigV2.hide();
       
    }//GEN-LAST:event_but_float1ActionPerformed

    private void but_fmaneiodefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_fmaneiodefActionPerformed
     
         scs_spo.ResetSPO();
      scs_spo.ResetSCS(); 
      
      int d1c = 0;
      int d2c = 0;
      int d5c= 0;
      int d10c= 0;
      int d20c= 0;
      int d50c= 0;
      int d1e= 0;
      int d2e= 0;
      int d5e= 0;
      int d10e= 0;
      int d20e= 0;
      int d50e= 0;
      int d100e= 0;
      int d200e= 0;
      int d500e= 0;
      
      
      
     spoSetup = scs_spo.GetSPOSetup();
      scsSetup = scs_spo.GetSCSSetup();
      
       for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
            
            switch (strNote){

                case "5.0 EUR" :
                d5e=strStored;
                break;

                case "10.0 EUR" :
                    d10e=strStored;
               break;

                case "20.0 EUR" :
                    d20e=strStored;
               break;

                case "50.0 EUR" :
                    d50e=strStored;
                 break;

                case "100.0 EUR" :
                    d100e=strStored;
               break;

                case "200.0 EUR" :
                    d200e=strStored;
              break;

                case "500.0 EUR" :
                    d500e=strStored;
               break;
            }

        }

        for(COIN coin : scsSetup.coins){
             double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
          switch (strcoin){

                case "0.01 EUR" :
                    d1c=strStored;
                break;

                case "0.02 EUR" :
                    d2c=strStored;
                break;

                case "0.05 EUR" :
                    d5c=strStored;
               break;

                case "0.1 EUR" :
                    d10c=strStored;
              break;

                case "0.2 EUR" :
                    d20c=strStored;
               break;

                case "0.5 EUR" :
                    d50c=strStored;
               break;

                case "1.0 EUR" :
                    d1e=strStored;
                break;

                case "2.0 EUR" :
                    d2e=strStored;
               break;

            }
        }
                
        
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select * from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";
            
            rss2 = stt2.executeQuery(queryy);
            
            
        
        DefinirFManeio.show();
        ecraconfigV2.hide();
        

            while(rss2.next()){
                 
              TitledBorder titled = new TitledBorder("<html><nobr><font size='+1'>"+"1 CENT | "+" <font color='red'>"+ d1c + "</font></nobr></html>");
              c1.setBorder(titled);  
              
              TitledBorder titled1 = new TitledBorder("<html><nobr><font size='+1'>"+"2 CENT | "+" <font color='red'>"+ d2c + "</font></nobr></html>");
              c2.setBorder(titled1);   
              
              TitledBorder titled2 = new TitledBorder("<html><nobr><font size='+1'>"+"5 CENT | "+" <font color='red'>"+ d5c + "</font></nobr></html>");
              c5.setBorder(titled2);   
              
              TitledBorder titled3 = new TitledBorder("<html><nobr><font size='+1'>"+"10 CENT | "+" <font color='red'>"+ d10c + "</font></nobr></html>");
              c10.setBorder(titled3);   
              
              TitledBorder titled4 = new TitledBorder("<html><nobr><font size='+1'>"+"20 CENT | "+" <font color='red'>"+ d20c + "</font></nobr></html>");
              c20.setBorder(titled4);   
              
              TitledBorder titled5 = new TitledBorder("<html><nobr><font size='+1'>"+"50 CENT | "+" <font color='red'>"+ d50c + "</font></nobr></html>");
              c50.setBorder(titled5);   
              
              TitledBorder titled6 = new TitledBorder("<html><nobr><font size='+1'>"+"1 EUR | "+" <font color='red'>"+ d1e + "</font></nobr></html>");
              e1.setBorder(titled6);   
              
              TitledBorder titled7 = new TitledBorder("<html><nobr><font size='+1'>"+"2 EUR | "+" <font color='red'>"+ d2e + "</font></nobr></html>");
              e2.setBorder(titled7);   
              
              TitledBorder titled8 = new TitledBorder("<html><nobr><font size='+1'>"+"5 EUR | "+" <font color='red'>"+ d5e + "</font></nobr></html>");
              n5.setBorder(titled8);   
              
              TitledBorder titled9 = new TitledBorder("<html><nobr><font size='+1'>"+"10 EUR | "+" <font color='red'>"+ d10e + "</font></nobr></html>");
              n10.setBorder(titled9);   
              
              TitledBorder titled10 = new TitledBorder("<html><nobr><font size='+1'>"+"20 EUR | "+" <font color='red'>"+ d20e + "</font></nobr></html>");
              n20.setBorder(titled10);   
              
              TitledBorder titled11 = new TitledBorder("<html><nobr><font size='+1'>"+"50 EUR | "+" <font color='red'>"+ d50e + "</font></nobr></html>");
              n50.setBorder(titled11);   
              
              TitledBorder titled12 = new TitledBorder("<html><nobr><font size='+1'>"+"100 EUR | "+" <font color='red'>"+ d100e + "</font></nobr></html>");
              n100.setBorder(titled12);   
              
              TitledBorder titled13 = new TitledBorder("<html><nobr><font size='+1'>"+"200 EUR | "+" <font color='red'>"+ d200e + "</font></nobr></html>");
              n200.setBorder(titled13);   
              
              TitledBorder titled14 = new TitledBorder("<html><nobr><font size='+1'>"+"500 EUR | "+" <font color='red'>"+ d500e + "</font></nobr></html>");
              n500.setBorder(titled14);   
              
                
// c1.setText("<html><nobr>"+String.valueOf(rss2.getDouble(2))+" <font color='red'> | 15</font></nobr></html>");
               c1.setText(String.valueOf(rss2.getDouble(2)));
               c2.setText(String.valueOf(rss2.getDouble(3)));
               c5.setText(String.valueOf(rss2.getDouble(4)));
               c10.setText(String.valueOf(rss2.getDouble(5)));
               c20.setText(String.valueOf(rss2.getDouble(6)));
               c50.setText(String.valueOf(rss2.getDouble(7)));
               e1.setText(String.valueOf(rss2.getDouble(8)));
               e2.setText(String.valueOf(rss2.getDouble(9)));
               n5.setText(String.valueOf(rss2.getDouble(10)));
               n10.setText(String.valueOf(rss2.getDouble(11)));
               n20.setText(String.valueOf(rss2.getDouble(12)));
               n50.setText(String.valueOf(rss2.getDouble(13)));
               n100.setText(String.valueOf(rss2.getDouble(14)));
               n200.setText(String.valueOf(rss2.getDouble(15)));
               n500.setText(String.valueOf(rss2.getDouble(16)));
               fmaneiodef.setText((rss2.getString(18)));
             // System.out.println(fmaneiodef);
              
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            
            System.out.println(ex);
        }
          
    }//GEN-LAST:event_but_fmaneiodefActionPerformed

    private void c5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c5MouseClicked
          c5.setText("");
        
                keyboard = new PopUpKeyboard(c5);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_c5MouseClicked

    private void c2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c2MouseClicked
      c2.setText("");
        
                keyboard = new PopUpKeyboard(c2);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        
    }//GEN-LAST:event_c2MouseClicked

    private void c10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c10MouseClicked
         c10.setText("");
        
                keyboard = new PopUpKeyboard(c10);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_c10MouseClicked

    private void c20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c20MouseClicked
         c20.setText("");
        
                keyboard = new PopUpKeyboard(c20);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_c20MouseClicked

    private void c50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c50MouseClicked
          c50.setText("");
        
                keyboard = new PopUpKeyboard(c50);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_c50MouseClicked

    private void e1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_e1MouseClicked
         e1.setText("");
        
                keyboard = new PopUpKeyboard(e1);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_e1MouseClicked

    private void e2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_e2MouseClicked
          e2.setText("");
        
                keyboard = new PopUpKeyboard(e2);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_e2MouseClicked

    private void n5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n5MouseClicked
          n5.setText("");
        
                keyboard = new PopUpKeyboard(n5);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n5MouseClicked

    private void n10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n10MouseClicked
         n10.setText("");
        
                keyboard = new PopUpKeyboard(n10);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n10MouseClicked

    private void n20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n20MouseClicked
         n20.setText("");
        
                keyboard = new PopUpKeyboard(n20);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n20MouseClicked

    private void n50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n50MouseClicked
         n50.setText("");
        
                keyboard = new PopUpKeyboard(n50);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n50MouseClicked

    private void n100MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n100MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_n100MouseEntered

    private void n100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n100MouseClicked
          n100.setText("");
        
                keyboard = new PopUpKeyboard(n100);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n100MouseClicked

    private void n200MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n200MouseClicked
         n200.setText("");
        
                keyboard = new PopUpKeyboard(n200);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n200MouseClicked

    private void n500MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n500MouseClicked
          n500.setText("");
        
                keyboard = new PopUpKeyboard(n500);
  keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
    }//GEN-LAST:event_n500MouseClicked
public void fecho(){
    
           AutoDismiss.showOptionDialog(rootPane, "Esta Operação pode demorar um pouco, aguarde pf", "ATENÇÃO" ,3000);
           
           
        double d1c = 0;
        double d2c = 0;
        double d5c = 0;
        double d10c = 0;
        double d20c = 0;
        double d50c = 0;
        double d1e = 0;
        double d2e = 0;
        
        double d5e = 0;
        double d10e = 0;
        double d20e = 0;
        double d50e = 0;
        double d100e = 0;
        double d200e = 0;
        double d500e = 0;
        
        try{

            Statement stt3;
            ResultSet rss3;

            Connection conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt3 = conn3.createStatement();
            String queryy3 = "select * from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";
            
            rss3 = stt3.executeQuery(queryy3);
            
            
        
      
        
        
        

            while(rss3.next()){

               d1c =(rss3.getDouble(2));
               
               d2c=(rss3.getDouble(3));
               d5c=(rss3.getDouble(4));
               d10c=(rss3.getDouble(5));
               d20c=(rss3.getDouble(6));
               d50c=(rss3.getDouble(7));
               d1e=(rss3.getDouble(8));
               d2e=(rss3.getDouble(9));
               d5e=(rss3.getDouble(10));
               d10e=(rss3.getDouble(11));
               d20e=(rss3.getDouble(12));
               d50e=(rss3.getDouble(13));
               d100e=(rss3.getDouble(14));
               d200e=(rss3.getDouble(15));
               d500e=(rss3.getDouble(16));
               
               
            }

            rss3.close();
            stt3.close();

        }catch(SQLException ex){
            
            System.out.println(ex);
        }
      
      
        double ed1c = 0;
        double ed2c = 0;
        double ed5c = 0;
        double ed10c = 0;
        double ed20c = 0;
        double ed50c = 0;
        double ed1e = 0;
        double ed2e = 0;
        
        double ed5e = 0;
        double ed10e = 0;
        double ed20e = 0;
        double ed50e = 0;
        double ed100e = 0;
        double ed200e = 0;
        double ed500e = 0;
        
        
          
         for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
            
            switch (strNote){

                case "5.0 EUR" :
                
                total = (strStored);
                
                ed5e = total;
               
                break;

                case "10.0 EUR" :
                
                total = (strStored);
               
                ed10e = total;
                
                break;

                case "20.0 EUR" :
               
                total = (strStored);
               
                ed20e = total;
               
                break;

                case "50.0 EUR" :
               
                total = (strStored);
              
                ed50e = total;
               
                break;

                case "100.0 EUR" :
                
                total = (strStored);
             
                ed100e = total;
                
                break;

                case "200.0 EUR" :
                
                total = (strStored);
               
                ed200e = total;
               
                break;

                case "500.0 EUR" :
                
                total = (strStored);
                
                ed500e = total;
                
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
            switch (strcoin){

                case "0.01 EUR" :
                total = (strStored);
              
                ed1c = total;
                

                break;

                case "0.02 EUR" :
                
                total = (strStored);
              
                ed2c = total;
                
                break;

                case "0.05 EUR" :
                
                total = (strStored);
             
                ed5c = total;
               
                break;

                case "0.1 EUR" :
               
                total = (strStored);
             
                ed10c= total;
               
                break;

                case "0.2 EUR" :
                
                total = (strStored);
               
                ed20c = total;
                
                break;

                case "0.5 EUR" :
               
                total = (strStored);
            
                ed50c = total;
               
                break;

                case "1.0 EUR" :
                
                total = (strStored);
                
                ed1e = total;
                
                break;

                case "2.0 EUR" :
                
                total = (strStored);
              
                ed2e = total;
                
                break;

            }
                
        }
        if(d1c > ed1c){d1c = ed1c;}
        if(d2c > ed2c){d2c = ed2c;}
        if(d5c > ed5c){d5c = ed5c;} 
        if(d10c > ed10c){d10c = ed10c;} 
        if(d20c > ed20c){d20c = ed20c;}
        if(d50c > ed50c){d50c = ed50c;}
        if(d1e > ed1e){d1e = ed1e;} 
        if(d2e > ed2e){d2e = ed2e;}
        if(d5e > ed5e){d5e = ed5e;} 
        if(d10e > ed10e){d10e = ed10e;} 
        if(d20e > ed20e){d20e = ed20e;} 
        if(d50e > ed50e){d50e = ed50e;} 
        if(d100e > ed100e){d100e = ed100e;} 
        if(d200e > ed200e){d200e = ed200e;}
        if(d500e > ed500e){d500e = ed500e;}
          
                         
                 
                 
         String ch = "EUR";
              
             scs_spo.SFloatMoedas(0.01,d1c,ch,0.02,d2c,0.05,d5c,0.1,d10c,0.2,d20c,0.5,d50c,1,d1e,2,d2e);
              
             scs_spo.SFloat(5,d5e,ch,10,d10e,20,d20e,50,d50e,100,d100e,200,d200e);
             
              
  //         scs_spo.ResetSPO();
   //        scs_spo.ResetSCS();
   //         spoSetup = scs_spo.GetSPOSetup();
   //         scsSetup = scs_spo.GetSCSSetup();
            
     /*       try{
                
                Thread.sleep(30000);
            
            
            }catch(Exception ex){
                
                
                
            }*/
            
            
           
        try {
            createdoc();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
            
        
        
           
                                                   
               Connection conn2 = null;
              try {
                  conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                          + "user=root&password=Pa$$w0rd");
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              String queryy = "INSERT INTO ith_transacoes (Data,documento,valor_doc,pago_com,valor_dispensado,user) SELECT ith_periodo.Data,ith_periodo.documento,ith_periodo.valor_doc,ith_periodo.pago_com,ith_periodo.valor_dispensado,ith_periodo.user  FROM ith_periodo";
              
              
              PreparedStatement preparedStmt = null;
              try {
                  preparedStmt = conn2.prepareStatement(queryy);
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
              try {
                  preparedStmt.execute();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
             
              
              String del = "delete  FROM ith_periodo;";
              
              PreparedStatement preparedStmt2 = null;
        try {
            preparedStmt2 = conn2.prepareStatement(del);
        } catch (SQLException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
              
              try {
                  preparedStmt2.execute();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
             
              
              
                 try {
                  conn2.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
                 
                  
      
        
          
          
          try {
            sendemail();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          AutoDismiss.showOptionDialog(rootPane, "Fecho Realizado, vai agora reiniciar!", "ATENÇÃO" ,3000);
          
          Runtime rt = Runtime.getRuntime();
      /*  try {
      //      Process pr = rt.exec("sudo init 6");
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }*/
          sendNotify("Fecho Efetuado com Sucesso");
}boolean sensorcofre = false;

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
       
      sensorcofre = true;
      
          AutoDismiss.showOptionDialog(rootPane, "Pode Retirar o Cofre","Atenção" ,2000);
       
    }//GEN-LAST:event_jButton39ActionPerformed

    private void retroceder_pass3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass3MouseClicked
       Supervisor.hide();
       ecraconfigV2.show();
       
    }//GEN-LAST:event_retroceder_pass3MouseClicked

    public   int spodenom5e;
     public   int spodenom10e;
     public   int spodenom20e;
     public   int spodenom50e;
     public   int spodenom100e;
     public   int spodenom200e;
    
    public void lerspodenoms(){
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select * from  SPOlimits;");

          
            while(rss2.next()){

                spodenom5e = rss2.getInt(3);
                spodenom10e = rss2.getInt(4);
                spodenom20e = rss2.getInt(5);
                spodenom50e = rss2.getInt(6);
                spodenom100e = rss2.getInt(7);
                spodenom200e = rss2.getInt(8);

            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
            
        }
        
       
       
       
       
   }
    
    
    
    
    
    
    
    private void panel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel5MouseClicked
      
  
        
        
    }//GEN-LAST:event_panel5MouseClicked

    private void jLabel43KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel43KeyPressed

    }//GEN-LAST:event_jLabel43KeyPressed

    private void but_float2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_float2ActionPerformed
         ArrayList<VendasHits> ListadePessoas = new ArrayList();
       
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT HOUR(Data) as hour, COUNT(*) as num_rows FROM `ith_transacoes` WHERE documento  LIKE 'FT%' GROUP BY HOUR(Data);");

         
            while(rss2.next()){

               

               ListadePessoas.add(new VendasHits(rss2.getString(1),rss2.getInt(2)));
                
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }
       
       
       
       
  //    NewJFrame graficoDeBarra = new NewJFrame();
       
      this.panel5.setLayout(new BorderLayout());
      this.panel5.setPreferredSize( new java.awt.Dimension( 396 , 43 ) );
      this.panel5.add(CriarGrafico(ListadePessoas));
      pack();
     
      
        ArrayList<VendasHits> ListadePessoas2 = new ArrayList();
       
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT HOUR(Data) as hour, sum(valor_doc) as Volume FROM `ith_transacoes` WHERE documento  LIKE 'F%' GROUP BY HOUR(Data);");

         
            while(rss2.next()){

               

               ListadePessoas2.add(new VendasHits(rss2.getString(1),rss2.getInt(2)));
                
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }
       
      this.panel2.setLayout(new BorderLayout());
      this.panel2.setPreferredSize( new java.awt.Dimension( 396 , 43 ) );
      this.panel2.add(CriarGrafico2(ListadePessoas2));
      pack();
     
      
        
        ArrayList<VendasHits> ListadePessoas3 = new ArrayList();
       
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT HOUR(Data) as hour, sum(valor_doc) as Volume FROM `ith_periodo` WHERE documento  LIKE 'F%' GROUP BY HOUR(Data);");

         
            while(rss2.next()){

               

               ListadePessoas3.add(new VendasHits(rss2.getString(1),rss2.getInt(2)));
                
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }
       
      this.panel3.setLayout(new BorderLayout());
      this.panel3.setPreferredSize( new java.awt.Dimension( 396 , 43 ) );
      this.panel3.add(CriarGrafico3(ListadePessoas3));
      pack();
     
      
      
        try{
           Statement stt;
            ResultSet rss;
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_periodo");
            
            while(rss.next()){
               double totalf = rss.getDouble(1);
               totalf = totalf *100;
               totalf = Math.round(totalf);
               totalf = totalf /100;
               valorperiodo1.setText(String.valueOf(totalf)+ " EUR");
            }
            rss.close();
            stt.close();
        }catch(SQLException ex){
        }
        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

           

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofrefecho1.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
        
       
        
        

        double totalcaixa = 0;

        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
            
            switch (strNote){

                case "5.0 EUR" :
               
                total = Math.round(strStored*5*100);
                total = total /100;
                totalcaixa += total;
              
                break;

                case "10.0 EUR" :
               
                total = Math.round(strStored*10*100);
                total = total /100;
                totalcaixa += total;
               
                break;

                case "20.0 EUR" :
                
                total = Math.round(strStored*20*100);
                total = total /100;
                totalcaixa += total;
                
                break;

                case "50.0 EUR" :
                
                total = Math.round(strStored*50*100);
                total = total /100;
                totalcaixa += total;
                
                break;

                case "100.0 EUR" :
                
                total = Math.round(strStored*100*100);
                total = total /100;
                totalcaixa += total;
                
                break;

                case "200.0 EUR" :
               
                total = Math.round(strStored*200*100);
                total = total /100;
                totalcaixa += total;
               
                break;

                case "500.0 EUR" :
              
                total = Math.round(strStored*500*100);
                total = total /100;
                totalcaixa += total;
               
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
           
            switch (strcoin){

                case "0.01 EUR" :

                total = Math.round(strStored*0.01*100);
                total = total /100;
                totalcaixa += total;
               

                break;

                case "0.02 EUR" :
               
                total = Math.round(strStored*0.02*100);
                total = total /100;
                totalcaixa += total;
               
                break;

                case "0.05 EUR" :
                
                total = Math.round(strStored*0.05*100);
                total = total /100;
                totalcaixa += total;
                
                break;

                case "0.1 EUR" :
               
                total = Math.round(strStored*0.10*100);
                total = total /100;
                totalcaixa += total;
               
                break;

                case "0.2 EUR" :
                
                total = Math.round(strStored*0.20*100);
                total = total /100;
                totalcaixa += total;
                
                break;

                case "0.5 EUR" :
                
                total = Math.round(strStored*0.50*100);
                total = total /100;
                totalcaixa += total;
               
                break;

                case "1.0 EUR" :
                
                total = Math.round(strStored*1*100);
                total = total /100;
                totalcaixa += total;
              
                break;

                case "2.0 EUR" :
               
                total = Math.round(strStored*2*100);
                total = total /100;
                totalcaixa += total;
               
                break;

            }
            totalcaixa = Math.round(totalcaixa*100);
            totalcaixa = totalcaixa /100;

            totalmaquinafecho1.setText(String.valueOf(totalcaixa)+" EUR");
        }  
        
          
      try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select valorfmaneiototal from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";
            
            rss2 = stt2.executeQuery(queryy);
            
            
        
    
        

            while(rss2.next()){

               fundomaneiodef1.setText((rss2.getString(1)));
               
             // System.out.println(fmaneiodef);
               
               
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            
            System.out.println(ex);
        }
      
      PasswordPrompt.hide();
      ecraconfigV2.hide();
      EstadoGeral.show();
      
    }//GEN-LAST:event_but_float2ActionPerformed

    public void sendNotify(String msg){
        
        String uID="";
 try{

            Statement stt3;
            ResultSet rss3;

            Connection conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt3 = conn3.createStatement();
            String queryy3 = "select * from AppUID";

            rss3 = stt3.executeQuery(queryy3);

            while(rss3.next()){

            uID =(rss3.getString(2));
               

            }

            rss3.close();
            stt3.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }

        
        




try {
   String jsonResponse;
   
   URL url = new URL("https://onesignal.com/api/v1/notifications");
   HttpURLConnection con = (HttpURLConnection)url.openConnection();
   con.setUseCaches(false);
   con.setDoOutput(true);
   con.setDoInput(true);

   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
   con.setRequestProperty("Authorization", "Basic YzNlNTM5MjgtZjI1Ni00NzllLTk2MGUtMzEyZWQyMDg0OGFj");
   con.setRequestMethod("POST");

   String strJsonBody = "{"
                      +   "\"app_id\": \"be6be266-f2b8-474f-9809-dc3e0c348b0a\","
                      +   "\"channel_for_external_user_ids\": \"push\","
                      +   "\"include_external_user_ids\": [\""+uID+"\"],"
                      +   "\"data\": {\"foo\": \"bar\"},"
                      +   "\"contents\": {\"en\": \""+MachineName+" -> "+msg+"\"}"
                      + "}";
   
         //  +   "\"app_id\":\""+uID+"\" ,"
      //"\"android_channel_id\":\"7379f698-04c3-42a6-b580-07f0fec83514\","   
         
   
   System.out.println("strJsonBody:\n" + strJsonBody);

   byte[] sendBytes = strJsonBody.getBytes("UTF-8");
   con.setFixedLengthStreamingMode(sendBytes.length);

   OutputStream outputStream = con.getOutputStream();
   outputStream.write(sendBytes);

   int httpResponse = con.getResponseCode();
   System.out.println("httpResponse: " + httpResponse);

   if (  httpResponse >= HttpURLConnection.HTTP_OK
      && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
      Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
      scanner.close();
   }
   else {
      Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
      scanner.close();
   }
   System.out.println("jsonResponse:\n" + jsonResponse);
   
} catch(Throwable t) {
   t.printStackTrace();
}

        
        
    }
    
    
    
    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
       EstadoGeral.hide();
       ecraconfigV2.show();      
    }//GEN-LAST:event_jLabel43MouseClicked

    private void but_float3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_float3ActionPerformed
  Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo init 6");
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }




        // TODO add your handling code here:
    }//GEN-LAST:event_but_float3ActionPerformed

    private void nv1cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv1cMouseClicked
       nv1c.setText("");
        
                keyboard = new PopUpKeyboard(nv1c);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

               
    }//GEN-LAST:event_nv1cMouseClicked

    private void nv10cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv10cMouseClicked
         nv10c.setText("");
        
                keyboard = new PopUpKeyboard(nv10c);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv10cMouseClicked

    private void nv50cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv50cMouseClicked
       nv50c.setText("");
        
                keyboard = new PopUpKeyboard(nv50c);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv50cMouseClicked

    private void nv20cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv20cMouseClicked
         nv20c.setText("");
        
                keyboard = new PopUpKeyboard(nv20c);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv20cMouseClicked

    private void nv2cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv2cMouseClicked
         nv2c.setText("");
        
                keyboard = new PopUpKeyboard(nv2c);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv2cMouseClicked

    private void nv1eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv1eMouseClicked
         nv1e.setText("");
        
                keyboard = new PopUpKeyboard(nv1e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv1eMouseClicked

    private void nv2eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv2eMouseClicked
          nv2e.setText("");
        
                keyboard = new PopUpKeyboard(nv2e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv2eMouseClicked

    private void nv5eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv5eMouseClicked
         nv5e.setText("");
        
                keyboard = new PopUpKeyboard(nv5e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv5eMouseClicked

    private void nv10eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv10eMouseClicked
          nv10e.setText("");
        
                keyboard = new PopUpKeyboard(nv10e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv10eMouseClicked

    private void nv20eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv20eMouseClicked
        nv20e.setText("");
        
                keyboard = new PopUpKeyboard(nv20e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv20eMouseClicked

    private void nv50eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv50eMouseClicked
         nv50e.setText("");
        
                keyboard = new PopUpKeyboard(nv50e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv50eMouseClicked

    private void nv100eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv100eMouseClicked
         nv100e.setText("");
        
                keyboard = new PopUpKeyboard(nv100e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv100eMouseClicked

    private void nv100eMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv100eMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nv100eMouseEntered

    private void nv200eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv200eMouseClicked
          nv200e.setText("");
        
                keyboard = new PopUpKeyboard(nv200e);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv200eMouseClicked

    private void retroceder_pass12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass12MouseClicked
        ecraconfigV2.show();
       Niveis_Minimos.hide();
       
       
         try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
             
        	

		
        
         
        String query = " insert into NiveisMinimos (nv1c,nv2c,nv5c,nv10c,nv20c,nv50c,nv1e,nv2e,nv5e,nv10e,nv20e,nv50e,nv100e,nv200e)"
        + " values (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble (1, Integer.parseInt(nv1c.getText()));
      preparedStmt.setDouble (2,Integer.parseInt(nv2c.getText()));
      preparedStmt.setDouble (3, Integer.parseInt(nv5c.getText()));
      preparedStmt.setDouble (4, Integer.parseInt(nv10c.getText()));
      preparedStmt.setDouble (5, Integer.parseInt(nv20c.getText()));
      preparedStmt.setDouble (6, Integer.parseInt(nv50c.getText()));
      preparedStmt.setDouble (7, Integer.parseInt(nv1e.getText()));
      preparedStmt.setDouble (8, Integer.parseInt(nv2e.getText()));
      preparedStmt.setDouble (9, Integer.parseInt(nv5e.getText()));
      preparedStmt.setDouble (10, Integer.parseInt(nv10e.getText()));
      preparedStmt.setDouble (11, Integer.parseInt(nv20e.getText()));
      preparedStmt.setDouble (12, Integer.parseInt(nv50e.getText()));
      preparedStmt.setDouble (13,Integer.parseInt(nv100e.getText()));
      preparedStmt.setDouble (14,Integer.parseInt(nv200e.getText()));
     
      // execute the preparedstatement
      preparedStmt.execute();
      
      con.close();
        
        
      }catch(SQLException ex){
          System.out.println(ex);
         }
        
    }//GEN-LAST:event_retroceder_pass12MouseClicked

    private void nv5cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nv5cMouseClicked
          nv5c.setText("");
        
                keyboard = new PopUpKeyboard(nv5c);
                keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
                keyboard.setVisible(true);
     
        

    }//GEN-LAST:event_nv5cMouseClicked

    private void AcFundomaneioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcFundomaneioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AcFundomaneioActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
   
        
        
        
        
        
        
        
       int ac1 = 0;
       int ac2 = 0;
       int ac3 = 0;
       int ac4 = 0;
       int ac5 = 0;
       int ac6 = 0;
       int ac7 = 0;
       int ac8 = 0;
       int ac9 = 0;
       int ac10 = 0;
       int ac11 = 0;
       int ac12 = 0;
        
       if (AcCarregamentos.isSelected()){ac1 = 1;}
       if (AcExistencias.isSelected()){ac2 = 1;}
       if (AcPagamentos.isSelected()){ac3 = 1;}
       if (AcFechoperiodo.isSelected()){ac4 = 1;}
       if (AcPainelbordo.isSelected()){ac5 = 1;}
       if (AcDefnotas.isSelected()){ac6 = 1;}
       if (AcFundomaneio.isSelected()){ac7 = 1;}
       if (AcNiveisminimos.isSelected()){ac8 = 1;}
       if (AcEstadoGeral.isSelected()){ac9 = 1;}
       if (AcSupervisor.isSelected()){ac10 = 1;}
       if (AcConsultacofre.isSelected()){ac11 = 1;}
       if (AcReinicio.isSelected()){ac12 = 1;}
       
        
        
        
        try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
      String query = " UPDATE categorias SET carregamentos=?,existencias=?,pagamentos=?,fechoperiodo=?,painelbordo=?,defnotas=?,fundomaneio=?,niveisminimos=?,estadogeral=?,supervisor=?,consultacofre=?,reinicio=? WHERE categoria=1";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, ac1);
      preparedStmt.setInt (2, ac2);
      preparedStmt.setInt (3, ac3);
      preparedStmt.setInt (4, ac4);
      preparedStmt.setInt (5, ac5);
      preparedStmt.setInt (6, ac6);
      preparedStmt.setInt (7, ac7);
      preparedStmt.setInt (8, ac8);
      preparedStmt.setInt (9, ac9);
      preparedStmt.setInt (10, ac10);
      preparedStmt.setInt (11, ac11);
      preparedStmt.setInt (12, ac12);
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }
        
      

        String p1 = superpass.getText();
        String p2 = operpass.getText();
        
  try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
      String query = " UPDATE users SET password=? WHERE id=1";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setString (1, p1);
   
  preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }
        



 try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
      String query = " UPDATE users SET password=? WHERE id=2";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setString (1, p2);
   
  preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }
        



Supervisor.hide();
PasswordPrompt.show();






        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
      scs_spo.DisableCoinValidator();
      
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        numerovezes = numerovezes + 1;

        if (numerovezes == 2) {
            SplashScreen.hide();
            PasswordPrompt.show();
            numerovezes=0;
        }
    }//GEN-LAST:event_jLabel40MouseClicked

    private void superpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_superpassMouseClicked
        superpass.setText("");

        keyboard = new PopUpKeyboard(superpass);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_superpassMouseClicked

    private void operpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operpassMouseClicked
        operpass.setText("");

        keyboard = new PopUpKeyboard(operpass);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_operpassMouseClicked

    
    
    public void fechoVal(){
        
          AutoDismiss.showOptionDialog(rootPane, "Esta Operação pode demorar um pouco, aguarde pf", "ATENÇÃO" ,3000);
           
           
        try {
            createdoc();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
                                                
               Connection conn2 = null;
              try {
                  conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                          + "user=root&password=Pa$$w0rd");
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              String queryy = "INSERT INTO ith_transacoes (Data,documento,valor_doc,pago_com,valor_dispensado,user) SELECT ith_periodo.Data,ith_periodo.documento,ith_periodo.valor_doc,ith_periodo.pago_com,ith_periodo.valor_dispensado,ith_periodo.user  FROM ith_periodo";
              
              
              PreparedStatement preparedStmt = null;
              try {
                  preparedStmt = conn2.prepareStatement(queryy);
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
              try {
                  preparedStmt.execute();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              String del = "delete  FROM ith_periodo;";
              
              PreparedStatement preparedStmt2 = null;
        try {
            preparedStmt2 = conn2.prepareStatement(del);
        } catch (SQLException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
              
              try {
                  preparedStmt2.execute();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
             
              
              
                 try {
                  conn2.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
             
          try {
            sendemail();
        } catch (UnsupportedEncodingException  ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            
        }
          
          AutoDismiss.showOptionDialog(rootPane, "Fecho Realizado, vai agora iniciar a movimentação de fundos!", "ATENÇÃO" ,3000);
          AutoDismiss.showOptionDialog(rootPane, "Aguarde pelo termino dos validadores pf!", "ATENÇÃO" ,3000);
          
          Runtime rt = Runtime.getRuntime();
    
         String TotalFecho = "";
         double fmn5 = 0;
         double fmn10 = 0;
         double fmn20 = 0;
         double fmn50 = 0;
         double fmn100 = 0;
         double fmn200 = 0;
        

        try{

            Statement stt3;
            ResultSet rss3;

            Connection conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt3 = conn3.createStatement();
            String queryy3 = "select * from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";

            rss3 = stt3.executeQuery(queryy3);

            while(rss3.next()){

                TotalFecho =(rss3.getString(18));
                fmn5 = (rss3.getDouble(10));
                fmn10 = (rss3.getDouble(11));
                fmn20 = (rss3.getDouble(12));
                fmn50 = (rss3.getDouble(13));
                fmn100 = (rss3.getDouble(14));
                fmn200 = (rss3.getDouble(15));

            }

            rss3.close();
            stt3.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }

        double tspo=0;
        double totscs=0;
        double totalgreal = 0;
        double fmspo=0;
        double n200=0;
        double n100=0;
        double n50=0;
        double n20=0;
        double n10=0;
        double n5=0;
        

        try{

            Statement stt3;
            ResultSet rss3;

            Connection conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt3 = conn3.createStatement();
            String queryy3 = "select * from  EstadoAtual ";

            rss3 = stt3.executeQuery(queryy3);

            while(rss3.next()){

                tspo =(rss3.getDouble(18));
                totscs = (rss3.getDouble(19));
                n200=(rss3.getDouble(16));
                n100=(rss3.getDouble(15));
                n50=(rss3.getDouble(14));
                n20=(rss3.getDouble(13));
                n10=(rss3.getDouble(12));
                n5=(rss3.getDouble(11));
             
            }

            rss3.close();
            stt3.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }

        String[] s = TotalFecho.split(" ");
        Pattern p = Pattern.compile ("(?!=\\d\\.\\d\\.)([\\d.]+)");
        double d = 0;
                    for (String item : s) {
                        Matcher m = p.matcher(item);
                        if(m.find()){
            //                System.out.println("fdqewf");
                       //     Double.parseDouble(p.replace(',','.'))
                            d = Double.parseDouble(m.group(1).replace(',','.'));
                        }
                    }
        System.out.println("valor " + d);
        double totgeral = d;
        
     //   totgeral = Double.valueOf(TotalFecho);

        totalgreal = tspo + totscs;

        if (totalgreal < totgeral){

            AutoDismiss.showOptionDialog(rootPane, "O valor nos reciladores é inferior ao definido no fundo de maneio!! Redefina pf", "ATENÇÃO" ,5000);return;
        }
  
        d=totalgreal - d;
        System.out.println(d + "jkldashfbvlisdfnvçl-dqefbnmçkjqedfbvçnqeçf.kvbçhel");
        
        
        fmspo = (fmn5*5)+(fmn10*10)+(fmn20*20)+(fmn50*50)+(fmn100*100)+(fmn200*200);
        double fmspo2 = d - (d % 5);
        
        
        double fmscs = d - fmspo2 ;
        
        double S1 = totscs-fmscs;
        
          S1 = S1 *100;

                S1 = Math.round(S1);
                S1 = S1 /100;
        double SP1 = tspo-fmspo2;
        
        double S2 = tspo - SP1;
        System.out.println("moedas - " + S1 + "Notas - " + S2);
        System.out.println(S2 + "valorspo");
        System.out.println(S1 + "valomoedas");
          
         if(S1!=0){
            scs_spo.FvalorMoedas(S1);
        }
        
        if (S2>0){
        
          double f200 = S2 - S2 % 200.0;
        double r200 = S2 % 200.0;
        double f200_2 = f200 / 200.0;
        double f200_1 = f200_2 - n200;
        
        if (f200_1==n200){
            
            f200_1=0;
            f200=r200;   
        } 
        if (f200_1<0){
            
            f200_1=Math.abs(f200_1);
           f200=f200-((n200-f200_1)*200)+r200;
      //      f200_1=n200-f200_1;
            
        }
        
        else if (f200_1>0){
            
            f200_1=0;
            f200=f200-(n200*200)+r200;
        }
        
        //------/
        
        double f100 = f200 - f200 % 100.0;
        double r100 = f200 % 100.0;
        double f100_2 = f100 / 100.0;
        double f100_1 = f100_2 - n100;
        
         if (f100_1==n100){
            
            f100_1=0;
            f100=r100;   
        } 
        if (f100_1<0){
            
            f100_1=Math.abs(f100_1);
          f100=f100-((n100-f100_1)*100)+r100;
         //   f100_1=n100-f100_1;
            
        }
        
       else if (f100_1>0){
            
            f100_1=0;
            f100=f100-(n100*100)+r100;
        }
        
        //-----------------/
        
        double f50 = f100 - f100 % 50.0;
        double r50 = f100 % 50.0;
        double f50_2 = f50 / 50.0;
        System.out.println(f50_2);
        double f50_1 = f50_2 - n50;
        
         if (f50_1==n50){
            
            f50_1=0;
            f50=r50;   
        } 
        if (f50_1<0){
            
            f50_1=Math.abs(f50_1);
            f50=f50-((n50-f50_1)*50)+r50;
          //  f50_1=n50-f50_1;
            
        }
        
        else if (f50_1>0){
            
            f50_1=0;
            f50=f50-(n50*50)+r50;
            System.out.println(f50);
        }
        
         //-----------------/
        
        double f20 = f50 - f50 % 20.0;
        double r20 = f50 % 20.0;
        double f20_2 = f20 / 20.0;
        System.out.println(f20_2);
        double f20_1 = f20_2 - n20;
        
         if (f20_1==n20){
            
            f20_1=0;
            f20=r20;   
        } 
        if (f20_1<0){
            
            f20_1=Math.abs(f20_1);
            System.out.println(f20_1);
            f20=f20-((n20-f20_1)*20)+r20;
          //  f20_1=n20-f20_1;
            System.out.println(f20);
            
        }else if (f20_1>0){
            
            f20_1=0;
            f20=f20-(n20*20)+r20;
        }
       
         //-----------------/
        
        double f10 = f20 - f20 % 10.0;
        double r10 = f20 % 10.0;
        double f10_2 = f10 / 10.0;
        double f10_1 = f10_2 - n10;
        
         if (f10_1==n10){
            
            f10_1=0;
            f10=r10;   
        } 
        if (f10_1<0){
            
            f10_1=Math.abs(f10_1);
           f10=f10-((n10-f10_1)*10)+r10;
        //    f10_1=n10-f10_1;
            
        }
        
        else if (f10_1>0){
            
            f10_1=0;
            f10=f10-(n10*10)+r10;
        }
        
        
        
        double f5 = f10 - f10 % 5;
        double r5 = f10 % 5;
        double f5_2 = f5 / 5;
        double f5_1 = f5_2 - n5;
        
         if (f5_1==n5){
            
            f5_1=0;
            f5=r5;   
        } 
        if (f5_1<0){
            
            f5_1=Math.abs(f5_1);
            f5=f5-((n5-f5_1)*5)+r5;
        //    f5_1=n5-f5_1;
            
        }
        
       else if (f5_1>0){
            
            f5_1=0;
            f5=f5-(n5*5)+r5;
        }
     
    //    scs_spo.FvalorNotas(SP1);
    String ch = "EUR";
    System.out.println("200 "+f200_1);
    System.out.println("100 "+f100_1);
    System.out.println("50 "+f50_1);
    System.out.println("20 "+f20_1);
    System.out.println("10 "+f10_1);
    System.out.println("5 "+f5_1);
    
    f200_2=f200_2-n200;
 
  scs_spo.SFloat(5,f5_1,ch,10,f10_1,20,f20_1,50,f50_1,100,f100_1,200,f200_1);
  
        }
        S1 = totscs-S1;
        S1 = S1 *100;
        S1 = Math.round(S1);
        S1 = S1 /100;
       
         AutoDismiss.showOptionDialog(rootPane, "Valores a movimentar -> | Moedas - "+S1+" € | Notas - "+S2+" € ", "ATENÇÃO" ,3000);
         sendNotify("Valores movimentados para os cofres no fecho -> | Moedas - "+S1+" € | Notas - "+S2+" € ");
         
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Esta ação irá esvaziar o Reciclador, tem a certeza?","Aviso!!",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
        
        scs_spo.EmptySmartPO();
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
           
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Esta ação irá esvaziar o Reciclador, tem a certeza?","Aviso!!",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
        int dialogResult2 = JOptionPane.showConfirmDialog (null, "ATENÇÃO - Se o nivel de moedas no hopper for maior que a capacidade do cofre de moedas irá provocar danos \n irreversiveis no validador de moedas, certifique-se que o nivel não excede a capacidade do cofre pf","Aviso!!",dialogButton);
        if(dialogResult2 == JOptionPane.YES_OPTION){
        scs_spo.EmptySmartCS();
        
        }
        
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked

       
    }//GEN-LAST:event_jLabel32MouseClicked

    private void retroceder_pass8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass8MouseClicked
       
        
         scs_spo.Unrefill();
        
        
        troco = 0;
        valorpago=0;
        valorP = 0;
        valortotal = 0;
        ammount=0;
        carregaconsulta = false;
       
        scs_spo.DisableCoinValidator();
        scs_spo.DisableNoteValidator();
        atualizacontadores();
        
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.show();
        Pagamentos.hide();
        CarregaV2.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();
                
    }//GEN-LAST:event_retroceder_pass8MouseClicked

    private void retroceder_pass9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass9MouseClicked
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.show();
        Pagamentos.hide();
        ConsultaV2.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();
    }//GEN-LAST:event_retroceder_pass9MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       fechoVal();
       
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel97MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel97MouseClicked
        fecho();
    }//GEN-LAST:event_jLabel97MouseClicked

    private void but_float5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_float5ActionPerformed

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=5 ");

            if (rss2.next() == false) {
                cc5.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc5.setText(String.valueOf(totalint)+ " |" + totalint*5 + " EUR");
                } while (rss2.next());
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`= 10 ");

            if (rss2.next() == false) {
                cc10.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc10.setText(String.valueOf(totalint)+ "|" + totalint*10 + " EUR");
                } while (rss2.next());
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=20 ");

            if (rss2.next() == false) {
                cc20.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc20.setText(String.valueOf(totalint)+ "|" + totalint*20 + " EUR");
                } while (rss2.next());
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=50 ");

            if (rss2.next() == false) {
                cc50.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc50.setText(String.valueOf(totalint)+ "|" + totalint*50 + " EUR");
                } while (rss2.next());
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=100 ");

            if (rss2.next() == false) {
                cc100.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc100.setText(String.valueOf(totalint)+ "|" + totalint*100 + " EUR");
                } while (rss2.next());
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=200 ");

            if (rss2.next() == false) {
                cc200.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc200.setText(String.valueOf(totalint)+ "|" + totalint*200 + " EUR");
                } while (rss2.next());
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT `valor`, COUNT(*) AS `count` FROM cofre WHERE `valor`=500 ");

            if (rss2.next() == false) {
                cc500.setText("0 | 0 EUR");
            } else {
                do {
                    double totalf2 = rss2.getDouble(2);
                    int totalint = (int) totalf2;
                    cc500.setText(String.valueOf(totalint)+ "|" + totalint*500 + " EUR");
                } while (rss2.next());
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                ccvalortotal.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        ConsultaCofre.show();
        ecraconfigV2.hide();
    }//GEN-LAST:event_but_float5ActionPerformed

    private void but_consultas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_consultas2ActionPerformed

        ConsultaV2.show();
        ecraconfigV2.hide();
        atualizacontadores();
    }//GEN-LAST:event_but_consultas2ActionPerformed

    private void But_painelbordo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But_painelbordo1ActionPerformed

        DefaultTableModel model = new DefaultTableModel(new String[]{"Data", "Documento", "Valor Introduzido","Valor Documento","Valor Dispensado ","Utilizador"}, 0);

        try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select * from ith_periodo");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

            while(rs.next()){
                String data = dateFormat.format(rs.getTimestamp(1));

                String documento = rs.getString(2);

                String valordoc = String.valueOf(rs.getDouble(3))+" €";

                String pagocom = String.valueOf(rs.getDouble(4))+" €";

                String dispen = String.valueOf(rs.getDouble(5))+" €";

                String user = rs.getString(6);

                model.addRow(new Object[]{ data, documento, valordoc,pagocom,dispen,user }) ;

            }
            table.setFillsViewportHeight(true);
            table.setModel(model);

            rs.close();
            st.close();

        }catch(SQLException ex){
        }

        try{

            Statement stt;
            ResultSet rss;

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_periodo");//where id in (1,2,3); ");

            while(rss.next()){

                double totalf = rss.getDouble(1);

                totalf = totalf *100;

                totalf = Math.round(totalf);
                totalf = totalf /100;

                totalfaturado.setText(String.valueOf(totalf)+ " EUR");

            }

            rss.close();
            stt.close();

        }catch(SQLException ex){
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre ");//where id in (1,2,3); ");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofre.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor_carregado) from  Carregamentos where DATE(reg_date) like CURDATE()");//where id in (1,2,3); ");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcarregamentos.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor_dispensado) from  ith_periodo WHERE Documento like 'Pgt Div%' ");//where id in (1,2,3); ");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalpagamentos.setText(String.valueOf(totalf2)+ " EUR");
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        atualizacontadores();
        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select totgeral from  EstadoAtual WHERE id = (select max(ID) from EstadoAtual)");//where id in (1,2,3); ");

            while(rss2.next()){
                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                fundomaneio.setText(String.valueOf(totalf2)+" EUR");
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        ecraconfigV2.hide();
        ecratransacoes.show();
    }//GEN-LAST:event_But_painelbordo1ActionPerformed

    private void but_float6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_float6ActionPerformed
        ArrayList<VendasHits> ListadePessoas = new ArrayList();

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT HOUR(Data) as hour, COUNT(*) as num_rows FROM `ith_transacoes` WHERE documento  LIKE 'FT%' GROUP BY HOUR(Data);");

            while(rss2.next()){

                ListadePessoas.add(new VendasHits(rss2.getString(1),rss2.getInt(2)));

            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        //    NewJFrame graficoDeBarra = new NewJFrame();

        this.panel5.setLayout(new BorderLayout());
        this.panel5.setPreferredSize( new java.awt.Dimension( 396 , 43 ) );
        this.panel5.add(CriarGrafico(ListadePessoas));
        pack();

        ArrayList<VendasHits> ListadePessoas2 = new ArrayList();

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT HOUR(Data) as hour, sum(valor_doc) as Volume FROM `ith_transacoes` WHERE documento  LIKE 'F%' GROUP BY HOUR(Data);");

            while(rss2.next()){

                ListadePessoas2.add(new VendasHits(rss2.getString(1),rss2.getInt(2)));

            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        this.panel2.setLayout(new BorderLayout());
        this.panel2.setPreferredSize( new java.awt.Dimension( 396 , 43 ) );
        this.panel2.add(CriarGrafico2(ListadePessoas2));
        pack();

        ArrayList<VendasHits> ListadePessoas3 = new ArrayList();

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("SELECT HOUR(Data) as hour, sum(valor_doc) as Volume FROM `ith_periodo` WHERE documento  LIKE 'F%' GROUP BY HOUR(Data);");

            while(rss2.next()){

                ListadePessoas3.add(new VendasHits(rss2.getString(1),rss2.getInt(2)));

            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        this.panel3.setLayout(new BorderLayout());
        this.panel3.setPreferredSize( new java.awt.Dimension( 396 , 43 ) );
        this.panel3.add(CriarGrafico3(ListadePessoas3));
        pack();

        try{
            Statement stt;
            ResultSet rss;
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");
            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_periodo");

            while(rss.next()){
                double totalf = rss.getDouble(1);
                totalf = totalf *100;
                totalf = Math.round(totalf);
                totalf = totalf /100;
                valorperiodo1.setText(String.valueOf(totalf)+ " EUR");
            }
            rss.close();
            stt.close();
        }catch(SQLException ex){
        }
        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofrefecho1.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        double totalcaixa = 0;

        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;

            switch (strNote){

                case "5.0 EUR" :

                total = Math.round(strStored*5*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "10.0 EUR" :

                total = Math.round(strStored*10*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "20.0 EUR" :

                total = Math.round(strStored*20*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "50.0 EUR" :

                total = Math.round(strStored*50*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "100.0 EUR" :

                total = Math.round(strStored*100*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "200.0 EUR" :

                total = Math.round(strStored*200*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "500.0 EUR" :

                total = Math.round(strStored*500*100);
                total = total /100;
                totalcaixa += total;

                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;

            switch (strcoin){

                case "0.01 EUR" :

                total = Math.round(strStored*0.01*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "0.02 EUR" :

                total = Math.round(strStored*0.02*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "0.05 EUR" :

                total = Math.round(strStored*0.05*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "0.1 EUR" :

                total = Math.round(strStored*0.10*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "0.2 EUR" :

                total = Math.round(strStored*0.20*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "0.5 EUR" :

                total = Math.round(strStored*0.50*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "1.0 EUR" :

                total = Math.round(strStored*1*100);
                total = total /100;
                totalcaixa += total;

                break;

                case "2.0 EUR" :

                total = Math.round(strStored*2*100);
                total = total /100;
                totalcaixa += total;

                break;

            }
            totalcaixa = Math.round(totalcaixa*100);
            totalcaixa = totalcaixa /100;

            totalmaquinafecho1.setText(String.valueOf(totalcaixa)+" EUR");
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select valorfmaneiototal from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";

            rss2 = stt2.executeQuery(queryy);

            while(rss2.next()){

                fundomaneiodef1.setText((rss2.getString(1)));

                // System.out.println(fmaneiodef);

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }

        PasswordPrompt.hide();
        ecraconfigV2.hide();
        
        EstadoGeral.show();
    }//GEN-LAST:event_but_float6ActionPerformed

    private void but_emptyall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_emptyall1ActionPerformed

        lerspodenoms();
        spo5e.setText(Integer.toString(spodenom5e));
        spo10e.setText(Integer.toString(spodenom10e));
        spo20e.setText(Integer.toString(spodenom20e));
        spo50e.setText(Integer.toString(spodenom50e));
        spo100e.setText(Integer.toString(spodenom100e));
        spo200e.setText(Integer.toString(spodenom200e));

        ecraconfigV2.hide();
        configgeral.show();
    }//GEN-LAST:event_but_emptyall1ActionPerformed

    private void but_trocos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_trocos1ActionPerformed

        scs_spo.ResetSPO();
        scs_spo.ResetSCS();

        int d1c = 0;
        int d2c = 0;
        int d5c= 0;
        int d10c= 0;
        int d20c= 0;
        int d50c= 0;
        int d1e= 0;
        int d2e= 0;
        int d5e= 0;
        int d10e= 0;
        int d20e= 0;
        int d50e= 0;
        int d100e= 0;
        int d200e= 0;
        int d500e= 0;

        spoSetup = scs_spo.GetSPOSetup();
        scsSetup = scs_spo.GetSCSSetup();

        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;

            switch (strNote){

                case "5.0 EUR" :
                d5e=strStored;
                break;

                case "10.0 EUR" :
                d10e=strStored;
                break;

                case "20.0 EUR" :
                d20e=strStored;
                break;

                case "50.0 EUR" :
                d50e=strStored;
                break;

                case "100.0 EUR" :
                d100e=strStored;
                break;

                case "200.0 EUR" :
                d200e=strStored;
                break;

                case "500.0 EUR" :
                d500e=strStored;
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
            switch (strcoin){

                case "0.01 EUR" :
                d1c=strStored;
                break;

                case "0.02 EUR" :
                d2c=strStored;
                break;

                case "0.05 EUR" :
                d5c=strStored;
                break;

                case "0.1 EUR" :
                d10c=strStored;
                break;

                case "0.2 EUR" :
                d20c=strStored;
                break;

                case "0.5 EUR" :
                d50c=strStored;
                break;

                case "1.0 EUR" :
                d1e=strStored;
                break;

                case "2.0 EUR" :
                d2e=strStored;
                break;

            }
        }

        Niveis_Minimos.show();
        ecraconfigV2.hide();

        TitledBorder titled = new TitledBorder("<html><nobr><font size='+1'>"+"1 CENT | "+" <font color='red'>"+ d1c + "</font></nobr></html>");
        nv1c.setBorder(titled);

        TitledBorder titled1 = new TitledBorder("<html><nobr><font size='+1'>"+"2 CENT | "+" <font color='red'>"+ d2c + "</font></nobr></html>");
        nv2c.setBorder(titled1);

        TitledBorder titled2 = new TitledBorder("<html><nobr><font size='+1'>"+"5 CENT | "+" <font color='red'>"+ d5c + "</font></nobr></html>");
        nv5c.setBorder(titled2);

        TitledBorder titled3 = new TitledBorder("<html><nobr><font size='+1'>"+"10 CENT | "+" <font color='red'>"+ d10c + "</font></nobr></html>");
        nv10c.setBorder(titled3);

        TitledBorder titled4 = new TitledBorder("<html><nobr><font size='+1'>"+"20 CENT | "+" <font color='red'>"+ d20c + "</font></nobr></html>");
        nv20c.setBorder(titled4);

        TitledBorder titled5 = new TitledBorder("<html><nobr><font size='+1'>"+"50 CENT | "+" <font color='red'>"+ d50c + "</font></nobr></html>");
        nv50c.setBorder(titled5);

        TitledBorder titled6 = new TitledBorder("<html><nobr><font size='+1'>"+"1 EUR | "+" <font color='red'>"+ d1e + "</font></nobr></html>");
        nv1e.setBorder(titled6);

        TitledBorder titled7 = new TitledBorder("<html><nobr><font size='+1'>"+"2 EUR | "+" <font color='red'>"+ d2e + "</font></nobr></html>");
        nv2e.setBorder(titled7);

        TitledBorder titled8 = new TitledBorder("<html><nobr><font size='+1'>"+"5 EUR | "+" <font color='red'>"+ d5e + "</font></nobr></html>");
        nv5e.setBorder(titled8);

        TitledBorder titled9 = new TitledBorder("<html><nobr><font size='+1'>"+"10 EUR | "+" <font color='red'>"+ d10e + "</font></nobr></html>");
        nv10e.setBorder(titled9);

        TitledBorder titled10 = new TitledBorder("<html><nobr><font size='+1'>"+"20 EUR | "+" <font color='red'>"+ d20e + "</font></nobr></html>");
        nv20e.setBorder(titled10);

        TitledBorder titled11 = new TitledBorder("<html><nobr><font size='+1'>"+"50 EUR | "+" <font color='red'>"+ d50e + "</font></nobr></html>");
        nv50e.setBorder(titled11);

        TitledBorder titled12 = new TitledBorder("<html><nobr><font size='+1'>"+"100 EUR | "+" <font color='red'>"+ d100e + "</font></nobr></html>");
        nv100e.setBorder(titled12);

        TitledBorder titled13 = new TitledBorder("<html><nobr><font size='+1'>"+"200 EUR | "+" <font color='red'>"+ d200e + "</font></nobr></html>");
        nv200e.setBorder(titled13);

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select * from  NiveisMinimos WHERE id=(SELECT MAX(id) FROM NiveisMinimos)";

            rss2 = stt2.executeQuery(queryy);

            while(rss2.next()){

                nv1c.setText(String.valueOf(rss2.getInt(3)));
                nv2c.setText(String.valueOf(rss2.getInt(4)));
                nv5c.setText(String.valueOf(rss2.getInt(5)));
                nv10c.setText(String.valueOf(rss2.getInt(6)));
                nv20c.setText(String.valueOf(rss2.getInt(7)));
                nv50c.setText(String.valueOf(rss2.getInt(8)));
                nv1e.setText(String.valueOf(rss2.getInt(9)));
                nv2e.setText(String.valueOf(rss2.getInt(10)));
                nv5e.setText(String.valueOf(rss2.getInt(11)));
                nv10e.setText(String.valueOf(rss2.getInt(12)));
                nv20e.setText(String.valueOf(rss2.getInt(13)));
                nv50e.setText(String.valueOf(rss2.getInt(14)));
                nv100e.setText(String.valueOf(rss2.getInt(15)));
                nv200e.setText(String.valueOf(rss2.getInt(16)));

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }
    }//GEN-LAST:event_but_trocos1ActionPerformed

    private void but_fmaneiodef1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_fmaneiodef1ActionPerformed

        scs_spo.ResetSPO();
        scs_spo.ResetSCS();

        int d1c = 0;
        int d2c = 0;
        int d5c= 0;
        int d10c= 0;
        int d20c= 0;
        int d50c= 0;
        int d1e= 0;
        int d2e= 0;
        int d5e= 0;
        int d10e= 0;
        int d20e= 0;
        int d50e= 0;
        int d100e= 0;
        int d200e= 0;
        int d500e= 0;

        spoSetup = scs_spo.GetSPOSetup();
        scsSetup = scs_spo.GetSCSSetup();

        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;

            switch (strNote){

                case "5.0 EUR" :
                d5e=strStored;
                break;

                case "10.0 EUR" :
                d10e=strStored;
                break;

                case "20.0 EUR" :
                d20e=strStored;
                break;

                case "50.0 EUR" :
                d50e=strStored;
                break;

                case "100.0 EUR" :
                d100e=strStored;
                break;

                case "200.0 EUR" :
                d200e=strStored;
                break;

                case "500.0 EUR" :
                d500e=strStored;
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
            switch (strcoin){

                case "0.01 EUR" :
                d1c=strStored;
                break;

                case "0.02 EUR" :
                d2c=strStored;
                break;

                case "0.05 EUR" :
                d5c=strStored;
                break;

                case "0.1 EUR" :
                d10c=strStored;
                break;

                case "0.2 EUR" :
                d20c=strStored;
                break;

                case "0.5 EUR" :
                d50c=strStored;
                break;

                case "1.0 EUR" :
                d1e=strStored;
                break;

                case "2.0 EUR" :
                d2e=strStored;
                break;

            }
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select * from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";

            rss2 = stt2.executeQuery(queryy);

            DefinirFManeio.show();
            ecraconfigV2.hide();

            while(rss2.next()){

                TitledBorder titled = new TitledBorder("<html><nobr><font size='+1'>"+"1 CENT | "+" <font color='red'>"+ d1c + "</font></nobr></html>");
                c1.setBorder(titled);

                TitledBorder titled1 = new TitledBorder("<html><nobr><font size='+1'>"+"2 CENT | "+" <font color='red'>"+ d2c + "</font></nobr></html>");
                c2.setBorder(titled1);

                TitledBorder titled2 = new TitledBorder("<html><nobr><font size='+1'>"+"5 CENT | "+" <font color='red'>"+ d5c + "</font></nobr></html>");
                c5.setBorder(titled2);

                TitledBorder titled3 = new TitledBorder("<html><nobr><font size='+1'>"+"10 CENT | "+" <font color='red'>"+ d10c + "</font></nobr></html>");
                c10.setBorder(titled3);

                TitledBorder titled4 = new TitledBorder("<html><nobr><font size='+1'>"+"20 CENT | "+" <font color='red'>"+ d20c + "</font></nobr></html>");
                c20.setBorder(titled4);

                TitledBorder titled5 = new TitledBorder("<html><nobr><font size='+1'>"+"50 CENT | "+" <font color='red'>"+ d50c + "</font></nobr></html>");
                c50.setBorder(titled5);

                TitledBorder titled6 = new TitledBorder("<html><nobr><font size='+1'>"+"1 EUR | "+" <font color='red'>"+ d1e + "</font></nobr></html>");
                e1.setBorder(titled6);

                TitledBorder titled7 = new TitledBorder("<html><nobr><font size='+1'>"+"2 EUR | "+" <font color='red'>"+ d2e + "</font></nobr></html>");
                e2.setBorder(titled7);

                TitledBorder titled8 = new TitledBorder("<html><nobr><font size='+1'>"+"5 EUR | "+" <font color='red'>"+ d5e + "</font></nobr></html>");
                n5.setBorder(titled8);

                TitledBorder titled9 = new TitledBorder("<html><nobr><font size='+1'>"+"10 EUR | "+" <font color='red'>"+ d10e + "</font></nobr></html>");
                n10.setBorder(titled9);

                TitledBorder titled10 = new TitledBorder("<html><nobr><font size='+1'>"+"20 EUR | "+" <font color='red'>"+ d20e + "</font></nobr></html>");
                n20.setBorder(titled10);

                TitledBorder titled11 = new TitledBorder("<html><nobr><font size='+1'>"+"50 EUR | "+" <font color='red'>"+ d50e + "</font></nobr></html>");
                n50.setBorder(titled11);

                TitledBorder titled12 = new TitledBorder("<html><nobr><font size='+1'>"+"100 EUR | "+" <font color='red'>"+ d100e + "</font></nobr></html>");
                n100.setBorder(titled12);

                TitledBorder titled13 = new TitledBorder("<html><nobr><font size='+1'>"+"200 EUR | "+" <font color='red'>"+ d200e + "</font></nobr></html>");
                n200.setBorder(titled13);

                TitledBorder titled14 = new TitledBorder("<html><nobr><font size='+1'>"+"500 EUR | "+" <font color='red'>"+ d500e + "</font></nobr></html>");
                n500.setBorder(titled14);

                // c1.setText("<html><nobr>"+String.valueOf(rss2.getDouble(2))+" <font color='red'> | 15</font></nobr></html>");
                c1.setText(String.valueOf(rss2.getDouble(2)));
                c2.setText(String.valueOf(rss2.getDouble(3)));
                c5.setText(String.valueOf(rss2.getDouble(4)));
                c10.setText(String.valueOf(rss2.getDouble(5)));
                c20.setText(String.valueOf(rss2.getDouble(6)));
                c50.setText(String.valueOf(rss2.getDouble(7)));
                e1.setText(String.valueOf(rss2.getDouble(8)));
                e2.setText(String.valueOf(rss2.getDouble(9)));
                n5.setText(String.valueOf(rss2.getDouble(10)));
                n10.setText(String.valueOf(rss2.getDouble(11)));
                n20.setText(String.valueOf(rss2.getDouble(12)));
                n50.setText(String.valueOf(rss2.getDouble(13)));
                n100.setText(String.valueOf(rss2.getDouble(14)));
                n200.setText(String.valueOf(rss2.getDouble(15)));
                n500.setText(String.valueOf(rss2.getDouble(16)));
                fmaneiodef.setText((rss2.getString(18)));
                // System.out.println(fmaneiodef);

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }
    }//GEN-LAST:event_but_fmaneiodef1ActionPerformed

    private void but_supervisor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_supervisor1ActionPerformed

        try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select * from categorias where categoria = 1 ");

            while(rs.next()){

                if (rs.getInt(3)==0){AcCarregamentos.setSelected(false) ;}else{AcCarregamentos.setSelected(true) ;}
                if (rs.getInt(4)==0){AcExistencias.setSelected(false) ;}else{AcExistencias.setSelected(true) ;}
                if (rs.getInt(5)==0){AcPagamentos.setSelected(false) ;}else{AcPagamentos.setSelected(true) ;}
                if (rs.getInt(6)==0){AcFechoperiodo.setSelected(false) ;}else{AcFechoperiodo.setSelected(true) ;}
                if (rs.getInt(7)==0){AcPainelbordo.setSelected(false) ;}else{AcPainelbordo.setSelected(true) ;}
                if (rs.getInt(8)==0){AcDefnotas.setSelected(false) ;}else{AcDefnotas.setSelected(true) ;}
                if (rs.getInt(9)==0){AcFundomaneio.setSelected(false) ;}else{AcFundomaneio.setSelected(true) ;}
                if (rs.getInt(10)==0){AcNiveisminimos.setSelected(false) ;}else{AcNiveisminimos.setSelected(true) ;}
                if (rs.getInt(11)==0){AcEstadoGeral.setSelected(false) ;}else{AcEstadoGeral.setSelected(true) ;}
                if (rs.getInt(12)==0){AcSupervisor.setSelected(false) ;}else{AcSupervisor.setSelected(true) ;}
                if (rs.getInt(13)==0){AcConsultacofre.setSelected(false) ;}else{AcConsultacofre.setSelected(true) ;}
                if (rs.getInt(14)==0){AcReinicio.setSelected(false) ;}else{AcReinicio.setSelected(true) ;}

            }

            rs.close();
            st.close();

        }catch(SQLException ex){
        }

        try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select password from users where id = 1 ");

            while(rs.next()){

                superpass.setText(rs.getString(1));

            }

            rs.close();
            st.close();

        }catch(SQLException ex){
        }

        try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select password from users where id = 2 ");

            while(rs.next()){

                operpass.setText(rs.getString(1));

            }

            rs.close();
            st.close();

        }catch(SQLException ex){
        }

        Supervisor.show();
        ecraconfigV2.hide();
    }//GEN-LAST:event_but_supervisor1ActionPerformed

    public void carregamento(){
        
        
      
        atualizapbs();
        
        scs_spo.Refill();

        carregaconsulta = true;
        scs_spo.EnableNoteValidator();
        scs_spo.EnableCoinValidator();

        //   scs_spo.ResetSPO();
        spoSetup = scs_spo.GetSPOSetup();
        scsSetup = scs_spo.GetSCSSetup();
        for(BANKNOTE note : spoSetup.notes){

            String strNote = note.currency.toString();
            int strStored =  note.stored;

            switch (strNote){

                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                break;

                case "200.0 EUR" :
                if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);}
                break;
            }}

            double totalcaixa = 0;

            totalcarregado = 0;

            for(BANKNOTE note : spoSetup.notes){
                double total = 0;
                String strNote = note.currency.toString();
                int strStored =  note.stored;
                switch (strNote){

                    case "5.0 EUR" :
                    nnota5.setText(String.valueOf(strStored));
                    //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);
                    total = Math.round(strStored*5*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota5.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb5e.setValue(strStored);
                     Un5e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb5e.setString("");
                    }else{
                        pb5e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                    }

                    break;

                    case "10.0 EUR" :
                    nnota10.setText(String.valueOf(strStored));
                    //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);
                    total = Math.round(strStored*10*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota10.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb10e.setValue(strStored);
                    Un10e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb10e.setString("");
                    }else{
                        pb10e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                    }
                    break;

                    case "20.0 EUR" :
                    nnota20.setText(String.valueOf(strStored));
                    //  scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);
                    total = Math.round(strStored*20*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota20.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb20e.setValue(strStored);
                     Un20e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb20e.setString("");
                    }else{
                        pb20e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                    }
                    break;

                    case "50.0 EUR" :
                    nnota50.setText(String.valueOf(strStored));
                    total = Math.round(strStored*50*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota50.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb50e.setValue(strStored);
                     Un50e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb50e.setString("");
                    }else{
                        pb50e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    }
                    break;

                    case "100.0 EUR" :
                    nnota100.setText(String.valueOf(strStored));
                    total = Math.round(strStored*100*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb100e.setValue(strStored);
                    Un100e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb100e.setString("");
                    }else{
                        pb100e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    }
                    break;

                    case "200.0 EUR" :
                    nnota200.setText(String.valueOf(strStored));
                    total = Math.round(strStored*200*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb200e.setValue(strStored);
                    Un200e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb200e.setString("");
                    }else{
                        pb200e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    }
                    break;

                    case "500.0 EUR" :
                    nnota500.setText(String.valueOf(strStored));
                    total = Math.round(strStored*500*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota500.setText(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    break;
                }

            }

            for(COIN coin : scsSetup.coins){
                double total = 0;
                String strcoin = coin.currency.toString();
                int strStored =  coin.stored;
                switch (strcoin){

                    case "0.01 EUR" :
                    nmoeda001.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.01*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda001.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb1c.setValue(strStored);
                    Un1c.setText(String.valueOf(strStored));
                    if(strStored==0){
                        pb1c.setString("");
                    }else{
                        pb1c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.02 EUR" :
                    nmoeda002.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.02*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda002.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb2c.setValue(strStored);
                    Un2c.setText(String.valueOf(strStored));
                   
                    System.out.println(strStored);
                    if(strStored==0){
                        pb2c.setString("");
                    }else{
                        pb2c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.05 EUR" :
                    nmoeda005.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.05*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda005.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb5c.setValue(strStored);
                     Un5c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb5c.setString("");
                    }else{
                        pb5c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    } break;

                    case "0.1 EUR" :
                    nmoeda010.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.10*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda010.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb10c.setValue(strStored);
                     Un10c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb10c.setString("");
                    }else{
                        pb10c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.2 EUR" :
                    nmoeda020.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.20*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda020.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb20c.setValue(strStored);
                     Un20c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb20c.setString("");
                    }else{
                        pb20c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.5 EUR" :
                    nmoeda050.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.50*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda050.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb50c.setValue(strStored);
                     Un50c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb50c.setString("");
                    }else{
                        pb50c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "1.0 EUR" :
                    nmoeda100.setText(String.valueOf(strStored));
                    total = Math.round(strStored*1*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb1e.setValue(strStored);
                     Un1e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb1e.setString("");
                    }else{
                        pb1e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "2.0 EUR" :
                    nmoeda200.setText(String.valueOf(strStored));
                    total = Math.round(strStored*2*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb2e.setValue(strStored);
                     Un2e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb2e.setString("");
                    }else{
                        pb2e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                }

                totalcaixa = Math.round(totalcaixa*100);
                totalcaixa = totalcaixa /100;

                ntotalmoedasnotas.setText(String.valueOf(totalcaixa)+" EUR");
                totalcarregadolb.setText("0 EUR");
                totcarregav2.setText("0 EUR");
                totatual.setText(String.valueOf(totalcaixa)+" EUR");

                ecraPagamentos.hide();
                PasswordPrompt.hide();
                ecraconfig_old.hide();
                Pagamentos.hide();
                scs_spo_consultas.hide();
                //    Carregamentos.show();
                Supervisor.hide();
                SplashScreen.hide();
                ecratransacoes.hide();
                cofre_removido.hide();

                ecraconfigV2.hide();
                CarregaV2.show();
                ConsultaV2.hide();

            }
        
    }
    
    private void but_carregamentos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_carregamentos1ActionPerformed
       carregamento();
    }//GEN-LAST:event_but_carregamentos1ActionPerformed

    private void but_pagamentos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_pagamentos1ActionPerformed

        Pagamentos.show();

        ecraconfigV2.hide();
    }//GEN-LAST:event_but_pagamentos1ActionPerformed

    private void but_float4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_float4ActionPerformed
        try{
            Statement stt;
            ResultSet rss;
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");
            stt = conn.createStatement();
            rss = stt.executeQuery("select sum(valor_doc) from  ith_periodo");

            while(rss.next()){
                double totalf = rss.getDouble(1);
                totalf = totalf *100;
                totalf = Math.round(totalf);
                totalf = totalf /100;
                valorperiodo.setText(String.valueOf(totalf)+ " EUR");
            }
            rss.close();
            stt.close();
        }catch(SQLException ex){
        }
        try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  cofre");

            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalcofrefecho.setText(String.valueOf(totalf2)+ " EUR");

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }

        atualizacontadores();

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select totgeral from  EstadoAtual WHERE id=1");//where id in (1,2,3); ");

            while(rss2.next()){
                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;

                totalmaquinafecho.setText(String.valueOf(totalf2)+" EUR");
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select valorfmaneiototal from  fmaneio WHERE id=(SELECT MAX(id) FROM fmaneio)";

            rss2 = stt2.executeQuery(queryy);

            while(rss2.next()){

                fundomaneiodef.setText((rss2.getString(1)));

            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){

            System.out.println(ex);
        }

        RetirarFundos.show();
        ecraconfigV2.hide();
    }//GEN-LAST:event_but_float4ActionPerformed

    private void retroceder_pass10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass10MouseClicked
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
    }//GEN-LAST:event_retroceder_pass10MouseClicked

    private void retroceder_pass13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass13MouseClicked
           
   scs_spo.ResetSPO();
            spoSetup = scs_spo.GetSPOSetup();
            scsSetup = scs_spo.GetSCSSetup();
            for(BANKNOTE note : spoSetup.notes){
                 
                 String strNote = note.currency.toString();
                 int strStored =  note.stored;
                 
                 switch (strNote){

                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                 if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                 if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                 if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                break;

                case "200.0 EUR" :
                 if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);}
                break;
                 }}
        
        
        descritivopagamento = "";
        pagamentovalor.setText("");
       
        descritivo.setText("");
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.show();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.hide();
        ConsultaV2.hide();
        CarregaV2.hide();
       jButton14.setVisible(true);
       
       
    }//GEN-LAST:event_retroceder_pass13MouseClicked

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
      Channels.show();
      ecraconfigV2.hide();
      
    }//GEN-LAST:event_jLabel99MouseClicked

    private void Ch5eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch5eurMouseClicked
      
        
        
      
      
    }//GEN-LAST:event_Ch5eurMouseClicked

    private void Ch10eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch10eurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Ch10eurMouseClicked

    private void Ch20eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch20eurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Ch20eurMouseClicked

    private void Ch50eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch50eurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Ch50eurMouseClicked

    private void Ch100eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch100eurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Ch100eurMouseClicked

    private void Ch500eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch500eurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Ch500eurMouseClicked

    private void Ch200eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ch200eurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Ch200eurMouseClicked

    private void retroceder_pass14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass14MouseClicked

        
        if(Ch5eur.isSelected()){
            System.out.println("sed");
            ch5e=1;
            
        }else{
        ch5e=0;
        System.out.println("seddwfwdfv");
        }
         if(Ch10eur.isSelected()){
            ch10e=1;
            
        }else{ch10e=0;
        
        } 
         if(Ch20eur.isSelected()){
            ch20e=1;
            
        }else{ch20e=0;
        
        } if(Ch50eur.isSelected()){
            ch50e=1;
            
        }else{ch50e=0;
        
        } if(Ch100eur.isSelected()){
            ch100e=1;
            
        }else{ch100e=0;
        
        } if(Ch200eur.isSelected()){
            ch200e=1;
            
        }else{ch200e=0;
        
        }
        if(Ch500eur.isSelected()){
            ch500e=1;
            
        }else{ch500e=0;
        
        }
        
        System.out.println(ch5e);
        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");
            String query = " UPDATE Channels_config SET Ch5e=?,Ch10e=?,Ch20e=?,Ch50e=?,Ch100e=?,Ch200e=?,Ch500e=? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, ch5e);
            preparedStmt.setInt (2, ch10e);
            preparedStmt.setInt   (3, ch20e);
            preparedStmt.setInt(4, ch50e);
            preparedStmt.setInt    (5, ch100e);
            preparedStmt.setInt (6, ch200e);
            preparedStmt.setInt (7, ch500e);
          
            preparedStmt.execute();
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }   
               
       configchannels();
       Channels.hide();
       ecraconfigV2.show();
       
       
    }//GEN-LAST:event_retroceder_pass14MouseClicked

    public void configchannels(){
        
         try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select * from  Channels_config;");

          
            while(rss2.next()){

                ch5e = rss2.getInt(3);
                ch10e = rss2.getInt(4);
                ch20e = rss2.getInt(5);
                ch50e = rss2.getInt(6);
                ch100e = rss2.getInt(7);
                ch200e = rss2.getInt(8);

            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
            
        }
         
         
       
          for(BANKNOTE note : spoSetup.notes){
                 String strNote = note.currency.toString();
                 int strStored =  note.stored;
                 switch (strNote){
                case "5.0 EUR" :
                if(ch5e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(0).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(0).currency);
                }  
                break;

                case "10.0 EUR" :
                  if(ch10e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(1).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(1).currency);
                }  
                 break;

                case "20.0 EUR" :
                 if(ch20e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(2).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(2).currency);
                }  
                break;

                case "50.0 EUR" :
                 if(ch50e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(3).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(3).currency);
                }  
                break;

                case "100.0 EUR" :
                  if(ch100e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(4).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(4).currency);
                }  
                 break;

                case "200.0 EUR" :
                  if(ch200e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(5).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(5).currency);
                }  
                 break;
                
                case "500.0 EUR" :
                  if(ch500e==1){
                scs_spo.UnInhibitBanknote(spoSetup.notes.get(6).currency);
                }else{
                scs_spo.InhibitBanknote(spoSetup.notes.get(6).currency);
                }  
                 break;
                 }}
         
         
        
        
    }
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       scs_spo.stir();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void retroceder_pass6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass6MouseClicked

        spodenom5e = Integer.parseInt(spo5e.getText());
        spodenom10e = Integer.parseInt(spo10e.getText());
        spodenom20e = Integer.parseInt(spo20e.getText());
        spodenom50e = Integer.parseInt(spo50e.getText());
        spodenom100e = Integer.parseInt(spo100e.getText());
        spodenom200e = Integer.parseInt(spo200e.getText());

        int totalpayout = spodenom5e+spodenom10e+spodenom20e+spodenom50e+spodenom100e+spodenom200e;
        if (totalpayout > 80){ AutoDismiss.showOptionDialog(rootPane, "A soma das denominações não pode ser superior a 80 unidades!! Redefina pf", "ATENÇÃO" ,5000);return;}

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");
            String query = " UPDATE SPOlimits SET denom5=?,denom10=?,denom20=?,denom50=?,denom100=?,denom200=? WHERE id=1";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, spodenom5e);
            preparedStmt.setInt (2, spodenom10e);
            preparedStmt.setInt   (3, spodenom20e);
            preparedStmt.setInt(4, spodenom50e);
            preparedStmt.setInt    (5, spodenom100e);
            preparedStmt.setInt (6, spodenom200e);
            preparedStmt.execute();
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        scs_spo.ResetSPO();
        spoSetup = scs_spo.GetSPOSetup();
        scsSetup = scs_spo.GetSCSSetup();
        for(BANKNOTE note : spoSetup.notes){

            String strNote = note.currency.toString();
            int strStored =  note.stored;

            switch (strNote){

                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                break;

                case "200.0 EUR" :
                if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);;}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);System.out.println("cofre200");}
                break;
            }}

            configgeral.hide();
            ecraconfigV2.show();

    }//GEN-LAST:event_retroceder_pass6MouseClicked

    private void spo5eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spo5eMouseClicked
        spo5e.setText("");

        keyboard = new PopUpKeyboard(spo5e);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_spo5eMouseClicked

    private void spo20eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spo20eMouseClicked
        spo20e.setText("");

        keyboard = new PopUpKeyboard(spo20e);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_spo20eMouseClicked

    private void spo10eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spo10eMouseClicked
        spo10e.setText("");

        keyboard = new PopUpKeyboard(spo10e);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_spo10eMouseClicked

    private void spo100eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spo100eMouseClicked
        spo100e.setText("");

        keyboard = new PopUpKeyboard(spo100e);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_spo100eMouseClicked

    private void spo200eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spo200eMouseClicked
        spo200e.setText("");

        keyboard = new PopUpKeyboard(spo200e);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_spo200eMouseClicked

    private void spo50eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spo50eMouseClicked
        spo50e.setText("");

        keyboard = new PopUpKeyboard(spo50e);
        keyboard.setDefaultCloseOperation(keyboard.DO_NOTHING_ON_CLOSE);
        keyboard.setVisible(true);

    }//GEN-LAST:event_spo50eMouseClicked

    private void but_fmaneiodef2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_fmaneiodef2ActionPerformed

        getchannels();
        
        if (ch5e==1) {
            Ch5eur.setSelected(true);
        }else{
            Ch5eur.setSelected(false);
        }
        
        if (ch10e==1) {
            Ch10eur.setSelected(true);
        }else{
            Ch10eur.setSelected(false);
        }
        if (ch20e==1) {
            Ch20eur.setSelected(true);
        }else{
            Ch20eur.setSelected(false);
        }
        if (ch50e==1) {
            Ch50eur.setSelected(true);
        }else{
            Ch50eur.setSelected(false);
        }
        if (ch100e==1) {
            Ch100eur.setSelected(true);
        }else{
            Ch100eur.setSelected(false);
        }
        if (ch200e==1) {
            Ch200eur.setSelected(true);
        }else{
            Ch200eur.setSelected(false);
        }
        if (ch500e==1) {
            Ch500eur.setSelected(true);
        }else{
            Ch500eur.setSelected(false);
        }
        
        
        ecraconfigV2.hide();
        Channels.show();
        
        
        
    }//GEN-LAST:event_but_fmaneiodef2ActionPerformed

    private void retroceder_pass15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retroceder_pass15MouseClicked
       
       atualizacontadores();
       
       
       Denomination.hide();
       ConsultaV2.show();
       
       
    }//GEN-LAST:event_retroceder_pass15MouseClicked

    private void b20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b20MouseClicked

    private void b20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b20ActionPerformed
       
        txtdenom.setText(txtdenom.getText()+"1");
    }//GEN-LAST:event_b20ActionPerformed

    private void b21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b21MouseClicked

    private void b22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b22MouseClicked
       
      
        
        
        
    }//GEN-LAST:event_b22MouseClicked

    private void b22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b22ActionPerformed
       txtdenom.setText(txtdenom.getText()+"3");
    }//GEN-LAST:event_b22ActionPerformed

    private void b23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b23MouseClicked

    private void b24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b24MouseClicked

    private void b25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b25MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b25MouseClicked

    private void b26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b26MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b26MouseClicked

    private void b27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b27MouseClicked

    private void b27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b27ActionPerformed
       txtdenom.setText(txtdenom.getText()+"8");
    }//GEN-LAST:event_b27ActionPerformed

    private void b28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b28MouseClicked

    private void b29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b29MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b29MouseClicked

    private void bc3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bc3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bc3MouseClicked

    private void b21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b21ActionPerformed
       txtdenom.setText(txtdenom.getText()+"2");
    }//GEN-LAST:event_b21ActionPerformed

    private void b25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b25ActionPerformed
       txtdenom.setText(txtdenom.getText()+"4");
    }//GEN-LAST:event_b25ActionPerformed

    private void b24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b24ActionPerformed
       txtdenom.setText(txtdenom.getText()+"5");
    }//GEN-LAST:event_b24ActionPerformed

    private void b23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b23ActionPerformed
       txtdenom.setText(txtdenom.getText()+"6");
    }//GEN-LAST:event_b23ActionPerformed

    private void b26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b26ActionPerformed
        txtdenom.setText(txtdenom.getText()+"7");
    }//GEN-LAST:event_b26ActionPerformed

    private void b28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b28ActionPerformed
      txtdenom.setText(txtdenom.getText()+"9");
    }//GEN-LAST:event_b28ActionPerformed

    private void b29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b29ActionPerformed
       txtdenom.setText(txtdenom.getText()+"0");
    }//GEN-LAST:event_b29ActionPerformed

    private void bc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc3ActionPerformed
       txtdenom.setText("");
    }//GEN-LAST:event_bc3ActionPerformed
boolean redraw = false;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        
        if (Type == 0){
            
            scs_spo.pagardenomM(Denom,Double.valueOf(txtdenom.getText()));
            redraw = true;
            
            
        }else{
            
            scs_spo.pagardenomN(Denom,Double.valueOf(txtdenom.getText()));
            redraw = true;
            
            
        }
        
      
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cpb1cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb1cMouseClicked
       
 Denom = 0.01;       
 Type = 0;
 DenomT="1 CENT";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d1c);
  txtdenom.setText("");
 ConsultaV2.hide();
 Denomination.show();
  
        
        
    }//GEN-LAST:event_cpb1cMouseClicked

    private void cpb2cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb2cMouseClicked
      Denom = 0.02;       
 Type = 0;
 DenomT="2 CENT";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d2c);
  txtdenom.setText("");
 ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb2cMouseClicked

    private void cpb5cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb5cMouseClicked
       Denom = 0.05;       
 Type = 0;
 DenomT="5 CENT";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d5c);
  txtdenom.setText("");
 ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb5cMouseClicked

    private void cpb10cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb10cMouseClicked
       Denom = 0.1;       
 Type = 0;
 DenomT="10 CENT";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d10c);
  txtdenom.setText("");       
      ConsultaV2.hide();
 Denomination.show(); 
    }//GEN-LAST:event_cpb10cMouseClicked

    private void cpb20cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb20cMouseClicked
       Denom = 0.2;       
 Type = 0;
 DenomT="20 CENT";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d20c);
  txtdenom.setText("");
 ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb20cMouseClicked

    private void cpb50cMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb50cMouseClicked
       Denom = 0.5;       
 Type = 0;
 DenomT="50 CENT";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d50c);
        ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb50cMouseClicked

    private void cpb1eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb1eMouseClicked
      Denom = 1;       
 Type = 0;
 DenomT="1 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d1e);
    txtdenom.setText("");     
      ConsultaV2.hide();
 Denomination.show();
    }//GEN-LAST:event_cpb1eMouseClicked

    private void cpb2eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb2eMouseClicked
           Denom = 2;       
 Type = 0;
 DenomT="2 EUR";
 txtdenom.setText("");
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d2e);
        ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb2eMouseClicked

    private void cpb5eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb5eMouseClicked
           Denom = 5;       
 Type = 1;
 DenomT="5 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d5e);
    txtdenom.setText("");     
     ConsultaV2.hide();
 Denomination.show(); 
    }//GEN-LAST:event_cpb5eMouseClicked

    private void cpb10eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb10eMouseClicked
                 Denom = 10;       
 Type = 1;
 DenomT="10 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d10e);
  txtdenom.setText(""); 
 ConsultaV2.hide();
 Denomination.show(); 
      
    }//GEN-LAST:event_cpb10eMouseClicked

    private void cpb20eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb20eMouseClicked
                 Denom = 20;       
 Type = 1;
 DenomT="20 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d20e);
      txtdenom.setText("");   
      ConsultaV2.hide();
 Denomination.show();
    }//GEN-LAST:event_cpb20eMouseClicked

    private void cpb50eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb50eMouseClicked
                 Denom = 50;       
 Type = 1;
 DenomT="50 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d50e);
  txtdenom.setText(""); 
 ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb50eMouseClicked

    private void cpb100eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb100eMouseClicked
                 Denom = 100;       
 Type = 1;
 DenomT="100 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d100e);
  txtdenom.setText("");  
 ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb100eMouseClicked

    private void cpb200eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpb200eMouseClicked
                 Denom = 200;       
 Type = 1;
 DenomT="200 EUR";
 txtdemonatuais.setText("Selecionou a denominação " + DenomT + " - Existencia Atual - "+d200e);
  txtdenom.setText("");  
 ConsultaV2.hide();
 Denomination.show();
      
    }//GEN-LAST:event_cpb200eMouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
      /* 
        
          try {
            String dbUrl="jdbc:sqlserver://192.168.1.251:1433;"
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
            
            
            
            String count = "SELECT * FROM artigos ";
        
            System.out.println(count);
            
             
            ResultSet count_ResultSet = count_Statement.executeQuery(count);
            
           
            while (count_ResultSet.next()){
            System.out.println(count_ResultSet.getString(2));
            
            String c1 =count_ResultSet.getString(1);
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
            
              int cc1 = Integer.parseInt(c1.replaceAll("[\\D]", ""));
              
            System.out.println("sudo php /var/www/html/art.php " + cc1+ "   '" + c2+ "' '" + c3+ "'  '" + c4+ "' '" + c5+ "' '" + c6+ "'");
            
           Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo php /var/www/html/art.php " + cc1+ "   " + c2+ " '" + c3+ "'  " + c4+ " " + c5+ " " + c6+ "");
            System.out.println(pr);
            
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } 
                    
            
            try {
                           Thread.sleep(10000);
                       }  catch (InterruptedException ex) {
                           Logger.getLogger(sqllistener.class.getName()).log(Level.SEVERE, null, ex);
                       }
                
                
                
             
            }

         

        }catch(SQLException ex){
        }
      
        
       */
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jLabel49MouseClicked

    private void moeda2imgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moeda2imgMouseClicked
          
            
             
    }//GEN-LAST:event_moeda2imgMouseClicked
 
    
    
 double Denom = 0;
 int Type =0;
 String DenomT;
    
    
 int ch5e=0;
 int ch10e=0;
 int ch20e=0;
 int ch50e=0;
 int ch100e=0;
 int ch200e=0;
 int ch500e=0;
 
    public void getchannels(){
        
         try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select * from  Channels_config;");

          
            while(rss2.next()){

                ch5e = rss2.getInt(3);
                ch10e = rss2.getInt(4);
                ch20e = rss2.getInt(5);
                ch50e = rss2.getInt(6);
                ch100e = rss2.getInt(7);
                ch200e = rss2.getInt(8);
                ch500e=rss2.getInt(9);
            }
            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            System.out.println(ex);
            
        }
        
        
    }
    
    public CategoryDataset  createDataset(ArrayList <VendasHits> ListaDePessoas){
    
    DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
    
    ListaDePessoas.forEach((VendasHits vendashits) -> {
        dataSet.addValue(vendashits.getVendas(),"Numero de Faturas",vendashits.getHora());
    })  ; 
    return dataSet;
}
  public JFreeChart cretateChartBar(CategoryDataset dataSet){
        
        JFreeChart graficoBarras = ChartFactory.createBarChart("Fluxo de Movimentos por Horário vs Numero Vendas",
                "","", 
                dataSet, 
                PlotOrientation.VERTICAL,true,true,true);
        return graficoBarras;
    }
    
    public ChartPanel CriarGrafico(ArrayList<VendasHits> ListaDePessoas){
        CategoryDataset dataSet  = this.createDataset(ListaDePessoas);
        
        JFreeChart grafico = this.cretateChartBar(dataSet);
        ChartPanel paineldografico  = new ChartPanel(grafico);
        return paineldografico;
        
    }
     public CategoryDataset  createDataset2(ArrayList <VendasHits> ListaDePessoas2){
    
    DefaultCategoryDataset dataSet2 = new DefaultCategoryDataset();
    
    ListaDePessoas2.forEach((VendasHits vendashits) -> {
        dataSet2.addValue(vendashits.getVendas(),"Volume faturação em  €",vendashits.getHora());
    })  ; 
    return dataSet2;
}
  public JFreeChart cretateChartBar2(CategoryDataset dataSet2){
        
        JFreeChart graficoBarras2 = ChartFactory.createBarChart("Fluxo de Movimentos por Horário vs Volume Faturado",
                "","", 
                dataSet2, 
                PlotOrientation.VERTICAL,true,true,true);
        return graficoBarras2;
    }
    
    public ChartPanel CriarGrafico2(ArrayList<VendasHits> ListaDePessoas2){
        CategoryDataset dataSet2  = this.createDataset2(ListaDePessoas2);
        
        JFreeChart grafico2 = this.cretateChartBar2(dataSet2);
        ChartPanel paineldografico2  = new ChartPanel(grafico2);
        return paineldografico2;
        
    }
     public CategoryDataset  createDataset3(ArrayList <VendasHits> ListaDePessoas3){
     DefaultCategoryDataset dataSet3 = new DefaultCategoryDataset();
    ListaDePessoas3.forEach((VendasHits vendashits) -> {
     dataSet3.addValue(vendashits.getVendas(),"Volume faturação em  €",vendashits.getHora());
    })  ; 
    return dataSet3;
}
  
  public JFreeChart cretateChartBar3(CategoryDataset dataSet3){
        
        JFreeChart graficoBarras3 = ChartFactory.createBarChart("Fluxo de Movimentos por Horário Periodo Atual",
                "","", 
                dataSet3, 
                PlotOrientation.VERTICAL,true,true,true);
        return graficoBarras3;
    }
    
    public ChartPanel CriarGrafico3(ArrayList<VendasHits> ListaDePessoas3){
        CategoryDataset dataSet3  = this.createDataset2(ListaDePessoas3);
        
        JFreeChart grafico3 = this.cretateChartBar3(dataSet3);
        ChartPanel paineldografico3  = new ChartPanel(grafico3);
        return paineldografico3;
        
    }
    
    public String senhacofre = "";
public int tec2 = 0;
   
    public void carrega (){
        
    double totalcaixa = 0;
    
  //  scs_spo.ResetSPO();
            spoSetup = scs_spo.GetSPOSetup();
            scsSetup = scs_spo.GetSCSSetup();
            for(BANKNOTE note : spoSetup.notes){
                 
                 String strNote = note.currency.toString();
                 int strStored =  note.stored;
                 
                 switch (strNote){

                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                 if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                 if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                 if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                
                 break;

                case "200.0 EUR" :
                 if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);}
                break;
                 }}
       
          for(BANKNOTE note : spoSetup.notes){
                double total = 0;
                String strNote = note.currency.toString();
                int strStored =  note.stored;
                switch (strNote){

                    case "5.0 EUR" :
                    nnota5.setText(String.valueOf(strStored));
                    //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);
                    total = Math.round(strStored*5*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota5.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb5e.setValue(strStored);
                     Un5e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb5e.setString("");
                    }else{
                        pb5e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                    }

                    break;

                    case "10.0 EUR" :
                    nnota10.setText(String.valueOf(strStored));
                    //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);
                    total = Math.round(strStored*10*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota10.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb10e.setValue(strStored);
                    Un10e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb10e.setString("");
                    }else{
                        pb10e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                    }
                    break;

                    case "20.0 EUR" :
                    nnota20.setText(String.valueOf(strStored));
                    //  scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);
                    total = Math.round(strStored*20*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota20.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb20e.setValue(strStored);
                     Un20e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb20e.setString("");
                    }else{
                        pb20e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                    }
                    break;

                    case "50.0 EUR" :
                    nnota50.setText(String.valueOf(strStored));
                    total = Math.round(strStored*50*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota50.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb50e.setValue(strStored);
                     Un50e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb50e.setString("");
                    }else{
                        pb50e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    }
                    break;

                    case "100.0 EUR" :
                    nnota100.setText(String.valueOf(strStored));
                    total = Math.round(strStored*100*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb100e.setValue(strStored);
                    Un100e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb100e.setString("");
                    }else{
                        pb100e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    }
                    break;

                    case "200.0 EUR" :
                    nnota200.setText(String.valueOf(strStored));
                    total = Math.round(strStored*200*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb200e.setValue(strStored);
                    Un200e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb200e.setString("");
                    }else{
                        pb200e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    }
                    break;

                    case "500.0 EUR" :
                    nnota500.setText(String.valueOf(strStored));
                    total = Math.round(strStored*500*100);
                    total = total /100;
                    totalcaixa += total;
                    nnota500.setText(" "+String.valueOf(strStored)+" | "+total+" EUR");
                    break;
                }

            }

            for(COIN coin : scsSetup.coins){
                double total = 0;
                String strcoin = coin.currency.toString();
                int strStored =  coin.stored;
                switch (strcoin){

                    case "0.01 EUR" :
                    nmoeda001.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.01*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda001.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb1c.setValue(strStored);
                    Un1c.setText(String.valueOf(strStored));
                    if(strStored==0){
                        pb1c.setString("");
                    }else{
                        pb1c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.02 EUR" :
                    nmoeda002.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.02*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda002.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb2c.setValue(strStored);
                    Un2c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb2c.setString("");
                    }else{
                        pb2c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.05 EUR" :
                    nmoeda005.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.05*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda005.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb5c.setValue(strStored);
                     Un5c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb5c.setString("");
                    }else{
                        pb5c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    } break;

                    case "0.1 EUR" :
                    nmoeda010.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.10*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda010.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb10c.setValue(strStored);
                     Un10c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb10c.setString("");
                    }else{
                        pb10c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.2 EUR" :
                    nmoeda020.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.20*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda020.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb20c.setValue(strStored);
                     Un20c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb20c.setString("");
                    }else{
                        pb20c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "0.5 EUR" :
                    nmoeda050.setText(String.valueOf(strStored));
                    total = Math.round(strStored*0.50*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda050.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb50c.setValue(strStored);
                     Un50c.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb50c.setString("");
                    }else{
                        pb50c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "1.0 EUR" :
                    nmoeda100.setText(String.valueOf(strStored));
                    total = Math.round(strStored*1*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb1e.setValue(strStored);
                     Un1e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb1e.setString("");
                    }else{
                        pb1e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

                    case "2.0 EUR" :
                    nmoeda200.setText(String.valueOf(strStored));
                    total = Math.round(strStored*2*100);
                    total = total /100;
                    totalcaixa += total;
                    nmoeda200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                    pb2e.setValue(strStored);
                     Un2e.setText(String.valueOf(strStored));
                   
                    if(strStored==0){
                        pb2e.setString("");
                    }else{
                        pb2e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                    }
                    break;

            }
        }
            totalcaixa = Math.round(totalcaixa*100);
            totalcaixa = totalcaixa /100;
            ntotalmoedasnotas.setText(String.valueOf(totalcaixa)+" EUR");  
            totalcarregado = Math.round(totalcarregado*100);
            totalcarregado = totalcarregado /100;
            totalcarregadolb.setText(String.valueOf(totalcarregado)+" EUR");
            totatual.setText(String.valueOf(totalcaixa)+" EUR");  
            totcarregav2.setText(String.valueOf(totalcarregado)+" EUR");
           
     }      
         public void carregapago(CURRENCY currency){   
           valortotal = currency.value;
           totalcarregado +=  valortotal;
           FT = "Carregamento";
           ammount = 000;
           troco = 000;
           gravapagamento();   
         }
    public double valorpago = 0 ;
    public double valorP = 0;
    public double troco = 0;
    public double ammount = 0;
    public boolean ocupado = false;
    public double valortotal = 0;
    public String s;
    public int pago_finalizado = 0;
    public int insert_conc = 0;
    public double troco_disp = 0;
   
    String Estado="";
    
    //CURRENCY currency;
    CURRENCY currency = new CURRENCY();
    public int stored = 0;
    
    public void gravapagamentouser(){
        
        try{
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
             
      java.util.Date utilDate = new java.util.Date();
      java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
      String query = " INSERT into CarregamentosUser (valor_carregado,user) values (?,?)";
      
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble (1, carregauser);
      preparedStmt.setString (1, user);
      
      
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }     
        
        
    }
    
    
    public void gravapagamento(){
        
        
        if("Carregamento".equals(FT)){
            
           try{
          
          log("a gravar doc");
         
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
             
      java.util.Date utilDate = new java.util.Date();
      java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
      String query = " INSERT into Carregamentos (valor_carregado) values (?)";
      
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble (1, valortotal);
      
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }     
            
            
        }else if (Estado.startsWith("Pagamento cancelado")){
            System.out.println("wdvfqwvlqefjvnblkefhbvçqklm");
            try{
                
             Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
             
      java.util.Date utilDate = new java.util.Date();
      java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
      String query = " insert into  ith_periodo  (Data,documento,valor_doc,pago_finalizado,insert_conc) values (?,?,?,?,?,?)";
      
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setTimestamp (1, sqlTimeStamp);
      preparedStmt.setString (2, FT + " - > " + Estado);
      preparedStmt.setDouble   (3, ammount);
      preparedStmt.setInt(4, 1);
      preparedStmt.setInt    (5, 1);
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }   
            
            
            
            
        }else{
        
        
        
        
         try{
          
          log("a gravar doc");
         
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
             
      java.util.Date utilDate = new java.util.Date();
      java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
      String query = " insert into  ith_periodo  (Data,documento,valor_doc,pago_com,valor_dispensado,user) values (?,?,?,?,?,?)";
      
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setTimestamp (1, sqlTimeStamp);
      preparedStmt.setString (2, FT);
      preparedStmt.setDouble   (3, ammount);
      preparedStmt.setDouble(4, valortotal);
      preparedStmt.setDouble    (5, troco);
      preparedStmt.setString (6, "Operador");
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }   
    }
    }
     public void gravapagamento2(){
        
         try{
          
         log("a gravar doc");
         
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
             
      java.util.Date utilDate = new java.util.Date();
      java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
      String query = " insert into mt (valor)"
        + " values (?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble   (1, ammount);
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }   
    }
    
    public void enviamail() throws FileNotFoundException {
        
        try {
            createdoc2();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sendemail2();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
     public void createdoc2() throws FileNotFoundException{
        
      try {
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(FILE2));
            document.open();
            addMetaData2(document);
            addTitlePage2(document);
          try {
              addContent2(document);
          } catch (BadElementException ex) {
              Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ParseException ex) {
              Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
          }
            document.close();
        } catch (DocumentException e) {
            
        }     
  }
  private static void addMetaData2(Document document) {
        document.addTitle("Relatório Diário iThink");
        document.addSubject("daily report");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Fernando Marinho | iThink");
        document.addCreator("Fernando Marinho | iThink ");
    }
  
  public  void addTitlePage2(Document document)
            throws DocumentException {
      
      double totalsafe = 0;
      double totalcharges= 0;
      double totalpays = 0;
      
      
       SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
          Date date = new Date();
          String horadff = time.format(date);
         StringBuffer str = new StringBuffer(Dataoffline);
        str.insert(4,'-');  // insert 'A' at the beginning "Ajava programing"
        str.insert(7,'-');  // insert at the 6th position  "Ajava Aprograming"
       
      try{
            Statement stt2;
            ResultSet rss2;
            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  mt WHERE DATE(reg_date) = '" + str + "'");
    
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;
                totalsafe=totalf2;
        
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
      
      try{
            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            rss2 = stt2.executeQuery("select sum(valor) from  mt");

      
            while(rss2.next()){

                double totalf2 = rss2.getDouble(1);

                totalf2 = totalf2 *100;

                totalf2 = Math.round(totalf2);
                totalf2 = totalf2 /100;
                totalcharges=totalf2;
             
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
        }
        
      Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine2(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Relatório Diário Manual Transactions iThink", catFont));

        addEmptyLine2(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Relatório gerado automáticamente por iThink,  "  + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine2(preface, 1);
        preface.add(new Paragraph(
                "Resumo de Valores ",
                greenFont));
        addEmptyLine2(preface, 1); 
        preface.add(new Paragraph(
                "Valor Total Dia - " + totalsafe + " EUR",
                smallBold));
        addEmptyLine2(preface, 1); 
        preface.add(new Paragraph(
                "Valor Total Geral  - " + totalcharges + " EUR",
                smallBold));
        addEmptyLine2(preface, 1);
        preface.add(new Paragraph(
                "Resumo de Transações",
                smallBold));
        addEmptyLine2(preface, 1);
        
        document.add(preface);
        // Start a new page
       // document.newPage();
    }
  
  private static void addContent2(Document document) throws DocumentException, BadElementException, ParseException{
       
        try {
            createTable2(document);
        
        } catch (ParseException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  private static  void createTable2(Document document)
            throws BadElementException, DocumentException, ParseException {
       
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
      
        Date date = new SimpleDateFormat("yyyyMMdd").parse(Dataoffline);
               
        String ddate = date.toString();
                    
        StringBuffer str = new StringBuffer(Dataoffline);
        str.insert(4,'-');  // insert 'A' at the beginning "Ajava programing"
        str.insert(7,'-');  // insert at the 6th position  "Ajava Aprograming"
                
       DefaultTableModel model = new DefaultTableModel(new String[]{"Data", "valor"}, 0);
       try{
       Statement st;
            ResultSet rs;
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
            st = con.createStatement();
            rs = st.executeQuery("select * from mt WHERE DATE(reg_date) = '" + str + "'");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
            while(rs.next()){
                String data = dateFormat.format(rs.getTimestamp(3));
                String valordoc = String.valueOf(rs.getDouble(2))+" €";
                model.addRow(new Object[]{ data, valordoc }) ;
            }
            
            PdfPTable tab = new PdfPTable(model.getColumnCount());
        for (int i = 0; i < model.getColumnCount(); i++) {
            tab.addCell(model.getColumnName(i));
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                if (model.getValueAt(i, j) != null) {
                    tab.addCell(model.getValueAt(i, j).toString());
                } else {
                    tab.addCell("-");
                }
            }
        }
        document.add(tab);
       
            rs.close();
            st.close();
        }catch(SQLException ex){
        System.out.println(ex);
        }

    }

   

    private static void addEmptyLine2(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    
    
    public void sendemail2 () throws UnsupportedEncodingException{
        
        
    Properties props = new Properties();   
    /** Parâmetros de conexão com servidor Gmail */
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
      }
    );
 
    try {
 
      Message message = new MimeMessage(session); 
      message.setFrom(new InternetAddress("fernando@ithinkiot.pt", "iThink - IoT")); 
     
      Address[] toUser = InternetAddress //Destinatário(s)
                 .parse(Destinatarios);  
 
      message.setRecipients(Message
     .RecipientType.TO, toUser);
      message.setSubject("iThink Machine  -> " + MachineName);
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText("Reporte diário iThink! -> " + MachineName);
      Multipart multipart = new MimeMultipart();

         multipart.addBodyPart(messageBodyPart);
         messageBodyPart = new MimeBodyPart();
         String filename = FILE2;
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         
         multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart);
         Transport.send(message);
    
     } catch (MessagingException e) {
         
        throw new RuntimeException(e);
    }
    }
    
    
    
    
    
    
    
    public void gravapagamentocofre (CURRENCY currency){
        
         try{
        valorpago=currency.value;
         log("Grava levantamento cofre");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                     + "user=root&password=Pa$$w0rd")) {
                 java.util.Date utilDate = new java.util.Date();
                 java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
                 String query2 = " insert into cofre (valor,data)"
                         + " values (?, ?)";
                 PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                 preparedStmt2.setTimestamp (2, sqlTimeStamp);
                 preparedStmt2.setDouble (1, valorpago);
                  preparedStmt2.execute();
             }
      }catch(SQLException ex){
          errorlog("Erro a gravar para cofre - " + ex);
         }
    }
    double ss;
    double falpagar;
    
      public void pagamento( CURRENCY currency ){
        ammount = vpp;
        
        log("Valor Fatura" + vpp + " EUR");
        valorpago = currency.value;
        valortotal += valorpago;
        valortotal = valortotal*100;
        valortotal = Math.round(valortotal);
        valortotal = valortotal /100;
        log("Valor Introduzido" + valorpago + " EUR");
        ss = valortotal - ammount;
        ss = ss *100;
        ss = Math.round(ss);
        ss = Math.abs(ss);      
        ss = ss/100;       
        sss = String.format("%.2f", ss);
        faltapagarlabel.setText(sss + " EUR");
         ssss = String.format("%.2f", valortotal);
         pagolabel2.setText(ssss + " EUR" );
       Send_a.recebedadospos("valorintroduzido"," ",valortotal);
         try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }        
       Send_a.recebedadospos("faltapagar"," ",ss);
              
        
       
    
        if (ammount == valortotal){
            if ( FT == "Manual"){
                
        gravapagamento2();
       
           semtroco();
        return;    
            }
            
            else{
              
                gravapagamento();
                 semtroco();
        return; 
            }
         
        }
         if (ammount > valortotal){  
        }else{ 
        troco = valortotal - ammount;
        faltapagarlabel.setText("0 EUR");
        troco = troco*100;
        troco = Math.round(troco);
        troco = troco /100;
        currency = new CURRENCY();
        currency.value  = troco;
        currency.countryCode = "EUR";
        trocolabel.setText(troco + " EUR");
        quantia.setText("Retire o seu troco p.f.");
        troco2.setText(troco + " EUR");
        Send_a.recebedadospos("troco"," ",troco);
        faltapagarlabel.setText("0 EUR"); 
        try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }    
        
        Send_a.recebedadospos("faltapagar"," ",0);
         if ( FT == "Manual"){
                
        gravapagamento2();}else{
                gravapagamento();
                
            }
         //pagoativo=false;
        
        scs_spo.DisableNoteValidator();
        scs_spo.DisableCoinValidator(); 
         log("disable coin 4");
         trocofalha = true;
   
      }
    }
public boolean pagoativo = false;
public boolean trocofalha = false;

public void falhanotroco(){
    log("troco aut ok!!!!!!||||||");
    if(trocofalha){
        log("falhou o troco automatico!!!!!!!!!!!!!");
        troco();
        return;
    }
    
}
boolean deativate = false;


    public void troco()
            
    {
        if (troco==0){
            
            return;
        }
        
        deativate = false;
        trocofalha = false;        
        pin.high();
        troco = troco*100;
        troco = Math.round(troco);
        troco = troco /100;
        currency.value  = troco;
        currency.countryCode = "EUR";
        scs_spo.PayoutValue(currency);
        apagaft();
    }
    public boolean carregaconsulta = false;
    public double vpp;
    public String Doc;
    public boolean pagook = false;
    public boolean nochange = false;
    public static String Dataoffline = "";
    
    String sss = Double.toString(ss);
    String ssss = Double.toString(valortotal);
    
    
    
    @Override
    public void evento_server(eventos ev, String vppp, String doc){
        
        switch (ev){
            
            case ev_Novo_Pago_Server:
          
                
                FT = doc;
                
                
            int i = vppp.length();     
                
           
                
            
           //      if (i > 7){
                     
        //    try {
                
                
          //     Dataoffline = vppp;
               
                
                
        //        enviamail();
       //     } catch (FileNotFoundException ex) {
        //        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
       //     }
        //          return;
                  
        //         }
                 
                  vpp = Double.parseDouble(vppp);
                  System.out.println(vppp);
            
               
                
                if (!pagoativo & vpp != -9999 & vpp > 0){
                 
              
                 Send_a.recebedadospos("valorapagar",FT,vpp);
              //  Send_a.enviardados(vppp, FT, ss);
                 pagoativo = true;
                 deativate = true;
                 apagarlabel.setText(String.valueOf(vpp));
                 apagarlabel.setText(String.format("%.2f",vpp));
                 apagarlabel.setText(apagarlabel.getText() + " EUR");
                 ecraPagamentos.show();
                 PasswordPrompt.hide();
                 ecraconfigV2.hide();
                 Pagamentos.hide();
                 scs_spo_consultas.hide();
                 CarregaV2.hide();
                 Supervisor.hide();
                 SplashScreen.hide();
                 ecratransacoes.hide();
                 cofre_removido.hide();
                 scs_spo.EnableNoteValidator();
                 scs_spo.EnableCoinValidator();
                 System.out.println("1");
                 
                
                 
                 
                }else if (vpp == -9999){
                   
          
                    scs_spo.DisableNoteValidator();
                    scs_spo.DisableCoinValidator(); 
                    log("disable coin 2");
                     if ( FT == "Manual"){
                
                 gravapagamento2();}
                
                 else{
                
                 gravapagamento();
                
                 }
                    apagaft();
                    AutoDismiss.showOptionDialog(rootPane, "Pagamento Cancelado", "ATENÇÃO" ,3000);
                    limpa();
                }
                else {
                    
               /*     vpp=Math.abs(vpp);
                    System.out.println(vpp);
                    currency = new CURRENCY();
                 currency.value  = vpp;
                 currency.countryCode = "EUR";
                 scs_spo.PayoutValue(currency);  
                       }
                */
        
                }
                 break;
                 
    }
    }
    String user = "";
    double carregauser=0;
    
    @Override
      public void evento_pagamento_ocurrido(eventos ev, double vp, String doc)
    {
        switch (ev){
            
            case ev_Novo_Pago:
                
                
                if (!pagoativo & vp != -9999 & vp > 0 & !"RASPADINHA".equals(doc)){
                    
                
                 vpp = vp;
            //     enviardados("valorapagar",FT,vpp);
                 FT = doc;
                 pagoativo = true;
                 deativate = true;
                 apagarlabel.setText(String.valueOf(vp));
                 apagarlabel.setText(String.format("%.2f",vp));
                 apagarlabel.setText(apagarlabel.getText() + " EUR");
                 ecraPagamentos.show();
                 PasswordPrompt.hide();
                 ecraconfigV2.hide();
                 Pagamentos.hide();
                 scs_spo_consultas.hide();
                 CarregaV2.hide();
                 Supervisor.hide();
                 SplashScreen.hide();
                 ecratransacoes.hide();
                 cofre_removido.hide();
                 scs_spo.EnableNoteValidator();
                 scs_spo.EnableCoinValidator();
               }else if ("RASPADINHA".equals(doc)){
                    gravapagamento();
                    pagoativo = false;
                    System.out.println(vp);
                    Double vpremio = vp; 
                    
                    
                 currency = new CURRENCY();
                 currency.value  = vpremio;
                 currency.countryCode = "EUR";
                 scs_spo.PayoutValue(currency);  
                 System.out.println(vpremio);
                 
                  
                  limpa();
                }
                else if (vp == -9999){
                    apagaft();
                    pagoativo=false;
                    scs_spo.DisableNoteValidator();
                    scs_spo.DisableCoinValidator(); 
                    AutoDismiss.showOptionDialog(rootPane, "Pagamento Cancelado", "ATENÇÃO" ,5000);
                    
                   FT=FT+" - Pagamento cancelado pelo utilizador" + vpp + "EUR";
                 
                   gravapagamento();
           
                    limpa();
                }else if (vp== -9988){
                    
                    user=doc;
                    carregamento();
                    
                    
                    
                }else if (vp==-9987){
                    
                     
         scs_spo.Unrefill();
        
        carregauser=totalcarregado;
        troco = 0;
        valorpago=0;
        valorP = 0;
        valortotal = 0;
        ammount=0;
        carregaconsulta = false;
       
        scs_spo.DisableCoinValidator();
        scs_spo.DisableNoteValidator();
        atualizacontadores();
        
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfigV2.hide();
        Pagamentos.hide();
        CarregaV2.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
        
        gravapagamentouser();
        String resumo = "Carregamento Efetuado pelo Utilizador -"+user+" | Valor Carregado - " + carregauser +" €";
        enviamailniveis.recebemail(resumo);
        carregauser=0;
        user="";
        
        
                    
                    
                    
                    
                }else if ("WinR".equals(doc)){
                    
                     Connection conn2 = null;
              try {
                  conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                          + "user=root&password=Pa$$w0rd");
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
               String del = "delete  FROM WinR;";
              System.out.println("jhfjhfk");


              PreparedStatement preparedStmt2 = null;
        try {
            preparedStmt2 = conn2.prepareStatement(del);
        } catch (SQLException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
              
              try {
                  preparedStmt2.execute();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
             
              
              
                 try {
                  conn2.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
             
                    
                }
                

                 break;
                 
                 
                 
                 
                 
                 
                 
            case ev_Novo_Pago_Sql:
                
               
                if (!pagoativo & vp != -9999 & vp > 0){
                 vpp = vp;
                 FT = doc;
                 Send_a.recebedadospos("valorapagar",FT,vpp);
                 System.out.println("valorsql");
                 pagoativo = true;
                 deativate=true;
                 apagarlabel.setText(String.valueOf(vp));
                 apagarlabel.setText(String.format("%.2f",vp));
                 apagarlabel.setText(apagarlabel.getText() + " EUR");
                 ecraPagamentos.show();
                 PasswordPrompt.hide();
                 ecraconfigV2.hide();
                 Pagamentos.hide();
                 scs_spo_consultas.hide();
                 CarregaV2.hide();
                 Supervisor.hide();
                 SplashScreen.hide();
                 ecratransacoes.hide();
                 cofre_removido.hide();
          
                 scs_spo.EnableNoteValidator();
                 scs_spo.EnableCoinValidator();
               
                }else if (vp == -9999){
                    pagoativo=false;
                    apagaft();
                    scs_spo.DisableNoteValidator();
                    scs_spo.DisableCoinValidator(); 
                    AutoDismiss.showOptionDialog(rootPane, "Pagamento Cancelado", "ATENÇÃO" ,5000);
                    FT="Pagamento cancelado pelo utilizador" + vpp + "EUR";
                 
                gravapagamento();
                
                    limpa();
                }
                else {
                
                       }
                break;
                 
            case ev_android_request:
            break;                
            case ev_novahora:
            horas1.setText(doc);
            break;
        }
    }
   
    
    
    public void semtroco(){
       
    
   // pagoativo=false;
    deativate=false;
    troco=0;
    valorpago=0;
    pago_finalizado=0;
    valorP = 0;
    valortotal = 0;
    ammount=0;
    ocupado = false;
    ecraPagamentos.hide();
    quantia.setText("");
    troco2.setText("Sem troco");
    EndPayment.show();
    
    scs_spo.DisableNoteValidator();
    scs_spo.DisableCoinValidator();
    apagaft();
    log("disable coin 3");
    Runnable task = () -> limpa();   
    ScheduledFuture<?> future = executor.schedule(task, 5, TimeUnit.SECONDS);
    

      }
   
   
     public void apagaft(){
  /*
             try {
            String dbUrl="jdbc:sqlserver://"+IPHOST+":1433;"
                    + "databaseName=Disco";
            
            String SQLUSER="sa";
            String SQLPASS = "Pa$$w0rd";
            
                   
            
            Connection con = DriverManager.getConnection(dbUrl,SQLUSER,SQLPASS);
            
            
            Statement count_Statement = con.createStatement();
            
            
            
              String queryy = "INSERT INTO movimentos_gerais_piscina (data,ndoc,valor,cartao,artigo,funcionario,POS,Descricao,nlanc,periodo) SELECT movimentos_piscina.data,movimentos_piscina.ndoc,movimentos_piscina.valor,movimentos_piscina.cartao,movimentos_piscina.artigo,movimentos_piscina.funcionario,movimentos_piscina.POS,movimentos_piscina.Descricao,movimentos_piscina.nlanc,0  FROM movimentos where cartao = '" + cardno+ "'";
            
        
         
                 PreparedStatement preparedStmt2 = con.prepareStatement(queryy);
                  
                 
                 
                 
                 
                 
                 preparedStmt2.execute();
        
        }catch(SQLException ex){
        }


        if(apagamov(cardno)){
       
        
      //  Pagamento.hide();
      //  SplashScreen.show();
        niff.setText("");
       
        model.removeAllElements();
        updatepago(cardno);
        
        
      
      
       
        
     //  System.exit(0);
       }else{
           
      AutoDismiss.showOptionDialog(rootPane, "Erro ao Liquidar Cartão!!!", "ATENÇÃO" ,3000);
           
           
       }    
       
  */
       }
     
  
    public  void limpa(){
        deativate = false;
        valoramaisBollean = false;
       
        faltapagarlabel.setText("0 EUR");
        trocolabel.setText("0 EUR");
        apagarlabel.setText("0 EUR");
        pagolabel2.setText("0 EUR");
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
        pag_rasp.hide();
        ocupado = false;
        troco = 0;
        valorpago=0;
        valorP = 0;
        valortotal = 0;
        ammount=0;
        Estado="O";
        troconotas.setText("0");
        trocomoedas.setText("0");
      
        RetirarFundos.hide();
        DefinirFManeio.hide();
        ConsultaCofre.hide();   
        EndPayment.hide();
        ConsultaV2.hide();
        CarregaV2.hide();
        ecraconfigV2.hide();
        Channels.hide();
        
        Jampanel.hide();
          pin.low();
   //      apagaft();
        scs_spo.ResetSPO();
        scs_spo.ResetSCS();
            spoSetup = scs_spo.GetSPOSetup();
            scsSetup = scs_spo.GetSCSSetup();
            for(BANKNOTE note : spoSetup.notes){
                 
                 String strNote = note.currency.toString();
                 int strStored =  note.stored;
                 
                 switch (strNote){

                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                 if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                 if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                 if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                break;

                case "200.0 EUR" :
                 if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);}
                break;
                 }}
       Send_a.recebedadospos("limpa","",0);
       atualizacontadores();
       pagoativo= false;
       
   }
    
   double valoramais = 0;
   boolean valoramaisBollean = false;
   public void valoramaispaga(){
       
     //  valoramaisBollean = true;
       valoramais = valoramais*100;
        valoramais = Math.round(valoramais);
        valoramais = valoramais /100;
        log(valoramais + "este");
        currency.value = 0;
        valoramaisBollean = false;
        currency.value  = valoramais;
        currency.countryCode = "EUR";
        scs_spo.PayoutValue(currency);
       deativate = true;
       limpa();
   }
   
   
   Boolean JamSPO = false;
   Double falta ;
   
     String est ="";
     
     boolean inittroco = false;
    @Override
    public void SCS_SPO_Event_Occurred(SCS_SPO_event ev, CURRENCY currency)
    {
        switch(ev){
        case ev_SYSTEM_INITIALISATION_OK:
            lerspodenoms();
            scs_spo.ResetSPO();
            spoSetup = scs_spo.GetSPOSetup();
            scsSetup = scs_spo.GetSCSSetup();
            scs_spo.bezel();
            
            String FWR = scs_spo.GetSPOSetup().firmwareVersion;
            String DTV = scs_spo.GetSPOSetup().datasetVersion;
            String SFWR = scs_spo.GetSCSSetup().firmwareVersion;
            String SDTV = scs_spo.GetSCSSetup().datasetVersion;
            
            
            spofwr.setText("SPO Firmware - "+FWR);
            spodts.setText("SPO Dataset - "+DTV);
            scsfwr.setText("SCS Firmware - "+SFWR);
            scsdts.setText("SCS Dataset - "+SDTV);
           
            
            
            
            spo5e.setText(Integer.toString(spodenom5e));
            spo10e.setText(Integer.toString(spodenom10e));
            spo20e.setText(Integer.toString(spodenom20e));
            spo50e.setText(Integer.toString(spodenom50e));
            spo100e.setText(Integer.toString(spodenom100e));
            spo200e.setText(Integer.toString(spodenom200e));
            for(BANKNOTE note : spoSetup.notes){
                 String strNote = note.currency.toString();
                 int strStored =  note.stored;
                 switch (strNote){
                case "5.0 EUR" :
                if (spodenom5e > strStored & spodenom5e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(0).currency);}
                break;

                case "10.0 EUR" :
                 if (spodenom10e > strStored & spodenom10e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(1).currency);}
                break;

                case "20.0 EUR" :
                 if (spodenom20e > strStored & spodenom20e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(2).currency);}
                break;

                case "50.0 EUR" :
                if (spodenom50e > strStored & spodenom50e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(3).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(3).currency);}
                break;

                case "100.0 EUR" :
                 if (spodenom100e > strStored & spodenom100e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(4).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(4).currency);}
                break;

                case "200.0 EUR" :
                 if (spodenom200e > strStored & spodenom200e !=0 ){scs_spo.RouteNoteToPayout(spoSetup.notes.get(5).currency);}else {scs_spo.RouteNoteToCashbox(spoSetup.notes.get(5).currency);}
                break;
                
                case "500.0 EUR" :
                 scs_spo.RouteNoteToCashbox(spoSetup.notes.get(6).currency);
                break;
                 }}
            
               configchannels();
               atualizacontadores();
               
            
            
              //  scs_spo.UnInhibitBanknote(spoSetup.notes.get(6).currency);

             if (Jampanel.isVisible()){
                
                Jampanel.hide();
                SplashScreen.show();
           
            }
            /* try{

            Statement st;
            ResultSet rs;

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                + "user=root&password=Pa$$w0rd");

            st = con.createStatement();
            rs = st.executeQuery("select * from ith_periodo where pago_finalizado = 0 ");

            while(rs.next()){
            
             vpp = rs.getDouble(4);
            
             log("Valor init - " + vpp);
             FT=rs.getString(2);
             
             
            }

            rs.close();
            st.close();

        }catch(SQLException ex){
            
        }
*/
            

       case ev_SYSTEM_VALUES_UPDATED:
            if (carregaconsulta){carrega();}
            
            
             if (redraw){
                 AutoDismiss.showOptionDialog(rootPane, "A dispensar a quantidade selecionada, aguarde p.f.","Dispensador" ,5000);
                 redraw=false;    
                 Denomination.hide();
                 ecraconfigV2.show();
                 atualizacontadores();
                 
            }
              
            
            break;
            
            
       case ev_FLOATED:
           
          
                 break;   
           
         
        
        case ev_COIN_VALUE_ACCEPTED:
                 
            if (!deativate && pagoativo){
              log("valor devolvido32"); 
              valoramais=  currency.value;
               valoramaisBollean = true;
              log(valoramais + "ladnfk.wjdbf-wrklnv-lwsfbv-ljwfbvkaejbgvl-rjgbfnv");
            //  Runnable task = () -> valoramaispaga();
            //  ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
                   
             
               return;      
             }   
                       
             if (!deativate && !pagoativo & !carregaconsulta){
              log("valor devolvido12");     
              currency.countryCode = "EUR";
              scs_spo.PayoutValue(currency);
               return;   
             }
            
            if (pagoativo && deativate){
               pagamento(currency);
               return;
                }
               if (carregaconsulta){carregapago(currency);return;}
               
               if (!pagoativo && !carregaconsulta ){
              log("valor devolvido2333");     
              currency.countryCode = "EUR";
              scs_spo.PayoutValue(currency);
               return;    
               }

               break;
        case ev_NOTE_STORED_IN_PAYOUT:
              if (!deativate && pagoativo){
              log("valor devolvido32"); 
              valoramais=  currency.value;
               valoramaisBollean = true;
              log(valoramais + "ladnfk.wjdbf-wrklnv-lwsfbv-ljwfbvkaejbgvl-rjgbfnv");
            //  Runnable task = () -> valoramaispaga();
            //  ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
                   
             
               return;      
             }   
                       
             if (!deativate && !pagoativo & !carregaconsulta){
              log("valor devolvido12");     
              currency.countryCode = "EUR";
              scs_spo.PayoutValue(currency);
               return;   
             }
            
            if (pagoativo && deativate){
               pagamento(currency);
               return;
                }
               if (carregaconsulta){carregapago(currency);return;}
               
               if (!pagoativo && !carregaconsulta ){
              log("valor devolvido2333");     
              currency.countryCode = "EUR";
              scs_spo.PayoutValue(currency);
               return;    
               }

               break;
                
        case ev_NOTE_STACKED:
            gravapagamentocofre(currency);
            
            if (!deativate && pagoativo){
              log("valor devolvido32"); 
              valoramais=  currency.value;
               valoramaisBollean = true;
              log(valoramais + "ladnfk.wjdbf-wrklnv-lwsfbv-ljwfbvkaejbgvl-rjgbfnv");
            //  Runnable task = () -> valoramaispaga();
            //  ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
                   
             
               return;      
             }   
                       
             if (!deativate && !pagoativo & !carregaconsulta){
              log("valor devolvido12");     
              currency.countryCode = "EUR";
              scs_spo.PayoutValue(currency);
               return;   
             }
            
            if (pagoativo && deativate){
               pagamento(currency);
               
               return;
                }
               if (carregaconsulta){
                   carregapago(currency);
                   
                   return;
               }
               
               if (!pagoativo && !carregaconsulta ){
              log("valor devolvido2333");     
              currency.countryCode = "EUR";
              scs_spo.PayoutValue(currency);
               return;    
               }

              
               break;
               
               
        case ev_DISPENSING: 
            
            
             log("New Event - " + " Valor dispensado de - "+ currency );
                break;
      
        case ev_DISPENSE_RESPONSE_OK:
            
            System.out.println("OK");
            
             if (pagof){
                 pagof=false;
                   try{
                       
                      double pagar = Double.parseDouble(pagamentovalor.getText());
          
          
         
           
                
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd") ;
                java.util.Date utilDate = new java.util.Date();
                
                
                java.sql.Timestamp sqlTimeStamp = convertJavaDateToSqlDate(utilDate);
                
                
                
                String query = " insert into ith_periodo (Data,documento,pago_com,valor_doc,valor_dispensado,user,insert_conc,pago_finalizado)"
                        + " values (?, ?, ?, ?, ?, ?,?,?)";
                
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setTimestamp (1, sqlTimeStamp);
                preparedStmt.setString (2, "Pgt Div."+ descritivo.getText());
                preparedStmt.setDouble   (3, 000);
                preparedStmt.setDouble(4, 000);
                preparedStmt.setDouble    (5, pagar);
                preparedStmt.setString (6, "Operador");
                preparedStmt.setInt (7, 1);
                
                preparedStmt.setInt (8, 1);
                // execute the preparedstatement
                preparedStmt.execute();
            
                con.close();
            
        
      }catch(SQLException ex){
          
          System.out.println(ex);
         }
                 
             }
              
               if (pagoativo && !valoramaisBollean){
               System.out.println("OK, pago ativo");    
                  // pagoativo=false;
                   ocupado = false;
               //    troco = 0;
                   valorpago=0;
                   valorP = 0;
                //   valortotal = 0;
                   ammount=0;
             //    pagoativo=false;
                   ecraPagamentos.hide();
                   EndPayment.show();
                   CarregaV2.hide();
                   ConsultaV2.hide();
                   
                   
        /*   if(imp.isSelected()){
                        
                    if(nifenter){    
                    
                   Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo php /var/www/html/fttnc.php" + nifentered);
            nifenter=false;
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } 
                    }else{
                        
                           
                   Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo php /var/www/html/ftt.php");
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } 
                        
                        
                    }
                    }*/
               }
        
               
                break;
         case ev_DISPENSE_RESPONSE_SYSTEM_BUSY:
               ocupado = true;
             log("sistema ocupado!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
             if (pagoativo &&!valoramaisBollean ){
             troco();
             return;}
        
            
             
             break;
                
         case ev_SYSTEM_COINMECH_DISABLED_ByNando:
             log("Moedas Desativo");
             if (pagoativo){
                
                 log("troco|!!!!!!!!!!!!!!");
                 
                 
             trocofalha = false;   
             troco();
             }
             break;
             
             case ev_SYSTEM_COINMECH_ENABLED_ByNando:
             log("Moedas ativo");
             break;
        case ev_COM_PORT_OPEN_FAILED:
                log("New Event - " + ev); 
                break;
               
        case ev_SCS_PAYOUT_TIMEOUT:
                log("New Event - " + ev);
                break;
        case ev_SCS_COMMS_ERROR:
        
            
             log("New Event - " + ev);
           
      
                break;
                
                case ev_SPO_COMMS_ERROR:
     
        
                log("New Event - " + ev);
                
                break;
                
                case ev_SYSTEM_TERMINATED:           
                log("New Event - " + ev);
    
                        
                break;
                
                
                case ev_SPO_CASHBOX_REMOVED:
                
                    if (sensorcofre){
                Connection conn2 = null;
              try {
                  conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                          + "user=root&password=Pa$$w0rd");
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              String del = "delete FROM cofre;";
              
              PreparedStatement preparedStmt2 = null;
        try {
            preparedStmt2 = conn2.prepareStatement(del);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                  preparedStmt2.execute();
              } catch (SQLException ex) {
                  Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
              }
                        
      sensorcofre = false;
                    }else{
       AutoDismiss.showOptionDialog(rootPane, "COFRE RETIRADO SEM PERMISSÃO, INTRODUZA A PASS SUPERVISOR","Atenção" ,2000);     
       sendNotify("Cofre Retirado!!");
        ecraPagamentos.hide();
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.hide();
        ecratransacoes.hide();
        cofre_removido.show();
        configgeral.hide();
        RetirarFundos.hide();
        ConsultaCofre.hide();
        DefinirFManeio.hide();
        ConsultaV2.hide();
        CarregaV2.hide();
        ecraconfigV2.hide();
                    }
               break;
               case ev_DISPENSE_RESPONSE_CANNOT_PAY_EXACT_AMOUNT:
                    System.out.println("Nao posso pagar");
                 if (pagoativo){  
                 log("New Event - " + ev); 
                 currency = new CURRENCY();
                 currency.value  = valortotal;
                 currency.countryCode = "EUR";
                 scs_spo.PayoutValue(currency);  
                 Send_a.recebedadospos("SemTroco","",0);
                 AutoDismiss.showOptionDialog(rootPane, "SEM TROCO, VALOR DE "+currency," € Devolvido" ,5000);
                 ecraPagamentos.hide();
                 SplashScreen.show();
                 }
                break;
           
                
               case ev_SPO_VALIDATOR_JAM_CLEARED:
                   
            //    Send_a.recebedadospos("unsafejamclear","",0);
                   
                   
                   break;
                   
                   case ev_SPO_UNSAFE_JAM:
                   
       //          Send_a.recebedadospos("unsafejam","",0);
                   
                   
                   break;
                   
               case ev_DISPENSED:
                   
                    
                 
                 
                       
                   
                   
                   
                   if (valoramaisBollean){
                       Runnable task = () -> valoramaispaga();
                       ScheduledFuture<?> future = executor.schedule(task, 5, TimeUnit.SECONDS);
                        log("kdfbglwudbgfkwujfhgvçoiwfehbvçoeifhjgvº-eqrajngçlkwrqeahnçgfilhçeqrhgçqwerhgçqewrh;0");
                       // valoramaispaga();
                   }
                   
                   if(JamSPO){
                       System.out.println("nota encravada");
                       
                     break;  
                       
                   }else{
                   
                    if (pagoativo){
                        log("limpa 1");
                        String str = String.valueOf(valoramaisBollean);
                        log(str);
                        if (!valoramaisBollean)
        
                        limpa();                   
                    }
                   }                    
                   break;
               
               case ev_SPO_CASHBOX_REPLACED:
                    if (sensorcofre){    
                        sensorcofre=false;
                    }else{
                        sendNotify("Cofre Recolocado!!");
                 ecraPagamentos.hide();
                 PasswordPrompt.hide();
                 ecraconfig_old.hide();
                 Pagamentos.hide();
                 scs_spo_consultas.hide();
                 Carregamentos.hide();
                 Supervisor.hide();
                 SplashScreen.show();
                 ecratransacoes.hide();
                 cofre_removido.hide();
                 ConsultaCofre.hide();
                 ecraconfigV2.hide();
                 
                 
                    }
                break;
                
                case ev_DISPENSE_RESPONSE_NOT_ENOUGH_MONEY_FOR_PAYOUT:
               log("New Event - " + ev);
                break;
            
            case ev_SPO_PAYOUT_JAMMED:
        PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
        configgeral.hide();
        EndPayment.hide();
        RetirarFundos.hide();      
        ConsultaCofre.hide();
        DefinirFManeio.hide();
        EstadoGeral.hide();
        Niveis_Minimos.hide();
        sendNotify("Nota Encravada!!");
        jammessage.setText("Nota Encravada no Validador");
        Jampanel.show();
         currency = new CURRENCY();
         falta = currency.value;
         JamSPO = true;
         
        
        
                
                log("New Event - " + ev);
                break;
            case ev_SPO_PAYOUT_HALTED:
                log("New Event - " + ev);
                break;
            case ev_SPO_PAYOUT_TIMEOUT:
               log("New Event - " + ev);
                break;
            case ev_SCS_PAYOUT_JAMMED:
                               PasswordPrompt.hide();
        ecraconfig_old.hide();
        Pagamentos.hide();
        scs_spo_consultas.hide();
        Carregamentos.hide();
        Supervisor.hide();
        SplashScreen.show();
        ecratransacoes.hide();
        cofre_removido.hide();
        configgeral.hide();
        EndPayment.hide();
        RetirarFundos.hide();      
        ConsultaCofre.hide();
        DefinirFManeio.hide();
        EstadoGeral.hide();
        Niveis_Minimos.hide();
        sendNotify("Moeda Encravada!!");
        jammessage.setText("Moeda encravada no Validador");
        Jampanel.show();
        
                log("New Event - " + ev);
                break;
            case ev_SCS_PAYOUT_HALTED:
               log("New Event - " + ev);
                break;
            
            case ev_SCS_PAYOUT_FRAUD_ATTEMPT:
              log("New Event" + ev);
                break;
                
            case  ev_INCOMPLETE_PAYOUT_SPO:
                
                break;
                
            case ev_INCOMPLETE_PAYOUT_SCS:
            
                
                break;
                
            case ev_SPOTOTAL_FORPAY:
                
                Totalsposcs(currency, "SPO");
        //        troconotas.setText(String.valueOf(currency.value) + " EUR");
                
                break;
                
            case ev_SCSTOTAL_FORPAY:
                
                Totalsposcs(currency, "SCS");
                
        //         trocomoedas.setText(String.format("%.2f",String.valueOf(currency.value) + " EUR"));
                
                break;
                 
                
                
                
            default:
                    log("New Event - " + ev);
                  
                    
        }
    }
    
   String FT = "";
   boolean restore = false;
    double incompspo=0;
   double TotalSpo = 0;
   double TotalScs = 0;
    double incomp = 0;
   
   
   
   
   public void Totalsposcs(CURRENCY currency, String Validador){
      
       if ("SPO".equals(Validador)){
        
           try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
      String query = " UPDATE ith_periodo SET Spo_value=? WHERE id=(select max(ID) from ith_periodo)";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble (1, currency.value);
      
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }
       }
       
        if ("SCS".equals(Validador)){
        
           try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
      String query = " UPDATE ith_periodo SET Scs_value=? WHERE id=(select max(ID) from ith_periodo)";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setDouble (1, currency.value);
      
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }
       }
       
       
   }
   
   
   public void restartapp(){
   
      AutoDismiss.showOptionDialog(rootPane, "Fecho Realizado, vai agora reiniciar!", "ATENÇÃO" ,6000);
          
          Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sudo init 6");
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   }
   
   
      int d1c = 0;
      int d2c = 0;
      int d5c= 0;
      int d10c= 0;
      int d20c= 0;
      int d50c= 0;
      int d1e= 0;
      int d2e= 0;
      int d5e= 0;
      int d10e= 0;
      int d20e= 0;
      int d50e= 0;
      int d100e= 0;
      int d200e= 0;
      int d500e= 0;
   
   public void atualizacontadores(){
      scs_spo.ResetSPO();
      scs_spo.ResetSCS(); 
      
    
      
      
      
      double totalcaixascs = 0;
      
      double totalcaixaspo = 0;
      
      double totalcaixa = 0;
      
      spoSetup = scs_spo.GetSPOSetup();
      scsSetup = scs_spo.GetSCSSetup();
      
     
        
        for(BANKNOTE note : spoSetup.notes){
            double total = 0;
            String strNote = note.currency.toString();
            int strStored =  note.stored;
            switch (strNote){

                case "5.0 EUR" :
                    d5e=strStored;
                nnota5.setText(String.valueOf(strStored));
                //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(0).currency);
                total = Math.round(strStored*5*100);
                total = total /100;
                totalcaixaspo += total;
                nnota5.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb5e.setValue(strStored);
                if(strStored==0){
                    cpb5e.setString("");
                }else{
                cpb5e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                } 
                
                break;

                case "10.0 EUR" :
                    d10e=strStored;
                nnota10.setText(String.valueOf(strStored));
                //   scs_spo.RouteNoteToPayout(spoSetup.notes.get(1).currency);
                total = Math.round(strStored*10*100);
                total = total /100;
                totalcaixaspo += total;
                nnota10.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb10e.setValue(strStored);
                 if(strStored==0){
                    cpb10e.setString("");
                }else{
                cpb10e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                } 
                break;

                case "20.0 EUR" :
                    d20e=strStored;
                nnota20.setText(String.valueOf(strStored));
                //  scs_spo.RouteNoteToPayout(spoSetup.notes.get(2).currency);
                total = Math.round(strStored*20*100);
                total = total /100;
                totalcaixaspo += total;
                nnota20.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb20e.setValue(strStored);
                if(strStored==0){
                    cpb20e.setString("");
                }else{
                cpb20e.setString(" "+String.valueOf(strStored)+ " | "+total+" EUR");
                } 
                break;

                case "50.0 EUR" :
                    d50e=strStored;
                nnota50.setText(String.valueOf(strStored));
                total = Math.round(strStored*50*100);
                total = total /100;
                totalcaixaspo += total;
                nnota50.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb50e.setValue(strStored);
              if(strStored==0){
                    cpb50e.setString("");
                }else{
                cpb50e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                }   
                break;

                case "100.0 EUR" :
                d100e=strStored;
                nnota100.setText(String.valueOf(strStored));
                total = Math.round(strStored*100*100);
                total = total /100;
                totalcaixaspo += total;
                nnota100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb100e.setValue(strStored);
                if(strStored==0){
                    cpb100e.setString("");
                }else{
                cpb100e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                }   
                break;

                case "200.0 EUR" :
                    d200e=strStored;
                nnota200.setText(String.valueOf(strStored));
                total = Math.round(strStored*200*100);
                total = total /100;
                totalcaixaspo += total;
                nnota200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb200e.setValue(strStored);
                 if(strStored==0){
                    cpb200e.setString("");
                }else{
                cpb200e.setString(" "+String.valueOf(strStored)+" | "+total+" EUR");
                }   
                break;

                case "500.0 EUR" :
                    d500e=strStored;
                nnota500.setText(String.valueOf(strStored));
                total = Math.round(strStored*500*100);
                total = total /100;
                totalcaixaspo += total;
                nnota500.setText(" "+String.valueOf(strStored)+" | "+total+" EUR");
                break;
            }

        }

        for(COIN coin : scsSetup.coins){
            double total = 0;
            String strcoin = coin.currency.toString();
            int strStored =  coin.stored;
            switch (strcoin){

                case "0.01 EUR" :
                    d1c=strStored;
                nmoeda001.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.01*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda001.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb1c.setValue(strStored);
                 if(strStored==0){
                     cpb1c.setString("");
                 }else{
                cpb1c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;

                case "0.02 EUR" :
                    d2c=strStored;
                nmoeda002.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.02*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda002.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb2c.setValue(strStored);
                System.out.println(strStored);
                 if(strStored==0){
                     cpb2c.setString("");
                 }else{
                cpb2c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;

                case "0.05 EUR" :
                    d5c=strStored;
                nmoeda005.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.05*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda005.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb5c.setValue(strStored);
                if(strStored==0){
                     cpb5c.setString("");
                 }else{
                cpb5c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 } break;

                case "0.1 EUR" :
                    d10c=strStored;
                nmoeda010.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.10*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda010.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb10c.setValue(strStored);
               if(strStored==0){
                     cpb10c.setString("");
                 }else{
                cpb10c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 } 
                break;

                case "0.2 EUR" :
                    d20c=strStored;
                nmoeda020.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.20*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda020.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb20c.setValue(strStored);
                if(strStored==0){
                     cpb20c.setString("");
                 }else{
                cpb20c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;
                
                case "0.5 EUR" :
                    d50c=strStored;
                nmoeda050.setText(String.valueOf(strStored));
                total = Math.round(strStored*0.50*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda050.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb50c.setValue(strStored);
                 if(strStored==0){
                     cpb50c.setString("");
                 }else{
                cpb50c.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }  
                break;

                case "1.0 EUR" :
                    d1e=strStored;
                nmoeda100.setText(String.valueOf(strStored));
                total = Math.round(strStored*1*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda100.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb1e.setValue(strStored);
                if(strStored==0){
                     cpb1e.setString("");
                 }else{
                cpb1e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 }
                break;

                case "2.0 EUR" :
                    d2e=strStored;
                nmoeda200.setText(String.valueOf(strStored));
                total = Math.round(strStored*2*100);
                total = total /100;
                totalcaixascs += total;
                nmoeda200.setText(String.valueOf(strStored)+" | "+total+" EUR");
                cpb2e.setValue(strStored);
                 if(strStored==0){
                     cpb2e.setString("");
                 }else{
                cpb2e.setString(String.valueOf(strStored)+ " Un"+"       ->    "+total+" EUR");
                 } 
                break;


            }
           totalcaixaspo = Math.round(totalcaixaspo*100);
            totalcaixaspo = totalcaixaspo /100;
            
    //        enviardadosrecicladores("spo",totalcaixaspo);
            
            totalcaixascs = Math.round(totalcaixascs*100);
            totalcaixascs = totalcaixascs /100;
      //      enviardadosrecicladores("scs",totalcaixascs);
            
            totalcaixa = totalcaixaspo + totalcaixascs;
            
            totalcaixa = Math.round(totalcaixa*100);
            totalcaixa = totalcaixa /100;
            
     //        enviardadosrecicladores("total",totalcaixa);
            

            ctotatual.setText(String.valueOf(totalcaixa)+" EUR");
            
             try{
          
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");
      String query = " UPDATE EstadoAtual SET d1c=?,d2c=?,d5c=?,d10c=?,d20c=?,d50c=?,d1e=?,d2e=?,d5e=?,d10e=?,d20e=?,d50e=?,d100e=?,d200e=?,d500e=?,totspo=?,totscs=?,totgeral=? WHERE id=(select max(ID) from EstadoAtual)";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, d1c);
      preparedStmt.setInt (2, d2c);
      preparedStmt.setInt (3, d5c);
      preparedStmt.setInt (4, d10c);
      preparedStmt.setInt (5, d20c);
      preparedStmt.setInt (6, d50c);
      preparedStmt.setInt (7, d1e);
      preparedStmt.setInt (8, d2e);
      preparedStmt.setInt (9, d5e);
      preparedStmt.setInt (10, d10e);
      preparedStmt.setInt (11, d20e);
      preparedStmt.setInt (12, d50e);
      preparedStmt.setInt (13, d100e);
      preparedStmt.setInt (14, d200e);
      preparedStmt.setInt (15, d500e);
      preparedStmt.setDouble (16, totalcaixaspo);
      preparedStmt.setDouble (17, totalcaixascs);
      preparedStmt.setDouble   (18, totalcaixa);
      preparedStmt.execute();
      con.close();
      }catch(SQLException ex){
          System.out.println(ex);
         }
        }
      
        
int nm1c= 0;
int nm2c= 0;
int nm5c= 0;
int nm10c= 0;
int nm20c= 0;
int nm50c= 0;
int nm1e= 0;
int nm2e= 0;
int nm5e= 0;
int nm10e= 0;
int nm20e= 0;
int nm50e= 0;
int nm100e= 0;
int nm200e= 0;        
        
        
           try{

            Statement stt2;
            ResultSet rss2;

            Connection conn2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iThinkdb?"
                    + "user=root&password=Pa$$w0rd");

            stt2 = conn2.createStatement();
            String queryy = "select * from  NiveisMinimos WHERE id=(SELECT MAX(id) FROM NiveisMinimos)";
            
            rss2 = stt2.executeQuery(queryy);
           

            while(rss2.next()){
    
                
               nm1c = (rss2.getInt(3));
                nm2c = (rss2.getInt(4));
                 nm5c = (rss2.getInt(5));
                  nm10c = (rss2.getInt(6));
                   nm20c = (rss2.getInt(7));
                    nm50c = (rss2.getInt(8));
                     nm1e = (rss2.getInt(9));
                      nm2e = (rss2.getInt(10));
                
               nm5e = (rss2.getInt(11));
                nm10e = (rss2.getInt(12));
                 nm20e = (rss2.getInt(13));
                  nm50e = (rss2.getInt(14));
                   nm100e = (rss2.getInt(15));
                    nm200e = (rss2.getInt(16));
               
            }

            rss2.close();
            stt2.close();

        }catch(SQLException ex){
            
            System.out.println(ex);
        }
          
           StringBuilder result = new StringBuilder();
           
           
           
         if (nm1c >= d1c & fl1 == 0 & nm1c!=0){
             
             fl1 = 1;
             System.out.println("Nivel 1c atingido" + d1c);
             sendNotify("Nivel de Moedas de 1 Centimo baixo");
             result.append("A denominação -> moedas de 1 centimo atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d1c);
             result.append(String.format("%n%n", ""));
                 
          }else if ( nm1c < d1c){
             
             fl1=0;
         }       
             
          if (nm2c >= d2c & fl10 == 0 & nm2c!=0){
             fl2 = 1;
             System.out.println("Nivel 1c atingido");
        sendNotify("Nivel de Moedas de 2 Centimos baixo");
                result.append("A denominação -> moedas de 2 centimos atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d2c);
                 result.append(String.format("%n%n", ""));
          }else if ( nm2c < d2c){
             
             fl2=0;
         }       
            if (nm5c >= d5c & fl5 == 0 & nm5c!=0){
             fl5 = 1;
             System.out.println("Nivel 1c atingido");
        sendNotify("Nivel de Moedas de 5 Centimos baixo");
               result.append("A denominação -> moedas de 5 centimos atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d5c);
                 result.append(String.format("%n%n", ""));
           }else if ( nm5c < d5c){
             
             fl5=0;
         }       
              if (nm10c >= d10c & fl10 == 0 & nm10c!=0){
             fl10 = 1;
             System.out.println("Nivel 1c atingido");
         sendNotify("Nivel de Moedas de 10 Centimos baixo");
              result.append("A denominação -> moedas de 10 centimos atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d10c);
                 result.append(String.format("%n%n", ""));
        }else if ( nm10c < d10c){
             
             fl10=0;
         }       
                if (nm20c >= d20c & fl20 == 0 & nm20c!=0){
             fl20 = 1;
             System.out.println("Nivel 1c atingido");
          sendNotify("Nivel de Moedas de 20 Centimos baixo");
               result.append("A denominação -> moedas de 20 centimos atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d20c);
                 result.append(String.format("%n%n", ""));
         }else if ( nm20c < d20c){
             
             fl20=0;
         }       
                  if (nm50c >= d50c & fl50 == 0 & nm50c!=0){
             fl50 = 1;
             System.out.println("Nivel 1c atingido");
         sendNotify("Nivel de Moedas de 50 Centimos baixo");
              result.append("A denominação -> moedas de 50 centimos atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d50c);
                 result.append(String.format("%n%n", ""));
        }else if ( nm50c < d50c){
             
             fl50=0;
         }       
                    if (nm1e >= d1e & fl100 == 0 & nm1e!=0){
             fl100 = 1;
             System.out.println("Nivel 1c atingido");
         sendNotify("Nivel de Moedas de 1 Euro baixo");
               result.append("A denominação -> moedas de 1 euro atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d1e);
                 result.append(String.format("%n%n", ""));
          
         }else if ( nm1e < d1e){
             
             fl100=0;
         }       
                      if (nm2e >= d2e & fl200 == 0 & nm2e!=0){
             fl200 = 1;
             System.out.println("Nivel 1c atingido");
           sendNotify("Nivel de Moedas de 2 Euros baixo");
              result.append("A denominação -> moedas de 2 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d2e);
                 result.append(String.format("%n%n", ""));
          
         }else if ( nm2e < d2e){
             
             fl200=0;
         }       
                        if (nm5e >= d5e & fl500 == 0 & nm5e!=0){
             fl500 = 1;
             System.out.println("Nivel 1c atingido");
             sendNotify("Nivel de Notas de 5 Euros baixo");
                result.append("A denominação -> Notas de 5 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d5e);
                 result.append(String.format("%n%n", ""));
          }else if ( nm5e < d5e){
             
             fl500=0;
         }       
                          if (nm10e >= d10e & fl1000 == 0 & nm10e!=0){
             fl1000 = 1;
             System.out.println("Nivel 1c atingido");
             sendNotify("Nivel de Notas de 10 Euros baixo");
               result.append("A denominação -> Notas de 10 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d10e);
                 result.append(String.format("%n%n", ""));
        }else if ( nm10e < d10e){
             
             fl1000=0;
         }       
                            if (nm20e >= d20e & fl2000 == 0 & nm20e!=0){
             fl2000 = 1;
             System.out.println("Nivel 1c atingido");
             sendNotify("Nivel de Notas de 20 Euros baixo");
               result.append("A denominação -> Notas de 20 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d20e);
                 result.append(String.format("%n%n", ""));
          }else if ( nm20e < d20e){
             
             fl2000=0;
         }       
                              if (nm50e >= d50e & fl5000 == 0 & nm50e!=0){
             fl5000 = 1;
             System.out.println("Nivel 1c atingido");
             sendNotify("Nivel de Notas de 50 Euros baixo");
             result.append("A denominação -> Notas de 50 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d50e);
                 result.append(String.format("%n%n", ""));
          }else if ( nm50e < d50e){
             
             fl5000=0;
         }       
                                if (nm100e >= d100e & fl10000 == 0 & nm100e!=0){
             fl10000 = 1;
             System.out.println("Nivel 1c atingido");
         sendNotify("Nivel de Notas de 100 Euros baixo");
             result.append("A denominação -> Notas de 100 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d100e);
                 result.append(String.format("%n%n", ""));
        
         }else if ( nm100e < d100e){
             
             fl10000=0;
         }       
                                  if (nm200e >= d200e & fl20000 == 0 & nm200e!=0){
             fl20000 = 1;
             System.out.println("Nivel 1c atingido");
         sendNotify("Nivel de Notas de 200 Euros baixo");
                result.append("A denominação -> Notas de 200 euros atingiu o limite minimo ").append("| Quantidades Atuais - ").append(d200e);
                 result.append(String.format("%n%n", ""));
         
         }else if ( nm200e < d200e){
             
             fl20000=0;
         }       
        
                                  
        if( result.length() != 0 ){
            
            enviamailniveis.recebemail(result.toString());
        }
      
       
   }
   
   
  
   
   
   int fl1=0;
   int fl2=0;
   int fl5=0;
   int fl10=0;
   int fl20=0;
   int fl50=0;
   int fl100=0;
   int fl200=0;
   int fl500=0;
   int fl1000=0;
   int fl2000=0;
   int fl5000=0;
   int fl10000=0;
   int fl20000=0;
   
   
  /*  public int enviardados(String tipo, String Fact,double v1 ){
       
        pagamentopos ed = new pagamentopos(tipo,Fact,v1);
        
     
        new Thread(() -> {
        
        try
			        { 
                                        log("inicio");
					InetAddress ip = InetAddress.getByName(IPHOST2); //IP do POS
                                          log("inicio2");
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
                                    
                                    errorlog("Erro na ligação ao POS" + e);
                                    
			        } 
    // atualizacontadores();
     
      
       
    }
        ).start();
        
        
     return 0;
    }
    */
                
                
          public int enviardadosrecicladores(String tipo,double v1 ){
       
/*      interfaceonlinestatus ed1 = new interfaceonlinestatus(tipo,v1);
        
     try
			        { 
                                       
					InetAddress ip = InetAddress.getByName(IPHOST2); //IP do POS
			                Socket s = new Socket(ip, 5058); 
			                log("Ligado a sistema iThink com sucesso");
                                        ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream());
                                        objOut.writeObject(new interfaceonlinestatus(tipo,v1));
                                        log("dados enviados " + v1 + " EUR");
			                objOut.flush();
			                objOut.close(); 
			                s.close();
			        }catch(IOException e){ 
                                    
                                    errorlog("Erro na ligação ao POS" + e);
                                    
			        } 
   */     return 0;
    }
  
    /**}
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String args[]) throws IOException, InterruptedException {
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
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            
            public String usr = "";
                
             
        
            
            
            @Override
                public void run() {
                 
                
                try {
                    new MainActivity(usr).setVisible(true);
                    
             
                    
                    
                    
                } catch (SAXException | IOException | ParserConfigurationException ex) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A;
    private javax.swing.JRadioButton AcCarregamentos;
    private javax.swing.JRadioButton AcConsultacofre;
    private javax.swing.JRadioButton AcDefnotas;
    private javax.swing.JRadioButton AcEstadoGeral;
    private javax.swing.JRadioButton AcExistencias;
    private javax.swing.JRadioButton AcFechoperiodo;
    private javax.swing.JRadioButton AcFundomaneio;
    private javax.swing.JRadioButton AcNiveisminimos;
    private javax.swing.JRadioButton AcPagamentos;
    private javax.swing.JRadioButton AcPainelbordo;
    private javax.swing.JRadioButton AcReinicio;
    private javax.swing.JRadioButton AcSupervisor;
    private javax.swing.JButton B;
    private javax.swing.JButton But_painelbordo;
    private javax.swing.JButton But_painelbordo1;
    private javax.swing.JButton C;
    private javax.swing.JPanel CarregaV2;
    private javax.swing.JPanel Carregamentos;
    private javax.swing.JToggleButton Ch100eur;
    private javax.swing.JToggleButton Ch10eur;
    private javax.swing.JToggleButton Ch200eur;
    private javax.swing.JToggleButton Ch20eur;
    private javax.swing.JToggleButton Ch500eur;
    private javax.swing.JToggleButton Ch50eur;
    private javax.swing.JToggleButton Ch5eur;
    private javax.swing.JPanel Channels;
    private javax.swing.JPanel ConsultaCofre;
    private javax.swing.JPanel ConsultaV2;
    private javax.swing.JButton D;
    private javax.swing.JPanel DefinirFManeio;
    private javax.swing.JPanel Denomination;
    private javax.swing.JButton E;
    private javax.swing.JPanel EndPayment;
    private javax.swing.JPanel EstadoGeral;
    private javax.swing.JButton F;
    private javax.swing.JButton G;
    private javax.swing.JButton H;
    private javax.swing.JButton I;
    private javax.swing.JButton J;
    private javax.swing.JPanel Jampanel;
    private javax.swing.JButton K;
    private javax.swing.JButton L;
    private javax.swing.JButton M;
    private javax.swing.JButton N;
    private javax.swing.JPanel Niveis_Minimos;
    private javax.swing.JButton O;
    private javax.swing.JButton P;
    private javax.swing.JButton PONT;
    private javax.swing.JPanel Pagamentos;
    private javax.swing.JPanel PasswordPrompt;
    private javax.swing.JButton Q;
    private javax.swing.JButton Q1;
    private javax.swing.JButton Q10;
    private javax.swing.JButton Q2;
    private javax.swing.JButton Q3;
    private javax.swing.JButton Q4;
    private javax.swing.JButton Q5;
    private javax.swing.JButton Q6;
    private javax.swing.JButton Q7;
    private javax.swing.JButton Q8;
    private javax.swing.JButton Q9;
    private javax.swing.JButton R;
    private javax.swing.JPanel RetirarFundos;
    private javax.swing.JButton S;
    private javax.swing.JButton SPACE;
    private javax.swing.JPanel SplashScreen;
    private javax.swing.JPanel Supervisor;
    private javax.swing.JButton T;
    private javax.swing.JButton TRAC;
    private javax.swing.JButton U;
    private javax.swing.JLabel Un100e;
    private javax.swing.JLabel Un10c;
    private javax.swing.JLabel Un10e;
    private javax.swing.JLabel Un1c;
    private javax.swing.JLabel Un1e;
    private javax.swing.JLabel Un200e;
    private javax.swing.JLabel Un20c;
    private javax.swing.JLabel Un20e;
    private javax.swing.JLabel Un2c;
    private javax.swing.JLabel Un2e;
    private javax.swing.JLabel Un50c;
    private javax.swing.JLabel Un50e;
    private javax.swing.JLabel Un5c;
    private javax.swing.JLabel Un5e;
    private javax.swing.JButton V;
    private javax.swing.JButton VIRG;
    private javax.swing.JButton W;
    private javax.swing.JButton X;
    private javax.swing.JButton Y;
    private javax.swing.JButton Z;
    private javax.swing.JLabel apagarlabel;
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
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b25;
    private javax.swing.JButton b26;
    private javax.swing.JButton b27;
    private javax.swing.JButton b28;
    private javax.swing.JButton b29;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b51;
    private javax.swing.JButton b52;
    private javax.swing.JButton b53;
    private javax.swing.JButton b54;
    private javax.swing.JButton b55;
    private javax.swing.JButton b56;
    private javax.swing.JButton b57;
    private javax.swing.JButton b58;
    private javax.swing.JButton b59;
    private javax.swing.JButton b6;
    private javax.swing.JButton b60;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton bc;
    private javax.swing.JButton bc1;
    private javax.swing.JButton bc2;
    private javax.swing.JButton bc3;
    private javax.swing.JButton be;
    private javax.swing.JButton be1;
    private javax.swing.JButton be2;
    private javax.swing.JButton but_carregamentos;
    private javax.swing.JButton but_carregamentos1;
    private javax.swing.JButton but_consultas;
    private javax.swing.JButton but_consultas2;
    private javax.swing.JButton but_emptyall;
    private javax.swing.JButton but_emptyall1;
    private javax.swing.JButton but_float;
    private javax.swing.JButton but_float1;
    private javax.swing.JButton but_float2;
    private javax.swing.JButton but_float3;
    private javax.swing.JButton but_float4;
    private javax.swing.JButton but_float5;
    private javax.swing.JButton but_float6;
    private javax.swing.JButton but_fmaneiodef;
    private javax.swing.JButton but_fmaneiodef1;
    private javax.swing.JButton but_fmaneiodef2;
    private javax.swing.JButton but_pagamentos;
    private javax.swing.JButton but_pagamentos1;
    private javax.swing.JButton but_supervisor;
    private javax.swing.JButton but_supervisor1;
    private javax.swing.JButton but_trocos;
    private javax.swing.JButton but_trocos1;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c10;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c20;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel c50;
    private javax.swing.JLabel cc10;
    private javax.swing.JLabel cc100;
    private javax.swing.JLabel cc20;
    private javax.swing.JLabel cc200;
    private javax.swing.JLabel cc5;
    private javax.swing.JLabel cc50;
    private javax.swing.JLabel cc500;
    private javax.swing.JLabel ccvalortotal;
    private javax.swing.JPanel cofre_removido;
    private javax.swing.JPanel configgeral;
    private javax.swing.JProgressBar cpb100e;
    private javax.swing.JProgressBar cpb10c;
    private javax.swing.JProgressBar cpb10e;
    private javax.swing.JProgressBar cpb1c;
    private javax.swing.JProgressBar cpb1e;
    private javax.swing.JProgressBar cpb200e;
    private javax.swing.JProgressBar cpb20c;
    private javax.swing.JProgressBar cpb20e;
    private javax.swing.JProgressBar cpb2c;
    private javax.swing.JProgressBar cpb2e;
    private javax.swing.JProgressBar cpb50c;
    private javax.swing.JProgressBar cpb50e;
    private javax.swing.JProgressBar cpb5c;
    private javax.swing.JProgressBar cpb5e;
    private javax.swing.JLabel ctotatual;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JTextArea descritivo;
    private javax.swing.JLabel e1;
    private javax.swing.JLabel e2;
    private javax.swing.JPanel ecraPagamentos;
    private javax.swing.JPanel ecraconfigV2;
    private javax.swing.JPanel ecraconfig_old;
    private javax.swing.JPanel ecratransacoes;
    private javax.swing.JLabel faltapagarlabel;
    private javax.swing.JLabel fmaneiodef;
    private javax.swing.JLabel fundomaneio;
    private javax.swing.JLabel fundomaneiodef;
    private javax.swing.JLabel fundomaneiodef1;
    private javax.swing.JLabel horas;
    private javax.swing.JLabel horas1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
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
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jammessage;
    private javax.swing.JLabel jammessage2;
    private javax.swing.JLabel lblcarregaV2;
    private javax.swing.JLabel moeda001;
    private javax.swing.JLabel moeda002;
    private javax.swing.JLabel moeda005;
    private javax.swing.JLabel moeda010;
    private javax.swing.JLabel moeda020;
    private javax.swing.JLabel moeda050;
    private javax.swing.JLabel moeda100;
    private javax.swing.JLabel moeda10cimg;
    private javax.swing.JLabel moeda1cimg;
    private javax.swing.JLabel moeda1img;
    private javax.swing.JLabel moeda200;
    private javax.swing.JLabel moeda20cimg;
    private javax.swing.JLabel moeda2cimg;
    private javax.swing.JLabel moeda2img;
    private javax.swing.JLabel moeda50cimg;
    private javax.swing.JLabel moeda5centimg;
    private javax.swing.JLabel n10;
    private javax.swing.JLabel n100;
    private javax.swing.JLabel n20;
    private javax.swing.JLabel n200;
    private javax.swing.JLabel n5;
    private javax.swing.JLabel n50;
    private javax.swing.JLabel n500;
    private javax.swing.JLabel nmoeda001;
    private javax.swing.JLabel nmoeda002;
    private javax.swing.JLabel nmoeda005;
    private javax.swing.JLabel nmoeda010;
    private javax.swing.JLabel nmoeda020;
    private javax.swing.JLabel nmoeda050;
    private javax.swing.JLabel nmoeda100;
    private javax.swing.JLabel nmoeda200;
    private javax.swing.JLabel nnota10;
    private javax.swing.JLabel nnota100;
    private javax.swing.JLabel nnota20;
    private javax.swing.JLabel nnota200;
    private javax.swing.JLabel nnota5;
    private javax.swing.JLabel nnota50;
    private javax.swing.JLabel nnota500;
    private javax.swing.JLabel nota10;
    private javax.swing.JLabel nota100;
    private javax.swing.JLabel nota100img;
    private javax.swing.JLabel nota10img;
    private javax.swing.JLabel nota20;
    private javax.swing.JLabel nota200;
    private javax.swing.JLabel nota200img;
    private javax.swing.JLabel nota20img;
    private javax.swing.JLabel nota5;
    private javax.swing.JLabel nota50;
    private javax.swing.JLabel nota500;
    private javax.swing.JLabel nota500img;
    private javax.swing.JLabel nota50img;
    private javax.swing.JLabel nota5img;
    private javax.swing.JLabel ntotalmoedasnotas;
    private javax.swing.JLabel nv100e;
    private javax.swing.JLabel nv10c;
    private javax.swing.JLabel nv10e;
    private javax.swing.JLabel nv1c;
    private javax.swing.JLabel nv1e;
    private javax.swing.JLabel nv200e;
    private javax.swing.JLabel nv20c;
    private javax.swing.JLabel nv20e;
    private javax.swing.JLabel nv2c;
    private javax.swing.JLabel nv2e;
    private javax.swing.JLabel nv50c;
    private javax.swing.JLabel nv50e;
    private javax.swing.JLabel nv5c;
    private javax.swing.JLabel nv5e;
    private javax.swing.JLabel operpass;
    private javax.swing.JPanel pag_rasp;
    private javax.swing.JTextField pagamentovalor;
    private javax.swing.JLabel pagolabel2;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private java.awt.Panel panel4;
    private java.awt.Panel panel5;
    private javax.swing.JProgressBar pb100e;
    private javax.swing.JProgressBar pb10c;
    private javax.swing.JProgressBar pb10e;
    private javax.swing.JProgressBar pb1c;
    private javax.swing.JProgressBar pb1e;
    private javax.swing.JProgressBar pb200e;
    private javax.swing.JProgressBar pb20c;
    private javax.swing.JProgressBar pb20e;
    private javax.swing.JProgressBar pb2c;
    private javax.swing.JProgressBar pb2e;
    private javax.swing.JProgressBar pb50c;
    private javax.swing.JProgressBar pb50e;
    private javax.swing.JProgressBar pb5c;
    private javax.swing.JProgressBar pb5e;
    private javax.swing.JLabel quantia;
    private javax.swing.JLabel raspeur;
    private javax.swing.JLabel retroceder_consulta;
    private javax.swing.JLabel retroceder_consulta1;
    private javax.swing.JLabel retroceder_pass;
    private javax.swing.JLabel retroceder_pass1;
    private javax.swing.JLabel retroceder_pass10;
    private javax.swing.JLabel retroceder_pass11;
    private javax.swing.JLabel retroceder_pass12;
    private javax.swing.JLabel retroceder_pass13;
    private javax.swing.JLabel retroceder_pass14;
    private javax.swing.JLabel retroceder_pass15;
    private javax.swing.JLabel retroceder_pass2;
    private javax.swing.JLabel retroceder_pass3;
    private javax.swing.JLabel retroceder_pass4;
    private javax.swing.JLabel retroceder_pass5;
    private javax.swing.JLabel retroceder_pass6;
    private javax.swing.JLabel retroceder_pass7;
    private javax.swing.JLabel retroceder_pass8;
    private javax.swing.JLabel retroceder_pass9;
    private javax.swing.JPanel scs_spo_consultas;
    private javax.swing.JLabel scsdts;
    private javax.swing.JLabel scsfwr;
    private javax.swing.JTextField senha;
    private javax.swing.JLabel spo100e;
    private javax.swing.JLabel spo10e;
    private javax.swing.JLabel spo200e;
    private javax.swing.JLabel spo20e;
    private javax.swing.JLabel spo50e;
    private javax.swing.JLabel spo5e;
    private javax.swing.JLabel spodts;
    private javax.swing.JLabel spofwr;
    private javax.swing.JLabel superpass;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalcarregadolb;
    private javax.swing.JLabel totalcarregamentos;
    private javax.swing.JLabel totalcofre;
    private javax.swing.JLabel totalcofrefecho;
    private javax.swing.JLabel totalcofrefecho1;
    private javax.swing.JLabel totalfaturado;
    private javax.swing.JLabel totalmaquinafecho;
    private javax.swing.JLabel totalmaquinafecho1;
    private javax.swing.JLabel totalmoedasnotas;
    private javax.swing.JLabel totalpagamentos;
    private javax.swing.JLabel totatual;
    private javax.swing.JLabel totcarregav2;
    private javax.swing.JLabel troco2;
    private javax.swing.JLabel trocolabel;
    private javax.swing.JLabel trocomoedas;
    private javax.swing.JLabel troconotas;
    private javax.swing.JLabel txtdemonatuais;
    private javax.swing.JTextField txtdenom;
    private javax.swing.JLabel valorperiodo;
    private javax.swing.JLabel valorperiodo1;
    private javax.swing.JTextField valorsaque;
    private javax.swing.JButton Ç;
    // End of variables declaration//GEN-END:variables
}
class MyPainter implements Painter<JProgressBar> {

    private final Color color;

    public MyPainter(Color c1) {
        this.color = c1;
    }
    @Override
    public void paint(Graphics2D gd, JProgressBar t, int width, int height) {
        gd.setColor(color);
        gd.fillRect(0, 0, width, height);
    }
}