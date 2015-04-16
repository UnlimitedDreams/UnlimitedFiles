package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author USR_Toshiba
 */
@WebServlet(urlPatterns = {"/file"})
public class file extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void update(String cod, String ruta) throws ClassNotFoundException {
        control.conectar();
        control.ejecuteUpdate("update rutas set ruta='" + ruta + "' where cod_rutas=" + cod);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession rut = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        HttpSession gr = request.getSession(true);
        String grup = (String) gr.getAttribute("Grupo");
        String cod = (String) gr.getAttribute("Cod");

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.err.println(" 1- " + isMultipart + " reqe " + request);
        File ruta = new File("");
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                System.out.println("tam " + iterator.hasNext() + " y " + items.iterator() + "-- " + items);
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        String root = getServletContext().getRealPath("/");
                        System.err.println("Subirrrrrrr doc");
                        File folder = new File("web\\Documentos\\" + grup);
                        folder.mkdir();
                        if (folder.exists()) {
                            System.out.println("exite");
                        }
                        File path = new File(root + "/../../web/Documentos/" + grup);
//                        File path = new File(root + "d:\\Users\\USR_Toshiba\\Desktop\\Doc");
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }

                        String sTexto = "" + fileName;
                        String sCadenaSinBlancos = "";
                        for (int x = 0; x < sTexto.length(); x++) {
                            if (sTexto.charAt(x) != ' ') {
                                sCadenaSinBlancos += sTexto.charAt(x);
                            }
                        }
                        File uploadedFile = new File(path + "/" + sCadenaSinBlancos);
                        File Nuevodoc = new File(sCadenaSinBlancos);
                        uploadedFile.renameTo(Nuevodoc);
                        item.write(uploadedFile);

                        update(cod, "Documentos/" + grup + "/" + sCadenaSinBlancos);
                        response.sendRedirect("main.html");

//                        fi.setAttribute("Ruta", uploadedFile);
//                        BeanArchivo archivo = new BeanArchivo();
//                        archivo.setNombre(fileName);
//                        archivo.setRuta(uploadedFile.getAbsolutePath());
//                        daoarchivo.insert(archivo);
                    }
                }
//                rut.setAttribute("Ruta", ruta);
//                response.sendRedirect("Subir_Documento_2.html");

            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet file</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet file at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
