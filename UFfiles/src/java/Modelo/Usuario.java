/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.Date;

/**
 *
 * @author Microinformatica
 */
public class Usuario {
    private String cedula;
    private String nombre;
    private String apellido;
    private int rol;
    private String rol_nom;
    private String usuario;
    private String correo;
    private String password;
    private Date Fecha_naci;

    public Usuario(String cedula, String nombre, String apellido, int rol, String rol_nom) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.rol_nom = rol_nom;
    }

    public Usuario(String cedula, String nombre, String apellido, String rol_nom, String usuario, String correo, String password, Date Fecha_naci) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol_nom = rol_nom;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.Fecha_naci = Fecha_naci;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha_naci() {
        return Fecha_naci;
    }

    public void setFecha_naci(Date Fecha_naci) {
        this.Fecha_naci = Fecha_naci;
    }

   
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", rol_nom=" + rol_nom + ", usuario=" + usuario + ", correo=" + correo + ", password=" + password + ", Fecha_naci=" + Fecha_naci + '}';
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
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getRol_nom() {
        return rol_nom;
    }

    public void setRol_nom(String rol_nom) {
        this.rol_nom = rol_nom;
    }
    
    
    
}
