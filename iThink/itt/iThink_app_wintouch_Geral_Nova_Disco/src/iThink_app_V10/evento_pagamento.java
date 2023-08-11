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
public interface evento_pagamento {
    public void evento_pagamento_ocurrido(eventos ev, double vp, String doc);
    public void evento_server(eventos ev, String vpp, String doc);
   
    
}
