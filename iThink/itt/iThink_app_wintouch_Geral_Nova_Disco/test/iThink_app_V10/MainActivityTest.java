/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import ITL_SCS_SPO.CURRENCY;
import ITL_SCS_SPO.SCS_SPO_event;
import com.github.lgooddatepicker.components.DatePicker;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class MainActivityTest {
    
    public MainActivityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDatePicker method, of class MainActivity.
     */
    @Test
    public void testGetDatePicker() {
        System.out.println("getDatePicker");
        MainActivity instance = null;
        DatePicker expResult = null;
        DatePicker result = instance.getDatePicker();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conetar method, of class MainActivity.
     */
    @Test
    public void testConetar() throws Exception {
        System.out.println("conetar");
        MainActivity instance = null;
        instance.conetar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of novovalorapagarsocket method, of class MainActivity.
     */
    @Test
    public void testNovovalorapagarsocket() throws Exception {
        System.out.println("novovalorapagarsocket");
        MainActivity instance = null;
        instance.novovalorapagarsocket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertJavaDateToSqlDate method, of class MainActivity.
     */
    @Test
    public void testConvertJavaDateToSqlDate() {
        System.out.println("convertJavaDateToSqlDate");
        Date date = null;
        Timestamp expResult = null;
        Timestamp result = MainActivity.convertJavaDateToSqlDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carrega method, of class MainActivity.
     */
    @Test
    public void testCarrega() {
        System.out.println("carrega");
        MainActivity instance = null;
        instance.carrega();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregapago method, of class MainActivity.
     */
    @Test
    public void testCarregapago() {
        System.out.println("carregapago");
        CURRENCY currency = null;
        MainActivity instance = null;
        instance.carregapago(currency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gravapagamento method, of class MainActivity.
     */
    @Test
    public void testGravapagamento() {
        System.out.println("gravapagamento");
        MainActivity instance = null;
        instance.gravapagamento();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gravapagamentocofre method, of class MainActivity.
     */
    @Test
    public void testGravapagamentocofre() {
        System.out.println("gravapagamentocofre");
        CURRENCY currency = null;
        MainActivity instance = null;
        instance.gravapagamentocofre(currency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pagamento method, of class MainActivity.
     */
    @Test
    public void testPagamento() {
        System.out.println("pagamento");
        CURRENCY currency = null;
        MainActivity instance = null;
        instance.pagamento(currency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of troco method, of class MainActivity.
     */
    @Test
    public void testTroco() {
        System.out.println("troco");
        MainActivity instance = null;
        instance.troco();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evento_pagamento_ocurrido method, of class MainActivity.
     */
    @Test
    public void testEvento_pagamento_ocurrido() {
        System.out.println("evento_pagamento_ocurrido");
        eventos ev = null;
        double vp = 0.0;
        String doc = "";
        MainActivity instance = null;
        instance.evento_pagamento_ocurrido(ev, vp, doc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cashboxremoved method, of class MainActivity.
     */
    @Test
    public void testCashboxremoved() {
        System.out.println("cashboxremoved");
        MainActivity instance = null;
        instance.cashboxremoved();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of semtroco method, of class MainActivity.
     */
    @Test
    public void testSemtroco() {
        System.out.println("semtroco");
        MainActivity instance = null;
        instance.semtroco();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of limpa method, of class MainActivity.
     */
    @Test
    public void testLimpa() {
        System.out.println("limpa");
        MainActivity instance = null;
        instance.limpa();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SCS_SPO_Event_Occurred method, of class MainActivity.
     */
    @Test
    public void testSCS_SPO_Event_Occurred() {
        System.out.println("SCS_SPO_Event_Occurred");
        SCS_SPO_event ev = null;
        CURRENCY currency = null;
        MainActivity instance = null;
        instance.SCS_SPO_Event_Occurred(ev, currency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class MainActivity.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        MainActivity.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
