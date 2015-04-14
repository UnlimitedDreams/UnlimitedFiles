package Control;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USR_Toshiba
 */
public class prue {

    public static void main(String[] args) {
//	    String sTexto = "Documento/informatica/palabra opalabra y palabra.docx";
//	    String sTextoBuscado = " ";
//	    int contador = 0;
//
//	    while (sTexto.indexOf(sTextoBuscado) > -1) {
//	      sTexto = sTexto.substring(sTexto.indexOf(
//	        sTextoBuscado)+sTextoBuscado.length(),sTexto.length());
//	      contador++; 
//	    }
//
//	    System.out.println (contador);
//            

        // Cadena de texto
        String sTexto = "Documento/informatica/palabraopalabraypalabra.docx";
        String sCadenaSinBlancos = "";
        for (int x = 0; x < sTexto.length(); x++) {
            if (sTexto.charAt(x) != ' ') {
                sCadenaSinBlancos += sTexto.charAt(x);
            }
        }
        System.out.println(sCadenaSinBlancos);

//	    // 2. Mediante un StringTokenizer cuyos delimitadores sean
//        // espacios en blanco
//        sCadenaSinBlancos = "";
//
//        StringTokenizer stTexto = new StringTokenizer(sTexto);
//
//        while (stTexto.hasMoreElements()) {
//            sCadenaSinBlancos += stTexto.nextElement();
//        }
//
//        System.out.println(sCadenaSinBlancos);

    }
}
