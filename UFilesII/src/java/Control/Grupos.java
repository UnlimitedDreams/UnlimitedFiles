package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Modelo.Grupo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/Grupos"})
public class Grupos extends HttpServlet {

    ArrayList<Grupo> grup = new ArrayList();

    public boolean insertar_Grupo(String nombre) throws ClassNotFoundException {
        control.conectar();
        int codigo = Secuencias.Sequen("select max(idgrupo) from grupo");
        boolean r = control.ejecuteUpdate("insert into grupo values(" + codigo + ",'" + nombre + "','Activo')");
        return r;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Sesion_grupo = request.getSession(true);

        String opt = request.getParameter("opt");
        if (opt.equalsIgnoreCase("1")) {
            String codigo = request.getParameter("codigo_borrar");
            System.err.println("Entro a borrrar el codigo " + codigo);
            control.conectar();
            control.ejecuteUpdate("update grupo set estado='Inactivo' where idgrupo=" + codigo);
            control.cerrarConexion();
        } else if (opt.equalsIgnoreCase("2")) {
            String descripcion_gruppo = request.getParameter("des");
            System.err.println("entro " + descripcion_gruppo);
            boolean r = insertar_Grupo(descripcion_gruppo);
            try {
                out.println("entro ok");
            } catch (Exception ex) {

            }
        } else if (opt.equalsIgnoreCase("3")) {
            String codigo = request.getParameter("codigo_update");
            control.conectar();
            control.ejecuteQuery("select * from grupo where idgrupo=" + codigo);
            String descripcion = "";
            try {
                while (control.rs.next()) {
                    descripcion = control.rs.getString(2);
                }
                Sesion_grupo.setAttribute("descripcion_grupo", descripcion);
            } catch (Exception ex) {

            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
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
