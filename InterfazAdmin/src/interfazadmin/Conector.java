/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import interfaz.acceso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author emilio
 */
public class Conector {
    private String driver="com.mysql.jdbc.Driver";
    private String cadenaConeccion="jdbc:mysql://127.0.0.1/cwm";
    private String usuario="root";
    private String contraseña="";
    public static Connection con;
    public static Statement sentencia;
    
    public void conectar(){
        try{
            Class.forName(driver);
            con=DriverManager.getConnection(cadenaConeccion, usuario, contraseña);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se ha podido establecer una coneccion con la BD"+e.getMessage());
        }
    }
    public int login(String user, String pass){
        int resultado=-1;
        try{
            sentencia = con.createStatement();
            ResultSet rs = sentencia.executeQuery("Select * from trabajadores Where user = '"+user+"' and pass = '"+pass+"'");
            if(rs.next()){
                acceso.puesto=rs.getInt("puesto");
                acceso.nE=rs.getInt("numeroEmpleado");
                switch(acceso.puesto){
                    case 0:
                        resultado=0;
                        break;
                    case 1:
                        case 2:
                        resultado=JOptionPane.showOptionDialog(
                                 null,
                                "Como desea accesar?", 
                                "Selector de opciones",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new Object[] { "Administrador de empleados", "Administrador de solicitudes", "Cancelar"},
                                "Administrador de empleados");
                        if(resultado==0){
                            resultado=2;
                        }else if(resultado==2){
                            resultado=0;
                        }
                        break;
                    default:
                        resultado=-1;                        
                }  
            }else{
                JOptionPane.showMessageDialog(null, "Problemas con usuario y/o contraseña");
                resultado=-1;
            }
        }catch(Exception e){    
            JOptionPane.showMessageDialog(null, "Error al conectar "+ e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    public void actualizar(String text, String tabla, String numSol){
        try{
            sentencia = con.createStatement();
            System.out.println("UPDATE "+tabla+" SET ordenStatus='"+text+"' WHERE numeroSolicitud='"+numSol+"'");
            PreparedStatement pps=con.prepareStatement("UPDATE "+tabla+" SET ordenStatus='"+text+"' WHERE numeroSolicitud='"+numSol+"'");
            pps.executeUpdate();
        }catch(Exception e){    
            JOptionPane.showMessageDialog(null, "Error al conectar "+ e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
