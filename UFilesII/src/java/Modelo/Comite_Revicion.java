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
public class Comite_Revicion {
    private int cod;
    private int nivel;
    private String estado;
    private boolean encontrado;
    private String Encargado;
    private String titulo;
    private String comentario;
    private String correo;

    public Comite_Revicion(int cod, int nivel, String estado, boolean encontrado,String correo,String titulo) {
        this.cod = cod;
        this.nivel = nivel;
        this.estado = estado;
        this.encontrado = encontrado;
        this.correo=correo;
        this.titulo=titulo;
    }

    public Comite_Revicion(String estado, String Encargado,String comentario) {
        this.estado = estado;
        this.Encargado = Encargado;
        this.comentario=comentario;
    }

    public Comite_Revicion() {
    }

    public Comite_Revicion(int cod, String correo) {
        this.cod = cod;
        this.correo = correo;
    }
    

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Comite_Revicion{" + "cod=" + cod + ", nivel=" + nivel + ", estado=" + estado + ", encontrado=" + encontrado + '}';
    }

    public String getEncargado() {
        return Encargado;
    }

    public void setEncargado(String Encargado) {
        this.Encargado = Encargado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
