/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.control;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author USR_Toshiba
 */
public class Usuario {

    private String cedula;
    private String nombre;
    private String Apellido;
    private String Fecha_naci;
    private String Sexo;

    public Usuario(String cedula, String nombre, String Apellido, String Fecha_naci, String Sexo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.Fecha_naci = Fecha_naci;
        this.Sexo = Sexo;
    }

    public Usuario() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getFecha_naci() {
        return Fecha_naci;
    }

    public void setFecha_naci(String Fecha_naci) {
        this.Fecha_naci = Fecha_naci;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public ArrayList Traer_Usuarios(ArrayList Mis_usuarios) throws ClassNotFoundException {
        Mis_usuarios.clear();
        control.conectar();
        control.ejecuteQuery("select * from persona where estado='Activo'");
        int cod = 0;
        String nom = "", apellido = "", sexo = "";
        Date fecha = null;
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                nom = control.rs.getString(2);
                fecha = control.rs.getDate(3);
                apellido = control.rs.getString(4);
                sexo = control.rs.getString(5);
                Mis_usuarios.add(new Usuario("" + cod, nom, "" + fecha, apellido, sexo));
            }
        } catch (Exception ex) {
            System.out.println("ex");
        }

        return Mis_usuarios;
    }

    public void borrar(String cod) throws ClassNotFoundException {
        System.err.println("Entro a borrar " +cod);
        control.conectar();
        control.ejecuteUpdate("update persona set estado='Inactivo' where cedula=" + cod);
    }
}
