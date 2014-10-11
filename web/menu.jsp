<%-- 
    Document   : menu
    Created on : 09/10/2014, 17:49:02
    Author     : Peri
--%>
<%@page import="Exceptions.NoException"%>
<%@page import="Modelo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">
<link href="dist/css/signin.css" rel="stylesheet">
<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<%@ page errorPage="proc/error.jsp" %>

<%                                                         //No puedo usar un bean acá porque será repetido en las paginas contenedoras.
    Usuario usr = (Usuario)session.getAttribute("usuario");
    
    if (usr.getClass() == Administrador.class) {
        RequestDispatcher rd = request.getRequestDispatcher("menu_admin.jsp");
        rd.include(request, response);
    } else if (usr.getClass() == Cliente.class) {
        RequestDispatcher rd = request.getRequestDispatcher("menu_cliente.jsp");
        rd.include(request, response);

    } else {
        throw new NoException("No logueado.");
    }

%>





