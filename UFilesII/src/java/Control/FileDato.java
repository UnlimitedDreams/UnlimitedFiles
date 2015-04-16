/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Grupo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USR_Toshiba
 */
@WebServlet(name = "FileDato", urlPatterns = {"/FileDato"})
public class FileDato extends HttpServlet {

    ArrayList<Grupo> grup = new ArrayList();

    public void cargar_grupos(String id_usu) throws ClassNotFoundException {
        control.conectar();
        grup.clear();
        control.ejecuteQuery("select grupo.* from grupo,usuario,usuariogrupo\n"
                + "where usuario.idusuario=usuariogrupo.idusuario and \n"
                + "usuariogrupo.idgrupo=grupo.idgrupo and \n"
                + "usuario.idusuario=" + id_usu);
        int cod = 0;
        String descrip = "";
        try {
            while (control.rs.next()) {
                cod = control.rs.getInt(1);
                descrip = control.rs.getString(2);
                grup.add(new Grupo(cod, descrip));
            }
        } catch (Exception ex) {

        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        control.conectar();
        HttpSession s = request.getSession(true);
        String opt = request.getParameter("opt");
        PrintWriter out = response.getWriter();
        System.out.println("opton " + opt);

        if (opt.equalsIgnoreCase("1")) {
            System.err.println("entro");
            String idUsu = (String) s.getAttribute("idUsuario");
            cargar_grupos(idUsu);
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<script src=\"Jquery/jquery-1.11.2.js\"></script>");
                out.println("<script> "
                        + "$(document).ready(function() {\n"
                        + "                $(\"#Carg\").click(function() {\n"
                        + "                    var option = 2;\n"
                        + "                    var Nombre = $(\"#Nom\").val();\n"
                        + "                    var Descripcion = $(\"#Descrip\").val();\n"
                        + "                    var Grup = $(\"#Gru\").val();\n"
                        + "                    $.post('FileDato', {\n"
                        + "                        opt: option,\n"
                        + "                        Nom:Nombre,\n"
                        + "                        Descrip:Descripcion,\n"
                        + "                        Grupo:Grup\n"
                        + "                    }, function(responseText) {\n"
                        + "                    $('#SubirDoc').html(responseText);"
                        + "                    });\n"
                        + "                });\n"
                        + "            });"
                        + "</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("   <form >\n"
                        + "                            <table >\n"
                        + "                                <tr><td><h4>Nombre</h4></td></tr>\n"
                        + "                                <tr><td><input class=\"form-control\" style=\"width: 100%; type=\"text\" id=\"Nom\"></td></tr>\n"
                        + "                                <tr><td><h4>Descripcion</h4> </td></tr>\n"
                        + "                                <tr><td> <textarea class=\"form-control\" style=\"width: 100%\" id=\"Descrip\"/>\n"
                        + "</td></tr> " + ""
                        + "  <tr><td><h4>Grupo</h4> </td></tr>\n");
                out.println(" <tr> <td><select id=\"Gru\" class=\"form-control\" >");
                Grupo temp = null;
                for (int i = 0; i < grup.size(); i++) {
                    temp = (Grupo) grup.get(i);
                    out.println("  <option value=\"" + temp.getCodigo() + "-" + temp.getNombre() + "\" >" + temp.getNombre() + "</option>");
                }
                out.println(" </select></td>\n"
                        + "                </tr>\n");
                out.println("                            </table>\n"
                        + "                            <br>\n"
                        + "                            <input type=\"button\" id=\"Carg\"  value=\"Cargar\" >\n"
                        + "                        </form>");

                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        } else if (opt.equalsIgnoreCase("2")) {
            System.out.println("entro a option 2");
            int codigo = Secuencias.Sequen("select max(cod_rutas) from rutas");
            int codigoGrupodoc = Secuencias.Sequen("select max(grupodoc) from grupodocu");
            String nom = request.getParameter("Nom");
            String descrip = request.getParameter("Descrip");
            String grp = request.getParameter("Grupo");

            String gruppo[] = grp.split("-");
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            String fechas = "" + dia + "-" + mes + "-" + año;
            System.out.println("fecha " + fechas);
            boolean r = control.ejecuteUpdate("insert into rutas values(" + codigo + ",'-','" + nom + "','" + descrip + "','" + fechas + "')");
            if (r) {
                System.out.println("Entro query");
                boolean f = control.ejecuteUpdate("insert into  grupodocu values(" + codigoGrupodoc + "," + codigo + "," + gruppo[0] + ")");
                System.out.println("f " + f);
            }
            HttpSession gr = request.getSession(true);
            String codigito = "" + codigo;
            System.out.println("paso 1" + codigito);
            gr.setAttribute("Grupo", gruppo[1]);
            System.out.println("paso 2 " + gruppo[1]);
            gr.setAttribute("Cod", codigito);
            try {
                out.println("<form action=\"file\" enctype=\"multipart/form-data\" method=\"post\">\n"
                        + "                            <table>\n"
                        + "                                <tr><td>  <img src=\"img/FilesUpload.png\" ></td></tr>\n"
                        + "                                <tr><td><input type=\"file\" name=\"archivo\"></td></tr>\n"
                        + "                            </table>\n"
                        + "                            <br>\n"
                        + "                            <input type=\"submit\"  class=\"btn btn-success\" value=\"Cargar\">\n"
                        + "                        </form>");
            } catch (Exception ex) {

            }
            //response.sendRedirect("Subir_Documento.html");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileDato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
