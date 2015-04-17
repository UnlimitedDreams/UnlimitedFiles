package org.apache.jsp.vista;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">\n");
      out.write("<xhtml xmlns=\"http://www.w3.org/1999/xhtml\"\n");
      out.write("       xmlns:h=\"http://xmlns.jcp.org/jsf/html\">\n");
      out.write("\n");
      out.write("    <h:head>\n");
      out.write("        <h:meta charset=\"utf-8\"></h:meta>\n");
      out.write("        <h:title>Unlimited Files</h:title>\n");
      out.write("        <h:meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n");
      out.write("        <!-- css -->\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" />\n");
      out.write("        <link  href = \"bootstrap-3.3.4/css/bootstrap.css\"  rel = \"stylesheet\" ></link>\n");
      out.write("        <script src=\"Jquery/jquery-1.11.2.js\"></script>\n");
      out.write("    </h:head>\n");
      out.write("    <h:body>\n");
      out.write("        <nav  class = \"navbar navbar-inverse navbar-fixed-top\" > \n");
      out.write("            <div  class= \"container-fluid\" >\n");
      out.write("                <div  class= \"navbar-header\" > \n");
      out.write("                    <a  class= \"navbar-brand\"  href= \"#\" > \n");
      out.write("                        <img src= \"img/text3338.png\" style=\"width: 27px; height: 27px;\"></img> \n");
      out.write("                    </a> \n");
      out.write("                    <ul class=\"nav navbar-nav\"> \n");
      out.write("                        <li> <a  href=\"inicio.html\"><h4 style=\"color:#006400\">Home</h4></a></li>\n");
      out.write("                        <li><a href=\"http://getbootstrap.com/examples/starter-template/#\"><h4 style=\"color:#006400\">Productos</h4></a></li>\n");
      out.write("                        <li><a href=\"http://getbootstrap.com/examples/starter-template/#\"><h4 style=\"color:#006400\">Contactos</h4></a></li>\n");
      out.write("                    </ul>                 \n");
      out.write("                </div> \n");
      out.write("            </div> \n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"jumbotron3\" >\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"jumbotron2\" >\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <ul class=\"breadcrumb2\">\n");
      out.write("                    <center><h4>Administrador</h4> </center> \n");
      out.write("                </ul></div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <br></br>\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <h1>GESTION DE ADMINISTRADOR</h1>\n");
      out.write("                <!--<div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"gestionUsuariosAdmon.html\" class=\"thumbnail\" >\n");
      out.write("                        <img src=\"img/slides/user197.png\" style=\"height: 100px; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center> <h3>Gestion de usuarios</h3></center>\n");
      out.write("                    </div>\n");
      out.write("                </div>-->\n");
      out.write("\n");
      out.write("                <div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"Crud_roles.html\" class=\"thumbnail\">\n");
      out.write("                        <img src=\"img/slides/network11.png\" style=\"height: 100px; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center>  <h3>Gestion de  roles</h3></center>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"crud_Grupos.jsp\" class=\"thumbnail\">\n");
      out.write("                        <img src=\"img/slides/group9.png\" style=\"height: 100px ; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center>  <h3>Gestion de  grupos de trabajo</h3></center>\n");
      out.write("                    </div>                   \n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"Crud_usuario_admon.jsp\" class=\"thumbnail\">\n");
      out.write("                        <img src=\"img/slides/group9.png\" style=\"height: 100px ; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center>  <h3>Gestion de  Usuarios</h3></center>\n");
      out.write("                    </div>                   \n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <br></br>\n");
      out.write("                <h1>GESTION OPERARIA</h1>\n");
      out.write("                <div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"Subir_Documento_2.html\" class=\"thumbnail\" >\n");
      out.write("                        <img src=\"img/slides/suitcase6.png\" style=\"height: 100px ; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center> <h3>Subir archivos</h3></center>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("              \n");
      out.write("                <div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"MisGrupos.html\" class=\"thumbnail\">\n");
      out.write("                        <img src=\"img/slides/manager3 .png\" style=\"height: 100px ; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center>  <h3>Mis grupos</h3></center>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-6 col-md-4\">\n");
      out.write("                    <a href=\"MisDatos.jsp\" class=\"thumbnail\">\n");
      out.write("                        <img src=\"img/slides/manager3 .png\" style=\"height: 100px ; width: 100px\" />\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"caption\">\n");
      out.write("                        <center>  <h3>Mis Datos</h3></center>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src = \"bootstrap-3.3.4/js/bootstrap.min.js\" ></script>\n");
      out.write("    </h:body>\n");
      out.write("</xhtml>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
