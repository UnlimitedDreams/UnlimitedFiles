/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Britt
 */
public class Persona {
    private String cedula;
    private String nombre;
    private String apellido;
    private boolean estado;
    private int cod_usuario;
    private int nivel;

    public Persona(String cedula, String nombre, String apellido, boolean estado, int serie) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.cod_usuario = serie;
    }

    public Persona() {
    }

    public Persona(String cedula, String nombre, String apellido, int cod_usuario, int nivel) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cod_usuario = cod_usuario;
        this.nivel = nivel;
    }

 

 

  

    public int getSerie() {
        return cod_usuario;
    }

    public void setSerie(int serie) {
        this.cod_usuario = serie;
    }

    
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

  

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", estado=" + estado + ", cod_usuario=" + cod_usuario +  '}';
    }
   

    
}
