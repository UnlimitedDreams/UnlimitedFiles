
package Modelo;

import Control.control;
import java.util.ArrayList;

public class Grupo {
    private int codigo;
    private String nombre;

    public Grupo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Grupo() {
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Grupo{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
    public ArrayList cargar_grupos(ArrayList x) throws ClassNotFoundException{
        x.clear();
        Control.control.conectar();
        control.ejecuteQuery("select * from Grupo where estado='Activo'");
        int cod = 0;
        String nom = "";
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                nom = control.rs.getString(2);
                x.add(new Grupo(cod, nom));
            }
        } catch (Exception ex) {

        }
        return x;
    }
}
