/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Control.control;
import java.util.ArrayList;

/**
 *
 * @author USR_Toshiba
 */
public class Rol {
    private int codigo;
    private String descripcion;

    public Rol(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Rol() {
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Rol{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }
    
     public ArrayList cargar_rol(ArrayList x) throws ClassNotFoundException{
        x.clear();
        Control.control.conectar();
        control.ejecuteQuery("select * from rol where estado='Activo'");
        int cod = 0;
        String nom = "";
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                nom = control.rs.getString(2);
                x.add(new Grupo(cod, nom));
            }
            System.err.println("cargo " + x.size());
        } catch (Exception ex) {

        }
        return x;
    }
}

