<%-- 
    Document   : prueba
    Created on : 15/04/2015, 05:25:28 PM
    Author     : USR_Toshiba
--%>

<%@page import="Control.control"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%!ArrayList<Usuario> Mis_usuarios = new ArrayList();
    String codi = "";%>
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
            $(document).ready(function() {
                var option = 3;
                $.post('Usuarios', {
                    opt: option,
                }, function(responseText) {
                    $('#Mis_dat').html(responseText);
                });
            });</script>
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
                <div id="Mis_dat" ></div>
            </center>
            <br/><br/><br/>

            <script src="Jquery/jquery-1.11.2.js"></script>

            </body>
            </html>
