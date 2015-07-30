/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Family
 */
public class Sequence {

    public static int seque(String sql) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery(sql);
        int cod=0;
        try{
            while(control.rs.next()){
                cod=control.rs.getInt(1);
            }
        }catch(Exception ex){
            
        }
        return cod+1;
    }
}
