/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

import java.io.Serializable;

/**
 *
 * @author Fernando
 */
public class interfaceonlinestatus implements Serializable {
    public static final long serialVersionUID = 6529685098267757690L;
    
    public final double valor;
   
    
    public final  String reciclador;
    
    

    public interfaceonlinestatus(String reciclador, double valor) {
     
        
        
         
        this.reciclador = reciclador;
        
        this.valor = valor;
        
        
    }
}

