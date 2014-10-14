<%-- 
    Document   : main
    Created on : 09/10/2014, 19:09:32
    Author     : Peri
--%>

<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map"%>
<%@page import="Controladora.CProducto"%>
<%@page import="Modelo.*"%>
<%@page import="Exceptions.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="proc/error.jsp" %>

<jsp:useBean id="usuario" class="Modelo.Usuario" scope="session"/>

<%! 
    List<Producto> lstProd;
%>

<%
    if (usuario.getClass() != Administrador.class && usuario.getClass() != Cliente.class) {
        throw new loginException();
    }

    CProducto cProd = new CProducto();
    lstProd = cProd.getAll();
    
    pageContext.setAttribute("lstProd", lstProd, PageContext.PAGE_SCOPE);
    session.setAttribute("productos", lstProd);

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de productos</title>
        <%@ include file="menu.jsp"%> 
    </head>
    <body>
        <h1 align="center">Lista de productos</h1>

        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">Productos</div>

            <c:forEach items="${lstProd}" var="prod">
                <form method="post" action="agregarCarrito">
                    <div id="lProd" align="center" class="panel panel-default">
                        <table class="table table-bordered">
                            <tr>
                                <td style="width: 319px">Prducto:</td>
                                <td><c:out value="${prod.nombre}"/></td>
                            </tr>
                            <tr>
                                <td style="width: 319px">Precio:</td>
                                <td>$ <c:out value="${prod.precio}"/></td>
                            </tr>
                            <tr>
                                <td style="width: 319px">Stock:</td>
                                <td><c:out value="${prod.stock}"/></td>
                            </tr>
                        </table>
                        <div class="auto-style1" style="width: 322px; height: 27px">Cantidad: <input type="number" name="cantidad" value="1" min="1" max="<c:out value="${prod.stock}"/>" style="width: 50px">
                            <input type="hidden" name="prodId" value="<c:out value="${prod.id}"/>"/>
                            <input name="Button1" type="submit" value="Agregar al carrito" />&nbsp;
                            <br />
                            <br />
                        </div>
                    </div>
                </form>
            </c:forEach>

    </body>
</html>
