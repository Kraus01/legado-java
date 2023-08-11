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
public class pagamentopos implements Serializable {
    public static final long serialVersionUID = 6529685098267757690L;
    public final String Fact;
    public final double v1;
   
    
    public final  String tipo;
    
    

    public pagamentopos(String tipo, String Fact, double v1) {
     
        
        
         
        this.tipo = tipo;
        this.Fact = Fact;
        this.v1 = v1;
        
        
    }
}

