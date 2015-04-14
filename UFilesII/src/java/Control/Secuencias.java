package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USR_Toshiba
 */
public class Secuencias {

    public static int Sequen(String sql) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteQuery(sql);
        int codigo=0;
        try{
            while(control.rs.next()){
                codigo=control.rs.getInt(1);
            }
        }catch(Exception ex){
            
        }
        return codigo+1;
    }

}
