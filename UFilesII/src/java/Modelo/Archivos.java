/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author USR_Toshiba
 */
public class Archivos {

    private int codigo_archi;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private String ruta;
    private String Estado;
    private String Tipo_doc;
    private int cod_revicion;

    public Archivos(int codigo, String nombre, String descripcion, Date fecha, String ruta) {
        this.codigo_archi = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ruta = ruta;
    }

    public Archivos(int cod_revicion,int cod,String nombre, Date fecha, String Estado, String Tipo_doc,String ruta) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.Estado = Estado;
        this.Tipo_doc = Tipo_doc;
        this.codigo_archi=cod;
        this.ruta=ruta;
        this.cod_revicion=cod_revicion;
    }

    public Archivos(int codigo_archi, String nombre, String descripcion, Date fecha, String ruta, String Estado) {
        this.codigo_archi = codigo_archi;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ruta = ruta;
        this.Estado = Estado;
    }
    

    public Archivos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getCodigo() {
        return codigo_archi;
    }

    public void setCodigo(int codigo) {
        this.codigo_archi = codigo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getTipo_doc() {
        return Tipo_doc;
    }

    public void setTipo_doc(String Tipo_doc) {
        this.Tipo_doc = Tipo_doc;
    }

    public int getCodigo_archi() {
        return codigo_archi;
    }

    public void setCodigo_archi(int codigo_archi) {
        this.codigo_archi = codigo_archi;
    }

    public int getCod_revicion() {
        return cod_revicion;
    }

    public void setCod_revicion(int cod_revicion) {
        this.cod_revicion = cod_revicion;
    }

}
