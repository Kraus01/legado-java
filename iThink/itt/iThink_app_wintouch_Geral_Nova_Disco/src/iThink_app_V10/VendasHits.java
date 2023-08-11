/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iThink_app_V10;

/**
 *
 * @author Fernando
 */
public class VendasHits {
    
    
       private final String Hora;
      private final int Vendas;
      
  
  public VendasHits(String Hora, int Vendas){
      
      this.Hora = Hora;
      this.Vendas = Vendas;
     
      
  }
 public String getHora(){
     return Hora;
     
 }
 
 public int getVendas(){
     
     return Vendas;
     
 }
}
