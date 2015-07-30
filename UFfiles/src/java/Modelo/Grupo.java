/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javax.faces.context.FacesContext;

/**
 *
 * @author USR_Toshiba
 */
public class Grupo {
    private int cod;
    private String Nombre;
    
    public Grupo(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
  public Grupo(int cod, String Nombre) {
        this.cod = cod;
        this.Nombre = Nombre;
    }

    public Grupo() {
    }
    
    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

   
    
}
