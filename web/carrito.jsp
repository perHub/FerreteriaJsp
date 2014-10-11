<%-- 
    Document   : carrito
    Created on : 10/10/2014, 18:44:43
    Author     : Peri
--%>

<%@page import="Exceptions.*"%>
<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"/>
<jsp:useBean id="carrito" class="Modelo.Compra" scope="session"/>

<%
    if (usuario.getClass() != Cliente.class) {
                throw new AdminClienteException(false);
            }
    else if(usuario.getClass() != Administrador.class){
        throw new NoException("No logueado.");
    }
    
    if(carrito.getDetalles().isEmpty()){
        throw new NoException("Carrito vacío.");
    }
%>
<html>
    <head>
        <title>Carrito</title>
        <%@ include file="menu.jsp"%> 
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
