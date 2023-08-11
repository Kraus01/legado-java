/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ithinkdisco;

/**
 *
 * @author Fernando
 */
public class artigoclass {
    
    
    String Descricao;
    Double pvp1;
    Double pvp2;
    Double pvp3;
    int codigo;
    int iva;
    String familia;
    
    
    public void setDescricao(String descricao){
           Descricao=descricao;
            
            }
    
    public void setpvp1(double PVP1){
        pvp1=PVP1;
    }
    
    public void setpvp2(double PVP2){
        pvp2=PVP2;
    }
    
     public void setpvp3(double PVP3){
        pvp3=PVP3;
    }
   
      public void setcodigo(int Codigo){
        codigo=Codigo;
    }
   
      public void setiva(int Iva){
        iva=Iva;
    }
   
      public void setfamilia(String Familia){
        familia=Familia;
    }
   
      public String getDescricao(){
          
          
          return Descricao;
      }
      
      public double getpvp1(){
          
          return pvp1;
      }
       public double getpvp2(){
          
          return pvp2;
      }
     public double getpvp3(){
          
          return pvp3;
      }

     public int getcodigo(){
         return codigo;
     }

     public int getiva(){
         
         return iva;
     }

     public String getfamilia(){
         
         
         return familia;
     }
}




