<%-- 
    Document   : prueba
    Created on : 15/04/2015, 05:25:28 PM
    Author     : USR_Toshiba
--%>

<%@page import="Control.control"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%!ArrayList<Usuario> Mis_usuarios = new ArrayList();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>UnlimitedFiles-Solicion Corporativa</title>
        <link href="bootstrap-3.3.4/css/bootstrap.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                var option = 3;
                $.post('Usuarios', {
                    opt: option,
                }, function (responseText) {
                    $('#tabla_usuarios').html(responseText);
                });
            });
        </script>
    </head>

    <body>
    <nav class="navbar navbar-inverse navbar-fixed-top"> 
        <div class="container-fluid">
            <div class="navbar-header"> 
                <a class="navbar-brand" href="#"> 
                    <img src="img/text3338.png" style="width: 30px; height: 30px;"/> 
                </a> 
                <ul class="nav navbar-nav"> 
                    <li> <a href="main.html"><h5 style="color:#006400">Home</h5></a></li>
                    <li><a href="http://getbootstrap.com/examples/starter-template/#"><h5 style="color:#006400">Productos</h5></a></li>
                    <li><a href="http://getbootstrap.com/examples/starter-template/#"><h5 style="color:#006400">Contactos</h5></a></li>
                </ul>                 
            </div> 
        </div> 
    </nav>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="container">
        <div class="row">
            <center>
                <h4>UnlimitedFiles</h4>
                <h5>
                    Esta Seccion de Unlimited Files le permitira subir cualquier tipo de documento de una manera 
                    muy segura para su organizacion solo debe darle click
                </h5>
                <br/><br/>
                <div style="display: none;" id="tabla_usuarios">


                    <script src="Jquery/jquery-1.11.2.js"></script>

                    <script>
            function borrar(codigo) {
                alert("Seguro que desea borrar ");
                $(document).ready(function () {
                    var option = 4;
                    $.post('Usuarios', {
                        codigo_borrar: codigo,
                        opt: option,
                    }, function (responseText) {
                        $("#Prueb_usu").html(responseText);
                        $("#tabla_usuarios").load("Crud_usuarios.html");
                    });
                });
            }
            function update(codigo) {
                $(document).ready(function () {
                    var option = 5;
                    $.post('Usuarios', {
                        codigo_borrar: codigo,
                        opt: option,
                    }, function (responseText) {
                        $("#tabla_usuarios").hide();
                        $("#Updat_usu").html(responseText);
                    });
                });
            }</script>


                    <center> 
                        <br/><br/><br/>
                        <table>
                            <%
                                Mis_usuarios = new Usuario().Traer_Usuarios(Mis_usuarios);
                                Usuario temp = null;
                                for (int i = 0; i < Mis_usuarios.size(); i++) {
                                    temp = (Usuario) Mis_usuarios.get(i);
                                    out.println("<tr>");
                                    out.println("<td>" + temp.getCedula() + "</td>");
                                    out.println("<td>" + temp.getNombre() + "</td>");
                                    out.println("<td>" + temp.getApellido() + "</td>");
                                    out.println("<td>" + temp.getFecha_naci() + "</td>");
                                    out.println("<td>" + temp.getSexo() + "</td>");
                                    out.println("<td><input type=\"button\" class=\"btn btn-info\" value=\"Borrar\" id=\"borr\" onclick=borrar(" + temp.getCedula() + ")></td>");
                                    out.println("<td><input type=\"button\"  class=\"btn btn-info\" value=\"Actualizar\"  onclick=update(" + temp.getCedula() + ")></td>");
                                    out.println("</tr>");
                                }
                                control.cerrarConexion();
                            %>
                        </table>
                    </center>
                </div>
            </center>
        </div>
    </div>
</body>
</html>
