package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/login_ser"})
public class login_ser extends HttpServlet {

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
        System.err.println("Entro al servelts");
        String usu = request.getParameter("usuario");
        String pas = request.getParameter("Clave");
        control.conectar();
        control.ejecuteQuery("select usuario.idusuario,rol.cod_rol from usuario,usuario_rol,rol where\n"
                + "                usuario.idusuario=usuario_rol.idusuario and \n"
                + "                rol.cod_rol=usuario_rol.cod_rol and usuario.nickname='" + usu + "' and\n"
                + "                usuario.password='" + pas + "' ");
        boolean esta = false;
        int rol = 0;
        String codigo = "";
        try {
            while (control.rs.next()) {
                System.err.println("entro a ciclo");
                codigo = control.rs.getString(1);
                rol = control.rs.getInt(2);
                esta = true;
            }
        } catch (Exception ex) {

        }
        HttpSession s = request.getSession(true);
        if (rol == 1) {
            s.setAttribute("idUsuario", codigo);
            s.setAttribute("idRol",rol);
            response.sendRedirect("main.html");
        }
        if (esta == false) {
            response.sendRedirect("inicio.html");
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
            Logger.getLogger(login_ser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login_ser.class.getName()).log(Level.SEVERE, null, ex);
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
