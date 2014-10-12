<%-- 
    Document   : detalles_de_compra
    Created on : Oct 12, 2014, 2:43:59 AM
    Author     : Dev
--%>

<%@page import="java.util.List"%>
<%@page import="Controladora.CCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="proc/error.jsp" %>
<%@ taglib prefix="lg" uri="WEB-INF/tlds/logincheck.tld"%>
<lg:logchk/>
<jsp:useBean id="compra" class="Modelo.Compra" scope="request"/>
<jsp:setProperty name="compra" property="id" />

<%
    if (compra.getId() == 0) {
        throw new NoException("Debe seleccionar un detalle.");
    }

    CCompra cComp = new CCompra();
    List<Detalle> detalles = cComp.getDetalles(compra.getId());
    pageContext.setAttribute("detalles", detalles, PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles de compra</title>
        <%@ include file="menu.jsp"%> 
    </head>
    <body>
        <div style="width: 321px; margin-left: auto; margin-right: auto;">
            <a  href="compras.jsp"> Ir a compras</a>
        </div>
        <br>
        <c:forEach items="${detalles}" var="d">
            <div style="width: 321px; margin-left: auto; margin-right: auto;">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>Prducto:</td>
                            <td>${d.getProducto().getNombre()}</td>
                        </tr>
                        <tr>
                            <td>Cantidad:</td>
                            <td>${d.cantidad}</td>
                        </tr>
                        <tr>
                            <td>Precio unitario:</td>
                            <td>${d.getProducto().getPrecio()}</td>
                        </tr>
                        <tr>
                            <td>Precio total:</td>
                            <td>${d.precio}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </body>
</html>
