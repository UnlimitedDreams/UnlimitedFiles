/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.control;
import Modelo.rol_List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author USR_Toshiba
 */
@ManagedBean
@SessionScoped
public class GestionRoles implements Serializable {

    ArrayList<rol_List> lista = new ArrayList();

    private int Cod_rol;
    private String descripcion;

    public int getCod_rol() {
        return Cod_rol;
    }

    public void setCod_rol(int Cod_rol) {
        this.Cod_rol = Cod_rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "GestionRoles{" + "Cod_rol=" + Cod_rol + ", descripcion=" + descripcion + '}';
    }

    public void crearRol() throws ClassNotFoundException {
        control.conectar();
        int codRol = codigoRol();
        boolean enviar = control.ejecuteUpdate("insert into Rol values(" + codRol + ",'" + descripcion + "','A');");
        System.err.println("" + codRol + ",'" + descripcion + "");

        if (enviar) {
            System.err.println("----inserto rol----");
        } else {
            System.err.println("error al insert");
        }
    }

    public int codigoRol() throws ClassNotFoundException {
        control.conectar();
        int codRol = 0;
        control.ejecuteQuery("select Max(cod_rol) from rol");
        try {
            while (control.rs.next()) {
                codRol = control.rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        return codRol + 1;
    }

    public void crudRol() throws ClassNotFoundException {
        control.conectar();

        boolean archivos = control.ejecuteQuery("select * from rol");

        lista.clear();
        try {

            while (control.rs.next()) {
                lista.add(new rol_List(control.rs.getInt(1), control.rs.getString(2), control.rs.getString(3)));
            }
        } catch (Exception ex) {
        }

    }

    public ArrayList<rol_List> getLista() {
        return lista;
    }

    public void setLista(ArrayList<rol_List> lista) {
        this.lista = lista;
    }

    public void eliminarRol(int x) throws ClassNotFoundException {

        control.conectar();
        System.out.println("ENTRO");
        boolean delete = control.ejecuteUpdate("delete from rol where cod_rol=" + x + ";");
        System.out.println("codigo =" + x + "Eliminado");

        if (delete) {
            System.out.println("elimino el rol");
        } else {
            System.err.println("Error al eliminar el rol indicado");
        }

    }

}
