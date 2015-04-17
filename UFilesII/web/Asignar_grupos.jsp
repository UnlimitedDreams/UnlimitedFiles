<%-- 
    Document   : prueba
    Created on : 15/04/2015, 05:25:28 PM
    Author     : USR_Toshiba
--%>

<%@page import="Control.control"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Grupo"%>
<%@page import="Modelo.Usuario"%>
<%!ArrayList<Grupo> Mis_grupos = new ArrayList();%>
<%!ArrayList<Grupo> Mis_usuarios = new ArrayList();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>UnlimitedFiles-Solicion Corporativa</title>
        <link href="bootstrap-3.3.4/css/bootstrap.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <!-- <script>
             $(document).ready(function() {
                 var option = 3;
                 $.post('Usuarios', {
                     opt: option,
                 }, function(responseText) {
                     $('#tabla_usuarios').html(responseText);
                 });
             });</script>-->
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
            </center>
            <center> 
                <br/><br/><br/>

                <%
                    Grupo gru = new Grupo();
                    Mis_grupos = gru.cargar_grupos(Mis_grupos);
                    Grupo temp = null;
                    out.println("<select id=\"pop\">");
                    for (int i = 0; i < Mis_grupos.size(); i++) {
                        temp = (Grupo) Mis_grupos.get(i);
                        out.println("<option value=" + temp.getCodigo() + "-" + temp.getNombre() + "/>" + temp.getNombre() + "</option>");
                    }
                    out.println("</select>");
                %>
                <br/><br/>

            </center>
                <a href="#" id="Buscar_per" onclick="traer()">Buscar</a>
            <script>
                function traer(){
                    $(document).ready(function() {
                        var option = 7;
                        var desc = $("#pop").val();
                        $.post('Usuarios', {
                            opt: option,
                            des: desc,
                        }, function(responseText) {
                            $("#Table_usu").html(responseText);
                          //   window.location.reload();
                        });
                    });
                }
            </script>

            <div id="Table_usu"></div>
            <br/><br/>




        </div>
        <script src="Jquery/jquery-1.11.2.js"></script>

    </body>
</html>
