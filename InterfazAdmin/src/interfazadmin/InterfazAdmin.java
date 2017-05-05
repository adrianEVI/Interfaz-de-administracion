/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import interfaz.acceso;
import java.sql.Connection;

/**
 *
 * @author emilio
 */
public class InterfazAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        acceso ventana =new acceso(); 
        ventana.setVisible(true);
    }
    
}
